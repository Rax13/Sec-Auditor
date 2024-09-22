import it.unisa.dia.gas.jpbc.Element;

public class Cloud {
    public static Element C,ro,deta,gama;
    public static Element[] v= new Element[Init.Tpa_blockNum];
    public static Element[] TPA(){
        for (int i = 0; i < v.length; i++) {
            v[i] = Init.Zr.newElement(i+1).getImmutable();
        }
        gama = Init.Zr.newRandomElement().getImmutable();
        C = Init.g.powZn(gama);
        return v;
    }
    public static boolean Resp(Element[] input,Element[] input1,Element[] input2){
        ro = Init.Zr.newZeroElement().getImmutable();
        deta = Init.G1.newOneElement().getImmutable();
        for (int i = 0; i < input2.length; i++) {
            ro=ro.add(input[i].mul(input2[i]));
            deta=deta.mul(input1[i].powZn(input2[i]));
        }
        Element left = Init.pairing.pairing(deta.powZn(gama), Init.g);
        Element right = Init.pairing.pairing(User.Zi,C.powZn(ro));
        return left.equals(right);
    }
}
