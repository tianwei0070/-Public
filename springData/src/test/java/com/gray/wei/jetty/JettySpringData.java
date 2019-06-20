package com.gray.wei.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettySpringData {

	public static void main(String[] args) throws Exception {
		String webapp = "src/main/webapp";

		Server server = new Server(80);

		WebAppContext context = new WebAppContext();
		context.setDescriptor(webapp + "/web.xml");
		context.setResourceBase(webapp);
		context.setContextPath("/springData");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setConfigurationDiscovered(true);
		context.setParentLoaderPriority(true);

		server.setHandler(context);
		server.start();
		System.out.println("Jetty start at 0.0.0.0:80");
		server.join();
	}

}
