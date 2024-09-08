import it.unisa.dia.gas.jpbc.Element;

public class User {
    public static String ID,name;
    public static String rule="1";
    public static String rule1="2";
    public static Element ai,Ai,Zi,RH1;
    //public static Element[] Ti = new Element[Init.blockNum];
    //public String tag = "" + Manger.RN;

    public static void getPKG(String id){
        ai = Init.Zr.newRandomElement().getImmutable();
        Ai = Init.g.powZn(ai);
        ID = id;
    }
    public static boolean verifyPKGData(Element[] input){
        Element Bi = input[0]; //bi
        Element bi = input[1];         //Bi
        Element RH = input[2];
        Element temp = Bi.add(Ai);
        Element left = Init.g.powZn(bi); //g^bi
        Element right = Bi.mul(KGC.P.powZn(Init.H2(ID,temp,RH)));
        return left.equals(right);
    }
    public static Element[] StorF(Element[] input,Element[] input2,Element input3){
        name="1";
        Element bi=input2[1];
        Element[] Tj = new Element[Init.blockNum];
        for (int i = 0; i < Tj.length; i++) {
            Tj[i] = Init.g.powZn(input[i].mul(ai.add(bi)));
        }
        Zi=Ai.mul(KGC.Bi.mul(KGC.P.powZn(Init.H2(ID,Ai.add(KGC.Bi),input3))));
        Element seata=Init.h(ID+Ai.toString()+KGC.Bi.toString()+name+Init.blockNum+Zi.toString());
        tag.ID=ID;
        tag.name=name;
        tag.Zi=Zi;
        tag.Ai=Ai;
        tag.Bi=KGC.Bi;
        tag.n=Init.blockNum;
        tag.seata=seata;
        return Tj;
    }
}