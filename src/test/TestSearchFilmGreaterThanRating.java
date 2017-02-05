package test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.FilmDaoImpl;

public class TestSearchFilmGreaterThanRating {
	String datafile = Paths.get("data/units.txt").toAbsolutePath().toString();
	FilmDaoImpl film = new FilmDaoImpl();
	Set<Film> allFilms = null;

	@BeforeTest
	public void beforeTest() {

		try {
			film.readFile(datafile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		allFilms = film.getFilms();

	}

	@Test(dataProvider = "dp")
	public void f(Integer rating) {
		int counter = 0;

		List<Film> result = null;
		try {
			
			result = film.findFilmsGreaterThanRating(rating);
		} catch (DaoException e) {

			e.printStackTrace();
		}
		for (Film film : allFilms) {
			if (film.getRating() > rating) {
				counter++;
			}
		}
		System.out.println(result.size()+" = "+counter);
		Assert.assertEquals(result.size(), counter);
		

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 10 }, new Object[] { 0 }, new Object[] { 7 }, };
	}
}
