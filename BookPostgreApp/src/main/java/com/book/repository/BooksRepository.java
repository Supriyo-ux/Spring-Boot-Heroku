package com.book.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.book.model.Book;

public interface BooksRepository extends CrudRepository<Book, Integer> {
	
	//@Query("SELECT book FROM Book book WHERE book.bookname = ?1")
	// Book findbookByBookName(String name);
	//query using like
	@Query(value="select * from Book book where book.bookname like %:bookname%" , nativeQuery=true)  
	Iterable<Book> findbookByBookName(@Param("bookname") String bookname);
	
	@Query(value="select * from Book book where book.author like %:author%" , nativeQuery=true)  
	Iterable<Book> findbookByAuthor(@Param("author") String author);
	

}


