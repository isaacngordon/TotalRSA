TotalRSA                    Author: Isaac Gordon
=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
CS 381 - Internet Security
Prof. Bon Sy
Queens College
=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+


HOW TO COMPILE
--------------
>> javac TotalRSA.java
>> javac Dashboard.java
>> javac Brutus.java


HOW TO RUN
----------
>> java Total RSA <prime.txt>
	- where prime.txt is a file of prime numbers, one per line. 


USE CASES
---------
Generate Keys ->  User prompts will lead to generating  p, q, n, d, e, cipher-text, and decrypted cipher-text
		  and output them to the console. 

Diffie Hellman -> will prompt the user for inputs for a DH exchange. The value of b will be sent encrypted 
		  with RSA. The resulting values of the DH exchange will be outputted to the console.

Crack By List -> a File Chooser will allow the user to upload a text file of RSA public keys (only n), one 
		 key per line. Let S be the set of all public keys. The program will then attempt to find 
		 a GCD of tuple in the set S X S. Results will be printed to the console in the form of "n= p= q=".

Compute Private Key -> User prompted for p,q,e and the program computes d. Values of p,q,n,e,d are 
                       printed to the console

Encrypt -> User is prompted for a message m, modulus n, and encryption key e/d. The cipher-text will 
	   be printed to the console. 

Decrypt -> User is prompted for cipher text x, modulus n, and decryption key e/d. The plain-text will 
	   be printed to the console.

New Brute Force Crack -> Prompts the user for a public key (n. e), then runs a new thread attempting to brute force 
			 crack the key and solve for the private key "d". This runs as a separate thread and will print 
		         out the results to the console once it finishes running. 
			 On success you will have the option to encrypt/decrypt messages using that key.


***!!! !!!***		***!!! !!!*** 
   ||| |||      BUGS  	   ||| |||
***       ***		***       ***

- all fixed :)


SOURCES
-------
This program was created using components whose authors are listed below. Said resources are modified
to different degrees for this program. I would say this program is 50/50 split between original code and 
resource code.

Prime Numbers : https://primes.utm.edu/lists/small/millions/
Basis for Generate Keys: https://github.com/TheAlgorithms/Java/blob/master/ciphers/RSA.java
Basis for Crack by List: https://github.com/Rawrz/RSA-Public-Key-Cracking
Basis for Brute Force: https://github.com/verachai/RSA/tree/master/miniRSA/src/miniRSA
