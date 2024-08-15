/**
 * Class that creates a node that is to be used to create a binary search tree. 
 * <p>
 * Fields include variables that represent: the country's name and GDP per capita. 
 * There are also nodes that indicate a left and right child. 
 * 
 * @author Brandon Rocha
 *
 */
public class Node {
	
	public String countryName;
	public double gdppc;
	public Node leftChild;
	public Node rightChild;
	
/**
 * Method that allows a node's data to be printed. Which would include a country name and GDP per capita.
 * 	
 */
	public void displayNode() {
		System.out.printf("%-35s %12.3f\n", countryName, gdppc);
	}

}
