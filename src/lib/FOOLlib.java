package lib;

import ast.core.Type;
import ast.type.BoolType;
import ast.type.IntType;

public class FOOLlib {
	
  //valuta se il tipo "a" è <= al tipo "b", dove "a" e "b" sono tipi di base: int o bool
  public static boolean isSubtype (final Type a, final Type b) {
	return a.getClass().equals(b.getClass()) ||
	    	   ( (a == BoolType.instance()) && (b == IntType.instance()));  
  }
  
  private static int labCount=0; 
  private static int funLabCount=0;
  private static String funCode="" ; 
  
  public static String freshLabel() { 
	return "label"+(labCount++);
  } 
	  
  public static String freshFunLabel() { 
		return "function"+(funLabCount++);
  } 

  public static void putCode(String c) { 
    funCode+="\n"+c; //aggiunge una linea vuota di separazione prima di funzione
  } 
  
  public static String getCode() { 
    return funCode;
  } 
	
}
