// Generated from FOOL.g4 by ANTLR 4.7

import java.util.*;
import ast.*;
import ast.core.*;
import ast.type.*;
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
		private SymbolTable symTable = new StandardSymbolTable();
		//livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides)
		//il "fronte" della lista di tabelle � symTable.get(nestingLevel)

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Node ast;
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
			setState(35);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				{
				setState(20);
				match(LET);
				setState(26);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(21);
					cllist();
					setState(23);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(22);
						declist();
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(25);
					((ProgContext)_localctx).d = declist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(28);
				match(IN);
				setState(29);
				((ProgContext)_localctx).letExp = exp();

				        	
				        	((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).d.astlist, ((ProgContext)_localctx).letExp.ast);
				        
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
				setState(32);
				((ProgContext)_localctx).e = exp();
				 
				            ((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast); 
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(37);
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
		public List<Node> classes;
		public List<TerminalNode> CLASS() { return getTokens(FOOLParser.CLASS); }
		public TerminalNode CLASS(int i) {
			return getToken(FOOLParser.CLASS, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
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
		public List<TerminalNode> EXTENDS() { return getTokens(FOOLParser.EXTENDS); }
		public TerminalNode EXTENDS(int i) {
			return getToken(FOOLParser.EXTENDS, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
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
			setState(108); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(39);
				match(CLASS);
				setState(40);
				match(ID);
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(41);
					match(EXTENDS);
					setState(42);
					match(ID);
					}
				}

				setState(45);
				match(LPAR);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(46);
					match(ID);
					setState(47);
					match(COLON);
					setState(48);
					type();
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(49);
						match(COMMA);
						setState(50);
						match(ID);
						setState(51);
						match(COLON);
						setState(52);
						type();
						}
						}
						setState(57);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(60);
				match(RPAR);
				setState(61);
				match(CLPAR);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(62);
					match(FUN);
					setState(63);
					match(ID);
					setState(64);
					match(COLON);
					setState(65);
					type();
					setState(66);
					match(LPAR);
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(67);
						match(ID);
						setState(68);
						match(COLON);
						setState(69);
						hotype();
						setState(76);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(70);
							match(COMMA);
							setState(71);
							match(ID);
							setState(72);
							match(COLON);
							setState(73);
							hotype();
							}
							}
							setState(78);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(81);
					match(RPAR);
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(82);
						match(LET);
						setState(91); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(83);
							match(VAR);
							setState(84);
							match(ID);
							setState(85);
							match(COLON);
							setState(86);
							type();
							setState(87);
							match(ASS);
							setState(88);
							exp();
							setState(89);
							match(SEMIC);
							}
							}
							setState(93); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==VAR );
						setState(95);
						match(IN);
						}
					}

					setState(99);
					exp();
					setState(100);
					match(SEMIC);
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				match(CRPAR);
				}
				}
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			 ((CllistContext)_localctx).classes =  null;
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
			        int offset = -1;
			    
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(159);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(115);
					match(VAR);
					setState(116);
					((DeclistContext)_localctx).id = match(ID);
					setState(117);
					match(COLON);
					setState(118);
					((DeclistContext)_localctx).t = hotype();
					setState(119);
					match(ASS);
					setState(120);
					((DeclistContext)_localctx).e = exp();

					    		//creo il nodo associato alla variabile che sto dichiarando
					            final VarNode var = new VarNode((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),((DeclistContext)_localctx).t.nodeType, ((DeclistContext)_localctx).e.ast);
					            //aggiungo alla declarations list la variabile
					            _localctx.astlist.add(var);
					            //aggiungo la dichiarazione della variabile nella symbol table
					            if (!symTable.addEntry((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),((DeclistContext)_localctx).t.nodeType, --offset))
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
					setState(123);
					match(FUN);
					setState(124);
					((DeclistContext)_localctx).funId = match(ID);
					setState(125);
					match(COLON);
					setState(126);
					((DeclistContext)_localctx).retType = type();

					        	//creo il function node associato
					            final FunNode function = new FunNode((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),((DeclistContext)_localctx).retType.nodeType);     
					            //aggiungo alla declarations list la funzione appena dichiarata 
					            _localctx.astlist.add(function);
					            //valori che mi serivaranno per aggiungere l'entry nella symbol table
					            final int functionNesting = symTable.getNesting();
					            final int functionOffset = --offset;
					            offset--; //decremento ulteriormente l'offset perch� le funzioni occupano offset doppio
					            //sto entrando dentro la funzione quindi devo aumentare il nesting level della symbol table
					            symTable.increaseNesting();
					        
					setState(128);
					match(LPAR);

					            	//parTypes � l'insieme di parametri della funzione
					                List<Type> parTypes = new ArrayList<Type>();
					                //offset parte da 1 e cresce
					          	    int parOffset = 1;
					            
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(130);
						((DeclistContext)_localctx).firstId = match(ID);
						setState(131);
						match(COLON);
						setState(132);
						((DeclistContext)_localctx).firstType = hotype();

						            	//aggiungo un parametro alla dichiarazione di funzioni
						                parTypes.add(((DeclistContext)_localctx).firstType.nodeType);
						                ParNode fpar = new ParNode((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),((DeclistContext)_localctx).firstType.nodeType); //creo nodo ParNode
						                function.addPar(fpar);                                 //lo attacco al FunNode con addPar
						                //se il parametro era di tipo funzionale occupa doppio offset
						                parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
						                if (!symTable.addEntry((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),((DeclistContext)_localctx).firstType.nodeType, parOffset ++)){
						                    throw new AlreadyDeclaredException(Declaration.Parameter,(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getLine():0));
						                }
						                
						              
						setState(142);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(134);
							match(COMMA);
							setState(135);
							((DeclistContext)_localctx).otherId = match(ID);
							setState(136);
							match(COLON);
							setState(137);
							((DeclistContext)_localctx).otherType = hotype();

							                   parTypes.add(((DeclistContext)_localctx).otherType.nodeType);
							                   ParNode par = new ParNode((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),((DeclistContext)_localctx).otherType.nodeType); 
							                   function.addPar(par);
							                    if (!symTable.addEntry((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),((DeclistContext)_localctx).otherType.nodeType, parOffset ++)){
							                    	throw new AlreadyDeclaredException(Declaration.Parameter,(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getLine():0));
							                	}
							                	//se il parametro era di tipo funzionale occupa doppio offset
							                	parOffset = fpar.getSymbolType() instanceof ArrowType ? parOffset + 1 : parOffset;
							                
							}
							}
							setState(144);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(147);
					match(RPAR);

					                	//a questo punto posso definire il tipo della funzione e di conseguenza lo aggiunger� alla symbol table
					                	final Type funType = new ArrowType(parTypes,((DeclistContext)_localctx).retType.nodeType);
						                if(!symTable.addEntryIn((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),funType,functionOffset,functionNesting)){
						                	throw new AlreadyDeclaredException(Declaration.Function,(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getLine():0));
						                }
					                
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(149);
						match(LET);
						setState(150);
						((DeclistContext)_localctx).declarations = declist();
						function.addDec(((DeclistContext)_localctx).declarations.astlist);
						setState(152);
						match(IN);
						}
					}

					setState(156);
					((DeclistContext)_localctx).bodyExp = exp();

					                  function.addBody(((DeclistContext)_localctx).bodyExp.ast);
					                  symTable.decreaseNesting();
					              
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(161);
				match(SEMIC);
				}
				}
				setState(165); 
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
			setState(167);
			((ExpContext)_localctx).first = term();

			        ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).first.ast;
			    
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(181);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(169);
					match(PLUS);
					setState(170);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case MINUS:
					{
					setState(173);
					match(MINUS);
					setState(174);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case OR:
					{
					setState(177);
					match(OR);
					setState(178);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).other.ast); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(185);
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
			setState(186);
			((TermContext)_localctx).firstFactor = factor();

			        ((TermContext)_localctx).ast =  ((TermContext)_localctx).firstFactor.ast;
			    
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(200);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(188);
					match(TIMES);
					setState(189);
					((TermContext)_localctx).other = factor();
					 ((TermContext)_localctx).ast =  new MultNode (_localctx.ast, ((TermContext)_localctx).other.ast); 
					}
					break;
				case DIV:
					{
					setState(192);
					match(DIV);
					setState(193);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new DivNode (_localctx.ast, ((TermContext)_localctx).other.ast);
					}
					break;
				case AND:
					{
					setState(196);
					match(AND);
					setState(197);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast, ((TermContext)_localctx).other.ast);
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
			setState(205);
			((FactorContext)_localctx).first = value();

			        ((FactorContext)_localctx).ast =  ((FactorContext)_localctx).first.ast;
			    
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(219);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(207);
					match(EQ);
					setState(208);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new EqualNode (_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case GE:
					{
					setState(211);
					match(GE);
					setState(212);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new GreaterEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case LE:
					{
					setState(215);
					match(LE);
					setState(216);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new LessEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
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

	public static class ValueContext extends ParserRuleContext {
		public Node ast;
		public Token n;
		public ExpContext c;
		public ExpContext th;
		public ExpContext e;
		public Token id;
		public ExpContext first;
		public ExpContext other;
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
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
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				((ValueContext)_localctx).n = match(INTEGER);
				 ((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				match(TRUE);
				 ((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
				match(FALSE);
				 ((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				match(NULL);
				((ValueContext)_localctx).ast =  null;
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(232);
				match(NEW);
				setState(233);
				match(ID);
				setState(234);
				match(LPAR);
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(235);
					exp();
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(236);
						match(COMMA);
						setState(237);
						exp();
						}
						}
						setState(242);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(245);
				match(RPAR);
				((ValueContext)_localctx).ast =  null; 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(247);
				match(IF);
				setState(248);
				((ValueContext)_localctx).c = exp();
				setState(249);
				match(THEN);
				setState(250);
				match(CLPAR);
				setState(251);
				((ValueContext)_localctx).th = exp();
				setState(252);
				match(CRPAR);
				setState(253);
				match(ELSE);
				setState(254);
				match(CLPAR);
				setState(255);
				((ValueContext)_localctx).e = exp();
				setState(256);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).c.ast, ((ValueContext)_localctx).th.ast, ((ValueContext)_localctx).e.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(259);
				match(NOT);
				setState(260);
				match(LPAR);
				setState(261);
				((ValueContext)_localctx).e = exp();
				setState(262);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast); 
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 8);
				{
				setState(265);
				match(PRINT);
				setState(266);
				match(LPAR);
				setState(267);
				((ValueContext)_localctx).e = exp();
				setState(268);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 9);
				{
				setState(271);
				match(LPAR);
				setState(272);
				((ValueContext)_localctx).e = exp();
				setState(273);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(276);
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
				        
				setState(308);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(278);
					match(LPAR);
					setState(290);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(279);
						((ValueContext)_localctx).first = exp();

						            arguments.add(((ValueContext)_localctx).first.ast);
						        
						setState(287);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(281);
							match(COMMA);
							setState(282);
							((ValueContext)_localctx).other = exp();

							            arguments.add(((ValueContext)_localctx).other.ast);
							        
							}
							}
							setState(289);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(292);
					match(RPAR);

					        	((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry.get(),arguments,symTable.getNesting());
					        
					}
					break;
				case DOT:
					{
					setState(294);
					match(DOT);
					setState(295);
					match(ID);
					setState(296);
					match(LPAR);
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(297);
						exp();
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(298);
							match(COMMA);
							setState(299);
							exp();
							}
							}
							setState(304);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(307);
					match(RPAR);
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
			setState(318);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).nodeType =  ((HotypeContext)_localctx).t.nodeType;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
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
			setState(325);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				match(INT);
				((TypeContext)_localctx).nodeType =  IntType.instance();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				match(BOOL);
				((TypeContext)_localctx).nodeType =  BoolType.instance();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(324);
				match(ID);
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
			setState(328);
			match(LPAR);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(329);
				((ArrowContext)_localctx).firstParameter = hotype();
				parameterList.add(((ArrowContext)_localctx).firstParameter.nodeType);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(331);
					match(COMMA);
					setState(332);
					((ArrowContext)_localctx).otherParameter = hotype();
					parameterList.add(((ArrowContext)_localctx).otherParameter.nodeType);
					}
					}
					setState(339);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(342);
			match(RPAR);
			setState(343);
			match(ARROW);
			setState(344);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u015e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\5\2\32\n\2\3\2\5\2\35\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\5\2&\n\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\7\38\n\3\f\3\16\3;\13\3\5\3=\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\7\3M\n\3\f\3\16\3P\13\3\5\3R\n\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3^\n\3\r\3\16\3_\3\3\3\3\5\3d\n\3\3\3\3"+
		"\3\3\3\7\3i\n\3\f\3\16\3l\13\3\3\3\6\3o\n\3\r\3\16\3p\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13\4\5\4\u0094"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009d\n\4\3\4\3\4\3\4\5\4\u00a2\n"+
		"\4\3\4\3\4\6\4\u00a6\n\4\r\4\16\4\u00a7\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b8\n\5\f\5\16\5\u00bb\13\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00cb\n\6\f\6\16\6"+
		"\u00ce\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7\u00de\n\7\f\7\16\7\u00e1\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u00f1\n\b\f\b\16\b\u00f4\13\b\5\b\u00f6\n\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\b\u0120\n\b\f\b\16\b\u0123\13\b\5\b\u0125\n\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u012f\n\b\f\b\16\b\u0132\13\b\5\b\u0134"+
		"\n\b\3\b\5\b\u0137\n\b\5\b\u0139\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0141"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\5\n\u0148\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\7\13\u0152\n\13\f\13\16\13\u0155\13\13\5\13\u0157\n\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2\u0183\2%\3"+
		"\2\2\2\4n\3\2\2\2\6t\3\2\2\2\b\u00a9\3\2\2\2\n\u00bc\3\2\2\2\f\u00cf\3"+
		"\2\2\2\16\u0138\3\2\2\2\20\u0140\3\2\2\2\22\u0147\3\2\2\2\24\u0149\3\2"+
		"\2\2\26\34\7\34\2\2\27\31\5\4\3\2\30\32\5\6\4\2\31\30\3\2\2\2\31\32\3"+
		"\2\2\2\32\35\3\2\2\2\33\35\5\6\4\2\34\27\3\2\2\2\34\33\3\2\2\2\35\36\3"+
		"\2\2\2\36\37\7\35\2\2\37 \5\b\5\2 !\b\2\1\2!&\3\2\2\2\"#\5\b\5\2#$\b\2"+
		"\1\2$&\3\2\2\2%\26\3\2\2\2%\"\3\2\2\2&\'\3\2\2\2\'(\7\13\2\2(\3\3\2\2"+
		"\2)*\7 \2\2*-\7(\2\2+,\7!\2\2,.\7(\2\2-+\3\2\2\2-.\3\2\2\2./\3\2\2\2/"+
		"<\7\7\2\2\60\61\7(\2\2\61\62\7\f\2\2\629\5\22\n\2\63\64\7\r\2\2\64\65"+
		"\7(\2\2\65\66\7\f\2\2\668\5\22\n\2\67\63\3\2\2\28;\3\2\2\29\67\3\2\2\2"+
		"9:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<\60\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\b\2"+
		"\2?j\7\t\2\2@A\7\37\2\2AB\7(\2\2BC\7\f\2\2CD\5\22\n\2DQ\7\7\2\2EF\7(\2"+
		"\2FG\7\f\2\2GN\5\20\t\2HI\7\r\2\2IJ\7(\2\2JK\7\f\2\2KM\5\20\t\2LH\3\2"+
		"\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OR\3\2\2\2PN\3\2\2\2QE\3\2\2\2QR\3\2"+
		"\2\2RS\3\2\2\2Sc\7\b\2\2T]\7\34\2\2UV\7\36\2\2VW\7(\2\2WX\7\f\2\2XY\5"+
		"\22\n\2YZ\7\25\2\2Z[\5\b\5\2[\\\7\13\2\2\\^\3\2\2\2]U\3\2\2\2^_\3\2\2"+
		"\2_]\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7\35\2\2bd\3\2\2\2cT\3\2\2\2cd\3\2"+
		"\2\2de\3\2\2\2ef\5\b\5\2fg\7\13\2\2gi\3\2\2\2h@\3\2\2\2il\3\2\2\2jh\3"+
		"\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mo\7\n\2\2n)\3\2\2\2op\3\2\2\2pn\3"+
		"\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\3\1\2s\5\3\2\2\2t\u00a5\b\4\1\2uv\7\36"+
		"\2\2vw\7(\2\2wx\7\f\2\2xy\5\20\t\2yz\7\25\2\2z{\5\b\5\2{|\b\4\1\2|\u00a2"+
		"\3\2\2\2}~\7\37\2\2~\177\7(\2\2\177\u0080\7\f\2\2\u0080\u0081\5\22\n\2"+
		"\u0081\u0082\b\4\1\2\u0082\u0083\7\7\2\2\u0083\u0093\b\4\1\2\u0084\u0085"+
		"\7(\2\2\u0085\u0086\7\f\2\2\u0086\u0087\5\20\t\2\u0087\u0090\b\4\1\2\u0088"+
		"\u0089\7\r\2\2\u0089\u008a\7(\2\2\u008a\u008b\7\f\2\2\u008b\u008c\5\20"+
		"\t\2\u008c\u008d\b\4\1\2\u008d\u008f\3\2\2\2\u008e\u0088\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0094\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0093\u0084\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\7\b\2\2\u0096\u009c\b\4\1\2\u0097\u0098\7\34"+
		"\2\2\u0098\u0099\5\6\4\2\u0099\u009a\b\4\1\2\u009a\u009b\7\35\2\2\u009b"+
		"\u009d\3\2\2\2\u009c\u0097\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\u009f\5\b\5\2\u009f\u00a0\b\4\1\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"u\3\2\2\2\u00a1}\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\13\2\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\7\3\2\2\2\u00a9\u00aa\5\n\6\2\u00aa\u00b9"+
		"\b\5\1\2\u00ab\u00ac\7\3\2\2\u00ac\u00ad\5\n\6\2\u00ad\u00ae\b\5\1\2\u00ae"+
		"\u00b8\3\2\2\2\u00af\u00b0\7\4\2\2\u00b0\u00b1\5\n\6\2\u00b1\u00b2\b\5"+
		"\1\2\u00b2\u00b8\3\2\2\2\u00b3\u00b4\7\17\2\2\u00b4\u00b5\5\n\6\2\u00b5"+
		"\u00b6\b\5\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00ab\3\2\2\2\u00b7\u00af\3\2"+
		"\2\2\u00b7\u00b3\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\t\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\5\f\7\2"+
		"\u00bd\u00cc\b\6\1\2\u00be\u00bf\7\5\2\2\u00bf\u00c0\5\f\7\2\u00c0\u00c1"+
		"\b\6\1\2\u00c1\u00cb\3\2\2\2\u00c2\u00c3\7\6\2\2\u00c3\u00c4\5\f\7\2\u00c4"+
		"\u00c5\b\6\1\2\u00c5\u00cb\3\2\2\2\u00c6\u00c7\7\20\2\2\u00c7\u00c8\5"+
		"\f\7\2\u00c8\u00c9\b\6\1\2\u00c9\u00cb\3\2\2\2\u00ca\u00be\3\2\2\2\u00ca"+
		"\u00c2\3\2\2\2\u00ca\u00c6\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2"+
		"\2\2\u00cc\u00cd\3\2\2\2\u00cd\13\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0"+
		"\5\16\b\2\u00d0\u00df\b\7\1\2\u00d1\u00d2\7\24\2\2\u00d2\u00d3\5\16\b"+
		"\2\u00d3\u00d4\b\7\1\2\u00d4\u00de\3\2\2\2\u00d5\u00d6\7\22\2\2\u00d6"+
		"\u00d7\5\16\b\2\u00d7\u00d8\b\7\1\2\u00d8\u00de\3\2\2\2\u00d9\u00da\7"+
		"\23\2\2\u00da\u00db\5\16\b\2\u00db\u00dc\b\7\1\2\u00dc\u00de\3\2\2\2\u00dd"+
		"\u00d1\3\2\2\2\u00dd\u00d5\3\2\2\2\u00dd\u00d9\3\2\2\2\u00de\u00e1\3\2"+
		"\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\r\3\2\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e2\u00e3\7\'\2\2\u00e3\u0139\b\b\1\2\u00e4\u00e5\7\26\2\2"+
		"\u00e5\u0139\b\b\1\2\u00e6\u00e7\7\27\2\2\u00e7\u0139\b\b\1\2\u00e8\u00e9"+
		"\7#\2\2\u00e9\u0139\b\b\1\2\u00ea\u00eb\7\"\2\2\u00eb\u00ec\7(\2\2\u00ec"+
		"\u00f5\7\7\2\2\u00ed\u00f2\5\b\5\2\u00ee\u00ef\7\r\2\2\u00ef\u00f1\5\b"+
		"\5\2\u00f0\u00ee\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00ed\3\2"+
		"\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\7\b\2\2\u00f8"+
		"\u0139\b\b\1\2\u00f9\u00fa\7\30\2\2\u00fa\u00fb\5\b\5\2\u00fb\u00fc\7"+
		"\31\2\2\u00fc\u00fd\7\t\2\2\u00fd\u00fe\5\b\5\2\u00fe\u00ff\7\n\2\2\u00ff"+
		"\u0100\7\32\2\2\u0100\u0101\7\t\2\2\u0101\u0102\5\b\5\2\u0102\u0103\7"+
		"\n\2\2\u0103\u0104\b\b\1\2\u0104\u0139\3\2\2\2\u0105\u0106\7\21\2\2\u0106"+
		"\u0107\7\7\2\2\u0107\u0108\5\b\5\2\u0108\u0109\7\b\2\2\u0109\u010a\b\b"+
		"\1\2\u010a\u0139\3\2\2\2\u010b\u010c\7\33\2\2\u010c\u010d\7\7\2\2\u010d"+
		"\u010e\5\b\5\2\u010e\u010f\7\b\2\2\u010f\u0110\b\b\1\2\u0110\u0139\3\2"+
		"\2\2\u0111\u0112\7\7\2\2\u0112\u0113\5\b\5\2\u0113\u0114\7\b\2\2\u0114"+
		"\u0115\b\b\1\2\u0115\u0139\3\2\2\2\u0116\u0117\7(\2\2\u0117\u0136\b\b"+
		"\1\2\u0118\u0124\7\7\2\2\u0119\u011a\5\b\5\2\u011a\u0121\b\b\1\2\u011b"+
		"\u011c\7\r\2\2\u011c\u011d\5\b\5\2\u011d\u011e\b\b\1\2\u011e\u0120\3\2"+
		"\2\2\u011f\u011b\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0119\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\7\b\2\2\u0127"+
		"\u0137\b\b\1\2\u0128\u0129\7\16\2\2\u0129\u012a\7(\2\2\u012a\u0133\7\7"+
		"\2\2\u012b\u0130\5\b\5\2\u012c\u012d\7\r\2\2\u012d\u012f\5\b\5\2\u012e"+
		"\u012c\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2"+
		"\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u012b\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0137\7\b\2\2\u0136\u0118\3\2"+
		"\2\2\u0136\u0128\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138"+
		"\u00e2\3\2\2\2\u0138\u00e4\3\2\2\2\u0138\u00e6\3\2\2\2\u0138\u00e8\3\2"+
		"\2\2\u0138\u00ea\3\2\2\2\u0138\u00f9\3\2\2\2\u0138\u0105\3\2\2\2\u0138"+
		"\u010b\3\2\2\2\u0138\u0111\3\2\2\2\u0138\u0116\3\2\2\2\u0139\17\3\2\2"+
		"\2\u013a\u013b\5\22\n\2\u013b\u013c\b\t\1\2\u013c\u0141\3\2\2\2\u013d"+
		"\u013e\5\24\13\2\u013e\u013f\b\t\1\2\u013f\u0141\3\2\2\2\u0140\u013a\3"+
		"\2\2\2\u0140\u013d\3\2\2\2\u0141\21\3\2\2\2\u0142\u0143\7$\2\2\u0143\u0148"+
		"\b\n\1\2\u0144\u0145\7%\2\2\u0145\u0148\b\n\1\2\u0146\u0148\7(\2\2\u0147"+
		"\u0142\3\2\2\2\u0147\u0144\3\2\2\2\u0147\u0146\3\2\2\2\u0148\23\3\2\2"+
		"\2\u0149\u014a\b\13\1\2\u014a\u0156\7\7\2\2\u014b\u014c\5\20\t\2\u014c"+
		"\u0153\b\13\1\2\u014d\u014e\7\r\2\2\u014e\u014f\5\20\t\2\u014f\u0150\b"+
		"\13\1\2\u0150\u0152\3\2\2\2\u0151\u014d\3\2\2\2\u0152\u0155\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2"+
		"\2\2\u0156\u014b\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\3\2\2\2\u0158"+
		"\u0159\7\b\2\2\u0159\u015a\7&\2\2\u015a\u015b\5\22\n\2\u015b\u015c\b\13"+
		"\1\2\u015c\25\3\2\2\2%\31\34%-9<NQ_cjp\u0090\u0093\u009c\u00a1\u00a7\u00b7"+
		"\u00b9\u00ca\u00cc\u00dd\u00df\u00f2\u00f5\u0121\u0124\u0130\u0133\u0136"+
		"\u0138\u0140\u0147\u0153\u0156";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}