package ins.domain.util;

import ins.domain.schema.model.FileInfoMsg;
import ins.domain.upload.server.TCPServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileInfoUtil {
	
    public static List<FileInfoMsg> getFileInfoMsgList(String taskId){
    	List<FileInfoMsg> list=new ArrayList<FileInfoMsg>();
    	//�õ��ϴ�������ͼƬ��Ϣ����
        Map<String,FileInfoMsg> data=TCPServer.datas;
        for (String item : data.keySet()) {
        	FileInfoMsg msg=data.get(item);
        	if(msg.getTaskId().equals(taskId)){
        		list.add(msg);
        		//�����Ƴ���
        		TCPServer.datas.remove(item);
        	}
		}
    	return list;
    }
}
