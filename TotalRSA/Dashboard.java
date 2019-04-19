import java.util.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;

import javax.swing.*;

import javafx.stage.FileChooser;

public class Dashboard extends JFrame{

    ArrayList<Long> primes;
    JButton btnGenerateKeys = new JButton("Generate Keys");
    JButton btnDiffieHellman = new JButton("Diffie Hellman");
    JButton btnCrackList = new JButton("Crack by List");
    JButton btnNewBruteForce = new JButton("New Brute Force Crack");
    JButton btnFindPrivateKey = new JButton("Find Private Key");
    JButton btnEncrypt = new JButton("Encrypt");
    JButton btnDecrypt = new JButton("Decrypt");

    

    public Dashboard(ArrayList<Long> primeList){
        primes = primeList;
        RSA.loadPrimes(primeList);
        initialize();
    }

    private void initialize(){
        //set JFrame
        this.setSize(500, 200);
        JPanel con = new JPanel();

        //generat key button
        btnGenerateKeys = new JButton("Generate Keys");
        btnGenerateKeys.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyGen keyGen =  new KeyGen();
                keyGen.run();
            }

        });
        con.add(btnGenerateKeys);

        //DH button
        btnDiffieHellman = new JButton("Diffie Hellman");
        btnDiffieHellman.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Diffie diffie = new Diffie();
                diffie.run();
            }
        });
        con.add(btnDiffieHellman);

        //btn crack by list
        btnCrackList = new JButton("Crack by List");
        btnCrackList.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = -2439;
                while(returnVal != JFileChooser.APPROVE_OPTION){
                    returnVal = fileChooser.showOpenDialog(Dashboard.this);
                } 
                File file = fileChooser.getSelectedFile();
                CrackPublicList cpl = new CrackPublicList(file);
                cpl.run();
            }
        });
        con.add(btnCrackList);


        btnNewBruteForce = new JButton("New Brute Force Crack");
        btnNewBruteForce.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Brutus crackAttack = new Brutus(primes);
                crackAttack.run();
            }
        });
        con.add(btnNewBruteForce);

        btnFindPrivateKey = new JButton("Compute Private Key");
        btnFindPrivateKey.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                PrivateKeyFinder pkf = new PrivateKeyFinder();
                pkf.run();
            }
        });
        con.add(btnFindPrivateKey);

        btnEncrypt = new JButton("Encrypt");
        btnEncrypt.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                BigInteger n, d, m;
                
                String p_n = JOptionPane.showInputDialog("What is n?");
                String p_d = JOptionPane.showInputDialog("What is encryption key?");
                String p_m = JOptionPane.showInputDialog("What is m?");

                n = new BigInteger(p_n);
                d = new BigInteger(p_d);
                m = new BigInteger(p_m);

                BigInteger result = KeyGen.encrypt(m, n, d);
                System.out.println( "Encrypted is>>> " + result);
                JOptionPane.showMessageDialog(null, ( "Encrypted is>>> " + result));


            }
        });
        con.add(btnEncrypt);

        btnDecrypt = new JButton("Decrypt");
        btnDecrypt.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                BigInteger n, ee, c;
                
                String p_n = JOptionPane.showInputDialog("What is n?");
                String p_ee = JOptionPane.showInputDialog("What is decryption key?");
                String p_c = JOptionPane.showInputDialog("What is c?");

               
                n = new BigInteger(p_n);
                ee = new BigInteger(p_ee);
                c = new BigInteger(p_c);

                BigInteger result = KeyGen.decrypt(c, n, ee);
                System.out.println( "Decrypted is>>> " + result);
                JOptionPane.showMessageDialog(null, ( "Decrypted is>>> " + result));
                
            }
        });
        con.add(btnDecrypt);

        //last JFrame setting
        this.add(con);
    }

    public void openDash(){
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}