package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import movie.com.a.model.MovieDto;

public class test {
	
	public static void main(String[] args) {
		Document doc;
		
		List<MovieDto> list = new ArrayList<MovieDto>();
		
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			
			//영화제목
			Elements titles = doc.select("div.box-contents strong.title");
			Elements img = doc.select("div.box-image span.thumb-image img");
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
				
//				int like = Integer.parseInt(sLike);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
