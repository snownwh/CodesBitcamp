package KSJ.exhibit.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import KSJ.exhibit.dao.ExhibitDao;
import KSJ.exhibit.dto.ExhibitDto;
import KSJ.files.dao.FilesDao;
import KSJ.files.dto.FilesDto;

@WebServlet("/exhibitinsert")
public class ExhibitInsert extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		 System.out.println("file upload connected");
		 req.setCharacterEncoding("utf-8");
		 resp.setCharacterEncoding("utf-8");
		
			
		List<String> filenames = new ArrayList<String>();
		List<String> origin_names = new ArrayList<String>();
 		// 파일 업로딩 	
		// FileDto - 표지 이미지만 우선 저장하기 

		//2가지 방법이 있음
		//1. tomcat에 배포(Server)	-> 자료가 사라질 가능성 있음()
		//임시 서버 경로로 나온다
		
		 // 2. 하드의 특정 공간에 저장
//		String fupload = "/Users/sunjukim/Desktop/tmp_pic/";		

		 

		 // 서버 내 저장
//		String fupload ="/Users/sunjukim/Desktop/semi/AgencyBgency/WebContent/upload/";
		String filepath = "/upload/title/";
		String fupload = req.getSession().getServletContext().getRealPath(filepath);
		System.out.println("fupload:"+fupload);
		
		
		//지정폴더 (Client)
		System.out.println("업로드 폴더: " + fupload);


		String yourTempDir = fupload;

		int yourMaxRequestSize = 100 * 1024* 1024;	// 최대 업로드 가능 사이즈 1 MB
		int yourMaxMemorySize = 100 * 1024;			// 최대 메모리 사이즈 1 KB

		// form field의 데이터를 저장할 변수
		
		String title = "";
		String begindate =  "";
		String enddate =  "";
		String ex_time =  "";
		String place =  "";
		String loc_info =  "";
		String sprice =  "";
		String certi_num =  "";
		String contact =  "";
		String content =  "";
		String filename =  "";// FileDto도 있음
		// 파일 
		String origin_name ="";	//origin_name
		String bbs_name = "exhibit";
		String sfile_seq = "";
		String contentfilename = "";
		
		int price = -1;
		int bbs_seq = -1;	// 이거는 전시 insert 후에 메소드로 받아오기 
		int file_seq = -1;
		
		// file path 는 위에 있음 

		// multipart 가 맞는지 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		

		if(isMultipart){
			
			// file item 생성하기
			DiskFileItemFactory factory = new DiskFileItemFactory();	

			// 용량 설정
			factory.setSizeThreshold(yourMaxMemorySize);	// max memorysize 설정
			
			// 저장소 설정 ( 저장 파일 생성 )
			factory.setRepository(new File(yourTempDir));
			
			// 업로드 설정
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(yourMaxRequestSize);	// 순간 전송크기를 설정
			
			// list 저장 -> id, title, content, file 등을 저장
			List<FileItem> items;
			
			try {
				items = upload.parseRequest(req);
				// list에서 get 아닌 iterator로 꺼낸다
				Iterator<FileItem> it = items.iterator();
				
				// 데이터 구분하기 (form과 file 데이터 구분하기)
				while(it.hasNext()){
					
					FileItem item = it.next();
					if(item.isFormField() ){
						// formfield 를 찾아서 구분해준다.
						
						if(item.getFieldName().equals("title")){
							title = item.getString("utf-8");
						}else if(item.getFieldName().equals("begindate")){
							begindate = item.getString("utf-8");
						}else if(item.getFieldName().equals("enddate")){
							enddate = item.getString("utf-8");
						}else if(item.getFieldName().equals("ex_time")){
							ex_time = item.getString("utf-8");
						}else if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						}else if(item.getFieldName().equals("loc_info")){
							loc_info = item.getString("utf-8");
						}else if(item.getFieldName().equals("price")){
							sprice = item.getString("utf-8");
							price = Integer.parseInt(sprice);	// 금액

						}else if(item.getFieldName().equals("certi_num")){
							certi_num = item.getString("utf-8");
						}else if(item.getFieldName().equals("contact")){
							contact = item.getString("utf-8");
						}else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						}else if(item.getFieldName().equals("filename")){
							filename = item.getString("utf-8").trim();
						}else if(item.getFieldName().equals("origin_name")){
							origin_name = item.getString("utf-8");
						}else if(item.getFieldName().equals("file_seq")){
							sfile_seq = item.getString("utf-8");
							file_seq = Integer.parseInt(sfile_seq);
						}
						else if(item.getFieldName().equals("contentfilename")){
							contentfilename = item.getString("utf-8");
							filenames.add(contentfilename);
							System.out.println("contentfilename:"+contentfilename);
							String orgin = contentfilename.split("__")[1];
							origin_names.add(orgin);
							System.out.println("orginfilename:"+orgin);
						}
					}
						else{	
//						// file 일 때
						if(item.getFieldName().equals("titlefile")){	// fileload 일 때
							filename = processUploadFile(item, fupload);
							// item을 만들어서 fuload라는 경로에 넣어두어라
						}
					}
				}
				
				// 파일 이름 바꾸기 
				File oldfile1 = new File(fupload,origin_name);
				File newfile = new File(fupload,filename);
				
				if(oldfile1.exists()) {
					oldfile1.renameTo(newfile);
				}
				 
					

				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	// 전송되어온 파일을 뽑아옴(parsing)
			
			
			
		}else{
			System.out.println("multipart가 아님");
			resp.sendRedirect("./main/main.jsp");
		}
		
		
		
		
		
		System.out.println("title : "+title);
		System.out.println("begindate : "+begindate);
		System.out.println("enddate : "+enddate);
		System.out.println("ex_time : "+ex_time);
		System.out.println("place : "+place);
		System.out.println("loc_info : "+loc_info);
		System.out.println("price : "+ price);
		System.out.println("certi_num : "+certi_num);
		System.out.println("contact : "+contact);
		System.out.println("content : "+content);
		System.out.println("filename : "+filename);
		System.out.println("filename: "+ filename );
		System.out.println("file_seq: "+ sfile_seq);
		System.out.println("bbs_name: "+ bbs_name);
		System.out.println("bbs_seq: "+ bbs_seq);
		System.out.println("filepath: "+ filepath);
		System.out.println("orgin_name: "+ origin_name);

	
		 
		ExhibitDao dao = ExhibitDao.getInstance();;
		FilesDao fdao = FilesDao.getInstance();
		// dto로 정보 담기 
		ExhibitDto edto = new ExhibitDto(-1, begindate, enddate, title, place, content, ex_time, 
				loc_info, 0, contact, certi_num, price, filename);
		
		
		// 콘텐츠 파일 디비에 입력하기 위한 Dto 작성 
		List<FilesDto> flist = new ArrayList<FilesDto>();

		
		//boolean insertdContentFile = dao.insertContentFile(flist);
