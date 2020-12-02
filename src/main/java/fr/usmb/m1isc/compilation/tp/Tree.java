package fr.usmb.m1isc.compilation.tp;

import java.util.ArrayList;

public class Tree {

	/// The type of node for the abstract tree
	public enum NodeType { 
		INTEGER, OPERATOR, VARIABLE_NAME, BOOLEAN, ROOT, INPUT, OUTPUT
	}
	public enum Operator {
		UNARY_MINUS, NOT, DO, //unary operators and nodes
		ADD, SUB, MULT, DIV, MOD, //binary operators
		LT, LTE, GT, GTE, EQ, DIFF, AND, OR, //binary logic operators
		LET, WHILE, //binary nodes
		IF //tertiary nodes
		}
	
	
	
	//	attributes
	private NodeType type;
	
	// we stock a string if VARIABLE_NAME, an operator if OPERATOR or an int if INTEGER
	private Object value;
	//private String variable_name;
	//private int value;
	//private Operator op
	
	private ArrayList<Tree> children = new ArrayList<>();
	
	// constructors 
		
	// constructors for 4 leaf node types
	
	//INTEGER constructor
	public Tree(NodeType type, Integer val)
	{
		this.value = val;
		this.type = type;
	}
	//BOOLEAN constructor
	public Tree(NodeType type, Boolean bool)
	{
		this.value = bool;
		this.type = type;
	}
	//OPERATOR constructor
	public Tree(NodeType type, Operator op)
	{
		this.value = op;
		this.type = type;
	}
	//VARIABLE_NAME constructor
	public Tree(NodeType type, String var)
	{
		this.value = var;
		this.type = type;
	}
	
	// constructors with children :
	public Tree(NodeType type, Object value, Tree child)
	{
		this.type = type;
		this.value = value;
		this.children.add(child);
	}
	
	public Tree(NodeType type, Object value, Tree leftChild, Tree rightChild)
	{
		this.type = type;
		this.value = value;
		this.children.add(leftChild);
		this.children.add(rightChild);
	}
	
	public Tree(NodeType type, Object value, Tree leftChild, Tree middleChild, Tree rightChild)
	{
		this.type = type;
		this.value = value;
		this.children.add(leftChild);
		this.children.add(middleChild);
		this.children.add(rightChild);
	}
	
	
	// constructors for root node and INPUT/OUTPUT nodes
	
	public Tree(NodeType type)
	{
		this.value = null;
		this.type = type;
	}
	
	public Tree(NodeType type, Tree child)
	{
		this.value = null;
		this.type = type;
		this.children.add(child);
	}
	
