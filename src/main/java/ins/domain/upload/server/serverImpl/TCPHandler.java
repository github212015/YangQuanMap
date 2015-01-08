package ins.domain.upload.server.serverImpl;

import ins.domain.schema.model.FileInfoMsg;
import ins.domain.schema.vo.MsgInfoVo;
import ins.domain.upload.server.TCPServer;
import ins.domain.upload.service.facade.FileInfoMsgService;
import ins.domain.util.BASE64Encoder;
import ins.domain.util.Converter2Type;
import ins.framework.common.ServiceFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import org.apache.log4j.Logger;
/**
 * TODO Put here a description of what this class does.
 * ��������Ϊÿ��socket�ṩ�Ĵ�����
 * Created 2012-10-17.
 */
public class TCPHandler implements Runnable{

	private Logger log = Logger.getLogger(TCPHandler.class);
	
	//���Կͻ��˵�socket
	private Socket socket=null;
	
	//�߳̿���
	private boolean isUpload = false;
	//����ʹ�õ���
	private InputStream in;
	private OutputStream out;
	private DataOutputStream Oout;
	private DataInputStream Oin;
    
	private FileInfoMsg vo=null;
	//���ձ������ݿ��
	private FileInfoMsg fvo=null;
	// �ļ���������
	private File file = null;
	
	// �ϵ���;
	private RandomAccessFile random = null;
	
	public TCPHandler(Socket socket){
		this.socket=socket;
	}
	
	public void run() {
		try {
			init();
			auth();
			transfer();
		} catch (Exception e) {
			this.log.info("�ͻ����̹߳ر�:"+e.getMessage());
		}finally{
			destroy();
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @throws IOException
	 */
	public void init() throws IOException {
		this.in = this.socket.getInputStream();
		this.Oin = new DataInputStream(this.in);
		this.out = this.socket.getOutputStream();
		this.Oout = new DataOutputStream(this.out);
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @throws Exception
	 */
	public void auth() throws Exception{
		this.log.info("---����������֤��");
		this.log.info("�ͻ��˵�ַ��"+this.socket.getInetAddress());
		// ��ȡ��֤��Ϣ ���ҽ���
		String request = this.Oin.readUTF();
		this.log.info("rqeuest="+request);
		this.vo=Converter2Type.FileInfoVo2Object(request);
	
		
	    //�����оʹ�Map����  û�оʹ����������
	    if(TCPServer.datas.containsKey(this.vo.getBizId())){
	    	
	    	this.vo=TCPServer.datas.get(this.vo.getBizId());
	    	//����������о�ֱ���ó���
	    	this.file=new File(this.vo.getFilePath().substring(0 ,this.vo.getFilePath().lastIndexOf(this.vo.getFileName()) -1),this.vo.getFileName());
	    }else{
	    	FileInfoMsgService fileInfoMsgService =(FileInfoMsgService) ServiceFactory.getService("fileInfoMsgService");
			FileInfoMsg msg=fileInfoMsgService.getFileInfoMsg(this.vo.getBizId());
			if(msg != null){
				this.vo = msg;
				this.file=new File(msg.getFilePath());
			}else{
				String path=this.vo.getTaskId();
				//���ô�ŵ�λ���뵱ǰӦ�õ�λ���й�
				File dir=new File("checkPic" + File.separator + path);
				//�ж��Ƿ����
				if(!dir.exists()){
					dir.mkdirs();
				}
				this.file=new File(dir,this.vo.getFileName());
				//�����ļ��ڷ������ϵ�·��
				
				this.vo.setFilePath(this.file.getAbsolutePath());
				TCPServer.datas.put(this.vo.getBizId(),this.vo);
			}
	    }
	    
		//���߿ͻ��˴��Ǹ�λ�ÿ�ʼ����
	    MsgInfoVo ms=new MsgInfoVo();
	    ms.setOffset(this.vo.getOffset());
	    ms.setSize(this.vo.getFileSize());
	    ms.setState(1);
	    
	    String response=Converter2Type.MsgInfo2String(ms);
	    this.log.info("response="+response);
	    //��д����
	    this.Oout.writeUTF(response);
	   
	    this.random=new RandomAccessFile(this.file,"rwd");
		this.isUpload=true;
		this.log.info("---������֤����,����·��Ϊ:"+this.file.getAbsolutePath());
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @throws IOException
	 */
	public void transfer() throws IOException{
		this.log.info("---���ı��濪ʼ");
		if(this.isUpload){
			byte[] by=new byte[1024];
		   	this.random.seek(this.vo.getOffset());
		   	int len=-1;
		   	//�õ������еı��μ�¼ ��¼ƫ����
		    this.fvo=TCPServer.datas.get(this.vo.getBizId());
		    
		    while((len=this.in.read(by))!=-1)
		    {
		    	
		    	System.out.println("�ļ������С��"+len);
		    	//ͼƬ�������ֶ�
//		        String str= BASE64Encoder.encode(by);
//		        this.fvo.setFileData(this.fvo.getFileData() + str);
		        
		    	this.random.write(by,0,len);
		    	
		    	//�õ��ļ������ƫ��������Ϊ�������������Ըı��ᱣ�������
		    	this.log.info("�ļ��Ĵ�����ȣ�"+this.random.getFilePointer());
		    	//��¼ƫ����
		    	this.fvo.setOffset(this.random.getFilePointer());
		    }
		    
		    this.log.info("----�ļ�������ϣ�");
		 
		}
	 
		this.log.info("---���ı�����ϣ�");
	}
	
	/**
	 * �̹߳ر�
	 *
	 */
	public void destroy() {
		//�������ݿⱣ��......������
		try{
			//���������� ��Map�е����������
		    if(this.fvo!=null){
		    	FileInfoMsgService fileInfoMsgService =(FileInfoMsgService) ServiceFactory.getService("fileInfoMsgService");
				FileInfoMsg msg=fileInfoMsgService.getFileInfoMsg(this.fvo.getBizId());
				if(msg!=null&&msg.getFileName()!=null&&!msg.getFileName().equals("")){
				   fileInfoMsgService.updateFileInfoMsg(this.fvo);
				}else{
				   fileInfoMsgService.addFileInfoMsg(fvo);
				}
				if((this.fvo.getOffset() + "").equals(this.fvo.getFileSize() + "")){
		    	    TCPServer.datas.remove(fvo.getBizId());
				}
		    }
		}catch(Exception e){
			this.log.info("--����ͼƬ��Ϣ�����쳣��");
			System.err.println("--����ͼƬ��Ϣ�����쳣���쳣��ϢΪ��"+e.getMessage());
		}
		
		// �������ѭ�� ��ʾ����Ҫ�Ѿ�������� ���Թر���Դ��
		this.log.info("���̹߳ر� ");
		try {
			this.isUpload = false;
			if(this.in!=null){
				this.in.close();
			}
			if(this.Oin!=null){
				this.Oin.close();
			}
			if(this.out!=null){
				this.out.close();
			}
			if(this.Oout!=null){
				this.Oout.close();
			}
			if(this.random!=null){
				this.random.close();
			}
			if(this.socket!=null){
				this.socket.close();	
			}
		} catch (Exception e) {
              this.log.info("�ر��߳�ʧ�ܣ�"+e.getMessage());
		}
	}
	

}
