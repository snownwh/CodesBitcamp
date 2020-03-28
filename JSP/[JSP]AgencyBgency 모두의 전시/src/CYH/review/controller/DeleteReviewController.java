package CYH.review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CYH.ReviewDao;

@WebServlet("/deletereview")
public class DeleteReviewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	String sseq = req.getParameter("seq");
	//	int seq = Integer.parseInt(sseq);
		int seq = Integer.parseInt(req.getParameter("seq"));
		System.out.println("\"수정할\" 리뷰seq는 " + seq);
		
		ReviewDao dao = ReviewDao.getInstance();
		boolean sisS = dao.deleteReview(seq);
		String isS = String.valueOf(sisS);
		
		System.out.println("isS = " + isS);
		
		//resp.sendRedirect("/mypage/myreviewDeleteAf.jsp?isS=" + isS);
		
		req.setAttribute("isS", isS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/mypage/myreviewDeleteAf.jsp");
		dispatcher.forward(req, resp);
	}
	
}
