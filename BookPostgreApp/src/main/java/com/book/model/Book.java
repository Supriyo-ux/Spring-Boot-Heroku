package com.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Book")
@Getter @Setter
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int bookid;
	
	@NotNull
	@Size(min=1, message="BookName should have atleast 1 characters")
	@Column
	private String bookname;
	
	@NotNull
	@Size(min=2, message="AuthorName should have atleast 2 characters")
	@Column
	private String author;
	
	@NotNull
	@Range(min = 1)
	@Column
	private int price;

	
}