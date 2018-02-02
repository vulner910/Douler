package douler;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tool.TimeGet;
import dao.CommonDao;

public class ReadConfig {

	Logger logger = LogManager.getLogger(ReadConfig.class.getName());
	CommonDao cdao = new CommonDao();

	TimeGet tg = new TimeGet();

	public void doReadConfig() {
		if(!Public_Var.PROP){
			return;
		}
		logger.info("读取配置文件config.properties");
		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("config.properties");
			pro.load(in);
			Public_Var.driver = pro.getProperty("driver");
			Public_Var.url = pro.getProperty("url");
			Public_Var.username = pro.getProperty("username");
			Public_Var.password = pro.getProperty("password");
		
			
			in.close();
		} catch (Exception e) {
			logger.info("初始化配置文件config.properties错误("+e+")，程序退出。");
			System.exit(0);
		}
	}
}
