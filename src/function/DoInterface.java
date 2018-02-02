package function;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tool.TimeGet;
import tool.ZZYFunctions;
import dao.CommonDao;
import douler.Main;
import douler.Public_Var;


public class DoInterface {
	
	static Logger logger = LogManager.getLogger(Main.class.getName());
	CommonDao cdao = new CommonDao();
	ZZYFunctions func = new ZZYFunctions();
	TimeGet tg = new TimeGet();
	
	public void doAutoWater(){
		//随机延迟一个时间1-3分钟
		
		java.util.Random random=new java.util.Random();
		int time=random.nextInt(180);
		System.out.println(time);
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Runtime.getRuntime().exec("curl "+Public_Var.autoWaterURL);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			e1.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(Public_Var.autoWaterURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.connect();
			httpURLConnection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
