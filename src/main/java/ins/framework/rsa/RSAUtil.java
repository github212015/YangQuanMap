package ins.framework.rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * RSA�㷨��ʵ�����ݵļ��ܽ��ܡ�
 *
 */
public class RSAUtil {
	
	private static Cipher cipher;
	
	static{
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Կ��
	 * @param filePath ������Կ��·��
	 * @return
	 */
	public static Map<String,String> generateKeyPair(String filePath){
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			// ��Կλ��
			keyPairGen.initialize(1024);
			// ��Կ��
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// ��Կ
			PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			// ˽Կ
			PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			//�õ���Կ�ַ���
			String publicKeyString = getKeyString(publicKey);
			//�õ�˽Կ�ַ���
			String privateKeyString = getKeyString(privateKey);
			//����Կ��д�뵽�ļ�
			FileWriter pubfw = new FileWriter(filePath+"/publicKey.keystore");
			FileWriter prifw = new FileWriter(filePath+"/privateKey.keystore");
			BufferedWriter pubbw = new BufferedWriter(pubfw);
			BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();
			//�����ɵ���Կ�Է���
			Map<String,String> map = new HashMap<String,String>();
			map.put("publicKey",publicKeyString);
			map.put("privateKey",privateKeyString);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �õ���Կ
	 * 
	 * @param key
	 *            ��Կ�ַ���������base64���룩
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	/**
	 * �õ�˽Կ
	 * 
	 * @param key
	 *            ��Կ�ַ���������base64���룩
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * �õ���Կ�ַ���������base64���룩
	 * 
	 * @return
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = (new BASE64Encoder()).encode(keyBytes);
		return s;
	}   	
	
	/**
	 * ʹ�ù�Կ�����Ľ��м��ܣ�����BASE64������ַ���
	 * @param publicKey
	 * @param plainText
	 * @return
	 */
	public static String encrypt(PublicKey publicKey,String plainText){
		try {			
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] enBytes = cipher.doFinal(plainText.getBytes());			
			return (new BASE64Encoder()).encode(enBytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ʹ��keystore�����Ľ��м���
	 * @param publicKeystore ��Կ�ļ�·��
	 * @param plainText      ����
	 * @return
	 */
	public static String encrypt(String publicKeystore,String plainText){
		try {			
			FileReader fr = new FileReader(publicKeystore);
			BufferedReader br = new BufferedReader(fr);
			String publicKeyString="";
			String str;
			while((str=br.readLine())!=null){
				publicKeyString+=str;
			}
			br.close();
			fr.close();
			cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(publicKeyString));
			byte[] enBytes = cipher.doFinal(plainText.getBytes());			
			return (new BASE64Encoder()).encode(enBytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * ʹ��˽Կ���������Ľ��н���
	 * @param privateKey
	 * @param enStr
	 * @return
	 */
	public static String decrypt(PrivateKey privateKey,String enStr){
		try {
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
			return new String(deBytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ʹ��keystore�����Ľ��н���
	 * @param privateKeystore  ˽Կ·��
	 * @param enStr	                                     ����
	 * @return
	 */
	public static String decrypt(String privateKeystore,String enStr){
		try {
			FileReader fr = new FileReader(privateKeystore);
			BufferedReader br = new BufferedReader(fr);
			String privateKeyString="";
			String str;
			while((str=br.readLine())!=null){
				privateKeyString+=str;
			}
			br.close();
			fr.close();			
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyString));
			byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
			return new String(deBytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		new RSAUtil().generateKeyPair("c:\\");
	}
}