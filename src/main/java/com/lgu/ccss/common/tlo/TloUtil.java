package com.lgu.ccss.common.tlo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.dao.code.CodeServiceDao;
import com.lgu.ccss.common.model.code.CodeVO;
import com.lgu.common.tlo.TloWriter;

@Component
public class TloUtil {
	private static final Logger logger = LoggerFactory.getLogger(TloUtil.class);
	private static final Map<String, String> svcClassMap = new HashMap<String, String>();
	
	private static final String TLO_CODE_NM = "API_NM";
		
	@Autowired
	private CodeServiceDao codeServiceDao;
	
	@PostConstruct
	public void postConstruct() throws Exception {
		CodeVO codeVO = new CodeVO();
		codeVO.setGrpCdNm(TLO_CODE_NM);
		List<CodeVO> groupList = codeServiceDao.selectGroupCodeListInfo(codeVO);
		if (groupList.size() == 0) {
			logger.error("Tlo Group List is zero!!");
			return;
		}
		
		CodeVO groupCode = groupList.get(0);
		
		CodeVO codeDtlVO = new CodeVO();
		codeDtlVO.setGrpCdId(groupCode.getGrpCdId());
		List<CodeVO> codeList = codeServiceDao.selectDtlCodeList(codeDtlVO);
		if (codeList.size() == 0) {
			logger.error("Tlo Code List is zero!! groupCode({})", groupCode);
			return;
		}
		
		for (CodeVO code : codeList) {
			svcClassMap.put(code.getDtlCdNm(), code.getCdVal());
		}
		
		logger.info("[TLO_SVC_CLASS_MAP] " + svcClassMap);
	}
	
	public static void setTloData(String tloKey, String tloValue) {
		if (tloKey == null || tloKey.length() <=0 || tloValue == null || tloValue.length() <= 0 ) {
			return;
		}
		
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(tloKey, tloValue);
		TloUtil.setTloData(tlo);
	}
	
	public static void setTloData(Map<String, String> tloMap) {
		
		if (TloWriter.getTloFieldSize() == 0) {
			TloWriter.setTloFieldMap(TloData.class.getDeclaredFields());
		}
		
		String exist = null;
		for (String key : tloMap.keySet()) {
			try {
				exist = TloWriter.getTloFieldValue(key);
			} catch (NullPointerException e) {
				
			}
			
			if (exist != null) {
				MDC.put(key, tloMap.get(key));
			}
		}
	}
	
	public static Map<String, String> getTloData() {
		Map<String, String> tloMap = new HashMap<String, String>();
		
		Map<String, String> mdcMap = MDC.getCopyOfContextMap();
		for (String key : mdcMap.keySet()) {
			tloMap.put(key, mdcMap.get(key));
		}
		
		return tloMap;
	}
	
	public static String getSvcClass(String key) {
		return svcClassMap.get(key);
	}
}