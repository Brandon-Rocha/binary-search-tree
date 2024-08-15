/**
 * The BinarySearchTree class creates a binary search tree utilizing the Node class and includes methods to search, delete, insert, traverse, and print in various formats. 
 * This class also uses a Node called root to indicate which node is the top if the binary search tree.
 * <p>
 * There is also an array that will help with printing in order of GDP per capita rather than by the country's name. 
 * 
 * 
 * @author Brandon Rocha
 *
 */
public class BinarySearchTree {
	private Node root;
	private ArrayHelp helperArray;
/**
 * Constructor that initializes the root and array.
 * 	
 */
	public BinarySearchTree() {
		root = null;
		helperArray = new ArrayHelp(250);
	}

/**
 * Method that allows for the insertion of a country into the binary search tree, its position being dependent on the country name.
 * 
 * 	
 * @param name is of type String that represents the name of the country to be inserted
 * @param gdppc is of type double that represents the GDP per capita of the country to be inserted
 */
	public void insert(String name, double gdppc) {
		Node newNode = new Node();
		newNode.countryName = name;
		newNode.gdppc = gdppc;
		
		helperArray.insert(name, gdppc);
		
		if (root == null) {
			root = newNode;
		}
		else {
			Node current = root;
			Node parent;

			while(true) {
				parent = current;
				
				if(name.compareTo(current.countryName) < 0 ) { //go left?
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}//end if null
				}//end go left
				else { //go right
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}//end if null
				}//end go right
			}//end while loop
		}//end else no root
	}// end insert()
	
/**
 * Method that allows the user to search the binary search tree for a specific country using the name of the country.
 * If the country is found then it will print the path that was taken in search of the given country name. 
 * 
 * @param name of type String is the name of the Country to be searched for	
 * @return gdppc of type double is the GDP per capita of the country that was found
 */
	public double find(String name) {
		Node current = root;
		String names[] = new String[50];
		int i = 0;
		while(!current.countryName.equals(name)) {
			names[i] = current.countryName;
			++i;
			if (name.compareTo(current.countryName) < 0) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			if (current == null) {
				return -1;
			}
		}
		names[i] = current.countryName;
		
		//print gdppc and path if found
		System.out.printf("The country %s was found and has a GDPPC of: %.3f \n", name, current.gdppc);
		System.out.print("The path taken was as follows: ");
		for (int j = 0; j <= i; ++j) {
			if(j==i) {
				System.out.println(names[j]);
			}
			else {
			System.out.print(names[j] + " -> ");
			}
		}
		return current.gdppc;
	}//end find()

/**
 * Method that allows a country to be deleted from the binary search tree using the country's name, if it exists within the tree.
 * The array will mimic the deletion, if there was one.
 * 
 * 
 * @param name of type String that is the name of the country to be deleted
 */
	public void delete(String name) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(!current.countryName.equals(name)) {
			parent = current;
			if(name.compareTo(current.countryName) < 0) { // go left
				isLeftChild = true;
				current = current.leftChild;
			}//end if go left
			else { //go right
				isLeftChild = false;
				current = current.rightChild;
			}//end else go right
			if(current==null) { 
				System.out.printf("The country %s was not in the binary search tree. \n", name);
				System.out.println();
				return; // did not find anything to delete
			}
		}//end while loop
		
		int yesNo = helperArray.find(name);
		if(yesNo >= 0 ) {
			helperArray.delete(yesNo);
		}
		
		//if there are no children then delete
		if(current.leftChild == null && current.rightChild == null) {
			if (current == root) {
				root = null;
			}
			else if(isLeftChild) {
				parent.leftChild = null;
			}
			else {
				parent.rightChild = null;
			}
		}//end delete if there leaf (no children)
		
		// if no right child, replace with left subtree
		else if(current.rightChild == null) { 
			if (current == root) {
				root = current.leftChild;
			}
			else if(isLeftChild) {
				parent.leftChild = current.leftChild;
			}
			else {
				parent.rightChild = current.leftChild;
			}
		}// end delete if no right child
		
		//if no left child, replace with right subtree
		else if(current.leftChild == null) {
			if(current == root) {
				root = current.rightChild;
			}
			else if(isLeftChild) {
				parent.leftChild = current.rightChild;
			}
			else {
				parent.rightChild = current.rightChild;
			}
		}//end if no left child
		
		//two children so must replace with inorder successor
		else {
			Node successor = getSuccessor(current);
		
			if (current == root) {
				root = successor;
			}
			else if (isLeftChild) {
				parent.leftChild = successor;
			}
			else {
				parent.rightChild = successor;
			}
			
			successor.leftChild = current.leftChild;
			
		}// end else two children
		System.out.printf("The country %s was deleted from the binary search tree. \n", name);
		System.out.println();
	}//end delete
