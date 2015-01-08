package ins.framework.rsa;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.io.*;

public class RSA {
    
    private final static SecureRandom random = new SecureRandom();
    private BigInteger a;
    private BigInteger b;
    private BigInteger n;
    private BigInteger p;
    private BigInteger q;
    private Hashtable<String, BigInteger> publicKey;
    private Hashtable<String, BigInteger> privateKey;

    /**
     * ��ȡ��Կ
     * @return ��Կ
     */
    public Hashtable<String, BigInteger> getPrivateKey() {
        return privateKey;
    }

    /**
     * ������Կ
     * @param privateKey ��Կ
     */
    public void setPrivateKey(Hashtable<String, BigInteger> privateKey) {
        this.privateKey = privateKey;
        p = privateKey.get("p");
        q = privateKey.get("q");
        a = privateKey.get("a");
        n = p.multiply(q);
    }

    /**
     * ��ȡ��Կ
     * @return ��Կ
     */
    public Hashtable<String, BigInteger> getPublicKey() {
        return publicKey;
    }

    /**
     * ���ù�Կ
     * @param publicKey ��Կ
     */
    public void setPublicKey(Hashtable<String, BigInteger> publicKey) {
        this.publicKey = publicKey;
        n = publicKey.get("n");
        b = publicKey.get("b");
    }
    /**
     * ��������ΪNλ�Ĺ�Կ��˽Կ
     * @param N
     */
    public void genKey(int N)
    {
        // ��������N/2λ�Ĵ�����p��q
        p = BigInteger.probablePrime(N/2, random);
        q = BigInteger.probablePrime(N/2, random);
        // ���㣨p-1)*(q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        // ����ģ��p*q
        n = p.multiply(q);
        // �����һ��b��ʹ��gcd(b, phi) =1;
        // ͨ�õĹ�Կ��2^16 + 1=65537
        b = new BigInteger("65537");
        // �����a����b��ģn��
        a = b.modInverse(phi);
        publicKey = new Hashtable<String, BigInteger>();
        privateKey = new Hashtable<String, BigInteger>();
        publicKey.put("n", n);
        publicKey.put("b", b);
        privateKey.put("p", p);
        privateKey.put("q", q);
        privateKey.put("a", a);
    }
    /**
     * ���ܺ���
     * @param plainText ����
     * @return ����
     */
    public byte[] encrypt(byte[] plainText) {
        return new BigInteger(plainText).modPow(b, n).toByteArray();
    }
    /**
     * ���ܺ���
     * @param cipherText ����
     * @return ����
     */
    public byte[] decrypt(byte[] cipherText) {
        return new BigInteger(cipherText).modPow(a, n).toByteArray();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        RSA rsa = new RSA();
        rsa.genKey(1024); // ������Կ
        // ����˽Կ���ļ� key
        ObjectOutputStream keyout = new ObjectOutputStream(new FileOutputStream("key"));
        keyout.writeObject(rsa.getPrivateKey());
        keyout.flush();
        keyout.close();
        // ����һ����Ϣ
        byte[] cipher = rsa.encrypt("s��adskfajldsfj;akdsjf;lajfd;alkjdsffasdofoiaudsof<>>>><<<ADS<FA<DSF<ASD>FA<D>ADSF<A>D<FADS>FA<DS>FA<DS<FA>DSF<ADSf�������s����".getBytes("gbk"));
        // ��������Ϣд���ļ�
//        ObjectOutputStream fileout = new ObjectOutputStream(new FileOutputStream("file"));
//        fileout.writeObject(cipher);
//        fileout.flush();
        System.out.println("���ģ�"+cipher.toString()+"�����ȣ�"+cipher.length);
        // ������Կ
        ObjectInputStream keyin = new ObjectInputStream(new FileInputStream("key"));
        RSA rsa1 = new RSA();
        Hashtable<String, BigInteger> privateKey = (Hashtable<String, BigInteger>)keyin.readObject();
        rsa1.setPrivateKey(privateKey);
        keyin.close();
//        ObjectInputStream filein = new ObjectInputStream(new FileInputStream("file"));
//        byte[] cp = (byte[])filein.readObject();
        // ����
        byte[] plain = rsa1.decrypt(cipher);
        System.out.println(new String(plain,"gbk"));
    }
}
