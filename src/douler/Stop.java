/**
 * 程序停止
 */
package douler;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Stop extends Thread {
	static Logger logger = LogManager.getLogger(Stop.class.getName());

	public void run() {
		while (true) {
			new Thread();
			try {
				Thread.sleep(1000 * 5);
				File f = new File("stp/stp1");
				
				if (f.exists()) {
					f.delete();
					logger.info("程序手动退出。");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
