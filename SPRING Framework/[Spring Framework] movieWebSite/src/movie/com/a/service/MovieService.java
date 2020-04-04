package movie.com.a.service;

import java.util.List;

import movie.com.a.model.MovieDto;

public interface MovieService {
	
	//영화예매 추가
	public boolean reserve(MovieDto movie);
	
	// 아이디 기준 예매리스트
	public List<MovieDto> myReserve(String id);

}
