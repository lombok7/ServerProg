package dy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dy.beans.MemberDAO;
import dy.beans.MemberVO;

public class WebMemberController extends HttpServlet {
	
	// 이미지가 저장될 서버 폴더 경로.
	// \\ 2개씩 한 개만 하면, \n\r 이런 걸로 인식함.
	// private static final String folderPath = "D:\\workspace\\WebTest\\WebContent\\uploadimage\\";
	
	private static final long serialVersionUID = 1L;
	
	// Receives standard HTTP requests from the public service method 
	// and dispatches them to the doXXX methods defined in this class.
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		
		// 파일 저장 폴더는 웹 루트\\upimage 폴더
		// D:\workspace\Web\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebTest\\upimage\
		
		ServletContext context = getServletContext();
		String folderPath = context.getRealPath("upimage") + "\\";
		
		System.out.println("Upload FolderPath : " + folderPath);
				
		Enumeration<String> eHeader = request.getHeaderNames();
		
		System.out.println("========================= Header =========================");
		
		while (eHeader.hasMoreElements()) {
			String hName = eHeader.nextElement();
			String hValue = request.getHeader(hName);
			
			System.out.println(hName + " : " + hValue);
		}
		
		System.out.println("======================= Parameter ========================");
		
		Enumeration<String> paraNames = request.getParameterNames();
		
