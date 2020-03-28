package KSJ.exhibit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KSJ.exhibit.dao.ExhibitDao;
import KSJ.exhibit.dto.ExhibitDto;
import KSJ.files.dao.FilesDao;
import KSJ.files.dto.FilesDto;

@WebServlet("/exhibitcuratordetail")
public class ExhibitCuratorDetail extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sseq = req.getParameter("seq");
		
		int seq = Integer.parseInt(sseq);
		ExhibitDao edao = ExhibitDao.getInstance();
		ExhibitDto edto = edao.getExDetail(seq);
		
		int bbs_seq = edto.getSeq();
		int file_seq = 1;
		
		FilesDao fdao = FilesDao.getInstance();
		FilesDto fdto = fdao.getTitleImg(bbs_seq, file_seq);
		
		
		req.setAttribute("edto", edto);
		req.setAttribute("fdto", fdto);
		System.out.println("fdto:"+fdto) ;
		System.out.println("edto:"+edto) ;
		
		req.getRequestDispatcher("./mypage/curatorwriteDetail.jsp").forward(req, resp);;
		
		
	
		
	}

	
	
}
