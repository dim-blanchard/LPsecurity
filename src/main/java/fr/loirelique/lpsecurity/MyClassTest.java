package fr.loirelique.lpsecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;



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

    public static boolean testChaineNumber(String chaine) {
        boolean testNumber = false;
        for (int i = 0; i < chaine.length(); i++) {
            char chaineDeCaractere = chaine.charAt(i);
            int ascii = chaineDeCaractere;
            // System.out.println(ascii);
            if (ascii >= 48 && ascii <= 57) {
                // System.out.println("ok");
                testNumber = true;

            } else {
                // System.out.println("Not Ok");
                testNumber = false;
                break;
            }
        }
        return testNumber;
    }

    public static void main(String args[]) {

        LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
        System.out.println(dateTime);


/*         String years = "2022";
        String months = "06";
        String dayOfMonths = "23";
        String hours = "10";
        String minutes = "20";

        if (testChaineNumber(years) == false && testChaineNumber(months) == true
        && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
        && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans l'année.");
 
        }else if(testChaineNumber(years) == true && testChaineNumber(months) == false
        && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
        && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans le mois.");
 
        }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
        && testChaineNumber(dayOfMonths) == false && testChaineNumber(hours) == true
        && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans le jour.");
 
        }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
        && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == false
        && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans l'heure.");
 
        }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
        && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
        && testChaineNumber(minutes) == false) {
            System.out.println("Tu as fais une erreur de caractère dans les minutes.");
 
        }else if (testChaineNumber(years) == true && testChaineNumber(months) == true
            && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
                && testChaineNumber(minutes) == true) {

            int year = Integer.parseInt(years.replaceAll("\\s", ""));
            int month = Integer.parseInt(months.replaceAll("\\s", ""));
            int dayOfMonth = Integer.parseInt(dayOfMonths.replaceAll("\\s", ""));
            int hour = Integer.parseInt(hours.replaceAll("\\s", ""));
            int minute = Integer.parseInt(minutes.replaceAll("\\s", ""));

            boolean bisextile = false;
            boolean dateVerification = false;
            boolean timeVerification = false;

            if (year >= 2020 && year <= 2100) {
                System.out.println("Années comprise entre 2020 et 2100.");

                if (year % 4 > 0) {
                    System.out.println("Année non bisextile.");
                    bisextile = false;

                } else if (year % 4 == 0) {
                    System.out.println("Année bisextile.");
                    bisextile = true;

                }
            }

            if (month >= 1 && month <= 12) {
                System.out.println("Mois compris entre 1 et 12.");

                if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
                    System.out.println("Mois entre 1 et 31.");
                    if (dayOfMonth >= 1 && dayOfMonth <= 31) {
                        System.out.println("Jour entre 1 et 31.");
                        dateVerification = true;
                    } else {
                        System.out.println("Jour entre 1 et 31 non respecter.");
                        dateVerification = false;
                    }
                }

                if (month == 4 | month == 6 | month == 9 | month == 11) {
                    System.out.println("Mois entre 1 et 30.");
                    if (dayOfMonth >= 1 && dayOfMonth <= 30) {
                        System.out.println("Jour entre 1 et 30.");
                        dateVerification = true;
                    } else {
                        System.out.println("Jour entre 1 et 30 non respecter.");
                        dateVerification = false;
                    }
                }

                if (month == 2 && bisextile == false) {
                    System.out.println("Mois entre 1 et 28.");
                    if (dayOfMonth >= 1 && dayOfMonth <= 28) {
                        System.out.println("Jour entre 1 et 28.");
                        dateVerification = true;
                    } else {
                        System.out.println("Jour entre 1 et 28 non respecter.");
                        dateVerification = false;
                    }
                }

                if (month == 2 && bisextile == true) {
                    System.out.println("Mois entre 1 et 29.");
                    if (dayOfMonth >= 1 && dayOfMonth <= 29) {
                        System.out.println("Jour entre 1 et 29.");
                        dateVerification = true;
                    } else {
                        System.out.println("Jour entre 1 et 29 non respecter.");
                        dateVerification = false;
                    }
                }

            } else {
                System.out.println("Erreur sur le mois donné.");
                dateVerification = false;
            }

            if (hour >= 0 && hour <= 23) {
                timeVerification = true;
                System.out.println("Heure entre 0 et 23.");
                if (minute >=0 && minute <=59) {
                    timeVerification = true;
                    System.out.println("Minute entre 0 et 59.");
                } else {
                    timeVerification = false;
                    System.out.println("Minute entre 0 et 59 non respecter.");
                }
            }else{
                timeVerification = false;
                System.out.println("Heure entre 0 et 23 non respecter.");
            }




            if (dateVerification == true && timeVerification == true) {
                System.out.println("On peut executer les requetes sql.");
                LocalDateTime heurDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
                System.out.println(heurDateTime);
    
                ZonedDateTime dateTimeNow = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
    
                LocalDateTime dateTimeZone = dateTimeNow.toLocalDateTime();
                System.out.println(dateTimeZone);
    
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
                System.out.println("yy/MM/dd HH:mm-> " + dtf2.format(heurDateTime));
    
                DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
                System.out.println("yy/MM/dd HH:mm-> " + dtf3.format(dateTimeNow));
    
                if (dateTimeZone.isAfter(heurDateTime)) {
                    System.out.println("Date donner apres date de mtn donc joueur deban");
                } else {
                    System.out.println("Toujour bannie");
                }

            } else if (dateVerification == false && timeVerification == true) {
                System.out.println(
                        "On a une érreur dans la date donné. YYYY/MM/DD -> " + year + "/" + month + "/" + dayOfMonth);
            } else if (dateVerification == true && timeVerification == false) {
                System.out.println("On a une érreur dans le l'heure donné. HH:MM -> " + hour + ":" + minute);
            } else if (dateVerification == false && timeVerification == false) {
                System.out.println("On a une érreur dans la date et l'heure donné. YYYY/MM/DD HH:MM -> " + year + "/"
                        + month + "/" + dayOfMonth + " " + hour + ":" + minute);
            }

           
        } */

      

        /*
         * String years = "2022E";
         * String months = "04";
         * String dayOfMonths = "12";
         * String hours = "12";
         * String minutes = "12";
         * 
         * int year = Integer.parseInt(years.replaceAll("\\s", ""));
         * int month = Integer.parseInt(months.replaceAll("\\s", ""));
         * int dayOfMonth = Integer.parseInt(dayOfMonths.replaceAll("\\s", ""));
         * int hour = Integer.parseInt(hours.replaceAll("\\s", ""));
         * int minute = Integer.parseInt(minutes.replaceAll("\\s", ""));
         * 
         * boolean bisextile = false;
         * boolean dateVerification = false;
         * boolean timeVerification = true;
         * 
         * if (year >= 2020 && year <= 2100) {
         * System.out.println("Années comprise entre 2020 et 2100.");
         * 
         * if (year % 4 > 0) {
         * System.out.println("Année non bisextile.");
         * bisextile = false;
         * 
         * }else if(year % 4 == 0){
         * System.out.println("Année bisextile.");
         * bisextile = true;
         * 
         * }
         * }
         * 
         * if(month >= 1 && month <= 12)
         * {
         * System.out.println("Mois compris entre 1 et 12.");
         * 
         * if(month == 1 | month == 3 |month == 5 |month == 7 |month == 8 |month == 10
         * |month == 12){
         * System.out.println("Mois entre 1 et 31.");
         * if (dayOfMonth >=1 && dayOfMonth <= 31) {
         * System.out.println("Jour entre 1 et 31.");
         * dateVerification = true;
         * }else{
         * System.out.println("Jour entre 1 et 31 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if(month == 4 | month == 6 |month == 9 |month == 11){
         * System.out.println("Mois entre 1 et 30.");
         * if (dayOfMonth >=1 && dayOfMonth <= 30) {
         * System.out.println("Jour entre 1 et 30.");
         * dateVerification = true;
         * }else{
         * System.out.println("Jour entre 1 et 30 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if(month == 2 && bisextile == false){
         * System.out.println("Mois entre 1 et 28.");
         * if (dayOfMonth >=1 && dayOfMonth <= 28) {
         * System.out.println("Jour entre 1 et 28.");
         * dateVerification = true;
         * }else{
         * System.out.println("Jour entre 1 et 28 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if(month == 2 && bisextile == true){
         * System.out.println("Mois entre 1 et 29.");
         * if (dayOfMonth >=1 && dayOfMonth <= 29) {
         * System.out.println("Jour entre 1 et 29.");
         * dateVerification = true;
         * }else{
         * System.out.println("Jour entre 1 et 29 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * }
         * else{
         * System.out.println("Erreur sur le mois donné.");
         * dateVerification = false;
         * }
         * 
         * 
         * if (dateVerification == true && timeVerification == true ) {
         * System.out.println("On peut executer les requetes sql.");
         * }else if(dateVerification == false && timeVerification == true){
         * System.out.println("On a une érreur dans la date donné. YYYY/MM/DD -> "+year+
         * "/"+month+"/"+dayOfMonth);
         * }else if (dateVerification == true && timeVerification == false) {
         * System.out.println("On a une érreur dans le l'heure donné. HH:MM -> "+hour+
         * ":"+minute);
         * }else if (dateVerification == false && timeVerification == false) {
         * System.out.
         * println("On a une érreur dans la date et l'heure donné. YYYY/MM/DD HH:MM -> "
         * +year+"/"+month+"/"+dayOfMonth +" "+hour+":"+minute);
         * }
         * 
         * 
         * 
         * LocalDateTime heurDateTime = LocalDateTime.of(year, month, dayOfMonth, hour,
         * minute);
         * System.out.println(heurDateTime);
         * 
         * ZonedDateTime dateTimeNow = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
         * 
         * LocalDateTime dateTimeZone = dateTimeNow.toLocalDateTime();
         * System.out.println(dateTimeZone);
         * 
         * DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
         * System.out.println("yy/MM/dd HH:mm-> " + dtf2.format(heurDateTime));
         * 
         * DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
         * System.out.println("yy/MM/dd HH:mm-> " + dtf3.format(dateTimeNow));
         * 
         * if (dateTimeZone.isAfter(heurDateTime)) {
         * System.out.println("Date donner apres date de mtn donc joueur deban");
         * } else {
         * System.out.println("Toujour bannie");
         * }
         */

        // String str ="{\"temp_ban\": \"null\", \"motif_ban\": \"null\", \"temp_mute\":
        // \"null\", \"motif_kick\": \"null\", \"motif_mute\": \"null\", \"motif_warn\":
        // \"null\", \"motif_unban\": \"null\", \"motif_unmute\": \"null\",
        // \"motif_tempban\": \"null\", \"motif_tempmute\": \"null\"}";

        /*
         * HashMap<String, String> str = new HashMap<String, String>();
         * str.put("temp_ban", "null");
         * str.put("temp_mute", "null");
         * str.put("motif_ban", "null");
         * str.put("motif_tempban", "null");
         * str.put("motif_unban", "null");
         * 
         * str.put("motif_mute", "null");
         * str.put("motif_unmute", "null");
         * str.put("motif_tempmute", "null");
         * str.put("motif_kick", "null");
         * str.put("motif_warn", "null");
         */

        // System.out.println(str);
        /*
         * try {
         * JSONObject joa = new JSONObject(str);
         * System.out.println(joa);
         * 
         * } catch (Exception e) {
         * //TODO: handle exception
         * }
         */

        // JSONObject jObject = new JSONObject(str);
        // System.out.println(jObject);

        // {"temp_ban": "null", "motif_ban": "null", "temp_mute": "null", "motif_kick":
        // "null", "motif_mute": "null", "motif_warn": "null", "motif_unban": "null",
        // "motif_unmute": "null", "motif_tempban": "null", "motif_tempmute": "null"}

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