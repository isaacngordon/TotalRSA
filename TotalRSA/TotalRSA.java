
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class TotalRSA{

    public static ArrayList<Long> primes = new ArrayList<Long>();


    public static void main(String args[]){

        int l = loadprimes(args);
        System.out.println("Primes loaded.\nPrimes list length: " + l);
        Dashboard dash = new Dashboard(primes);
        dash.openDash();

    }//main

    public static int loadprimes(String args[]){
        System.out.println("args num: " + args.length);
        try{
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = -2439;
            File file;
            if(args.length != 1){
                while(returnVal != JFileChooser.APPROVE_OPTION){
                    returnVal = fileChooser.showOpenDialog(null);
                } 
                file = fileChooser.getSelectedFile();
            }
            else{
                file = new File(args[0]);
            }
            
            
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            while(br.ready()){
                String line = br.readLine();
                String regex = "[0-9]+";
                Pattern pattern = Pattern.compile(regex);
                Matcher m = pattern.matcher(line);
                long l;
                while(m.find()){
                    l = Long.parseLong(m.group());
                    primes.add(((long) l ));
                }
            }
            br.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        primes.add((long) 2);
        primes.add((long) 3);
        }
        return primes.size();
    }//loadPrimes

}//class

