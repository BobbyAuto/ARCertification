package com.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.WriteBlockContainerToFile;

public class BlockContainerInitializer implements ServletContextListener {
	
	/**
	 * Load the block data file when the web application starts.
	 */
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();
        
        //String contextPath = "/Users/wangweichun/MyTomcat/webapps/ARCertification/";
        String contextPath = servletContext.getRealPath("/");
                
        WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(contextPath);
        ArrayList<BlockUnit> blockContainer = wbct.readBlockContainer();
        if (blockContainer == null) {
        	blockContainer = new ArrayList<BlockUnit>();
        }
        
        servletContext.setAttribute("blockContainer", blockContainer);
    }

	/**
	 * This method is called when the web application is about to be shut down.
	 */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
	
}
