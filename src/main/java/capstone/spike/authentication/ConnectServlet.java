package capstone.spike.authentication;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by mtogagi on 30/09/14.
 */
public class ConnectServlet extends HttpServlet {

    /**
     * Default HTTP transport to use to make HTTP requests.
     */
    private static final HttpTransport TRANSPORT = new NetHttpTransport();

    /**
     * Default JSON factory to use to deserialize JSON.
     */
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    /**
     * Gson object to serialize JSON responses to requests to this servlet.
     */
    private static final Gson GSON = new Gson();

    /**
     * Creates a client secrets object from the client_secrets.json file.
     */
    private static GoogleClientSecrets clientSecrets;

    static {
        try {
            Reader reader = new FileReader( "client_secrets.json" );
            clientSecrets = GoogleClientSecrets.load( JSON_FACTORY, reader );
        } catch ( final IOException e ) {
            throw new Error( "No client_secrets.json found", e );
        }
    }

    /**
     * This is the Client ID that you generated in the API Console.
     */
    private static final String CLIENT_ID = clientSecrets.getWeb().getClientId();

    /**
     * This is the Client Secret that you generated in the API Console.
     */
    private static final String CLIENT_SECRET = clientSecrets.getWeb().getClientSecret();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType( "application/json" );
        String tokenData = ( String ) request.getSession().getAttribute( "token" );

        if ( null != tokenData ) {
            response.setStatus( HttpServletResponse.SC_OK );
            response.getWriter().print( GSON.toJson( "Current user is already connected." ) );
            return;
        }

        if ( !request.getParameter( "state" ).equals( request.getSession().getAttribute( "state" ) ) ) {
            response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
            response.getWriter().print( GSON.toJson( "Invalid state parameter." ) );
            return;
        }

        request.getSession().removeAttribute( "state" );

        final ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        getContent( request.getInputStream(), resultStream );
        final String code = new String( resultStream.toByteArray(), "UTF-8" );

        try {
            final GoogleTokenResponse tokenResponse =
                    new GoogleAuthorizationCodeTokenRequest(
                            TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, code, "postmessage" ).execute();

            request.getSession().setAttribute( "token", tokenResponse.toString() );
            response.setStatus( HttpServletResponse.SC_OK );
            response.getWriter().print( GSON.toJson( "Successfully connected user." ) );

        } catch ( final TokenResponseException e ) {

            response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            response.getWriter().print( GSON.toJson( "Failed to upgrade the authorization code." ) );

        } catch (final IOException e ) {

            response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            response.getWriter().print( GSON.toJson( "Failed to read token data from Google. " + e.getMessage() ) );
        }
    }

    private static void getContent( final InputStream inputStream, final ByteArrayOutputStream outputStream )
            throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream ) );
        int readChar;
        while ( ( readChar = reader.read() ) != -1 ) {
            outputStream.write( readChar );
        }
        reader.close();
    }
}
