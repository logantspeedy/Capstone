package capstone.spike.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getParameter( "url" ); //gets the requested url
		
		request.getSession().invalidate(); //invalidates the session
		
		//if users haven't supplied the url, 
		if ( null == url ) {
			response.sendRedirect( "." ); // send them home
		} else {
			response.sendRedirect( url ); // send them to the url
		}
	}
}
