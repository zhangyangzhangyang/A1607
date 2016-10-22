package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyClass {
    public  static void main(String [] args){
        String password ="12345";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder builder  = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                builder.append(bytes[i]);
            }
            System.out.println(builder.toString());

            StringBuilder oxBuilder = new StringBuilder();   
            for (int j = 0; j < bytes.length; j++) {
                String hexString = Integer.toHexString(bytes[j]);
                oxBuilder.append(hexString);
            }
            System.out.println(oxBuilder.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
