package movie.com.a.dao;

import java.util.List;

import movie.com.a.model.MovieDto;

public interface MovieDao {

	public boolean reserve(MovieDto movie);
	
	public List<MovieDto> myReserve(String id);
}
