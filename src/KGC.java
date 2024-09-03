import it.unisa.dia.gas.jpbc.Element;

public class KGC {
    public static Element x,P;
    public static Element ri,Bi,RH,bi,temp;
    public static void init_ks(){
        x = Init.Zr.newRandomElement().getImmutable(); //X
        P = Init.g.powZn(x);  //  P
    }
    public static void getPKG(String ID, Element Ai){
        String rule = "1";
        ri = Init.Zr.newRandomElement().getImmutable(); //X
        Bi = Init.g.powZn(ri);  //  P
        temp = Ai.add(Bi);
        RH = Init.h(rule);
        bi = ri.add(x.mul(Init.H2(ID,temp,RH)));
    }
}
