
public class Book {
	public String ISBN;
	public String title;
	public String author;
	public String publisher; 
	public String pubYear;
	public Book left;
	public Book right;
	public Book(String ISBN, String title, String author, String publisher, String pubYear){
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.pubYear = pubYear;
	}
	public String toString(){
		return(getISBN() + " "+ getTitle() + "; " + getAuthor() + "; "+ getPublisher() + "; "+ getPubYear());
	}
	public String getISBN(){
		return ISBN;
	}
	public String getTitle(){
		return title;
	}
	public String getAuthor(){
		return author;
	}
	public String getPublisher(){
		return publisher;
	}
	public String getPubYear(){
		return pubYear;
	}
}
