package KEC.reserv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import KEC.reserv.dao.ReservDao;
import KSJ.exhibit.dto.ExhibitDto;
import NWH.member.dto.MemberDto;

@WebServlet("/myexhibitiist")
public class myexhibitList extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost ReserveList success");	
		process(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet ReserveList success");
		process(req, resp);
		
	}
	
public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String spageNumber = req.getParameter("pageNumber");
		System.out.println("spageNumber :" + spageNumber);
		
		int pageNumber = 0; 
		
		if(spageNumber != null && !spageNumber.equals("")){
			pageNumber = Integer.parseInt(spageNumber);
		}else {
			pageNumber = 0; 
		}
		
		// singleton
		ReservDao dao = ReservDao.getInstance();		
		
		HttpSession session = req.getSession();
		MemberDto loginuser = (MemberDto)session.getAttribute("loginuser");
		String myId = loginuser.getId();
		
		List<ExhibitDto> list = dao.getMyExhibitList(pageNumber, myId); // list / paging	
		
		int len = 0;
		len = dao.getAllExhibit(myId);
		System.out.println("총 글의 갯수:" + len);

		int listPage = len / 10;	// 예: 22개의 글 -> 3페이지
		if(len % 10 > 0){
			listPage = listPage + 1;
		}

		System.out.println(listPage);
		

        // 데이터(list) 포장
        req.setAttribute("list", list);       
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("listPage", listPage);
        
        
		// list 전송
		req.getRequestDispatcher("/mypage/myexhibit.jsp").forward(req, resp);
	}

}
