package test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.BookDaoImpl;

public class TestSearchBookByAuthor {

	@Test(dataProvider = "dp")
	public void f(String author) {
		
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		BookDaoImpl book = new BookDaoImpl();
		List<Book> result = null;
		try {
			result = book.findBooksByAuthor(author);
		} catch (DaoException e) {

			e.printStackTrace();
		}
		
		if (!(result.isEmpty())) {
			for (Book oneBook : result) {
				System.out.println("-------------------------");
				System.out.println(oneBook.getAuthor().equalsIgnoreCase(author));
				System.out.println((oneBook.getAuthor().contains(author)));

				Assert.assertTrue(
						oneBook.getAuthor().equalsIgnoreCase(author) || (oneBook.getAuthor().contains(author)));
			}
		} else {
			Assert.assertTrue(result.isEmpty());
		}

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "Tolkien" }, new Object[] { "hgfNoah" }, };
	}
}
