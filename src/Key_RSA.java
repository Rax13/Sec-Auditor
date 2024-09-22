
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Key_RSA {
    public static class KeyPair {
        public BigInteger n;
        public BigInteger e;
        public BigInteger d;
    }

    //公共静态方法pickE，计算与euler互质的整数e
    public static BigInteger pickE(BigInteger euler) {
        BigInteger e;
        Boolean flag = false;
        do {
            e = new BigInteger(euler.bitLength(), new Random());
            if(e.gcd(euler).equals(BigInteger.ONE)&&(e.compareTo(euler)<0)&&(e.compareTo(BigInteger.ONE)>0)){//如果e与euler互质，并且e小于euler且大于1
                flag = true;
            }
        } while (!flag);
        return e;
    }
    //初始化参数
    public static KeyPair initialize(int bitLength) {

        BigInteger p = new BigInteger(bitLength, 100,new Random());
        //System.out.println("p:" + p.toString());
        BigInteger q = new BigInteger(bitLength, 100,new Random());
        //System.out.println("q:" + q.toString());
        BigInteger n = p.multiply(q);
        //System.out.println("n:" + n.toString());

        BigInteger p1 = p.subtract(BigInteger.ONE); //计算P-1
        BigInteger q1 = q.subtract(BigInteger.ONE); //计算Q-1
        BigInteger euler = p1.multiply(q1);         //计算n的欧拉函数，即(P-1)*(Q-1)
        //System.out.println("euler:" + euler.toString());
        BigInteger e = pickE(euler);                                  //选择一个与euler互质的整数e
        //System.out.println("e:" + e.toString());
        BigInteger d =  e.modInverse(euler);                          //计算e模euler的逆元d
        //System.out.println("d:" + d.toString());

        KeyPair keys = new KeyPair();
        keys.n = n;
        keys.e = e;
        keys.d = d;
        return keys;
    }

    //将String类型的数据转换为BigInteger
    public static BigInteger convertIDToBigInteger(String id) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] idBytes = id.getBytes();
            byte[] hashBytes = md.digest(idBytes);
            return new BigInteger(1, hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    //用户私钥生成
    public static BigInteger userPrivateKey(String id, KeyPair keys) {

        BigInteger i = convertIDToBigInteger(id);
        BigInteger g = i.modPow(keys.d, keys.n);
        //System.out.println("私钥:" + g.toString());
        return g;
    }

    //进行签名
    public static BigInteger[] sign(BigInteger g, KeyPair keys)  {

        BigInteger tag1 = Init.H2(tag.ID+tag.Ai.toString()+tag.Bi.toString()+tag.name+tag.n+tag.Zi.toString()).toBigInteger();
        SecureRandom random = new SecureRandom();
        BigInteger r = new BigInteger(keys.n.bitLength(), random).mod(keys.n);
        BigInteger t = r.modPow(keys.e, keys.n);
        BigInteger f = convertIDToBigInteger(t.toString() + tag1.toString());//假设f是一个基于t和m的哈希函数
        BigInteger s = g.multiply(r.modPow(f, keys.n)).mod(keys.n);
        BigInteger[] result=new BigInteger[2];
        result[0] = s;
        result[1] = t;
        return result;

    }

    //验证
    public static void verify(BigInteger s,BigInteger t, String id, KeyPair keys)  {

        BigInteger tag1 = Init.H2(tag.ID+tag.Ai.toString()+tag.Bi.toString()+tag.name+tag.n+tag.Zi.toString()).toBigInteger();
        BigInteger i = convertIDToBigInteger(id);
        BigInteger f = convertIDToBigInteger(t.toString() + tag1.toString()); //假设f是一个基于t和m的哈希函数

        BigInteger left = s.modPow(keys.e, keys.n);
        BigInteger right = i.multiply(t.modPow(f, keys.n)).mod(keys.n);

        int result = left.compareTo(right);
        if(result == 0){
            System.out.println("Accept");                                        //如果相同，则签名有效
        }
        else{
            System.out.println("Reject");                                        //如果不相同，则签名无效
        }
    }
}
