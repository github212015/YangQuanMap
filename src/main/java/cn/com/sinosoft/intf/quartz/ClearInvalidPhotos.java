package cn.com.sinosoft.intf.quartz;

import ins.domain.upload.service.facade.FileInfoMsgService;
import ins.framework.common.ServiceFactory;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ClearInvalidPhotos extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub

		System.out.println(System.currentTimeMillis() + "��ʱ����������");
		FileInfoMsgService fileInfoMsgService = (FileInfoMsgService)ServiceFactory.getService("fileInfoMsgService");
		fileInfoMsgService.deleteInvalidPhotos();
		System.out.println(System.currentTimeMillis() + "��ʱ����ִ����ϣ�");
	}

}
