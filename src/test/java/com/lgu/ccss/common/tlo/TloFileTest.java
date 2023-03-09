package com.lgu.ccss.common.tlo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lgu.common.tlo.TloWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/spring/context-tlo.xml")
public class TloFileTest {

	@Autowired
	private TloWriter tloWriter;
	
	@Ignore
	@Test
	public void test() {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "LOG_TYPE value");
		tlo.put(TloData.SID, "SID value");
		
		tloWriter.write(tlo);
	}

}
