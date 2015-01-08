package ins.domain.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * �ϵ���Ϣ
 * 
 * 
 */
@Entity
@Table(name="FileInfoMsg")
public class FileInfoMsg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ҵ��Ψһ�� ����ҵ���Ŵ���Ŀ¼ ����Ψһ
	private String bizId;
	//���к�
    private int serialNo;
	//����� ��ʶͼƬ��ͳһ��
    private String taskId;
	// ��Ƭ����
	private String fileName;
	// �ļ�����·��
	private String filePath;
	// ��Ƭ�Ѿ������˶����ֽڵ�����
	private Long offset = new Long(0);
	//ͼƬ����
	private String fileType;
	//ͼƬ��ע
	private String fileRemark;
	// ��Ƭһ����Ҫ���Ͷ����ֽڵ�����
	private Long fileSize=new Long(0);
	// ͼƬ�����ַ
	private String fileAddress;
	//����ʱ��
	private String fileDate;
	//
	private String addfileType;
	//����ID
	private String carId;
	//��Ƭԭʼ��Ϣ
	private String fileOriginalInfo;
	//�ļ���Կ����
	private String fileKeyType;
	//�ļ���Կֵ
	private String fileKeyValue;
	
	@Id
	@Column(name = "bizId",length=24)
	public String getBizId() {
		return this.bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	@Column(name="serialNo")
	public int getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name="taskId",length=24)
	public String getTaskId() {
		return this.taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="fileName",length=50)
	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="filePath",length=255)
	public String getFilePath() {
		return this.filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="offset",length=19)
	public Long getOffset() {
		return this.offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	@Column(name="fileType",length=15)
	public String getFileType() {
		return this.fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name="fileRemark",length=100)
	public String getFileRemark() {
		return this.fileRemark;
	}
	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
	}
	@Column(name="fileSize",length=19)
	public Long getFileSize() {
		return this.fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="fileAddress",length=50)
	public String getFileAddress() {
		return this.fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	@Column(name="fileDate",length=25)
	public String getFileDate() {
		return this.fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	@Column(name="addfileType",length=2)
	public String getAddfileType() {
		return this.addfileType;
	}
	public void setAddfileType(String addfileType) {
		this.addfileType = addfileType;
	}
	@Column(name="carId",length=24)
	public String getCarId() {
		return this.carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name="fileKeyValue")
	public String getFileKeyValue() {
		return fileKeyValue;
	}
	public void setFileKeyValue(String fileKeyValue) {
		this.fileKeyValue = fileKeyValue;
	}
	@Column(name="fileOriginalInfo")
	public String getFileOriginalInfo() {
		return fileOriginalInfo;
	}
	public void setFileOriginalInfo(String fileOriginalInfo) {
		this.fileOriginalInfo = fileOriginalInfo;
	}
	@Column(name="fileKeyType")
	public String getFileKeyType() {
		return fileKeyType;
	}
	public void setFileKeyType(String fileKeyType) {
		this.fileKeyType = fileKeyType;
	}
	
	 
 
	 
}
