// Generated from FOOL.g4 by ANTLR 4.7

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import ast.*;
import ast.core.*;
import ast.type.*;

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
		private int nestingLevel = 0;
		private List<Map<String,STentry>> symTable = new ArrayList<Map<String,STentry>>();
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

			    	//all'inizio si inizializza la symbol table con una entry vuota, definisce il livello globale
			        final Map<String,STentry> hm = new HashMap<String,STentry> ();
			       	symTable.add(hm);    
			    
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				{
				setState(21);
				match(LET);
				setState(27);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(22);
					cllist();
					setState(24);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(23);
						declist();
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(26);
					((ProgContext)_localctx).d = declist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(29);
				match(IN);
				setState(30);
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
				setState(33);
				((ProgContext)_localctx).e = exp();
				 
				            ((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast); 
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(38);
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
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				match(CLASS);
				setState(41);
				match(ID);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(42);
					match(EXTENDS);
					setState(43);
					match(ID);
					}
				}

				setState(46);
				match(LPAR);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(47);
					match(ID);
					setState(48);
					match(COLON);
					setState(49);
					type();
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(50);
						match(COMMA);
						setState(51);
						match(ID);
						setState(52);
						match(COLON);
						setState(53);
						type();
						}
						}
						setState(58);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(61);
				match(RPAR);
				setState(62);
				match(CLPAR);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(63);
					match(FUN);
					setState(64);
					match(ID);
					setState(65);
					match(COLON);
					setState(66);
					type();
					setState(67);
					match(LPAR);
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(68);
						match(ID);
						setState(69);
						match(COLON);
						setState(70);
						hotype();
						setState(77);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(71);
							match(COMMA);
							setState(72);
							match(ID);
							setState(73);
							match(COLON);
							setState(74);
							hotype();
							}
							}
							setState(79);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(82);
					match(RPAR);
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(83);
						match(LET);
						setState(92); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(84);
							match(VAR);
							setState(85);
							match(ID);
							setState(86);
							match(COLON);
							setState(87);
							type();
							setState(88);
							match(ASS);
							setState(89);
							exp();
							setState(90);
							match(SEMIC);
							}
							}
							setState(94); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==VAR );
						setState(96);
						match(IN);
						}
					}

					setState(100);
					exp();
					setState(101);
					match(SEMIC);
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(108);
				match(CRPAR);
				}
				}
				setState(111); 
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
		public List<Node> astlist;
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
			    
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(116);
					match(VAR);
					setState(117);
					((DeclistContext)_localctx).id = match(ID);
					setState(118);
					match(COLON);
					setState(119);
					((DeclistContext)_localctx).t = hotype();
					setState(120);
					match(ASS);
					setState(121);
					((DeclistContext)_localctx).e = exp();

					                final VarNode var = new VarNode((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),((DeclistContext)_localctx).t.nodeType, ((DeclistContext)_localctx).e.ast);
					                _localctx.astlist.add(var);
					                //cerca di fare meglio qua!
					                final Map<String,STentry> hm = symTable.get(nestingLevel);
					                if (hm.put((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).t.nodeType, --offset)) != null )
					                {
					                    //TODO METTI ECCEZIONI 
					                    System.out.println("Var id "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null)+" at line "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getLine():0)+" already declared");
					                    System.exit(0);
					                }
					            
					}
					break;
				case FUN:
					{
					setState(124);
					match(FUN);
					setState(125);
					((DeclistContext)_localctx).funId = match(ID);
					setState(126);
					match(COLON);
					setState(127);
					((DeclistContext)_localctx).retType = type();

					                final FunNode function = new FunNode((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),((DeclistContext)_localctx).retType.nodeType);      
					                _localctx.astlist.add(function);                              
					                Map<String,STentry> hm = symTable.get(nestingLevel);
					                final STentry entry = new STentry(nestingLevel,null,--offset);
					                if (hm.put((((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null),entry) != null  )
					                {
					                    //TODO METTI ECCEZIONI 
					                    System.out.println("Fun id "+(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getText():null)+" at line "+(((DeclistContext)_localctx).funId!=null?((DeclistContext)_localctx).funId.getLine():0)+" already declared");
					                    System.exit(0);
					                }
					                //creare una nuova hashmap per la symTable
					                //RICORDA DI AGGIUNGERE IL NESTING LEVEL
					                nestingLevel++;
					                Map<String,STentry> hmn = new HashMap<String,STentry> ();
					                symTable.add(hmn);
					            
					setState(129);
					match(LPAR);

					                    List<Type> parTypes = new ArrayList<Type>();
					              	    int parOffset = 1;
					                
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(131);
						((DeclistContext)_localctx).firstId = match(ID);
						setState(132);
						match(COLON);
						setState(133);
						((DeclistContext)_localctx).firstType = hotype();

						                    parTypes.add(((DeclistContext)_localctx).firstType.nodeType);
						                    ParNode fpar = new ParNode((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),((DeclistContext)_localctx).firstType.nodeType); //creo nodo ParNode
						                    function.addPar(fpar);                                 //lo attacco al FunNode con addPar
						                    if ( hmn.put((((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).firstType.nodeType, parOffset ++)) != null  ){
						                        //TODO lancia eccezioni
						                        System.out.println("Parameter id "+(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getText():null)+" at line "+(((DeclistContext)_localctx).firstId!=null?((DeclistContext)_localctx).firstId.getLine():0)+" already declared");
						                        System.exit(0);
						                    }
						                  
						setState(143);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(135);
							match(COMMA);
							setState(136);
							((DeclistContext)_localctx).otherId = match(ID);
							setState(137);
							match(COLON);
							setState(138);
							((DeclistContext)_localctx).otherType = hotype();

								                   parTypes.add(((DeclistContext)_localctx).otherType.nodeType);
								                   ParNode par = new ParNode((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),((DeclistContext)_localctx).otherType.nodeType); 
								                   function.addPar(par);
								                    if ( hmn.put((((DeclistContext)_localctx).otherId!=null?((DeclistContext)_localctx).otherId.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).otherType.nodeType, parOffset ++)) != null  ){
								                        //TODO lancia eccezioni
								                        System.out.println("Parameter id "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null)+" at line "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getLine():0)+" already declared");
								                        System.exit(0);
								                    }
								                
							}
							}
							setState(145);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(148);
					match(RPAR);

						                    entry.addType(new ArrowType(parTypes,((DeclistContext)_localctx).retType.nodeType));
						                
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(150);
						match(LET);
						setState(151);
						((DeclistContext)_localctx).declarations = declist();
						function.addDec(((DeclistContext)_localctx).declarations.astlist);
						setState(153);
						match(IN);
						}
					}

					setState(157);
					((DeclistContext)_localctx).bodyExp = exp();

					                      function.addBody(((DeclistContext)_localctx).bodyExp.ast);
					                      symTable.remove(nestingLevel--);
					                  
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(162);
				match(SEMIC);
				}
				}
				setState(166); 
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
			setState(168);
			((ExpContext)_localctx).first = term();

			        ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).first.ast;
			    
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(182);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(170);
					match(PLUS);
					setState(171);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case MINUS:
					{
					setState(174);
					match(MINUS);
					setState(175);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).other.ast);
					}
					break;
				case OR:
					{
					setState(178);
					match(OR);
					setState(179);
					((ExpContext)_localctx).other = term();
					((ExpContext)_localctx).ast =  null; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(186);
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
			setState(187);
			((TermContext)_localctx).firstFactor = factor();

			        ((TermContext)_localctx).ast =  ((TermContext)_localctx).firstFactor.ast;
			    
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(201);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(189);
					match(TIMES);
					setState(190);
					((TermContext)_localctx).other = factor();
					 ((TermContext)_localctx).ast =  new MultNode (_localctx.ast, ((TermContext)_localctx).other.ast); 
					}
					break;
				case DIV:
					{
					setState(193);
					match(DIV);
					setState(194);
					factor();
					((TermContext)_localctx).ast =  null;
					}
					break;
				case AND:
					{
					setState(197);
					match(AND);
					setState(198);
					((TermContext)_localctx).other = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast, ((TermContext)_localctx).other.ast);
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
			setState(206);
			((FactorContext)_localctx).first = value();

			        ((FactorContext)_localctx).ast =  ((FactorContext)_localctx).first.ast;
			    
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(220);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(208);
					match(EQ);
					setState(209);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new EqualNode (_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case GE:
					{
					setState(212);
					match(GE);
					setState(213);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new GreaterEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
					}
					break;
				case LE:
					{
					setState(216);
					match(LE);
					setState(217);
					((FactorContext)_localctx).other = value();
					 ((FactorContext)_localctx).ast =  new LessEqualNode(_localctx.ast,((FactorContext)_localctx).other.ast);
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
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				((ValueContext)_localctx).n = match(INTEGER);
				 ((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				match(TRUE);
				 ((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(FALSE);
				 ((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(231);
				match(NULL);
				((ValueContext)_localctx).ast =  null;
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(233);
				match(NEW);
				setState(234);
				match(ID);
				setState(235);
				match(LPAR);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(236);
					exp();
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(237);
						match(COMMA);
						setState(238);
						exp();
						}
						}
						setState(243);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(246);
				match(RPAR);
				((ValueContext)_localctx).ast =  null; 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(248);
				match(IF);
				setState(249);
				((ValueContext)_localctx).c = exp();
				setState(250);
				match(THEN);
				setState(251);
				match(CLPAR);
				setState(252);
				((ValueContext)_localctx).th = exp();
				setState(253);
				match(CRPAR);
				setState(254);
				match(ELSE);
				setState(255);
				match(CLPAR);
				setState(256);
				((ValueContext)_localctx).e = exp();
				setState(257);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).c.ast, ((ValueContext)_localctx).th.ast, ((ValueContext)_localctx).e.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(260);
				match(NOT);
				setState(261);
				match(LPAR);
				setState(262);
				exp();
				setState(263);
				match(RPAR);
				((ValueContext)_localctx).ast =  null; 
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 8);
				{
				setState(266);
				match(PRINT);
				setState(267);
				match(LPAR);
				setState(268);
				((ValueContext)_localctx).e = exp();
				setState(269);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 9);
				{
				setState(272);
				match(LPAR);
				setState(273);
				((ValueContext)_localctx).e = exp();
				setState(274);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(277);
				((ValueContext)_localctx).id = match(ID);

				            int j = nestingLevel;
				            STentry entry = null;
				            while (j>=0 && entry==null) {
				             	entry=(symTable.get(j--)).get((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null));
				            }
				            if (entry == null){
				                System.out.println("Id "+(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null)+" at line "+(((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getLine():0)+" not declared");
				                System.exit(0);
				            }
				            //se non ci sono argomenti dopo l'id immagino che sia una variabile
				            ((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry,nestingLevel);
				            final List<Node> arguments = new ArrayList<Node>();
				        
				setState(309);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(279);
					match(LPAR);
					setState(291);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(280);
						((ValueContext)_localctx).first = exp();

						            arguments.add(((ValueContext)_localctx).first.ast);
						        
						setState(288);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(282);
							match(COMMA);
							setState(283);
							((ValueContext)_localctx).other = exp();

							            arguments.add(((ValueContext)_localctx).other.ast);
							        
							}
							}
							setState(290);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(293);
					match(RPAR);

					        	((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).id!=null?((ValueContext)_localctx).id.getText():null),entry,arguments,nestingLevel);
					        
					}
					break;
				case DOT:
					{
					setState(295);
					match(DOT);
					setState(296);
					match(ID);
					setState(297);
					match(LPAR);
					setState(306);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(298);
						exp();
						setState(303);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(299);
							match(COMMA);
							setState(300);
							exp();
							}
							}
							setState(305);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(308);
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
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).nodeType =  ((HotypeContext)_localctx).t.nodeType;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				arrow();
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
			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(INT);
				((TypeContext)_localctx).nodeType =  IntType.instance();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				match(BOOL);
				((TypeContext)_localctx).nodeType =  BoolType.instance();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(323);
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
			setState(326);
			match(LPAR);
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(327);
				hotype();
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(328);
					match(COMMA);
					setState(329);
					hotype();
					}
					}
					setState(334);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(337);
			match(RPAR);
			setState(338);
			match(ARROW);
			setState(339);
			type();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u0158\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\5\2\33\n\2\3\2\5\2\36\n\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\5\2\'\n\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3/\n\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\5\3>\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3\5\3S\n\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3_\n\3\r\3\16\3`\3\3\3\3\5\3e\n\3"+
		"\3\3\3\3\3\3\7\3j\n\3\f\3\16\3m\13\3\3\3\6\3p\n\3\r\3\16\3q\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0090\n\4\f\4\16\4\u0093\13\4\5\4"+
		"\u0095\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009e\n\4\3\4\3\4\3\4\5\4\u00a3"+
		"\n\4\3\4\3\4\6\4\u00a7\n\4\r\4\16\4\u00a8\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b9\n\5\f\5\16\5\u00bc\13\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00cc\n\6\f\6\16"+
		"\6\u00cf\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\7\7\u00df\n\7\f\7\16\7\u00e2\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\b\u00f2\n\b\f\b\16\b\u00f5\13\b\5\b\u00f7\n\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u0121\n\b\f\b\16\b\u0124\13\b\5\b\u0126\n\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0130\n\b\f\b\16\b\u0133\13\b\5\b\u0135"+
		"\n\b\3\b\5\b\u0138\n\b\5\b\u013a\n\b\3\t\3\t\3\t\3\t\5\t\u0140\n\t\3\n"+
		"\3\n\3\n\3\n\3\n\5\n\u0147\n\n\3\13\3\13\3\13\3\13\7\13\u014d\n\13\f\13"+
		"\16\13\u0150\13\13\5\13\u0152\n\13\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4"+
		"\6\b\n\f\16\20\22\24\2\2\2\u017d\2\26\3\2\2\2\4o\3\2\2\2\6u\3\2\2\2\b"+
		"\u00aa\3\2\2\2\n\u00bd\3\2\2\2\f\u00d0\3\2\2\2\16\u0139\3\2\2\2\20\u013f"+
		"\3\2\2\2\22\u0146\3\2\2\2\24\u0148\3\2\2\2\26&\b\2\1\2\27\35\7\34\2\2"+
		"\30\32\5\4\3\2\31\33\5\6\4\2\32\31\3\2\2\2\32\33\3\2\2\2\33\36\3\2\2\2"+
		"\34\36\5\6\4\2\35\30\3\2\2\2\35\34\3\2\2\2\36\37\3\2\2\2\37 \7\35\2\2"+
		" !\5\b\5\2!\"\b\2\1\2\"\'\3\2\2\2#$\5\b\5\2$%\b\2\1\2%\'\3\2\2\2&\27\3"+
		"\2\2\2&#\3\2\2\2\'(\3\2\2\2()\7\13\2\2)\3\3\2\2\2*+\7 \2\2+.\7(\2\2,-"+
		"\7!\2\2-/\7(\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60=\7\7\2\2\61\62\7("+
		"\2\2\62\63\7\f\2\2\63:\5\22\n\2\64\65\7\r\2\2\65\66\7(\2\2\66\67\7\f\2"+
		"\2\679\5\22\n\28\64\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;>\3\2\2\2<:"+
		"\3\2\2\2=\61\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\7\b\2\2@k\7\t\2\2AB\7\37\2"+
		"\2BC\7(\2\2CD\7\f\2\2DE\5\22\n\2ER\7\7\2\2FG\7(\2\2GH\7\f\2\2HO\5\20\t"+
		"\2IJ\7\r\2\2JK\7(\2\2KL\7\f\2\2LN\5\20\t\2MI\3\2\2\2NQ\3\2\2\2OM\3\2\2"+
		"\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RF\3\2\2\2RS\3\2\2\2ST\3\2\2\2Td\7\b\2"+
		"\2U^\7\34\2\2VW\7\36\2\2WX\7(\2\2XY\7\f\2\2YZ\5\22\n\2Z[\7\25\2\2[\\\5"+
		"\b\5\2\\]\7\13\2\2]_\3\2\2\2^V\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2a"+
		"b\3\2\2\2bc\7\35\2\2ce\3\2\2\2dU\3\2\2\2de\3\2\2\2ef\3\2\2\2fg\5\b\5\2"+
		"gh\7\13\2\2hj\3\2\2\2iA\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2"+
		"\2mk\3\2\2\2np\7\n\2\2o*\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2"+
		"\2st\b\3\1\2t\5\3\2\2\2u\u00a6\b\4\1\2vw\7\36\2\2wx\7(\2\2xy\7\f\2\2y"+
		"z\5\20\t\2z{\7\25\2\2{|\5\b\5\2|}\b\4\1\2}\u00a3\3\2\2\2~\177\7\37\2\2"+
		"\177\u0080\7(\2\2\u0080\u0081\7\f\2\2\u0081\u0082\5\22\n\2\u0082\u0083"+
		"\b\4\1\2\u0083\u0084\7\7\2\2\u0084\u0094\b\4\1\2\u0085\u0086\7(\2\2\u0086"+
		"\u0087\7\f\2\2\u0087\u0088\5\20\t\2\u0088\u0091\b\4\1\2\u0089\u008a\7"+
		"\r\2\2\u008a\u008b\7(\2\2\u008b\u008c\7\f\2\2\u008c\u008d\5\20\t\2\u008d"+
		"\u008e\b\4\1\2\u008e\u0090\3\2\2\2\u008f\u0089\3\2\2\2\u0090\u0093\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0095\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0085\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0097\7\b\2\2\u0097\u009d\b\4\1\2\u0098\u0099\7\34\2\2\u0099"+
		"\u009a\5\6\4\2\u009a\u009b\b\4\1\2\u009b\u009c\7\35\2\2\u009c\u009e\3"+
		"\2\2\2\u009d\u0098\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\5\b\5\2\u00a0\u00a1\b\4\1\2\u00a1\u00a3\3\2\2\2\u00a2v\3\2\2\2"+
		"\u00a2~\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\13\2\2\u00a5\u00a7\3"+
		"\2\2\2\u00a6\u00a2\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\7\3\2\2\2\u00aa\u00ab\5\n\6\2\u00ab\u00ba\b\5\1\2"+
		"\u00ac\u00ad\7\3\2\2\u00ad\u00ae\5\n\6\2\u00ae\u00af\b\5\1\2\u00af\u00b9"+
		"\3\2\2\2\u00b0\u00b1\7\4\2\2\u00b1\u00b2\5\n\6\2\u00b2\u00b3\b\5\1\2\u00b3"+
		"\u00b9\3\2\2\2\u00b4\u00b5\7\17\2\2\u00b5\u00b6\5\n\6\2\u00b6\u00b7\b"+
		"\5\1\2\u00b7\u00b9\3\2\2\2\u00b8\u00ac\3\2\2\2\u00b8\u00b0\3\2\2\2\u00b8"+
		"\u00b4\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\t\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\5\f\7\2\u00be\u00cd"+
		"\b\6\1\2\u00bf\u00c0\7\5\2\2\u00c0\u00c1\5\f\7\2\u00c1\u00c2\b\6\1\2\u00c2"+
		"\u00cc\3\2\2\2\u00c3\u00c4\7\6\2\2\u00c4\u00c5\5\f\7\2\u00c5\u00c6\b\6"+
		"\1\2\u00c6\u00cc\3\2\2\2\u00c7\u00c8\7\20\2\2\u00c8\u00c9\5\f\7\2\u00c9"+
		"\u00ca\b\6\1\2\u00ca\u00cc\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb\u00c3\3\2"+
		"\2\2\u00cb\u00c7\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\13\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\5\16\b"+
		"\2\u00d1\u00e0\b\7\1\2\u00d2\u00d3\7\24\2\2\u00d3\u00d4\5\16\b\2\u00d4"+
		"\u00d5\b\7\1\2\u00d5\u00df\3\2\2\2\u00d6\u00d7\7\22\2\2\u00d7\u00d8\5"+
		"\16\b\2\u00d8\u00d9\b\7\1\2\u00d9\u00df\3\2\2\2\u00da\u00db\7\23\2\2\u00db"+
		"\u00dc\5\16\b\2\u00dc\u00dd\b\7\1\2\u00dd\u00df\3\2\2\2\u00de\u00d2\3"+
		"\2\2\2\u00de\u00d6\3\2\2\2\u00de\u00da\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\r\3\2\2\2\u00e2\u00e0\3\2\2\2"+
		"\u00e3\u00e4\7\'\2\2\u00e4\u013a\b\b\1\2\u00e5\u00e6\7\26\2\2\u00e6\u013a"+
		"\b\b\1\2\u00e7\u00e8\7\27\2\2\u00e8\u013a\b\b\1\2\u00e9\u00ea\7#\2\2\u00ea"+
		"\u013a\b\b\1\2\u00eb\u00ec\7\"\2\2\u00ec\u00ed\7(\2\2\u00ed\u00f6\7\7"+
		"\2\2\u00ee\u00f3\5\b\5\2\u00ef\u00f0\7\r\2\2\u00f0\u00f2\5\b\5\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00ee\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\b\2\2\u00f9\u013a\b\b"+
		"\1\2\u00fa\u00fb\7\30\2\2\u00fb\u00fc\5\b\5\2\u00fc\u00fd\7\31\2\2\u00fd"+
		"\u00fe\7\t\2\2\u00fe\u00ff\5\b\5\2\u00ff\u0100\7\n\2\2\u0100\u0101\7\32"+
		"\2\2\u0101\u0102\7\t\2\2\u0102\u0103\5\b\5\2\u0103\u0104\7\n\2\2\u0104"+
		"\u0105\b\b\1\2\u0105\u013a\3\2\2\2\u0106\u0107\7\21\2\2\u0107\u0108\7"+
		"\7\2\2\u0108\u0109\5\b\5\2\u0109\u010a\7\b\2\2\u010a\u010b\b\b\1\2\u010b"+
		"\u013a\3\2\2\2\u010c\u010d\7\33\2\2\u010d\u010e\7\7\2\2\u010e\u010f\5"+
		"\b\5\2\u010f\u0110\7\b\2\2\u0110\u0111\b\b\1\2\u0111\u013a\3\2\2\2\u0112"+
		"\u0113\7\7\2\2\u0113\u0114\5\b\5\2\u0114\u0115\7\b\2\2\u0115\u0116\b\b"+
		"\1\2\u0116\u013a\3\2\2\2\u0117\u0118\7(\2\2\u0118\u0137\b\b\1\2\u0119"+
		"\u0125\7\7\2\2\u011a\u011b\5\b\5\2\u011b\u0122\b\b\1\2\u011c\u011d\7\r"+
		"\2\2\u011d\u011e\5\b\5\2\u011e\u011f\b\b\1\2\u011f\u0121\3\2\2\2\u0120"+
		"\u011c\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2"+
		"\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u011a\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\7\b\2\2\u0128\u0138\b\b"+
		"\1\2\u0129\u012a\7\16\2\2\u012a\u012b\7(\2\2\u012b\u0134\7\7\2\2\u012c"+
		"\u0131\5\b\5\2\u012d\u012e\7\r\2\2\u012e\u0130\5\b\5\2\u012f\u012d\3\2"+
		"\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u012c\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135\u0136\3\2\2\2\u0136\u0138\7\b\2\2\u0137\u0119\3\2\2\2\u0137"+
		"\u0129\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a\3\2\2\2\u0139\u00e3\3\2"+
		"\2\2\u0139\u00e5\3\2\2\2\u0139\u00e7\3\2\2\2\u0139\u00e9\3\2\2\2\u0139"+
		"\u00eb\3\2\2\2\u0139\u00fa\3\2\2\2\u0139\u0106\3\2\2\2\u0139\u010c\3\2"+
		"\2\2\u0139\u0112\3\2\2\2\u0139\u0117\3\2\2\2\u013a\17\3\2\2\2\u013b\u013c"+
		"\5\22\n\2\u013c\u013d\b\t\1\2\u013d\u0140\3\2\2\2\u013e\u0140\5\24\13"+
		"\2\u013f\u013b\3\2\2\2\u013f\u013e\3\2\2\2\u0140\21\3\2\2\2\u0141\u0142"+
		"\7$\2\2\u0142\u0147\b\n\1\2\u0143\u0144\7%\2\2\u0144\u0147\b\n\1\2\u0145"+
		"\u0147\7(\2\2\u0146\u0141\3\2\2\2\u0146\u0143\3\2\2\2\u0146\u0145\3\2"+
		"\2\2\u0147\23\3\2\2\2\u0148\u0151\7\7\2\2\u0149\u014e\5\20\t\2\u014a\u014b"+
		"\7\r\2\2\u014b\u014d\5\20\t\2\u014c\u014a\3\2\2\2\u014d\u0150\3\2\2\2"+
		"\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e"+
		"\3\2\2\2\u0151\u0149\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0154\7\b\2\2\u0154\u0155\7&\2\2\u0155\u0156\5\22\n\2\u0156\25\3\2\2"+
		"\2%\32\35&.:=OR`dkq\u0091\u0094\u009d\u00a2\u00a8\u00b8\u00ba\u00cb\u00cd"+
		"\u00de\u00e0\u00f3\u00f6\u0122\u0125\u0131\u0134\u0137\u0139\u013f\u0146"+
		"\u014e\u0151";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}