import it.unisa.dia.gas.jpbc.Element;

public class Cloud {
    public static Element C,ro,deta,gama;
    public static Element[] v= new Element[Init.blockNum];
    public static Element[] TPA(String ID){
        Element left = User.tag||ID||User.Ai||KGC.Bi||User.name||Init.blockNum||User.Zi;
        Element right = Init.h(ID||User.Ai||KGC.Bi||User.name||Init.blockNum||User.Zi);
        if(left!=right){
            System.out.println("Cloud verify TAG data failed");
        }else {
            for (int i = 0; i < v.length; i++) {
                v[i] = Init.Zr.newElement(i+1).getImmutable();
            }
            gama = Init.Zr.newRandomElement().getImmutable();
            C = Init.g.powZn(gama);
        }
        return v;
    }
    public static boolean Resp(Element[] input,Element[] input1){
        ro = Init.Zr.newZeroElement().getImmutable();
        deta = Init.G1.newOneElement().getImmutable();
        for (int i = 0; i < input.length; i++) {
            ro=ro.add(input[i].mul(v[i]));
            deta=deta.mul(input1[i].mul(v[i]));
        }
        Element left = Init.pairing.pairing(deta.mul(gama), Init.g);
        Element right = Init.pairing.pairing(User.Zi,C.mul(ro));
        return left.equals(right);
    }
}