		while (paraNames.hasMoreElements()) {
			String paraName = paraNames.nextElement();
			String paraValue = request.getParameter(paraName);
			
			System.out.println(paraName + " : " + paraValue);
			
		}
 				
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("insert")) {
			
			insertMember(request, response, folderPath);
		
		} else if (cmd.equals("select")) {
			
			String idx = request.getParameter("idx");
			selectMember(request, response, idx, folderPath);
		
		} else if (cmd.equals("selectall")) {
			
			selectAllMember(request, response);
		
		} else if (cmd.equals("login")) {
			
			loginMember(request, response);
		
		} else if (cmd.equals("logout")) {
		
			logoutMember(request, response);
			
		}		
		// System.out.println("======= service =======");
		// System.out.println(request.getParameter("id"));
		// System.out.println(request.getParameter("pwd"));
	}
	
	public void loginMember(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = mdao.checkLogin(id, pwd);
		
		if (mvo instanceof MemberVO) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginid", mvo.getId());
			session.setAttribute("loginfilename", mvo.getFilename() == null ? "noimage.png" : mvo.getFilename());
			
			RequestDispatcher rd = request.getRequestDispatcher("./member.do?cmd=selectall");
						
			try {
				rd.forward(request, response);
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			} catch (IOException ie) {
				ie.printStackTrace();
			}			
			
		} else {
		
			String errMsg = "아이디와 비밀번호를 확인해 주세요.";
			
			request.setAttribute("errMsg", errMsg);
						
			try {
				
				RequestDispatcher rd = request.getRequestDispatcher("./err.jsp");
				rd.forward(request, response);
			
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public void logoutMember(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		try {
		
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
			
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
	
	}

	public void insertMember(HttpServletRequest request,
			HttpServletResponse response, String folderPath) {
		
		// 이미지 파일 제한 용량 (mbytes, bytes)
		int maxMbSize = 1;
		int maxByteSize = maxMbSize * 1024 * 1024;
				
		// File
		File file = null;
		File rename = null;
		
		// 파라미터로 전송된 값 저장
		String id;
		String pwd;
		String filename;
		int filesize;
		
		// 이 순간 파일 업로드됨.
		try {
			
			MultipartRequest mRequest = new MultipartRequest(request, folderPath, maxByteSize,"UTF-8");
			
			id = mRequest.getParameter("id");
			pwd = mRequest.getParameter("pwd");
			file = mRequest.getFile("upimage");
			
			// System.out.println(request.getParameter("id"));
			// System.out.println(request.getParameter("pwd"));
			
			// System.out.println(id);
			// System.out.println(pwd);
					
			if (file == null) {
			
				filename = null;
				filesize = 0;
			
			} else {
				
				String orgfilename = file.getName();
				
				// 확장자 떼어내기.
				String ext = orgfilename.substring(orgfilename.lastIndexOf('.')+1, orgfilename.length());
				
				// 변경될 파일명을 위해 값 가져옴.
				long ctm = System.currentTimeMillis();
				
				// 드라이브명 부터 경로를 적어야 함. 
				// renameTo 호출시 파일이 사라져 버렸음.
				rename = new File(folderPath + ctm + "." + ext);	
				
				// 파일 이름 중복 체크, 한 번 더 시스템 값을 얻어옴.
				if (rename.exists()) {
					ctm = System.currentTimeMillis();
					rename = new File(folderPath + ctm + "." + ext);	
				} 
				
				// 변경할 파일명 확인.
				System.out.println("rename : " + rename);
				
				// 파일명 변경.
				file.renameTo(rename);	
				
				// request 객체에 결과 담기.
				request.setAttribute("filepath", rename);
				
				// 결과값을 가지고 jsp로 이동.
				// 경로 '/'로 시작할 것.
				// HttpServletRequest, HttpServletResponse, RequestDispatcher 등 연구대상.
				
				filename = rename.getName();
				filesize = (int)rename.length();
			}
			
			// 전송, 가공된 파라미터 값들을 VO 객체에 담기.
			MemberVO mvo = new MemberVO(id, pwd, filename, filesize);
			
			// VO 객체들을 DB에 저장하기
			MemberDAO mdao = new MemberDAO();
			boolean result = mdao.insertMember(mvo);
			
			if (result) {
							
				RequestDispatcher rd = request.getRequestDispatcher("./member.do?cmd=select&idx=" + id);
				rd.forward(request, response);	
								
			} else {
				String errMsg = "회원 가입에 실패하였습니다.";
				
				// DB에 insert 실패 시 업로드된 파일 삭제
				if (mvo.getFilename() != null) {
					rename.delete();
				}
					
				request.setAttribute("errMsg", errMsg);
				
				RequestDispatcher rd = request.getRequestDispatcher("./err.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ServletException se1) {
			se1.printStackTrace();
		
		} catch (IOException ie1) {
			// 지정된 용량을 초과하면 IOException 발생.
			try {
				String errMsg = "업로드 파일의 최대 제한 용량은 " + maxMbSize  + " Mbytes 입니다.";
				
				request.setAttribute("errMsg", errMsg);
							
				RequestDispatcher rd = request.getRequestDispatcher("./err.jsp");
				rd.forward(request, response);
				
			} catch (IOException ie2) {
				ie2.printStackTrace();
			} catch (ServletException se2) {
				se2.printStackTrace();
			}
		} 
	}
	
	public void selectMember(HttpServletRequest request,
			HttpServletResponse response, String idx, String folderPath) {
		
		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = mdao.selectMember(idx);
		
		if (mvo.getFilename() == null) {

			request.setAttribute("filewebpath", "./upimage/noimage.png");
					
		} else {
			
			request.setAttribute("filelocalpath", folderPath + mvo.getFilename());
			request.setAttribute("filewebpath", "./upimage/" + mvo.getFilename());
			// System.out.println(folderPath + mvo.getFilename());

		}
		
		request.setAttribute("member", mvo);
		
		// 결과값을 가지고 jsp로 이동.
		// 경로 '/'로 시작할 것.
		// HttpServletRequest, HttpServletResponse, RequestDispatcher 등 연구대상.
		try {

			RequestDispatcher rd = request.getRequestDispatcher("./showResult.jsp");
			rd.forward(request, response);
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void selectAllMember(HttpServletRequest request, 
			HttpServletResponse response) {
		
		MemberDAO mdao = new MemberDAO();
		List<MemberVO> mvoList = new ArrayList<MemberVO>();
		
		mvoList = mdao.selectAllMember();
		
		request.setAttribute("mvoList", mvoList);
		
		try {
	
			RequestDispatcher rd = request.getRequestDispatcher("./showMember.jsp");
			rd.forward(request, response);
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
