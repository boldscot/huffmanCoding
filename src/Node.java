/**
 *	@author Stephen Collins 20061696
 * 
 */

public class Node implements Comparable<Node>{

	private int weight;
	private char data;
	private Node left = null, right = null;

	public Node(int weight, char data) {
		this.weight = weight;
		this.data = data;
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
	public char getData() {
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

	@Override
	public String toString()
	{
		return  "Weight: " + weight + "\n"
				+ "Data: " + data + "\n"
				+"Left Node: " + left + "\n"
				+"Right Node: " + right + "\n";

	}

}

