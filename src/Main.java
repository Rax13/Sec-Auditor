import it.unisa.dia.gas.jpbc.Element;

public class Main {
    public static void main(String[] args) {

        Init.init();
        KGC.init_ks();
        String User_id = "1";
        //Genkey
        User.getPKG(User_id);
        Element[] result;
        result=KGC.getPKG(User_id);
        if(!User.verifyPKGData(result)){
            System.out.println("User verify PKG data failed");
        }
        //Storf
        Element[] Ti = new Element[Init.blockNum];
        for (int i = 0; i < Ti.length; i++) {
            Ti[i] = Init.Zr.newElement(i+1).getImmutable();
        }
        Element[] Tj;
        Tj=User.StorF(Ti,result,KGC.RH);
        if(!Blockchain.verifyTAGData()){
            System.out.println("Blockchain verify TAG data failed");
        }
        //Chal
        Element[] v;
        v=Cloud.TPA();
        //Resp
        if(Cloud.Resp(Ti,Tj,v)){
            System.out.println("Cloud verify TAG data succeed");
        }
        //UptRule
        KGC.Refreshrule();
        result[1]=KGC.bi1;
        if(!User.verifyPKGData(result)){
            System.out.println("User verify PKG data failed");
        }
        Element[] Tj_new;
        Tj_new=User.StorF(Ti,result,KGC.RH1);
        if(!Blockchain.verifyTAGData()){
            System.out.println("Blockchain verify NEW TAG data failed");
        }
        //Chal
        Element[] v_new;
        v_new=Cloud.TPA();
        //Resp
        if(Cloud.Resp(Ti,Tj_new,v_new)){
            System.out.println("Cloud verify NEW TAG data succeed");
        }

    }
}
