package com.epam.catalog.bean;

import java.util.Scanner;

public class Film {
	static Scanner in = new Scanner(System.in);
    private String name;
    private String country;
    private Integer year;
    private Integer rating;
    
    
	public Film() {
		super();
	}
	public Film(String name, String country, Integer year, Integer rating) {
		super();
		this.name = name;
		this.country = country;
		this.year = year;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Film makeFilm() {
		
		System.out.println("Input Film's name");
		if (in.hasNextLine()) {
			this.name = in.nextLine();
		}
		System.out.println("Input Film's country");
		if (in.hasNextLine()) {
			this.country = in.nextLine();
		}

		System.out.println("Input Film's year (Integer)");
			this.year = inputInt();
	
		System.out.println("Input Films' rating(Integer)");

		this.rating=inputInt();

		return new Film(name,country, year, rating);

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
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
		Film other = (Film) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
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
		return "Film [name=" + name + ", country=" + country + ", year=" + year + ", rating=" + rating + "]";
	}

}
