import it.unisa.dia.gas.jpbc.Element;

public class Main {
    public static void main(String[] args) {

        Element Ai;
        Init.init();
        KGC.init_ks();
        String User_id = "1";
        //Genkey
        User.getPKG(User_id);
        Element[] result = new Element[3];
        result=KGC.getPKG(User_id);
        if(!User.verifyPKGData(result)){
            System.out.println("User verify PKG data failed");
        }
        //Storf
        Element[] Ti = new Element[Init.blockNum];
        for (int i = 0; i < Ti.length; i++) {
            Ti[i] = Init.Zr.newElement(i+1).getImmutable();
        }
        Element[] Tj = new Element[Init.blockNum];
        Tj=User.StorF(Ti);

    }
}
