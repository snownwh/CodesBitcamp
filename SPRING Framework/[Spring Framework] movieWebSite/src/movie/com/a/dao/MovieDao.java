package movie.com.a.dao;

import java.util.List;

import movie.com.a.model.MovieDto;

public interface MovieDao {

	//영화예매 추가
	public boolean reserve(MovieDto movie);
	
	// 아이디 기준 예매리스트
	public List<MovieDto> myReserve(String id);
}
