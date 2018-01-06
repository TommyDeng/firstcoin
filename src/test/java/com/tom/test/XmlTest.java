package com.tom.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import com.tom.test.xmlbeans.TiripPackage;

public class XmlTest {
	public static void main(String[] args) throws Exception {

		String xmlFilePath = "G:/git/firstcoin/src/test/java/com/tom/test/test2.xml";
		InputStream xmlInput = new FileInputStream(new File(xmlFilePath));
		String xmlStr = IOUtils.toString(xmlInput, "UTF-8");
		IOUtils.closeQuietly(xmlInput);
		TiripPackage xmlBean = generateJavaBean(xmlStr, TiripPackage.class);
		System.out.println(xmlBean.identity.serviceId);
		System.out.println(xmlBean.identity.dkType);

	}

	@SuppressWarnings("unchecked")
	public static <T> T generateJavaBean(String xmlStr, Class<T> cls) throws Exception {
		if ("".equals(xmlStr) || xmlStr == null)
			return null;

		JAXBContext jaxbContext = JAXBContext.newInstance(cls);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		T returnObject = null;
		StringReader strReader = null;

		try {
			strReader = new StringReader(xmlStr);
			returnObject = (T) jaxbUnmarshaller.unmarshal(strReader);
		} finally {
			if (strReader != null) {
				strReader.close();
			}
		}
		return returnObject;
	}
}