/**
 * Method that helps with deletion and maintains the structure of the binary search tree by finding which nodes is to take the place of the node that was deleted.
 * 
 * @param delNode of type Node that is the node to be deleted from the tree
 * @return successor of type Node that is to take the place of the node that was deleted from the tree
 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		
		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		
		if(successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		
		return successor;
	}//end getSuccessor()
/**
 * Method that determines in which order the contents of the tree will be printed, depending on the menu option chosen from the menu of Project4.
 * It will call a method to print in: pre-order, in-order, or post-order.
 * 
 * 	
 * @param traverseType of type int that will indicate in what order to print the contents of the tree 
 */
	public void traverse(int traverseType) {
		switch(traverseType) {
			case 1: 
				System.out.print("Pre-order traversal is as follows: \n");
				System.out.println();
				System.out.printf("%-33s %10s\n", "Name of Country", "GDP Per Capita");
				System.out.println("------------------------------------------------");
				preOrder(root);
				break;
			case 2:
				System.out.print("In-order traversal is as follows: \n");
				System.out.println();
				System.out.printf("%-33s %10s\n", "Name of Country", "GDP Per Capita");
				System.out.println("------------------------------------------------");
				inOrder(root);
				break;
			case 3:
				System.out.print("Post-order traversal is as follows: \n");
				System.out.println();
				System.out.printf("%-33s %10s\n", "Name of Country", "GDP Per Capita");
				System.out.println("------------------------------------------------");
				postOrder(root);
				break;
		}
	}//end traverse
/**
 * Method that prints the contents of the tree in pre-order using recursion.
 * 
 * @param localRoot of type node that indicates the top(root) of the tree
 */
	public void preOrder(Node localRoot) {
		if(localRoot != null) {
			localRoot.displayNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}//end preOrder

	/**
	 * Method that prints the contents of the tree in in-order using recursion.
	 * 
	 * @param localRoot of type node that indicates the top(root) of the tree
	 */
	public void inOrder(Node localRoot) {
		if(localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.displayNode();
			inOrder(localRoot.rightChild);
		}
	}//end inOrder
	
	/**
	 * Method that prints the contents of the tree in post-order using recursion.
	 * 
	 * @param localRoot of type node that indicates the top(root) of the tree
	 */
	public void postOrder(Node localRoot) {
		if(localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.displayNode();
		}
	}//end post order
	
/**
 * Method that will indicate which node is at the top (root) of the tree.
 * 
 * @return root of type Node that is the current root (top) of the tree
 */
	public Node getRoot() {
		return root;
	}

/**
 * Method that will print a given number of countries with the lowest GDP per capita that are in the tree. 
 * 
 * @param c of type int that indicates how many countries are to be printed
 */
	public void printBottom(int c) {
		helperArray.printBottom(c);
	}
	
	/**
	 * Method that will print a given number of countries with the highest GDP per capita that are in the tree. 
	 * 
	 * @param c of type int that indicates how many countries are to be printed
	 */
	public void printTop(int c) {
		helperArray.printTop(c);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//end BinarySearchTree
