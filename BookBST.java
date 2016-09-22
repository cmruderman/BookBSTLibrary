import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookBST {
	public Book root;
	public Book newNode;
	public Book ans;
	public static Scanner scanner;
	public int size = 0;
	public String ISBN;
	public String title;
	public String author;
	public String publisher; 
	public String pubYear;
	public int height;
	int i = 0;
	public String[] arrayStr = new String[1023];
	public int count;
	public BookBST(){
		root = null;
		count = 0; //number of items
		height = 0; //height of tree
		
	}
	public void plotArray(String[] array){
		double[] arrayX = new double[array.length];
		double[] arrayY = new double[array.length];
		double xCoord = .5;
		double yCoord = .9;
		arrayX[0] = xCoord; //initiates root xCoord
		arrayY[0] = yCoord;//initiates root yCoord
		double xP = xCoord;// actual parent of node we want to print
		double xPL = xCoord;// left most node of previous level that we want to use as our incrementor 
		int lev = 0; // level we want to use when constructing y array
		int lev2 = 0;//variable that keeps track of the current level that the array for x is on
		StdDraw.setCanvasSize(1080, 720); //sets canvas size to 1080p 
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
		StdDraw.picture(.5, .5, "Polizzi.png"); //puts great background picture 
		StdDraw.setPenColor(Color.BLUE); 
				for(int x=1;i<array.length;x++){ //goes through array, finds child (L or R) and assigns appropriate coordinates in double arrays xArray and yArray
					if(x==1024) 
						break;
						lev = 0;
						int why = x;
						while(why>0){
							why=((int)(why-1)/2); 
							lev++;}
						arrayY[x]=.9-(.1*lev);
						lev2 = lev;
						if((x==(Math.pow(2, lev2)-1)) && x!=1){ //keeps track of left most node of previous level to use as incrementor
							xPL = arrayX[(x-1)/2];} //incrementor
						xP = arrayX[(x-1)/2]; 
						if(x%2==0) //right child
							arrayX[x] = xP +(xPL/2);
						else //left child
							arrayX[x] = xP - (xPL/2);}


	StdDraw.filledCircle(arrayX[0], arrayY[0], .005); //draws root
	for(int p = 1;p<array.length;p++){ //goes through array, if null then does not draw
		if(array[p]!=null){
			StdDraw.filledCircle(arrayX[p], arrayY[p], .005);
			StdDraw.line(arrayX[(p-1)/2],arrayY[(p-1)/2],arrayX[p],arrayY[p]); //connects line from parents to children
		}
	}
}	

	public String[] toBSTArray(){
	    int size = 1024; //ASSUMING THIS IS LESS THAN OR EQUAL TO NUMBER OF NODES IN THE TREE
	    String[] BSTarray = new String [size];
	    makeArray(root, 0, BSTarray); //calls make array using BSTarray
	    return BSTarray; //returns String array
	}

	//helper method called by toBSTArray
	public void makeArray(Book b, int index, String[] BSTarray ) {
	    if (b!= null && index<1023) { //makes sure b is not leaf and index is not greater than size
	        BSTarray[index] = b.getISBN();  //makes String array of ISBN's
	        makeArray(b.left, 2*index+1, BSTarray); //
	        makeArray(b.right, 2*index+2, BSTarray);
	   }
	}



	public void displayArray(String[] array){ // displays array by stepping through array and only printing books in the BST that aren't null
		System.out.println("[i]    ISBN");
		for(int i = 0;i<array.length;i++){
			if(array[i]==null)
				System.out.print("");
			else
				System.out.println(i + "    " + array[i]);
		}	
	}
	
	public int findHeight(Book b) // find height of the BST
	{
	    if(b == null)
	        return -1; // helps avoid null pointer exception

	    int lefth = findHeight(b.left);
	    int righth = findHeight(b.right);

	    if(lefth > righth) // displays biggest height
	        return lefth + 1;
	    else
	        return righth +1;
	}
	
	public int getCount(){
		return count;
	}
	public void InitializeCollection(String filename) throws NoSuchElementException, FileNotFoundException{ //makes the Admin BST by stepping through each line of txt doc
        File file = new File(filename);
        scanner = new Scanner(file);
        size = Integer.parseInt(scanner.nextLine());
        while(scanner.hasNext()){ //while scanner has more elements
      	  String ISBN = scanner.nextLine(); // scan line into String media 
      	  String title = scanner.nextLine(); // scan line into String title 
      	  String author = scanner.nextLine();// scan line into String reference 
      	  String publisher = scanner.nextLine();// scan line into String
      	  String pubYear = scanner.nextLine();
      	  Book newBook = new Book(ISBN,title,author,publisher,pubYear);
      	  this.insertISBN(newBook);
        }scanner.close();}
		
	public void yearBST(Book current,String year) {//makes User BST based on the year of the book (case 1)
		if(current!=null){
			yearBST(current.left, year); //searches left 
			if(current.pubYear.equals(year)){ // find book that has the year in Admin BST
				Book newBook = new Book(current.ISBN, current.title, current.author,current.publisher,current.pubYear);// makes new book with same parameters so that it can be inserted into User BST
				newBook.right=null;
				newBook.left = null;
				this.insertTitle(newBook); //inserts book into User BST sorted by title 
				count++;
			}
			yearBST(current.right, year);//searches right 
		}
	}
	public void binSearchArray(Book b, String[] books){// 
		for(int i = 0;i<books.length;i++){ // checks if book is null, if not then calls bin search 
			if(books[i]==null){}
			else
				binSearch(b,books[i]);
	}
	}
	public void binSearch(Book b, String book){//used for case 5, does the actual searching with Binary algorithm
			if(b!=null){
				binSearch(b.left,book);
				if(b.ISBN.equals(book)) //if book at b is equal to search key
					System.out.println(b.toString());
				else
					binSearch(b.right,book);
			}	
			}

	
	public void pubBST(Book current,String publisher) {//makes User BST based on the year of the book (case 1)
		if(current!=null){
			pubBST(current.left, publisher); //searches left 
			if(current.publisher.equals(publisher)){ // finds book that has the same publisher in Admin BST
				Book newBook = new Book(current.ISBN, current.title, current.author,current.publisher,current.pubYear);// makes new book with same parameters so that it can be inserted into User BST
				newBook.right=null; //makes sure does not have children
				newBook.left = null;//makes sure does not have children
				this.insertTitle(newBook);//inserts book into User BST sorted by title 
				count++;
			}
			pubBST(current.right, publisher);//searches right 
		}
	}
	
	public void recinsertISBN(Book current,Book b) {//insert used for Admin BST 
		if (b.ISBN.compareTo(current.ISBN)<0){ // search left
		 if (current.left==null)// node needs to be inserted
			 current.left= b;
		 else
			 recinsertISBN(current.left,b); // keep searching
		 }
		else if (b.ISBN.compareTo(current.ISBN)>0){// search right
			if (current.right==null)// node needs to be inserted
			 current.right= b;
		 else
			 recinsertISBN(current.right,b);// keep searching
		 }
		}
	public void insertISBN(Book b){ // uses recinsert to make Admin BST
	if (root==null)// node needs to be inserted (case 1)
		root= b;
	else // (case 2)
		recinsertISBN(root,b);// initiate the recursion
	}
	public void recInsertTitle(Book current,Book b) {
		if (b.title.compareTo(current.title)<0){ // search left
		 if (current.left==null)// node needs to be inserted
			 current.left= b;
		 else
			 recInsertTitle(current.left,b); // keep searching
		 }
		else if (b.title.compareTo(current.title)>=0){// search right
		 if (current.right==null)// node needs to be inserted
			 current.right= b;
		 else
			 recInsertTitle(current.right,b);// keep searching
		 }
		}
	public void insertTitle(Book b){ // inserts for User BST 
	if (this.root==null)// node needs to be inserted (case 1)
		this.root= b;
	else // (case 2)
		recInsertTitle(this.root,b);// initiate the recursion
	}
	
	public void displayByTitle(Book curr){ //prints strings in alphabetical order according to title 
		 if (curr!=null){
			 displayByTitle(curr.left); //recursive call for left child
			 System.out.println(curr.toString());// //prints book to string using toString
			 displayByTitle(curr.right); //recursive call for right child
		}
	}

	

	
	
	
	public static void toolMenu(){ //prints interface
		System.out.println("");
		System.out.println("Menu");
		System.out.println("====");
		System.out.println("1-From admin BST database, extract user custom BST database using PubYear");
		System.out.println("2-From admin BST database, extract user custom BST database using Publisher");
		System.out.println("3-Display in-order user BST database");
		System.out.println("4-Convert user BST database into array of ISBN");
		System.out.println("5-Search admin database using ISBN array");
		System.out.println("6-Plot user BST database (via array)");
		System.out.println("0-Exit");
		System.out.println("");
		System.out.print("Command: ");
	}
}


