import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookApp {
	public static void main(String[] args) throws NoSuchElementException, FileNotFoundException{

		BookBST admin = new BookBST();
		System.out.println("Welcome to Book Collection App (10000 items)");
		System.out.println("=============================================");
		EasyIn easy = new EasyIn();
		admin.InitializeCollection("BookDB.txt"); // makes admin BST
		BookBST user = new BookBST();
		boolean exit = false;
		boolean array = false;
		boolean userTree = false;
		long sT,eT, duration; // variables for time 
		do {
			admin.toolMenu(); // interface for user 
			int option = easy.readInt();
			String blank;
			switch(option){
				case 1: //extract user custom BST database using pubYear
						user = new BookBST();
						System.out.println("Which year are you interested in?");
						Scanner scan = new Scanner(System.in); 
				      	String year = scan.nextLine();
						System.out.println("Begin Subtree extraction for PubYear="+year+" using in-order traversal");
						user.yearBST(admin.root,year);
						System.out.println("Number of Items found= "+ user.getCount() );
						System.out.println("");
						blank = easy.readString(); //scans for blank line so it can prompt again
						userTree=true;
						break;
				case 2: //extract user custom BST database using Publisher, does the same thing for the User BST of pubYear, just with Publisher 
						user = new BookBST();
						System.out.println("Which publisher are you interested in?");
						Scanner scan2 = new Scanner(System.in); 
						String pub = scan2.nextLine();
						System.out.println("Begin Subtree extraction for Publisher="+pub+" using in-order traversal");
						user.pubBST(admin.root, pub);
						System.out.println("Number of Items found= "+user.getCount());
						blank = easy.readString();
						userTree=true;
						break;
				case 3: //display in-order user BST database
						if(userTree==true){ //will not run unless userBST is defined
							System.out.println("List in-order " + user.getCount() + " items sorted by Title");
							user.displayByTitle(user.root); // prints out books by title 
							blank = easy.readString();}
						else
							System.out.println("Please make userBST (Option 1 or 2)");
						break;
				case 4: //Convert user BST database into array of ISBN
						if(userTree==true){ //will not run unless userBST is defined
						String[] answer = user.toBSTArray();// makes user BST array of type String 
						user.displayArray(answer);
						System.out.println("Number of items " + user.getCount() + "; Number of levels " + user.findHeight(user.root));
						blank = easy.readString();
						array = true;}
						else
							System.out.println("Please make userBST (Option 1 or 2)");
						break;
				case 5: //Search admin database using ISBN array
						if(array == true && userTree==true && user.getCount()>0){ //will not run unless userBST is defined, array is defined and count>0
						String[] ANS = user.toBSTArray();// makes user BST array of type String 
						sT = System.currentTimeMillis(); //start time
						user.binSearchArray(admin.root, ANS); //searches for ISBN in AdminBST
						eT = System.currentTimeMillis(); //end time
						duration = (eT-sT); // gets time for search 
						System.out.println("Search time " + duration + "ms");
						blank = easy.readString();}
						else
							System.out.println("Please make userBST (Option 1 or 2) and please make ISBN array (Option 4)");
						break;
				case 6: 
						//Plot user BST database(via array)
						if(array ==true && userTree==true && user.getCount()>0){ //will not run unless userBST is defined, array is defined and count>0
							String[] answer = user.toBSTArray();// makes user BST array of type String 
							user.plotArray(answer); //plots tree given array of ISBN
							blank = easy.readString();
						}
						else
							System.out.println("Please make userBST (Option 1 or 2) and please make ISBN array (Option 4)");
						break;
				case 0: exit = true;
						System.out.println("Goodbye");
						break;
			}
		
		
	}while(!exit);
}
}

