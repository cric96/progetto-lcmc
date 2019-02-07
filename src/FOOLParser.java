// Generated from FOOL.g4 by ANTLR 4.7

import java.util.*;
import java.util.stream.*;
import ast.*;
import ast.leaf.*;
import ast.core.*;
import ast.type.*;
import ast.oo.*;
import ast.exception.*;
import ast.operator.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MINUS=2, TIMES=3, DIV=4, LPAR=5, RPAR=6, CLPAR=7, CRPAR=8, SEMIC=9, 
		COLON=10, COMMA=11, DOT=12, OR=13, AND=14, NOT=15, GE=16, LE=17, EQ=18, 
		ASS=19, TRUE=20, FALSE=21, IF=22, THEN=23, ELSE=24, PRINT=25, LET=26, 
		IN=27, VAR=28, FUN=29, CLASS=30, EXTENDS=31, NEW=32, NULL=33, INT=34, 
		BOOL=35, ARROW=36, INTEGER=37, ID=38, WHITESP=39, COMMENT=40, ERR=41;
	public static final int
		RULE_prog = 0, RULE_cllist = 1, RULE_declist = 2, RULE_exp = 3, RULE_term = 4, 
		RULE_factor = 5, RULE_value = 6, RULE_hotype = 7, RULE_type = 8, RULE_arrow = 9;
	public static final String[] ruleNames = {
		"prog", "cllist", "declist", "exp", "term", "factor", "value", "hotype", 
		"type", "arrow"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'+'", "'-'", "'*'", "'/'", "'('", "')'", "'{'", "'}'", "';'", "':'", 
		"','", "'.'", "'||'", "'&&'", "'!'", "'>='", "'<='", "'=='", "'='", "'true'", 
		"'false'", "'if'", "'then'", "'else'", "'print'", "'let'", "'in'", "'var'", 
		"'fun'", "'class'", "'extends'", "'new'", "'null'", "'int'", "'bool'", 
		"'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PLUS", "MINUS", "TIMES", "DIV", "LPAR", "RPAR", "CLPAR", "CRPAR", 
		"SEMIC", "COLON", "COMMA", "DOT", "OR", "AND", "NOT", "GE", "LE", "EQ", 
		"ASS", "TRUE", "FALSE", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", "VAR", 
		"FUN", "CLASS", "EXTENDS", "NEW", "NULL", "INT", "BOOL", "ARROW", "INTEGER", 
		"ID", "WHITESP", "COMMENT", "ERR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		//TODO ricorda di mettere il concetto di symbol table
		private final SymbolTable symTable = new StandardSymbolTable();
		private final ClassTable classTable = new ClassTable();
		//livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides)
		//il "fronte" della lista di tabelle � symTable.get(nestingLevel)
		private boolean classesDecl = false;
		private int classOffset = -1;

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Node ast;
		public CllistContext c;
		public DeclistContext d;
		public ExpContext letExp;
		public ExpContext e;
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public CllistContext cllist() {
			return getRuleContext(CllistContext.class,0);
		}
		public DeclistContext declist() {
			return getRuleContext(DeclistContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				{

				    		//in questa lista verranno salvate tutte le dichiarazioni (classe,funzione,variabili) che poi si utilizzeranno nel nodo ProgLetIn 
				    		final List<DeclarationNode> declarations = new ArrayList<>();
				    	
				setState(21);
				match(LET);
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(22);
					((ProgContext)_localctx).c = cllist();

									declarations.addAll(((ProgContext)_localctx).c.classes);	
					    		
					setState(27);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(24);
						((ProgContext)_localctx).d = declist();

										declarations.addAll(((ProgContext)_localctx).d.astlist);
						    		
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(29);
					((ProgContext)_localctx).d = declist();

					    			declarations.addAll(((ProgContext)_localctx).d.astlist);	
					        	
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(34);
				match(IN);
				setState(35);
				((ProgContext)_localctx).letExp = exp();

				        	((ProgContext)_localctx).ast =  new ProgLetInNode(declarations, ((ProgContext)_localctx).letExp.ast);
				        
				}
				break;
			case LPAR:
			case NOT:
			case TRUE:
			case FALSE:
			case IF:
			case PRINT:
			case NEW:
			case NULL:
			case INTEGER:
			case ID:
				{
				setState(38);
				((ProgContext)_localctx).e = exp();
				 
				            ((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast); 
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(43);
			match(SEMIC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CllistContext extends ParserRuleContext {
		public List<DeclarationNode> classes;
		public Token classId;
		public Token className;
		public Token fieldName;
		public TypeContext fieldType;
		public Token otherFieldName;
		public TypeContext otherFieldType;
		public Token methodId;
		public TypeContext returnType;
		public Token firstId;
		public HotypeContext firstType;
		public Token otherId;
		public HotypeContext otherType;
		public Token varId;
		public TypeContext varType;
		public ExpContext varExp;
		public ExpContext methodExp;
		public List<TerminalNode> CLASS() { return getTokens(FOOLParser.CLASS); }
		public TerminalNode CLASS(int i) {
			return getToken(FOOLParser.CLASS, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<TerminalNode> EXTENDS() { return getTokens(FOOLParser.EXTENDS); }
		public TerminalNode EXTENDS(int i) {
			return getToken(FOOLParser.EXTENDS, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public CllistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cllist; }
	}

	public final CllistContext cllist() throws RecognitionException {
		CllistContext _localctx = new CllistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cllist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

					classesDecl = true; /*
					 * viene utilizzato per settare correttamente l'offset della declaration list successiva alla classi, 
					 * nel caso ci siano delle classi dichiarate, l'offset delle altre funzioni e dichiarazioni deve essere successivo
					 * a quello delle classi (offset classi - 1)
					 */
					((CllistContext)_localctx).classes =  new ArrayList<>(); //insieme delle dichiarazioni delle classi
				
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				match(CLASS);
				setState(47);
				((CllistContext)_localctx).classId = match(ID);

						final List<FieldNode> fields = new ArrayList<>(); //lista dove vengono memorizzati i campi nel classNode corrente (non quelli ereditati)
						final Set<String> fieldsName = new HashSet<>(); /*OTTIMIZZAZIONE: evita l'overriding di campi dichiarati nella stessa classe */
						final Set<String> methodsName = new HashSet<>(); /*OTTIMIZZAZIONE: evita l'overriding di metodi dichiarati nella stessa classe */
						final List<MethodNode> methods = new ArrayList<>(); //lista dove vengono memorizzati i metodi nel classNode corrente (non quelli ereditati)
						final Map<String, STentry> vt = new HashMap<>(); //virtual table della classe corrente
						classTable.addClass((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), vt); //aggiungo la classe nella class table, la virtual table verr� popolata man mano che scorro le dichiarazioni nella classe
						STentry superEntry = null; //descrive l'entry della classe ereditata
						final ClassType classType = new ClassType((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), 
				          		new ArrayList<>(), new ArrayList<>());
				        //classType � il tipo della classe corrente che verr� popolato durante con tutti i metodi e campi in modo incrementale
				        final STentry classEntry = STentry.createStandard(0, classType, --classOffset); //creo l'entry da aggiungere nella symbol table (nesting level sempre uguale a 0 per le classi)
				  		symTable.addEntry((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null),classEntry); //aggiungo l'entry nella symbol table cos� facendo sar� visibile nel corpo della classe 
				  		
					
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(49);
					match(EXTENDS);
					setState(50);
					((CllistContext)_localctx).className = match(ID);

							/* OGGETTI CON EREDITARIET�*/
							//verifico se estensione � legitta, cio� se esiste la classe con il nome id, per farlo utilizzo la class table
							if(!classTable.isClassPresent((((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null))) {
								//nel caso che non sia presente lancio un'eccezione
								throw new NotDeclaredException(Declaration.Class, (((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null), (((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getLine():0));
							}
							ClassHierarchy.addRelation((((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null), (((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null)); //aggiungo una relazione padre figlio tra la classe corrente e la super classe
							superEntry = symTable.findEntryById((((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null)).get(); //vado a cercare l'entry della super classe nella symbol table
							final ClassType superType = (ClassType) symTable.findEntryById((((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null)).get().getType(); //cast al tipo corretto 
							final Map<String, STentry> superVt = classTable.getVirtualTable((((CllistContext)_localctx).className!=null?((CllistContext)_localctx).className.getText():null)); /*
							 * prendo la virtual table della super classe in modo da copiare nella virtual table corrente i campi
							 * ed i metodi della super classe
							 */
							final List<Type> superFields = superType.getFields(); //tipi dei campi della super classe
							final List<Type> superMethods = superType.getMethods(); //tipo dei metodi della super classe
							superFields.forEach(classType::addField); //aggiungo tutti i campi della super classe alla sotto classe
							superMethods.forEach(classType::addMethod); //aggiungo tutti i metodi della super classe alla sotto classe
							vt.putAll(superVt); //aggiorno la virtual table della sotto classe mettendo tutto il contenuto della super classe
						
					}
				}


						/*NB! in seguito ci appoggiamo comunque alla symTable per prendere il nesting level corrente, in questo caso in realt�
						 * si potrebbe non usare, infatti sappiamo che i campi e i metodi avranno tutti nesting level 1 e le dichiarazioni interne
						 * ai metodi avranno nesting level 2
						 * 
						 * in questo punto si inizializza la symbol table con il valore della corrente virtual table per via dell'ereditariet� 
						 */
						symTable.increaseNesting(vt);
					
				setState(55);
				match(LPAR);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(56);
					((CllistContext)_localctx).fieldName = match(ID);
					setState(57);
					match(COLON);
					setState(58);
					((CllistContext)_localctx).fieldType = type();

							/* ESTENSIONE CON EREDITARIET�: l'offset dei attributi deve essere successivo a quello della super classe (se � presente)
					      	 * perci� parte all'offset corrente di tutti i campi (corrispondente alla lunghezza dei campi ereditati negativa
					      	 * se non ho campi ereditati parto da 0)
					      	 * */
							int offset = -classType.getFields().size();
							fieldsName.add((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null)); //aggiungo il nome dell'attributo corrente per verificare che non sia ridefinito all'interno di questa classe OTTIMIZZAZIONE
							fields.add(new FieldNode((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null), ((CllistContext)_localctx).fieldType.nodeType)); //aggiungo il campo corrente a quelli del class node
							STentry firstFieldEntry = null; //entry del campo corrente
							STentry supEntry = vt.get((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null)); //verifico se c'� overriding, per farlo devo vedere se nella virtual table sia gi� presente un campo con lo stesso nome
							if(supEntry == null) { 
								//in questo caso non � presente, non c'� overriding incodo l'attributo corrente sotto gli altri
								
								firstFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).fieldType.nodeType, --offset);
								classType.addField(((CllistContext)_localctx).fieldType.nodeType);
							} else {
								//in questo caso c'� gi� un entry associato al nome del campo, devo vedere che non sia un metodo
								if(supEntry.isMethod()) {
									//se lo � lancio un eccezione apposita
									throw new IllegalOverridingException(Declaration.Method, Declaration.Field);
								}
								//creo la nuova entry associata al campo facendo override, cio� mettendo nell'entry l'offset associato al campo della super classe
								firstFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).fieldType.nodeType, supEntry.getOffset());
								//aggiorno il tipo della classe corrente dicendo che c'� stato un override all'offset specificato
								classType.addOverridedField(((CllistContext)_localctx).fieldType.nodeType, supEntry.getOffset());
							}
							//aggiorno la symbol table e la virtual table		
							symTable.addEntry((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null),firstFieldEntry);  
					        vt.put((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null), firstFieldEntry);
						
					setState(68);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(60);
						match(COMMA);
						setState(61);
						((CllistContext)_localctx).otherFieldName = match(ID);
						setState(62);
						match(COLON);
						setState(63);
						((CllistContext)_localctx).otherFieldType = type();

								//controllo che non si sia fatta una redifinizione del campo nella stessa classe
								if(fieldsName.contains((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null))) {
									throw new AlreadyDeclaredException(Declaration.Field, (((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null), (((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getLine():0));
								}
								//da qui in poi � uguale alla definizione del primo campo
								fieldsName.add((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null));
								fields.add(new FieldNode((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null), ((CllistContext)_localctx).otherFieldType.nodeType));
								STentry otherFieldEntry = null;
								supEntry = vt.get((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null));
								if(supEntry == null) {
									otherFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).otherFieldType.nodeType, --offset);
									classType.addField(((CllistContext)_localctx).otherFieldType.nodeType);
								} else {
									if(supEntry.isMethod()) {
										throw new IllegalOverridingException(Declaration.Method, Declaration.Field);
									}
									otherFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).otherFieldType.nodeType, supEntry.getOffset());
									classType.addOverridedField(((CllistContext)_localctx).otherFieldType.nodeType, supEntry.getOffset());
								}
										
								symTable.addEntry((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null),otherFieldEntry);  
						        vt.put((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null), otherFieldEntry);
						        
							
						}
						}
						setState(70);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(73);
				match(RPAR);
				setState(74);
				match(CLPAR);
				 
				              	/* ESTENSIONE CON EREDITARIET�: l'offset dei metodi deve essere successivo a quello della super classe (se � presente)
				              	 * perci� parte all'offset corrente di tutti i metodi (se non ho metodi ereditati parto da 0)
				              	 * */
				              	int methodOffset = classType.getMethods().size();
				          	
				              
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(76);
					match(FUN);
					setState(77);
					((CllistContext)_localctx).methodId = match(ID);
					setState(78);
					match(COLON);
					setState(79);
					((CllistContext)_localctx).returnType = type();

					                 	final List<Type> parTypes = new ArrayList<>(); //mi memorizzo tutti i tipi dei parametri dei metoi
							            //creo il function node associato
							            final MethodNode method = new MethodNode((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null),((CllistContext)_localctx).returnType.nodeType, methodOffset); 
							            //OTTIMIZZAZIONE verifico che non ci sia una redifizione del metodo nella stessa classe
							            if(methodsName.contains((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null))) {
											throw new AlreadyDeclaredException(Declaration.Field, (((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null), (((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getLine():0));
										}
										//nome metodo ok, aggiungo il nome del metodo ai nomi dei metodi della classe corrente
										methodsName.add((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null));    
							            //aggiungo alla declarations list la funzione appena dichiarata 
							            methods.add(method);
							            //valori che mi serviranno per aggiungere l'entry nella symbol table
							            final int methodNesting = symTable.getNesting();
							            //come per le funzioni, i parametri sono a + 1 rispetto al fp
					                 	int parOffset = 1;
					                 	//aumento il nesting level della symbol table
					                 	symTable.increaseNesting(new HashMap<>());
					                 
					setState(81);
					match(LPAR);
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(82);
						((CllistContext)_localctx).firstId = match(ID);
						setState(83);
						match(COLON);
						setState(84);
						((CllistContext)_localctx).firstType = hotype();

						                
							            	//aggiungo un parametro alla dichiarazione dei metodi 
							                parTypes.add(((CllistContext)_localctx).firstType.nodeType);
							                ParNode fpar = new ParNode((((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null),((CllistContext)_localctx).firstType.nodeType); //creo nodo ParNode
							                method.addPar(fpar);                                 //lo attacco al MethodNode con addPar
							                //se il parametro era di tipo funzionale occupa doppio offset
							                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
							                //creo la nuova entry per il parametro
							                final STentry entry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).firstType.nodeType, parOffset++);
							                //verifico se � gi� presente un entry con lo stesso nome, nel caso lancio un'eccezione 
							                if (!symTable.addEntry((((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null), entry)){
							                    throw new AlreadyDeclaredException(Declaration.Parameter,(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null),(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getLine():0));
							                }
						                
						              	
						setState(94);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(86);
							match(COMMA);
							setState(87);
							((CllistContext)_localctx).otherId = match(ID);
							setState(88);
							match(COLON);
							setState(89);
							((CllistContext)_localctx).otherType = hotype();

							                	//stesso discorso del primo parametro
												parTypes.add(((CllistContext)_localctx).otherType.nodeType);
												ParNode par = new ParNode((((CllistContext)_localctx).otherId!=null?((CllistContext)_localctx).otherId.getText():null),((CllistContext)_localctx).otherType.nodeType); 
												method.addPar(par);
												//se il parametro era di tipo funzionale occupa doppio offset
												parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
												final STentry otherEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).otherType.nodeType, parOffset++);
												if (!symTable.addEntry((((CllistContext)_localctx).otherId!=null?((CllistContext)_localctx).otherId.getText():null),otherEntry)){
													throw new AlreadyDeclaredException(Declaration.Parameter,(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null),(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getLine():0));
												}
							                
							}
							}
							setState(96);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(99);
					match(RPAR);
					 
						               	// a questo punto posso aggiungere il metodo alla symbol table visto che ho tutte le sue informazioni
						               	final Type methodType = new ArrowType(parTypes,((CllistContext)_localctx).returnType.nodeType);
						               	//ESTENSIONE CON EREDITARIET�: verifico che il metodo sia gi� stato dichiarato, vado a controllare la virtual table
						               	final STentry superMethod = vt.get((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null));
						               	STentry methodEntry = null;
						               	if(superMethod == null) {
						               		//se � null vuol dire che il metodo non era presente, allora lo accodo ai nuovi metodi della classe
						               		methodEntry = STentry.createMethod(methodNesting, methodType, methodOffset++);
						               		classType.addMethod(methodType);
						               	} else {
						               		//ho fatto overriding, allora devo verificare che l'entry associata al nome sia un metodo altrimenti lancio eccezione
						               		if(!superMethod.isMethod()) {
						               			throw new IllegalOverridingException(Declaration.Field, Declaration.Method);
						               		}
						               		//creo la nuova entry associata al metodo usando l'offset del metodo della classe padre
						               		methodEntry = STentry.createMethod(methodNesting, methodType, superMethod.getOffset());
						               		//sovrascrivo il tipo del metodo con quello nuovo
						               		classType.addOverridedMethod(methodType, superMethod.getOffset());
						               		//aggiungo il metodo alla symbol table
						               		//modifico l'offset nel methodNode
						               		method.overrideOffset(superMethod.getOffset());
						               	}   	
					                	
						                symTable.addEntry((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null), methodEntry);
						                // utilizzato per l'offset delle dichiarazioni all'interno del metodo
						                
										vt.put((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null), methodEntry);
					                	int declarationOffset = -1;
					                
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(101);
						match(LET);
						setState(111); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(102);
							match(VAR);
							setState(103);
							((CllistContext)_localctx).varId = match(ID);
							setState(104);
							match(COLON);
							setState(105);
							((CllistContext)_localctx).varType = type();
							setState(106);
							match(ASS);
							setState(107);
							((CllistContext)_localctx).varExp = exp();
							setState(108);
							match(SEMIC);

								               		final VarNode var = new VarNode((((CllistContext)_localctx).varId!=null?((CllistContext)_localctx).varId.getText():null),((CllistContext)_localctx).varType.nodeType, ((CllistContext)_localctx).varExp.ast);
										            //aggiungo alla declarations list la variabile
													method.addDec(var);            
										            //aggiungo la dichiarazione della variabile nella symbol table
										            final STentry varEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).varType.nodeType,--declarationOffset);
										            if (!symTable.addEntry((((CllistContext)_localctx).varId!=null?((CllistContext)_localctx).varId.getText():null),varEntry))
										            {
										            	throw new AlreadyDeclaredException(Declaration.Var,(((CllistContext)_localctx).varId!=null?((CllistContext)_localctx).varId.getText():null),(((CllistContext)_localctx).varId!=null?((CllistContext)_localctx).varId.getLine():0));
										            }
										            //se var � di tipo funzionale allora occuper� offset doppio
										            if(var.getSymbolType() instanceof ArrowType) {
										            	declarationOffset--;
										            } 					
								               
							}
							}
							setState(113); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==VAR );
						setState(115);
						match(IN);
						}
					}

					setState(119);
					((CllistContext)_localctx).methodExp = exp();
					setState(120);
					match(SEMIC);

						               		method.addBody(((CllistContext)_localctx).methodExp.ast); //aggiungo il body al metodo
						               		symTable.decreaseNesting(); //esco dal nesting nel metodo corrente 
						               
					}
					}
					setState(127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(128);
				match(CRPAR);

					        //a questo punto ho tutti i metodi e tutti i campi della classe corrente, posso creare il class node
				          	final DeclarationNode classNode = new ClassNode(classType, (((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), fields, methods, superEntry);	
				          	//aggiungo la classe ai nodi da restituire
				          	_localctx.classes.add(classNode);
				          	//esco dal nesting level
				          	symTable.decreaseNesting();
				          
				}
				}
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclistContext extends ParserRuleContext {
		public List<DeclarationNode> astlist;
		public Token id;
		public HotypeContext t;
		public ExpContext e;
		public Token funId;
		public TypeContext retType;
		public Token firstId;
		public HotypeContext firstType;
		public Token otherId;
		public HotypeContext otherType;
		public DeclistContext declarations;
		public ExpContext bodyExp;
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public List<DeclistContext> declist() {
			return getRuleContexts(DeclistContext.class);
		}
		public DeclistContext declist(int i) {
			return getRuleContext(DeclistContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public DeclistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declist; }
	}

	public final DeclistContext declist() throws RecognitionException {
		DeclistContext _localctx = new DeclistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((DeclistContext)_localctx).astlist =  new ArrayList<>();
			        int offset = classesDecl == true ? classOffset : -1;
			        classesDecl = false;
			    
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(179);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(135);
					match(VAR);
					setState(136);
					((DeclistContext)_localctx).id = match(ID);
					setState(137);
					match(COLON);
					setState(138);
					((DeclistContext)_localctx).t = hotype();
					setState(139);
					match(ASS);
					setState(140);
					((DeclistContext)_localctx).e = exp();

					    		//creo il nodo associato alla variabile che sto dichiarando
					            final VarNode var = new VarNode((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),((DeclistContext)_localctx).t.nodeType, ((DeclistContext)_localctx).e.ast);
					            //aggiungo alla declarations list la variabile
					            _localctx.astlist.add(var);
					            //aggiungo la dichiarazione della variabile nella symbol table
					            final STentry varEntry = STentry.createStandard(symTable.getNesting(), ((DeclistContext)_localctx).t.nodeType,--offset);
					            if (!symTable.addEntry((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),varEntry))
					            {
					            	throw new AlreadyDeclaredException(Declaration.Var,(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getLine():0));
					            }
					            //se var � di tipo funzionale allora occuper� offset doppio
					            if(var.getSymbolType() instanceof ArrowType) {
					            	offset--;
					            }
					        
					}
					break;
				case FUN:
					{
					setState(143);
					match(FUN);
					setState(144);
					((DeclistContext)_localctx).funId = match(ID);
					setState(145);
					match(COLON);
					setState(146);
					((DeclistContext)_localctx).retType = type();

					        	//creo il function node associato
					            final FunNode function = new FunNode((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),((DeclistContext)_localctx).retType.nodeType);     
					            //aggiungo alla declarations list la funzione appena dichiarata 
					            _localctx.astlist.add(function);
					            //valori che mi serviranno per aggiungere l'entry nella symbol table
					            final int functionNesting = symTable.getNesting();
					            final int functionOffset = --offset;
					            offset--; //decremento ulteriormente l'offset perch� le funzioni occupano offset doppio
					            //sto entrando dentro la funzione quindi devo aumentare il nesting level della symbol table
					            symTable.increaseNesting(new HashMap<>());
					        
					setState(148);
					match(LPAR);

					            	//parTypes � l'insieme di parametri della funzione
					                List<Type> parTypes = new ArrayList<Type>();
					                //offset parte da 1 e cresce
					          	    int parOffset = 1;
					            
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(150);
						((DeclistContext)_localctx).firstId = match(ID);
						setState(151);
						match(COLON);
						setState(152);
						((DeclistContext)_localctx).firstType = hotype();

						            	//aggiungo un parametro alla dichiarazione di funzioni
						                parTypes.add(((DeclistContext)_localctx).firstType.nodeType);
						                
						                ParNode fpar = new ParNode((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),((DeclistContext)_localctx).firstType.nodeType); //creo nodo ParNode
						                function.addPar(fpar);                                 //lo attacco al FunNode con addPar
						                //se il parametro era di tipo funzionale occupa doppio offset
						                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
						                final STentry entry = STentry.createStandard(symTable.getNesting(), ((DeclistContext)_localctx).firstType.nodeType, parOffset++);
						                if (!symTable.addEntry((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null), entry)){
						                    throw new AlreadyDeclaredException(Declaration.Parameter,(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getLine():0));
						                }
						                
						              
						setState(162);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(154);
							match(COMMA);
							setState(155);
							((DeclistContext)_localctx).otherId = match(ID);
							setState(156);
							match(COLON);
							setState(157);
							((DeclistContext)_localctx).otherType = hotype();

							                   parTypes.add(((DeclistContext)_localctx).otherType.nodeType);
							                   ParNode par = new ParNode((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),((DeclistContext)_localctx).otherType.nodeType); 
							                   function.addPar(par);
							                	//se il parametro era di tipo funzionale occupa doppio offset
							                	parOffset = par.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
							                	final STentry otherEntry = STentry.createStandard(symTable.getNesting(), ((DeclistContext)_localctx).otherType.nodeType, parOffset++);
							                    if (!symTable.addEntry((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),otherEntry)){
							                    	throw new AlreadyDeclaredException(Declaration.Parameter,(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getLine():0));
							                	}
							                
							}
							}
							setState(164);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(167);
					match(RPAR);

					                	//a questo punto posso definire il tipo della funzione e di conseguenza lo aggiunger� alla symbol table
					                	final Type funType = new ArrowType(parTypes,((DeclistContext)_localctx).retType.nodeType);
					                	final STentry funEntry = STentry.createStandard(functionNesting, funType, functionOffset);
						                if(!symTable.addEntry((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null), funEntry)){
						                	throw new AlreadyDeclaredException(Declaration.Function,(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getLine():0));
						                }
					                
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(169);
						match(LET);
						setState(170);
						((DeclistContext)_localctx).declarations = declist();
						((DeclistContext)_localctx).declarations.astlist.forEach(x -> function.addDec(x));
						setState(172);
						match(IN);
						}
					}

					setState(176);
					((DeclistContext)_localctx).bodyExp = exp();

					                  function.addBody(((DeclistContext)_localctx).bodyExp.ast);
					                  symTable.decreaseNesting();
					              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(181);
				match(SEMIC);
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR || _la==FUN );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Node ast;
		public TermContext first;
		public TermContext other;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(FOOLParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(FOOLParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(FOOLParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(FOOLParser.MINUS, i);
		}
		public List<TerminalNode> OR() { return getTokens(FOOLParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(FOOLParser.OR, i);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			((ExpContext)_localctx).first = term();

			        ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).first.ast;
			    
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(201);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(189);
					match(PLUS);
					setState(190);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case MINUS:
					{
					setState(193);
					match(MINUS);
					setState(194);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case OR:
					{
					setState(197);
					match(OR);
					setState(198);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).other.ast); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Node ast;
		public FactorContext firstFactor;
		public FactorContext other;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(FOOLParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(FOOLParser.TIMES, i);
		}
		public List<TerminalNode> DIV() { return getTokens(FOOLParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FOOLParser.DIV, i);
		}
		public List<TerminalNode> AND() { return getTokens(FOOLParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(FOOLParser.AND, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			((TermContext)_localctx).firstFactor = factor();

			        ((TermContext)_localctx).ast =  ((TermContext)_localctx).firstFactor.ast;
			    
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(220);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(208);
					match(TIMES);
					setState(209);
					((TermContext)_localctx).other = factor();
					 ((TermContext)_localctx).ast =  new MultNode (_localctx.ast, ((TermContext)_localctx).other.ast); 
					}
					break;
				case DIV:
					{
					setState(212);
					match(DIV);
					setState(213);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new DivNode (_localctx.ast, ((TermContext)_localctx).other.ast);
					}
					break;
				case AND:
					{
					setState(216);
					match(AND);
					setState(217);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast, ((TermContext)_localctx).other.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Node ast;
		public ValueContext first;
		public ValueContext other;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(FOOLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(FOOLParser.EQ, i);
		}
		public List<TerminalNode> GE() { return getTokens(FOOLParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(FOOLParser.GE, i);
		}
		public List<TerminalNode> LE() { return getTokens(FOOLParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(FOOLParser.LE, i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			((FactorContext)_localctx).first = value();

			        ((FactorContext)_localctx).ast =  ((FactorContext)_localctx).first.ast;
			    
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(239);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(227);
					match(EQ);
					setState(228);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new EqualNode (_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case GE:
					{
					setState(231);
					match(GE);
					setState(232);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new GreaterEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case LE:
					{
					setState(235);
					match(LE);
					setState(236);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new LessEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public Node ast;
		public Token n;
		public Token id;
		public ExpContext e;
		public ExpContext other;
		public ExpContext c;
		public ExpContext th;
		public ExpContext first;
		public Token methodId;
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public TerminalNode PRINT() { return getToken(FOOLParser.PRINT, 0); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			setState(339);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				((ValueContext)_localctx).n = match(INTEGER);
				 ((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(TRUE);
				 ((ValueContext)_localctx).ast =  BoolNode.trueNode();
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(FALSE);
				 ((ValueContext)_localctx).ast =  BoolNode.falseNode();
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				match(NULL);
				((ValueContext)_localctx).ast =  EmptyNode.instance();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(252);
				match(NEW);
				setState(253);
				((ValueContext)_localctx).id = match(ID);

					    		//lista dei parametri passata al costruttore
					    		final List<Node> parNodes = new ArrayList<>();
					    		//per verificare se � presente nelle classe uso la class table
					    		if (!classTable.isClassPresent((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null))){
					    			throw new NotDeclaredException(Declaration.Class,(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null), (((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0)); 			
					    		}
					    		//vado a prendere l'entry associata alla classe
					    		final STentry entry = symTable.findEntryById((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null)).get();
					    	
				setState(255);
				match(LPAR);
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(256);
					((ValueContext)_localctx).e = exp();
					parNodes.add(((ValueContext)_localctx).e.ast);
					setState(264);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(258);
						match(COMMA);
						setState(259);
						((ValueContext)_localctx).other = exp();
						parNodes.add(((ValueContext)_localctx).other.ast);
						}
						}
						setState(266);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(269);
				match(RPAR);

					    		((ValueContext)_localctx).ast =  new NewNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null), entry, parNodes);
					    	
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(271);
				match(IF);
				setState(272);
				((ValueContext)_localctx).c = exp();
				setState(273);
				match(THEN);
				setState(274);
				match(CLPAR);
				setState(275);
				((ValueContext)_localctx).th = exp();
				setState(276);
				match(CRPAR);
				setState(277);
				match(ELSE);
				setState(278);
				match(CLPAR);
				setState(279);
				((ValueContext)_localctx).e = exp();
				setState(280);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).c.ast, ((ValueContext)_localctx).th.ast, ((ValueContext)_localctx).e.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(283);
				match(NOT);
				setState(284);
				match(LPAR);
				setState(285);
				((ValueContext)_localctx).e = exp();
				setState(286);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast); 
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 8);
				{
				setState(289);
				match(PRINT);
				setState(290);
				match(LPAR);
				setState(291);
				((ValueContext)_localctx).e = exp();
				setState(292);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 9);
				{
				setState(295);
				match(LPAR);
				setState(296);
				((ValueContext)_localctx).e = exp();
				setState(297);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(300);
				((ValueContext)_localctx).id = match(ID);

					    	//vado a ricercare l'entry associata all'id 
					    	final Optional<STentry> entry = symTable.findEntryById((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null));
					    	//nel caso che non sia presente lancio l'eccezione not declared
				            if (!entry.isPresent()){
				            	throw new NotDeclaredException((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0));
				            }
				            //se non ci sono argomenti dopo l'id immagino che sia una variabile
				            ((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry.get(),symTable.getNesting());
				            //in arguments metter� l'insieme di valori trovati nella chiamata di funzione (o nel caso sia un oggetto nella chiamata a metodo)
				            final List<Node> arguments = new ArrayList<Node>();
				        
				setState(337);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(302);
					match(LPAR);
					setState(314);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(303);
						((ValueContext)_localctx).first = exp();

						            arguments.add(((ValueContext)_localctx).first.ast);
						        
						setState(311);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(305);
							match(COMMA);
							setState(306);
							((ValueContext)_localctx).other = exp();

							            arguments.add(((ValueContext)_localctx).other.ast);
							        
							}
							}
							setState(313);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(316);
					match(RPAR);

					        	if(entry.get().isMethod()) {
					        		((ValueContext)_localctx).ast =  new MethodCallNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry.get(),arguments,symTable.getNesting());		
					        	} else {
					    			((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry.get(),arguments,symTable.getNesting());		
					        	}
					        
					}
					break;
				case DOT:
					{
					setState(318);
					match(DOT);
					setState(319);
					((ValueContext)_localctx).methodId = match(ID);

					             	//prendo il tipo dell'oggetto, se non � un RefType lancio un eccezione 
					             	final Type objectType = entry.get().getType();
					             	if(!(objectType instanceof RefType)) {
					             		//TODO fare eccezione apposita
					             		throw new NotDeclaredException(Declaration.Class,(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0));
					             	}
					             	//prendo il nome della classe
					             	final String className = ((RefType)objectType).getId();
					             	//se il nome della classe non � presente lancio un'eccezione
					             	if(!classTable.isClassPresent(className)){
					             		throw new NotDeclaredException(Declaration.Class,(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0));
					             	}
					             	//vado a prendere la virtual table per prendere l'entry dell metodo che sto chiamando
					             	final Map<String, STentry> virtualTable = classTable.getVirtualTable(className);
					             	//se il metodo non � dichiarato lancio un'eccezione
					             	if(!virtualTable.containsKey((((ValueContext)_localctx).methodId!=null?((ValueContext)_localctx).methodId.getText():null))){
					             		throw new NotDeclaredException(Declaration.Method,(((ValueContext)_localctx).methodId!=null?((ValueContext)_localctx).methodId.getText():null),(((ValueContext)_localctx).methodId!=null?((ValueContext)_localctx).methodId.getLine():0));
					             	}
					             	final STentry methodEntry = virtualTable.get((((ValueContext)_localctx).methodId!=null?((ValueContext)_localctx).methodId.getText():null));
					             	final List<Node> methodPar = new ArrayList<>();
					             
					setState(321);
					match(LPAR);
					setState(333);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(322);
						((ValueContext)_localctx).e = exp();

						             		methodPar.add(((ValueContext)_localctx).e.ast);
						             	
						setState(330);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(324);
							match(COMMA);
							setState(325);
							((ValueContext)_localctx).other = exp();

							             		methodPar.add(((ValueContext)_localctx).other.ast);
							             	
							}
							}
							setState(332);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(335);
					match(RPAR);

						         	((ValueContext)_localctx).ast =  new ClassCallNode((((ValueContext)_localctx).methodId!=null?((ValueContext)_localctx).methodId.getText():null), entry.get(), methodEntry, methodPar, symTable.getNesting());
						         
					}
					break;
				case PLUS:
				case MINUS:
				case TIMES:
				case DIV:
				case RPAR:
				case CRPAR:
				case SEMIC:
				case COMMA:
				case OR:
				case AND:
				case GE:
				case LE:
				case EQ:
				case THEN:
					break;
				default:
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HotypeContext extends ParserRuleContext {
		public Type nodeType;
		public TypeContext t;
		public ArrowContext a;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrowContext arrow() {
			return getRuleContext(ArrowContext.class,0);
		}
		public HotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hotype; }
	}

	public final HotypeContext hotype() throws RecognitionException {
		HotypeContext _localctx = new HotypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_hotype);
		try {
			setState(347);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).nodeType =  ((HotypeContext)_localctx).t.nodeType;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(344);
				((HotypeContext)_localctx).a = arrow();
				((HotypeContext)_localctx).nodeType =  ((HotypeContext)_localctx).a.nodeType;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type nodeType;
		public Token id;
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(349);
				match(INT);
				((TypeContext)_localctx).nodeType =  IntType.instance();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				match(BOOL);
				((TypeContext)_localctx).nodeType =  BoolType.instance();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(353);
				((TypeContext)_localctx).id = match(ID);

				 	    	if(!classTable.isClassPresent((((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null))) {
				 	    		throw new NotDeclaredException(Declaration.Class,(((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null),(((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getLine():0));
				 	    	}
				 	    	((TypeContext)_localctx).nodeType =  new RefType((((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null));
				 	    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowContext extends ParserRuleContext {
		public Type nodeType;
		public HotypeContext firstParameter;
		public HotypeContext otherParameter;
		public TypeContext returnType;
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode ARROW() { return getToken(FOOLParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public ArrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrow; }
	}

	public final ArrowContext arrow() throws RecognitionException {
		ArrowContext _localctx = new ArrowContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			final List<Type> parameterList = new ArrayList<>();
			setState(358);
			match(LPAR);
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(359);
				((ArrowContext)_localctx).firstParameter = hotype();
				parameterList.add(((ArrowContext)_localctx).firstParameter.nodeType);
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(361);
					match(COMMA);
					setState(362);
					((ArrowContext)_localctx).otherParameter = hotype();
					parameterList.add(((ArrowContext)_localctx).otherParameter.nodeType);
					}
					}
					setState(369);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(372);
			match(RPAR);
			setState(373);
			match(ARROW);
			setState(374);
			((ArrowContext)_localctx).returnType = type();
			((ArrowContext)_localctx).nodeType =  new ArrowType(parameterList, ((ArrowContext)_localctx).returnType.nodeType);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u017c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\3\2\3\2\5\2#\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3E\n\3\f\3"+
		"\16\3H\13\3\5\3J\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3_\n\3\f\3\16\3b\13\3\5\3d\n\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3r\n\3\r\3\16\3s\3\3\3\3\5\3"+
		"x\n\3\3\3\3\3\3\3\3\3\7\3~\n\3\f\3\16\3\u0081\13\3\3\3\3\3\6\3\u0085\n"+
		"\3\r\3\16\3\u0086\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00a3\n\4\f\4"+
		"\16\4\u00a6\13\4\5\4\u00a8\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00b1\n"+
		"\4\3\4\3\4\3\4\5\4\u00b6\n\4\3\4\3\4\6\4\u00ba\n\4\r\4\16\4\u00bb\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00cc\n\5\f\5"+
		"\16\5\u00cf\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\7\6\u00df\n\6\f\6\16\6\u00e2\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\7\7\u00f2\n\7\f\7\16\7\u00f5\13\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0109\n"+
		"\b\f\b\16\b\u010c\13\b\5\b\u010e\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0138\n\b"+
		"\f\b\16\b\u013b\13\b\5\b\u013d\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b\u014b\n\b\f\b\16\b\u014e\13\b\5\b\u0150\n\b\3\b\3\b\5"+
		"\b\u0154\n\b\5\b\u0156\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u015e\n\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\n\u0166\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\7\13\u0170\n\13\f\13\16\13\u0173\13\13\5\13\u0175\n\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2\u01a1\2+\3\2\2"+
		"\2\4/\3\2\2\2\6\u0088\3\2\2\2\b\u00bd\3\2\2\2\n\u00d0\3\2\2\2\f\u00e3"+
		"\3\2\2\2\16\u0155\3\2\2\2\20\u015d\3\2\2\2\22\u0165\3\2\2\2\24\u0167\3"+
		"\2\2\2\26\27\b\2\1\2\27\"\7\34\2\2\30\31\5\4\3\2\31\35\b\2\1\2\32\33\5"+
		"\6\4\2\33\34\b\2\1\2\34\36\3\2\2\2\35\32\3\2\2\2\35\36\3\2\2\2\36#\3\2"+
		"\2\2\37 \5\6\4\2 !\b\2\1\2!#\3\2\2\2\"\30\3\2\2\2\"\37\3\2\2\2#$\3\2\2"+
		"\2$%\7\35\2\2%&\5\b\5\2&\'\b\2\1\2\',\3\2\2\2()\5\b\5\2)*\b\2\1\2*,\3"+
		"\2\2\2+\26\3\2\2\2+(\3\2\2\2,-\3\2\2\2-.\7\13\2\2.\3\3\2\2\2/\u0084\b"+
		"\3\1\2\60\61\7 \2\2\61\62\7(\2\2\62\66\b\3\1\2\63\64\7!\2\2\64\65\7(\2"+
		"\2\65\67\b\3\1\2\66\63\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\b\3\1\29I\7"+
		"\7\2\2:;\7(\2\2;<\7\f\2\2<=\5\22\n\2=F\b\3\1\2>?\7\r\2\2?@\7(\2\2@A\7"+
		"\f\2\2AB\5\22\n\2BC\b\3\1\2CE\3\2\2\2D>\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG"+
		"\3\2\2\2GJ\3\2\2\2HF\3\2\2\2I:\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7\b\2\2L"+
		"M\7\t\2\2M\177\b\3\1\2NO\7\37\2\2OP\7(\2\2PQ\7\f\2\2QR\5\22\n\2RS\b\3"+
		"\1\2Sc\7\7\2\2TU\7(\2\2UV\7\f\2\2VW\5\20\t\2W`\b\3\1\2XY\7\r\2\2YZ\7("+
		"\2\2Z[\7\f\2\2[\\\5\20\t\2\\]\b\3\1\2]_\3\2\2\2^X\3\2\2\2_b\3\2\2\2`^"+
		"\3\2\2\2`a\3\2\2\2ad\3\2\2\2b`\3\2\2\2cT\3\2\2\2cd\3\2\2\2de\3\2\2\2e"+
		"f\7\b\2\2fw\b\3\1\2gq\7\34\2\2hi\7\36\2\2ij\7(\2\2jk\7\f\2\2kl\5\22\n"+
		"\2lm\7\25\2\2mn\5\b\5\2no\7\13\2\2op\b\3\1\2pr\3\2\2\2qh\3\2\2\2rs\3\2"+
		"\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\7\35\2\2vx\3\2\2\2wg\3\2\2\2wx\3"+
		"\2\2\2xy\3\2\2\2yz\5\b\5\2z{\7\13\2\2{|\b\3\1\2|~\3\2\2\2}N\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177"+
		"\3\2\2\2\u0082\u0083\7\n\2\2\u0083\u0085\b\3\1\2\u0084\60\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\5\3\2\2\2"+
		"\u0088\u00b9\b\4\1\2\u0089\u008a\7\36\2\2\u008a\u008b\7(\2\2\u008b\u008c"+
		"\7\f\2\2\u008c\u008d\5\20\t\2\u008d\u008e\7\25\2\2\u008e\u008f\5\b\5\2"+
		"\u008f\u0090\b\4\1\2\u0090\u00b6\3\2\2\2\u0091\u0092\7\37\2\2\u0092\u0093"+
		"\7(\2\2\u0093\u0094\7\f\2\2\u0094\u0095\5\22\n\2\u0095\u0096\b\4\1\2\u0096"+
		"\u0097\7\7\2\2\u0097\u00a7\b\4\1\2\u0098\u0099\7(\2\2\u0099\u009a\7\f"+
		"\2\2\u009a\u009b\5\20\t\2\u009b\u00a4\b\4\1\2\u009c\u009d\7\r\2\2\u009d"+
		"\u009e\7(\2\2\u009e\u009f\7\f\2\2\u009f\u00a0\5\20\t\2\u00a0\u00a1\b\4"+
		"\1\2\u00a1\u00a3\3\2\2\2\u00a2\u009c\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u0098\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00aa\7\b\2\2\u00aa\u00b0\b\4\1\2\u00ab\u00ac\7\34\2\2\u00ac\u00ad\5"+
		"\6\4\2\u00ad\u00ae\b\4\1\2\u00ae\u00af\7\35\2\2\u00af\u00b1\3\2\2\2\u00b0"+
		"\u00ab\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\5\b"+
		"\5\2\u00b3\u00b4\b\4\1\2\u00b4\u00b6\3\2\2\2\u00b5\u0089\3\2\2\2\u00b5"+
		"\u0091\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7\13\2\2\u00b8\u00ba\3"+
		"\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\7\3\2\2\2\u00bd\u00be\5\n\6\2\u00be\u00cd\b\5\1\2"+
		"\u00bf\u00c0\7\3\2\2\u00c0\u00c1\5\n\6\2\u00c1\u00c2\b\5\1\2\u00c2\u00cc"+
		"\3\2\2\2\u00c3\u00c4\7\4\2\2\u00c4\u00c5\5\n\6\2\u00c5\u00c6\b\5\1\2\u00c6"+
		"\u00cc\3\2\2\2\u00c7\u00c8\7\17\2\2\u00c8\u00c9\5\n\6\2\u00c9\u00ca\b"+
		"\5\1\2\u00ca\u00cc\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb"+
		"\u00c7\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\t\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\5\f\7\2\u00d1\u00e0"+
		"\b\6\1\2\u00d2\u00d3\7\5\2\2\u00d3\u00d4\5\f\7\2\u00d4\u00d5\b\6\1\2\u00d5"+
		"\u00df\3\2\2\2\u00d6\u00d7\7\6\2\2\u00d7\u00d8\5\f\7\2\u00d8\u00d9\b\6"+
		"\1\2\u00d9\u00df\3\2\2\2\u00da\u00db\7\20\2\2\u00db\u00dc\5\f\7\2\u00dc"+
		"\u00dd\b\6\1\2\u00dd\u00df\3\2\2\2\u00de\u00d2\3\2\2\2\u00de\u00d6\3\2"+
		"\2\2\u00de\u00da\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\13\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\5\16\b"+
		"\2\u00e4\u00f3\b\7\1\2\u00e5\u00e6\7\24\2\2\u00e6\u00e7\5\16\b\2\u00e7"+
		"\u00e8\b\7\1\2\u00e8\u00f2\3\2\2\2\u00e9\u00ea\7\22\2\2\u00ea\u00eb\5"+
		"\16\b\2\u00eb\u00ec\b\7\1\2\u00ec\u00f2\3\2\2\2\u00ed\u00ee\7\23\2\2\u00ee"+
		"\u00ef\5\16\b\2\u00ef\u00f0\b\7\1\2\u00f0\u00f2\3\2\2\2\u00f1\u00e5\3"+
		"\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\r\3\2\2\2\u00f5\u00f3\3\2\2\2"+
		"\u00f6\u00f7\7\'\2\2\u00f7\u0156\b\b\1\2\u00f8\u00f9\7\26\2\2\u00f9\u0156"+
		"\b\b\1\2\u00fa\u00fb\7\27\2\2\u00fb\u0156\b\b\1\2\u00fc\u00fd\7#\2\2\u00fd"+
		"\u0156\b\b\1\2\u00fe\u00ff\7\"\2\2\u00ff\u0100\7(\2\2\u0100\u0101\b\b"+
		"\1\2\u0101\u010d\7\7\2\2\u0102\u0103\5\b\5\2\u0103\u010a\b\b\1\2\u0104"+
		"\u0105\7\r\2\2\u0105\u0106\5\b\5\2\u0106\u0107\b\b\1\2\u0107\u0109\3\2"+
		"\2\2\u0108\u0104\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u0102\3\2"+
		"\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\7\b\2\2\u0110"+
		"\u0156\b\b\1\2\u0111\u0112\7\30\2\2\u0112\u0113\5\b\5\2\u0113\u0114\7"+
		"\31\2\2\u0114\u0115\7\t\2\2\u0115\u0116\5\b\5\2\u0116\u0117\7\n\2\2\u0117"+
		"\u0118\7\32\2\2\u0118\u0119\7\t\2\2\u0119\u011a\5\b\5\2\u011a\u011b\7"+
		"\n\2\2\u011b\u011c\b\b\1\2\u011c\u0156\3\2\2\2\u011d\u011e\7\21\2\2\u011e"+
		"\u011f\7\7\2\2\u011f\u0120\5\b\5\2\u0120\u0121\7\b\2\2\u0121\u0122\b\b"+
		"\1\2\u0122\u0156\3\2\2\2\u0123\u0124\7\33\2\2\u0124\u0125\7\7\2\2\u0125"+
		"\u0126\5\b\5\2\u0126\u0127\7\b\2\2\u0127\u0128\b\b\1\2\u0128\u0156\3\2"+
		"\2\2\u0129\u012a\7\7\2\2\u012a\u012b\5\b\5\2\u012b\u012c\7\b\2\2\u012c"+
		"\u012d\b\b\1\2\u012d\u0156\3\2\2\2\u012e\u012f\7(\2\2\u012f\u0153\b\b"+
		"\1\2\u0130\u013c\7\7\2\2\u0131\u0132\5\b\5\2\u0132\u0139\b\b\1\2\u0133"+
		"\u0134\7\r\2\2\u0134\u0135\5\b\5\2\u0135\u0136\b\b\1\2\u0136\u0138\3\2"+
		"\2\2\u0137\u0133\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u0131\3\2"+
		"\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\7\b\2\2\u013f"+
		"\u0154\b\b\1\2\u0140\u0141\7\16\2\2\u0141\u0142\7(\2\2\u0142\u0143\b\b"+
		"\1\2\u0143\u014f\7\7\2\2\u0144\u0145\5\b\5\2\u0145\u014c\b\b\1\2\u0146"+
		"\u0147\7\r\2\2\u0147\u0148\5\b\5\2\u0148\u0149\b\b\1\2\u0149\u014b\3\2"+
		"\2\2\u014a\u0146\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c"+
		"\u014d\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0144\3\2"+
		"\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\7\b\2\2\u0152"+
		"\u0154\b\b\1\2\u0153\u0130\3\2\2\2\u0153\u0140\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0154\u0156\3\2\2\2\u0155\u00f6\3\2\2\2\u0155\u00f8\3\2\2\2\u0155"+
		"\u00fa\3\2\2\2\u0155\u00fc\3\2\2\2\u0155\u00fe\3\2\2\2\u0155\u0111\3\2"+
		"\2\2\u0155\u011d\3\2\2\2\u0155\u0123\3\2\2\2\u0155\u0129\3\2\2\2\u0155"+
		"\u012e\3\2\2\2\u0156\17\3\2\2\2\u0157\u0158\5\22\n\2\u0158\u0159\b\t\1"+
		"\2\u0159\u015e\3\2\2\2\u015a\u015b\5\24\13\2\u015b\u015c\b\t\1\2\u015c"+
		"\u015e\3\2\2\2\u015d\u0157\3\2\2\2\u015d\u015a\3\2\2\2\u015e\21\3\2\2"+
		"\2\u015f\u0160\7$\2\2\u0160\u0166\b\n\1\2\u0161\u0162\7%\2\2\u0162\u0166"+
		"\b\n\1\2\u0163\u0164\7(\2\2\u0164\u0166\b\n\1\2\u0165\u015f\3\2\2\2\u0165"+
		"\u0161\3\2\2\2\u0165\u0163\3\2\2\2\u0166\23\3\2\2\2\u0167\u0168\b\13\1"+
		"\2\u0168\u0174\7\7\2\2\u0169\u016a\5\20\t\2\u016a\u0171\b\13\1\2\u016b"+
		"\u016c\7\r\2\2\u016c\u016d\5\20\t\2\u016d\u016e\b\13\1\2\u016e\u0170\3"+
		"\2\2\2\u016f\u016b\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171"+
		"\u0172\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0169\3\2"+
		"\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\7\b\2\2\u0177"+
		"\u0178\7&\2\2\u0178\u0179\5\22\n\2\u0179\u017a\b\13\1\2\u017a\25\3\2\2"+
		"\2%\35\"+\66FI`csw\177\u0086\u00a4\u00a7\u00b0\u00b5\u00bb\u00cb\u00cd"+
		"\u00de\u00e0\u00f1\u00f3\u010a\u010d\u0139\u013c\u014c\u014f\u0153\u0155"+
		"\u015d\u0165\u0171\u0174";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}