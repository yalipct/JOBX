package com.example.Jobx.util;

import java.math.BigInteger;

//Class used to decrypt the pdf.

public class EncryptedKey {
    public static void main(String args[]) {

        // Creating BigInteger object
        BigInteger b1;

        b1 = new BigInteger("2");
        int exponent = 800;

        int sum = 0;

        //(exponent >= 0) otherwise ArithmeticException is thrown
        try {

            // apply pow() method
            BigInteger result = b1.pow(exponent);

            //BigInteger converted to character array
            char[] digits = result.toString().toCharArray();


            //addition
           for (int i = 0; i< digits.length; i++) {
                //System.out.println(digits[i]);
               sum += Character.getNumericValue(digits[i]);
            }

            // print result
            System.out.println("Result of pow operation between " + b1
                    + " and " + exponent + " equal to " + result);

            //print addition
            System.out.println("Encrypted key = " + sum);

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
