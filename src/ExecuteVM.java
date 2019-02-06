import ast.util.Utils;

public class ExecuteVM {

	public static final int CODESIZE = 10000;
	public static final int MEMSIZE = Utils.MEM_SIZE;

	private int[] code;
	private int[] memory = new int[MEMSIZE];

	private int ip = 0;
	private int sp = MEMSIZE;

	private int hp = 0;
	private int fp = MEMSIZE;
	private int ra;
	private int rv;

	public ExecuteVM(int[] code) {
		this.code = code;
	}

	public void cpu() {
		while (true) {
			int bytecode = code[ip++]; // fetch
			//printMemory();
			
			//System.out.println(ip + " " + printInstruction(bytecode) + " " + ra + " mem " + memory[sp-1]);
			int v1, v2;
			int address;
			switch (bytecode) {
			case SVMParser.PUSH:
				push(code[ip++]);
				break;
			case SVMParser.POP:
				pop();
				break;
			case SVMParser.ADD:
				v1 = pop();
				v2 = pop();
				push(v2 + v1);
				break;
			case SVMParser.MULT:
				v1 = pop();
				v2 = pop();
				push(v2 * v1);
				break;
			case SVMParser.DIV:
				v1 = pop();
				v2 = pop();
				push(v2 / v1);
				break;
			case SVMParser.SUB:
				v1 = pop();
				v2 = pop();
				push(v2 - v1);
				break;
			case SVMParser.STOREW: //
				address = pop();
				memory[address] = pop();
				break;
			case SVMParser.LOADW: //
				push(memory[pop()]);
				break;
			case SVMParser.BRANCH:
				address = code[ip];
				ip = address;
				break;
			case SVMParser.BRANCHEQ: //
				address = code[ip++];
				v1 = pop();
				v2 = pop();
				if (v2 == v1)
					ip = address;
				break;
			case SVMParser.BRANCHLESSEQ:
				address = code[ip++];
				v1 = pop();
				v2 = pop();
				if (v2 <= v1)
					ip = address;
				break;
			case SVMParser.JS: //
				address = pop();
				ra = ip;
				ip = address;
				break;
			case SVMParser.STORERA: //
				ra = pop();
				break;
			case SVMParser.LOADRA: //
				push(ra);
				break;
			case SVMParser.STORERV: //
				rv = pop();
				break;
			case SVMParser.LOADRV: //
				push(rv);
				break;
			case SVMParser.LOADFP: //
				push(fp);
				break;
			case SVMParser.STOREFP: //
				fp = pop();
				break;
			case SVMParser.COPYFP: //
				fp = sp;
				break;
			case SVMParser.STOREHP: //
				hp = pop();
				break;
			case SVMParser.LOADHP: //
				push(hp);
				break;
			case SVMParser.PRINT:
				System.out.println((sp < MEMSIZE) ? memory[sp] : "Empty stack!");
				break;
			case SVMParser.HALT:
				return;
			}
		}
	}

	private int pop() {
		return memory[sp++];
	}

	private void push(int v) {
		memory[--sp] = v;
	}
	private void printMemory() {
		System.out.println("STACK:::");
		for(int i = 9999; i >= sp; i --) {
			System.out.println(memory[i]);
		}
		System.out.println("________");
		System.out.println("________");
		System.out.println("________");
		System.out.println("HEAP:::");
		for(int i = hp; i >= 0; i--) {
			System.out.println(memory[i]);
		}
	}
	
	private String printInstruction(int bytecode) {
	switch (bytecode) {
		case SVMParser.PUSH:
			return "push";
		case SVMParser.POP:
			return "pop";
		case SVMParser.ADD:
			return "add";
		case SVMParser.MULT:
			return "mult";
		case SVMParser.DIV:
			return "div";
		case SVMParser.SUB:
			return "sub";
		case SVMParser.STOREW:
			return "storew";
		case SVMParser.LOADW:
			return "loadw";
		case SVMParser.BRANCH:
			return "branch";
		case SVMParser.BRANCHEQ: //
			return "breancheq";
		case SVMParser.BRANCHLESSEQ:
			return "branchlesseq";
		case SVMParser.JS: //
			return "js";
		case SVMParser.STORERA: //
			return "storear";
		case SVMParser.LOADRA: //
			return "loadra";
		case SVMParser.STORERV: //
			return "storerv";
		case SVMParser.LOADRV: //
			return "loadrv";
		case SVMParser.LOADFP: //
			return "loadfp";
		case SVMParser.STOREFP: //
			return "storefp";
		case SVMParser.COPYFP: //
			return "copyfp";
		case SVMParser.STOREHP: //
			return "storehp";
		case SVMParser.LOADHP: //
			return "loadhp";
		case SVMParser.PRINT:
			return "print";
		case SVMParser.HALT:
			return "halt";
		}
	return "";
	}

}