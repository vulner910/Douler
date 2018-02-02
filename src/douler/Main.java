package douler;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tool.TimeGet;
import dao.CommonDao;

/**
 * 主入口
 * 
 * @author Administrator
 *
 */
public class Main {
	static Logger logger = LogManager.getLogger(Main.class.getName());
	CommonDao cdao = new CommonDao();

	TimeGet tg = new TimeGet();

	public static void main(String[] args) {

		try {
			logger.info("程序启动，当前版本："+Public_Var.version);
			
			// 初始化配置信息
			if(Public_Var.PROP)
				new ReadConfig().doReadConfig();
			//new Setup().doSetup();
			// 启动程序停止器
			System.out.println(Public_Var.url);
			new Stop().start();
			
			//创建文件目录
			new File(Public_Var.input).mkdirs();
			new File(Public_Var.output).mkdirs();
			
			
			// 启动结算触发器
			
			try {
				while (true) {
					Settlement s = new Settlement();
					s.run();
					if(Public_Var.debug)
						break;
				}
			} catch (Exception e) {
				logger.error("触发器：" + e);
			}

		} catch (Exception e) {
			logger.error(e);
		}
//		System.exit(0);
	}

}
