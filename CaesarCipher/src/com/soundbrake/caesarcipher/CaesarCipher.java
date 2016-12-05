package com.soundbrake.caesarcipher;

public class CaesarCipher {

	public String encrypt(String str, int key) {
		String encrypted = "";
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);

			if (Character.isUpperCase(c)) {
				// 26 letters of the alphabet so mod by 26
				c = c + (key % 26);
				if (c > 'Z')
					c = c - 26;
			} else if (Character.isLowerCase(c)) {
				c = c + (key % 26);
				if (c > 'z')
					c = c - 26;
			}
			encrypted += (char) c;
		}
		return encrypted;
	}

	public String decrypt(String str, int key) {
		String decrypted = "";
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				c = c - (key % 26);
				if (c < 'A')
					c = c + 26;
			} else if (Character.isLowerCase(c)) {
				c = c - (key % 26);
				if (c < 'a')
					c = c + 26;
			}
			decrypted += (char) c;
		}
		return decrypted;
	}
	
	public String decode(String enc, int offset) {
        return encode(enc, 26-offset);
    }
 
    public String encode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }
    
 // This method returns entropy for a string containing some english text
    // calculated using frequencies of individual letters.
    public static double getEntropy(String str) {
        double[] freq = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228,
                         0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
                         0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
                         0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
                         0.01974,0.00074};

        double res = 0;
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if ('a' <= ch && ch <= 'z') 
                res += -Math.log(freq[ch - 'a']);
            else if ('A' <= ch && ch <= 'Z')
                res += -Math.log(freq[ch - 'A']);
            // We don't need to do anything for other characters
        }
        return res;
    }
    
    public String crack(String text) {
    	
    	// This variable stores the value of lowest entropy so far.
        // Initialize with very large value, because all entropies are positive
        // and 0 will be less than all of them.
        double lowestEntropy = Double.MAX_VALUE;
        // A string corresponding to the lowest entropy.
        String lowestEntropyString = "";

        
        try{
        // Try all possible keys
        for (int key = 0; key < 26; ++key) {
            // We pass -key, because our method shifts characters to the right, 
            // so we pass negative value to make it shift to the left.
            String decodedText = decode(text, -key);
            double entropy = getEntropy(decodedText);
            if (entropy < lowestEntropy) {
                lowestEntropy = entropy;
                lowestEntropyString = decodedText;
            }
        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        return lowestEntropyString;
    	
    }

}
