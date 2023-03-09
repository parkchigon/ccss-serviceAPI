package com.lgu.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Util {
	
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	
	public static String AESEncode(String key, String str) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] keyData = key.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		 
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(ivBytes));
		 
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String encodedStr = new String(Base64.encodeBase64(encrypted));		 
		 
		return encodedStr;
	}
	
	public static String AESDecode(String key, String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {	
		byte[] keyData = key.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(ivBytes));
		
		byte[] decrypted = Base64.decodeBase64(str.getBytes());
		
		String decodedStr = new String(c.doFinal(decrypted), "UTF-8");
		return decodedStr;
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		//neSAbrzy/0T8jB2+egCDRw==
		
		//yd1505 : MDAxMTAwMDcyODA3NzI0MTYwMg==6305 : membId : 114450572054626305
		
		System.out.println("**********************************************************");
		System.out.println("**********************************************************");
		System.out.println("**********************************************************");
		
		
		System.out.println("**********************************************************");
		System.out.println("[serial: ]:"+AESEncode("NTc3NTY3MzEzMDY5Njg2NjUzMg==3105","8888888888857756731F"));
		System.out.println("[ctn : ]:"+AESEncode("NTc3NTY3MzEzMDY5Njg2NjUzMg==3105","012057756731"));
		System.out.println("**********************************************************");
		
		System.out.println("TARGET======================================================");
		
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","126.970596"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","37.553322"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","126.969646"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","37.552997"));
		System.out.println("===========================================================");
		
		System.out.println("START ======================================================");
		
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","126.965010"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","37.529741"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","126.964972"));
		System.out.println(AESEncode("MDAxMTAwMDcyODc2MjUyMTk0Ng==6305","37.528208"));
		System.out.println("===========================================================");
		
		
		System.out.println("===========================================================");
		//MjI5OTA1NjYyMTE3NDUzNTg2Mw==8417
		System.out.println("===========================================================");
		System.out.println(AESDecode("MjI5OTA1NjYyMTE3NDUzNTg2Mw==8417","lKWapFFf6araiLa5EoWTBA\u003d\u003d\n"));
		System.out.println(AESDecode("MjI5OTA1NjYyMTE3NDUzNTg2Mw==8417","1eXIZY+bwbmYZknk4aKRCQ\u003d\u003d\n"));
		
		System.out.println(AESDecode("MjI5OTA1NjYyMTE3NDUzNTg2Mw==8417","3UPT5JuINJsc7mtYEiTF3Q\u003d\u003d\n"));
		System.out.println(AESDecode("MjI5OTA1NjYyMTE3NDUzNTg2Mw==8417","wDcN4/hQx6H6Zw9WtC9DQA\u003d\u003d\n"));
		System.out.println("===========================================================");
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.911391"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.5183031"));
		
		System.out.println("11===========================================================");
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.970652"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.554492"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","129.041443"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","35.114864"));
		System.out.println("===========================================================");
		
		System.out.println("22===========================================================");
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.9114684"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.5184188"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.970596"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.553322"));
		System.out.println("===========================================================");
		
		System.out.println("===========================================================");
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.9114684"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.5184188"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","126.951677"));
		System.out.println(AESEncode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","37.390924"));
		System.out.println("===========================================================");
		
		
		System.out.println(AESDecode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","t1kbar8ecNtPL9mKdKoIyw=="));
		System.out.println(AESDecode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","JrvVGnhEbhTFN5ihfqt0oA=="));
		System.out.println(AESDecode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","BntQZGKILPaZAJfUXJhc7Q="));
		System.out.println(AESDecode("MDAxMTAwMDIxODUxMjY4MzYxNA==3377","nggzVJoECKORzJkeNC+vuw=="));
		
		System.out.println("###################################################");
		
		System.out.println(AESDecode("ABCDEFGHIJKLMNOP1234567891234567","XWbtYHknTdVwm/1zjaXYPw=="));
		
		System.out.println("###################################################");
		String SX = AESEncode("ABCDEFGHIJKLMNOP","106.970651");
		String SY = AESEncode("ABCDEFGHIJKLMNOP","37.554491");
		System.out.println("SX:"+ SX);
		System.out.println("SY:"+ SY);
		System.out.println("###################################################");
		
		String startLonx = AESEncode("Dg1ODgxMjk5Mg==7","126.970652");
		String startLaty = AESEncode("Dg1ODgxMjk5Mg==7","37.554492");		
		
		String targetLonx = AESEncode("Dg1ODgxMjk5Mg==7","129.041443");
		String targetLaty = AESEncode("Dg1ODgxMjk5Mg==7","35.114864");	
		
		String viaLonx = AESEncode("Dg1ODgxMjk5Mg==7","127.433103");
		String viaLaty = AESEncode("Dg1ODgxMjk5Mg==7","36.331571");	
		
		
		System.out.println("[startLonx] :" + startLonx);
		System.out.println("[startLaty] :" + startLaty);
		System.out.println("[targetLonx] :" + targetLonx);
		System.out.println("[targetLaty] :" + targetLaty);
		System.out.println("[viaLonx] :" + viaLonx);
		System.out.println("[viaLaty] :" + viaLaty);
		
		
		System.out.println("%%%:" +AESDecode("TcyOTQ1MDY4OA==5","FQHG01PsF4lVlkyOdMANVg\u003d\u003d\n"));
		System.out.println("%%%:" +AESDecode("TcyOTQ1MDY4OA==5","GNoCNoVTp4BSA0TKrLf06g\u003d\u003d\n"));
	
		String encAA = AESEncode("DY0MzE2Njk2Nzl=1","127.433103");
		String encBB = AESEncode("DY0MzE2Njk2Nzl=1","36.331571");
		
		//String encBBB = AESEncode("1234567891234567","37.554492");
		System.out.println("##encAA" +encAA);
		System.out.println("##encBB" +encBB);
		String encCC = AESEncode("Dg1ODU2OTg0Mw==5","127.433120");
		String encDD = AESEncode("Dg1ODU2OTg0Mw==5","36.331571");	
		
		System.out.println("##encCC1 : " +encCC);
		System.out.println("##encDD1 : " +encDD); 
		//String encEE = AESEncode("jk2NTI3NTYxOQ==7","106.970651");
		//String encFF = AESEncode("jk2NTI3NTYxOQ==7","37.554491");		
		
		
		//String decAA = AESDecode("DY0MzE2Njk2Nzl=1",encAA);
		System.out.println("##encAA" +encAA);
		System.out.println("##encBB" +encBB);
		
		System.out.println("##encCC: " +encCC);
		System.out.println("##encDD: " +encDD);
		
		//System.out.println("##encEE: " +encEE);
		//System.out.println("##encFF: " +encFF);
		
		System.out.println(">>>>>" + AESEncode("abcdefghijklmnop","37.554492"));
		
		String encStr ="neSAbrzy/0T8jB2+egCDRw==";
		System.out.println(AESDecode("secure1234567890",encStr));
//		System.out.println(AESEncode("secure12345678901234",AESDecode("secure1234567890",encStr)));
		System.out.println("GPS 인코딩");
		System.out.println(AESEncode("DY0ODA1Mzg1NzQ=1","37.5181679"));
		System.out.println(AESEncode("DY0ODA1Mzg1NzQ=1","126.9110370"));
		System.out.println(AESEncode("DY0MzE2Njk2Nzl=1","37.5181679"));
		System.out.println(AESEncode("DY0MzE2Njk2Nzl=1","126.9110370"));
		System.out.println(AESDecode("DY0MzE2Njk2NzI=1",AESEncode("DY0MzE2Njk2NzI=1","36.1332456")));
		System.out.println(AESDecode("DY0MzE2Njk2NzI=1",AESEncode("DY0MzE2Njk2NzI=1","127.3132456")));
		
		System.out.println(AESDecode("DY0MzE2Njk2NzI=1","LaWphNEo7ocxegaUKghwOg=="));
		System.out.println(AESDecode("DY0MzE2Njk2NzI=1","A4X60/97vI5NgD+6t93Q+A=="));
		
		//YWJjZA==
		
		System.out.println("############################");
		System.out.println("ENC:" + AESEncode("DY0ODA1Mzg1NzQ=1","abcd"));
		System.out.println("############################");
		
		System.out.println("DEC:" + AESDecode("DY0ODA1Mzg1NzQ=1","hWis9Hb1R7s1w3NjxN45lQ=============="));
		System.out.println("DEC:" + AESDecode("DY0ODA1Mzg1NzQ=1", AESEncode("DY0ODA1Mzg1NzQ=1","abcd")));
		System.out.println("############################");
		
	}
}
