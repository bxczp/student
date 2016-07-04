package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.DataDic;
import Model.PageBean;
import Util.StringUtil;

/**
 * @date 2016年3月11日 DataDicDao.java
 * @author CZP
 * @parameter
 */
public class DataDicDao {

	public boolean existDataDicWithTypeId(Connection conn, String ddTypeId) throws Exception {
		String sql = "select * from t_datadic where ddTypeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(ddTypeId));
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	// 根据数据字典的类别名称查询
	public List<DataDic> dataDicList(Connection conn, DataDic s_dataDic, PageBean pageBean) throws Exception {
		List<DataDic> dataDicList = new ArrayList<DataDic>();
		StringBuffer sql = new StringBuffer(
				"select * from t_datadic t1,t_datadictype t2 where t1.ddTypeId=t2.ddTypeId");
		if (StringUtil.isNotEmpty(s_dataDic.getDdTypeName())) {
			sql.append(" and t2.ddTypeName like '%" + s_dataDic.getDdTypeName() + "%'");
		}
		if (pageBean != null) {
			sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			DataDic dataDic = new DataDic();
			dataDic.setDdDesc(rs.getString("ddDesc"));
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdTypeName(rs.getString("ddTypeName"));
			dataDicList.add(dataDic);
		}
		return dataDicList;
	}

	public int countDataDicList(Connection conn, DataDic s_dataDic) throws Exception {
		StringBuffer sql = new StringBuffer(
				"select count(*) as total from t_datadic t1,t_datadictype t2 where t1.ddTypeId=t2.ddTypeId");
		if (StringUtil.isNotEmpty(s_dataDic.getDdTypeName())) {
			sql.append(" and t2.ddTypeName like '%" + s_dataDic.getDdTypeName() + "%'");
		}
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	// 根据ddId获取记录
	public DataDic getDataDicByDdId(Connection conn, String ddId) throws Exception {
		String sql = "select * from t_datadic t1,t_datadictype t2 where t1.ddTypeId=t1.ddTypeId and ddId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(ddId));
		ResultSet rs = preparedStatement.executeQuery();
		DataDic dataDic = new DataDic();
		if (rs.next()) {
			dataDic.setDdDesc(rs.getString("ddDesc"));
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdTypeName(rs.getString("ddTypeName"));
		}
		return dataDic;
	}

	public int dataDicAdd(Connection conn, DataDic dataDic) throws Exception {
		String sql = "insert into t_datadic values(null,?,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, dataDic.getDdTypeId());
		preparedStatement.setString(2, dataDic.getDdValue());
		preparedStatement.setString(3, dataDic.getDdDesc());
		return preparedStatement.executeUpdate();
	}

	public int dataDicUpdate(Connection conn, DataDic dataDic) throws Exception {
		String sql = "update t_datadic set ddTypeId=?,ddValue=?,ddDesc=? where ddId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, dataDic.getDdTypeId());
		preparedStatement.setString(2, dataDic.getDdValue());
		preparedStatement.setString(3, dataDic.getDdDesc());
		preparedStatement.setInt(4, dataDic.getDdId());
		return preparedStatement.executeUpdate();
	}

	public int dataDicDelete(Connection conn, String ddId) throws Exception {
		String sql = "delete from t_datadic where ddId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(ddId));
		return preparedStatement.executeUpdate();
	}

}
