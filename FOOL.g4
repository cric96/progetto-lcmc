grammar FOOL;

//header: serve per importare i package nel SVM parser (definisco quali package il parser avrà bisogno di usare)
@header{
import java.util.*;
import java.util.stream.*;
import ast.*;
import ast.leaf.*;
import ast.core.*;
import ast.type.*;
import ast.oo.*;
import ast.exception.*;
import ast.operator.*;
}

//attributi della classe parser, verranno usati durante il parsing
@parser::members{
	//TODO ricorda di mettere il concetto di symbol table
	private final SymbolTable symTable = new StandardSymbolTable();
	private final ClassTable classTable = new ClassTable();
	//livello ambiente con dichiarazioni piu' esterno è 0 (prima posizione ArrayList) invece che 1 (slides)
	//il "fronte" della lista di tabelle è symTable.get(nestingLevel)
	private boolean classesDecl = false;
	private int classOffset = -1;
}


@lexer::members {
	int lexicalErrors=0;
}
  
/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
/*
 * prog è la variabile principale della grammatica del nostro programma, 
 * un programma del nostro linguaggio può essere una semplice espressione 
 * (3 + 5 ad esempio) oppure può un programma più complesso: 
 * in generale può essere della forma:
 * let
 * .
 * .
 * .
 * in
 * .
 * .
 * .
 * nella parte let ci si possono mettere variabili globali, dichiarazioni di funzioni e 
 * definizioni di classi
 * nella parte in invece si possono usare le funzioni e classi definite nella parte let
 */
prog returns [Node ast] : 
    (
    	{
    		//in questa lista verranno salvate tutte le dichiarazioni (classe,funzione,variabili) che poi si utilizzeranno nel nodo ProgLetIn 
    		final List<DeclarationNode> declarations = new ArrayList<>();
    	} 
    	//in questo caso il programma è del tipo let ... in ...
    	LET ( 
    		/*
    		posso avere una serie di dichiarazioni di classi 
    		(che devono essere posizionate prima di tutto il resto) e poi dichiarazioni di funzioni e variabili
    		*/
    		c = cllist {
				declarations.addAll($c.classes);	
    		}
    		(d = declist {
				declarations.addAll($d.astlist);
    		})? 
    		//oppure possono avere delle dichiarazioni di funzioni e variabili
        	| d = declist {
    			declarations.addAll($d.astlist);	
        	}
            )
        //nella parte in avrò delle espressioni del tipo 2+3 oppure x + 4 
        IN letExp = exp 
        //posso creare a questo punto un ProgLetInNode visto che il programma scritto avrà sia let che in
        {
        	$ast = new ProgLetInNode(declarations, $letExp.ast);
        }
        | e = exp 
        //in questo caso creo un programma semplice che conterrà solo un'espressione
        { 
            $ast = new ProgNode($e.ast); 
        }
     ) SEMIC ;


