/**
 * @author Stephen 20061696
 *
 */
import java.util.ArrayList;
import java.util.Collections;

public class BinaryTree {

	private Node treeRootNode;
	private ArrayList<Node> forrest;
	private ArrayList<Character> charArray;
	private final String TEST_STRING= "Hello World, Good Morning! How is everyone doing?";
	
	public BinaryTree() {
		Main();
	}

	private void Main() {
		forrest = new ArrayList<>();
		charArray = new ArrayList<>();
		buildTree();
		
		encodeText(TEST_STRING);
	}
	
	private void buildTree() {
		//Add all characters from the string to the charArray.
		for (int index = 0; index < TEST_STRING.length(); ++index) {
			charArray.add(TEST_STRING.charAt(index) );
		}
		//Call to build nodes method.
		buildNodes();
		//Call to linkNodes method.
		linkNodes();
	}

	/*
	 * This method makes the nodes that will be linked to form a binary tree.
	 */
	private void buildNodes () {
		//Sort the charArray to make finding the number of times a char appears easier.
		Collections.sort(charArray);
		char ch = charArray.get(0);
		int counter = 1;
		for (int index = 1; index <= charArray.size(); ++index) {
			if (index != charArray.size() && charArray.get(index) == ch) {
				++counter;
			} else {
				forrest.add(new Node(counter, String.valueOf(ch)) );
				if (index != charArray.size()) ch = charArray.get(index);
				counter = 1;
			}
		}
	}

	/*
	 * This method links the nodes to create the tree, the two lowest weighted nodes data and weight are used to make
	 * a root node. The root node has the lowest nodes as it's left and right node links and those nodes have the new node as their root node.
	 * The final new node is set as the root node of the tree.
	 */
	private void linkNodes() {
		Collections.sort(forrest);

		int counter = forrest.size()-1;
		while (counter != 0) {
			Node lowest = forrest.get(counter);
			--counter;
			Node secondLowest = forrest.get(counter);
			//Make a new node that is the root of the 2 lowest weight nodes.
			Node newNode = new Node( lowest.getWeight() + secondLowest.getWeight() , lowest.getData() + secondLowest.getData() ); 
			newNode.setLeft(lowest);
			newNode.setRight(secondLowest);
			System.out.println("The two lowest weight nodes: " + "\n" + lowest + secondLowest + "\n" + "The new node: " + newNode);

			//Set the root on both the nodes to the new node.
			lowest.setRoot(newNode);
			secondLowest.setRoot(newNode);

			//Add the new node to the forrest ArrayList at index 0 and sort a sublist of the list.
			//The sublist decreases by one each iteration.
			forrest.add(0, newNode);
			Collections.sort(forrest.subList(0, counter));
		}

		treeRootNode = forrest.get(0);
		System.out.println("This is the root of the tree: " + treeRootNode);

	}
	
	private void encodeText(String text) {
		String encodedString = "";
		for (int index = 0; index < text.length(); ++index) {
			encodedString+=encodeACharacter(text.charAt(index));
		}
		
		System.out.println("This is the encoded text: " + encodedString);
	}
	
	private void decodeString(String str) {
		
	}

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
				else if (currentNode.getLeft() != null && currentNode.getLeft().getData().contains(String.valueOf(currentCh) ) ) {
					currentNode = currentNode.getLeft();
					encodedChar += "0";
				} else if (currentNode.getRight() != null && currentNode.getRight().getData().contains(String.valueOf(currentCh) ) ) {
					currentNode = currentNode.getRight();
					encodedChar += "1";
				}
			}
		}
		return encodedChar;
	}

	public static void main (String[] args) {
		new BinaryTree();
	}

}
