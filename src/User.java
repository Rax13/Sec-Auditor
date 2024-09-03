import it.unisa.dia.gas.jpbc.Element;

public class User {
    public String m_IDString;
    public static Element ai,Ai;
    public Element[] Ti = new Element[Init.blockNum];
    //public String tag = "" + Manger.RN;
    public User(String ID) {
        m_IDString = ID;
    }

    public static Element getPKG(){
        ai = Init.Zr.newRandomElement().getImmutable();
        Ai = Init.g.powZn(ai);
        return Ai;
    }
}
