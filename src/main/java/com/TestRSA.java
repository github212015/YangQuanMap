/*    */ package com;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TestRSA
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/*  7 */     RSAHelper helper = new RSAHelper();
/*  8 */     Map keyMap = RSAHelper.initKey();
/*  9 */     byte[] data = new String("���").getBytes();
/* 10 */     String publicKey = "";
/* 11 */     String privateKey = "";
/*    */ 
/* 21 */     publicKey = RSAHelper.getPublicKey(keyMap);
/* 22 */     privateKey = RSAHelper.getPrivateKey(keyMap);
/*    */ 
/* 39 */     byte[] enData = RSAHelper.encryptByPublicKey(data, publicKey);
/* 40 */     byte[] deData = RSAHelper.decryptByPrivateKey(enData, privateKey);
/*    */ 
/* 42 */     System.out.print("��Կ��" + publicKey);
/* 43 */     System.out.print("˽Կ��" + privateKey);
/* 44 */     System.out.println("-----------------");
/* 45 */     System.out.print("���ܺ����ݣ�" + new String(enData, "utf8"));
/* 46 */     System.out.print("���ܺ����ݣ�" + new String(deData, "GBK"));
/*    */   }
/*    */ }

/* Location:           C:\Users\DeathSilence\Desktop\mobileplat\mobileplat\WEB-INF\classes\
 * Qualified Name:     com.TestRSA
 * JD-Core Version:    0.6.0
 */