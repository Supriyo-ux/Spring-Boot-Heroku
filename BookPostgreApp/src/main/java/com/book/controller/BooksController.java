package com.book.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.BookApplication;
import com.book.model.Book;
import com.book.service.BooksService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BooksController {

	@Autowired
	BooksService booksService;

	@GetMapping("/getAllBooks")
	public Resources<Book> getAllBooks() {
		Iterable<Book> book = booksService.getAllBooks();
		Resources<Book> resource =  new Resources < > (book);

		hateoasLinkBooks(resource);
		return resource;
	}

	@GetMapping("/bookDetails/{bookid}")
	public Resource<Book> getBooks(@PathVariable("bookid") int bookid) {

		Book book = booksService.getBooksById(bookid);

		Resource<Book> resource = new Resource<Book>(book);

		hateoasLink(resource);
		return resource;

	}

	@GetMapping("/bookDetailsbyName/{bookname}")
	public Resources<Book> getBooksbyName(@PathVariable("bookname") String bookName) {
		Iterable<Book> book = booksService.getBooksByBookName(bookName);
		Resources<Book> resource = new Resources < > (book);

		hateoasLinkBooks(resource);
		return resource;
	}

	@GetMapping("/bookDetailsbyAuthor/{authorName}")
	public Resources<Book> getBooksbyAuthorName(@PathVariable("authorName") String author) {
		Iterable<Book> book = booksService.getBooksByAuthorName(author);
		Resources<Book> resource = new Resources < > (book);

		hateoasLinkBooks(resource);
		return resource;
	}

	@DeleteMapping("/deleteBook/{bookid}")
	public Resource<Book> deleteBook(@PathVariable("bookid") int bookid) {
		Book book = booksService.delete(bookid);
		log.info("Book Deleted Successfully");
		Resource<Book> resource = new Resource<Book>(book);

		hateoasLink(resource);
		return resource;
	}

	@PostMapping("/createBook")
	public Resource<Book> saveBook(@Valid @RequestBody Book books) {
		Book book = booksService.saveOrUpdate(books);
		log.info("Book Saved Sucessfully");
		Resource<Book> resource = new Resource<Book>(book);

		hateoasLink(resource);
		return resource;
	}

	@PutMapping("/updateBook/{bookid}")
	public Resource<Book> update(@Valid @RequestBody Book books, @PathVariable("bookid") int bookid) {
		// booksService.saveOrUpdate(books);
		Book book = booksService.update(books, bookid);
		log.info("Book Updated Sucessfully");
		Resource<Book> resource = new Resource<Book>(book);

		hateoasLink(resource);
		return resource;
	}

	public void hateoasLink(Resource<Book> resource) {

		log.info("Link Creation Start For Book Pojo Scenario");
		Book book = null;
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBooks());
		ControllerLinkBuilder linkToBookName = linkTo(methodOn(this.getClass()).getBooksbyName("Java"));
		ControllerLinkBuilder linkToAuthor = linkTo(methodOn(this.getClass()).getBooksbyAuthorName("Brian"));
		ControllerLinkBuilder linkTogetBook = linkTo(methodOn(this.getClass()).getBooks(1));
		ControllerLinkBuilder linkToupdateBook = linkTo(methodOn(this.getClass()).update(book, 1));
		ControllerLinkBuilder linkTocreateBook = linkTo(methodOn(this.getClass()).saveBook(book));
		ControllerLinkBuilder linkTodeleteBook = linkTo(methodOn(this.getClass()).deleteBook(1));

		resource.add(linkTo.withRel("all-books"));
		resource.add(linkToBookName.withRel("search-by-bookName"));
		resource.add(linkToAuthor.withRel("search-by-authorName"));
		resource.add(linkTogetBook.withRel("bookDetails-by-id"));
		resource.add(linkToupdateBook.withRel("bookDetails-update-by-id"));
		resource.add(linkTocreateBook.withRel("bookDetails-add"));
		resource.add(linkTodeleteBook.withRel("bookDetails-delete-by-id"));
	}

	public void hateoasLinkBooks(Resources<Book> resource) {

		log.info("Link Creation Start For Book Collection Scenario");
		Book book = null;
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBooks());
		ControllerLinkBuilder linkToBookName = linkTo(methodOn(this.getClass()).getBooksbyName("Java"));
		ControllerLinkBuilder linkToAuthor = linkTo(methodOn(this.getClass()).getBooksbyAuthorName("Brian"));
		ControllerLinkBuilder linkTogetBook = linkTo(methodOn(this.getClass()).getBooks(1));
		ControllerLinkBuilder linkToupdateBook = linkTo(methodOn(this.getClass()).update(book, 1));
		ControllerLinkBuilder linkTocreateBook = linkTo(methodOn(this.getClass()).saveBook(book));
		ControllerLinkBuilder linkTodeleteBook = linkTo(methodOn(this.getClass()).deleteBook(1));

		resource.add(linkTo.withRel("all-books"));
		resource.add(linkToBookName.withRel("search-by-bookName"));
		resource.add(linkToAuthor.withRel("search-by-authorName"));
		resource.add(linkTogetBook.withRel("bookDetails-by-id"));
		resource.add(linkToupdateBook.withRel("bookDetails-update-by-id"));
		resource.add(linkTocreateBook.withRel("bookDetails-add"));
		resource.add(linkTodeleteBook.withRel("bookDetails-delete-by-id"));
	}

}
