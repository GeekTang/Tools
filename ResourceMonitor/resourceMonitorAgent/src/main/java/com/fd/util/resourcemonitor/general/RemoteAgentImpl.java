/**
 * 
 */
package com.fd.util.resourcemonitor.general;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.fd.util.resourcemonitor.agent.RemoteAgent;
import com.fd.util.resourcemonitor.resource.Resource;

/**
 * @author eweitan
 * 
 */
public class RemoteAgentImpl extends UnicastRemoteObject implements RemoteAgent {

	private static final long serialVersionUID = -2649216381304187552L;

	private static final String NAME = "Default Remote Agent";

	private List<Resource> resources = new ArrayList<Resource>();

	private String description = "Default Remote Agent";

	private static final String CONFIG_FILE = "config.properties";

	private static final String RESOURCE_TAG = "resource";

	public RemoteAgentImpl() throws RemoteException {
		super();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
			resources.add((Resource) Class.forName(
					properties.getProperty(RESOURCE_TAG)).newInstance());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.agent.Agent#getResource()
	 */
	public List<Resource> getResources() throws RemoteException {
		return resources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.agent.Agent#start()
	 */
	public boolean start() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.agent.Agent#stop()
	 */
	public boolean stop() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.agent.Agent#getName()
	 */
	public String getName() throws RemoteException {
		return NAME;
	}

	private String buildXML(String type, String name, String description, List<Resource> resources) {
		String response = "";
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Data");
			doc.appendChild(rootElement);
			
			Attr typeAttr = doc.createAttribute("type");
			typeAttr.setValue(type);
			rootElement.setAttributeNode(typeAttr);
			
			Attr nameAttr = doc.createAttribute("name");
			nameAttr.setValue(name);
			rootElement.setAttributeNode(typeAttr);
			
			Attr descriptionAttr = doc.createAttribute("description");
			descriptionAttr.setValue(description);
			rootElement.setAttributeNode(descriptionAttr);
			
			Element application = doc.createElement("Application");
			rootElement.appendChild(application);

			for(Resource resource : resources){
				application.appendChild(buildModule(resource, doc));
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StringWriter outWriter = new StringWriter();
			StreamResult result = new StreamResult(outWriter);
			transformer.transform(source, result);
			StringBuffer sb = outWriter.getBuffer();
			String finalstring = sb.toString();

			response = finalstring;
			System.out.println(response);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		return response;
	}

	private Node buildModule(Resource resource, Document doc) {
		Element module = doc.createElement("Module");
		
		Element name = doc.createElement("Name");
		name.appendChild(doc.createTextNode(resource.getName()));
		module.appendChild(name);
		
		Element description = doc.createElement("Description");
		description.appendChild(doc.createTextNode(resource.getDescription()));
		module.appendChild(description);
		
		Element status = doc.createElement("Status");
		status.appendChild(doc.createTextNode(resource.getStatus().toString()));
		module.appendChild(status);
		
		Element details = doc.createElement("Details");
		details.appendChild(doc.createTextNode(resource.getDetail()));
		module.appendChild(details);
		
		return module;
	}

	public String getData(String type) throws RemoteException {
		
		return buildXML(type, getName(), getDescription(), getResources());
		
	}

	public static void main(String[] args) {
		try {
			RemoteAgent agent = new RemoteAgentImpl();
			LocateRegistry.createRegistry(18787);
			Naming.rebind("//0.0.0.0:18787/agent", agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDescription() throws RemoteException {
		return description;
	}

}
