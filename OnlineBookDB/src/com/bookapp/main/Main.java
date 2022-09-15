package com.bookapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Main {

	public static void main(String[] args) {
		boolean flag=false;
		Scanner scn=new Scanner(System.in);
		BookInter onlinebook=new BookImpl();
		System.out.println("Welcome to Acheron Online Library");
		do {
		System.out.println("choose :-  For Admin 1  or  For Customer 2");
		int user=scn.nextInt();
		switch(user) {
		case 1:
			System.out.println("press 1 for Add book");
			System.out.println("Press 2 for delete book");
			System.out.println("press 3 for Get book by id");
			System.out.println("Press 4 for Update book");
			int adminInput=scn.nextInt();
			scn.nextLine();
				switch(adminInput) {
				case 1:
//						Add Book
					System.out.println("Enter   title ");
					String title=scn.nextLine();
					System.out.println("Enter  author");
					String author=scn.nextLine();
					System.out.println("Enter category");
					String category=scn.nextLine();
					System.out.println("Enter bookid");
					int bookId=scn.nextInt();
					System.out.println("Enter price");
					int price=scn.nextInt();
					Book book=new Book(title,author,category,bookId,price);
					
					onlinebook.addBook(book);
					break;
				case 2:
//					delete book
					
					System.out.println("Enter the book id");
					int bookid=scn.nextInt();
					try {
						onlinebook.deleteBook(bookid);
					} catch (BookNotFoundException e) {
						//e.printStackTrace();
						System.out.println(e.getMessage());
					}
					break;
				case 3:
//					get book
					
					System.out.println("Enter the Book id");
					 bookid=scn.nextInt();
					try {
						System.out.println( onlinebook.getBookById(bookid));
					} catch (BookNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					
					 
					 break;
				case 4:
					System.out.println("Enter price and book Id for update");
					price=scn.nextInt();
					bookid=scn.nextInt();
					
					try {
						onlinebook.updateBook(bookid, price);
					} catch (BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}
			
			break;
		case 2:
			System.out.println("press 1 for get all book details");
			System.out.println("Press 2 for get book by author");
			System.out.println("Press 3 for get book by Category");
			int customerinput=scn.nextInt();
			scn.nextLine();
			switch(customerinput) {
			case 1:
				
				List<Book> booklist=new ArrayList<Book>();
				booklist=onlinebook.getAllBooks();
				for(Book b:booklist) {
					System.out.println(b);
				}
				break;
			case 2:
				
				System.out.println("Enter the author name");
				String author=scn.nextLine();
				booklist=new ArrayList<Book>();
				try {
					booklist=onlinebook.getBookByAuthor(author);
					for(Book b:booklist) {
						System.out.println(b);
					}
				} catch (AuthorNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter the category ");
				String category=scn.nextLine();
				booklist=new ArrayList<Book>();
				try {
					booklist=onlinebook.getBookByCategory(category);
					for(Book b:booklist) {
						System.out.println(b);
					}
				} catch (CategoryNotFoundException e) {
					e.printStackTrace();
				}
				break;
				default:
					System.out.println("Invalid input");
					break;
			}
			
			break;
		default:
			System.out.println("Invalid input");
		}
		System.out.println("Do you want to continue\n1.to continue\n0.to exit");
		int ch=scn.nextInt();
		if(ch==1) {
			flag=true;
		}
		else {
			flag=false;
		}
		}while(flag);
		System.out.println();
		System.out.println("Tank you for visit us");
		scn.close();
		
	}

}
