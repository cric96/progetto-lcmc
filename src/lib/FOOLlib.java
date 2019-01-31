package lib;

public class FOOLlib {
	
  private static String funCode="" ; 
   

  public static void putFunctionCode(String c) { 
    funCode+="\n"+c; //aggiunge una linea vuota di separazione prima di funzione
  } 
  
  public static String getFunctionCode() { 
    return funCode;
  } 
	
}
