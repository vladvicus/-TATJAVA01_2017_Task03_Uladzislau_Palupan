package com.epam.catalog.bean;

import java.util.Scanner;

public class Disk {

	static Scanner in = new Scanner(System.in);
	private String type;
	private String name;
	private Integer year;
	private Double price;

	public Disk(String type, String name, Integer year, Double price) {
		this.type = type;
		this.name = name;
		this.year = year;
		this.price = price;
	}

	public Disk() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disk other = (Disk) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disk{" + "type='" + type + '\'' + ", name='" + name + '\'' + ", year=" + year + ", price=" + price
				+ '}';
	}

	public Disk makeDisk() {

		System.out.println("Input Disk's type");
		if (in.hasNextLine()) {
			this.type = in.nextLine();
		}
		System.out.println("Input Disk's name");
		if (in.hasNextLine()) {
			this.name = in.nextLine();
		}

		System.out.println("Input Disk's year (Integer)");
	
		this.year=inputInt();
		System.out.println("Input Disks' price(Double)");

		
			this.price = inputDouble();
	

		return new Disk(type, name, year, price);

	}

	Double inputDouble() {
		Double x;
		while (!in.hasNextDouble()) {
			System.out.println("You input not an double number");
			System.out.println("Input an double number");

			in = new Scanner(System.in);
		}
		x = in.nextDouble();
		return x;
	}

	int inputInt() {
		int x = 0;

		while (!in.hasNextInt()) {
			System.out.println("You input not an integer number");
			System.out.println("Input an integer number");

			in = new Scanner(System.in);
		}
		x = in.nextInt();
		return x;
	}
}