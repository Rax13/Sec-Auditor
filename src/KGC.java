import it.unisa.dia.gas.jpbc.Element;

public class KGC {
    public static Element x,P;
    public static String ID;
    public static Element ri,Bi,RH,bi,temp,RH1,bi1;
    public static void init_ks(){
        x = Init.Zr.newRandomElement().getImmutable(); //X
        P = Init.g.powZn(x);  //  P
    }
    public static Element[] getPKG(String id){
        ID = id;
        Element[] result = new Element[3];
        ri = Init.Zr.newRandomElement().getImmutable(); //X
        Bi = Init.g.powZn(ri);  //  P
        temp = User.Ai.add(Bi);
        RH = Init.h(User.rule);
        bi = ri.add(x.mul(Init.H2(ID,temp,RH)));
        result[0] = Bi;
        result[1] = bi;
        result[2] = RH;
        return result;
    }
    public static void Refreshrule(){
        RH1=Init.h(User.rule1);
        bi1=bi.sub(x.mul(Init.H2(ID,User.Ai.add(Bi),RH))).add(x.mul(Init.H2(ID,User.Ai.add(Bi),RH1)));
    }
}
