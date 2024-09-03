import it.unisa.dia.gas.jpbc.Element;

public class KGC {
    public static Element x,P;
    public static Element ri,Bi,RH,bi,temp;
    public static void init_ks(){
        x = Init.Zr.newRandomElement().getImmutable(); //X
        P = Init.g.powZn(x);  //  P
    }
    public static Element[] getPKG(String ID, Element Ai){
        String rule = "1";
        Element[] result = new Element[3];
        ri = Init.Zr.newRandomElement().getImmutable(); //X
        Bi = Init.g.powZn(ri);  //  P
        temp = Ai.add(Bi);
        RH = Init.h(rule);
        bi = ri.add(x.mul(Init.H2(ID,temp,RH)));
        result[0] = Bi;
        result[1] = bi;
        result[2] = RH;
        return result;
    }
}
