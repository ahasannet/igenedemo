package com.igene.demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "regions")
public class DataCenterRegions {
	
	@XmlElement(name = "region", type = DataCenterRegion.class)
	private List<DataCenterRegion> dataCenterRegions;

	public List<DataCenterRegion> getDataCenterRegions() {
		return dataCenterRegions;
	}

	public void setDataCenterRegions(List<DataCenterRegion> dataCenterRegions) {
		this.dataCenterRegions = dataCenterRegions;
	}
	
}
