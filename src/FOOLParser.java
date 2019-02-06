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

					classesDecl = true;
					((CllistContext)_localctx).classes =  new ArrayList<>();
				
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				match(CLASS);
				setState(47);
				((CllistContext)_localctx).classId = match(ID);

						final List<FieldNode> fields = new ArrayList<>();
						final List<MethodNode> methods = new ArrayList<>();
						final Map<String, STentry> vt = new HashMap<>();
						classTable.addClass((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), vt);
						symTable.increaseNesting(new HashMap<>()/*da riempire quando far� ereditariet� */);
					
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(49);
					match(EXTENDS);
					setState(50);
					match(ID);
					/*da fare ereditariet� */
					}
				}

				setState(54);
				match(LPAR);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(55);
					((CllistContext)_localctx).fieldName = match(ID);
					setState(56);
					match(COLON);
					setState(57);
					((CllistContext)_localctx).fieldType = type();

							int offset = 0;
							fields.add(new FieldNode((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null), ((CllistContext)_localctx).fieldType.nodeType));	
							final STentry firstFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).fieldType.nodeType, --offset);
							if (!symTable.addEntry((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null),firstFieldEntry))
					        {
					        	throw new AlreadyDeclaredException(Declaration.Field,(((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null),(((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getLine():0));
					        }
					        vt.put((((CllistContext)_localctx).fieldName!=null?((CllistContext)_localctx).fieldName.getText():null), firstFieldEntry);
						
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(59);
						match(COMMA);
						setState(60);
						((CllistContext)_localctx).otherFieldName = match(ID);
						setState(61);
						match(COLON);
						setState(62);
						((CllistContext)_localctx).otherFieldType = type();

								fields.add(new FieldNode((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null), ((CllistContext)_localctx).otherFieldType.nodeType));
								final STentry otherFieldEntry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).otherFieldType.nodeType, --offset);
								if (!symTable.addEntry((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null),otherFieldEntry))
						        {
						        	throw new AlreadyDeclaredException(Declaration.Field,(((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null),(((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getLine():0));
						        }
						        vt.put((((CllistContext)_localctx).otherFieldName!=null?((CllistContext)_localctx).otherFieldName.getText():null), otherFieldEntry);
						        
							
						}
						}
						setState(69);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(72);
				match(RPAR);
				setState(73);
				match(CLPAR);
				 /*ricorda che dopo metodi e campi dovranno essere arricchiti con quelli ereditati */
				              	int methodOffset = 0;
				              	final ClassType classType = new ClassType((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), 
				          		new ArrayList<>(),
				          		fields.stream().map(DeclarationNode::getSymbolType).collect(Collectors.toList()));
				          		final STentry classEntry = STentry.createStandard(0, classType, --classOffset);
				          		symTable.addEntry((((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null),classEntry);
				          	
				              
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(75);
					match(FUN);
					setState(76);
					((CllistContext)_localctx).methodId = match(ID);
					setState(77);
					match(COLON);
					setState(78);
					((CllistContext)_localctx).returnType = type();

					                 	final List<Type> parTypes = new ArrayList<>();
							            //creo il function node associato
							            final MethodNode method = new MethodNode((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null),((CllistContext)_localctx).returnType.nodeType, methodOffset);     
							            //aggiungo alla declarations list la funzione appena dichiarata 
							            methods.add(method);
							            //valori che mi serviranno per aggiungere l'entry nella symbol table
							            final int methodNesting = symTable.getNesting();
							            symTable.increaseNesting(new HashMap<>());
					                 	int parOffset = 1;
					                 
					setState(80);
					match(LPAR);
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(81);
						((CllistContext)_localctx).firstId = match(ID);
						setState(82);
						match(COLON);
						setState(83);
						((CllistContext)_localctx).firstType = hotype();

							            	//aggiungo un parametro alla dichiarazione di funzioni
							                parTypes.add(((CllistContext)_localctx).firstType.nodeType);
							                ParNode fpar = new ParNode((((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null),((CllistContext)_localctx).firstType.nodeType); //creo nodo ParNode
							                method.addPar(fpar);                                 //lo attacco al FunNode con addPar
							                //se il parametro era di tipo funzionale occupa doppio offset
							                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
							                final STentry entry = STentry.createStandard(symTable.getNesting(), ((CllistContext)_localctx).firstType.nodeType, parOffset++);
							                if (!symTable.addEntry((((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null), entry)){
							                    throw new AlreadyDeclaredException(Declaration.Parameter,(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getText():null),(((CllistContext)_localctx).firstId!=null?((CllistContext)_localctx).firstId.getLine():0));
							                }
						                
						              	
						setState(93);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(85);
							match(COMMA);
							setState(86);
							((CllistContext)_localctx).otherId = match(ID);
							setState(87);
							match(COLON);
							setState(88);
							((CllistContext)_localctx).otherType = hotype();

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
							setState(95);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(98);
					match(RPAR);
					 
						               	// a questo punto posso aggiungere il metodo alla symbol table visto che ho tutte le sue informazioni
						               	final Type methodType = new ArrowType(parTypes,((CllistContext)_localctx).returnType.nodeType);
						               	classType.addMethod(methodType);
					                	final STentry methodEntry = STentry.createMethod(methodNesting, methodType, methodOffset++);
						                if(!symTable.addEntry((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null), methodEntry)){
						                	throw new AlreadyDeclaredException(Declaration.Function,(((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null),(((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getLine():0));
						                }
						                // utilizzato per l'offset delle dichiarazioni all'interno del metodo
						                
										vt.put((((CllistContext)_localctx).methodId!=null?((CllistContext)_localctx).methodId.getText():null), methodEntry);
					                	int declarationOffset = -1;
					                
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(100);
						match(LET);
						setState(110); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(101);
							match(VAR);
							setState(102);
							((CllistContext)_localctx).varId = match(ID);
							setState(103);
							match(COLON);
							setState(104);
							((CllistContext)_localctx).varType = type();
							setState(105);
							match(ASS);
							setState(106);
							((CllistContext)_localctx).varExp = exp();
							setState(107);
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
							setState(112); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==VAR );
						setState(114);
						match(IN);
						}
					}

					setState(118);
					((CllistContext)_localctx).methodExp = exp();
					setState(119);
					match(SEMIC);

						               		method.addBody(((CllistContext)_localctx).methodExp.ast);
						               		symTable.decreaseNesting();
						               
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(127);
				match(CRPAR);

				          	final DeclarationNode classNode = new ClassNode(classType, (((CllistContext)_localctx).classId!=null?((CllistContext)_localctx).classId.getText():null), fields, methods);	
				          	_localctx.classes.add(classNode);
				          	symTable.decreaseNesting();
				          
				}
				}
				setState(131); 
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
			    
			setState(182); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(178);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(134);
					match(VAR);
					setState(135);
					((DeclistContext)_localctx).id = match(ID);
					setState(136);
					match(COLON);
					setState(137);
					((DeclistContext)_localctx).t = hotype();
					setState(138);
					match(ASS);
					setState(139);
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
					setState(142);
					match(FUN);
					setState(143);
					((DeclistContext)_localctx).funId = match(ID);
					setState(144);
					match(COLON);
					setState(145);
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
					        
					setState(147);
					match(LPAR);

					            	//parTypes � l'insieme di parametri della funzione
					                List<Type> parTypes = new ArrayList<Type>();
					                //offset parte da 1 e cresce
					          	    int parOffset = 1;
					            
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(149);
						((DeclistContext)_localctx).firstId = match(ID);
						setState(150);
						match(COLON);
						setState(151);
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
						                
						              
						setState(161);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(153);
							match(COMMA);
							setState(154);
							((DeclistContext)_localctx).otherId = match(ID);
							setState(155);
							match(COLON);
							setState(156);
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
							setState(163);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(166);
					match(RPAR);

					                	//a questo punto posso definire il tipo della funzione e di conseguenza lo aggiunger� alla symbol table
					                	final Type funType = new ArrowType(parTypes,((DeclistContext)_localctx).retType.nodeType);
					                	final STentry funEntry = STentry.createStandard(functionNesting, funType, functionOffset);
						                if(!symTable.addEntry((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null), funEntry)){
						                	throw new AlreadyDeclaredException(Declaration.Function,(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getLine():0));
						                }
					                
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(168);
						match(LET);
						setState(169);
						((DeclistContext)_localctx).declarations = declist();
						((DeclistContext)_localctx).declarations.astlist.forEach(x -> function.addDec(x));
						setState(171);
						match(IN);
						}
					}

					setState(175);
					((DeclistContext)_localctx).bodyExp = exp();

					                  function.addBody(((DeclistContext)_localctx).bodyExp.ast);
					                  symTable.decreaseNesting();
					              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(180);
				match(SEMIC);
				}
				}
				setState(184); 
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
			setState(186);
			((ExpContext)_localctx).first = term();

			        ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).first.ast;
			    
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(200);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(188);
					match(PLUS);
					setState(189);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case MINUS:
					{
					setState(192);
					match(MINUS);
					setState(193);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case OR:
					{
					setState(196);
					match(OR);
					setState(197);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).other.ast); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(204);
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
			setState(205);
			((TermContext)_localctx).firstFactor = factor();

			        ((TermContext)_localctx).ast =  ((TermContext)_localctx).firstFactor.ast;
			    
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(219);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(207);
					match(TIMES);
					setState(208);
					((TermContext)_localctx).other = factor();
					 ((TermContext)_localctx).ast =  new MultNode (_localctx.ast, ((TermContext)_localctx).other.ast); 
					}
					break;
				case DIV:
					{
					setState(211);
					match(DIV);
					setState(212);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new DivNode (_localctx.ast, ((TermContext)_localctx).other.ast);
					}
					break;
				case AND:
					{
					setState(215);
					match(AND);
					setState(216);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast, ((TermContext)_localctx).other.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(223);
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
			setState(224);
			((FactorContext)_localctx).first = value();

			        ((FactorContext)_localctx).ast =  ((FactorContext)_localctx).first.ast;
			    
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(238);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(226);
					match(EQ);
					setState(227);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new EqualNode (_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case GE:
					{
					setState(230);
					match(GE);
					setState(231);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new GreaterEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case LE:
					{
					setState(234);
					match(LE);
					setState(235);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new LessEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(242);
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
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				((ValueContext)_localctx).n = match(INTEGER);
				 ((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(TRUE);
				 ((ValueContext)_localctx).ast =  BoolNode.trueNode();
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
				match(FALSE);
				 ((ValueContext)_localctx).ast =  BoolNode.falseNode();
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(249);
				match(NULL);
				((ValueContext)_localctx).ast =  EmptyNode.instance();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(251);
				match(NEW);
				setState(252);
				((ValueContext)_localctx).id = match(ID);

					    		final List<Node> parNodes = new ArrayList<>();
					    		//per verificare se � presente nelle classe uso la class table
					    		if (!classTable.isClassPresent((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null))){
					    			throw new NotDeclaredException(Declaration.Class,(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null), (((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0)); 			
					    		}
					    		//vado a prendere l'entry associata alla classe
					    		final STentry entry = symTable.findEntryById((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null)).get();
					    	
				setState(254);
				match(LPAR);
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(255);
					((ValueContext)_localctx).e = exp();
					parNodes.add(((ValueContext)_localctx).e.ast);
					setState(263);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(257);
						match(COMMA);
						setState(258);
						((ValueContext)_localctx).other = exp();
						parNodes.add(((ValueContext)_localctx).other.ast);
						}
						}
						setState(265);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(268);
				match(RPAR);

					    		((ValueContext)_localctx).ast =  new NewNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null), entry, parNodes);
					    	
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(270);
				match(IF);
				setState(271);
				((ValueContext)_localctx).c = exp();
				setState(272);
				match(THEN);
				setState(273);
				match(CLPAR);
				setState(274);
				((ValueContext)_localctx).th = exp();
				setState(275);
				match(CRPAR);
				setState(276);
				match(ELSE);
				setState(277);
				match(CLPAR);
				setState(278);
				((ValueContext)_localctx).e = exp();
				setState(279);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).c.ast, ((ValueContext)_localctx).th.ast, ((ValueContext)_localctx).e.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(282);
				match(NOT);
				setState(283);
				match(LPAR);
				setState(284);
				((ValueContext)_localctx).e = exp();
				setState(285);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast); 
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 8);
				{
				setState(288);
				match(PRINT);
				setState(289);
				match(LPAR);
				setState(290);
				((ValueContext)_localctx).e = exp();
				setState(291);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 9);
				{
				setState(294);
				match(LPAR);
				setState(295);
				((ValueContext)_localctx).e = exp();
				setState(296);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(299);
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
				        
				setState(336);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(301);
					match(LPAR);
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(302);
						((ValueContext)_localctx).first = exp();

						            arguments.add(((ValueContext)_localctx).first.ast);
						        
						setState(310);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(304);
							match(COMMA);
							setState(305);
							((ValueContext)_localctx).other = exp();

							            arguments.add(((ValueContext)_localctx).other.ast);
							        
							}
							}
							setState(312);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(315);
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
					setState(317);
					match(DOT);
					setState(318);
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
					             
					setState(320);
					match(LPAR);
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(321);
						((ValueContext)_localctx).e = exp();

						             		methodPar.add(((ValueContext)_localctx).e.ast);
						             	
						setState(329);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(323);
							match(COMMA);
							setState(324);
							((ValueContext)_localctx).other = exp();

							             		methodPar.add(((ValueContext)_localctx).other.ast);
							             	
							}
							}
							setState(331);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(334);
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
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).nodeType =  ((HotypeContext)_localctx).t.nodeType;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
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
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				match(INT);
				((TypeContext)_localctx).nodeType =  IntType.instance();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				match(BOOL);
				((TypeContext)_localctx).nodeType =  BoolType.instance();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(352);
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
			setState(357);
			match(LPAR);
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(358);
				((ArrowContext)_localctx).firstParameter = hotype();
				parameterList.add(((ArrowContext)_localctx).firstParameter.nodeType);
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(360);
					match(COMMA);
					setState(361);
					((ArrowContext)_localctx).otherParameter = hotype();
					parameterList.add(((ArrowContext)_localctx).otherParameter.nodeType);
					}
					}
					setState(368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(371);
			match(RPAR);
			setState(372);
			match(ARROW);
			setState(373);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u017b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\3\2\3\2\5\2#\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3D\n\3\f\3\16"+
		"\3G\13\3\5\3I\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\7\3^\n\3\f\3\16\3a\13\3\5\3c\n\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3q\n\3\r\3\16\3r\3\3\3\3\5\3w\n"+
		"\3\3\3\3\3\3\3\3\3\7\3}\n\3\f\3\16\3\u0080\13\3\3\3\3\3\6\3\u0084\n\3"+
		"\r\3\16\3\u0085\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00a2\n\4\f\4\16"+
		"\4\u00a5\13\4\5\4\u00a7\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00b0\n\4"+
		"\3\4\3\4\3\4\5\4\u00b5\n\4\3\4\3\4\6\4\u00b9\n\4\r\4\16\4\u00ba\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00cb\n\5\f\5\16"+
		"\5\u00ce\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\7\6\u00de\n\6\f\6\16\6\u00e1\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\7\7\u00f1\n\7\f\7\16\7\u00f4\13\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0108\n\b"+
		"\f\b\16\b\u010b\13\b\5\b\u010d\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0137\n\b\f"+
		"\b\16\b\u013a\13\b\5\b\u013c\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\7\b\u014a\n\b\f\b\16\b\u014d\13\b\5\b\u014f\n\b\3\b\3\b\5\b"+
		"\u0153\n\b\5\b\u0155\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u015d\n\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\n\u0165\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13\u016f\n\13\f\13\16\13\u0172\13\13\5\13\u0174\n\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2\u01a0\2+\3\2\2\2\4"+
		"/\3\2\2\2\6\u0087\3\2\2\2\b\u00bc\3\2\2\2\n\u00cf\3\2\2\2\f\u00e2\3\2"+
		"\2\2\16\u0154\3\2\2\2\20\u015c\3\2\2\2\22\u0164\3\2\2\2\24\u0166\3\2\2"+
		"\2\26\27\b\2\1\2\27\"\7\34\2\2\30\31\5\4\3\2\31\35\b\2\1\2\32\33\5\6\4"+
		"\2\33\34\b\2\1\2\34\36\3\2\2\2\35\32\3\2\2\2\35\36\3\2\2\2\36#\3\2\2\2"+
		"\37 \5\6\4\2 !\b\2\1\2!#\3\2\2\2\"\30\3\2\2\2\"\37\3\2\2\2#$\3\2\2\2$"+
		"%\7\35\2\2%&\5\b\5\2&\'\b\2\1\2\',\3\2\2\2()\5\b\5\2)*\b\2\1\2*,\3\2\2"+
		"\2+\26\3\2\2\2+(\3\2\2\2,-\3\2\2\2-.\7\13\2\2.\3\3\2\2\2/\u0083\b\3\1"+
		"\2\60\61\7 \2\2\61\62\7(\2\2\62\66\b\3\1\2\63\64\7!\2\2\64\65\7(\2\2\65"+
		"\67\b\3\1\2\66\63\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28H\7\7\2\29:\7(\2\2"+
		":;\7\f\2\2;<\5\22\n\2<E\b\3\1\2=>\7\r\2\2>?\7(\2\2?@\7\f\2\2@A\5\22\n"+
		"\2AB\b\3\1\2BD\3\2\2\2C=\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FI\3\2\2"+
		"\2GE\3\2\2\2H9\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\b\2\2KL\7\t\2\2L~\b\3\1"+
		"\2MN\7\37\2\2NO\7(\2\2OP\7\f\2\2PQ\5\22\n\2QR\b\3\1\2Rb\7\7\2\2ST\7(\2"+
		"\2TU\7\f\2\2UV\5\20\t\2V_\b\3\1\2WX\7\r\2\2XY\7(\2\2YZ\7\f\2\2Z[\5\20"+
		"\t\2[\\\b\3\1\2\\^\3\2\2\2]W\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3"+
		"\2\2\2a_\3\2\2\2bS\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\7\b\2\2ev\b\3\1\2fp\7"+
		"\34\2\2gh\7\36\2\2hi\7(\2\2ij\7\f\2\2jk\5\22\n\2kl\7\25\2\2lm\5\b\5\2"+
		"mn\7\13\2\2no\b\3\1\2oq\3\2\2\2pg\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2"+
		"\2st\3\2\2\2tu\7\35\2\2uw\3\2\2\2vf\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\5\b"+
		"\5\2yz\7\13\2\2z{\b\3\1\2{}\3\2\2\2|M\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2"+
		"~\177\3\2\2\2\177\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\n\2\2\u0082"+
		"\u0084\b\3\1\2\u0083\60\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2"+
		"\2\u0085\u0086\3\2\2\2\u0086\5\3\2\2\2\u0087\u00b8\b\4\1\2\u0088\u0089"+
		"\7\36\2\2\u0089\u008a\7(\2\2\u008a\u008b\7\f\2\2\u008b\u008c\5\20\t\2"+
		"\u008c\u008d\7\25\2\2\u008d\u008e\5\b\5\2\u008e\u008f\b\4\1\2\u008f\u00b5"+
		"\3\2\2\2\u0090\u0091\7\37\2\2\u0091\u0092\7(\2\2\u0092\u0093\7\f\2\2\u0093"+
		"\u0094\5\22\n\2\u0094\u0095\b\4\1\2\u0095\u0096\7\7\2\2\u0096\u00a6\b"+
		"\4\1\2\u0097\u0098\7(\2\2\u0098\u0099\7\f\2\2\u0099\u009a\5\20\t\2\u009a"+
		"\u00a3\b\4\1\2\u009b\u009c\7\r\2\2\u009c\u009d\7(\2\2\u009d\u009e\7\f"+
		"\2\2\u009e\u009f\5\20\t\2\u009f\u00a0\b\4\1\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009b\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\b\2\2\u00a9\u00af\b\4"+
		"\1\2\u00aa\u00ab\7\34\2\2\u00ab\u00ac\5\6\4\2\u00ac\u00ad\b\4\1\2\u00ad"+
		"\u00ae\7\35\2\2\u00ae\u00b0\3\2\2\2\u00af\u00aa\3\2\2\2\u00af\u00b0\3"+
		"\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\b\5\2\u00b2\u00b3\b\4\1\2\u00b3"+
		"\u00b5\3\2\2\2\u00b4\u0088\3\2\2\2\u00b4\u0090\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b7\7\13\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b4\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\7\3\2\2\2"+
		"\u00bc\u00bd\5\n\6\2\u00bd\u00cc\b\5\1\2\u00be\u00bf\7\3\2\2\u00bf\u00c0"+
		"\5\n\6\2\u00c0\u00c1\b\5\1\2\u00c1\u00cb\3\2\2\2\u00c2\u00c3\7\4\2\2\u00c3"+
		"\u00c4\5\n\6\2\u00c4\u00c5\b\5\1\2\u00c5\u00cb\3\2\2\2\u00c6\u00c7\7\17"+
		"\2\2\u00c7\u00c8\5\n\6\2\u00c8\u00c9\b\5\1\2\u00c9\u00cb\3\2\2\2\u00ca"+
		"\u00be\3\2\2\2\u00ca\u00c2\3\2\2\2\u00ca\u00c6\3\2\2\2\u00cb\u00ce\3\2"+
		"\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\t\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00cf\u00d0\5\f\7\2\u00d0\u00df\b\6\1\2\u00d1\u00d2\7\5\2\2\u00d2"+
		"\u00d3\5\f\7\2\u00d3\u00d4\b\6\1\2\u00d4\u00de\3\2\2\2\u00d5\u00d6\7\6"+
		"\2\2\u00d6\u00d7\5\f\7\2\u00d7\u00d8\b\6\1\2\u00d8\u00de\3\2\2\2\u00d9"+
		"\u00da\7\20\2\2\u00da\u00db\5\f\7\2\u00db\u00dc\b\6\1\2\u00dc\u00de\3"+
		"\2\2\2\u00dd\u00d1\3\2\2\2\u00dd\u00d5\3\2\2\2\u00dd\u00d9\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\13\3\2\2"+
		"\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\5\16\b\2\u00e3\u00f2\b\7\1\2\u00e4"+
		"\u00e5\7\24\2\2\u00e5\u00e6\5\16\b\2\u00e6\u00e7\b\7\1\2\u00e7\u00f1\3"+
		"\2\2\2\u00e8\u00e9\7\22\2\2\u00e9\u00ea\5\16\b\2\u00ea\u00eb\b\7\1\2\u00eb"+
		"\u00f1\3\2\2\2\u00ec\u00ed\7\23\2\2\u00ed\u00ee\5\16\b\2\u00ee\u00ef\b"+
		"\7\1\2\u00ef\u00f1\3\2\2\2\u00f0\u00e4\3\2\2\2\u00f0\u00e8\3\2\2\2\u00f0"+
		"\u00ec\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2"+
		"\2\2\u00f3\r\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\7\'\2\2\u00f6\u0155"+
		"\b\b\1\2\u00f7\u00f8\7\26\2\2\u00f8\u0155\b\b\1\2\u00f9\u00fa\7\27\2\2"+
		"\u00fa\u0155\b\b\1\2\u00fb\u00fc\7#\2\2\u00fc\u0155\b\b\1\2\u00fd\u00fe"+
		"\7\"\2\2\u00fe\u00ff\7(\2\2\u00ff\u0100\b\b\1\2\u0100\u010c\7\7\2\2\u0101"+
		"\u0102\5\b\5\2\u0102\u0109\b\b\1\2\u0103\u0104\7\r\2\2\u0104\u0105\5\b"+
		"\5\2\u0105\u0106\b\b\1\2\u0106\u0108\3\2\2\2\u0107\u0103\3\2\2\2\u0108"+
		"\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010d\3\2"+
		"\2\2\u010b\u0109\3\2\2\2\u010c\u0101\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010f\7\b\2\2\u010f\u0155\b\b\1\2\u0110\u0111\7\30"+
		"\2\2\u0111\u0112\5\b\5\2\u0112\u0113\7\31\2\2\u0113\u0114\7\t\2\2\u0114"+
		"\u0115\5\b\5\2\u0115\u0116\7\n\2\2\u0116\u0117\7\32\2\2\u0117\u0118\7"+
		"\t\2\2\u0118\u0119\5\b\5\2\u0119\u011a\7\n\2\2\u011a\u011b\b\b\1\2\u011b"+
		"\u0155\3\2\2\2\u011c\u011d\7\21\2\2\u011d\u011e\7\7\2\2\u011e\u011f\5"+
		"\b\5\2\u011f\u0120\7\b\2\2\u0120\u0121\b\b\1\2\u0121\u0155\3\2\2\2\u0122"+
		"\u0123\7\33\2\2\u0123\u0124\7\7\2\2\u0124\u0125\5\b\5\2\u0125\u0126\7"+
		"\b\2\2\u0126\u0127\b\b\1\2\u0127\u0155\3\2\2\2\u0128\u0129\7\7\2\2\u0129"+
		"\u012a\5\b\5\2\u012a\u012b\7\b\2\2\u012b\u012c\b\b\1\2\u012c\u0155\3\2"+
		"\2\2\u012d\u012e\7(\2\2\u012e\u0152\b\b\1\2\u012f\u013b\7\7\2\2\u0130"+
		"\u0131\5\b\5\2\u0131\u0138\b\b\1\2\u0132\u0133\7\r\2\2\u0133\u0134\5\b"+
		"\5\2\u0134\u0135\b\b\1\2\u0135\u0137\3\2\2\2\u0136\u0132\3\2\2\2\u0137"+
		"\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013c\3\2"+
		"\2\2\u013a\u0138\3\2\2\2\u013b\u0130\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013d\3\2\2\2\u013d\u013e\7\b\2\2\u013e\u0153\b\b\1\2\u013f\u0140\7\16"+
		"\2\2\u0140\u0141\7(\2\2\u0141\u0142\b\b\1\2\u0142\u014e\7\7\2\2\u0143"+
		"\u0144\5\b\5\2\u0144\u014b\b\b\1\2\u0145\u0146\7\r\2\2\u0146\u0147\5\b"+
		"\5\2\u0147\u0148\b\b\1\2\u0148\u014a\3\2\2\2\u0149\u0145\3\2\2\2\u014a"+
		"\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014f\3\2"+
		"\2\2\u014d\u014b\3\2\2\2\u014e\u0143\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0151\7\b\2\2\u0151\u0153\b\b\1\2\u0152\u012f\3\2"+
		"\2\2\u0152\u013f\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0155\3\2\2\2\u0154"+
		"\u00f5\3\2\2\2\u0154\u00f7\3\2\2\2\u0154\u00f9\3\2\2\2\u0154\u00fb\3\2"+
		"\2\2\u0154\u00fd\3\2\2\2\u0154\u0110\3\2\2\2\u0154\u011c\3\2\2\2\u0154"+
		"\u0122\3\2\2\2\u0154\u0128\3\2\2\2\u0154\u012d\3\2\2\2\u0155\17\3\2\2"+
		"\2\u0156\u0157\5\22\n\2\u0157\u0158\b\t\1\2\u0158\u015d\3\2\2\2\u0159"+
		"\u015a\5\24\13\2\u015a\u015b\b\t\1\2\u015b\u015d\3\2\2\2\u015c\u0156\3"+
		"\2\2\2\u015c\u0159\3\2\2\2\u015d\21\3\2\2\2\u015e\u015f\7$\2\2\u015f\u0165"+
		"\b\n\1\2\u0160\u0161\7%\2\2\u0161\u0165\b\n\1\2\u0162\u0163\7(\2\2\u0163"+
		"\u0165\b\n\1\2\u0164\u015e\3\2\2\2\u0164\u0160\3\2\2\2\u0164\u0162\3\2"+
		"\2\2\u0165\23\3\2\2\2\u0166\u0167\b\13\1\2\u0167\u0173\7\7\2\2\u0168\u0169"+
		"\5\20\t\2\u0169\u0170\b\13\1\2\u016a\u016b\7\r\2\2\u016b\u016c\5\20\t"+
		"\2\u016c\u016d\b\13\1\2\u016d\u016f\3\2\2\2\u016e\u016a\3\2\2\2\u016f"+
		"\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0174\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0173\u0168\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0175\3\2\2\2\u0175\u0176\7\b\2\2\u0176\u0177\7&\2\2\u0177\u0178\5\22"+
		"\n\2\u0178\u0179\b\13\1\2\u0179\25\3\2\2\2%\35\"+\66EH_brv~\u0085\u00a3"+
		"\u00a6\u00af\u00b4\u00ba\u00ca\u00cc\u00dd\u00df\u00f0\u00f2\u0109\u010c"+
		"\u0138\u013b\u014b\u014e\u0152\u0154\u015c\u0164\u0170\u0173";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}