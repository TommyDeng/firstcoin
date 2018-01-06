package com.tom.test.xmlbeans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://www.e12366.com/dataspec", name = "tiripPackage")
public class TiripPackage {

	@XmlElement
	public Identity identity;


}
