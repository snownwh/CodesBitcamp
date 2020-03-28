package movie.com.a.model;

import java.io.Serializable;

public class MovieDto implements Serializable{
	private int seq;
	private String id;
	private String title;
	private String movieImg;
	private String movieLike;
	private String cgvSeq;
	private String director;
	private String actor;
	private String movieLocation;
	private String movieTime;
	
	
	public MovieDto() {
	}


	public MovieDto(String id, String title, String movieImg, String movieLike, String cgvSeq, String director,
			String actor, String movieLocation, String movieTime) {
		super();
		this.id = id;
		this.title = title;
		this.movieImg = movieImg;
		this.movieLike = movieLike;
		this.cgvSeq = cgvSeq;
		this.director = director;
		this.actor = actor;
		this.movieLocation = movieLocation;
		this.movieTime = movieTime;
	}


	public MovieDto(int seq, String id, String title, String movieImg, String movieLike, String cgvSeq, String director,
			String actor, String movieLocation, String movieTime) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.movieImg = movieImg;
		this.movieLike = movieLike;
		this.cgvSeq = cgvSeq;
		this.director = director;
		this.actor = actor;
		this.movieLocation = movieLocation;
		this.movieTime = movieTime;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMovieImg() {
		return movieImg;
	}


	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}


	public String getMovieLike() {
		return movieLike;
	}


	public void setMovieLike(String movieLike) {
		this.movieLike = movieLike;
	}


	public String getCgvSeq() {
		return cgvSeq;
	}


	public void setCgvSeq(String cgvSeq) {
		this.cgvSeq = cgvSeq;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getActor() {
		return actor;
	}


	public void setActor(String actor) {
		this.actor = actor;
	}


	public String getMovieLocation() {
		return movieLocation;
	}


	public void setMovieLocation(String movieLocation) {
		this.movieLocation = movieLocation;
	}


	public String getMovieTime() {
		return movieTime;
	}


	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}


	@Override
	public String toString() {
		return "MovieDto [seq=" + seq + ", id=" + id + ", title=" + title + ", movieImg=" + movieImg + ", movieLike="
				+ movieLike + ", cgvSeq=" + cgvSeq + ", director=" + director + ", actor=" + actor + ", movieLocation="
				+ movieLocation + ", movieTime=" + movieTime + "]";
	}

	
	
	

	

	

	
	

	
	
	
}
