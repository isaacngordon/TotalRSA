import java.math.BigInteger;

import javax.swing.JOptionPane;

public class PrivateKeyFinder implements Runnable{

    BigInteger p,q,n,e,d;

    public PrivateKeyFinder(){

    }

    public void run(){

        String p_p = JOptionPane.showInputDialog("What is p?");
        String p_q = JOptionPane.showInputDialog("What is q?");
        String p_e = JOptionPane.showInputDialog("What is e?");

        p = new BigInteger(p_p);
        q = new BigInteger(p_q);
        e = new BigInteger(p_e);

        KeyGen k = new KeyGen();
        d = k.generateTheD(p, q, e);

    }


}