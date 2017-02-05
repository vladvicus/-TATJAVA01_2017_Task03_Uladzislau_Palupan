package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Disk;

import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiskDaoImpl implements DiskDao {
	private Set<Disk> disks = new HashSet<>();
	String datafile = Paths.get("data/units.txt").toAbsolutePath().toString();

	public Set<Disk> getDisks() {
		return disks;
	}

	public void setDisks(Set<Disk> disks) {
		this.disks = disks;
	}

	@Override
	public void addDisk(String disk) throws DaoException {
		FileWriter wr = null;
		try {
			wr = new FileWriter(datafile, true);
			wr.append("\n" + disk);
			wr.flush();
			wr.close();
		} catch (IOException e) {

			throw new DaoException();
		}

	}

	@Override
	public List<Disk> findDisksLessThanPrice(Double price) throws DaoException {

		System.out.println("Price-->" + price);
		List<Disk> disksFoundByPrice = new ArrayList<>();

		try {
			readFile(datafile);

		} catch (IOException e) {

			throw new DaoException("error in findDisksLessThenPrice method");
		}
		for (Disk oneDisk : disks) {
			if (oneDisk.getPrice() < (price)) {
				disksFoundByPrice.add(oneDisk);
			}
		}

		return disksFoundByPrice;

	}

	@Override
	public List<Disk> findDisksByName(String name) throws DaoException {

		System.out.println("Name-->" + name);
		List<Disk> disksFoundByName = new ArrayList<>();

		try {
			readFile(datafile);

		} catch (IOException e) {
			// e.printStackTrace();
			throw new DaoException(e);
		}
		for (Disk oneDisk : disks) {
			if (oneDisk.getName().toLowerCase().equals(name.toLowerCase()) || 
					(oneDisk.getName().toLowerCase().contains(name.toLowerCase()))) {
				disksFoundByName.add(oneDisk);
			}
		}
		System.out.println("The list of disks with name:" + name);

		return disksFoundByName;
	}

	public Set<Disk> readFile(String fname) throws IOException {

		FileInputStream fis = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			for (int i = 0; i < data.length; i++) {
				data[i] = data[i].trim();

			}
			if (data[0].startsWith("disk")) {

				String type = data[1];
				String name = data[2];
				Integer year = Integer.parseInt(data[3]);
				Double price = Double.parseDouble(data[4]);
				disks.add(new Disk(type, name, year, price));

			} else
				continue;

		}
		br.close();

		System.out.println("Disks are suscessfully loaded from file!");
		return disks;
	}

}
