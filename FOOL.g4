grammar FOOL;

//header: serve per importare i package nel SVM parser (definisco quali package il parser avrà bisogno di usare)
@header{
import java.util.*;
import ast.*;
import ast.core.*;
import ast.type.*;
import ast.exception.*;
import ast.operator.*;
}

//attributi della classe parser, verranno usati durante il parsing
@parser::members{
	//TODO ricorda di mettere il concetto di symbol table
	private SymbolTable symTable = new StandardSymbolTable();
	//livello ambiente con dichiarazioni piu' esterno è 0 (prima posizione ArrayList) invece che 1 (slides)
	//il "fronte" della lista di tabelle è symTable.get(nestingLevel)
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
    	//in questo caso il programma è del tipo let ... in ...
    	LET ( 
    		/*
    		posso avere una serie di dichiarazioni di classi 
    		(che devono essere posizionate prima di tutto il resto) e poi dichiarazioni di funzioni e variabili
    		*/
    		cllist (declist)? 
    		//oppure possono avere delle dichiarazioni di funzioni e variabili
        	| d = declist
            )
        //nella parte in avrò delle espressioni del tipo 2+3 oppure x + 4 
        IN letExp = exp 
        //posso creare a questo punto un ProgLetInNode visto che il programma scritto avrà sia let che in
        {
        	
        	$ast = new ProgLetInNode($d.astlist, $letExp.ast);
        }
        | e = exp 
        //in questo caso creo un programma semplice che conterrà solo un'espressione
        { 
            $ast = new ProgNode($e.ast); 
        }
     ) SEMIC ;

/*
 *  da fare
 */
cllist returns [List<Node> classes]: ( CLASS ID (EXTENDS ID)? LPAR (ID COLON type (COMMA ID COLON type)* )? RPAR    
              CLPAR
                 ( FUN ID COLON type LPAR (ID COLON hotype (COMMA ID COLON hotype)* )? RPAR
	                     (LET (VAR ID COLON type ASS exp SEMIC)+ IN)? exp 
        	       SEMIC
        	     )*                
              CRPAR
          )+ { $classes = null;} //per adesso devi finirlo
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
        int offset = -1;
    }
    //in questo sto dichiarando una variabile
    ((VAR id=ID COLON t=hotype ASS e=exp {
    		//creo il nodo associato alla variabile che sto dichiarando
            final VarNode var = new VarNode($id.text,$t.nodeType, $e.ast);
            //aggiungo alla declarations list la variabile
            $astlist.add(var);
            //aggiungo la dichiarazione della variabile nella symbol table
            if (!symTable.addEntry($id.text,$t.nodeType, --offset))
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
            //valori che mi serivaranno per aggiungere l'entry nella symbol table
            final int functionNesting = symTable.getNesting();
            final int functionOffset = --offset;
            offset--; //decremento ulteriormente l'offset perché le funzioni occupano offset doppio
            //sto entrando dentro la funzione quindi devo aumentare il nesting level della symbol table
            symTable.increaseNesting();
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
                if (!symTable.addEntry($firstId.text,$firstType.nodeType, parOffset ++)){
                    throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
                }
                
              }
                (COMMA otherId=ID COLON otherType=hotype {
                   parTypes.add($otherType.nodeType);
                   ParNode par = new ParNode($otherId.text,$otherType.nodeType); 
                   function.addPar(par);
                    if (!symTable.addEntry($otherId.text,$otherType.nodeType, parOffset ++)){
                    	throw new AlreadyDeclaredException(Declaration.Parameter,$firstId.text,$firstId.line);
                	}
                	//se il parametro era di tipo funzionale occupa doppio offset
                	parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
                })*)? RPAR {
                	//a questo punto posso definire il tipo della funzione e di conseguenza lo aggiungerò alla symbol table
                	final Type funType = new ArrowType(parTypes,$retType.nodeType);
	                if(!symTable.addEntryIn($funId.text,funType,functionOffset,functionNesting)){
	                	throw new AlreadyDeclaredException(Declaration.Function,$funId.text,$funId.line);
	                }
                }
              (LET declarations = declist {function.addDec($declarations.astlist);} IN)? bodyExp=exp {
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
	    | TRUE { $ast= new BoolNode(true);}  
    	| FALSE { $ast= new BoolNode(false);}  
	    | NULL {$ast = null;} //da fare 
	    | NEW ID LPAR (exp (COMMA exp)* )? RPAR {$ast = null; } // da fare        
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
        	$ast= new CallNode($id.text,entry.get(),arguments,symTable.getNesting());
        }
        //in questo punto invece sto usando id come se fosse un oggetto
             | DOT ID LPAR (exp (COMMA exp)* )? RPAR 
	         )?	   
        ; 
               
hotype returns [Type nodeType] : t=type {$nodeType = $t.nodeType;} 
        | a=arrow {$nodeType = $a.nodeType;}
        ;

type returns [Type nodeType]
//DA FARE
        : INT {$nodeType = IntType.instance();}    		      
        | BOOL {$nodeType = BoolType.instance();}		      	
 	    | ID                   
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

