package movie.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.com.a.dao.MovieDao;
import movie.com.a.model.MovieDto;
import movie.com.a.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieDao movieDao;

	@Override
	public boolean reserve(MovieDto movie) {
		return movieDao.reserve(movie);
	}

	@Override
	public List<MovieDto> myReserve(String id) {
		return movieDao.myReserve(id);
	}
	
	
	
	
}
