package test;

import org.testng.annotations.Test;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.BookDaoImpl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestSearchBookLessThanPrice {
	@Test(dataProvider = "dp")
	public void f(Double price) {

		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		BookDaoImpl book = new BookDaoImpl();
		List<Book> result = null;
		try {
			result = book.findBooksLessThenPrice(price);
		} catch (DaoException e) {

			e.printStackTrace();
		}

		if (!(result.isEmpty())) {
			for (Book oneBook : result) {

				Assert.assertTrue(oneBook.getPrice() < (price));
			}
		} else {
			Assert.assertTrue(result.isEmpty());
		}

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 12.7 }, new Object[] { 20.5 }, new Object[] { 0.0 }, };
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
