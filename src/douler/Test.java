package douler;

import dao.CommonDao;
import function.DoInterface;


public class Test {
	static CommonDao cdao = new CommonDao();
	public static void main(String[] args) {
	new DoInterface().doAutoWater();
		
	}
}
