package org.tlh.em.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.tlh.em.entity.Product;

public class ProductDao extends BaseDao {

	public int[] batch(List<Product> products) throws Exception{
		Connection connection = null;
		PreparedStatement prepareStatement=null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			String sql="INSERT into goods(goodstypeid,goodsname,img,description,price,flag)VALUES(?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(sql);
			
			for (Product product : products) {
				prepareStatement.setInt(1, product.getCategoryId());
				prepareStatement.setString(2, product.getGoodsname());
				prepareStatement.setString(3, product.getImg());
				prepareStatement.setString(4, product.getDescription());
				prepareStatement.setFloat(5, product.getPrice());
				prepareStatement.setBoolean(6, product.isFlag());
				
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
