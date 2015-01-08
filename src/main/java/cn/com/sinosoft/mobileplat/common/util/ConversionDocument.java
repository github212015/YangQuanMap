package cn.com.sinosoft.mobileplat.common.util;

import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jdom.*;

import org.jdom.input.*;
import org.jdom.output.*;

/**
 * ת��org.w3c.dom.Document��
 * 
 * @author �̿�
 */
public class ConversionDocument {

	/**
	 * ����org.w3c.dom.Document
	 * @param xmlString
	 *            XML��ʽ���ַ���
	 * @return ���ؽ�����org.w3c.dom.Document���󣬽������ɹ�����null ��
	 * @throws Exception
	 */
	public static org.w3c.dom.Document createW3CDocument(String xmlString)
			throws Exception {
		Document document = buildFromXMLString(xmlString);

		if (document != null) {
			return outputToDom(document);
		} else {
			return null;
		}
	}

	/**
	 * ����XML �ַ��� ����JDom����
	 * 
	 * @param xmlString
	 *            XML��ʽ���ַ���
	 * @return ���ؽ�����JDom���󣬽������ɹ�����null ��
	 * @throws Exception
	 */
	public static Document buildFromXMLString(String xmlString)
			throws Exception {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new StringReader(xmlString));
			return document;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��jdocumentת����org.w3c.dom.Document
	 * 
	 * @param jdomDoc
	 * @return
	 * @throws JDOMException
	 */
	public static org.w3c.dom.Document outputToDom(org.jdom.Document jdomDoc)
			throws JDOMException {
		org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
		return outputter.output(jdomDoc);
	}
}
