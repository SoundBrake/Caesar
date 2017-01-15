package com.soundbrake.caesarcipher.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soundbrake.caesarcipher.CaesarCipher;

public class CaesarCipherTest {

	@Test
	public void testEncryption() {
		
		CaesarCipher cipher = new CaesarCipher();
		try{
			 assertEquals("lwkl", cipher.encrypt("test", 18));
			 
		 
		}catch(Exception e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDecryption() {
		
		CaesarCipher cipher = new CaesarCipher();
		try{
			 assertEquals("attackatonce", cipher.encrypt("exxegoexsrgi", -4));
			 assertEquals("attackatonce", cipher.crack("exxegoexsrgi"));
		 
		}catch(Exception e){
			fail(e.getMessage());
		}
	}

}
