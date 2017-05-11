package com.igene.demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sshkeys")
public class SSHKey {
	
	private List<String> sshkey;

	public List<String> getSshkey() {
		return sshkey;
	}

	public void setSshkey(List<String> sshkey) {
		this.sshkey = sshkey;
	}

}
