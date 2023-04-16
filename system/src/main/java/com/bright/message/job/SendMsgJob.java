package com.bright.message.job;//package com.brightcomplete.modules.message.job;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.brightcomplete.modules.message.entity.SysMessage;
//import com.brightcomplete.modules.message.handle.enums.SendMsgStatusEnum;
//import com.brightcomplete.modules.message.service.ISysMessageService;
//import lombok.extern.slf4j.Slf4j;
//import com.brightcomplete.common.api.dto.message.MessageDTO;
//import com.brightcomplete.common.system.api.ISysBaseAPI;
//import com.brightcomplete.common.util.DateUtils;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
///**
// * 发送消息任务
// */
//
//@Slf4j
//public class SendMsgJob implements Job {
//
//	@Autowired
//	private ISysMessageService sysMessageService;
//
//	@Autowired
//	private ISysBaseAPI sysBaseAPI;
//
//	@Override
//	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//
//		log.info(String.format(" 发送消息任务 SendMsgJob !  时间:" + DateUtils.getTimestamp()));
//
//		// 1.读取消息中心数据，只查询未发送的和发送失败不超过次数的
//		QueryWrapper<SysMessage> queryWrapper = new QueryWrapper<SysMessage>();
//		queryWrapper.eq("es_send_status", SendMsgStatusEnum.WAIT.getCode())
//				.or(i -> i.eq("es_send_status", SendMsgStatusEnum.FAIL.getCode()).lt("es_send_num", 6));
//		List<SysMessage> sysMessages = sysMessageService.list(queryWrapper);
//		System.out.println(sysMessages);
//		// 2.根据不同的类型走不通的发送实现类
//		for (SysMessage sysMessage : sysMessages) {
//			// 模板消息发送测试调用方法修改
//			Integer sendNum = sysMessage.getEsSendNum();
//			try {
//				MessageDTO md = new MessageDTO();
//				md.setTitle(sysMessage.getEsTitle());
//				md.setContent(sysMessage.getEsContent());
//				md.setToUser(sysMessage.getEsReceiver());
//				md.setType(sysMessage.getEsType());
//				md.setToAll(false);
//				sysBaseAPI.sendTemplateMessage(md);
//				//发送消息成功
//				sysMessage.setEsSendStatus(SendMsgStatusEnum.SUCCESS.getCode());
//			} catch (Exception e) {
//				e.printStackTrace();
//				// 发送消息出现异常
//				sysMessage.setEsSendStatus(SendMsgStatusEnum.FAIL.getCode());
//			}
//			sysMessage.setEsSendNum(++sendNum);
//			// 发送结果回写到数据库
//			sysMessageService.updateById(sysMessage);
//		}
//
//	}
//
//}
