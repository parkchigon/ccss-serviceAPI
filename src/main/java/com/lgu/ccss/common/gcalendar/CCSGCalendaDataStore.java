package com.lgu.ccss.common.gcalendar;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.model.InfotainAuthInfoVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.common.ai.auth.AuthConst;

public class CCSGCalendaDataStore<V extends Serializable> implements DataStore<V> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	private static CCSGCalendaDataStore instance = null;
	
	@SuppressWarnings("rawtypes")
	public static synchronized DataStore getInstance()
	{
		if( instance == null )
		{
			instance = new CCSGCalendaDataStore();
		}
		
		return instance;
	}
	
	private CCSGCalendaDataStore()
	{
		
	}
	
	@Override
	public DataStoreFactory getDataStoreFactory() {
		// TODO Auto-generated method stub
		logger.debug("getDataStoreFactory Call!!");
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		logger.debug("getId Call!!");
		return null;
	}

	@Override
	public int size() throws IOException {
		// TODO Auto-generated method stub
		logger.debug("size Call!!");
		return 0;
	}

	@Override
	public boolean isEmpty() throws IOException {
		// TODO Auto-generated method stub
		logger.debug("isEmpty Call!!");
		return false;
	}

	@Override
	public boolean containsKey(String key) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("containsKey Call!!");
		return false;
	}

	@Override
	public boolean containsValue(V value) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("containsValue Call!!");
		return false;
	}

	@Override
	public Set<String> keySet() throws IOException {
		// TODO Auto-generated method stub
		logger.debug("keySet Call!!");
		return null;
	}

	@Override
	public Collection<V> values() throws IOException {
		// TODO Auto-generated method stub
		logger.debug("values Call!!");
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public V get(String key) throws IOException {
		// TODO Auto-generated method stub
		
		// select StoredCredential
		InfotainAuthInfoDao infotainAuthInfoDao = (InfotainAuthInfoDao) CCSSUtil.getBean("infotainAuthInfoDao");
		List<InfotainAuthInfoVO> infotainAuthInfoList = infotainAuthInfoDao.selectInfotainAuthInfo(key, AuthConst.SERVICE_CODE_GOOGLE);
		
		if( infotainAuthInfoList == null || infotainAuthInfoList.size() == 0 )
			return null;
		
		
		
		java.util.Iterator<InfotainAuthInfoVO> iter = infotainAuthInfoList.iterator();
		StoredCredential credential = new StoredCredential();
		while( iter.hasNext() )
		{
			InfotainAuthInfoVO authInfoVo = iter.next();
			if( authInfoVo.getTokenNm().equals(GoogleCalendarService.TOKEN_NAME_ACCESSTOKEN) )
				credential.setAccessToken(authInfoVo.getTokenValue());
			else if( authInfoVo.getTokenNm().equals(GoogleCalendarService.TOKEN_NAME_REFRESHTOKEN) )
				credential.setRefreshToken(authInfoVo.getTokenValue());
			else if( authInfoVo.getTokenNm().equals(GoogleCalendarService.TOKEN_NAME_EXPDATE) )
				credential.setExpirationTimeMilliseconds(GoogleCalendarUtil.convertStringToDate(GoogleCalendarUtil.EXP_DATE_FORMAT, authInfoVo.getTokenValue()));
		}
				
		logger.debug("credential : " + credential.toString());
		return (V)credential;
	}

	@Override
	public DataStore<V> set(String key, V value) throws IOException {
		// TODO Auto-generated method stub
		
		//insert / update StoredCredential
		
		logger.debug("set Call!!");		
		return null;
	}

	@Override
	public DataStore<V> clear() throws IOException {
		// TODO Auto-generated method stub
		logger.debug("clear Call!!");
		return null;
	}

	@Override
	public DataStore<V> delete(String key) throws IOException {
		// TODO Auto-generated method stub
		
		//delete StoredCredential
		logger.debug("delete Call!!");
		return null;
	}
}
