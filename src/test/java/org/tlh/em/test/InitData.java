package org.tlh.em.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.tlh.em.dao.CategoryDao;
import org.tlh.em.dao.ProductDao;
import org.tlh.em.entity.Category;
import org.tlh.em.entity.Product;

public class InitData {
	
	private static final int CATEGORY_COUNT=100;
	private static final int PRODUCT_COUNT=1000;
	

	@Test
	public void insertCategory() throws Exception{
		List<Category> categories=new ArrayList<>();
		Category category=null;
		for(int i=0;i<CATEGORY_COUNT;i++){
			category=new Category();
			category.setTypename("类别"+(i+1));
			category.setFlag(true);
			
			categories.add(category);
			category=null;
		}
		
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.batch(categories);
	}
	
	@Test
	public void insertProduct() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=null;
		Random random=new Random();
		for(int i=0;i<PRODUCT_COUNT;i++){
			product=new Product();
			product.setCategoryId(random.nextInt(CATEGORY_COUNT)+1);
			product.setDescription("还可以吧"+(i+1));
			product.setFlag(true);
			product.setGoodsname("商品"+(i+1));
			product.setImg("fasf");
			product.setPrice(random.nextFloat()*1000);
			
			
			products.add(product);
			product=null;
		}
		
		ProductDao productDao=new ProductDao();
		productDao.batch(products);
	}
	
}
