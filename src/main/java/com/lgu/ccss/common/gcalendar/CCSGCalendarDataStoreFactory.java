package com.lgu.ccss.common.gcalendar;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

public class CCSGCalendarDataStoreFactory implements DataStoreFactory {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
		// TODO Auto-generated method stub
		
		// select StoredCredential
		logger.debug("id:" + id + " getDataStore call!!");
		
		
		return CCSGCalendaDataStore.getInstance();
	}

}
