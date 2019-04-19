import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author Roseline Okpara
 * 
 * RSACrack reads in the file full of RSA keys and creates threads to start the program.
 *
 */
public class CrackPublicList implements Runnable {

    public CrackPublicList(File f){
        this.file = f;
    }
    public File file;

    /**
     * Main program
     * @param args
     */
    public void run() {
        // TODO Auto-generated method stub
        LinkedList<BigInteger> keys = null;
        Scanner scanner = null;
        BigInteger key = null;
        if(true){
            try {
                scanner = new Scanner(file);
                keys = new LinkedList<BigInteger>();
                while(scanner.hasNext()){
                    key = scanner.nextBigInteger();
                    if(key.equals(BigInteger.ZERO)){
                        System.err.println("Invalid RSA Key in File");
                        return;
                    }
                    else{
                        keys.add(key);
                    }
                }
                if(keys.size() < 2){
                    return;
                }
            } 
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                System.err.println("File not found.");
                return;
            } 
            catch (Exception e){
                String err = scanner.next();
                if(!err.equals("\n")){
                    System.err.println("Invalid RSA key in file.");
                    return;
                }
            }
            
            //Set up barrier
            int parties = 1;
            for (int k = 0; k < 2; k++) {
                parties = parties * (keys.size()-k)/(k+1);
            } 
            CyclicBarrier barrier = new CyclicBarrier(parties,new Runnable(){
                @Override
                public void run() {
                    RSACrackRunnable.print();
                }    
            });
            //Start threads
            int combo = 1;
            for (int i = 0; i < keys.size(); ++ i){
                for(int c = combo; c < keys.size();c++){
                    new Thread (new RSACrackRunnable(keys.get(i), keys.get(c), barrier)).start();
                }
                combo++;
            }
        }
        else{
            System.err.println("Invalid number of Arguments."); 
            return;
        }
    }

}

