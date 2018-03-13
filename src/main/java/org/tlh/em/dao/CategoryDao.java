package org.tlh.em.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.tlh.em.entity.Category;

public class CategoryDao extends BaseDao {

	public int[] batch(List<Category> categories) throws Exception{
		Connection connection = null;
		PreparedStatement prepareStatement=null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			String sql="INSERT into goodstype(typename,flag) VALUES(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			for (Category category : categories) {
				prepareStatement.setString(1, category.getTypename());
				prepareStatement.setBoolean(2, category.isFlag());
				
				prepareStatement.addBatch();
			}

			int[] result = prepareStatement.executeBatch();
			connection.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}finally {
			release(connection, prepareStatement, null);
		}
		return null;
	}
	
}
