package capstone.spike.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchResponse;

// This is where open id connects to the application
public class OpenIDAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ConsumerManager manager = (ConsumerManager) request.getSession().getServletContext().getAttribute( "MANAGER" );
	
	        // extract the parameters from the authentication response
	        // (which comes in as a HTTP request from the OpenID provider)
	        ParameterList parameters = new ParameterList( request.getParameterMap() );
	
	        // retrieve the previously stored discovery information
	        DiscoveryInformation discovered = (DiscoveryInformation) request.getSession().getAttribute("openid");
	 
	        // extract the receiving URL from the HTTP request
	        StringBuffer receivingURL = request.getRequestURL();
	        String queryString = request.getQueryString();
	        if ( queryString != null && queryString.length() > 0 ) {
	            receivingURL.append("?").append( request.getQueryString() );
	        }
	
	        // verify the response; ConsumerManager needs to be the same
	        // (static) instance used to place the authentication request
	        VerificationResult verification = manager.verify(
	                receivingURL.toString(),
	                parameters, discovered);

	        // examine the verification result and extract the verified identifier
	        Identifier verified = verification.getVerifiedId();
	        
	        // You are logged in
	        if ( null != verified ) {

                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();

                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
                {
                    FetchResponse fetchResp = (FetchResponse) authSuccess
                            .getExtension(AxMessage.OPENID_NS_AX);

                    List<?> emails = fetchResp.getAttributeValues("email");
                    String email = (String) emails.get(0);

                    //puts user's email into the session
                    request.getSession().setAttribute( "email", email );
                    
                    //redirects users after authentication 
                    response.sendRedirect( "." );
                }
	        }

		} catch ( Exception e ) {

			// Do error stuff
		}
	}
}
