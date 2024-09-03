import it.unisa.dia.gas.jpbc.Element;

public class Main {
    public static void main(String[] args) {

        Element Ai;
        Init.init();
        KGC.init_ks();
        String User_id = "1";

        Ai=User.getPKG();
        Element[] result = new Element[3];
        result=KGC.getPKG(User_id,Ai);
    }
}
