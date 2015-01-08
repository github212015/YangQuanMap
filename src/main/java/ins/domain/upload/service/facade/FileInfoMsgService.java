package ins.domain.upload.service.facade;

import java.util.List;

import ins.domain.schema.model.FileInfoMsg;

/**
 * �����ϴ��ļ���Ϣ
 * 2012-10-22
 */
public interface FileInfoMsgService {
	/**
	 * ������Ϣ
	 * @param fileInfoMsg
	 */
    public void addFileInfoMsg(FileInfoMsg fileInfoMsg);
    /**
     * ��ѯ��taskId�µ�����ͼƬ
     * 
     */
    public List<FileInfoMsg> getFileInfoMsgList(String taskId);
    /**
     * ��ѯ���������µ�����ͼƬ
     * @param registNo
     * @return
     */
    public List<FileInfoMsg> getFileInfoMsgListByRegistNo(String registNo);
    
    /**
     * @param taskId �����
     * @return ���ش��ڵ���Ϣ
     */
    public FileInfoMsg getFileInfoMsg(String bizId);
    /**
     * �������ݿ����Ϣ 
     * @param fileInfoMsg
     */
    public void updateFileInfoMsg(FileInfoMsg fileInfoMsg);
    
    /**
     * @param bizId
     */
    public void deleteFileInfoMsg(String bizId);
    
    public void deleteInvalidPhotos();
}
