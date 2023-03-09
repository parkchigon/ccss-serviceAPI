package com.lgu.common.datagift;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.lgu.common.datagift.domain.DataGiftCtnCheck;
import com.lgu.common.datagift.domain.DataGiftCtnCheckRequest;
import com.lgu.common.datagift.domain.DataGiftList;
import com.lgu.common.datagift.domain.DataGiftOwnList;
import com.lgu.common.datagift.domain.DataGiftOwnListRequest;
import com.lgu.common.datagift.domain.DataGiftReg;
import com.lgu.common.datagift.service.DataGiftService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class DataGiftManager {
	
	private static final Logger logger = LoggerFactory.getLogger(DataGiftManager.class);
	public static final String SUCCESS = "0000";

	// //////////////////////////////////////////////////////////////////////
	// Required Parameters
	// //////////////////////////////////////////////////////////////////////
	@Value("#{config['data.gift.service.url']}")
	private String dataGiftServiceUrl; // URL
	@Value("#{config['data.gift.service.id']}")
	private String dataGiftServiceId; // PAP 사이트에서 발급받은 서비스 ID
	@Value("#{config['data.gift.encryption.key']}")
	private String dataGiftEncryptionKey; // PAP 사이트에서 발급받은 암호화키 

	// //////////////////////////////////////////////////////////////////////
	// PAP 사이트에서 발급한 API 인증키들
	// //////////////////////////////////////////////////////////////////////
	@Value("#{config['data.gift.list']}")
	private String dataGiftListAccessKey; // 상품권 조회
	@Value("#{config['data.gift.issue']}")
	private String dataGiftIssueAccessKey; // 상품권 발행
	@Value("#{config['data.gift.issue.cancel']}")
	private String dataGiftIssueCancelAccessKey; // 상품권 발행 취소
	@Value("#{config['data.gift.reg']}")
	private String dataGiftRegAccessKey; // 상품권 발행등록
	@Value("#{config['data.gift.pin.check']}")
	private String dataGiftPINCheckAccessKey; // 상품권 PIN번호 인증
	@Value("#{config['data.gift.pin.regist']}")
	private String dataGiftPINRegistAccessKey; // 상품권 PIN번호 등록
	@Value("#{config['data.gift.pin.used.check']}")
	private String dataGiftPinUsedCheckAccessKey; // 상품권 사용 여부 조회
	@Value("#{config['data.gift.own.list']}")
	private String dataGiftOwnListAccessKey; // 상품권 리스트 조회
	@Value("#{config['data.gift.ctn.check']}")
	private String dataGiftCtnCheckAccessKey; // 고객 CTN 인증
	@Value("#{config['data.gift.pin.resend']}")
	private String dataGiftPinResendAccessKey; // 상품권 MMS 재발송
	@Value("#{config['data.gift.pin.cancel.gift']}")
	private String dataGiftPinCancelGiftAccessKey; // 상품권 취소
	@Value("#{config['data.gift.chg.data.pgpay']}")
	private String dataGiftChgDataPgpayAccessKey; // 데이터 충전하기 [PG결제]
	@Value("#{config['data.gift.give.data.pgpay']}")
	private String dataGiftGiveDataPgpayAccessKey; // 데이터 선물하기 [PG결제-바로충전]
	@Value("#{config['data.gift.give.pin.pgpay']}")
	private String dataGiftGivePinPgpayAccessKey; // 데이터 선물하기 [PG결제-상품권번호전송]
	
	
	static Retrofit retrofit = null;
	static Retrofit getInstance(String url) {
		if (retrofit == null) {
//			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//			logging.setLevel(Level.BODY);

//			OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//			httpClient.connectTimeout(60, TimeUnit.SECONDS);
//			httpClient.readTimeout(60, TimeUnit.SECONDS);
//			httpClient.writeTimeout(60, TimeUnit.SECONDS);
//			httpClient.addInterceptor(logging);

//			OkHttpClient client = httpClient.build();
			OkHttpClient client = getUnsafeOkHttpClient();		// 

			retrofit = new Retrofit.Builder().baseUrl(url)
					.addConverterFactory(GsonConverterFactory.create())
					.client(client).build();
		}

		return retrofit;
	}
	
	
	@SuppressWarnings("deprecation")
	public static OkHttpClient getUnsafeOkHttpClient() {
		logger.debug("getUnsafeOkHttpClient start");
	    try {
	        // Create a trust manager that does not validate certificate chains
	        final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	            @Override
	            public void checkClientTrusted(
	                    java.security.cert.X509Certificate[] chain,
	                    String authType) throws CertificateException {
	            }

	            @Override
	            public void checkServerTrusted(
	                    java.security.cert.X509Certificate[] chain,
	                    String authType) throws CertificateException {
	            }

	            @Override
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return new java.security.cert.X509Certificate[0];
	            }
	        } };

	        // Install the all-trusting trust manager
	        final SSLContext sslContext = SSLContext.getInstance("TLS");
	        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	        
	        // Create an ssl socket factory with our all-trusting manager
	        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(Level.BODY);	        
	        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
			httpClient.connectTimeout(30, TimeUnit.SECONDS);
			httpClient.readTimeout(30, TimeUnit.SECONDS);
			httpClient.writeTimeout(30, TimeUnit.SECONDS);
			httpClient.addInterceptor(logging);	    
			OkHttpClient client = httpClient.build();
			
	        //OkHttpClient okHttpClient = new OkHttpClient();
   
	        
			client = client.newBuilder()
	                .sslSocketFactory(sslSocketFactory)
	                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

	        return client;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }

	}
	
	/**
	 * 상품권 조회 (DataGiftList)
	 * 
	 * @return
	 * @throws Exception
	 */
	public DataGiftList getDataGiftList() throws Exception {
		logger.debug("getDataGiftList start");
		DataGiftList responseBody = null;
		Retrofit retrofit = getInstance(dataGiftServiceUrl);

		DataGiftService service = retrofit.create(DataGiftService.class);
		Call<DataGiftList> call = service.dataGiftList(dataGiftServiceId, dataGiftListAccessKey);
		Response<DataGiftList> response = call.execute();

		// Request request = call.request();
		// Response response = response;

		if(response == null){
			logger.error(String.format("fail dataGiftList, No response!"));
			responseBody = new DataGiftList();
			responseBody.setResult(null);
			responseBody.setResultCode(null);
			responseBody.setResultMessage("No response!");
		}else{
			responseBody = response.body();
			if(responseBody == null)
			{
				logger.error(String.format("dataGiftList - response status code[%d]", response.code()));
			    Gson gson = new Gson();
			    TypeAdapter<DataGiftList> adapter = gson.getAdapter(DataGiftList.class);
			    try {
			        if (response.errorBody() != null){
			        	responseBody = adapter.fromJson(response.errorBody().string());
			        }else{
						responseBody = new DataGiftList();
						responseBody.setResult(null);
						responseBody.setResultCode(null);
						responseBody.setResultMessage(String.format("No response body, status code[%d]", response.code()));			        
					}
			    } catch (IOException e) {
			        logger.error(String.format("DataGiftList fail, reason=%s", e.getMessage()), e);
			        
					responseBody = new DataGiftList();
					responseBody.setResult(null);
					responseBody.setResultCode(null);
					responseBody.setResultMessage(String.format("DataGiftList fail, reason=%s", e.getMessage()));	
			    }
			}
		}		
		
		return responseBody;
	}

	// /**
	// * 상품권 발행 (DataGiftIssue)
	// *
	// * @param issue_no
	// * - "상품권 발행번호 (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사 페이지에서
	// * 확인가능 (각 전문에 사용하는 암호화키는 동일)"
	// * @param ctn
	// * - "CTN (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사 페이지에서 확인가능 (각
	// * 전문에 사용하는 암호화키는 동일)"
	// * @return
	// * @throws Exception
	// */
	// public DataGiftIssue getDataGiftIssue(String issue_no, String ctn)
	// throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftIssue> call = service.dataGiftIssue(dataGiftServiceId,
	// dataGiftIssueAccessKey, issue_no, ctn);
	// Response<DataGiftIssue> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 상품권 발행 취소 (DataGiftIssueCancel)
	// *
	// * @param gift_no
	// * - "발행된 상품권 고유번호 (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사
	// * 페이지에서 확인가능 (각 전문에 사용하는 암호화키는 동일)"
	// * @param ctn
	// * - "상품권이 발행된 CTN (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사
	// * 페이지에서 확인가능 (각 전문에 사용하는 암호화키는 동일)"
	// * @return
	// * @throws Exception
	// */
	// public DataGiftIssueCancel getDataGiftIssueCancel(String gift_no,
	// String ctn) throws Exception {
	//
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftIssueCancel> call = service.dataGiftIssueCancel(
	// dataGiftServiceId, dataGiftIssueCancelAccessKey, gift_no, ctn);
	// Response<DataGiftIssueCancel> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	/**
	 * 상품권 발행등록 (DataGiftReg)
	 * 
	 * @param issue_no
	 *            - "상품권 발행번호 (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사 페이지에서
	 *            확인가능 (각 전문에 사용하는 암호화키는 동일)"
	 * @param ctn
	 *            - "CTN (AES256암호화 후 BASE64 인코딩) -AES256 암호화키는제휴사 페이지에서 확인가능 (각
	 *            전문에 사용하는 암호화키는 동일)"
	 * @return
	 * @throws Exception
	 */
	public DataGiftReg getDataGiftReg(String issue_no, String ctn)
			throws Exception {
		logger.debug("getDataGiftReg start");
		DataGiftReg responseBody = null;
		Retrofit retrofit = getInstance(dataGiftServiceUrl);

		DataGiftService service = retrofit.create(DataGiftService.class);
		Call<DataGiftReg> call = service.dataGiftReg(dataGiftServiceId, dataGiftRegAccessKey, issue_no, ctn);
		Response<DataGiftReg> response = call.execute();

		// Request request = call.request();
		// Response response = response;
		
		if(response == null){
			logger.error(String.format("fail dataGiftReg, No response!"));
			responseBody = new DataGiftReg();
			responseBody.setResult(null);
			responseBody.setResultCode(null);
			responseBody.setResultMessage("No response!");
		}else{
			responseBody = response.body();
			if(responseBody == null)
			{
				logger.error(String.format("dataGiftReg - response status code[%d]", response.code()));
			    Gson gson = new Gson();
			    TypeAdapter<DataGiftReg> adapter = gson.getAdapter(DataGiftReg.class);
			    try {
			        if (response.errorBody() != null){
			        	responseBody = adapter.fromJson(response.errorBody().string());
			        }else{
						responseBody = new DataGiftReg();
						responseBody.setResult(null);
						responseBody.setResultCode(null);
						responseBody.setResultMessage(String.format("No response body, status code[%d]", response.code()));			        
					}
			    } catch (IOException e) {
			        logger.error(String.format("dataGiftReg fail, reason=%s", e.getMessage()), e);
			        
					responseBody = new DataGiftReg();
					responseBody.setResult(null);
					responseBody.setResultCode(null);
					responseBody.setResultMessage(String.format("dataGiftReg fail, reason=%s", e.getMessage()));	
			    }
			}
		}
		logger.debug("getDataGiftReg success");
		return responseBody;
	}

	// /**
	// * 상품권 PIN번호 인증 (DataGiftPINCheck)
	// *
	// * @param ctn
	// * - "CTN - AES256암호화 후 BASE64 인코딩"
	// * @param gift_no
	// * - "상품권 번호(PIN) - AES256암호화 후 BASE64 인코딩"
	// * @return
	// * @throws Exception
	// */
	// public DataGiftPINCheck getDataGiftPINCheck(String ctn, String gift_no)
	// throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftPINCheck> call = service.dataGiftPINCheck(
	// dataGiftServiceId, dataGiftPINCheckAccessKey, gift_no, ctn);
	// Response<DataGiftPINCheck> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 상품권 PIN번호 등록(DataGiftPINRegist)
	// *
	// * @param ctn
	// * - "CTN - AES256암호화 후 BASE64 인코딩"
	// * @param gift_no
	// * - "상품권 번호(PIN) - AES256암호화 후 BASE64 인코딩"
	// * @return
	// * @throws Exception
	// */
	// public DataGiftPINRegist getDataGiftPINRegist(String ctn, String gift_no)
	// throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftPINRegist> call = service.dataGiftPINRegist(
	// dataGiftServiceId, dataGiftPINRegistAccessKey, gift_no, ctn);
	// Response<DataGiftPINRegist> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 상품권 사용 여부 조회 (DataGiftPinUsedCheck)
	// *
	// * @param gift_no
	// * - "상품권 번호(PIN) - AES256암호화 후 BASE64 인코딩"
	// * @return
	// * @throws Exception
	// */
	// public DataGiftPinUsedCheck getDataGiftPinUsedCheck(String gift_no)
	// throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftPinUsedCheck> call = service.dataGiftPinUsedCheck(
	// dataGiftServiceId, dataGiftPinUsedCheckAccessKey, gift_no);
	// Response<DataGiftPinUsedCheck> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	/**
	 * 상품권 리스트 조회 (DataGiftOwnList)
	 * 
	 * @param ctn
	 *            - "CTN - AES256암호화 후 BASE64 인코딩"
	 * @param reg_type
	 *            - "상품권리스트 타입 U : 나의 이용중 내역(미입력 시 기본값) P : 충전하기 내역 G : 선물하기 내역
	 *            R : 상품권번호 등록 내역"
	 * @param start_date
	 *            - 등록일 검색 시작일
	 * @param end_date
	 *            - 등록일 검색 종료일
	 * @return
	 * @throws Exception
	 */
	public DataGiftOwnList getDataGiftOwnList(String ctn, String reg_type,
			String start_date, String end_date) throws Exception {
		logger.debug("getDataGiftOwnList start");
		DataGiftOwnList responseBody = null;
		Retrofit retrofit = getInstance(dataGiftServiceUrl);
		DataGiftService service = retrofit.create(DataGiftService.class);

//		Call<DataGiftOwnList> call = service.dataGiftOwnList(dataGiftServiceId,
//				dataGiftOwnListAccessKey, 
//				ctn, reg_type, start_date, end_date);
		
		DataGiftOwnListRequest requestBody = new DataGiftOwnListRequest();
		requestBody.setCtn(ctn);
		requestBody.setReg_type(reg_type);
		requestBody.setStart_date(start_date);
		requestBody.setEnd_date(end_date);
		
		Call<DataGiftOwnList> call = null;
		
		if(start_date == null || end_date == null || "".contentEquals(start_date.trim()) || "".contentEquals(end_date.trim()))
		{
			call = service.dataGiftOwnList(dataGiftServiceId,	dataGiftOwnListAccessKey, 
					requestBody.getCtn(), 
					requestBody.getReg_type(), "19000101", "99991230");
		}else{
			call = service.dataGiftOwnList(dataGiftServiceId,	dataGiftOwnListAccessKey, 
					requestBody.getCtn(), 
					requestBody.getReg_type(), 
					requestBody.getStart_date(), 
					requestBody.getEnd_date());
		}
	
		
		Response<DataGiftOwnList> response = call.execute();

		if(response == null){
			logger.error(String.format("fail getDataGiftOwnList, No response!"));
			responseBody = new DataGiftOwnList();
			responseBody.setResult(null);
			responseBody.setResultCode(null);
			responseBody.setResultMessage("No response!");
		}else{
			responseBody = response.body();
			if(responseBody == null)
			{
				logger.error(String.format("getDataGiftOwnList - response status code[%d]", response.code()));
			    Gson gson = new Gson();
			    TypeAdapter<DataGiftOwnList> adapter = gson.getAdapter(DataGiftOwnList.class);
			    try {
			        if (response.errorBody() != null){
			        	responseBody = adapter.fromJson(response.errorBody().string());
			        }else{
						responseBody = new DataGiftOwnList();
						responseBody.setResult(null);
						responseBody.setResultCode(null);
						responseBody.setResultMessage(String.format("No response body, status code[%d]", response.code()));			        
					}
			    } catch (IOException e) {
			        logger.error(String.format("getDataGiftOwnList fail, reason=%s", e.getMessage()), e);
			        
					responseBody = new DataGiftOwnList();
					responseBody.setResult(null);
					responseBody.setResultCode(null);
					responseBody.setResultMessage(String.format("DataGiftOwnList fail, reason=%s", e.getMessage()));	
			    }
			}
		}

		return responseBody;
	}

	/**
	 * 고객 CTN 인증 (DataGiftCtnCheck)
	 *
	 * @param ctn
	 *            - "CTN - AES256암호화 후 BASE64 인코딩"
	 * @return
	 * @throws Exception
	 */
	public DataGiftCtnCheck getDataGiftCtnCheck(String ctn) throws Exception {
		logger.debug("getDataGiftCtnCheck start");
		DataGiftCtnCheck responseBody = null;
		Retrofit retrofit = getInstance(dataGiftServiceUrl);

		DataGiftService service = retrofit.create(DataGiftService.class);
		
//		Call<DataGiftCtnCheck> call = service.dataGiftCtnCheck(
//				dataGiftServiceId, dataGiftCtnCheckAccessKey, ctn);
		
		DataGiftCtnCheckRequest requestBody = new DataGiftCtnCheckRequest();
		requestBody.setCtn(ctn);
		Call<DataGiftCtnCheck> call = service.dataGiftCtnCheck(dataGiftServiceId, dataGiftCtnCheckAccessKey, requestBody.getCtn());
		
		Response<DataGiftCtnCheck> response = call.execute();

		if(response == null){
			logger.error(String.format("fail dataGiftCtnCheck, No response!"));
			responseBody = new DataGiftCtnCheck();
			responseBody.setResult(null);
			responseBody.setResultCode(null);
			responseBody.setResultMessage("No response!");
		}else{
			responseBody = response.body();
			if(responseBody == null)
			{
				logger.error(String.format("dataGiftCtnCheck - response status code[%d]", response.code()));
			    Gson gson = new Gson();
			    TypeAdapter<DataGiftCtnCheck> adapter = gson.getAdapter(DataGiftCtnCheck.class);
			    try {
			        if (response.errorBody() != null){
			        	responseBody = adapter.fromJson(response.errorBody().string());
			        }else{
						responseBody = new DataGiftCtnCheck();
						responseBody.setResult(null);
						responseBody.setResultCode(null);
						responseBody.setResultMessage(String.format("No response body, status code[%d]", response.code()));			        
					}
			    } catch (IOException e) {
			        logger.error(String.format("dataGiftCtnCheck fail, reason=%s", e.getMessage()), e);
			        
					responseBody = new DataGiftCtnCheck();
					responseBody.setResult(null);
					responseBody.setResultCode(null);
					responseBody.setResultMessage(String.format("dataGiftCtnCheck fail, reason=%s", e.getMessage()));	
			    }
			}
		}

		return responseBody;
	}

	// /**
	// * 상품권 MMS 재발송(DataGiftPinResend)
	// *
	// * @param issue_date
	// * - 발행일자
	// * @param gift_no
	// * - 상품권고유번호
	// * @return
	// * @throws Exception
	// */
	// public DataGiftPinResend getDataGiftPinResend(String issue_date,
	// String gift_no) throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftPinResend> call = service.dataGiftPinResend(
	// dataGiftServiceId, dataGiftPinResendAccessKey, issue_date,
	// gift_no);
	// Response<DataGiftPinResend> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 상품권 취소(DataGiftPinCancelGift)
	// *
	// * @param issue_date
	// * - 발행일자
	// * @param gift_no
	// * - 상품권고유번호
	// * @return
	// * @throws Exception
	// */
	// public DataGiftPinCancelGift getDataGiftPinCancelGift(String issue_date,
	// String gift_no) throws Exception {
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftPinCancelGift> call = service.dataGiftPinCancelGift(
	// dataGiftServiceId, dataGiftPinCancelGiftAccessKey, issue_date,
	// gift_no);
	// Response<DataGiftPinCancelGift> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 데이터 충전하기 [PG결제] (DataGiftChgDataPgpay)
	// *
	// * @param lgd_amount
	// * - 결제금액 (AES256암호화 후 BASE64 인코딩)
	// * @param lgd_oid
	// * - 주문번호 (상점에서 정의하는 유니크한 주문번호)
	// * @param pay_type
	// * - 결제 수단
	// * @param isAgree
	// * - ‘Y’
	// * @param ctn
	// * - CTN (AES256암호화 후 BASE64 인코딩)
	// * @param issue_no
	// * - 상품권 발행번호 (AES256암호화 후 BASE64 인코딩)
	// * @return
	// * @throws Exception
	// */
	// public DataGiftChgDataPgpay getDataGiftChgDataPgpay(String lgd_amount,
	// String lgd_oid, String pay_type, String isAgree, String ctn,
	// String issue_no) throws Exception {
	//
	// final String cst_platform = VALUE_CST_PLATFORM;
	// final String cst_mid = VALUE_CST_MID;
	// final String lgd_paykey = VALUE_LGD_PAYKEY;
	// final String lgd_custom_processtimeout = VALUE_LGD_CUSTOM_PROCESSTIMEOUT;
	//
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftChgDataPgpay> call = service.dataGiftChgDataPgpay(
	// dataGiftServiceId, dataGiftChgDataPgpayAccessKey, cst_platform,
	// cst_mid, lgd_paykey, lgd_custom_processtimeout, lgd_amount,
	// lgd_oid, pay_type, isAgree, ctn, issue_no);
	// Response<DataGiftChgDataPgpay> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 데이터 선물하기 [PG결제-바로충전] (DataGiftGiveDataPgpay)
	// *
	// * @param lgd_amount
	// * - 결제금액 (AES256암호화 후 BASE64 인코딩)
	// * @param lgd_oid
	// * - 주문번호 (상점에서 정의하는 유니크한 주문번호)
	// * @param pay_type
	// * - 결제 수단
	// * @param isAgree
	// * - ‘Y’
	// * @param ctn
	// * - CTN (AES256암호화 후 BASE64 인코딩)
	// * @param gift_ctn
	// * - 선물 받을 사용자CTN (AES256암호화 후 BASE64 인코딩)
	// * @param issue_no
	// * - 상품권 발행번호 (AES256암호화 후 BASE64 인코딩)
	// * @param mms_context
	// * - 선물 메시지
	// * @return
	// * @throws Exception
	// */
	// public DataGiftGiveDataPgpay getDataGiftGiveDataPgpay(String lgd_amount,
	// String lgd_oid, String pay_type, String isAgree, String ctn,
	// String gift_ctn, String issue_no, String mms_context)
	// throws Exception {
	//
	// final String cst_platform = VALUE_CST_PLATFORM;
	// final String cst_mid = VALUE_CST_MID;
	// final String lgd_paykey = VALUE_LGD_PAYKEY;
	// final String lgd_custom_processtimeout = VALUE_LGD_CUSTOM_PROCESSTIMEOUT;
	//
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftGiveDataPgpay> call = service.dataGiftGiveDataPgpay(
	// dataGiftServiceId, dataGiftGiveDataPgpayAccessKey, cst_platform,
	// cst_mid, lgd_paykey, lgd_custom_processtimeout, lgd_amount,
	// lgd_oid, pay_type, isAgree, ctn, gift_ctn, issue_no,
	// mms_context);
	// Response<DataGiftGiveDataPgpay> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }

	// /**
	// * 데이터 선물하기 [PG결제-상품권번호전송] (DataGiftGivePinPgpay)
	// *
	// * @param lgd_amount
	// * - 결제 금액 (AES256암호화 후 BASE64 인코딩)
	// * @param lgd_oid
	// * - 주문번호 (상점에서 정의하는 유니크한 주문번호)
	// * @param pay_type
	// * - 결제 수단
	// * @param isAgree
	// * - ‘Y’
	// * @param ctn
	// * - CTN (AES256암호화 후 BASE64 인코딩)
	// * @param gift_ctn
	// * - 받을사용자CTN (AES256암호화 후 BASE64 인코딩)
	// * @param issue_no
	// * - 받을사용자CTN (AES256암호화 후 BASE64 인코딩)
	// * @param mms_type
	// * - MMS 전송 시간 타입 (now/reserve)
	// * @param mms_reserveDate
	// * - MMS 전송 시간 타입 (now/reserve)
	// * @param mms_context
	// * - 선물 메시지
	// * @return
	// * @throws Exception
	// */
	// public DataGiftGivePinPgpay getDataGiftGivePinPgpay(String lgd_amount,
	// String lgd_oid, String pay_type, String isAgree, String ctn,
	// String gift_ctn, String issue_no, String mms_type,
	// String mms_reserveDate, String mms_context) throws Exception {
	//
	// final String cst_platform = VALUE_CST_PLATFORM;
	// final String cst_mid = VALUE_CST_MID;
	// final String lgd_paykey = VALUE_LGD_PAYKEY;
	// final String lgd_custom_processtimeout = VALUE_LGD_CUSTOM_PROCESSTIMEOUT;
	//
	// Retrofit retrofit = getInstance(dataGiftServiceUrl);
	//
	// DataGiftService service = retrofit.create(DataGiftService.class);
	// Call<DataGiftGivePinPgpay> call = service.dataGiftGivePinPgpay(
	// dataGiftServiceId, dataGiftGivePinPgpayAccessKey, cst_platform,
	// cst_mid, lgd_paykey, lgd_custom_processtimeout, lgd_amount,
	// lgd_oid, pay_type, isAgree, ctn, gift_ctn, issue_no, mms_type,
	// mms_reserveDate, mms_context);
	// Response<DataGiftGivePinPgpay> response = call.execute();
	//
	// // Request request = call.request();
	// // Response response = response;
	//
	// return response.body();
	// }
}
