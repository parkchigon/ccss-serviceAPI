package com.lgu.ccss.common.dao.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.code.CodeVO;

import devonframe.dataaccess.CommonDao;

@Component
public class CodeServiceDao {
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	
	/**
	 * GRP_CD_ID 조회
	 * @param codeVO
	 * @return String
	 */
	
	public String selectGrpCdId (String grpCdNm) throws Exception{
		return  commonDao_oracle.select("Code.selectGrpCdId",grpCdNm);
	}
	
	
	/**
	 * Group Code 정보 리스트 조회
	 * 
	 * @param appVO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectGroupCodeList(CodeVO codeVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> resultList = commonDao_oracle.selectList("Code.selectGroupCodeList",codeVO);
		int totalCount = commonDao_oracle.select("Code.selectGroupCodeListCnt",codeVO);
		resultMap.put("resultList", resultList);
		resultMap.put("totalCount", totalCount);
		return resultMap;
		
	}
	
	public List<CodeVO> selectGroupCodeListInfo(CodeVO codeVO) throws Exception {
		return commonDao_oracle.selectList("Code.selectGroupCodeList",codeVO);		
	}
	
	/**
	 * Group Code 정보 상세
	 * 
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	public CodeVO selectGroupCodeDetail(CodeVO codeVO) throws Exception {
		codeVO = commonDao_oracle.select("Code.selectGroupCodeDetail",codeVO);
		return codeVO; 
	}

	/**
	 * Dtl Code 정보 상세
	 * 
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	public  CodeVO selectDtlCodeDetail(CodeVO codeVO) throws Exception {
		
		codeVO = commonDao_oracle.select("Code.selectDtlCodeDetail",codeVO);
		return codeVO; 
	}
	
	/**
	 * DTL_CODE 리스트 조회
	 * @param codeVO
	 * @return
	 */
	public List<CodeVO> selectDtlCodeList(CodeVO codeVO) throws Exception{
		List<CodeVO> resultList = commonDao_oracle.selectList("Code.selectDtlCodeList",codeVO);
		return resultList;
	}
	

	/**
	 * DTL_CODE 조회
	 * @param codeVO
	 * @return
	 */
	public List<CodeVO> selectDtlCodeListByGrpCodeNm(CodeVO codeVO) throws Exception{
		List<CodeVO> resultList = commonDao_oracle.selectList("Code.selectDtlCodeListByGrpCodeNm",codeVO);
		return resultList;
	}
	
}
