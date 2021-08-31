package com.example.jwt.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class Base64Util {
   public static String convertToBase64(Object val){
       return Base64.getEncoder().encodeToString(val.toString().getBytes());
   }

   public static Object convertFromBase64(String baseStr){
       Object obj = null;
       try {
           byte[] bytes = Base64.getDecoder().decode(baseStr);
           ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
           ObjectInputStream ois = new ObjectInputStream (bis);
           obj = ois.readObject();
           ois.close();
           bis.close();
       } catch (IOException ex) {
           ex.printStackTrace();
       } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
       }
       return obj;
   }
}
