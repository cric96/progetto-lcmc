package ast;

import ast.core.Node;
import ast.core.Type;

public class ParNode implements Node {

  private final String id;
  private final Type type;
  
  public ParNode (final String i, final Type t) {
   id=i;
   type=t;
  }
  
  public String toPrint(String s) {
	   return s+"Par:" + id +"\n"
			   +type.toPrint(s+"  ") ; 
  }

  //non utilizzato
  public Type typeCheck() {return null;}
   
  public String codeGeneration() {return "";}

}  