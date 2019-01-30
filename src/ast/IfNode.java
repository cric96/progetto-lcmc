package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import lib.FOOLlib;

public class IfNode implements Node {

  private Node cond;
  private Node th;
  private Node el;
  
  public IfNode (Node c, Node t, Node e) {
   cond=c;
   th=t;
   el=e;
  }
  
  public String toPrint(String s) {
   return s+"If\n" + cond.toPrint(s+"  ") 
                 + th.toPrint(s+"  ")   
                 + el.toPrint(s+"  ") ; 
  }

  public Type typeCheck() throws WrongTypeException {
	if (!(FOOLlib.isSubtype(cond.typeCheck(), BoolType.instance())) ) {
		throw new WrongTypeException("non boolean condition in if");
	}
	final Type then= th.typeCheck();  
	final Type e=  el.typeCheck();  
	if (FOOLlib.isSubtype(then, e))
      return e;
	if (FOOLlib.isSubtype(e, then))
	  return then;
	throw new WrongTypeException("Incompatible types in then-else branches");
  }
  
  public String codeGeneration() {
	  String l1= FOOLlib.freshLabel();
	  String l2= FOOLlib.freshLabel();
	  return cond.codeGeneration()+
		     "push 1\n"+		     
			 "beq "+l1+"\n"+
	         el.codeGeneration()+
			 "b "+l2+"\n"+
			 l1+": \n"+
			 th.codeGeneration()+
			 l2+": \n"
			 ;
  }
 
}  