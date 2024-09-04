import it.unisa.dia.gas.jpbc.Element;

public class User {
    public static String ID;
    public static Element ai,Ai,tag;
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
    public static Element[] StorF(Element[] input){
        String name="1";
        for (int i = 0; i < input.length; i++) {
            input[i] = Init.Zr.newElement(i+1).getImmutable();
        }
        Element[] Tj = new Element[Init.blockNum];
        for (int i = 0; i < Tj.length; i++) {
            Tj[i] = Init.g.powZn(input[i].mul(ai.add(KGC.bi)));
        }
        Element Zi=Ai.mul(KGC.Bi.mul(KGC.P.powZn(Init.H2(ID,Ai.add(KGC.Bi),KGC.RH))));
        Element seata=Init.h(ID||Ai||KGC.Bi||name||Init.blockNum||Zi);
        tag=ID||Ai||KGC.Bi||name||Init.blockNum||Zi||seata;
        return Tj;
    }
}