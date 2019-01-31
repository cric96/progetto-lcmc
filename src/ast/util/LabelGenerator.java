package ast.util;

/**
 * utilizzata per creare label differenti sulla base dell'istruzione cui faranno
 * riferimento
 */
public class LabelGenerator {
	// indice associato alle label delle funzioni
	protected static int functionIndex = 0;

	// simple factory, costruisce una label generica
	public static String generate() {
		return generate(GenerationSeed.Standard.Generic);
	}

	// simple factory, costruisce una label sulla base del seme passato
	public static String generate(GenerationSeed seed) {
		return seed.toString() + (seed.next());
	}

	private LabelGenerator() {
	}

	/**
	* Interfaccia che mi dà i diversi semi che posso associare a una label 
	*/
	public interface GenerationSeed {
		/**
		 * 
		 * @return il prossimo indice univoco per seme
		 */
		int next();

		/**
		 *
		 *enumerazione che offre semi di base
		 */
		public enum Standard implements GenerationSeed {
			True("true"), Generic("label"), False("false"), IfTrue("ifTrue"), IfFalse("ifFalse");

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
				return nextNum++;
			}
		}
		/**
		 * 
		 * @param id, nome della funzione
		 * @return il seed per generare la label da associare alla funzione
		 */
		public static GenerationSeed createFunctionSeed(final String id) {
			return new GenerationSeed() {

				@Override
				public int next() {
					return functionIndex++;
				}

				public String toString() {
					return id;
				}
			};
		}
	}

}
