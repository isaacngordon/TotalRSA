import java.math.BigInteger;

import javax.swing.JOptionPane;

class Diffie implements Runnable{

    public Diffie(){};

    public void run(){

        BigInteger d_b,a,b,n,e,p,g,gA,gB,gab;
        

        String p_b = JOptionPane.showInputDialog("What is encrypted b?");
        String p_n = JOptionPane.showInputDialog("What is n?");
        String p_e = JOptionPane.showInputDialog("What is e?");
        String p_p = JOptionPane.showInputDialog("What is p?");
        String p_g = JOptionPane.showInputDialog("What is g?");
        String p_a = JOptionPane.showInputDialog("Decide an a?");

    

        d_b = new BigInteger(p_b);
        n = new BigInteger(p_n);
        e = new BigInteger(p_e);
        p = new BigInteger(p_p);
        g = new BigInteger(p_g);
        a = new BigInteger(p_a);
        

        b = KeyGen.decrypt(d_b, n, e);
        gA = generateGpowA(g, a, p);
        gB = generateGpowA(g, b, p);
        gab = generateGpowA(g, a.multiply(b), p);

        
        JOptionPane.showMessageDialog(null, "g^a (mod p) = " + gA.toString());

        System.out.println("a= " + a);
        System.out.println("b= " + b);
        System.out.println("p= " + p);
        System.out.println("g= " + g);
        System.out.println("n= " + n);
        System.out.println("e= " + e);
        System.out.println("g^a (mod p) = " + gA.toString());
        System.out.println("g^b (mod p) = " + gB.toString());
        System.out.println("g^ab (mod p) = " + gab.toString());


    }

    private static BigInteger generateGpowA(BigInteger g, BigInteger a, BigInteger p){
        return g.modPow(a, p);
    }
}