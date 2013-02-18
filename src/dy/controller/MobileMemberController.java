package dy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class MobileMemberController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String folderPath = "D:\\GitHub\\WebTest\\WebContent\\upimage\\";

	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
	
		// 파라미터로 전송된 값 저장
		String id;
		String pwd;
		String filename;
		File file;
		
		// 이미지 파일 제한 용량 (mbytes, bytes)
		int maxMbSize = 1;
		int maxByteSize = maxMbSize * 1024 * 1024;
		
		try {
			// 이 순간 파일 업로드됨.
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			Enumeration<String> eHeader = request.getHeaderNames();
			
			while (eHeader.hasMoreElements()) {
				String hName = eHeader.nextElement();
				String hValue = request.getHeader(hName);
				
				System.out.println(hName + " : " + hValue);
			}
			
			Enumeration<String> paraNames = request.getParameterNames();
			
			while (paraNames.hasMoreElements()) {
				String paraName = paraNames.nextElement();
				String paraValue = request.getParameter(paraName);
				
				System.out.println(paraName + " : " + paraValue);
				
			}
			
			System.out.println("=== mRequest ===");
			
			MultipartRequest mRequest = new MultipartRequest(request, folderPath, maxByteSize);
			
			file = mRequest.getFile("upimage");
			
			Enumeration mpara = mRequest.getParameterNames();
			
			while (mpara.hasMoreElements()) {
				String paraName = (String)mpara.nextElement();
				String paraValue = mRequest.getParameter(paraName);
				
				System.out.println(paraName + " : " + paraValue);
				
			}
			
			
			
			System.out.println(file.exists());
					
			System.out.println("id  : " + mRequest.getParameter("id"));
			System.out.println("pwd : " + mRequest.getParameter("pwd"));
			System.out.println("cmd : " + mRequest.getParameter("cmd"));
				
			//System.out.println(id);	
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

