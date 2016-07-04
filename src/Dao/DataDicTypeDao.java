package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.DataDicType;

/**
 * @date 2016年3月11日 DataDicTypeDao.java
 * @author CZP
 * @parameter
 */
public class DataDicTypeDao {
	/**
	 * 获取t_datadictype表中的所有数据
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<DataDicType> dataDicTypeList(Connection conn) throws Exception {
		List<DataDicType> dataDicTypeList = new ArrayList<DataDicType>();
		String sql = "select * from t_datadictype";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			DataDicType dataDicType = new DataDicType();
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
			dataDicTypeList.add(dataDicType);
		}
		return dataDicTypeList;
	}

	/**
	 * 添加数据
	 * 
	 * @param conn
	 * @param dataDicType
	 * @return
	 * @throws Exception
	 */
	public int dataDicTypeAdd(Connection conn, DataDicType dataDicType) throws Exception {
		String sql = "insert into t_datadicType values(null,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, dataDicType.getDdTypeName());
		preparedStatement.setString(2, dataDicType.getDdTypeDesc());
		return preparedStatement.executeUpdate();
	}

	/**
	 * 更新数据
	 * 
	 * @param conn
	 * @param dataDicType
	 * @return
	 * @throws Exception
	 */
	public int dataDicTypeUpdate(Connection conn, DataDicType dataDicType) throws Exception {
		String sql = "update t_datadictype set ddTypeName=?,ddTypeDesc=? where ddTypeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, dataDicType.getDdTypeName());
		preparedStatement.setString(2, dataDicType.getDdTypeDesc());
		preparedStatement.setInt(3, dataDicType.getDdTypeId());
		return preparedStatement.executeUpdate();
	}

	/**
	 * 根据id查找数据
	 * 
	 * @param conn
	 * @param dataDicTypeId
	 * @return
	 * @throws Exception
	 */
	public DataDicType getDataDicTypeById(Connection conn, String dataDicTypeId) throws Exception {
		String sql = "select * from t_datadictype where ddTypeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(dataDicTypeId));
		ResultSet rs = preparedStatement.executeQuery();
		DataDicType dataDicType = null;
		if (rs.next()) {
			dataDicType = new DataDicType();
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
		}
		return dataDicType;
	}

	public int dataDicTypeDelete(Connection conn, String ddTypeId) throws Exception {
		String sql = "delete from t_datadictype where ddTypeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(ddTypeId));
		return preparedStatement.executeUpdate();
	}

}
