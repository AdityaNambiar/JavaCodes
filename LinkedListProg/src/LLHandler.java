import java.util.Scanner;
class Node{
	private Object data = null; // Can be any valued item.
	private Node next = null; // Points to a new Node.
	
	Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}
	// Getters and Setters:
	// To get a Node data:
	public Object getData() {
		return this.data;
	}
	// To set a Node data:
	public void setData(Object data) {
		this.data = data;
	}
	public Node getNext() {
		return this.next;
	}
	public void setNext(Node nextNode) {
		this.next = nextNode;
	}
}
class LinkedList{
	private Node head;
	private Node tail;	
	LinkedList(){
		this.head = null;
		this.tail = null;
	}
	public Node getHead() {
		return this.head;
	}
	public Node getTail() {
		return this.tail;
	}
	// Methods to add: addNode, insertNode, updateNode, deleteNode, findNode, length
	// Main method is: display
	public void addNode(Object data) { // this is also called "append node"
		Node newNode = new Node(data, null);
		if (this.head == null) {
			this.head = newNode;
			this.tail = null;
		}
		else {
			Node temp = getHead();
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);
			this.tail = newNode;
		}
	}
	public void display() {
		Node temp = getHead();
		while(temp != null) {
			LLHandler.op("Linked List contains: "+String.valueOf(temp.getData()));
			temp = temp.getNext();
		}
	}
	public int length() {
		int c = 0;
		Node temp = getHead();
		while (temp != null) {
			c += 1;
			temp = temp.getNext();
		}
		return c;
	}
	public void insertNode(int pos, Object data) {
		Node newNode = new Node(data, null);
		Node temp = getHead();
		if (temp.getNext() == null) {
			addNode(data);
		}
		while (pos > pos-1) {
			//LLHandler.op("CHECK HERE: "+String.valueOf(temp.getData()));
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);	
		}
	}
}
public class LLHandler {
	
	public static <T> void op(Object x) {
		System.out.println(x);
	}

	public static void main(String args[]) {
		// Create Nodes and Perform operation on Linked List here.
		
		LinkedList ll = new LinkedList();
		Scanner sc = new Scanner(System.in);
		op("Enter a value for a node: ");
		String name = "";
		int val1 = 0;
		name = sc.next();
		val1 = sc.nextInt();
		ll.addNode(name);
		ll.addNode(val1);
		ll.addNode(name);
		ll.addNode(val1);
		ll.display();
		ll.insertNode(2, name+"Nigga");
		
		ll.display();

		op("Length of LL: "+String.valueOf(ll.length()));
	}
}
