package fr.loirelique.lpsecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.checkerframework.checker.units.qual.h;

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

    public static String sansAccent(String s) {

        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    public static void main(String args[]) {
        String years = "2022";
        int month = 04;
        int dayOfMonth = 16;
        int hour = 16;
        int minute = 30;
       

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Veuillez saisir une date(yyyy/MM/dd HH:mm:ss):");
            years = sc.nextLine();
            month = Integer.parseInt(sc.nextLine());
            dayOfMonth = Integer.parseInt(sc.nextLine());
            hour = Integer.parseInt(sc.nextLine());
            minute = Integer.parseInt(sc.nextLine());
            

            int year = Integer.parseInt(years.replaceAll("\\s", ""));

            LocalDateTime heurDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            System.out.println(heurDateTime);
    

            ZonedDateTime dateTimeNow= ZonedDateTime.now(ZoneId.of("Europe/Paris"));
            // ZonedDateTime.now(ZoneId.of("Europe/Paris"))

            LocalDateTime dateTimeZone= dateTimeNow.toLocalDateTime();
            System.out.println(dateTimeZone);
    
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
            System.out.println("yy/MM/dd HH:mm-> " + dtf2.format(heurDateTime));
    
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
            System.out.println("yy/MM/dd HH:mm-> " + dtf3.format(dateTimeNow));
        
            if(dateTimeZone.isAfter(heurDateTime)){
                System.out.println("Date donner apres date de mtn donc joueur deban");
            }else{
                System.out.println("Toujour bannie");
            }

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("La saisie de la date ou l'heure est incorrecte.");
        }




        /*
         * SELECT historique_sanctions->'$.motifs_sanctions.ban' FROM pf8kr9g9players ;
         */
        /*
         * UPDATE pf8kr9g9players SET historique_sanctions =
         * JSON_SET(historique_sanctions, '$.motifs_sanction.ban',2);
         */

        /*
         * {"motifs_sanction": {"ban": "","tempban": "","unban": "","mute":
         * "","tempmute": "","unmute": "","warn":"","kick": ""},"temp_sanction": {"ban":
         * "","mute": ""}}
         */

        /*
         * INSERT INTO pf8kr9g9players (historique_sanctions) VALUES
         * ('{"motifs_sanction": {
         * "ban": "",
         * "tempban": "",
         * "unban": "",
         * "mute": "",
         * "tempmute": "",
         * "unmute": "",
         * "warn":"",
         * "kick": ""
         * },
         * 
         * "temp_sanction": {
         * "ban": "",
         * "mute": ""
         * },}');
         */

        /*
         * '{"motif_ban": "null","motif_tempban": "null","motif_unban":
         * "null","motif_mute": "null","motif_tempmute": "null","motif_unmute":
         * "null","motif_warn": "null","motif_kick": "null","temp_ban":
         * "null","temp_mute": "null"}'
         */

        /*
         * String[] argss = new String[3];
         * 
         * argss[0]="J'ai";
         * argss[1]="peut être fait";
         * argss[2]="une érreur.";
         * 
         * 
         * // Chaîne avec accent
         * String test="Marché public, école, j'ai programmé, chaîne de caractère";
         * 
         * // Effacer les accents de la chaîne de caractère 'test'
         * String str_sans_accent=sansAccent(test);
         * 
         * //Afficher le résultat
         * System.out.println(str_sans_accent);
         * 
         * 
         * StringBuilder builder = new StringBuilder();
         * for (int i = 0; i < argss.length; i++) {
         * String ar = sansAccent(argss[i]);
         * builder.append(ar).append(" ");
         * }
         * String msg = builder.toString();
         * System.out.println(msg);
         */

        /*
         * // start stopwatch
         * //for(int i =0 ; i < 10 ; i++){
         * long startTime = System.nanoTime();
         * // Here is the code to measure
         * getIpOfPlayerBeforeLogin("1","qsfseeeeeqzgzergzgrgrggggggzrgzrgzrgzergzrg");
         * getIpOfPlayerBeforeLogin("1","zgrzrgzgrzgggggrgrgrgrgrgqgrgqzrgtergqegzgg");
         * getIpOfPlayerBeforeLogin("1","zrgzzgqqqqegeqggbzrbqwiukkkkkkkkkkkukgukget");
         * getIpOfPlayerBeforeLogin("1","gergesbserbgsh'rjtgshey'j( y,cjc,cjc;j;,,,,");
         * getIpOfPlayerBeforeLogin("1","jgdergwdrgwdrgzer gb'tzbesgnjetgdxrnhnxhtnn");
         * getIpOfPlayerBeforeLogin("1","xnyte(kxtj,kxxynjhxhbrxbgxrxnjrwrthfhjytjyj");
         * 
         * System.out.println(listArrays.get("1"));
         * System.out.println(listArrays.get("1").size());
         * 
         * listArrays.get("1").remove("zrgzzgqqqqegeqggbzrbqwiukkkkkkkkkkkukgukget");
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

        /*
         * String str = "Bonjour vous         allez                   bien";
         * System.out.println("Mot avec des espaces: " + str);
         * str = str.replaceAll("\\s", "");
         * System.out.println("Mot sans des espaces: " + str);
         */

    }
}