package com.seecoder.BlueWhale;

import com.seecoder.BlueWhale.po.Product;
import com.seecoder.BlueWhale.po.User;
import com.seecoder.BlueWhale.serviceImpl.stragegy.*;
import com.seecoder.BlueWhale.util.TokenUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlueWhaleApplicationTests {

	@Autowired
	TokenUtil tokenUtil;
	CalculateStrategy calculateStrategy;
	@Test
	void contextLoads() {
		User user=new User();
		user.setId(1);
		user.setPassword("123456");
		System.out.println(tokenUtil.getToken(user));
	}

	@Test
	void priceCalculating(){
		calculateStrategy = new FillReductionCouponCalculateStrategy();
		double a = calculateStrategy.calculate(100.0, 50.0, 10.0);
		double b = calculateStrategy.calculate(50.0, 50.0, 60.0);
		double c = calculateStrategy.calculate(100.0, 150.0, 10.0);
		Assert.assertEquals(90.0, a, 0.001);
		Assert.assertEquals(-10.0, b, 0.001);
		Assert.assertEquals(100.0, c, 0.001);
		calculateStrategy = new DiscountCouponCalculateStrategy();
		double d = calculateStrategy.calculate(100.0, 50.0, 0.9);
		double e = calculateStrategy.calculate(100.0, 150.0, 0.9);
		Assert.assertEquals(90.0, d, 0.001);
		Assert.assertEquals(100.0, e, 0.001);

	}


	@Test
	void createProduct(){
		Product product = new Product();
		product.setName("test");
		product.setPrice(100.0);
		product.setStock(100);
		product.setStoreId(1);
		product.setCreateTime(null);
		product.setUpdateTime(null);
		Assert.assertEquals("test", product.getName());
		Assert.assertEquals(100.0, product.getPrice(), 0.001);
	}

	@Test
	public void testGetProduct(){
		Product product = new Product();
		product.setName("test");
		product.setPrice(100.0);
		product.setStock(100);
		product.setStoreId(1);
		product.setCreateTime(null);
		product.setUpdateTime(null);
		Assert.assertEquals("test", product.getName());
		Assert.assertEquals(100.0, product.getPrice(), 0.001);
	}
}
