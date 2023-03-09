package com.lgu.ccss.common.ncas;

import static org.junit.Assert.*;

import java.net.URLEncoder;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lgtel.mmdb.CasCrypto;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/spring/context-common.xml")
public class NcasTest {

	@Autowired
	NCASQueryManager queryManager;
	
	@SuppressWarnings("deprecation")
	@Ignore
	@Test
	public void test() {
		String pwd = URLEncoder.encode(CasCrypto.casCryptoEncode("E645919BADBAD0D9", "D076AEABE5BC7585", "DF89BCE93B70CD13", "D91C3245767F1C0E", "2577seen!!"));
		System.out.println(pwd);

		assertEquals(pwd, "rPjajk8Yp0BB4N%2B%2FHHfQZA%3D%3D");
		
		String ctn = queryManager.changeCTNFormat("01022330811");
		System.out.println(ctn);
		
		assertEquals(ctn, "010022330811");
		
		NCASResultData ncasData = null;
		try {
			ncasData = queryManager.query("01022330811");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[SUBS_INFO] " + ncasData.getSubsInfo());
		System.out.println("[RESP_CODE] " + ncasData.getRespCode());
	}
}
