package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServerInicioListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent sce)  {}

    public void contextInitialized(ServletContextEvent sce)  { 
    	Config.db = sce.getServletContext().getRealPath("/WEB-INF/DB.db");
    }
	
}
