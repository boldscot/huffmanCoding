import java.util.ArrayList;

/**
 * @author Stephen 20061696
 *
 */

public class BinaryTree {

	private Node rootNode;
	private ArrayList<Node> tree;
	private final String TEST_STRING= "Hello World, Good Morning!";

	public BinaryTree() {
		tree = new ArrayList<>();
		buildTree();
	}

	private void buildTree() {
		for (int index = 0; index < TEST_STRING.length(); ++index) {
			tree.add(new Node(1, TEST_STRING.charAt(index)));
		}
		for (int i = 0; i < tree.size()-1; ++i) {
			System.out.println(tree.get(i));
		}

	}

	public static void main (String[] args) {
		new BinaryTree();
		
	}

}
