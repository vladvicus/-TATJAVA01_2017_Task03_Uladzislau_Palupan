package test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.DiskDaoImpl;

public class TestSearchDiskByName {

	String datafile = Paths.get("data/units.txt").toAbsolutePath().toString();
	DiskDaoImpl disk = new DiskDaoImpl();
	Set<Disk> allDisks = null;

	@BeforeTest
	public void beforeTest() {

		try {
			disk.readFile(datafile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		allDisks = disk.getDisks();

	}

	@Test(dataProvider = "dp")
	public void f(String name) {
		int counter = 0;

		List<Disk> result = null;
		try {

			result = disk.findDisksByName(name);
		} catch (DaoException e) {

			e.printStackTrace();
		}
		for (Disk disk : allDisks) {
			if (disk.getName().toLowerCase().equals(name.toLowerCase())
					|| (disk.getName().toLowerCase().contains(name.toLowerCase()))) {

				counter++;
			}

		}
		System.out.println(result.size() + " = " + counter);
		Assert.assertEquals(result.size(), counter);

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "Ruptur" }, new Object[] { "ruptur" },  new Object[] { "music" }};
	}
}
