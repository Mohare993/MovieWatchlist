package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String imdbID;
	private String Title;
	private Integer Year;
	private String Genre;
	private String Plot;
	private Float imdbRating;
	private String Poster;

	public MovieList(Integer id, String imdbId, String title, Integer year, String genre, String plot, Float imdbRating,
			String poster) {
		super();
		this.id = id;
		this.imdbID = imdbId;
		this.Title = title;
		this.Year = year;
		this.Genre = genre;
		this.Plot = plot;
		this.imdbRating = imdbRating;
		this.Poster = poster;
	}

	public MovieList() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImdbId() {
		return imdbID;
	}

	public void setImdbId(String imdbId) {
		this.imdbID = imdbId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public Integer getYear() {
		return Year;
	}

	public void setYear(Integer year) {
		this.Year = year;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		this.Plot = plot;
	}

	public Float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		this.Poster = poster;
	}

}
