package ast.util;

public class LabelGenerator {
	public static String generate() {
		return generate(GenerationSeed.Standard.Generic);
	}

	public static String generate(GenerationSeed seed) {
		return seed.toString() + (seed.next());
	}

	protected static int functionIndex = 0;
	public interface GenerationSeed {
		int next();
		public enum Standard implements GenerationSeed {
			True("true"), 
			Generic("label"), 
			False("false"), 
			IfTrue("ifTrue"), 
			IfFalse("ifFalse");
			
			private final String myName;
			private int nextNum;
			
			Standard(final String string) {
				this.myName = string;
				nextNum = 0;
			}
			public String toString() {
				return myName;
			}
			
			public int next() {
				return nextNum ++;
			}
		}
		
		public static GenerationSeed Function(final String id) {
			return new GenerationSeed() {
				
				@Override
				public int next() {
					return functionIndex ++;
				}
				
				public String toString() {
					return id;
				}
			};
		}
	}
	
}
