package com.lgu.ccss.common.tlo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class TloTest {

	@Test
	public void testTlo() {
		
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "LOG_TYPE value");
		tlo.put(TloData.SID, "SID value");
		tlo.put(TloData.RESULT_CODE, "RESULT_CODE value");
		tlo.put(TloData.REQ_TIME, "REQ_TIME value");
		tlo.put(TloData.RSP_TIME, "RSP_TIME value");
		tlo.put(TloData.CLIENT_IP, "CLIENT_IP value");
		tlo.put(TloData.DEV_INFO, "DEV_INFO value");
		tlo.put(TloData.OS_INFO, "OS_INFO value");
		tlo.put(TloData.NW_INFO, "NW_INFO value");
		tlo.put(TloData.SVC_NAME, "SVC_NAME value");
		tlo.put(TloData.DEV_MODEL, "DEV_MODEL value");
		tlo.put(TloData.CARRIER_TYPE, "CARRIER_TYPE value");
		tlo.put(TloData.SESSION_ID, "SESSION_ID value");
		tlo.put(TloData.DEVICE_TYPE, "DEVICE_TYPE value");
		tlo.put(TloData.APP_TYPE, "APP_TYPE value");
		tlo.put(TloData.CAR_OEM, "CAR_OEM value");
		tlo.put(TloData.CLIENT_ID, "CLIENT_ID value");
		tlo.put(TloData.SVC_CLASS, "SVC_CLASS value");
		tlo.put(TloData.NCAS_REQ_TIME, "NCAS_REQ_TIME value");
		tlo.put(TloData.NCAS_RES_TIME, "NCAS_RES_TIME value");
		tlo.put(TloData.KMC_REQ_TIME, "KMC_REQ_TIME value");
		tlo.put(TloData.KMC_RES_TIME, "KMC_RES_TIME value");
		tlo.put(TloData.SMS_REQ_TIME, "SMS_REQ_TIME value");
		tlo.put(TloData.SMS_RES_TIME, "SMS_RES_TIME value");
		tlo.put(TloData.PUSH_REQ_TIME, "PUSH_REQ_TIME value");
		tlo.put(TloData.PUSH_RES_TIME, "PUSH_RES_TIME value");
		tlo.put(TloData.CARPUSH_REQ_TIME, "CARPUSH_REQ_TIME value");
		tlo.put(TloData.CARPUSH_RES_TIME, "CARPUSH_RES_TIME value");
		tlo.put(TloData.MAP_AM_REQ_TIME, "MAP_AM_REQ_TIME value");
		tlo.put(TloData.MAP_AM_RES_TIME, "MAP_AM_RES_TIME value");
		tlo.put(TloData.MAP_BM_REQ_TIME, "MAP_BM_REQ_TIME value");
		tlo.put(TloData.MAP_BM_RES_TIME, "MAP_BM_RES_TIME value");

		// positive test
		TloUtil.setTloData(tlo);
		Map<String, String> outTlo = TloUtil.getTloData();

		for (String key : tlo.keySet()) {
			assertEquals(tlo.get(key), outTlo.get(key));
		}
		
		// negative test
		tlo.put("AAAAA", "AAAAA");
		TloUtil.setTloData(tlo);
		outTlo = TloUtil.getTloData();
		assertEquals(outTlo.get("AAAAA"), null);
	}
	
	@Test
	public void testTloThread() {
		final int threadCnt = 10;
		ExecutorService threadPool = Executors.newFixedThreadPool(threadCnt);


		for (int i=0; i<threadCnt; i++) {
			threadPool.execute(new Worker("Worker" + i));
		}
		
		threadPool.shutdown();
	}
}

class Worker implements Runnable {
	
	private String name;
	
	public Worker (String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.SID, name);
		
		TloUtil.setTloData(tlo);
		Map<String, String> outTlo = TloUtil.getTloData();
		
		for (String key : tlo.keySet()) {
			assertEquals(tlo.get(key), outTlo.get(key));
		}
		
		System.out.println("Thread finished : " + name + " " + outTlo);
	}
}
