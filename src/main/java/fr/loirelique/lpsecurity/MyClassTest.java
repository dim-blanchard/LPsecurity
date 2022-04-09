package fr.loirelique.lpsecurity;

import java.util.ArrayList;
import java.util.HashMap;

public class MyClassTest {

    public static HashMap<String, ArrayList<String>> listArrays = new HashMap<String, ArrayList<String>>();

    public static void getIpOfPlayerBeforeLogin(String ip, String name) {
        if (listArrays.get(ip) != null) {
            (listArrays.get(ip)).add(name);

        } else {
            listArrays.put(ip, new ArrayList<String>());
            (listArrays.get(ip)).add(name);
        }
    }

    public static void main(String args[]) {
        
        String[] argss = new String[3];

        argss[0]="J'ai";
        argss[1]="peut être fait";
        argss[2]="une érreur.";



        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < argss.length; i++) {
            builder.append(argss[i]).append(" ");
        }
        String msg = builder.toString();
        System.out.println(msg);


















/*           // start stopwatch
          //for(int i =0 ; i < 10 ; i++){
          long startTime = System.nanoTime();
          // Here is the code to measure
          getIpOfPlayerBeforeLogin("1","qsfseeeeeqzgzergzgrgrggggggzrgzrgzrgzergzrg");
          getIpOfPlayerBeforeLogin("1","zgrzrgzgrzgggggrgrgrgrgrgqgrgqzrgtergqegzgg");
          getIpOfPlayerBeforeLogin("1","zrgzzgqqqqegeqggbzrbqwiukkkkkkkkkkkukgukget");
          getIpOfPlayerBeforeLogin("1","gergesbserbgsh'rjtgshey'j( y,cjc,cjc;j;,,,,");
          getIpOfPlayerBeforeLogin("1","jgdergwdrgwdrgzer gb'tzbesgnjetgdxrnhnxhtnn");
          getIpOfPlayerBeforeLogin("1","xnyte(kxtj,kxxynjhxhbrxbgxrxnjrwrthfhjytjyj");
          
          System.out.println(listArrays.get("1"));
          System.out.println(listArrays.get("1").size());
          
          listArrays.get("1").remove("zrgzzgqqqqegeqggbzrbqwiukkkkkkkkkkkukgukget");
          
          System.out.println(listArrays.get("1").size());
          
          System.out.println(listArrays.get("1"));
          // stop stopwatch
          long endTime = System.nanoTime();
          
          System.out.println("Test de vitesse : " + (endTime -
          startTime)*Math.pow(10,-6) + " ms"); */
        

        // }

        // System.out.println("Test de vitesse : " + calcul + " ms");

        /*
         * try (Connection connection_update = DriverManager.getConnection(
         * ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
         * ConfigBdd.getPort()
         * + "/"
         * + ConfigBdd.getDatabase1()
         * + "?characterEncoding=latin1&useConfigs=maxPerformance",
         * ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
         * String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
         * " SET online=? WHERE uuid=?";
         * try (PreparedStatement statement2_select =
         * connection_update.prepareStatement(requet_Update_sql2)) {
         * statement2_select.setInt(1, 0);
         * statement2_select.setString(2, uuid);
         * statement2_select.executeUpdate();
         * }
         * 
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

       /*  String str = "Bonjour vous         allez                   bien";
        System.out.println("Mot avec des espaces: " + str);
        str = str.replaceAll("\\s", "");
        System.out.println("Mot sans des espaces: " + str); */

    }
}