package ins.domain.schema.vo;


import java.io.Serializable;

/**
 * ���������ͻ��˴��͵ļ�Ҫ��Ϣ
 *
 */
@SuppressWarnings("serial")
public class MsgInfoVo implements Serializable{
	//�ļ���С �ֽ�
	private long size = 0;
	//�ļ���Ҫ�����Լ���ƫ����
	private long offset = 0;
	//0���� ������ݿ⽻���ɹ�
	//1���д���
	//2���� ��ν�������
	//3���� ��������⵽�ļ�β�� ֪ͨ�ͻ���׼���˳�
	private int state=0;
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
