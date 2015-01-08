package com.sinosoft.test;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 * TODO Put here a description of what this class does.
 * 
 * Created 2012-10-17.
 */
public class Client {
	// ��Ҫ���ӵ�IP��ַ
	private String ip = "192.168.1.101:7001";
	// �ϴ��Ķ˿ں�
	private int port = 9527;

	private Socket s;
	private InputStream in = null;
	private OutputStream out = null;

	private DataOutputStream dout;
	private DataInputStream din;
    
	//������ͣ��ʼ
	public boolean con=false;
	// �ļ����� ����
	private File file = null;
	private RandomAccessFile random = null;
//	private PhotoInfo  photo;//��Ƭ�����ϴ���ı�״̬
//	private FileInfoMsg info = null;
//	private MsgInfoVo msg = null;
//	private Handler handler;//���һ����Ϣ�巢���ϴ�����
	private long offsetLength = 0;
	 private  boolean  isFinish;//�����Ƿ����
//	 private Context  context;
	 private  boolean  isFirst = true;
	 
	 
	 private String msg;
	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param file
	 * @param info
	 * @param port
	 * @param ip
	 */
//	public Client(File file, FileInfoMsg info, int port, String ip,Handler handler,PhotoInfo  photo) {
//		this.file = file;
//		this.info = info;
//		this.port = port;
//		this.ip = ip;
//		this.handler = handler;
//		this.photo = photo;
//		this.context = ConfigHelper.context;
//		this.isFirst = true;
//	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * ��ʼ��
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException {
		this.s = new Socket(this.ip, this.port);
		this.in = this.s.getInputStream();
		this.din = new DataInputStream(this.in);
		this.out = this.s.getOutputStream();
		this.dout = new DataOutputStream(this.out);
		this.random = new RandomAccessFile(this.file, "r");
	}

	/**
	 * 
	 * ��֤
	 * 
	 * @throws IOException
	 */
	public void auth() throws IOException {
		System.out.println("---��������������");
		this.dout.writeUTF("bizId=20122421022428.jpg,serialNo=1,taskId=104360515072386,fileName=20122421022428.jpg,filePath=/sdcard/ClaimDemo/20122421022428_s.jpg,offset=0,fileType=5001,fileRemark=,fileSize=482679,fileAddress=����,fileDate=2012-12-21 14:24:28,fileData=,addfileType=5001,carId=1,fileOriginalInfo =����ʱ�䣺2012:12:21 14:24:25<br>         EXIF�汾��48 50 50 48<br>         ���Ʒ�ƣ�ZTE                            <br>         ����ͺţ�ZTE U960s3                     <br>         ��Ȧ��2.8<br>         ���T��99994/1000000<br>,fileKeyType=MD5,fileKeyValue=D29AE806626A1C30B92C9CA05685BF1C");
		System.out.println("---�������ݽ������ȴ���������");
		this.msg = this.din.readUTF();
		System.out.println("---��������Ϊ��" + this.msg);
//		this.msg = Converter2Type.MsgInfo2Object(line);
		System.out.println("---��֤����");
	}

	/**
	 * ִ���ļ�����
	 * 
	 * @throws Exception
	 */
	public void transfer( ) throws Exception {
		byte[] by = new byte[1024];
		int length = -1;
		// �����ļ��Ĵ���
		System.out.println("---�������Ĵ���׶�");
//		System.out.println("offset=" + this.msg.getOffset());
//		// ����ƫ����
//		this.random.seek(this.msg.getOffset());
		// �ۼƽ���
		if ("111111".equals(this.msg)) {
				
			System.out.println("�ļ��Ѿ��������");
			
		}else{
			System.out.println("ʧ��");
		}
	}

	/**
	 * @return �ļ�����Ľ���
	 */
	public long getOffsetLength() {
		return this.offsetLength;
	}

	/**
	 * �̹߳ر�
	 * 
	 */
	public void destroy() {
		// �������ѭ�� ��ʾ����Ҫ�Ѿ�������� ���Թر���Դ��
		// System.out.println("������ : ���ݴ����ж� ��ʼ�ƺ�");
		try {
			if (this.in != null) {
				this.in.close();
			}
			if (this.din != null) {
				this.din.close();
			}
			if (this.out != null) {
				this.out.close();
			}
			if (this.dout != null) {
				this.dout.close();
			}
			if (this.random != null) {
				this.random.close();
			}
			if (this.s != null) {
				this.s.close();
			}
		} catch (Exception e) {
			System.err.println("�ر��̷߳����쳣��");
		}
	}

	public void run() {
		
		try {
			System.out.println("rrrrrrrrrrrrrrrrrr");
			this.init();
			this.auth();
			this.transfer();
		
		} catch (Exception e) {
			System.err.println("���ʷ������������ݷ����쳣��");
			e.printStackTrace();
		} finally {
			this.destroy();
		}
	}
	/**
	 * ��ͣ
	 */
	public void stop(){
		try {
		  this.con=true;
//		  ConfigHelper.isStopUpload = true;
		} catch (Exception e) {
			System.err.print("�ͻ�����ͣ��");
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ
	 */
	public void begin(){
		this.con=false;
//		ConfigHelper.isStopUpload = false;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
	
}
