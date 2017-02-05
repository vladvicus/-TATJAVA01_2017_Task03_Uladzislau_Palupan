package test;

import org.testng.annotations.*;

import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.BookDaoImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.Assert;


public class TestAddBook {

	@Test(dataProvider = "dp")

	public void f(String message) {
		Path datafile = Paths.get("data/units.txt").toAbsolutePath();
		BookDaoImpl book = new BookDaoImpl();

		try {
			book.addBook(message);

			String data = new String(Files.readAllBytes(datafile));
			System.out.println(data);
			Assert.assertTrue(data.contains(message), "Файл не содержит запись");

		} catch (IOException | DaoException e) {

			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "book,Tolkien,Hobbit,299,20,9" },
				new Object[] { "book,null,null,null,null" }, };
	}

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {
	}

}
