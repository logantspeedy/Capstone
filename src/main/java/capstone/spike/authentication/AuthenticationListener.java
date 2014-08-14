package capstone.spike.authentication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.openid4java.consumer.ConsumerManager;

public class AuthenticationListener implements ServletContextListener {

    public void contextInitialized( ServletContextEvent sce ) {

    	sce.getServletContext().setAttribute( "MANAGER", new ConsumerManager() );
    }

	public void contextDestroyed(ServletContextEvent sce) {

	}
	
}
