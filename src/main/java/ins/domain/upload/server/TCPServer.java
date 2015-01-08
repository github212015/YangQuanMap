package ins.domain.upload.server;


import ins.domain.schema.model.FileInfoMsg;
import ins.domain.upload.server.serverImpl.TCPHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

/**
 * TODO Put here a description of what this class does.
 *
 * Created 2012-10-17.
 */
public class TCPServer {
	    //��־��¼
		private Logger logger=Logger.getLogger(TCPServer.class);
	    //�̳߳�
		private ExecutorService executorService;
		//�����˿�
		private int port;
		//�˳�
		private boolean quit=false;
		//������
		private ServerSocket server;
		//��֤��Ϣ
		public static Map<String,FileInfoMsg> datas =new HashMap<String,FileInfoMsg>();
	 
		/**
		 * TODO Put here a description of what this constructor does.
		 *
		 * @param port
		 */
		public TCPServer(int port){
			//�趨�˿ں�
			this.port=port;
			//�����̳߳أ����о��У�cup����*50�����߳�
			this.executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
		}
		/**
		 * �˳�
		 */
		@SuppressWarnings("deprecation")
		public void quit(){
			this.quit=true;
			try{
				this.server.close();
			}catch(IOException e){
				this.logger.info("��ǰʱ��Ϊ :"+new Date().toLocaleString()+",�رշ����������쳣");
				e.printStackTrace();
			}
		}
		
		/** 
		  * �������� 
		  * @throws Exception 
		  */  
		 public void start() throws Exception {  
		     //ʵ�ֶ˿ڼ���  
		     this.server = new ServerSocket(this.port); 
		     this.logger.info("�����������˿ں�Ϊ="+this.server.getLocalPort());
		     while(!this.quit){  
		          try{  
		               Socket socket = this.server.accept();  
		               //Ϊ֧�ֶ��û��������ʣ������̳߳ع���ÿһ���û�����������  
		               this.executorService.execute(new TCPHandler(socket));  
		             }   
		             catch (Exception e)   
		             {  
		                 e.printStackTrace();  
		             }  
		        }  
		 }  

}
