package ast;

import ast.core.Node;

public class IntTypeNode implements Node {
  
  public IntTypeNode () {
  }
  
  public String toPrint(String s) {
	   return s+"IntType\n";  
  }

  //non utilizzato
  public Node typeCheck() {return null;}

  //non utilizzato
  public String codeGeneration() {return "";}

}  