package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MovieManager {
	// 크롤링
	public static List<MovieVo> getCGVdata(){
		Document doc;
		
		List<MovieVo> list = new ArrayList<MovieVo>();
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			
			// 영화제목
			Elements titles = doc.select("div.box-contents strong.title");
			
			Elements img = doc.select("div.box-image span.thumb-image img");
			
			Elements likes = doc.select("div.box-contents span.count Strong i");
			
			for (int i = 0; i < 7; i++) {
				Element eTitle = titles.get(i);
				String title = eTitle.text();
				System.out.print(title + " ");
				
				Element eImage = img.get(i);
				System.out.println(eImage.attr("src"));
				
				Element eLike = likes.get(i);
				String sLike = eLike.text().replace(",", "");
				System.out.println(sLike);
				
				int like = Integer.parseInt(sLike);
				
				MovieVo vo = new MovieVo();
				vo.setTitle(eImage.attr("src"));
				vo.setLike(like);
				
				
				list.add(vo);
				System.out.println("이건 리스트에 담긴 이미지 :" +list.get(0).getTitle());
			}
			// like 실시간 변화 가능
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
}
