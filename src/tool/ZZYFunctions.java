/**
 * 个人常用函数库
 */
package tool;

import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import douler.Public_Var;

public class ZZYFunctions {

	/*
	 * 将时间转换为时间戳
	 */
	public long dateToStamp(String s) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ts = date.getTime();
		return ts;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/*
	 * 获取某天 晚上23:59:59秒的时间戳
	 */

	public long getTonightStamp(String riqi) {
		riqi = riqi + " 23:59:59";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(riqi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ts = date.getTime();
		return ts;
	}

	public long getRandom(int i) {
		// TODO Auto-generated method stub
		return (long) (Math.random() * Math.pow(10, i));

	}

	public File[] getFiles(final String type) {
		File[] files = new File(Public_Var.input).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if(name.startsWith(type)){
					return true;
				}
				// TODO Auto-generated method stub
				return false;
			}

		});
		return files;
	}
}
