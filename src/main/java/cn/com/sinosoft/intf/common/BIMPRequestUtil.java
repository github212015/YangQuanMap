package cn.com.sinosoft.intf.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;



public class BIMPRequestUtil {

	private static final Logger logger = Logger.getLogger(BIMPRequestUtil.class);
	
	public String send(String json) throws Exception {
		String urlTemp = "";	
		double start = System.currentTimeMillis();

		String msgXML = "";
		HttpURLConnection httpConnection;
//	    IPServiceConfig  ipServiceConfig  = DictAPIService.getServiceInfoByCode("scms");
//	    urlTemp = ipServiceConfig.getProteclType()+"://"+ipServiceConfig.getServerIP()+":"+ipServiceConfig.getServerPort()+"/"+ipServiceConfig.getServerAppName().trim()+"/"+ipServiceConfig.getMethods();
		urlTemp = "http://22.8.142.17:8108/mobileService-lioneye/ifc";
	    try {
			httpConnection = (HttpURLConnection) new URL(urlTemp)
					.openConnection();

			httpConnection.setRequestMethod("POST");
//			httpConnection.setRequestProperty("content-type",
//					"text/xml,charset=GBK");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setAllowUserInteraction(true);
			httpConnection.setConnectTimeout(50 * 1000);
			httpConnection.setReadTimeout(300 * 1000);
			httpConnection.connect();

			// 2����������
			OutputStream outputStream = httpConnection.getOutputStream();
			long time = System.currentTimeMillis();
			outputStream.write(json.getBytes());

			outputStream.flush();
			outputStream.close();
			// 3����������
			long time2 = System.currentTimeMillis();
			InputStreamReader inputStreamReader = new InputStreamReader(
					httpConnection.getInputStream(), "GBK");

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String inputLine = "";
			StringBuffer inputLines = new StringBuffer();
			inputLine = bufferedReader.readLine();
			while (inputLine != null) {
				inputLines.append(inputLine);
				inputLine = bufferedReader.readLine();
			}
			inputStreamReader.close();
			bufferedReader.close();

			// 4���ر�����
			httpConnection.disconnect();
			msgXML = inputLines.toString();

		} catch (MalformedURLException e) {
			logger.error("������Ϣ:" + e.toString());
			throw new Exception("������Ϣ:" + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("������Ϣ:" + e.toString());
			throw new Exception("������Ϣ:" + e.toString());
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("������Ϣ:" + e.toString());
		}
		System.out.println("lizhen=(�뾫�ѽ���ʱ��)="
				+ (System.currentTimeMillis() - start));
		return msgXML;
	}
}