cllist returns [List<DeclarationNode> classes]: {
		classesDecl = true; /*
		 * viene utilizzato per settare correttamente l'offset della declaration list successiva alla classi, 
		 * nel caso ci siano delle classi dichiarate, l'offset delle altre funzioni e dichiarazioni deve essere successivo
		 * a quello delle classi (offset classi - 1)
		 */
		$classes = new ArrayList<>(); //insieme delle dichiarazioni delle classi
	}
	( CLASS classId=ID {
		final List<FieldNode> fields = new ArrayList<>(); //lista dove vengono memorizzati i campi nel classNode corrente (non quelli ereditati)
		final Set<String> fieldsName = new HashSet<>(); /*OTTIMIZZAZIONE: evita l'overriding di campi dichiarati nella stessa classe */
		final Set<String> methodsName = new HashSet<>(); /*OTTIMIZZAZIONE: evita l'overriding di metodi dichiarati nella stessa classe */
		final List<MethodNode> methods = new ArrayList<>(); //lista dove vengono memorizzati i metodi nel classNode corrente (non quelli ereditati)
		final Map<String, STentry> vt = new HashMap<>(); //virtual table della classe corrente
		classTable.addClass($classId.text, vt); //aggiungo la classe nella class table, la virtual table verrà popolata man mano che scorro le dichiarazioni nella classe
		STentry superEntry = null; //descrive l'entry della classe ereditata
		final ClassType classType = new ClassType($classId.text, 
          		new ArrayList<>(), new ArrayList<>());
        //classType è il tipo della classe corrente che verrà popolato durante con tutti i metodi e campi in modo incrementale
        final STentry classEntry = STentry.createStandard(0, classType, --classOffset); //creo l'entry da aggiungere nella symbol table (nesting level sempre uguale a 0 per le classi)
  		symTable.addEntry($classId.text,classEntry); //aggiungo l'entry nella symbol table così facendo sarà visibile nel corpo della classe 
  		
	} (EXTENDS className=ID {
		/* OGGETTI CON EREDITARIETà*/
		//verifico se estensione è legitta, cioè se esiste la classe con il nome id, per farlo utilizzo la class table
		if(!classTable.isClassPresent($className.text)) {
			//nel caso che non sia presente lancio un'eccezione
			throw new NotDeclaredException(Declaration.Class, $className.text, $className.line);
		}
		ClassHierarchy.addRelation($className.text, $classId.text); //aggiungo una relazione padre figlio tra la classe corrente e la super classe
		superEntry = symTable.findEntryById($className.text).get(); //vado a cercare l'entry della super classe nella symbol table
		final ClassType superType = (ClassType) symTable.findEntryById($className.text).get().getType(); //cast al tipo corretto 
		final Map<String, STentry> superVt = classTable.getVirtualTable($className.text); /*
		 * prendo la virtual table della super classe in modo da copiare nella virtual table corrente i campi
		 * ed i metodi della super classe
		 */
		final List<Type> superFields = superType.getFields(); //tipi dei campi della super classe
		final List<Type> superMethods = superType.getMethods(); //tipo dei metodi della super classe
		superFields.forEach(classType::addField); //aggiungo tutti i campi della super classe alla sotto classe
		superMethods.forEach(classType::addMethod); //aggiungo tutti i metodi della super classe alla sotto classe
		vt.putAll(superVt); //aggiorno la virtual table della sotto classe mettendo tutto il contenuto della super classe
	})? {
		/*NB! in seguito ci appoggiamo comunque alla symTable per prendere il nesting level corrente, in questo caso in realtà
		 * si potrebbe non usare, infatti sappiamo che i campi e i metodi avranno tutti nesting level 1 e le dichiarazioni interne
		 * ai metodi avranno nesting level 2
		 * 
		 * in questo punto si inizializza la symbol table con il valore della corrente virtual table per via dell'ereditarietà 
		 */
		symTable.increaseNesting(vt);
	}
	/*attributi */ 
	LPAR (fieldName=ID COLON fieldType=type {
		/* ESTENSIONE CON EREDITARIETà: l'offset dei attributi deve essere successivo a quello della super classe (se è presente)
      	 * perciò parte all'offset corrente di tutti i campi (corrispondente alla lunghezza dei campi ereditati negativa
      	 * se non ho campi ereditati parto da 0)
      	 * */
		int offset = -classType.getFields().size();
		fieldsName.add($fieldName.text); //aggiungo il nome dell'attributo corrente per verificare che non sia ridefinito all'interno di questa classe OTTIMIZZAZIONE
		fields.add(new FieldNode($fieldName.text, $fieldType.nodeType)); //aggiungo il campo corrente a quelli del class node
		STentry firstFieldEntry = null; //entry del campo corrente
		STentry supEntry = vt.get($fieldName.text); //verifico se c'è overriding, per farlo devo vedere se nella virtual table sia già presente un campo con lo stesso nome
		if(supEntry == null) { 
			//in questo caso non è presente, non c'è overriding incodo l'attributo corrente sotto gli altri
			
			firstFieldEntry = STentry.createStandard(symTable.getNesting(), $fieldType.nodeType, --offset);
			classType.addField($fieldType.nodeType);
		} else {
			//in questo caso c'è già un entry associato al nome del campo, devo vedere che non sia un metodo
			if(supEntry.isMethod()) {
				//se lo è lancio un eccezione apposita
				throw new IllegalOverridingException(Declaration.Method, Declaration.Field);
			}
			//creo la nuova entry associata al campo facendo override, cioè mettendo nell'entry l'offset associato al campo della super classe
			firstFieldEntry = STentry.createStandard(symTable.getNesting(), $fieldType.nodeType, supEntry.getOffset());
			//aggiorno il tipo della classe corrente dicendo che c'è stato un override all'offset specificato
			classType.addOverridedField($fieldType.nodeType, supEntry.getOffset());
		}
		//aggiorno la symbol table e la virtual table		
		symTable.addEntry($fieldName.text,firstFieldEntry);  
        vt.put($fieldName.text, firstFieldEntry);
	} 
	(COMMA otherFieldName=ID COLON otherFieldType=type {
		//controllo che non si sia fatta una redifinizione del campo nella stessa classe
		if(fieldsName.contains($otherFieldName.text)) {
			throw new AlreadyDeclaredException(Declaration.Field, $otherFieldName.text, $otherFieldName.line);
		}
		//da qui in poi è uguale alla definizione del primo campo
		fieldsName.add($otherFieldName.text);
		fields.add(new FieldNode($otherFieldName.text, $otherFieldType.nodeType));
		STentry otherFieldEntry = null;
		supEntry = vt.get($otherFieldName.text);
		if(supEntry == null) {
			otherFieldEntry = STentry.createStandard(symTable.getNesting(), $otherFieldType.nodeType, --offset);
			classType.addField($otherFieldType.nodeType);
		} else {
			if(supEntry.isMethod()) {
				throw new IllegalOverridingException(Declaration.Method, Declaration.Field);
			}
			otherFieldEntry = STentry.createStandard(symTable.getNesting(), $otherFieldType.nodeType, supEntry.getOffset());
			classType.addOverridedField($otherFieldType.nodeType, supEntry.getOffset());
		}
				
		symTable.addEntry($otherFieldName.text,otherFieldEntry);  
        vt.put($otherFieldName.text, otherFieldEntry);
        
	})* )? RPAR   
	/*metodi*/ 
              CLPAR
              { 
              	/* ESTENSIONE CON EREDITARIETà: l'offset dei metodi deve essere successivo a quello della super classe (se è presente)
              	 * perciò parte all'offset corrente di tutti i metodi (se non ho metodi ereditati parto da 0)
              	 * */
              	int methodOffset = classType.getMethods().size();
          	
              }
                 ( FUN methodId=ID COLON returnType=type {
                 	final List<Type> parTypes = new ArrayList<>(); //mi memorizzo tutti i tipi dei parametri dei metoi
		            //creo il function node associato
		            final MethodNode method = new MethodNode($methodId.text,$returnType.nodeType, methodOffset); 
		            //OTTIMIZZAZIONE verifico che non ci sia una redifizione del metodo nella stessa classe
		            if(methodsName.contains($methodId.text)) {
						throw new AlreadyDeclaredException(Declaration.Field, $methodId.text, $methodId.line);
					}
					//nome metodo ok, aggiungo il nome del metodo ai nomi dei metodi della classe corrente
					methodsName.add($methodId.text);    
		            //aggiungo alla declarations list la funzione appena dichiarata 
		            methods.add(method);
		            //valori che mi serviranno per aggiungere l'entry nella symbol table
		            final int methodNesting = symTable.getNesting();
		            //come per le funzioni, i parametri sono a + 1 rispetto al fp
                 	int parOffset = 1;
                 	//aumento il nesting level della symbol table
                 	symTable.increaseNesting(new HashMap<>());
                 }
                 LPAR (firstId=ID COLON firstType=hotype {
                
	            	//aggiungo un parametro alla dichiarazione dei metodi 
	                parTypes.add($firstType.nodeType);
	                ParNode fpar = new ParNode($firstId.text,$firstType.nodeType); //creo nodo ParNode
	                method.addPar(fpar);                                 //lo attacco al MethodNode con addPar
	                //se il parametro era di tipo funzionale occupa doppio offset
	                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
	                //creo la nuova entry per il parametro
	                final STentry entry = STentry.createStandard(symTable.getNesting(), $firstType.nodeType, parOffset++);
	                //verifico se è già presente un entry con lo stesso nome, nel caso lancio un'eccezione 
	                if (!symTable.addEntry($firstId.text, entry)){
	                    throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
	                }
                
              	}
                (COMMA otherId=ID COLON otherType=hotype {
                	//stesso discorso del primo parametro
					parTypes.add($otherType.nodeType);
					ParNode par = new ParNode($otherId.text,$otherType.nodeType); 
					method.addPar(par);
					//se il parametro era di tipo funzionale occupa doppio offset
					parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
					final STentry otherEntry = STentry.createStandard(symTable.getNesting(), $otherType.nodeType, parOffset++);
					if (!symTable.addEntry($otherId.text,otherEntry)){
						throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
					}
                })*)? 
                RPAR { 
	               	// a questo punto posso aggiungere il metodo alla symbol table visto che ho tutte le sue informazioni
	               	final Type methodType = new ArrowType(parTypes,$returnType.nodeType);
	               	//ESTENSIONE CON EREDITARIETà: verifico che il metodo sia già stato dichiarato, vado a controllare la virtual table
	               	final STentry superMethod = vt.get($methodId.text);
	               	STentry methodEntry = null;
	               	if(superMethod == null) {
	               		//se è null vuol dire che il metodo non era presente, allora lo accodo ai nuovi metodi della classe
	               		methodEntry = STentry.createMethod(methodNesting, methodType, methodOffset++);
	               		classType.addMethod(methodType);
	               	} else {
	               		//ho fatto overriding, allora devo verificare che l'entry associata al nome sia un metodo altrimenti lancio eccezione
	               		if(!superMethod.isMethod()) {
	               			throw new IllegalOverridingException(Declaration.Field, Declaration.Method);
	               		}
	               		//creo la nuova entry associata al metodo usando l'offset del metodo della classe padre
	               		methodEntry = STentry.createMethod(methodNesting, methodType, superMethod.getOffset());
	               		//sovrascrivo il tipo del metodo con quello nuovo
	               		classType.addOverridedMethod(methodType, superMethod.getOffset());
	               		//aggiungo il metodo alla symbol table
	               		//modifico l'offset nel methodNode
	               		method.overrideOffset(superMethod.getOffset());
	               	}   	
                	
	                symTable.addEntry($methodId.text, methodEntry);
	                // utilizzato per l'offset delle dichiarazioni all'interno del metodo
	                
					vt.put($methodId.text, methodEntry);
                	int declarationOffset = -1;
                }	
	               (LET (VAR varId=ID COLON varType=type ASS varExp=exp SEMIC {
	               		final VarNode var = new VarNode($varId.text,$varType.nodeType, $varExp.ast);
			            //aggiungo alla declarations list la variabile
						method.addDec(var);            
			            //aggiungo la dichiarazione della variabile nella symbol table
			            final STentry varEntry = STentry.createStandard(symTable.getNesting(), $varType.nodeType,--declarationOffset);
			            if (!symTable.addEntry($varId.text,varEntry))
			            {
			            	throw new AlreadyDeclaredException(Declaration.Var,$varId.text,$varId.line);
			            }
			            //se var è di tipo funzionale allora occuperà offset doppio
			            if(var.getSymbolType() instanceof ArrowType) {
			            	declarationOffset--;
			            } 					
	               })+ IN)? methodExp = exp SEMIC 
	               {
	               		method.addBody($methodExp.ast); //aggiungo il body al metodo
	               		symTable.decreaseNesting(); //esco dal nesting nel metodo corrente 
	               }
        	     )*                
              CRPAR
          {
	        //a questo punto ho tutti i metodi e tutti i campi della classe corrente, posso creare il class node
          	final DeclarationNode classNode = new ClassNode(classType, $classId.text, fields, methods, superEntry);	
          	//aggiungo la classe ai nodi da restituire
          	$classes.add(classNode);
          	//esco dal nesting level
          	symTable.decreaseNesting();
          })+ 
        ;
