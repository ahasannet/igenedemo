package com.igene.demo.model;

import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "applications")
public class OneClickApplications {
	
	@XmlElement(name = "application", type = OneClickApplication.class)
	private List<OneClickApplication> applications;

	public List<OneClickApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<OneClickApplication> applications) {
		this.applications = applications;
	}

}
