import java.math.BigInteger;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.*;

public class Brutus {

    static ArrayList<Long> primes;

    public Brutus(ArrayList<Long> p){
		primes = p;
    }

	/**
	 * @param args
	 */
	public void run() {
        BigInteger n,e,c;

		String p_n = JOptionPane.showInputDialog("What is n?");
        String p_e = JOptionPane.showInputDialog("What is e?");
        String p_c = JOptionPane.showInputDialog("What is c?");

        n = new BigInteger(p_n);
        e = new BigInteger(p_e);
		c = new BigInteger(p_c);
		
		System.out.println("running thread crack...");
		
		BigInteger dd = crack(e, n);
        System.out.println("*******************************CRACKED RSA KEY*********************************\n"
						 + "For n= " + n + "\n\ne= " + e + "\n\nD was found to be " + dd 
					   + "\n*******************************************************************************\n");
		
		
		boolean close = false;
		try{
			while(!close) {
				Scanner read = new Scanner(System.in);
				System.out.println("\nEnter a number to encrypt/decrypt, or .quit to exit.");
				String s = read.next();
			
				if (s.equalsIgnoreCase(".quit")) { 
					System.out.println("Cracking finished. Bye!");
					close = true;
				}
				if(close){
					read.close();
					break;
				}

				while (!s.matches("[0-9]+")) {
					System.out.println("This is not a valid number. Enter a number to encrypt/decrypt, or quit to exit.");
					s = read.next();
				}
				BigInteger num = new BigInteger(s);
				BigInteger result = RSA.endecrypt(num, dd, n);
				System.out.println("This char decrypted to " + result);
				System.out.println("The letter is " + (char) result.intValue());
				read.close();
			}
		} catch(NoSuchElementException nse){
			System.out.println(e);
		}
	}
	
	/**
	 * return d
	 * @param e
	 * @param c
	 * @return
	 */
	public static BigInteger crack(BigInteger e, BigInteger c) {
		
		BigInteger public_e = e;
		BigInteger public_c = c;
		
		boolean isCracked = false;
		long aa = -1;
		for (Long p: primes) {
			aa = p;
			if (public_c.mod(BigInteger.valueOf(p)).compareTo(BigInteger.ZERO) == 0) {
				isCracked = true;
				break;
			}
		}
		BigInteger dd = BigInteger.valueOf(-1);
		if (isCracked) {
			long bb = public_c.divide(BigInteger.valueOf(aa)).longValue();
			BigInteger mm = BigInteger.valueOf(aa - 1).multiply(BigInteger.valueOf(bb-1));
			dd = RSA.mod_inverse(public_e, mm);
		} else {
			// PrimeGenerator pg = new PrimeGenerator();
			int n = RSA.primes.size();
			while (!isCracked && n < RSA.primes.size()) {
				aa = RSA.primes.get(n);
				if (public_c.mod(BigInteger.valueOf(aa)).compareTo(BigInteger.ZERO) == 0) {
					isCracked = true;
					long bb = public_c.divide(BigInteger.valueOf(aa)).longValue();
					BigInteger mm = BigInteger.valueOf(aa - 1).multiply(BigInteger.valueOf(bb-1));
					dd = RSA.mod_inverse(public_e, mm);
					break;
				}
				n++;
			}
			if(!isCracked) System.out.println(">>> FAILED <<<" + "n= " + public_c + "  e= " + public_e + "\n");
		}
		return dd;
	}
}
