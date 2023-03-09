package com.lgu.ccss.common.dao.datagift;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.datagift.PaymentVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DatagiftPaymentDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	
	public int insertPayment(PaymentVO payment)
	{
		return commonDao_oracle.insert("GiftPayment.insertPayment", payment);
	}
	
	
}
