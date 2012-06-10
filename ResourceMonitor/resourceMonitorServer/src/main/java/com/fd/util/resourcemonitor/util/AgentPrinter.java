/**
 * 
 */
package com.fd.util.resourcemonitor.util;

import com.fd.util.resourcemonitor.agent.Agent;

/**
 * @author "Ares Tang"
 *
 */
public class AgentPrinter {

	public static String print(Agent agent)
	{
		String agentName = agent.getName();
		
		String resourceName = agent.getResource().getName();
		
		String resourceDescription = agent.getResource().getDescription();
		
		String resourceStatus = agent.getResource().getMonitor().getStatus().toString();
		
		String resourceStatusDetails = agent.getResource().getMonitor().getResourceInfor().getDetail();
		
		String result = "Agent: 				" + agentName + "\r\n"
					  + "Resource Name: 		" + resourceName + "\r\n"
					  + "Resource Description:	" + resourceDescription + "\r\n"
					  + "Resource Status: 		" + resourceStatus + "\r\n"
					  + "Resource Details: 		" + resourceStatusDetails + "\r\n";
		return result;
	}
}
