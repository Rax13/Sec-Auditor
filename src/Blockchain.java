import it.unisa.dia.gas.jpbc.Element;

public class Blockchain {


    public static boolean verifyTAGData(String ID){

        Element left = User.tag||ID||User.Ai||KGC.Bi||User.name||Init.blockNum||User.Zi; //g^bi
        Element right = Init.h(ID||User.Ai||KGC.Bi||User.name||Init.blockNum||User.Zi);
        return left.equals(right);
    }
}
