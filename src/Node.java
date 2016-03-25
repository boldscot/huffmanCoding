/**
 *	@author Stephen Collins 20061696
 * 
 */

public class Node implements Comparable<Node>{

	private int weight, nodeNumber;
	private String data;
	private Node left, right, root;
	public static int counter = 0;

	public Node(int weight, String data) {
		this.weight = weight;
		this.data = data;
		this.left = null;
		this.right = null;
		this.root = null;
		this.nodeNumber = counter;
		++counter;
	}

	/**
	 * 	The compareTo method will return a negative number if current value is less than the other value, 
	 *	positive number if current value is greater than the other value and zero if both values 
	 *	are equal.
	 */
	@Override
	public int compareTo(Node compareNode){
		int compareNodeWeight = compareNode.getWeight();
		return compareNodeWeight-this.getWeight();
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the left
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	
	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return  "Node number: " + nodeNumber + ": [ Weight: " + weight + "," + " Data: " + data + " ]" + "\n";

	}

}

