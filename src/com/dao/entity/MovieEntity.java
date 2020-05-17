package com.dao.entity;

import java.sql.Timestamp;

public class MovieEntity {

	private int mid;
	private String name;
	private int year;
	private int rating;
	private long budget;
	private int teams;
	private Timestamp timestamp;

	public MovieEntity(int mid, String name, int year, int rating, long budget, int teams, Timestamp timestamp) {
		super();
		this.mid = mid;
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.budget = budget;
		this.teams = teams;
		this.timestamp = timestamp;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public int getTeams() {
		return teams;
	}

	public void setTeams(int teams) {
		this.teams = teams;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "MovieEntity [mid=" + mid + ", name=" + name + ", year=" + year + ", rating=" + rating + ", budget="
				+ budget + ", teams=" + teams + ", timestamp=" + timestamp + "]";
	}

}
