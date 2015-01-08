/*    */package com;

/*    */
/*    */import java.io.IOException;
/*    */
import java.io.OutputStreamWriter;
/*    */
import java.io.PrintStream;
/*    */
import java.net.HttpURLConnection;
/*    */
import java.net.MalformedURLException;
/*    */
import java.net.URL;
/*    */
import java.util.ArrayList;
/*    */
import java.util.List;
import org.apache.commons.io.IOUtils;

/*    */
/*    */public class Test
/*    */{
	/*    */public void test()
	/*    */{
		/*    */try
		/*    */{
			/* 34 */URL urlCach = new URL(
					"http://localhost:7001/mobileplat/AddCheckInfoServlet");
			/* 35 */HttpURLConnection urlconCach = (HttpURLConnection) urlCach
					.openConnection();
			/* 36 */urlconCach.setDoOutput(true);
			/* 37 */urlconCach.setRequestMethod("POST");
			/* 38 */urlconCach.setUseCaches(false);
			/* 39 */urlconCach.setDefaultUseCaches(false);
			/* 40 */OutputStreamWriter CachWriter = new OutputStreamWriter(
					urlconCach.getOutputStream(), "GBK");
			/*    */

			 
			String inxml1 = "<AddCheckInfoReq><ReqHeadVo><tokenId>123458</tokenId>" +
			"<userCode>0000107</userCode>"+
			"<flowinTime>2012-06-11 12:34:56</flowinTime>"+
			"</ReqHeadVo><optionType>2</optionType>"+
			"<CheckInfoVo><taskId>4640220422</taskId>"+
			"<registNo>605012012120000000275</registNo>"+
			"<checkSite>ss</checkSite>"+
			"<checkConText>��</checkConText>"+
			"<checkUserCode>0000107</checkUserCode>"+
			"<checkInDate>2012-10-10 15:21:59</checkInDate>"+
			"<checkStartDate>2012-10-10 15:21:59</checkStartDate>"+
			"<checkEndDate>2012-10-10 15:21:59</checkEndDate>"+
			"<inputDate>2012-10-10 15:21:59</inputDate>"+
			"<caseType>1</caseType>"+
			"</CheckInfovo><CarInfoVoList><CarInfoVo><serialNo>1</serialNo>"+
			"<lossItemType>050</lossItemType>"+
			"<licenseNo>��MJ0566</licenseNo>"+
		    "<dutyType>1</dutyType>"+
			"<isLoss>1</isLoss>"+
			"</CarInfoVo></CarInfoVoList></AddCheckInfoReq>";
			CachWriter.write(inxml1);
			/*    */
			/* 45 */CachWriter.flush();
			/* 46 */CachWriter.close();
			/*    */
			/* 48 */List ioList = new ArrayList();
			/*    */
			/* 50 */ioList = IOUtils.readLines(urlconCach.getInputStream());
			/* 51 */StringBuilder sb = new StringBuilder(1024);
			/*    */
			/* 53 */for (int j = 0; j < ioList.size(); j++) {
				/* 54 */sb.append(ioList.get(j));
				/*    */}
			/* 56 */System.out.println("---------" + sb.toString());
			/* 57 */urlconCach.getResponseMessage();
			/*    */
			/* 59 */urlconCach.disconnect();
			/*    */} catch (MalformedURLException e) {
			/* 61 */e.printStackTrace();
			/*    */} catch (IOException e) {
			/* 63 */e.printStackTrace();
			/*    */} catch (Exception e) {
			/* 65 */e.printStackTrace();
			/*    */}
		/*    */}

	/*    */
	/*    */public static void main(String[] args)
	/*    */{
		/*    */try
		/*    */{
			/* 76 */// URL urlCach = new
					// URL("http://localhost:7001/mobileplat/ActionRecordServlet");
			URL urlCach = new URL(
					"http://localhost:7001/mobileplat/AddCheckInfoServlet");
			/* 77 */HttpURLConnection urlconCach = (HttpURLConnection) urlCach
					.openConnection();
			/* 78 */urlconCach.setDoOutput(true);
			/* 79 */urlconCach.setRequestMethod("POST");
			/* 80 */urlconCach.setUseCaches(false);
			/* 81 */urlconCach.setDefaultUseCaches(false);
			/* 82 */OutputStreamWriter CachWriter = new OutputStreamWriter(
					urlconCach.getOutputStream(), "GBK");
			/*    */
			/* 85 */
			/* 42 */

			String inxml1 = "<AddCheckInfoReq><ReqHeadVo><tokenId>123458</tokenId>" +
			"<userCode>0000107</userCode>"+
			"<flowinTime>2012-06-11 12:34:56</flowinTime>"+
			"</ReqHeadVo><optionType>2</optionType>"+
			"<CheckInfoVo><taskId>4640220422</taskId>"+
			"<registNo>605012012120000000275</registNo>"+
			"<checkSite>ss</checkSite>"+
			"<checkConText>��</checkConText>"+
			"<checkUserCode>0000107</checkUserCode>"+
			"<checkInDate>2012-10-10 15:21:59</checkInDate>"+
			"<checkStartDate>2012-10-10 15:21:59</checkStartDate>"+
			"<checkEndDate>2012-10-10 15:21:59</checkEndDate>"+
			"<inputDate>2012-10-10 15:21:59</inputDate>"+
			"<caseType>1</caseType>"+
			"</CheckInfovo><CarInfoVoList><CarInfoVo><serialNo>1</serialNo>"+
			"<lossItemType>050</lossItemType>"+
			"<licenseNo>��MJ0566</licenseNo>"+
		    "<dutyType>1</dutyType>"+
			"<isLoss>1</isLoss>"+
			"</CarInfoVo></CarInfoVoList></AddCheckInfoReq>";

			StringBuffer stringBuilder = new StringBuffer();
			stringBuilder.append("<LoginReq>");
			stringBuilder.append("<ReqHeadVo>");
			stringBuilder
					.append("<tokenId>786d9d0b-1ec1-46af-b310-bf33a26dfea8</tokenId>");
			stringBuilder.append("<userCode>00000107</userCode>");
			stringBuilder
					.append("<flowinTime>2012/03/30 12:13:00</flowinTime>");
			stringBuilder.append("</ReqHeadVo>");
			stringBuilder.append("<password>1</password>");
			stringBuilder.append("</LoginReq>");

			/* 86 */CachWriter.write(inxml1);
			/*    */
			/* 88 */CachWriter.flush();
			/* 89 */CachWriter.close();
			/*    */
			/* 91 */List ioList = new ArrayList();
			/*    */
			/* 93 */ioList = IOUtils.readLines(urlconCach.getInputStream());
			/* 94 */StringBuilder sb = new StringBuilder(1024);
			/*    */
			/* 96 */for (int j = 0; j < ioList.size(); j++) {
				/* 97 */sb.append(ioList.get(j));
				/*    */}
			/* 99 */System.out.println("---------------" + sb.toString());
			/* 100 */urlconCach.getResponseMessage();
			/*    */
			/* 102 */urlconCach.disconnect();
			/*    */} catch (MalformedURLException e) {
			/* 104 */e.printStackTrace();
			/*    */} catch (IOException e) {
			/* 106 */e.printStackTrace();
			/*    */} catch (Exception e) {
			/* 108 */e.printStackTrace();
			/*    */}
		/*    */}

	/**
	 * �԰汾У�鼰�������й����Բ��ԣ� ������ 
	 * 1.�ͻ�����Ҫ������Ԥ�ڷ�����Ӧ���ģ� 
	 * 		a.����Ψһ��ʶ��
	 * 		b.�������ͣ�0��
	 * 		c.�Ƿ���£�1��
	 * 		d.����·����"lajflkajdla/jldjfsl/djldj.test" 
	 * 		e.�汾��Ϣ��"1.2.0"
	 * 
	 * 
	 * 2.�ͻ��˲���Ҫ������ 
	 * 		a.����Ψһ��ʶ��
	 * 		b.�������ͣ�0�� 
	 * 		c.�Ƿ���£�0��
	 * 
	 * 
	 * 3.ϵͳ�쳣�� 
	 * 		a.����Ψһ��ʶ�� 
	 * 		b.�������ͣ�-1��
	 * 		c.����������"ϵͳ�쳣"; 
	 * 		d.�Ƿ����:2;
	 * 		e.�汾��Ϣ:"1.2.3"��
	 */

	public void testCheckVersion() {
		String inBaseXMLHead  = "<VersionInfoReq>"
				+ "<ReqHeadVo>"
				+ "<tokenId>786d9d0b-1ec1-46af-b310-bf33a26dfea8</tokenId>"
				+ "<userCode>0000107</userCode>"
				+ "<flowinTime>2012/09/17 15:04:00</flowinTime>"
				+ "</ReqHeadVo>" ;
				
		
		StringBuffer sucBuffer = new StringBuffer();
		//��ӱ���ͷ��Ϣ
		sucBuffer.append(inBaseXMLHead);
		
		
		/**
		 * 1.�ͻ�����Ҫ����
		 */
		//�豸����
		sucBuffer.append("<equipmentType>");
		sucBuffer.append("2");
		sucBuffer.append("</equipmentType>");
		
		//Ӧ������
		sucBuffer.append("<applicationType>");
		sucBuffer.append("2");
		sucBuffer.append("</applicationType>");
		//Ӧ�ð汾��
		sucBuffer.append("<applicationVersionNo>");
		sucBuffer.append("123");
		sucBuffer.append("</applicationVersionNo>");
		
		
		//���ĸ�ʽ
		sucBuffer.append("<messageType>");
		sucBuffer.append("xml");
		sucBuffer.append("</messageType>");
		
		
		sucBuffer.append("</VersionInfoReq>");
		try {
			// �趨������ַ
			URL urlCach = new URL(
					"http://localhost:7001/mobileplat/CheckVersionServlet");
			HttpURLConnection urlconCach;
			try {
				// ����״̬Ϊ��
				urlconCach = (HttpURLConnection) urlCach.openConnection();
				// ���������Ϊ�棬Ĭ��Ϊfalse��������
				urlconCach.setDoOutput(true);
				// ���󷽷���post;
				urlconCach.setRequestMethod("POST");
				// ��ʹ�û��棻
				urlconCach.setUseCaches(false);
				// Ĭ�ϲ�ʹ�û��棻
				urlconCach.setDefaultUseCaches(false);
				// ��GBK���뷽ʽ��ȡ�������
				OutputStreamWriter cacheWriter = new OutputStreamWriter(
						urlconCach.getOutputStream(), "GBK");
				
				//д�뻺��
				cacheWriter.write(sucBuffer.toString());
				
				cacheWriter.flush();
				cacheWriter.close();
				
				List ioList = new ArrayList();
				//�������ؽ��
				ioList = IOUtils.readLines(urlconCach.getInputStream());
				
				
				StringBuffer sucResult = new StringBuffer(1024);
				
				if(null != ioList && ioList.size()>0){
					for(int i=0;i<ioList.size();i++){
						sucResult.append(ioList.get(i));
					}
				}
				
				
				System.out.println("��Ҫ������"+sucResult.toString());
				System.out.println(urlconCach.getResponseMessage());
				
				urlconCach.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*    */
}

/*
 * Location:
 * C:\Users\DeathSilence\Desktop\mobileplat\mobileplat\WEB-INF\classes\
 * Qualified Name: com.Test JD-Core Version: 0.6.0
 */