	public Tree(NodeType type, Tree leftChild, Tree rightChild)
	{
		this.value = null;
		this.type = type;
		this.children.add(leftChild);
		this.children.add(rightChild);
	}
	
	
	// Getters and Setters 
	public NodeType getType() {
		return type;
	}
	public void setType(NodeType type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Tree getFirstSon() {
		return this.children.get(0);
	}
	public void setFirstSon(Tree son) {
		this.children.set(0, son);
	}
	public Tree getSecondSon() {
		return this.children.get(1);
	}
	public void setSecondSon(Tree son) {
		this.children.set(1, son);
	}
	
	private String unaryOperatorTostring() {
		StringBuilder acc = new StringBuilder("");
		
		switch((Operator) this.value)
		{
		case UNARY_MINUS:
			acc.append("- ");
			break;
			
		case NOT:
			acc.append("NOT ");
			break;
			
		case DO:
			acc.append("DO ");
			break;
			
		default:
			return "error, incorrect operator";
		}
		acc.append(this.children.get(0).toString());
		return acc.toString();
	}
	
	public String binaryOperatorToString() {
		StringBuilder acc = new StringBuilder("");
		acc.append("( ");
		
		switch((Operator) this.value)
		{
		case ADD:
			acc.append(this.children.get(0).toString());
			acc.append(" + ");
			acc.append(this.children.get(1).toString());
			break;
		case SUB:
			acc.append(this.children.get(0).toString());
			acc.append(" - ");
			acc.append(this.children.get(1).toString());
			break;
		case MULT:
			acc.append(this.children.get(0).toString());
			acc.append(" * ");
			acc.append(this.children.get(1).toString());
			break;
		case DIV:
			acc.append(this.children.get(0).toString());
			acc.append(" / ");
			acc.append(this.children.get(1).toString());
			break;
		case MOD:
			acc.append(this.children.get(0).toString());
			acc.append(" % ");
			acc.append(this.children.get(1).toString());
			break;
		case LT:
			acc.append(this.children.get(0).toString());
			acc.append(" < ");
			acc.append(this.children.get(1).toString());
			break;
		case LTE:
			acc.append(this.children.get(0).toString());
			acc.append(" <= ");
			acc.append(this.children.get(1).toString());
			break;
		case GT:
			acc.append(this.children.get(0).toString());
			acc.append(" > ");
			acc.append(this.children.get(1).toString());
			break;
		case GTE:
			acc.append(this.children.get(0).toString());
			acc.append(" >= ");
			acc.append(this.children.get(1).toString());
			break;
		case EQ:
			acc.append(this.children.get(0).toString());
			acc.append(" == ");
			acc.append(this.children.get(1).toString());
			break;
		case DIFF:
			acc.append(this.children.get(0).toString());
			acc.append(" != ");
			acc.append(this.children.get(1).toString());
			break;
		case AND:
			acc.append(this.children.get(0).toString());
			acc.append(" && ");
			acc.append(this.children.get(1).toString());
			break;
		case OR:
			acc.append(this.children.get(0).toString());
			acc.append(" || ");
			acc.append(this.children.get(1).toString());
			break;
		case LET:
			acc.append("LET ");
			acc.append(this.children.get(0).toString());
			acc.append(" = ");
			acc.append(this.children.get(1).toString());
		case WHILE:
			acc.append("WHILE( ");
			acc.append(this.children.get(0).toString());
			acc.append(") ");
			acc.append(this.children.get(1).toString());
		default:
			return "error, incorrect operator";
		}
		acc.append(" )");
		return acc.toString();
	}
	
	public String tertiaryOperatorToString()
	{
		StringBuilder acc = new StringBuilder("");
		
		switch((Operator) this.value)
		{
		case IF:
			acc.append("IF( ");
			acc.append(this.children.get(0).toString());
			acc.append(" ) THEN ( ");
			acc.append(this.children.get(1).toString());
			acc.append(" ) ELSE ( ");
			acc.append(this.children.get(2).toString());
			acc.append(" )");
			break;
			
		default:
			return "error, wrong operator";
		}
		return acc.toString();
	}
	
	public String toString()
	{
		StringBuilder acc = new StringBuilder("");

		switch(this.type) {
		
		case INTEGER:
			acc.append(Integer.toString((int) this.value));
			break;
			
		case BOOLEAN:
			acc.append(Boolean.toString((boolean) this.value));
			
		case OPERATOR:
			switch(this.children.size()) {
			
			case 1: //1 children => unary operator
				acc.append(this.unaryOperatorTostring());
				break;
				
			case 2: //2 children => binary operator (infix notation)
				acc.append(this.binaryOperatorToString());
				break;
				
			case 3: //3 children => tertiary operator
				acc.append(this.tertiaryOperatorToString());
				break;
				
			default:
				acc.append("error, wrong number of children");
			}
			break;
			
		case VARIABLE_NAME:
			acc.append((String) this.value);
			break;
			
		case INPUT:
			acc.append("INPUT");
			break;
		
		case OUTPUT:
			acc.append("OUTPUT");
			break;
			
		case ROOT:
			for (int i = 0; i < this.children.size(); ++i)
			{
				acc.append(this.children.get(i));
				acc.append(";");
			}
			break;
			
		default:
			acc.append("??ERROR??");
			break;
		}
		return acc.toString();
	}
}
