/*
 * 基本数据库操作
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import structure.Tree;
import structure.TreeInfo;
import tool.TimeGet;
import tool.ZZYFunctions;
import douler.Public_Var;

public class CommonDao extends BaseDao {
	static Logger logger = LogManager.getLogger(CommonDao.class.getName());

	TimeGet tg = new TimeGet();
	ZZYFunctions func = new ZZYFunctions();

	/*
	 * 获取程序运行状态
	 */

	public int getProgramRunningStatus() {
		int status = 1;// 0未运行 1运行
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select status from zx_programrunningstatus";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				status = rs.getInt("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

		return status;
	}

	/*
	 * 设置程序运行状态
	 */
	public void setProgramRunningStatus(int status) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "update zx_programrunningstatus set status=?";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, status);
			stmt.execute();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

	}
	
	/**
	 * 初始化数据
	 * @return
	 */
	
	public void setUp(){
		//初始化树信息
		Public_Var.TreeInfo = new HashMap<Integer, TreeInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select * from ims_xiangyun_game_tree_info";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				TreeInfo ti = new TreeInfo();
				ti.setId(rs.getInt("id"));
				ti.setName(rs.getString("name"));
				ti.setTotal(rs.getInt("total"));
				Public_Var.TreeInfo.put(ti.getId(), ti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		
		sql = "select value from ims_xiangyun_game_config where name='creditLaxin'";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Public_Var.creditLaxin= rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

	}
	/*
	 * 事务处理批量sqls，如果出错，回滚。
	 */
	public boolean doSqls() {
		Connection conn = null;
		Statement stmt = null;
		boolean success = false;
		try {
			conn = openConnection();
			//conn.setAutoCommit(false);
			for (int i = 0; i < Public_Var.sqls.size(); i++) {
				String sql = (String) Public_Var.sqls.get(i);
				try {
					
					if(Public_Var.printsql){
						System.out.println(sql);
					}
					stmt = conn.prepareStatement(sql);
					// logger.info(sql);
					stmt.execute(sql);
				} catch (Exception e) {
					logger.error("doSqls.error:"+e+":"+sql);
				}
				
			}
			//conn.commit();
			//conn.setAutoCommit(true);
			success = true;

		} catch (SQLException e) {
			success = false;
			//conn.rollback();
			logger.error(e);

		} finally {
			// 清空sqls容器
			Public_Var.sqls = new Vector<String>();
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return success;

	}

	public int getMinTreeType(int parseInt) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int type = 1;
		String sql = "select type from ims_xiangyun_game_tree where ownerid = "+parseInt+" order by type";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				type = rs.getInt("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

		return type;
	}

	public boolean haveUser(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean have = false;
		String sql = "select * from ims_xiangyun_game_user where userid="+string;
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				have = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

		return have;
	}

	public String getPwd(String string,String date) {
		String pwd ="";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select pwd from ims_xiangyun_game_pwd where id='"+string+"' and date>'"+date+"'";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				pwd = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return pwd;
	}

	public Tree getTree(String string) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from ims_xiangyun_game_tree where id='"+string+"'";
		Tree tree = null;
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tree = new Tree();
				tree.setId(rs.getString("id"));
				tree.setType(rs.getInt("type"));
				tree.setOwnerid(rs.getInt("ownerid"));
				tree.setTreeYield(rs.getFloat("treeYield"));
				tree.setTreeLevel(rs.getFloat("treeLevel"));
				tree.setTreeGrown(rs.getFloat("treeGrown"));
				tree.setTreeGrownTime(rs.getString("treeGrownTime"));
				if(tree.getTreeGrownTime()==null){
					tree.setTreeGrownTime("2017-01-01 00:00:00");
				}
				tree.setTreeTotal(rs.getFloat("treeTotal"));
				tree.setTreeLastWater(rs.getString("treeLastWater"));
				if(tree.getTreeAutoWaterTime()==null){
					tree.setTreeLastWater("2017-01-01 00:00:00");
				}
				tree.setTreeAutoWaterTime(rs.getString("treeAutoWaterTime"));
				if(tree.getTreeAutoWaterTime()==null){
					tree.setTreeAutoWaterTime("2017-01-01 00:00:00");
				}
				tree.setFertilize(rs.getInt("fertilize"));
				tree.setStatus(rs.getInt("status"));
				tree.setGetFrom(rs.getString("getFrom"));
				tree.setGetTime(rs.getString("getTime"));
				tree.setGetType(rs.getInt("getType"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return tree;
	}

	public int getLeaderID(int recommendID) {
		while(true){
			int id = getRecommendId(recommendID);
			if(id==0){
				return recommendID;
			}else{
				recommendID = id;
			}
		}
	}
	
	public int getRecommendId(int id){
		int recommendID = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select recommendid from ims_xiangyun_game_user where userid="+id+"";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				recommendID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return recommendID;
	}

	public float getCloudBean(int ownerid) {
		float cloudbean = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select cloudbean from ims_xiangyun_game_user where userid="+ownerid+"";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				cloudbean = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return cloudbean;
	}

	public int getParentID(String userid) {
		int parentID = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select recommendid from ims_xiangyun_game_user where userid="+userid+"";
		try {
			conn = openConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				parentID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
		return parentID;
	}

	public void setUserName(String userID, String userName) {
		
		
	}

	
	
}
