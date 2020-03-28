package movie.com.a.service;

import java.util.List;

import movie.com.a.model.MovieDto;

public interface MovieService {
	
	public boolean reserve(MovieDto movie);
	
	public List<MovieDto> myReserve(String id);

}
