package movie.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import movie.com.a.dao.MovieDao;
import movie.com.a.model.MovieDto;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	SqlSession sqlSession;
	String ns = "Movie.";

	//영화예매 추가
	@Override
	public boolean reserve(MovieDto movie) {
		try {
			int reservation = sqlSession.insert(ns + "movieReserveAdd", movie);
			System.out.println(reservation);

			if (reservation > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 아이디 기준 예매리스트
	@Override
	public List<MovieDto> myReserve(String id) {
		List<MovieDto> list = sqlSession.selectList(ns + "myReserve", id);
		System.out.println(list);
		return list;
	}
	
	

}
