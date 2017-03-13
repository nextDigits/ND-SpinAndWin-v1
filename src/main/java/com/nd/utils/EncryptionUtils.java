package com.nd.utils;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	private static String algorithm = "AES";
	private static String charset = "UTF-8";
	private static String key = "VZF2e3VLoS6XIOXZ23rL45f367jQ1e8r";

	public static String encrypt(String input) throws Exception {
		Key aesKey = new SecretKeySpec(key.getBytes(Charset.forName(charset)), algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes(Charset.forName(charset))));
	}

	public static String decrypt(String input) throws Exception {
		Key aesKey = new SecretKeySpec(key.getBytes(Charset.forName(charset)), algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		return new String(cipher.doFinal(Base64.getDecoder().decode(input)), Charset.forName(charset));
	}
}
