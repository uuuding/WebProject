package csu.web.mypetstore.web.config;

import csu.web.mypetstore.persistence.DBUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application starting.... checking database tables");
        DBUtil.creatLogTable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application stopping...");
    }
}