/*
 * questa variabile viene utilizzata per identificare l'insieme di dichiarazione effettuate nella parte   
 */
declist returns [List<DeclarationNode> astlist]:
	/*
	 * astlist sono l'insieme di variabili e funzioni dichiarate in una parte di let
	 * le variabili globali nello stack crescono verso verso il basso (per questo motivo si
	 * inizializza l'offset -1 e andrà poi a -2, -3, ...)
	 */
 	{
        $astlist = new ArrayList<>();
        int offset = classesDecl == true ? classOffset : -1;
        classesDecl = false;
    }
    //in questo sto dichiarando una variabile
    ((VAR id=ID COLON t=hotype ASS e=exp {
    		//creo il nodo associato alla variabile che sto dichiarando
            final VarNode var = new VarNode($id.text,$t.nodeType, $e.ast);
            //aggiungo alla declarations list la variabile
            $astlist.add(var);
            //aggiungo la dichiarazione della variabile nella symbol table
            final STentry varEntry = STentry.createStandard(symTable.getNesting(), $t.nodeType,--offset);
            if (!symTable.addEntry($id.text,varEntry))
            {
            	throw new AlreadyDeclaredException(Declaration.Var,$id.text,$id.line);
            }
            //se var è di tipo funzionale allora occuperà offset doppio
            if(var.getSymbolType() instanceof ArrowType) {
            	offset--;
            }
        }
        //in questo caso sto dichiarando una funzione

        | FUN funId=ID COLON retType=type {
        	//creo il function node associato
            final FunNode function = new FunNode($funId.text,$retType.nodeType);     
            //aggiungo alla declarations list la funzione appena dichiarata 
            $astlist.add(function);
            //valori che mi serviranno per aggiungere l'entry nella symbol table
            final int functionNesting = symTable.getNesting();
            final int functionOffset = --offset;
            offset--; //decremento ulteriormente l'offset perché le funzioni occupano offset doppio
            //sto entrando dentro la funzione quindi devo aumentare il nesting level della symbol table
            symTable.increaseNesting(new HashMap<>());
        }
            LPAR {
            	//parTypes è l'insieme di parametri della funzione
                List<Type> parTypes = new ArrayList<Type>();
                //offset parte da 1 e cresce
          	    int parOffset = 1;
            } (firstId=ID COLON firstType=hotype {
            	//aggiungo un parametro alla dichiarazione di funzioni
                parTypes.add($firstType.nodeType);
                
                ParNode fpar = new ParNode($firstId.text,$firstType.nodeType); //creo nodo ParNode
                function.addPar(fpar);                                 //lo attacco al FunNode con addPar
                //se il parametro era di tipo funzionale occupa doppio offset
                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
                final STentry entry = STentry.createStandard(symTable.getNesting(), $firstType.nodeType, parOffset++);
                if (!symTable.addEntry($firstId.text, entry)){
                    throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
                }
                
              }
              //altra lista dei parametri
                (COMMA otherId=ID COLON otherType=hotype {
                   parTypes.add($otherType.nodeType);
                   ParNode par = new ParNode($otherId.text,$otherType.nodeType); 
                   function.addPar(par);
                	//se il parametro era di tipo funzionale occupa doppio offset
                	parOffset = par.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
                	final STentry otherEntry = STentry.createStandard(symTable.getNesting(), $otherType.nodeType, parOffset++);
                    if (!symTable.addEntry($otherId.text,otherEntry)){
                    	throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
                	}
                })*)? RPAR {
                	//a questo punto posso definire il tipo della funzione e di conseguenza lo aggiungerò alla symbol table
                	final Type funType = new ArrowType(parTypes,$retType.nodeType);
                	final STentry funEntry = STentry.createStandard(functionNesting, funType, functionOffset);
	                if(!symTable.addEntry($funId.text, funEntry)){
	                	throw new AlreadyDeclaredException(Declaration.Function,$funId.text,$funId.line);
	                }
                }
              (LET declarations = declist {$declarations.astlist.forEach(x -> function.addDec(x));} IN)? bodyExp=exp {
                  function.addBody($bodyExp.ast);
                  symTable.decreaseNesting();
              } 
        ) SEMIC 
      )+;

