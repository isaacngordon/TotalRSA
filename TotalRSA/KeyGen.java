import java.math.BigInteger;
import java.security.SecureRandom;
import javax.swing.*;

/**
 * @author Nguyen Duy Tiep on 23-Oct-17.
 */
public class KeyGen implements Runnable{

    
    /**
     * Trivial test program.
     *
     * @param args
     * @deprecated TODO remove main and make JUnit Testing or any other
     * methodology
     */
    public void run() {

        KeyGen rsa = new KeyGen(2048);
        String text1 = JOptionPane.showInputDialog("Enter a message to encrypt :");
        System.err.println("before encrpt>>  " + text1);

        String ciphertext = rsa.encrypt(text1);
        System.out.println("ciphertext>>  " + ciphertext);
        JOptionPane.showMessageDialog(null, "Your encrypted message : " + ciphertext);

        String de = rsa.decrypt(ciphertext);
        JOptionPane.showMessageDialog(null, "Your message after decrypt : " + de);
        System.out.println("after decrypt>>  " + ciphertext);
    }

    private BigInteger modulus, privateKey, publicKey;

    /**
     *
     * @param bits
     */
    public KeyGen(int bits) {
       generateKeys(bits);
    }

    

    /**
     *
     * @param message
     * @return encrypted message
     */
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
    }

    /**
     *
     * @param message
     * @return encrypted message as big integer
     */
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    /**
     *
     * @param encryptedMessage
     * @return plain message
     */
    public synchronized String decrypt(String encryptedMessage) {
        return new String((new BigInteger(encryptedMessage)).modPow(privateKey, modulus).toByteArray());
    }

    /**
     *
     * @param encryptedMessage
     * @return plain message as big integer
     */
    public synchronized BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    /**
     * Generate a new public and private key set.
     *
     * @param bits
     */
    public synchronized void generateKeys(int bits) {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        modulus = p.multiply(q);

        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("3");

        while (m.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(new BigInteger("2"));
        }

        privateKey = publicKey.modInverse(m);

        String str = "p=" + p + "\n\nq= " + q+ "\n\nn= " + p.multiply(q) + "\n\ne=" + publicKey + "\n\nd=" + privateKey;
        
        System.out.println(str);
        //JOptionPane.showMessageDialog(null, str);
    }






    /*

        Isaac Defined shit

    */

    public KeyGen(){};

    public BigInteger generateTheD(BigInteger p, BigInteger q, BigInteger e){
        BigInteger d;

        modulus = p.multiply(q);

        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = e;

        // while (m.gcd(publicKey).intValue() > 1) {
        //     publicKey = publicKey.add(new BigInteger("2"));
        // }

        privateKey = publicKey.modInverse(m);

        String str = "p=" + p + "\n\nq= " + q+ "\n\nn= " + p.multiply(q) + "\n\ne=" + publicKey + "\n\nd=" + privateKey;
        
        System.out.println(str);
        //JOptionPane.showMessageDialog(null, str);

        return privateKey;
    }

    public static BigInteger encrypt(BigInteger m, BigInteger n, BigInteger e){
        return m.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger m, BigInteger n, BigInteger d){
        return m.modPow(d, n);
    }
}
