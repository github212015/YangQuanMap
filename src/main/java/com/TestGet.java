package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis.client.ServiceFactory;
import org.apache.commons.codec.binary.Base64;

public class TestGet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String a = "{&quot;policyNo&quot;:&quot;305072011110000009169&quot;,&quot;registNo&quot;:null,&quot;insuredName&quot;:&quot;������&quot;,&quot;licenseNo&quot;:&quot;��J00031&quot;,&quot;insuredTel&quot;:null,&quot;insuredCom&quot;:&quot;�����ֹ�˾Ӫҵһ���г��Ŷ�&quot;,&quot;startDate&quot;:&quot;2011-06-28&quot;,&quot;endDate&quot;:&quot;2012-06-27&quot;,&quot;policyType&quot;:&quot;��ǿ��&quot;,&quot;customerTypeName&quot;:&quot;��ͨ�ͻ�&quot;,&quot;vipFlagName&quot;:&quot;&quot;,&quot;damageCount&quot;:0,&quot;itemInfo&quot;:[{&quot;kindCode&quot;:&quot;0507&quot;,&quot;kindName&quot;:&quot;��������ͨ�¹�����ǿ����&quot;,&quot;amount&quot;:122000,&quot;premium&quot;:878.46,&quot;exceptFlag&quot;:null}],&quot;endorseInfo&quot;:null,&quot;engageInfo&quot;:[{&quot;clausecode&quot;:&quot;T0111&quot;,&quot;clauses&quot;:&quot;�𾴵Ŀͻ���Ͷ��������������ͨ����˾��վhttp��//cha.bocins.com���ͷ�ר��40069 95566��Ӫҵ�����̨��ѯ�������������Ϣ�����Բ�ѯ��������飬������˾��վ�����Ի򲦴���˾24Сʱ�ͻ�ר��40069 95566��һ����ʵ��\n&quot;},{&quot;clausecode&quot;:&quot;T0100&quot;,&quot;clauses&quot;:&quot;���ĳ�����β�����з�Χ���������н�ǿ�ձ��Ѽ��⣬���Ᵽ�� 71.54 Ԫ�����㷽�� ���Ᵽ��=(636.89/365*41) ������󱣷�Ϊ ��׼���ѽ��-���Ᵽ�ѽ��=�������\n&quot;}]}";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(df.format(new Date()));
//		System.out.println(System.currentTimeMillis());
//		System.out.println(Runtime.getRuntime().availableProcessors()*50*1024);
//		FileInputStream in;
//		try {
//			in = new FileInputStream(new File("E:\\20121218134513.jpg"));
//			byte[] ib7;
//			try {
//				ib7 = new byte[in.available()];
//				in.read(ib7);
//				byte[] bb7 = Base64.encodeBase64(ib7);
//				String fileData = new String(bb7);
//				System.out.println(fileData);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	       BufferedInputStream bis=new BufferedInputStream(in,1024*1024);
//			System.out.println(new File("E:\\20121218134513.jpg"));

		String  a = ",a,b,c,d,";
		System.out.println(a.indexOf(",c"));
	}

}
