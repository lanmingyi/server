//package com.brightcomplete;
//
//import com.brightcomplete.modules.demo.mock.MockController;
//import com.brightcomplete.modules.demo.test.entity.Demo;
//import com.brightcomplete.modules.demo.test.mapper.DemoMapper;
//import com.brightcomplete.modules.demo.test.service.IDemoService;
//import com.brightcomplete.modules.system.service.ISysDataLogService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = SystemApplication.class)
//public class SampleTest {
//
//	@Resource
//	private DemoMapper demoMapper;
//	@Resource
//	private IDemoService demoService;
//	@Resource
//	private ISysDataLogService sysDataLogService;
//	@Resource
//	private MockController mock;
//
//	@Test
//	public void testSelect() {
//		System.out.println(("----- selectAll method test ------"));
//		List<Demo> userList = demoMapper.selectList(null);
//		Assert.assertEquals(5, userList.size());
//		userList.forEach(System.out::println);
//	}
//
//	@Test
//	public void testXmlSql() {
//		System.out.println(("----- selectAll method test ------"));
//		List<Demo> userList = demoMapper.getDemoByName("Sandy12");
//		userList.forEach(System.out::println);
//	}
//
//	/**
//	 * 测试事务
//	 */
//	@Test
//	public void testTran() {
//		demoService.testTran();
//	}
//
//	/**
//	 * 测试数据日志添加
//	 */
//	@Test
//	public void testDataLogSave() {
//		System.out.println(("----- datalog test ------"));
//		String tableName = "demo";
//		String dataId = "4028ef81550c1a7901550c1cd6e70001";
//		String dataContent = mock.sysDataLogJson();
//		sysDataLogService.addDataLog(tableName, dataId, dataContent);
//	}
//}
