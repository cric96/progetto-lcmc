package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;

public class PrintNode implements Node {

  private Node exp;
  
  public PrintNode (Node e) {
   exp=e;
  }
  
  public String toPrint(String s) {
   return s+"Print\n" + exp.toPrint(s+"  ") ;
  }

  public Type typeCheck() throws WrongTypeException {
	    return exp.typeCheck();
  }
      
  public String codeGeneration() {
	  return exp.codeGeneration()+
			 "print\n";
  }

}  