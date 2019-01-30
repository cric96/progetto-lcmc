package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.IntType;
import lib.*;

public class PlusNode implements Node {

  private final Node left;
  private final Node right;
  
  public PlusNode (final Node l, final Node r) {
   left=l;
   right=r;
  }
  
  public String toPrint(String s) {
   return s+"Plus\n" + left.toPrint(s+"  ")  
                     + right.toPrint(s+"  ") ; 
  }

  public Type typeCheck() throws WrongTypeException {
	checkCorrectness(left);
	checkCorrectness(right);
	return IntType.instance();
  }
  private void checkCorrectness(final Node node) throws WrongTypeException {
	  if(!( FOOLlib.isSubtype(node.typeCheck(), IntType.instance()))) {
		  throw new WrongTypeException("Non integers in sum",IntType.instance(), node.typeCheck());
	  }
  }
  
  public String codeGeneration() {
	  return left.codeGeneration()+right.codeGeneration()+"add\n";
  }

}  