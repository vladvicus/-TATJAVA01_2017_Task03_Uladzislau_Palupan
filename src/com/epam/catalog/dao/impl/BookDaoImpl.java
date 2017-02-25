package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.view.Main;

import java.io.*;
import java.util.*;

public class BookDaoImpl implements BookDao {

	private Set<Book> books = new HashSet<>();


	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public void addBook(String book) throws DaoException {
		FileWriter wr = null;
		try {
			wr = new FileWriter(Main.datafile, true);
			wr.append("\n" + book);
			wr.flush();
			wr.close();
		} catch (IOException e) {

			throw new DaoException();
		}

	}

	@Override
	public List<Book> findBooksByAuthor(String author) throws DaoException {
		System.out.println("AUTHOR-->" + author);
		List<Book> booksFoundByAuthorName = new ArrayList<>();
		author = author.toLowerCase();
		try {
			readFile(Main.datafile);

		} catch (IOException e) {
			// e.printStackTrace();
			throw new DaoException(e);
		}
		for (Book oneBook : books) {

			if (oneBook.getAuthor().toLowerCase().equals(author.toLowerCase())
					|| (oneBook.getAuthor().toLowerCase().contains(author.toLowerCase()))) {
				booksFoundByAuthorName.add(oneBook);
			}
		}
		System.out.println("The list of books with author:" + author);
		if (booksFoundByAuthorName.isEmpty()) {
			return Collections.emptyList();
		} else {

			return booksFoundByAuthorName;
		}
	}

	public Set<Book> readFile(String fname) throws IOException {

		FileInputStream fis = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			for (int i = 0; i < data.length; i++) {
				data[i] = data[i].trim();

			}
			if (data[0].startsWith("book")) {

				String name = data[1];
				String author = data[2];
				Integer page = Integer.parseInt(data[3]);
				Double price = Double.parseDouble(data[4]);
				books.add(new Book(name, author, page, price));

			} else
				continue;

		}
		br.close();

		System.out.println("Books are suscessfully loaded from file!");
		return books;
	}

	@Override
	public List<Book> findBooksLessThenPrice(Double price) throws DaoException {
		System.out.println("Price-->" + price);
		List<Book> booksFoundByPrice = new ArrayList<>();

		try {
			readFile(Main.datafile);

		} catch (IOException e) {

			throw new DaoException("error in findBooksLessThenPrice method");
		}
		for (Book oneBook : books) {
			if (oneBook.getPrice() < (price)) {
				booksFoundByPrice.add(oneBook);
			}
		}

		return booksFoundByPrice;

	}

}
