package cn.com.sinosoft.map.web;

import ins.domain.util.UUIDUtil;
import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.ServiceFactory;
import ins.framework.exception.BusinessException;
import ins.map.schema.model.LocationInfo;
import ins.map.service.facade.SinoMapService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import cn.com.sinosoft.map.model.vo.HeadMsg;
import cn.com.sinosoft.map.model.vo.LocationUploadReq;
import cn.com.sinosoft.map.model.vo.LocationUploadRsp;
import cn.com.sinosoft.mobileplat.common.util.PubTools;

public class LocationUploadServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(LocationUploadServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException
	{
	  doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException
	{
	  List ioList = IOUtils.readLines(request.getInputStream());
	  String mobilePlatJSON = "";
	  this.logger.info("-------------LocationUploadServlet���ÿ�ʼ---------------");
	
	  String mobilePlatJSONTemp = (String)request.getAttribute("JSONString");
	
	  String ip = request.getRemoteHost();
	  String url = request.getRequestURI();
	  HttpSession session = request.getSession();
	  String id = session != null ? session.getId().toUpperCase() : "";
	
	  String infoPre = "[CommServer:" + id + "]";
	  long timeBegin = System.currentTimeMillis();
	
	  this.logger.info(infoPre + " Begin - IP: " + ip + "; URI: " + url);
	
	  InputStream in = request.getInputStream();
	  BufferedInputStream input = null;
	  ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(2048);
	  try
	  {
	    byte[] bufferRead = new byte[1024];
	    input = new BufferedInputStream(in);
	    int count = 0;
	    while ((count = input.read(bufferRead)) != -1) {
	      byteOutput.write(bufferRead, 0, count);
	    }
	    this.logger.info("oList.size() ��" + ioList.size());
	    StringBuilder sb = new StringBuilder(1024);
	    for (int i = 0; i < ioList.size(); i++) {
	      sb.append(ioList.get(i));
	    }
	    mobilePlatJSON = mobilePlatJSONTemp;
	    if (mobilePlatJSON == null) {
	      mobilePlatJSON = sb.toString();
	    }
	    mobilePlatJSON = URLDecoder.decode(mobilePlatJSON, "UTF-8");
	    this.logger.info("�ӵ�ԭʼJSON��Ϣ��" + mobilePlatJSON);
	    System.out.println("�ӵ�ԭʼJSON��Ϣ��" + mobilePlatJSON);
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    this.logger.error("Error reading:" + e.toString(), e);
	
	    if (input != null)
	      try {
	        input.close();
	      } catch (Exception e1) {
	        this.logger.error("Error closing:" + e1.toString(), e1);
	      }
	  }
	  finally
	  {
	    if (input != null) {
	      try {
	        input.close();
	      } catch (Exception e1) {
	        this.logger.error("Error closing:" + e1.toString(), e1);
	      }
	
	    }
	
	  }
	
	  String responseMessage = "";
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
	  
	  LocationUploadReq locationUploadReq = new LocationUploadReq();
	  LocationUploadRsp locationUploadRsp = new LocationUploadRsp();
	  
	  try
	  {
	    this.logger.info("�ӿڵ��ÿ�ʼ");
	    String requestMessage = mobilePlatJSON;
	    this.logger.info(infoPre + " Info  - Input: " + requestMessage);
	    System.out.println("1.�����յ�ʱ��:" + sf.format(new Date(System.currentTimeMillis())));
	    try {
	      long xmls = System.currentTimeMillis();
	      //1������Json
	      JSONObject j = (JSONObject) JSONSerializer.toJSON(requestMessage);
	      locationUploadReq = (LocationUploadReq)JSONObject.toBean(j, LocationUploadReq.class);
	      long xmle = System.currentTimeMillis();
	      System.out.println("2.��������ʱ�䣺" + (xmle - xmls) + "ms");
	    } catch (Exception e) {
	    	responseMessage = responseMessage +"��ͼ����ƽ̨���Ľ����쳣��";
	    	e.printStackTrace();
	      this.logger.error("��ͼ����ƽ̨���Ľ����쳣��" + e.toString(), e);
	      throw new BusinessException("XML_DECODE_ERROR", "����ƽ̨���Ľ����쳣��");
	    }
	    long timeStart_XML = System.currentTimeMillis();
	    
	    
	    SinoMapService sinoMapService = (SinoMapService)ServiceFactory.getService("sinoMapService");
	    CacheService cache = CacheManager.getInstance("config");

	    if(locationUploadReq.getFlag() !=null && !"0".equals(locationUploadReq.getFlag().trim())){
	    	LocationInfo locationInfo = new LocationInfo();
	    	LocationInfo loca = sinoMapService.findLocationInfoByUserCode(locationUploadReq.getUserCode());
	    	if(loca != null){
	    		loca.setLngX(locationUploadReq.getLngX());
	    		loca.setLatY(locationUploadReq.getLatY());
	    		loca.setName(locationUploadReq.getUserName());
	    		loca.setOperateTimeForHis(new Date());
	    	//	loca.setPhoneNumber(locationUploadReq.getPhoneNumber());
	    		if("1".equals(locationUploadReq.getFlag())){
	    			loca.setValidStatus("1");
	    		}else if("2".equals(locationUploadReq.getFlag())){
	    			loca.setValidStatus("0");
	    		}
	    		sinoMapService.updateLocationInfo(loca);
	    	}else{
	    		locationInfo.setId(UUIDUtil.getUUID());
	    		locationInfo.setLngX(locationUploadReq.getLngX());
	    		locationInfo.setLatY(locationUploadReq.getLatY());
	    		locationInfo.setName(locationUploadReq.getUserCode());
	    		locationInfo.setName(locationUploadReq.getUserName());
	    		//locationInfo.setPhoneNumber(locationUploadReq.getPhoneNumber());
	    		locationInfo.setInsertTimeForHis(new Date());
	    		locationInfo.setOperateTimeForHis(new Date());
	    		locationInfo.setValidStatus("1");
	    		sinoMapService.saveLocationInfo(locationInfo);
	    	}
	    	HeadMsg headMsg = new HeadMsg();
		    headMsg.setResponseCode("0");
		    headMsg.setMessage("�ɹ���");
		    locationUploadRsp.setHeadMsg(headMsg);
	    }else{
	    	HeadMsg headMsg = new HeadMsg();
		    headMsg.setResponseCode("0");
		    headMsg.setMessage("�ɹ���");
		    locationUploadRsp.setHeadMsg(headMsg);
	 	    locationUploadRsp.setEachTime(cache.getCache("mobileTime") + "");
	    }
	   
	    //3��תJSON
	    responseMessage = JSONObject.fromBean(locationUploadRsp).toString();
	    System.out.println("3.������Ϣ\n" + responseMessage);
	    long timeEnd_XML = System.currentTimeMillis();
	    
	    this.logger.info("����������Ӧʱ�� ��" + (timeEnd_XML - timeStart_XML) / 1000.0D);
	
	    this.logger.info("�ӿڵ��ý���");
	  }
	  catch (BusinessException e)
	  {
	    e.printStackTrace();
	    this.logger.error("����ƽ̨�߼������쳣��" + e.toString(), e);
	    HeadMsg headMsg = new HeadMsg();
	    headMsg.setResponseCode("-1");
	    headMsg.setMessage("����ƽ̨�߼������쳣��");
	    locationUploadRsp.setHeadMsg(headMsg);
	    responseMessage = JSONObject.fromBean(locationUploadRsp).toString();;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    this.logger.error("����ƽ̨�߼������쳣��" + e.toString(), e);
	    HeadMsg headMsg = new HeadMsg();
	    headMsg.setResponseCode("-1");
	    headMsg.setMessage("����ƽ̨�߼������쳣��");
	    locationUploadRsp.setHeadMsg(headMsg);
	    responseMessage = JSONObject.fromBean(locationUploadRsp).toString();;
	  }
	  
	  this.logger.info("responseMessage=" + responseMessage);
	  responseMessage = URLEncoder.encode(responseMessage,"UTF-8");
	  byte[] byteMessages = responseMessage.getBytes();
	  try
	  {
	    OutputStream outS = response.getOutputStream();
	    response.setContentType("text/html; charset=GBK");
	    response.setContentLength(byteMessages.length);
	    outS.write(byteMessages);
	    outS.flush();
	    outS.close();
	  } catch (Exception e) {
	    e.printStackTrace();
	    this.logger.error("Error sending XML: " + e.toString(), e);
	  }
	
	  long timeEnd = System.currentTimeMillis();
	  this.logger.info("��������Ӧʱ�� ��" + infoPre + " End   - Seconds: " + (timeEnd - timeBegin) / 1000.0D);
	
	  this.logger.info("-------------������ý���---------------");
	}
}
