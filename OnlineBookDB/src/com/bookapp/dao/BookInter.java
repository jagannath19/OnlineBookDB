package com.bookapp.dao;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public interface BookInter {
//	for admin
	void addBook(Book book);
	boolean deleteBook(int bookId) throws BookNotFoundException;
	Book getBookById(int bookId) throws BookNotFoundException;
	boolean updateBook(int bookId,int price) throws BookNotFoundException;
	
//	for customer
	List<Book> getAllBooks();
	List<Book> getBookByAuthor(String author)throws AuthorNotFoundException;
	List<Book> getBookByCategory(String category)throws CategoryNotFoundException;
	
}
