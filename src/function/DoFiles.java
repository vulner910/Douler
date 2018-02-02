package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import structure.Tree;
import tool.TimeGet;
import tool.ZZYFunctions;
import dao.CommonDao;
import douler.Main;
import douler.Public_Var;

public class DoFiles {

	static Logger logger = LogManager.getLogger(Main.class.getName());
	CommonDao cdao = new CommonDao();
	ZZYFunctions func = new ZZYFunctions();
	TimeGet tg = new TimeGet();

	public void doMembers() {
		try {
			File[] files = func.getFiles("member");
			for (File f : files) {
				try {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String rl = "";
					while ((rl = br.readLine()) != null) {
						try {
							String[] ss = rl.split("\t");
							// 查询密码临时表，是否有新密码，并且日期大于regtime
							String pwd = cdao.getPwd(ss[0], ss[5]);
							// 如果有，用新密码insert
							// 没有，就用接口密码
							if (pwd.equals("")) {
								pwd = ss[2];
							}
							String sql = "insert into ims_xiangyun_game_user(userid,username,password,truename,phonenumber,regtime,recommendid) " + "values(" + ss[0] + ",'" + ss[1] + "','" + pwd + "','" + ss[3] + "','" + ss[4] + "','" + ss[5] + "'," + ss[6] + ")";
							Public_Var.sqls.add(sql);
							// System.out.println(sql);
							//处理 分组
							// 获取顶点ID
							int leaderid = 0;
							try {
								leaderid = Integer.parseInt(ss[6]);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if(leaderid==0){
								leaderid = Integer.parseInt(ss[0]);
							}else{
								leaderid = cdao.getLeaderID(leaderid);
							}
							sql = "insert into ims_xiangyun_game_group (userid,leaderid) values("+ss[0]+","+leaderid+")";
							Public_Var.sqls.add(sql);
							cdao.doSqls();
						} catch (Exception e) {
							logger.error("while:" + e + "(" + rl + ")");
						}
					}
					br.close();
					fr.close();
					f.renameTo(new File(Public_Var.output + f.getName()));
				} catch (Exception e) {
					logger.error(f.getName() + ":" + e);
				}

			}
		} catch (Exception e) {
			logger.error("doMembers:" + e);
		}
		cdao.doSqls();
	}

	public void doBuy() {
		try {
			File[] files = func.getFiles("buy");
			for (File f : files) {
				try {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String rl = "";
					while ((rl = br.readLine()) != null) {
						try {
							String[] ss = rl.split("\t");
							//107	2017120701-A000008	会员产品第二期	床位购买	27300.0	2017-12-13 12:00:00	106	姓名
							
							//2018.1.24 buy文件最后增加购买人姓名字段，按购买人id更新到game_user表。
							//cdao.setUserName(ss[0],ss[7]);
							String sql = "update ims_xiangyun_game_user set truename = '"+ss[7]+"' where userid="+ss[0];
							Public_Var.sqls.add(sql);
							// 树购买
							// 获得推荐人当前的最高级别树
							int type = 1;
							if (!ss[6].equals("0")) {
								type = cdao.getMinTreeType(Integer.parseInt(ss[6])) + 1;
								if(type>=6){
									type =5;
								}
							}

							// 618 2017120201-A970002 会员产品第二期 床位购买 27300.0 2017-12-13 12:00:00 0
							// 2018.01.28 购买树改成金杉
							int treeType = 2;//1云杉 2金杉
							sql = "insert into ims_xiangyun_game_tree(id,`type`,ownerid,treeYield,treeLevel,treeGrown,treeTotal,fertilize,getType,getTime) " + 
							"values('" + ss[1] + "'," + treeType + "," + ss[0] + "," + ss[4] + "," + 2 + "," + 0 + "," + Public_Var.TreeInfo.get(treeType).getTotal() + ",0,1,'"+ss[5]+"')";
							Public_Var.sqls.add(sql);
							// System.out.println(sql);
							// 给推荐人增加一个化肥
							// 18.1.24 不给化肥，给冻结化肥，后台发放 推荐人从user表出
							int parentID = cdao.getParentID(ss[0]);
							if (parentID!=0) {
								sql = "update ims_xiangyun_game_user set freeze_fertilize = freeze_fertilize+1 where userid = " + parentID;
								Public_Var.sqls.add(sql);
								// 给推荐人增加积分
								if (cdao.haveUser(parentID+"")) {
									sql = "update ims_mc_members set credit3 = credit3+" + ss[4] + "*"+Public_Var.creditLaxin+" where userid = " + parentID;
								} else {
									sql = "update ims_xiangyun_game_user set credit = credit+" + ss[4] + "*"+Public_Var.creditLaxin+" where userid = " + parentID;
								}
								Public_Var.sqls.add(sql);
							}
						} catch (Exception e) {
							logger.error("while:" + e + "(" + rl + ")");
						}
						cdao.doSqls();
					}
					br.close();
					fr.close();
					f.renameTo(new File(Public_Var.output + f.getName()));
				} catch (Exception e) {
					logger.error(f.getName() + ":" + e);
				}

			}
		} catch (Exception e) {
			logger.error("doMembers:" + e);
		}
		

	}

	public void doTransferOrGive(String tg) {
		try {
			File[] files = func.getFiles(tg);
			for (File f : files) {
				try {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String rl = "";
					int getType = tg.equals("transfer")?4:5;//来源方式 转让4   赠与5
					while ((rl = br.readLine()) != null) {
						try {
							String[] ss = rl.split("\t");
							// 586 2017092700-B380002 72 2017070800-A154072	会员产品第一期 床位转让 2017-12-13 12:00:00
							// 获取原始树的信息
							Tree tree = cdao.getTree(ss[3]);
							// 2018.1.24 增加原始树的状态，如果是回收 冻结 赠与 不操作
							if(tree.getStatus()==3||tree.getStatus()==4||tree.getStatus()==5){
								continue;
							}
							int newType = tree.getType()+1<=5?tree.getType()+1:tree.getType();
							//2018.01.31 临时处理，所有赠与转让给云杉
							newType = 1;
							//处理转让云豆解冻问题
							// 生长中 直接转让
							
							// 已收获
							// 2018.1.5修改，
							// 原规则 收获后转让，解冻云豆；现规则 没有收获状态了 不进行状态判断 ，判断剩余冻结云豆，如果小于树的值，就给最小值
							if(tree.getTreeLevel()==4||true){
								
								float cloudbean = cdao.getCloudBean(tree.getOwnerid());//用户当前冻结云豆
								float jifen = Math.min(tree.getTreeYield(),cloudbean);// 取最小值
								String sql = "update ims_xiangyun_game_user set cloudbean=cloudbean-"+jifen+",CloudbeanAvailable=CloudbeanAvailable+"+jifen+" where userid="+tree.getOwnerid();
								Public_Var.sqls.add(sql);
								//给邓哥增加云豆
								sql = "update ims_mc_members set credit1 = credit1+"+jifen+" where userid = " + tree.getOwnerid();
								Public_Var.sqls.add(sql);
//								System.out.println(sql);
							}
							//修改旧树
							
							String sql = "update ims_xiangyun_game_tree set status = " + getType + " where id='" + ss[3] + "'";
							Public_Var.sqls.add(sql);
							//增加新树
							sql = "insert into ims_xiangyun_game_tree(id,type,ownerid,treeYield,treeLevel,treeGrown,treeTotal,fertilize,status,getFrom,getTime,getType) "+
							"values('"+ss[1]+"',"+newType+","+ss[0]+","+tree.getTreeYield()+","+2+","+0+","+Public_Var.TreeInfo.get(newType).getTotal()+","+0+",1,'"+ss[2]+"','"+ss[6]+"',"+getType+")";
							Public_Var.sqls.add(sql);
//							System.out.println(sql);
						} catch (Exception e) {
							logger.error("while:" + e + "(" + rl + ")");
						}
					}
					br.close();
					fr.close();
					f.renameTo(new File(Public_Var.output + f.getName()));
				} catch (Exception e) {
					logger.error(f.getName() + ":" + e);
				}

			}
		} catch (Exception e) {
			logger.error("doMembers:" + e);
		}
		cdao.doSqls();

	}

	public void doFreeze(String freeze) {
		try {
			File[] files = func.getFiles(freeze);
			for (File f : files) {
				try {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String rl = "";
					while ((rl = br.readLine()) != null) {
						try {
							// 652 2017121201-A260009 2017-12-13 23:59:59
							String[] ss = rl.split("\t");
							Tree tree = cdao.getTree(ss[1]);
							// 2018.1.24 增加原始树的状态，如果是回收 冻结 赠与 不操作
							if(tree.getStatus()==3||tree.getStatus()==4||tree.getStatus()==5){
								continue;
							}
							int status = 1;
							if (freeze.equals("freeze")) {
								status = 2;
							} else if (freeze.equals("recycle")) {
								status = 3;
							}
							
							String sql = "update ims_xiangyun_game_tree set status = " + status + "  where id='" + ss[1] + "'";
							Public_Var.sqls.add(sql);
//							System.out.println(sql);
						} catch (Exception e) {
							logger.error("while:" + e + "(" + rl + ")");
						}
					}
					br.close();
					fr.close();
					f.renameTo(new File(Public_Var.output + f.getName()));
				} catch (Exception e) {
					logger.error(f.getName() + ":" + e);
				}

			}
		} catch (Exception e) {
			logger.error("doMembers:" + e);
		}
		cdao.doSqls();

	}

	public void doPwd() {

		try {
			File[] files = func.getFiles("pwd");
			for (File f : files) {
				try {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String rl = "";
					while ((rl = br.readLine()) != null) {
						try {
							String[] ss = rl.split("\t");
							// 726 e10adc3949ba59abbe56e057f20f883e 2017-12-14 10:39:07
							String sql = "", pwd = "";
							// 判断是否有该用户
							// 有，直接修改密码
							if (cdao.haveUser(ss[0])) {
								sql = "update ims_xiangyun_game_user set password = '" + ss[1] + "' where userid=" + ss[0];
							}
							// 没有，查询密码临时表是否有
							else {
								pwd = cdao.getPwd(ss[0], "2001-01-01 00:00:00");

								// 有的话，update
								if (!pwd.equals("")) {
									sql = "update ims_xiangyun_game_pwd set pwd='" + ss[1] + "' ,date = '" + ss[2] + "' where id=" + ss[0];
								}
								// 没有，insert
								else {
									sql = "insert into ims_xiangyun_game_pwd values(" + ss[0] + ",'" + ss[1] + "','" + ss[2] + "')";
								}
							}

							Public_Var.sqls.add(sql);
							// System.out.println(sql);
						} catch (Exception e) {
							logger.error("while:" + e + "(" + rl + ")");
						}
					}
					br.close();
					fr.close();
					f.renameTo(new File(Public_Var.output + f.getName()));
				} catch (Exception e) {
					logger.error(f.getName() + ":" + e);
				}

			}
		} catch (Exception e) {
			logger.error("doMembers:" + e);
		}
		cdao.doSqls();

	}
}
