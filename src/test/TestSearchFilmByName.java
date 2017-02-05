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

public class TestSearchFilmByName {
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
	public void f(String name) {
		int counter = 0;

		List<Film> result = null;
		try {

			result = film.findFilmsByName(name);
		} catch (DaoException e) {

			e.printStackTrace();
		}
		for (Film film : allFilms) {
			if (film.getName().toLowerCase().equals(name.toLowerCase())
					|| (film.getName().toLowerCase().contains(name.toLowerCase()))) {

				counter++;
			}

		}
		System.out.println(result.size() + " = " + counter);
		Assert.assertEquals(result.size(), counter);

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] {"Armagedon"}, new Object[] {""}, new Object[] {"mile"}, };
	}
}
