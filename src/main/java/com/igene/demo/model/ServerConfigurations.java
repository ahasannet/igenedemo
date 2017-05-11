package com.igene.demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "configurations")
public class ServerConfigurations {
	
	@XmlElement(name = "configuration", type = ServerConfiguration.class)
	private List<ServerConfiguration> serverConfigurations;

	public List<ServerConfiguration> getServerConfigurations() {
		return serverConfigurations;
	}

	public void setServerConfigurations(List<ServerConfiguration> serverConfigurations) {
		this.serverConfigurations = serverConfigurations;
	}
	
}
