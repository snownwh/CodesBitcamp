package movie.com.a.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import movie.com.a.model.MovieDto;
import movie.com.a.service.MovieService;

@Controller
public class MovieController {
	
	private Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieService movieService;
	
	// 영화전체리스트(CGV에서 크롤링으로 제목, 포스터 좋아요 데이터 가지고 옴)
	@RequestMapping(value="movielist.do", method={RequestMethod.GET, RequestMethod.POST})
	public String getMovieList(Model model) {
		Document doc;
		
		List<MovieDto> list = new ArrayList<MovieDto>();
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			
			
			//영화제목
			Elements titles = doc.select("div.box-contents strong.title");
			//영화포스터
			Elements img = doc.select("div.box-image span.thumb-image img");
			//영화 좋아요
			Elements likes = doc.select("div.box-contents span.count Strong i");
	
			
			
			for(int i = 0; i < 7; i++) {
				Element eTitle = titles.get(i);
				String title = eTitle.text();
				System.out.println(title+" ");
				
				Element eImage = img.get(i);
				System.out.println(eImage.attr("src")+" ");
				
				Element eLike = likes.get(i);
				String sLike = eLike.text();
				System.out.println(sLike);
				
				Element cgvMovieSeq = img.get(i);
				System.out.println("cgvMovieSeq: " +cgvMovieSeq.attr("src").substring(cgvMovieSeq.attr("src").length()-13, cgvMovieSeq.attr("src").length()-8));
				
				
				
				MovieDto dto = new MovieDto();
				dto.setTitle(title);
				dto.setMovieImg(eImage.attr("src"));
				dto.setMovieLike(sLike);
				dto.setCgvSeq(cgvMovieSeq.attr("src").substring(cgvMovieSeq.attr("src").length()-13, cgvMovieSeq.attr("src").length()-8));
				
				list.add(dto);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("movielist", list);
		
		return "movie/movielist";
	}
		
	// 영화디테일(CGV에서 크롤링으로 제목, 포스터 좋아요, 감독, 배우 데이터 가지고 옴)
	@RequestMapping(value="moviedetail.do", method={RequestMethod.GET, RequestMethod.POST})
	public String movieDetail(String cgvSeq, Model model) {
		Document doc, doc1;
		
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/detail-view/?midx="+cgvSeq).get();
			
			
			//영화제목
			Elements titles = doc.select("div.sect-base-movie h3 strong");
			//영화포스터
			Elements img = doc.select("div.box-image span.thumb-image img");
			//영화 좋아
			Elements likes = doc.select("div.box-contents span.count Strong i");
			
			// 감독, 배우
			Elements person = doc.select("div.spec dl dd a");
			
			//감독
			String director = person.get(0).text();
			System.out.println("director: " + director);
			//배우
			String actor = person.get(1).text();
			System.out.println("actor: " + actor);
			
		
			String title = titles.text();
			System.out.println(title+" ");
			
			String movieImg = img.attr("src");
			System.out.println(movieImg);
			
			String like = likes.text();
			System.out.println(like);
			
			MovieDto dto = new MovieDto();
			dto.setTitle(title);
			dto.setMovieImg(movieImg);
			dto.setMovieLike(like);
			dto.setCgvSeq(cgvSeq);
			dto.setDirector(director);
			dto.setActor(actor);
			
			model.addAttribute("moviedetail", dto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "movie/movie_detail";
	}
	
	//영화선택 뷰(CGV에서 크롤링으로 제목, 포스터 좋아요 데이터 가지고 옴)
	@RequestMapping(value="movieSelect.do", method={RequestMethod.GET, RequestMethod.POST})
	public String movieSelect(String cgvSeq, Model model) {
Document doc;
		
		List<MovieDto> list = new ArrayList<MovieDto>();
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			
			
			//영화제목
			Elements titles = doc.select("div.box-contents strong.title");
			//영화포스
			Elements img = doc.select("div.box-image span.thumb-image img");
			//영화 좋아
			Elements likes = doc.select("div.box-contents span.count Strong i");
	
			
			
			for(int i = 0; i < 7; i++) {
				Element eTitle = titles.get(i);
				String title = eTitle.text();
				System.out.println(title+" ");
				
				Element eImage = img.get(i);
				System.out.println(eImage.attr("src")+" ");
				
				Element eLike = likes.get(i);
				String sLike = eLike.text();
				System.out.println(sLike);
				
				Element cgvMovieSeq = img.get(i);
				System.out.println("cgvMovieSeq: " +cgvMovieSeq.attr("src").substring(cgvMovieSeq.attr("src").length()-13, cgvMovieSeq.attr("src").length()-8));
				
				
				
				MovieDto dto = new MovieDto();
				dto.setTitle(title);
				dto.setMovieImg(eImage.attr("src"));
				dto.setMovieLike(sLike);
				dto.setCgvSeq(cgvMovieSeq.attr("src").substring(cgvMovieSeq.attr("src").length()-13, cgvMovieSeq.attr("src").length()-8));
				
				list.add(dto);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("movielist", list);
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/detail-view/?midx="+cgvSeq).get();
			
			
			//영화제목
			Elements titles = doc.select("div.sect-base-movie h3 strong");
			//영화포스터
			Elements img = doc.select("div.box-image span.thumb-image img");
			//영화 좋아
			Elements likes = doc.select("div.box-contents span.count Strong i");
			
			// 감독, 배우
			Elements person = doc.select("div.spec dl dd a");
			
			//감독
			String director = person.get(0).text();
			System.out.println("director: " + director);
			//배우
			String actor = person.get(1).text();
			System.out.println("actor: " + actor);
			
		
			String title = titles.text();
			System.out.println(title+" ");
			
			String movieImg = img.attr("src");
			System.out.println(movieImg);
			
			String like = likes.text();
			System.out.println(like);
			
			MovieDto dto = new MovieDto();
			dto.setTitle(title);
			dto.setMovieImg(movieImg);
			dto.setMovieLike(like);
			dto.setCgvSeq(cgvSeq);
			dto.setDirector(director);
			dto.setActor(actor);
			
			model.addAttribute("moviedetail", dto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "movie/movieSelectOne";
	}
	
	//영화예매 추가
	@RequestMapping(value="movieReserveAdd.do", method={RequestMethod.GET, RequestMethod.POST})
	public String movieReserve(MovieDto movie, Model model) {
		System.out.println(movie.toString());
		
		boolean isS = movieService.reserve(movie);
		System.out.println("리저드에드 불린: " + isS);
		
		return "movie/movieReserveAf";
	}
	
	// 아이디 기준 예매리스트
	@RequestMapping(value="movieMyReserve.do", method={RequestMethod.GET, RequestMethod.POST})
	public String movieMyReserve(String id, Model model) {
		
		List<MovieDto> list = movieService.myReserve(id);
		System.out.println("뮤비컨트롤러에서 리스트: "+ list);
		
		model.addAttribute("myReserve", list);
		
		return "movie/movieReserv";
	}
	
	
}
