/**
 * 
 */
package com.fd.util.resourcemonitor.general;

import java.io.IOException;
import java.util.Properties;

import com.fd.util.resourcemonitor.agent.Agent;
import com.fd.util.resourcemonitor.resource.Resource;

/**
 * @author "Ares Tang"
 *
 */
public class LibLocalAgent implements Agent {
	
	private static final String NAME = "Default Local Lib Agent";
	
	private Resource resource = null;
	
	private static final String CONFIG_FILE = "config.properties";
	
	private static final String RESOURCE_TAG = "resource";
	
	public LibLocalAgent()
	{
		Properties properties = new Properties();
		try {
			System.out.println(CONFIG_FILE);
			properties.load(this.getClass().getResourceAsStream(CONFIG_FILE));
			resource = (Resource) Class.forName(properties.getProperty(RESOURCE_TAG)).newInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.fd.util.resourcemonitor.agent.Agent#getResource()
	 */
	public Resource getResource() {
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.fd.util.resourcemonitor.agent.Agent#start()
	 */
	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.fd.util.resourcemonitor.agent.Agent#stop()
	 */
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.fd.util.resourcemonitor.agent.Agent#getName()
	 */
	public String getName() {
		return NAME;
	}

}
