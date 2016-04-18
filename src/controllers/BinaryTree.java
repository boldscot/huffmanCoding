package controllers;
/**
 * @author Stephen 20061696
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import models.Node;

public class BinaryTree {

	private Node treeRootNode;
	public ArrayList<Node> forrest;
	public Map<Character, Integer> charMap;
	public final String TEST_STRING = "Hello World, Good Morning! How is everyone doing?";
	public String fileString;

	/*
	 *  Constructor.
	 */
	public BinaryTree() {
		forrest = new ArrayList<>();
		charMap = new HashMap<>();
	}

	/*
	 * Build the tree.
	 */
	public void buildTree() {
		//Call to build nodes method.
		buildNodes();
		//Call to linkNodes method.
		linkNodes();
	}

	/*
	 * This method makes the nodes that will be linked to form a binary tree.
	 */
	private void buildNodes () {
		//Add all characters from the fileString to the charMap.
		for (int index = 0; index < fileString.length(); ++index) {
			char key = fileString.charAt(index);
			if (!charMap.containsKey(key))charMap.put(key, 1);
			else
				charMap.put(key, charMap.get(key)+1);
		}
		for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
			forrest.add(new Node(entry.getValue(), String.valueOf(entry.getKey())) );
			if (forrest.size() > 1) siftUp();
		}
		for (int i = 0; i< forrest.size(); i++) {
			System.out.println(forrest.get(i));
		}
	}


	/*
	 * This method links the nodes to create the tree, the two lowest weighted nodes data and weight are used to make
	 * a root node. The root node has the lowest nodes as it's left and right node links and those nodes have the new node as their root node.
	 * The final new node is set as the root node of the tree.
	 */
	private void linkNodes() {
		int counter = 0;
		while (counter != forrest.size() -1) {
			Node lowest = forrest.get(counter);
			counter++;
			Node secondLowest = forrest.get(counter);
			counter++;
			//Make a new node that is the root of the 2 lowest weight nodes.
			Node newNode = new Node( lowest.getWeight() + secondLowest.getWeight() , 
					lowest.getData() + secondLowest.getData() ); 
			newNode.setLeft(lowest);
			newNode.setRight(secondLowest);
			System.out.println("The two lowest weight nodes: " + "\n" + lowest + secondLowest + "\n" + "The new node: " + newNode);
			//Set the root on both the nodes to the new node.
			lowest.setRoot(newNode);
			secondLowest.setRoot(newNode);
			forrest.add(newNode);
			siftUp();
		}
		treeRootNode = forrest.get(forrest.size()-1);
		System.out.println("This is the root of the tree: " + treeRootNode);
	}

	/*
	 * This method cycles throught the array working from the bottom up, the weight of the two nodes
	 * are compared and if the nodes weight  that the compareTo method was called on is less than
	 * the node being compared the node is moved up the array. 
	 * Caling this method on each new array entry sorts the array in descending order.
	 */
	private void siftUp() {
		int dataToSift = forrest.size() - 1;
		while (dataToSift > 0) {
			int nextItem = dataToSift - 1;
			Node item = forrest.get(dataToSift);
			Node nextNode = forrest.get(nextItem);
			if (item.compareTo(nextNode) < 0) {
				// swap
				forrest.set(nextItem, item);
				forrest.set(dataToSift, nextNode);
				// move up one node
				dataToSift--;
			} else
				break;
		}
	}

	/*
	 * 
	 */
	public String encodeText(String text) {
		String encodedStr = "";
		for (int index = 0; index < text.length(); ++index) {
			encodedStr += encodeACharacter(text.charAt(index));
		}
		System.out.println(encodedStr);
		return encodedStr;
	}

	/*
	 * This method encodes a single character into a series of 1's and 0's, starting from the root node of the tree the left and right 
	 * nodes are checked to see which one contains the character. The process is repeated until the data of a node equals the character.
	 * For each node traversed a 1 or 0 is added to a string which is returned when the data of a node equals the character.
	 */
	private String encodeACharacter(char ch) {
		Node currentNode = null;
		String encodedChar= "";
		char currentCh = ch ;

		if (treeRootNode.getLeft().getData().contains(String.valueOf(currentCh) ) ) {
			currentNode = treeRootNode.getLeft();
			encodedChar += "0";
		}
		else {
			currentNode = treeRootNode.getRight();
			encodedChar += "1";
		}
		while (currentNode.getLeft() != null && currentNode.getRight() != null ) {
			if (currentNode.getData().contains(String.valueOf(currentCh)) ) {
				if (currentNode.getData().equals(String.valueOf(currentCh)) ){
					return encodedChar;
				}
				else if (currentNode.getLeft() != null 
						&& currentNode.getLeft().getData().contains(String.valueOf(currentCh) ) ) {
					currentNode = currentNode.getLeft();
					encodedChar += "0";
				} else if (currentNode.getRight() != null 
						&& currentNode.getRight().getData().contains(String.valueOf(currentCh) ) ) {
					currentNode = currentNode.getRight();
					encodedChar += "1";
				}
			}
		}
		return encodedChar;
	}


	/*
	 * This method takes is a string of 1's and 0's and traverses the tree to decode it back into letters.
	 * If it's a 0 we left in the tree or if its a 1 we go right, if there is no node that corresponds with 
	 * the 1 or 0 then we go back to the root of the tree because this must mean the start of a new char.
	 */
	public String decodeText(String str) {
		Node currentNode = treeRootNode;
		String decodedText = "";
		char goLeft = '0';

		int counter = 0;
		while(counter < str.length() )  {
			if (str.charAt(counter) == goLeft) {
				if (currentNode.getLeft() != null ) {
					currentNode = currentNode.getLeft();
					++counter;
				} else {
					//Node was null, must be the the first bit of the next char.
					decodedText += currentNode.getData();
					currentNode = treeRootNode;
				}
			} else {
				if (currentNode.getRight() != null ) {
					currentNode = currentNode.getRight();
					++counter;
				} else {
					//Node was null, must be the the first bit of the next char.
					decodedText+=currentNode.getData();
					currentNode = treeRootNode;
				} 
			}
		}
		//Last character.
		decodedText += currentNode.getData();
		return decodedText;
	}
}
