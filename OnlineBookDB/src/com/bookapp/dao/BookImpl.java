package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter{
	PreparedStatement statement=null;

	@Override
	public void addBook(Book book) {
		Connection connection=null;
		try {
			String query="insert into book values(?,?,?,?,?)";
			connection=ModelDAO.openConnection();
			statement=connection.prepareStatement(query);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getCategory());
			statement.setInt(4, book.getBookId());
			statement.setInt(5, book.getPrice());
			
			statement.execute();
				System.out.println("Book added ");
			}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ModelDAO.closeConnection();
		}
		
	}

	@Override
	public boolean deleteBook(int bookId) throws BookNotFoundException {
		//try {
		String query="delete from book where bookId=?";
		Connection connection=null;
		
				try {
					connection=ModelDAO.openConnection();
					statement=connection.prepareStatement(query);
					statement.setInt(1, bookId);
					int  val = statement.executeUpdate();
					
						if(val==0) {
							throw new BookNotFoundException(" Book Id not found");
						}
						else {
							System.out.println("Book deleted");
						}
						
				} catch (SQLException e) {
					
					
						throw new BookNotFoundException("Invalid Book Id");
				}
				finally {
					ModelDAO.closeConnection();
				}
				
				
				
			return true;
	}


	@Override
	public Book getBookById(int bookId) throws BookNotFoundException{
		String query="select * from book where bookId = ?";
		Book book=null;
		Connection connection=null;
		try {
			connection=ModelDAO.openConnection();
			statement=connection.prepareStatement(query);
			statement.setInt(1, bookId);
			ResultSet resultset=statement.executeQuery();
			if(resultset.next()) {
				book=new Book(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4),resultset.getInt(5));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ModelDAO.closeConnection();
		}
		
		if(book == null)
			throw new BookNotFoundException("Book Id mismatch ");
		else
			
			return book;
		
		
	}

	
	
	
	
	@Override 
	public boolean updateBook(int bookId, int price) throws BookNotFoundException{
		String query="update book set price= ? where bookId= ?";
		Connection connection=null;
		int val=0;
		try {
			connection=ModelDAO.openConnection();
			statement=connection.prepareStatement(query);
			statement.setInt(1, price);
			statement.setInt(2, bookId);
			 val=statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			finally {
				if(statement!=null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				ModelDAO.closeConnection();
			}
		if(val==0) {
			throw new BookNotFoundException("Id mismatch please enter a valid book id");
		}
		else
			System.out.println("Update successful");
		return true;
	}
	

	@Override
	public List<Book> getAllBooks() {
		Connection connection=null;
		
		List<Book> booklist=new ArrayList<Book>();
		String query="select * from book;";
		try {
			connection=ModelDAO.openConnection();
			statement=connection.prepareStatement(query);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				Book book=new Book();
			book.setTitle(resultset.getString(1));
			book.setAuthor(resultset.getString(2));
			book.setCategory(resultset.getString(3));
			book.setBookId(resultset.getInt(4));
			book.setPrice(resultset.getInt(5));
			booklist.add(book);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ModelDAO.closeConnection();
		}
		return booklist;
	}

	@SuppressWarnings("finally")
	@Override
	public List<Book> getBookByAuthor(String author) throws AuthorNotFoundException{
		String query="select * from book where Author= ?";
		List<Book> booklist=new ArrayList<Book>();
		
		Connection connection=ModelDAO.openConnection();;
		try {
			statement=connection.prepareStatement(query);
			statement.setString(1, author);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				Book book=new Book();
				book.setTitle(resultset.getString(1));
				book.setAuthor(resultset.getString(2));
				book.setCategory(resultset.getString(3));
				book.setBookId(resultset.getInt(4));
				book.setPrice(resultset.getInt(5));
				booklist.add(book);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ModelDAO.closeConnection();
			if(booklist.isEmpty()) {
				throw new AuthorNotFoundException("Author not found");
			}
				return booklist;
			
		} 
		
	}

	@Override
	public List<Book> getBookByCategory(String category)throws CategoryNotFoundException {
		String query="select * from book where category= ?";
		List<Book> booklist=new ArrayList<Book>();
		Connection connection=ModelDAO.openConnection();
		try{
			statement=connection.prepareStatement(query);
			statement.setString(1, category);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				Book book=new Book();
				book.setTitle(resultset.getString(1));
				book.setAuthor(resultset.getString(2));
				book.setCategory(resultset.getString(3));
				book.setBookId(resultset.getInt(4));
				book.setPrice(resultset.getInt(5));
				booklist.add(book);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ModelDAO.closeConnection();
			if(booklist.isEmpty())
				throw new CategoryNotFoundException("Category not found ");
		}
		return booklist; 
		
	}
	

}
