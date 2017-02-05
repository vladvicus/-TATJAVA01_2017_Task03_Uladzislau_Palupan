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

public class TestSearchDiskLessThanPrice {
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
	public void f(Double price) {
		int counter = 0;

		List<Disk> result = null;
		try {
			
			result = disk.findDisksLessThanPrice(price);
		} catch (DaoException e) {

			e.printStackTrace();
		}
		for (Disk disk : allDisks) {
			if (disk.getPrice() < price) {
				counter++;
			}
		}
		System.out.println(result.size()+" = "+counter);
		Assert.assertEquals(result.size(), counter);
		/*if (!(result.isEmpty())) {
			for (Disk oneDisk : result) {

				Assert.assertTrue(oneDisk.getPrice() < (price));
			}
		} else {
			Assert.assertTrue(result.isEmpty());
		}*/

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1.7 }, new Object[] { 4.0 }, new Object[] { 0.0 }, };
	}
}
