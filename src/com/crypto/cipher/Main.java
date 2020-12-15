package com.crypto.cipher;

/**
* This application performs encoding and decoding of the given strings with the 
* specified number of iterations.
*
* @author Deepak Pradhan

*/
public class Main {

	public static void main(String[] args) {
		CipherImpl cipher = new CipherImpl();
		System.out.println("Encoded String: " + cipher.encode("ZIXJYZ", 5));
		System.out.println("Decoded String: " + cipher.decode("IJHTIJ", 5));
	}

}
