import it.unisa.dia.gas.jpbc.Element;
import java.util.Random;
import java.math.BigInteger;
public class Main {
    public static String generateRandomString(int length) {
        //String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    public static String[] generateRandomStringArray(int arraySize, int stringLength) {
        String[] randomStrings = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            randomStrings[i] = generateRandomString(stringLength);
        }
        return randomStrings;
    }
    public static void main(String[] args) throws Exception {

        Init.init();
        String User_id = "1";
        Key_RSA.KeyPair keys = Key_RSA.initialize(512);
        //生成用户私钥
        BigInteger g = Key_RSA.userPrivateKey(User_id, keys);
        KGC.init_ks();
        //Genkey
        User.getPKG(User_id);
        Element[] result;
        result=KGC.getPKG(User_id);
        if(!User.verifyPKGData(result)){
            System.out.println("User verify PKG data failed");
        }
        Element[] Tj;
        String[] Ti_1 = generateRandomStringArray(Init.blockNum, 16);
        BigInteger[] Ti_2 = new BigInteger[Init.blockNum];
        for (int i = 0; i < Init.blockNum; i++) {
            Ti_2[i] = new BigInteger(Ti_1[i]);
        }
        Element[] Ti_3 = new Element[Init.blockNum];
        for (int i = 0; i < Init.blockNum; i++) {
            Ti_3[i] = Init.Zr.newElement().set(Ti_2[i]).getImmutable();
        }
        Timer.begin_timer();
        //Storf
        Tj=User.StorF(Ti_3,result,KGC.RH);
        //签名
        BigInteger[] result1=Key_RSA.sign(g,keys);
        System.out.println("storf ms " +  Timer.get_timer() );
        //Chal
        Timer.begin_timer();
        Element[] v;
        Key_RSA.verify(result1[0],result1[1], User_id, keys);
        v=Cloud.TPA();
        System.out.println("Chal ms " +  Timer.get_timer() );
        //Resp
        if(Cloud.Resp(Ti_3,Tj,v)){
            System.out.println("Cloud verify TAG data succeed");
        }
        Timer.begin_timer();
        //UptRule
        KGC.Refreshrule();
        result[1]=KGC.bi1;
        if(!User.verifyPKGData(result)){
            System.out.println("User verify PKG data failed");
        }
        Element[] Tj_new;
        Tj_new=User.StorF(Ti_3,result,KGC.RH1);
        BigInteger[] result2=Key_RSA.sign(g,keys);
        //Chal
        Element[] v_new;
        //验证签名
        Key_RSA.verify(result2[0],result2[1], User_id, keys);
        v_new=Cloud.TPA();
        //Resp
        if(Cloud.Resp(Ti_3,Tj_new,v_new)){
            System.out.println("Cloud verify NEW TAG data succeed");
        }
        System.out.println("UptRule ms " +  Timer.get_timer() );
    }
}
