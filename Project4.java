import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * COP 3530: Project 4 – Binary Search Trees
 * <p>
 *  Project 4 class starts by taking in a file named Countries4.csv, parsing out the information and creating a binary search tree using the country's name 
 *  and calculated GDP per capita. The binary search tree that is created is sorted by name of the country.
 * <p>
 * Once the binary search tree is created, a menu will continually print until the user inputs 9, at which point the program will terminate. 
 * All other numbers between 1 and 8 correspond to different actions to be performed using the binary search tree.  
 * 
 * @author Brandon Rocha
 *@version November 16, 2022
 *
 */
public class Project4 {

	public static void main(String[] args) {
		Scanner openFl = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String filename = openFl.next();
		BinarySearchTree BST = new BinarySearchTree();

		
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e ){
			System.out.println("Unable to open the file: "+filename);
			System.exit(1);
		}
		inFile.useDelimiter(",|\n");
		//get through the first line
		inFile.nextLine();
		String Name;
		double gdp;
		double population;
		double gdppc;
		while(inFile.hasNext()) {
			Name = inFile.next();
			inFile.next(); //Capitol
			population = inFile.nextDouble(); //Population
			gdp = inFile.nextDouble(); //GDP
			inFile.nextLong(); //COVID Cases
			inFile.nextLong(); //COVID Deaths
			String a = inFile.next(); //Area
			
			gdppc = getGdppc(population, gdp);
			BST.insert(Name, gdppc);
			
			/*Name = null;
			population = 0;
			gdp = 0;
			gdppc = 0;*/
				
		} //close while loop
		
		Scanner menuOption = new Scanner(System.in);
		String input = "0";
		while(!input.equals("9")) {
			Project4.printMenu();
			input = menuOption.next();
			System.out.println();
			if (input.equals("1")) { // print in order
				BST.traverse(2);
			}//end option 1
			
			else if (input.equals("2")) { //print pre order
				BST.traverse(1);
			}//end option 2
			
			else if (input.equals("3")) { //print post order
				BST.traverse(3);
			}//end option 3
			
			else if (input.equals("4")) { //insert a country node into the BST using input
				Scanner couNameInput = new Scanner(System.in);
				String inputName;
				double inputGdp;
				System.out.print("Enter the name of the country you would like to insert: ");
				inputName = couNameInput.nextLine();
					while(true) {
						try {
						System.out.print("Enter the GDP per capita of the country you would like to insert: ");
						Scanner couGdpInput = new Scanner(System.in);
						inputGdp = couGdpInput.nextDouble();
						}// end try
						catch(InputMismatchException e) {
							System.out.println();
							System.out.println("Be sure that your input is a number, it may include a decimal. \n");
							continue;
						}//end catch
						break;
				}//end while
					BST.insert(inputName, inputGdp);
					System.out.printf("%s has been inserted with a GDP of: %.3f \n", inputName, inputGdp);
			}//end option 4
			
			else if (input.equals("5")) { //delete a country node from the BST
				Scanner delete = new Scanner(System.in);
				System.out.print("Enter the name of the country you would like to delete: ");
				String deleteName = delete.nextLine();
				BST.delete(deleteName);
			}//end option 5
			
			else if (input.equals("6")) {
				double trueValue = 1;
				while(trueValue == 1) {
				Scanner search = new Scanner(System.in);
				try {
				System.out.print("Enter the name of the country you would like to search for: ");
					String searchName = search.nextLine();
				System.out.println("");
				double resultOsearch = BST.find(searchName);
				if (resultOsearch < 0) {
					System.out.printf("There were no results for your search %s. \n"
							+ "Please note that the search is case sensitive, the first letter of every word should be capitalized. \n"
							+ "\n", searchName);
				}
				break;
				}//end try
				catch(InputMismatchException e) {
					System.out.print("Be sure that your input is a series of letters. \n");
					continue;
				}//end catch
			}//while loop
			
			}//end option 6
			
			else if (input.equals("7")) { //print bottom
				int bottomInput = 0;
				while(true) {
					Scanner bottom = new Scanner(System.in);
					try {
						System.out.print("Enter the number of countries you would like to print: ");
						bottomInput = bottom.nextInt();
					System.out.println("");
					break;
					}
					catch(InputMismatchException e) {
						System.out.println("");
						System.out.print("Be sure that your input is an integer and try again. \n");
						System.out.println("");
						continue;
					}
				}
				BST.printBottom(bottomInput);
			}//end option 7
			
			else if (input.equals("8")) { //print top
				int topInput = 0;
				while(true) {
					Scanner top = new Scanner(System.in);
					try {
						System.out.print("Enter the number of countries you would like to print: ");
						topInput = top.nextInt();
					System.out.println("");
					break;
					}
					catch(InputMismatchException e) {
						System.out.println("");
						System.out.print("Be sure that your input is an integer and try again. \n");
						System.out.println("");
						continue;
					}
				}
				BST.printTop(topInput);
			}//end option 8
			
			else if (input.equals("9")) {
				break;
			}//end option 9
			else {
				System.out.println("That is not a valid menu option, please enter a number between 1 and 8 or 9 to quit.");
			}
			System.out.println();
		}//end of while loop
		System.out.println("Goodbye!");
		openFl.close();
		menuOption.close();
		inFile.close();
	}//end of main
	
	public static double getGdppc(double population, double gdp) {
		double gdppc = gdp/population;
		return gdppc;
	}
	/**
	 * This method is called to print a menu, each option allowing a different action to be performed or 9 to exit the program. 
	 * 
	 */
	public static void printMenu () {
		System.out.print("1) Print tree inorder\r\n"
				+ "2) Print tree preorder\r\n"
				+ "3) Print tree postorder\r\n"
				+ "4) Insert a country with name and GDP per capita\r\n"
				+ "5) Delete a country for a given name\r\n"
				+ "6) Search and print a country and its path for a given name.\r\n"
				+ "7) Print bottom countries regarding GDPPC\r\n"
				+ "8) Print top countries regarding GDPPC\r\n"
				+ "9) Exit\n"
				+ "Enter a menu option: ");
	} //end of printMenu
}//end of Project3