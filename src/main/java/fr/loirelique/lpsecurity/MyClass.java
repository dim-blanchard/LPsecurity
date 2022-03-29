package fr.loirelique.lpsecurity;

import java.util.ArrayList;
import java.util.HashMap;

public class MyClass {



    public static HashMap<String,ArrayList<String>> listArrays=new HashMap<String,ArrayList<String>>();
    
    public static void getIpOfPlayerBeforeLogin(String ip , String name){
        
        

        if(listArrays.get(ip)!= null){
            (listArrays.get(ip)).add(name);
      
        }else{
            listArrays.put(ip,new ArrayList<String>());
            (listArrays.get(ip)).add(name);

        }   

        
  
    }

    public static void main(String args[]) {
        
      getIpOfPlayerBeforeLogin("1","j1");
      getIpOfPlayerBeforeLogin("1","j2");
      getIpOfPlayerBeforeLogin("1","j3");
      getIpOfPlayerBeforeLogin("1","j4");
      getIpOfPlayerBeforeLogin("1","j5"); 
      getIpOfPlayerBeforeLogin("1","j6");
      
      System.out.println(listArrays.get("1"));
      System.out.println(listArrays.get("1").size());
      System.out.println(listArrays.get("2"));  


    }
}