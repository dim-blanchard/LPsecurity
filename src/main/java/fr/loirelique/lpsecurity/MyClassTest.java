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
        /*
         * // start stopwatch
         * //for(int i =0 ; i < 10 ; i++){
         * long startTime = System.nanoTime();
         * // Here is the code to measure
         * getIpOfPlayerBeforeLogin("1","j1");
         * getIpOfPlayerBeforeLogin("1","j2");
         * getIpOfPlayerBeforeLogin("1","j3");
         * getIpOfPlayerBeforeLogin("1","j4");
         * getIpOfPlayerBeforeLogin("1","j5");
         * getIpOfPlayerBeforeLogin("1","j6");
         * 
         * System.out.println(listArrays.get("1"));
         * System.out.println(listArrays.get("1").size());
         * 
         * listArrays.get("1").remove("j3");
         * 
         * System.out.println(listArrays.get("1").size());
         * 
         * System.out.println(listArrays.get("1"));
         * // stop stopwatch
         * long endTime = System.nanoTime();
         * 
         * System.out.println("Test de vitesse : " + (endTime -
         * startTime)*Math.pow(10,-6) + " ms");
         */

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

        String str = "Bonjour vous         allez                   bien";
        System.out.println("Mot avec des espaces: " + str);
        str = str.replaceAll("\\s", "");
        System.out.println("Mot sans des espaces: " + str);

    }
}