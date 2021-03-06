package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import service.user.UserService;
import entity.user.UserEntity;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		String id=request.getParameter("inputID");
		String pw=request.getParameter("inputPassword");
		UserService service=new UserService();
		UserEntity entity=new UserEntity();
		entity.setUid(id);
		entity.setUpw(pw);
		boolean result=service.login(entity);
		
				
		if(result){
			HttpSession session=request.getSession(true);
			session.setAttribute("USERID",id);
			//session.setAttribute("FLAG",true);
		}
		JSONObject obj=new JSONObject();
		
		obj.put("result", result);
		
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(obj);

	      out.flush();
	      out.close();
		

	}

}
