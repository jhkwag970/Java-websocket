package frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_Services.dbGet;
import db_Services.dbImpl;
import db_Services.dbInsert;
import file_Services.fileGet;
import file_Services.fileImpl;
import file_Services.fileInsert;

/**
 * Servlet implementation class FileController
 */
@WebServlet("*.do")
public class frontController extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public frontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*******************************/
		String c = request.getRequestURI().substring
                     (request.getContextPath().length());
		/**********************************/
		
		String str = "Live_index7.jsp";  ///////////
		fileImpl fi = null; 
		dbImpl di = null;
		switch(c) {
		
		case "/insertFile.do":
			fi = new fileInsert();
			try {
				fi.fileWork(request, response);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			break;	
			
			
		case "/getAllFile.do":
			fi = new fileGet();
			try {
				fi.fileWork(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case "/insertDB.do":
			di = new dbInsert();
			try {
				di.dbWork(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			
			
		case "/getAllDB.do":
			di = new dbGet();
			try {
				di.dbWork(request, response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		} //swich -end 
		//String chat = (String) request.getAttribute("chat");
		//System.out.println(chat);
		request.setAttribute("n", "s");
		RequestDispatcher rd1 = request.getRequestDispatcher(str);
		rd1.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		

		
	}

}
