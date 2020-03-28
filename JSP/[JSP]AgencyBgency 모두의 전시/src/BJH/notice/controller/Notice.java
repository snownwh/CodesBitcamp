package BJH.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.jni.Time;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import BJH.notice.dao.FileDao;
import BJH.notice.dao.NoticeDao;
import BJH.notice.dto.NoticeDto;
import KSJ.files.dto.FilesDto;
@WebServlet("/Notice")
public class Notice extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String notice = req.getParameter("notice");
		if(notice != null) {
			// 공지 작성 페이지 이동
			if(notice.equals("write")) {
				resp.sendRedirect(req.getContextPath()+"/admin/noticeWrite.jsp");
			// 공지 업데이트	
			}else if(notice.equals("update")){
				req.setCharacterEncoding("utf-8");
				resp.setCharacterEncoding("utf-8");
				String sseq = req.getParameter("seq");
				int seq = Integer.parseInt(sseq);
				// 실제 저장 경로 
				MultipartRequest multi =null;
				String filePath ="/upload/notice/";
				
				String upload = req.getSession().getServletContext().getRealPath(filePath);
				//String upload = req.getContextPath()+filePath;
				System.out.println("upload=" + upload);
				// 사이즈 지정
				int maxSize = 100*1024*1024;
				//int maxMemSize = 100 * 1024;
				
				// 파라미터 받기위한 변수
				String id ="";
				String title ="";
				String content="";
				String fileName ="";
				int choice =0;
				
				
				//파일
				String originName="";
				String bbs_name = "notice";
				int bbs_seq = -1;

				try {
					multi= new MultipartRequest(
							req, 
							upload,
							maxSize,
							"utf-8",
							new DefaultFileRenamePolicy());
					
					id = multi.getParameter("id");
					title= multi.getParameter("title");
					content = multi.getParameter("content");
					String schoice = multi.getParameter("choice");
					choice = Integer.parseInt(schoice);
					fileName = multi.getFilesystemName("fileName");
					System.out.println("fileName="+fileName);
					//실제 내가 올린 파일명
					originName = multi.getOriginalFileName("fileName");
					System.out.println("originName="+originName);
				
				}catch(Exception e) {
					System.out.println("파일업로드 문제 발생");
					System.out.println(e.getMessage());
				}
				NoticeDao noticeDao = NoticeDao.getInstance();
				FileDao fileDao = FileDao.getInstance();
				boolean chkFile = false; 
				if(!fileName.equals("") && fileName != null) {
					chkFile = true;
				}
				System.out.println("chkFile="+chkFile);
				NoticeDto noticeDto = new NoticeDto(id,title,content,choice,fileName);
				
	//-----------------------------------예외처리 마무리할것!!
				
				boolean result = noticeDao.notice_Update(seq,noticeDto,chkFile);
				
				if(result) {
					System.out.println("공지 DB 수정 완료");
					bbs_seq = fileDao.getNoticeSeq(fileName);
					System.out.println("fileName = "+fileName);
					FilesDto fileDto = new FilesDto(-1,fileName,originName,
													filePath,bbs_name,bbs_seq,0,2);
					System.out.println("fileDto = "+ fileDto);
					// case 1 기존 첨부 존재시 update
					String noFile = multi.getParameter("noFile");
					System.out.println("noFile = " + noFile);
					if(noFile != null) {
						System.out.println("상단 위치");
						
						boolean fileResult = fileDao.insertFile(fileDto);
						if(fileResult) {
							System.out.println("send filePath = "+filePath);
							System.out.println("send fileName = "+fileName);
							// 첨부파일 X --> 공지O 첨부 O update
							resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=true");
						}else {
							// 첨부파일 X --> 공지O 첨부 X update
							resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=fail");
						}
					// case 2 기존 첨부 미 존재시 update
					}else {
						System.out.println("하단 위치");
						boolean fileResult = fileDao.updateFile(fileDto);
						if(fileResult) {
							System.out.println("send filePath = "+filePath);
							System.out.println("send fileName = "+fileName);
							// 첨부파일O --> 공지 O 첨부파일 O  update
							resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=true");
						}else {	
							// 첨부파일O --> 공지 O 첨부파일 X  update
							resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=fail");
						}
					}
				//update Fail (공지 업데이트 실패 )
				}else {
					resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=false");
				}

				 
			//공지 디테일
			}else if(notice.equals("detail")) {
				
				req.setCharacterEncoding("utf-8");
				String sseq = req.getParameter("seq");
				System.out.println("seq="+sseq);
				int seq = Integer.parseInt(sseq);
				NoticeDao noticeDao = NoticeDao.getInstance(); 
				FileDao fileDao = FileDao.getInstance();
				
				NoticeDto noticeDto = noticeDao.notice_detail(seq);
				//문제발생
				FilesDto fileDto = fileDao.getOriginName(seq);		
				
				if(fileDto != null) {
					req.setAttribute("fileDto", fileDto);					
				}
				req.setAttribute("noticeDetail", noticeDto);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/noticeDetail.jsp");
				dispatcher.forward(req	, resp);
			//공지 DB 적용
			}else if(notice.equals("insert")) {
				req.setCharacterEncoding("utf-8");
				resp.setCharacterEncoding("utf-8");
				// 실제 저장 경로 
				MultipartRequest multi =null;
				String filePath ="/upload/notice/";
				
				String upload = req.getSession().getServletContext().getRealPath(filePath);
				File Folder = new File(upload);
				
				if (!Folder.exists()) {
					try{
					    Folder.mkdir(); //폴더 생성합니다.
					    System.out.println("폴더가 생성되었습니다.");
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("이미 폴더가 생성되어 있습니다.");
				}
				
				//String upload = req.getContextPath()+filePath;
				System.out.println("upload=" + upload);
				// 사이즈 지정
				int maxSize = 100*1024*1024;
				//int maxMemSize = 100 * 1024;
				
				// 파라미터 받기위한 변수
				String id ="";
				String title ="";
				String content="";
				String fileName ="";
				int choice =0;
				
				//파일
				String originName="";
				String bbs_name = "notice";
				int bbs_seq = -1;

				try {
					multi= new MultipartRequest(
							req, 
							upload,
							maxSize,
							"utf-8",
							new DefaultFileRenamePolicy());
					
					id = multi.getParameter("id");
					title= multi.getParameter("title");
					content = multi.getParameter("content");
					String schoice = multi.getParameter("choice");
					choice = Integer.parseInt(schoice);
					//서버에 저장되는 이름(중복이름발생시  ex)notice1 notice2 숫자++)
//					SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMMddHHmmss");
//					//Calendar time = Calendar.getInstance();
//					String uploadTime = timeformat.format(System.currentTimeMillis());
					fileName = multi.getFilesystemName("fileName");
					System.out.println("fileName="+fileName);
					//실제 내가 올린 파일명
					originName = multi.getOriginalFileName("fileName");
					System.out.println("originName="+originName);
				
				}catch(Exception e) {
					System.out.println("파일업로드 문제 발생");
					System.out.println(e.getMessage());
				}
				NoticeDao noticeDao = NoticeDao.getInstance();
				FileDao fileDao = FileDao.getInstance();
				NoticeDto noticeDto = new NoticeDto(id,title,content,choice,fileName);
				
				
				boolean result = noticeDao.notice_Insert(noticeDto);
				
				if(result) {
					System.out.println("공지 DB 입력 완료");
					bbs_seq = fileDao.getNoticeSeq(fileName);
					FilesDto fileDto = new FilesDto(-1,fileName,originName,
													filePath,bbs_name,bbs_seq,0,2);
					boolean fileResult = fileDao.insertFile(fileDto);
					if(fileResult) {
						
						System.out.println("send filePath = "+filePath);
						System.out.println("send fileName = "+fileName);
						
						resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=true");
					}else {
						resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=fail");
					}
				}else {
					resp.sendRedirect(req.getContextPath()+"/admin/admin_result.jsp?result=false");
				}
				
				 
			// 공지 삭제	 
			}else if(notice.equals("delete")){
				
				String[] deleteList = req.getParameterValues("deleteList[]");
				System.out.println(deleteList);
				for (int i = 0; i < deleteList.length; i++) {
					System.out.println(deleteList[i]);
				}
				req.setCharacterEncoding("utf-8");
				resp.setContentType("application/json");
				
				
				boolean noticeResult = false;
				boolean fileResult = false;
				NoticeDao noticeDao = NoticeDao.getInstance();
				noticeResult = noticeDao.notice_delete(deleteList);
				if(noticeResult) {
					FileDao fileDao = FileDao.getInstance();
					fileResult = fileDao.file_delete(deleteList);
				}
				System.out.println("del_result="+noticeResult);
				System.out.println("file_result="+ fileResult);
				String result = noticeResult+"";
				//String gson = new Gson().toJson(noticeResult);
				resp.getWriter().write(result);  
			}
		// notice 파라미터 없을시 메인뷰 이동 			
		}else {
			resp.sendRedirect(req.getContextPath()+"/admin/test.jsp");
		}
		
			
		
				
		
	}

	
	
}