//		System.out.println(b);

		boolean insertExhibitSuccess = dao.insertExhibit(edto);
		 
		if(insertExhibitSuccess) {
			System.out.println("전시 디비 입력 성공 ");
			bbs_seq = fdao.getExhibitSeq(certi_num);	// 파일에 저장하기 위해 시퀀스 불러오기 
			System.out.println("bbs_seq : " + bbs_seq);
			// Title Img -> dto 
			FilesDto titleDto = new FilesDto(-1, filename, origin_name, filepath, bbs_name, bbs_seq, 0, file_seq);
			
			// contentImg -> dto
			for(int i=0; i< filenames.size(); i++) {
				FilesDto contentDto = new FilesDto(-1, 	// seq  db에서 입력 
											filenames.get(i), // new filename 
											origin_names.get(i),	// old origin_name
											"/upload/content/", 	// filepath
											"exhibit", 				// bbs_name
											bbs_seq, 	// bbs_seq : dao에서 넣기 
											0,		// del
											i+2 );	// file_seq
				flist.add(contentDto);
			}
			
			System.out.println(titleDto.toString());
			//req.setAttribute("newFiles", newFile);
			
			boolean titleSuccess = fdao.insertFile(titleDto);
			
			if(titleSuccess) {
				System.out.println("Title file into database succeed");
				
				boolean contentSuccess = fdao.insertContentFile(flist);
				
				if(contentSuccess) {
					System.out.println("content files into database succeed");
					
					resp.sendRedirect("./exhibitcuratordetail?seq="+bbs_seq);

				}else {
					System.out.println("fail to save conetent to db");
					resp.sendRedirect("./curatorexhibitlist?page=0");
				}

			}else {
				System.out.println("fail to save title to db");
				resp.sendRedirect("./curatorexhibitlist?page=0");
			}
			
		}else {
			System.out.println("fail to save exhibit to db");
			resp.sendRedirect("./curatorexhibitlist?page=0");
		}
		
	}
	

public String processUploadFile(FileItem fileItem, String dir ) throws IOException{
		
		String filename = fileItem.getName();	
		// --> 경로 + 파일명으로 들어옴 ( 1.서버(톰캣) 경로  2.클라이언트 경로)
		long sizeInBytes = fileItem.getSize();	
		// 파일의 크기 구하기
		if(sizeInBytes > 0 ) {	
			// 클라이언트의 경로일 때 예시 : d:/tmp/abc.txt 또는 d:\\tmp\\abc.txt
			// 정상적으로 파일이 업로드 되었을 때
			
			int idx = filename.lastIndexOf("\\");	// \으로 입력되있을 때
			if(idx == -1 ){
				idx = filename.lastIndexOf("/");	// /로 입력되었을 때
			}
			
			// 경로 빼고 파일명만 가져오기
			filename = filename.substring( idx+1 );
			

			
			// 업로드하는 디렉토리에 업로드 한 파일을 기입함
			File uploadfile = new File(dir, filename);
			
			
			try{
				fileItem.write(uploadfile);	// 실제 업로드 부분
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return filename;	// return 값은 확인용
		
	}
	
	

}
