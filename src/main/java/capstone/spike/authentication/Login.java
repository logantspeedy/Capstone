package capstone.spike.authentication;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ax.FetchRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ServletContext context = request.getSession().getServletContext();
			ConsumerManager manager = (ConsumerManager) context.getAttribute( "MANAGER" );
	
			// Tell's open id to use google
			List<?> discoveries = manager.discover("https://www.google.com/accounts/o8/id");
	
			// Tries to associate with OpenID provider google
			DiscoveryInformation discovered = manager.associate(discoveries);
	
			// Store info in session for when they return from open id
			request.getSession().setAttribute( "openid", discovered );

			// obtain a AuthRequest message to be sent to the OpenID provider
            AuthRequest authReq = manager.authenticate( discovered, getURL( request ) + "/openid" );

            // What information do we want?
            FetchRequest fetch = FetchRequest.createFetchRequest();
            fetch.addAttribute("email",
                    // attribute alias
                    "http://schema.openid.net/contact/email",   // type URI
                    true);                                      // required

            // attach the extension to the authentication request
            authReq.addExtension(fetch);

            // send them off to open id provider
            response.sendRedirect( authReq.getDestinationUrl(true) );

		} catch ( OpenIDException e ) {
			// Do some error handling here
		}
	}

	private static String getURL( final HttpServletRequest request ) {

		final StringBuffer url = request.getRequestURL();
		final String uri = request.getRequestURI();

		return url.toString().replaceAll( uri, request.getContextPath() );
	}
}
