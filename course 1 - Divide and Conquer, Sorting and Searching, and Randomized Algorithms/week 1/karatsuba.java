/*
If the numbers are in odd length, it is handled by last else case
since the code fetches last 4 digits at a time if more than 4 digits are found
*/

import java.math.BigInteger;

class karatsuba {
    public static void main(String args[] ) throws Exception {
    
	/*Replace the strings to multiply*/    
	    
    String x = "3141592653589793238462643383279502884197169399375105820974944592";
    String y = "2718281828459045235360287471352662497757247093699959574966967627";
    
    System.out.println(karatsuba(x,y));

    }
    
    private static String karatsuba(String l, String m){
    
    int len1 = l.length(), len2 = m.length();
    
    if(len1 > 4 && len2 > 4){
		
        String a = l.substring(0,len1-4);
        String b = l.substring(len1-4);
        String c = m.substring(0,len2-4);
        String d = m.substring(len2-4);
        
		//System.out.println("a:"+a+"\nb:"+b+"\nc:"+c+"\nd:"+d);
		
        String strac = karatsuba(a,c);
        String strbd = karatsuba(b,d);
        
        String strad = karatsuba(a,d);
        String strbc = karatsuba(b,c);
        
        BigInteger z2 = new BigInteger(strac);
        z2 = z2.multiply(BigInteger.valueOf(100000000)); //ac ready for addition
        
        BigInteger bi1,bi2;
        bi1 = new BigInteger(strad);
        bi2 = new BigInteger(strbc);
        
        //bd adds directly.
        BigInteger z1 = new BigInteger(strbd);

        // 3 - 2 - 1 = ad + bc and multiply by 10000 for later step;
        BigInteger z0 = (bi1.add(bi2)).multiply(BigInteger.valueOf(10000));   

        return String.valueOf((z2.add(z1)).add(z0));
    }
	
	else if (len1 == 4 && len2 == 4){
		String a = l.substring(0,2);
        String b = l.substring(2);
        String c = m.substring(0,2);
        String d = m.substring(2);
        
		//System.out.println("a:"+a+"\nb:"+b+"\nc:"+c+"\nd:"+d);
		
        String strac = karatsuba(a,c);
        String strbd = karatsuba(b,d);
        
        String strad = karatsuba(a,d);
        String strbc = karatsuba(b,c);
        
        BigInteger z2 = new BigInteger(strac);
        z2 = z2.multiply(BigInteger.valueOf(10000)); //ac ready for addition
        
        BigInteger bi1,bi2;
        bi1 = new BigInteger(strad);
        bi2 = new BigInteger(strbc);
        
        //bd adds directly.
        BigInteger z1 = new BigInteger(strbd);

        // 3 - 2 - 1 = ad + bc and multiply by 100 for later step;
        BigInteger z0 = (bi1.add(bi2)).multiply(BigInteger.valueOf(100));   

        return String.valueOf((z2.add(z1)).add(z0));
		
	}
    
    //unequal lengths less than 4, direct multiplication
    else{  
        BigInteger bim1 = new BigInteger(m);
        BigInteger bil2 = new BigInteger(l);
        
        BigInteger res = bim1.multiply(bil2);
        
        return ""+res;
    }
        
    }
}
