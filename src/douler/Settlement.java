package douler;

import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tool.TimeGet;
import dao.CommonDao;
import function.DoFiles;
import function.DoInterface;

public class Settlement {
	static Logger logger = LogManager.getLogger(Settlement.class.getName());
	CommonDao cdao = new CommonDao();

	TimeGet tg = new TimeGet();

	public void run() {
		try {
			// 判断是否有程序在运行，防止多进程，数据错乱。标志用数据库
			if (cdao.getProgramRunningStatus() == 1 && !Public_Var.debug) {
				logger.info("有相同程序在运行，程序退出。");
				System.exit(0);
			}
			new Thread();
			Thread.sleep(50);// 50ms后再判断一次，保证没有时间差。
			if (cdao.getProgramRunningStatus() == 1 && !Public_Var.debug) {
				logger.info("有相同程序在运行，程序退出。");
				System.exit(0);
			}
			// 将运行状态设为运行
			cdao.setProgramRunningStatus(1);
			long startTime = System.currentTimeMillis();
			//初始化配置信息
			logger.info("初始化配置信息");
			cdao.setUp();
			
			Public_Var.sqls = new Vector<String>();
			//处理MEMBERS文件
			logger.info("处理MEMBERS文件");
			new DoFiles().doMembers();
			//处理BUY文件
			logger.info("处理BUY文件");
			new DoFiles().doBuy();
			//处理TRANSFER文件
			logger.info("处理TRANSFER文件");
			new DoFiles().doTransferOrGive("transfer");
			//处理GIVE文件
			logger.info("处理GIVE文件");
			new DoFiles().doTransferOrGive("give");
			//处理FREEZE文件
			logger.info("处理FREEZE文件");
			new DoFiles().doFreeze("freeze");
			//处理UNFREEZE文件
			logger.info("处理UNFREEZE文件");
			new DoFiles().doFreeze("unfreeze");
			//处理RECYLE文件
			logger.info("处理RECYCLE文件");
			new DoFiles().doFreeze("recycle");
			//处理PWD文件
			logger.info("处理PWD文件");
			new DoFiles().doPwd();
			//调用自动浇水接口
			logger.info("调用自动浇水接口");
			new DoInterface().doAutoWater();
			
			
			logger.info("程序轮训第" + ++Public_Var.runTimes + "次，结束。用时：" + (System.currentTimeMillis() - startTime) / 1000 + "秒。");
			// 将运行状态设为空闲
			
			Thread.sleep(Public_Var.restTime);
			
			cdao.setProgramRunningStatus(0);
			// 休息一个时间
		} catch (Exception e) {
			logger.error("大循环错误：" + e);
			// TODO: handle exception
		}
	}
}
