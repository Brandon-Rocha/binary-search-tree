/**
 * ArrayHelp is a helper class for the BinarySearchTree class. It creates an array of nodes that mimics the contents of the tree but in order of GDP per capita
 * rather than by country name.
 * 
 * 
 * @author Brandon Rocha
 *
 */
public class ArrayHelp {
	
	private Node[] array;
	private int nElem;
	
/**
 * Constructor that initializes an array of nodes and number of elements.
 * 	
 * @param max of type int that indicates the maximum size of the array
 */
	public ArrayHelp(int max) {
		array = new Node[max];
		nElem = 0;
	}
	
/**
 * Method that searches for a specific country in the array using the name of the country as the key.
 * 
 * @param searchFor of type String that is the name of the country being searched for
 * @return of type int that indicates the index of the country if it was found in the array, otherwise will return -1
 */
	public int find(String searchFor) {
		String currentName = array[0].countryName;
		
		for(int i = 0; i < nElem; ++i ) {
			currentName = array[i].countryName;
			if (searchFor.equals(currentName)) {
				return i;
			}
		}
		return -1;
	}
	
/**
 * Method that inserts a node into the array, using the GDP per capita to indicate the order of insertion.
 * 	
 * @param cName of type String that is the name of the country to be inserted
 * @param gdp of type double that is the GDP per capita of the country to be inserted
 */
	public void insert(String cName, double gdp) {
		Node newNode = new Node();
		newNode.countryName = cName;
		newNode.gdppc = gdp;
		
		int j;
		
		for (j=0; j < nElem; ++j) {
			if (array[j].gdppc > newNode.gdppc) {
				break;
			}
		}
		for (int k = nElem; k > j; --k) {
			array[k] = array[k-1];
			
		}
		array[j] = newNode;
		nElem++;
	}
	
/**
 * Method that deletes a node from the array at a specific index and shifts the contents of the array as needed. 
 * 	
 * @param indexDelete of type int that indicates the index of the node to be deleted from the array
 */
	public void delete(int indexDelete) {
		
		int j = indexDelete;
		
		if (j < 0) {
			return;
		}
		else {
			for (int k = j; k < nElem; ++k) {
				array[k] = array[k+1];
			}
		nElem--;
		}
		
	}
	
	/**
	 * Method that will print a given number of countries with the lowest GDP per capita that are in the tree. 
	 * 
	 * @param c of type int that indicates how many countries are to be printed
	 */
	public void printBottom(int c) {
		
		System.out.printf("%-33s %10s\n", "Name of Country", "GDP Per Capita");
		System.out.println("------------------------------------------------");
		
		for (int i = 0; i < c; ++i) {
			if(array[i]==null) {
				break;
			}
			array[i].displayNode();
		}
	}
	
	/**
	 * Method that will print a given number of countries with the highest GDP per capita that are in the tree. 
	 * 
	 * @param c of type int that indicates how many countries are to be printed
	 */
	public void printTop(int c) {
		
		System.out.printf("%-33s %10s\n", "Name of Country", "GDP Per Capita");
		System.out.println("------------------------------------------------");
		
		for(int i = nElem-1; i >= 0; -- i) {
			array[i].displayNode();
			--c;
			if(c == 0) {
				break;
			}
		}
	}
	
}
