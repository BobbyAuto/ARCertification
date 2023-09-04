package com.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class YearMapContainerInitializer implements ServletContextListener {
	
	/**
	 * // This method is called when the web application starts.
	 */
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();

        // Create and initialize your map here
        Map yearMapContainer = new HashMap();
        servletContext.setAttribute("yearMapContainer", yearMapContainer);
    }

	/**
	 * This method is called when the web application is about to be shut down.
	 */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
	
}
