package com.lgu.ccss.common.tlo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lgu.common.ncas.NCASQueryManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/spring/context-tlo.xml")
public class TloAopTest {
	
	@Autowired
	NCASQueryManager queryManager;
	
	@Ignore
	@Test
	public void test() {		
		try {
			queryManager.query("01022330811");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
