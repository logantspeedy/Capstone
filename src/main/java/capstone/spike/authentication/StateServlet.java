package capstone.spike.authentication;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by mtogagi on 30/09/14.
 */
public class StateServlet extends HttpServlet implements HttpSessionListener {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType( "application/json" );
        PrintWriter out = response.getWriter();

        out.print( "{\"state\":\"" );
        out.print( request.getSession().getAttribute( "state" ) );
        out.print( "\"}" );
        out.close();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String state = new BigInteger( 130, new SecureRandom() ).toString( 32 );
        se.getSession().setAttribute( "state", state );
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // nothing
    }
}
