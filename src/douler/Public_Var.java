package douler;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import structure.TreeInfo;


/**
 * 全局变量
 * 
 * @author Administrator
 *
 */
public class Public_Var {
	public static final String FENGEFU = "-";// 日期分隔符
	public static boolean debug = false;// 测试中

	// 系统部分
	public static long runTimes = 0;// 程序轮询次数，看着玩的。
	public static int restTime = 1000 * 15;// 没有任务的情况下，休息秒数
	public static boolean printsql = false;// 是否打印sql到屏幕
	public static final boolean PROP = false; //是否重读配置文件
	
	//输入输出目录
	public static String input = "/datas/neu/";
	public static String output = "/data/databak/neu/";
	public static String autoWaterURL = "http://douler.yunyunapp.com/game/game/game_water.php";//自动浇水接口地址
	
	//数据库部分
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://rm-2ze44ogo4710z95f2.mysql.rds.aliyuncs.com/xiangyun";//新 171106
	public static String username = "xiangyun";
	public static String password = "XYadmin!171204";
//	
	//本地
//	public static String url="jdbc:mysql://59.110.174.81/game";//新 171106
//	public static String username = "gameadmin";
//	public static String password = "Game!@#171122";
	
	//测试
//	public static String url="jdbc:mysql://rm-2ze357ds4ryf3k58i.mysql.rds.aliyuncs.com/xingdu";//新 171106
//	public static String username = "xingdu";
//	public static String password = "XD!171204";


	
	public static Vector<String> sqls;
	public static final String version = "1801311045";
	
	//配置容器部分
	public static Map<Integer,TreeInfo> TreeInfo = new HashMap<Integer, TreeInfo>();
	public static String creditLaxin = "0.05";
	

	/**
	 * 东软接口处理ssh账号：  interface_neu/I_neu!171205
		服务器ip port：59.110.220.137  58122
		数据库：url：rm-2ze44ogo4710z95f2.mysql.rds.aliyuncs.com
		port：3306
		数据库名：xiangyun
		数据库账号/密码：xiangyun/XYadmin!171204
		数据输入路径：/datas/neu
		数据备份路径：/data/databak/neu
	 */
/**
 * '59.110.174.81',  /* The host to connect to 连接MySQL地址 
    'gameadmin',      /* The user to connect as 连接MySQL用户名 
    'Game!@#171122',  /* The password to use 连接MySQL密码 
    'game');    /* The default database to query 连接数据库名称
 */
	
	// interface_neu/I_neu!171205
}