//si associa a sx ma hanno maggiore precedenza quelli scritti più in basso (factor > term > exp)
exp	returns [Node ast] 
    : first=term 
    {
        $ast = $first.ast;
    }
        ( PLUS other=term {$ast = new PlusNode($ast, $other.ast);}
           | MINUS other=term {$ast = new MinusNode($ast, $other.ast);}
           | OR other=term {$ast = new OrNode($ast, $other.ast); }
           )* 
    ;  

term returns [Node ast] 
    : firstFactor=factor {
        $ast = $firstFactor.ast;
    }
        ( TIMES other=factor { $ast = new MultNode ($ast, $other.ast); }
  	             | DIV  other=factor {$ast = new DivNode ($ast, $other.ast);}

  	             | AND  other=factor {$ast = new AndNode($ast, $other.ast);}
  	             )*
  	    ;
  	
factor returns [Node ast]
    : first=value {
        $ast = $first.ast;
    }
        ( EQ other=value { $ast = new EqualNode ($ast,$other.ast);}
	            | GE other=value { $ast = new GreaterEqualNode($ast,$other.ast);}
	            | LE other=value { $ast = new LessEqualNode($ast,$other.ast);}
	            )*
	    ;    	
  	
value returns [Node ast] 
    : n = INTEGER { $ast = new IntNode(Integer.parseInt($n.text));}
	    | TRUE { $ast= BoolNode.trueNode();}  
    	| FALSE { $ast= BoolNode.falseNode();}  
	    | NULL {$ast = EmptyNode.instance();} 
	    | NEW id=ID {
	    		//lista dei parametri passata al costruttore
	    		final List<Node> parNodes = new ArrayList<>();
	    		//per verificare se è presente nelle classe uso la class table
	    		if (!classTable.isClassPresent($id.text)){
	    			throw new NotDeclaredException(Declaration.Class,$id.text, $id.line); 			
	    		}
	    		//vado a prendere l'entry associata alla classe
	    		final STentry entry = symTable.findEntryById($id.text).get();
	    	}
	    	LPAR (e = exp {parNodes.add($e.ast);} (COMMA other=exp {parNodes.add($other.ast);})* )? RPAR {
	    		$ast = new NewNode($id.text, entry, parNodes);
	    	}       
	    | IF c=exp THEN CLPAR th=exp CRPAR ELSE CLPAR e=exp CRPAR {$ast = new IfNode($c.ast, $th.ast, $e.ast);}
	    | NOT LPAR e=exp RPAR {$ast = new NotNode($e.ast); } 
	    | PRINT LPAR e=exp RPAR {$ast = new PrintNode($e.ast);}
        | LPAR e=exp RPAR {$ast = $e.ast;}
        /*
         * se incontro un id devo cercare nella symbol table se 
         * è l'id utilizzato è già stato dichiarato, in questo caso
         * posso verificare se sia una variabile, una funzione e 
         * un oggetto
         */
	    | id=ID {
	    	//vado a ricercare l'entry associata all'id 
	    	final Optional<STentry> entry = symTable.findEntryById($id.text);
	    	//nel caso che non sia presente lancio l'eccezione not declared
            if (!entry.isPresent()){
            	throw new NotDeclaredException($id.text,$id.line);
            }
            //se non ci sono argomenti dopo l'id immagino che sia una variabile
            $ast= new IdNode($id.text,entry.get(),symTable.getNesting());
            //in arguments metterò l'insieme di valori trovati nella chiamata di funzione (o nel caso sia un oggetto nella chiamata a metodo)
            final List<Node> arguments = new ArrayList<Node>();
        }
        //in questa parte vuol dire che sta provando ad effettuare una chiamata a funzione
        ( LPAR (first=exp {
            arguments.add($first.ast);
        }(COMMA other=exp {
            arguments.add($other.ast);
        })* )? RPAR {
        	if(entry.get().isMethod()) {
        		$ast= new MethodCallNode($id.text,entry.get(),arguments,symTable.getNesting());		
        	} else {
    			$ast= new CallNode($id.text,entry.get(),arguments,symTable.getNesting());		
        	}
        }
        //in questo punto invece sto usando id come se fosse un oggetto
             | DOT methodId=ID {
             	//prendo il tipo dell'oggetto, se non è un RefType lancio un eccezione 
             	final Type objectType = entry.get().getType();
             	if(!(objectType instanceof RefType)) {
             		//TODO fare eccezione apposita
             		throw new NotDeclaredException(Declaration.Class,$id.text,$id.line);
             	}
             	//prendo il nome della classe
             	final String className = ((RefType)objectType).getId();
             	//se il nome della classe non è presente lancio un'eccezione
             	if(!classTable.isClassPresent(className)){
             		throw new NotDeclaredException(Declaration.Class,$id.text,$id.line);
             	}
             	//vado a prendere la virtual table per prendere l'entry dell metodo che sto chiamando
             	final Map<String, STentry> virtualTable = classTable.getVirtualTable(className);
             	//se il metodo non è dichiarato lancio un'eccezione
             	if(!virtualTable.containsKey($methodId.text)){
             		throw new NotDeclaredException(Declaration.Method,$methodId.text,$methodId.line);
             	}
             	final STentry methodEntry = virtualTable.get($methodId.text);
             	final List<Node> methodPar = new ArrayList<>();
             }
             //vado a popolare la lista del parametri
             	LPAR (e=exp {
             		methodPar.add($e.ast);
             	} (COMMA other=exp {
             		methodPar.add($other.ast);
             	})* )? RPAR 
	         {
	         	$ast = new ClassCallNode($methodId.text, entry.get(), methodEntry, methodPar, symTable.getNesting());
	         })?	   
        ; 
               
