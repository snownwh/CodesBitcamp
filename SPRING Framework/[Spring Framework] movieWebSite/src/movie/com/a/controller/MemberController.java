package movie.com.a.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import movie.com.a.model.MemberDto;
import movie.com.a.service.MemberService;

@Controller
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired // 의존성 Dependency Injection(DI) 스프링이 클래스를 찾아준다.
	MemberService memberService;
	
	// 메인페이지
	@RequestMapping(value="mainPage.do", method={RequestMethod.GET, RequestMethod.POST})
	public String mainPage() {
	
		return "/main/main";
	}
	
	
	@RequestMapping(value="allmember.do", method=RequestMethod.GET)
	public String allmember(Model model) {
		logger.info("MemberController allmember " + new Date());
		
		List<MemberDto> list = memberService.allMember();
		
		model.addAttribute("memlist", list);
		
		return "allMember";
	}
	
	// 중복체크
	@ResponseBody
	@RequestMapping(value="idCheck.do", produces="application/String; charset=utf-8", method={RequestMethod.GET, RequestMethod.POST})
	public String idCheck(MemberDto mem) {
		logger.info("MemberController idCheck " + new Date());
		
		logger.info("id: "+ mem.getId());
		
		boolean isS = memberService.idCheck(mem.getId());
		logger.info("isS: "+ isS);
		
		return isS+"";
	}
	
	// 회원가입
	@RequestMapping(value="join.do", produces="application/String; charset=utf-8", method={RequestMethod.GET, RequestMethod.POST})
	public String MemberJoin(MemberDto mem) {
		logger.info("MemberController MemberJoin " + new Date());
		
		logger.info("MemberController id " + mem.getId());
		logger.info("MemberController PWD " + mem.getPwd());
		logger.info("MemberController NAME " + mem.getName());
		logger.info("MemberController EMAIL " + mem.getEmail());
		
		boolean isS = memberService.join(mem);
		logger.info("isS: "+ isS);
		
		return "redirect:/mainPage.do";
	}
	// 회원가입뷰
	@RequestMapping(value="joinPage.do", method={RequestMethod.GET, RequestMethod.POST})
	public String joinPage() {
		logger.info("MemberController joinPage " + new Date());
			
		return "member/join";
	}
	
	// 로그인뷰
		@RequestMapping(value="loginPage.do", method={RequestMethod.GET, RequestMethod.POST})
		public String loginPage() {
			logger.info("MemberController loginPage " + new Date());
			
			return "member/login";
		}
	
	// 로그인
	@ResponseBody
	@RequestMapping(value="login.do", produces="application/String; charset=utf-8", method= {RequestMethod.GET, RequestMethod.POST})
	public String MemberLogin(MemberDto mem, HttpServletRequest req) {
		
		logger.info("id는 "+ mem.getId());
		logger.info("패스워드는 "+ mem.getPwd());
		
		MemberDto dto = memberService.login(mem);
//		logger.info("투스트링은 "+ dto.toString());
		if(dto != null && !dto.getId().equals("")) {
			req.getSession().setAttribute("loginuser", dto);
			req.getSession().setMaxInactiveInterval(60*60*365);
			return "success";
		} else {
			return "fail";
		}
	}
	
	//로그아웃
	@RequestMapping(value="logout.do", produces="application/String; charset=utf-8", method= {RequestMethod.GET, RequestMethod.POST})
	public String MemberLogOut(HttpServletRequest req) {
		
		req.getSession().removeAttribute("loginuser");
		
		return "redirect:/mainPage.do";
	}
	
	
	
}
