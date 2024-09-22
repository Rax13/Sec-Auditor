import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import java.math.BigInteger;

public class Init {
    public static Pairing pairing = PairingFactory.getPairing("a.properties");
    public static Field G1, G2, Zr;
    public static Element g;
    public static int blockNum = 360;
    public static int Tpa_blockNum = 180;

    public static void init() {
        G1 = pairing.getG1();        //群
        G2 = pairing.getGT();
        Zr = pairing.getZr();//群

        g = Init.G1.newRandomElement().getImmutable();  //生成随机元素

    }
    public static Element H2(String element1){

        //这里必须加getImmutable(),否则后面其数值会变化
        return Init.pairing.getZr().newElement()
                .setFromHash(element1.getBytes(), 0, element1.length()).getImmutable();
    }

    //H2返回值为整数
    public static Element H1(String element1, Element element2,Element element3){
        String hashInputString = element1 + element2.toString() + element3.toString();

        //这里必须加getImmutable(),否则后面其数值会变化
        return Init.pairing.getZr().newElement()
                .setFromHash(hashInputString.getBytes(), 0, hashInputString.length()).getImmutable();
    }

    //h返回值为G1类型元素
    public static Element H3(String element1){
        return Init.pairing.getG1().newElement()
                .setFromHash(element1.getBytes(), 0, element1.length()).getImmutable();
    }//h3
}