hotype returns [Type nodeType] : t=type {$nodeType = $t.nodeType;} 
        | a=arrow {$nodeType = $a.nodeType;}
        ;

type returns [Type nodeType]
//DA FARE
        : INT {$nodeType = IntType.instance();}    		      
        | BOOL {$nodeType = BoolType.instance();}	
        //verifica qua se l'id è già stato dichiarato	      	
 	    | id=ID {
 	    	if(!classTable.isClassPresent($id.text)) {
 	    		throw new NotDeclaredException(Declaration.Class,$id.text,$id.line);
 	    	}
 	    	$nodeType = new RefType($id.text);
 	    }                 
 	    ;  
 	 //DA FARE
arrow returns [Type nodeType] : {final List<Type> parameterList = new ArrayList<>();}
								LPAR (firstParameter=hotype {parameterList.add($firstParameter.nodeType);}
									(COMMA otherParameter=hotype {parameterList.add($otherParameter.nodeType);})*
								)? RPAR ARROW returnType=type {$nodeType = new ArrowType(parameterList, $returnType.nodeType);};          
		  
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

PLUS  	: '+' ;
MINUS   : '-' ;
TIMES   : '*' ;
DIV 	: '/' ;
LPAR	: '(' ;
RPAR	: ')' ;
CLPAR	: '{' ;
CRPAR	: '}' ;
SEMIC 	: ';' ;
COLON   : ':' ; 
COMMA	: ',' ;
DOT	    : '.' ;
OR	    : '||';
AND	    : '&&';
NOT	    : '!' ;
GE	    : '>=' ;
LE	    : '<=' ;
EQ	    : '==' ;	
ASS	    : '=' ;
TRUE	: 'true' ;
FALSE	: 'false' ;
IF	    : 'if' ;
THEN	: 'then';
ELSE	: 'else' ;
PRINT	: 'print' ;
LET     : 'let' ;	
IN      : 'in' ;	
VAR     : 'var' ;
FUN	    : 'fun' ; 
CLASS	: 'class' ; 
EXTENDS : 'extends' ;	
NEW 	: 'new' ;	
NULL    : 'null' ;	  
INT	    : 'int' ;
BOOL	: 'bool' ;
ARROW   : '->' ; 	
INTEGER : '0' | ('-')?(('1'..'9')('0'..'9')*) ; 

ID  	: ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;


WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+    -> channel(HIDDEN) ;

COMMENT : '/*' (.)*? '*/' -> channel(HIDDEN) ;
 
ERR   	 : . { System.out.println("Invalid char: "+ getText()); lexicalErrors++; } -> channel(HIDDEN); 

