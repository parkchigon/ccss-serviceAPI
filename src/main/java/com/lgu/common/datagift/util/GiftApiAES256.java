package com.lgu.common.datagift.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class GiftApiAES256 {
	private static String KEY = "";

	private static SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
		byte[] bytes = new byte[32];
		SecretKeySpec spec = null;
		bytes = KEY.getBytes();
		spec = new SecretKeySpec(bytes, "AES");
		return spec;
	}

	public static String encrypt(String text) throws Exception {
		SecretKeySpec spec = getKeySpec();
		Cipher cipher = Cipher.getInstance("AES");		// AES
		cipher.init(Cipher.ENCRYPT_MODE, spec);
		return GiftApiBase64Utils.base64Encode(cipher.doFinal(text.getBytes("UTF-8")));
	}

	public static String decrypt(String text) throws Exception {
		SecretKeySpec spec = getKeySpec();
		Cipher cipher = Cipher.getInstance("AES");		// AES
		cipher.init(Cipher.DECRYPT_MODE, spec);
		return new String(cipher.doFinal(GiftApiBase64Utils.base64Decode(text)), "UTF-8");
	}

	public static String encrypt(String text, String key) throws Exception {
		KEY = key;
		return encrypt(text);
	}

	public static String decrypt(String text, String key) throws Exception {
		KEY = key;
		return decrypt(text);
	}

	public static void main(String[] args) {
		try {
			String key = "dXQHxbTLsSidftS9SXVjUgiFCcf8oNl9"; // 제휴사페이지에서 확인 가능
			String ctn = "01080801497"; 
			String issueNo = "20181017554214174822";	// 20181017554214174822, 20181017439814183776, 20181017428814192466, 20181017784414195083, 20181017375514201826, 20181017839314204762
			
			// 암호화를 위한 변수, 01000110007(필링크 개발)  
			// encode_ctn[UHFER1lYeGtYdXhzNi9uVitRb0o3Zz09] => UHFER1lYeGtYdXhzNi9uVitRb0o3Zz09
			// encode_issueNo[T3ZmRDZ1ZnR2b0h2bmFSdTFiN2pEaUY4OHZLdVVjeDMyeEp5RG9oRWNKWT0=] => T3ZmRDZ1ZnR2b0h2bmFSdTFiN2pEaUY4OHZLdVVjeDMyeEp5RG9oRWNKWT0%3D

			String encrypt_ctn = GiftApiAES256.encrypt(ctn, key);
			String encode_ctn = new String(Base64.encodeBase64(encrypt_ctn.getBytes()));
			String decode_ctn = new String(Base64.decodeBase64(encode_ctn));
			String decrypt_ctn = GiftApiAES256.decrypt(decode_ctn, key);
			String urlencode_ctn = URLEncoder.encode(encode_ctn, "UTF-8");
			
			String encrypt_issueNo = GiftApiAES256.encrypt(issueNo, key);
			String encode_issueNo = new String(Base64.encodeBase64(encrypt_issueNo.getBytes()));
			String decode_issueNo = new String(Base64.decodeBase64(encode_issueNo));
			String decrypt_issueNo = GiftApiAES256.decrypt(decode_issueNo, key);
			String urlencode_issueNo = URLEncoder.encode(encode_issueNo, "UTF-8");
			
			System.out.println(String.format("encrypt_ctn[%s], encode_ctn[%s], decode_ctn[%s], decrypt_ctn[%s], urlencode_ctn[%s]"
					, encrypt_ctn, encode_ctn, decode_ctn, decrypt_ctn, urlencode_ctn));
			System.out.println(String.format("encrypt_issueNo[%s], encode_issueNo[%s], decode_issueNo[%s], decrypt_issueNo[%s], urlencode_issueNo[%s]"
					, encrypt_issueNo, encode_issueNo, decode_issueNo, decrypt_issueNo, urlencode_issueNo));
			
		} catch (Exception e) {
			e.printStackTrace();
		} // end of [try~catch]
	}
}
