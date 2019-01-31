grammar FOOL;

//header: serve per importare i package nel SVM parser (definisco quali package il parser avrà bisogno di usare)
@header{
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import ast.*;
import ast.core.*;
import ast.type.*;
}

//attributi della classe parser, verranno usati durante il parsing
@parser::members{
	//TODO ricorda di mettere il concetto di symbol table
	private int nestingLevel = 0;
	private List<Map<String,STentry>> symTable = new ArrayList<Map<String,STentry>>();
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
    {
    	//all'inizio si inizializza la symbol table con una entry vuota, definisce il livello globale
        final Map<String,STentry> hm = new HashMap<String,STentry> ();
       	symTable.add(hm);    
    }
    
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
declist returns [List<Node> astlist]:
	/*
	 * astlist sono l'insieme di variabili e funzioni dichiarate in una parte di let
	 * le variabili globali nello stack crescono verso verso il basso (per questo motivo si
	 * inizializza l'offset -1 e andrà poi a -2, -3, ...)
	 */
 	{
        $astlist = new ArrayList<>();
        int offset = -1;
    }
    
    (( VAR id=ID COLON t=hotype ASS e=exp {
                final VarNode var = new VarNode($id.text,$t.nodeType, $e.ast);
                $astlist.add(var);
                //cerca di fare meglio qua!
                final Map<String,STentry> hm = symTable.get(nestingLevel);
                if (hm.put($id.text,new STentry(nestingLevel,$t.nodeType, --offset)) != null )
                {
                    //TODO METTI ECCEZIONI 
                    System.out.println("Var id "+$id.text+" at line "+$id.line+" already declared");
                    System.exit(0);
                }
            }
            | FUN funId=ID COLON retType=type {
                final FunNode function = new FunNode($funId.text,$retType.nodeType);      
                $astlist.add(function);                              
                Map<String,STentry> hm = symTable.get(nestingLevel);
                final STentry entry = new STentry(nestingLevel,null,--offset);
                if (hm.put($funId.text,entry) != null  )
                {
                    //TODO METTI ECCEZIONI 
                    System.out.println("Fun id "+$funId.text+" at line "+$funId.line+" already declared");
                    System.exit(0);
                }
                //creare una nuova hashmap per la symTable
                //RICORDA DI AGGIUNGERE IL NESTING LEVEL
                nestingLevel++;
                Map<String,STentry> hmn = new HashMap<String,STentry> ();
                symTable.add(hmn);
            }
                LPAR {
                    List<Type> parTypes = new ArrayList<Type>();
              	    int parOffset = 1;
                } (firstId=ID COLON firstType=hotype {
                    parTypes.add($firstType.nodeType);
                    ParNode fpar = new ParNode($firstId.text,$firstType.nodeType); //creo nodo ParNode
                    function.addPar(fpar);                                 //lo attacco al FunNode con addPar
                    if ( hmn.put($firstId.text,new STentry(nestingLevel,$firstType.nodeType, parOffset ++)) != null  ){
                        //TODO lancia eccezioni
                        System.out.println("Parameter id "+$firstId.text+" at line "+$firstId.line+" already declared");
                        System.exit(0);
                    }
                  }
	                (COMMA otherId=ID COLON otherType=hotype {
	                   parTypes.add($otherType.nodeType);
	                   ParNode par = new ParNode($otherId.text,$otherType.nodeType); 
	                   function.addPar(par);
	                    if ( hmn.put($otherId.text,new STentry(nestingLevel,$otherType.nodeType, parOffset ++)) != null  ){
	                        //TODO lancia eccezioni
	                        System.out.println("Parameter id "+$id.text+" at line "+$id.line+" already declared");
	                        System.exit(0);
	                    }
	                })*)? RPAR {
	                    entry.addType(new ArrowType(parTypes,$retType.nodeType));
	                }
                  (LET declarations = declist {function.addDec($declarations.astlist);} IN)? bodyExp=exp {
                      function.addBody($bodyExp.ast);
                      symTable.remove(nestingLevel--);
                  } 
            ) SEMIC 
          )+
        ;

//si associa a sx ma hanno maggiore precedenza quelli scritti più in basso (factor > term > exp)
exp	returns [Node ast] 
    : first=term 
    {
        $ast = $first.ast;
    }
        ( PLUS other=term {$ast = new PlusNode($ast, $other.ast);}
           | MINUS other=term {$ast = new MinusNode($ast, $other.ast);}
           | OR other=term {$ast = null; }//DA FARE
           )* 
    ;  

term returns [Node ast] 
    : firstFactor=factor {
        $ast = $firstFactor.ast;
    }
        ( TIMES other=factor { $ast = new MultNode ($ast, $other.ast); }
  	             | DIV  factor {$ast = null;}
  	             | AND  factor {$ast = null;}
  	             )*
  	    ;
  	
factor returns [Node ast]
    : first=value {
        $ast = $first.ast;
    }
        ( EQ other=value { $ast = new EqualNode ($ast,$other.ast);}
	            | GE other=value { $ast = null;}
	            | LE other=value { $ast = null;}
	            )*
	    ;    	
  	
value returns [Node ast] 
    : n = INTEGER { $ast = new IntNode(Integer.parseInt($n.text));}
	    | TRUE { $ast= new BoolNode(true);}  
    	| FALSE { $ast= new BoolNode(false);}  
	    | NULL {$ast = null;} //da fare 
	    | NEW ID LPAR (exp (COMMA exp)* )? RPAR {$ast = null; } // da fare        
	    | IF c=exp THEN CLPAR th=exp CRPAR ELSE CLPAR e=exp CRPAR {$ast = new IfNode($c.ast, $th.ast, $e.ast);}
	    | NOT LPAR exp RPAR {$ast = null; } // da fare
	    | PRINT LPAR e=exp RPAR {$ast = new PrintNode($e.ast);}
        | LPAR e=exp RPAR {$ast = $e.ast;}
	    | id=ID {
            int j = nestingLevel;
            STentry entry = null;
            while (j>=0 && entry==null) {
             	entry=(symTable.get(j--)).get($id.text);
            }
            if (entry == null){
                System.out.println("Id "+$id.text+" at line "+$id.line+" not declared");
                System.exit(0);
            }
            //se non ci sono argomenti dopo l'id immagino che sia una variabile
            $ast= new IdNode($id.text,entry,nestingLevel);
            final List<Node> arguments = new ArrayList<Node>();
        }
        //in questa parte vuol dire che sta provando ad effettuare una chiamata a funzione
        ( LPAR (first=exp {
            arguments.add($first.ast);
        }(COMMA other=exp {
            arguments.add($other.ast);
        })* )? RPAR {
        	$ast= new CallNode($id.text,entry,arguments,nestingLevel);
        }
        //in questo punto invece sto usando id come se fosse un oggetto
             | DOT ID LPAR (exp (COMMA exp)* )? RPAR 
	         )?	   
        ; 
               
hotype returns [Type nodeType] : t=type {$nodeType = $t.nodeType;} 
		//DA FARE
        | arrow
        ;

type returns [Type nodeType]
//DA FARE
        : INT {$nodeType = IntType.instance();}    		      
        | BOOL {$nodeType = BoolType.instance();}		      	
 	    | ID                   
 	    ;  
 	 //DA FARE
arrow returns [Type nodeType] : LPAR (hotype (COMMA hotype)* )? RPAR ARROW type;          
		  
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

