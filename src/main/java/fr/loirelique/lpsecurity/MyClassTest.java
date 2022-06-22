package fr.loirelique.lpsecurity;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MyClassTest {

    /*
     * public static boolean testChaineNumber(String chaine) {
     * boolean testNumber = false;
     * for (int i = 0; i < chaine.length(); i++) {
     * char chaineDeCaractere = chaine.charAt(i);
     * int ascii = chaineDeCaractere;
     * if (ascii >= 48 && ascii <= 57) {
     * testNumber = true;
     * } else {
     * testNumber = false;
     * break;
     * }
     * }
     * return testNumber;
     * }
     * private static HashMap<String, HashMap<String,String>> listSupport = new
     * HashMap<String, HashMap<String,String>>();
     * 
     * 
     * public static void creatSupport(String nom , String String) {
     * 
     * if (listSupport.get(nom)==null) {
     * listSupport.put(nom, new HashMap<String,String>());
     * listSupport.get(nom).put(String, String);
     * }else{
     * System.out.println("support deja exisant");
     * // String.sendMessage("Support deja exsistant choisir un autre nom.");
     * }
     * }
     * 
     * public static String getlistSupport() {
     * StringBuilder builder = new StringBuilder();
     * Object lisObject[] = listSupport.keySet().toArray();
     * String list ="";
     * for (int i = 0; i <lisObject.length ; i++) {
     * String ar = "[Support]["+i+"]:"+lisObject[i].toString().replace(" ' ",
     * " \' ");
     * builder.append(ar).append(" \n");
     * list = builder.toString();
     * }
     * return list;
     * }
     * 
     * public static void joinSupport(String nom, String String) {
     * if (listSupport.get(nom)!=null) {
     * if (listSupport.get(nom).get(String)== null) {
     * listSupport.get(nom).put(String, String);
     * }else{
     * //player.sendMessage("Tu fais deja partie du support.");
     * }
     * }else{
     * //String.sendMessage("le nom de support n'existe pas.");
     * }
     * }
     * 
     * public static void removeSupport(String nom , String String) {
     * if(listSupport.get(nom)!= null){
     * listSupport.remove(nom);
     * }else{
     * //String.sendMessage("le nom de support n'existe pas.");
     * }
     * 
     * 
     */
    // private static String chemainFolder =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity";
    // private static String chemainFiles =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity/historique_sanctions/";
    /*
     * private static String chemainFolder =
     * "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity";
     * private static String chemainFiles =
     * "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity\\historique_sanctions\\";
     * 
     * public static void createFiles(String ban, String mute,String warn,String
     * temp_ban, String motif_ban,String temp_mute,String motif_kick,String
     * motif_mute,String motif_warn,String motif_unban,String motif_unmute,String
     * motif_tempban,String motif_tempmute,File file) {
     * 
     * JSONObject historique_sanctions = new JSONObject();
     * historique_sanctions.put("ban", ban);
     * historique_sanctions.put("mute",mute);
     * historique_sanctions.put("warn", warn);
     * historique_sanctions.put("temp_ban", temp_ban);
     * historique_sanctions.put("motif_ban", motif_ban);
     * historique_sanctions.put("temp_mute", temp_mute);
     * historique_sanctions.put("motif_kick", motif_kick);
     * historique_sanctions.put("motif_mute", motif_mute);
     * historique_sanctions.put("motif_warn", motif_warn);
     * historique_sanctions.put("motif_unban", motif_unban);
     * historique_sanctions.put("motif_unmute", motif_unmute);
     * historique_sanctions.put("motif_tempban", motif_tempban);
     * historique_sanctions.put("motif_tempmute", motif_tempmute);
     * /*
     * JSONObject histo = new JSONObject();
     * histo.put("historique_sanctions", historique_sanctions);
     * 
     * try (FileWriter fileW = new FileWriter(file)) {
     * fileW.write(historique_sanctions.toJSONString());
     * fileW.flush();
     * 
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * 
     * }
     * 
     * public static void updateFiles(String ban, String mute,String warn,String
     * temp_ban, String motif_ban,String temp_mute,String motif_kick,String
     * motif_mute,String motif_warn,String motif_unban,String motif_unmute,String
     * motif_tempban,String motif_tempmute,File file) {
     * 
     * JSONObject historique_sanctions = new JSONObject();
     * historique_sanctions.put("ban", ban);
     * historique_sanctions.put("mute",mute);
     * historique_sanctions.put("warn", warn);
     * historique_sanctions.put("temp_ban", temp_ban);
     * historique_sanctions.put("motif_ban", motif_ban);
     * historique_sanctions.put("temp_mute", temp_mute);
     * historique_sanctions.put("motif_kick", motif_kick);
     * historique_sanctions.put("motif_mute", motif_mute);
     * historique_sanctions.put("motif_warn", motif_warn);
     * historique_sanctions.put("motif_unban", motif_unban);
     * historique_sanctions.put("motif_unmute", motif_unmute);
     * historique_sanctions.put("motif_tempban", motif_tempban);
     * historique_sanctions.put("motif_tempmute", motif_tempmute);
     * 
     * 
     * try (FileWriter fileW = new FileWriter(file)) {
     * fileW.write(historique_sanctions.toJSONString());
     * fileW.flush();
     * 
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * 
     * }
     * 
     * public static String readFiles(String key,String namefile, String chemain) {
     * JSONParser parser = new JSONParser();
     * 
     * Object obj;
     * try {
     * obj = parser.parse(new FileReader(chemain+namefile));
     * JSONObject jsonObject = (JSONObject) obj;
     * String data = (String) jsonObject.get(key);
     * return data;
     * 
     * } catch (FileNotFoundException e) {
     * // 
     * e.printStackTrace();
     * return "error";
     * } catch (IOException e) {
     * //
     * e.printStackTrace();
     * return "error";
     * } catch (org.json.simple.parser.ParseException e) {
     * // 
     * e.printStackTrace();
     * return "error";
     * }
     * }
     * 
     * public static void creatFolderAndVerifExists(String nameFolder, String
     * chemain) {
     * 
     * //Creation du dossier
     * File test = new File(
     * chemain, nameFolder);
     * if (test.exists() == true) {
     * System.out.println("Dossier Exsist...");
     * } else {
     * System.out.println("Dossier abs création..");
     * test.mkdir();
     * }
     * ////////////////////
     * }
     * 
     * private static void creatFilesAndVerifExists(String chemain , String
     * nameFile) {
     * File test1 = new File(chemain, nameFile);
     * if (test1.exists() == true) {
     * System.out.println("Fichier exsite...");
     * } else {
     * System.out.println("Fichier n'exsite pas...Fichier en création...");
     * createFiles("ban", "mute", "warn", "temp_ban", "motif_ban", "temp_mute",
     * "motif_kick", "motif_mute", "motif_warn", "motif_unban", "motif_unmute",
     * "motif_tempban", "motif_tempmute", test1);
     * 
     * }
     * }
     */
    /*     private static String chemainFolder = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity";
    private static String chemainFiles = "C:\\Users\\Master\\Desktop\\Erizia-spigot-1.8.8\\Spigot-1.8.8-serveur\\plugins\\LPsecurity\\dataPlayerUsefull\\";
    private static String nameFile = "players.json";
    private static String nameFolder = "dataPlayerUsefull";
    String ban;
    String mute;
    String warn;
    String temp_ban;
    String motif_ban;
    String temp_mute;
    String motif_kick;
    String motif_mute;
    String motif_warn;
    String motif_unban;

    String motif_unmute;
    String motif_tempban;
    String motif_tempmute;
    String isOnline;
    String isLogin;
    // private static String chemainFiles =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity/dataPlayerUsefull/";
    // private static String chemainFolder =
    // "/home/padymaster/Documents/VisualStudioCode/Spigot-serveur-1.8.8/plugins/LPsecurity";

    public static void creatFolderAndVerifExists(String nameFolder, String chemain) {
        // Creation du dossier
        File folder = new File(
                chemain, nameFolder);
        if (folder.exists() == true) {
            System.out.println("Dossier Exsist...");
        } else {
            System.out.println("Dossier abs création..");
            folder.mkdir();
        }
        ////////////////////
    }

    public static void createFilesAndIfExisteUpdate(String ban, String mute, String warn, String temp_ban,
            String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, String isOnline, String isLogin,
            String chemain, String nameFile) {
        final File file = new File(chemain, nameFile);
        if (file.exists() == true) {
            System.out.println("Le fichier existe\nLe fichier est mit à jour");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("création fichier");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String readFiles(String key, String namefile, String chemain) {
        JSONParser parser = new JSONParser();

        Object obj;
        try {
            obj = parser.parse(new FileReader(chemain + namefile));
            JSONObject jsonObject = (JSONObject) obj;
            String data = (String) jsonObject.get(key);
            return data;

        } catch (FileNotFoundException e) {
            //
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            // 
            e.printStackTrace();
            return "error";
        } catch (org.json.simple.parser.ParseException e) {
            // 
            e.printStackTrace();
            return "error";
        }
    }

    public static void updateFileAndIsExiste(String ban, String mute, String warn, String temp_ban, String motif_ban,
            String temp_mute, String motif_kick, String motif_mute, String motif_warn, String motif_unban,
            String motif_unmute, String motif_tempban, String motif_tempmute, String isOnline, String isLogin,
            String chemain, String nameFile) {
        final File file = new File(chemain, nameFile);
        if (file.exists() == true) {
            System.out.println("Le fichier existe\nLe fichier est mit à jour");
            JSONObject dataPlayerUsefull = new JSONObject();
            dataPlayerUsefull.put("ban", ban);
            dataPlayerUsefull.put("mute", mute);
            dataPlayerUsefull.put("warn", warn);
            dataPlayerUsefull.put("temp_ban", temp_ban);
            dataPlayerUsefull.put("motif_ban", motif_ban);
            dataPlayerUsefull.put("temp_mute", temp_mute);
            dataPlayerUsefull.put("motif_kick", motif_kick);
            dataPlayerUsefull.put("motif_mute", motif_mute);
            dataPlayerUsefull.put("motif_warn", motif_warn);
            dataPlayerUsefull.put("motif_unban", motif_unban);
            dataPlayerUsefull.put("motif_unmute", motif_unmute);
            dataPlayerUsefull.put("motif_tempban", motif_tempban);
            dataPlayerUsefull.put("motif_tempmute", motif_tempmute);
            dataPlayerUsefull.put("isOnline", isOnline);
            dataPlayerUsefull.put("isLogin", isLogin);

            try (FileWriter fileW = new FileWriter(file)) {
                fileW.write(dataPlayerUsefull.toJSONString());
                fileW.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Le fichier n'existe pas.");
        }
    } */


  /*   public boolean testDateEtTime(String years, String months, String dayOfMonths, String hours, String minutes) {

        boolean testDateEtTime = false;

        if (testChaineNumber(years) == false && testChaineNumber(months) == true
                && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
                && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans l'année.");

        } else if (testChaineNumber(years) == true && testChaineNumber(months) == false
                && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
                && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans le mois.");

        } else if (testChaineNumber(years) == true && testChaineNumber(months) == true
                && testChaineNumber(dayOfMonths) == false && testChaineNumber(hours) == true
                && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans le jour.");

        } else if (testChaineNumber(years) == true && testChaineNumber(months) == true
                && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == false
                && testChaineNumber(minutes) == true) {
            System.out.println("Tu as fais une erreur de caractère dans l'heure.");

        } else if (testChaineNumber(years) == true && testChaineNumber(months) == true
                && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
                && testChaineNumber(minutes) == false) {
            System.out.println("Tu as fais une erreur de caractère dans les minutes.");

        } else if (testChaineNumber(years) == true && testChaineNumber(months) == true
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
                if (minute >= 0 && minute <= 59) {
                    timeVerification = true;
                    System.out.println("Minute entre 0 et 59.");
                } else {
                    timeVerification = false;
                    System.out.println("Minute entre 0 et 59 non respecter.");
                }
            } else {
                timeVerification = false;
                System.out.println("Heure entre 0 et 23 non respecter.");
            }

            if (dateVerification == true && timeVerification == true) {
                System.out.println("On peut executer les requetes sql.");
                testDateEtTime = true;

            } else if (dateVerification == false && timeVerification == true) {
                testDateEtTime = false;
                System.out.println(
                        "On a une érreur dans la date donné. YYYY/MM/DD -> " + year + "/" + month + "/" + dayOfMonth);
            } else if (dateVerification == true && timeVerification == false) {
                testDateEtTime = false;
                System.out.println("On a une érreur dans le l'heure donné. HH:MM -> " + hour + ":" + minute);
            } else if (dateVerification == false && timeVerification == false) {
                testDateEtTime = false;
                System.out.println("On a une érreur dans la date et l'heure donné. YYYY/MM/DD HH:MM -> " + year + "/"
                        + month + "/" + dayOfMonth + " " + hour + ":" + minute);
            }

        }

        return testDateEtTime;
    } */

    public static void main(String args[]) {

/*         String name ="Dimitri";
        String username="LoiRelique";

        ArrayList<Object> listObject = new ArrayList<Object>();

        Tclass test = new Tclass("Julien", "JulianoXV");

        listObject.add(test);
        listObject.add(new Tclass(name, username));
    
        Tclass test2 = (Tclass) listObject.get(1);
        System.out.println(test2.getName());

 */









 /*        String message = "/register dhfdhdthd";

        String []testmessage = message.split(" ");
        System.out.println(testmessage[0]); */
/* 
    
        final File folder = new File("C:/Users/Master/Desktop/Erizia-spigot-1.8.8/Spigot-1.8.8-serveur/plugins/LPsecurity", "DataPlayer");
        try{
          setfalseIsOnline(folder);
        }catch(IOException e){
          e.printStackTrace();
        }

 */

   /*      HashMap<String, String> myMap = new HashMap<String, String>();


        String requette = "{\"ban\": \"12\", \"mute\":\"0\", \"warn\": \"2\", \"temp_ban\": \"null\", \"motif_ban\": \"null\", \"temp_mute\": \"null\", \"motif_kick\": \"null\", \"motif_mute\": \"null\", \"motif_warn\": \"Advertissement niveau 2\", \"motif_unban\": \"null\", \"motif_unmute\": \"null\", \"motif_tempban\": \"null\", \"motif_tempmute\": \"null\"}";
        System.out.println(requette);
        requette = requette.replaceAll(" ", "");
        requette = requette.substring(1, (requette.length()-1));
        requette = requette.replaceAll("\"", "");
        System.out.println(requette);
        String[] pairs = requette.split(",");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            myMap.put(keyValue[0], keyValue[1]);

            System.out.println(keyValue[0] +""+keyValue[1] );

        }
        System.out.println(myMap.get("ban"));

        System.out.println(System.getProperty("os.name"));

        List<String> ip = new ArrayList<String>();
            Boolean testip = false;
            ip.add("azert");
            for(int i=0;i<ip.size();i++){
                System.out.println(ip.get(i));
                String getIp = ip.get(i);
                if (getIp==("azert")) {
                    testip=true;
                    System.out.println(testip);               
                }else{
                    System.out.println(testip);
                }
            }
            System.out.println(testip);
 */
    
        
    /*
     * public static void main(String args[]) {
     * 
     * // if le fichier du joueur existe on le cree sinon non
     * creatFolderAndVerifExists(nameFolder, chemainFolder);
     * createFilesAndIfExisteUpdate("ban", "mute", "warn", "temp_ban", "motif_ban",
     * "temp_mute", "motif_kick",
     * "motif_mute",
     * "motif_warn", "motif_unban", "motif_unmute", "motif_tempban",
     * "motif_tempmute", "1", "1", chemainFiles,
     * nameFile);
     * System.out.println("\n[--Lecture du fichier--]");
     * String ban = readFiles("ban", nameFile, chemainFiles);
     * String temp_mute = readFiles("temp_mute", nameFile, chemainFiles);
     * 
     * System.out.println(ban);
     * System.out.println(temp_mute);
     * 
     * updateFileAndIsExiste("test", "mute", "warn", "temp_ban", "motif_ban",
     * "temp_mute", "motif_kick", "motif_mute",
     * "motif_warn", "motif_unban", "motif_unmute", "motif_tempban",
     * "motif_tempmute", "1", "1", chemainFiles,
     * nameFile);
     * String ban2 = readFiles("ban", "players.json", chemainFiles);
     * String temp_mute2 = readFiles("temp_mute", "players.json", chemainFiles);
     * 
     * System.out.println(ban2);
     * System.out.println(temp_mute2);
     * 
     * }
     */


        /*
         * //if le fichier du joueur existe on le cree sinon non
         * String nameFile = "players.json";
         * String nameFolder = "historique_sanctions";
         * creatFolderAndVerifExists(nameFolder, chemainFolder);
         * creatFilesAndVerifExists(chemainFiles, nameFile);
         * 
         * System.out.println("\n[--Lecture du fichier--]");
         * String ban = readFiles("ban", "players.json",chemainFiles);
         * String temp_mute = readFiles("temp_mute", "players.json",chemainFiles);
         * 
         * System.out.println(ban);
         * System.out.println(temp_mute);
         */

        /*
         * creatSupport("Atest", "joueur1");
         * 
         * creatSupport("Btest", "joueur1");
         * 
         * creatSupport("Ctest", "joueur1");
         * 
         * joinSupport("Atest", "j");
         * 
         * Object lisObject[] = listSupport.keySet().toArray();
         * String list ="";
         * for (int i = 0; i <lisObject.length ; i++) {
         * list=lisObject[i].toString();
         * if (listSupport.get(list).get("j2") !=null) {
         * System.out.println("Joueur dans une list");
         * }else{
         * System.out.println("Pas de joueur dans list");
         * }
         * }
         */

        /*
         * System.out.println(listSupport.keySet());
         * Object test[] = listSupport.keySet().toArray();
         * 
         * System.out.println(test.length);
         * for (int i = 0; i <test.length ; i++) {
         * System.out.println(test[i]);
         * }
         */

        /*
         * int jours_donne = (-10);
         * String saisieClavier2 = "j";
         * 
         * 
         * Calendar aujourdhui = Calendar.getInstance();
         * Calendar aujourdhui2= Calendar.getInstance();
         * Date datefinal = aujourdhui.getTime();
         * Date dateauj = aujourdhui2.getTime();
         * 
         * if (saisieClavier2.equals("j")) {
         * System.out.println("On est en jours.");
         * aujourdhui.add(Calendar.DATE,130);
         * System.out.println(aujourdhui.getTime());
         * datefinal = aujourdhui.getTime();
         * }
         * 
         * if (saisieClavier2.equals("m")) {
         * System.out.println("On est en mois.");
         * aujourdhui.add(Calendar.MONTH,2);
         * System.out.println(aujourdhui.getTime());
         * datefinal = aujourdhui.getTime();
         * }
         * if (saisieClavier2.equals("h")) {
         * System.out.println("On est en heure.");
         * aujourdhui.add(Calendar.HOUR,24);
         * System.out.println(aujourdhui.getTime());
         * datefinal = aujourdhui.getTime();
         * }
         * if (saisieClavier2.equals("min")) {
         * System.out.println("On est en minutes.");
         * aujourdhui.add(Calendar.MINUTE,10);
         * System.out.println(aujourdhui.getTime());
         * datefinal = aujourdhui.getTime();
         * }
         * 
         * if (!saisieClavier2.equals("j") && !saisieClavier2.equals("m") &&
         * !saisieClavier2.equals("h") &&!saisieClavier2.equals("min")) {
         * System.out.println("Pas de correspondance");
         * }
         * 
         * 
         * if (datefinal.after(dateauj)) {
         * System.out.println("Toujour bannie");
         * }else{
         * System.out.println("plus bannie");
         * }
         * /* DateTimeFormatter formatter =
         * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
         * String parsetest = datefinal.format(formatter);
         * System.out.println(parsetest);
         * 
         * 
         * LocalDateTime dateTime = LocalDateTime.parse(parsetest, formatter);
         * 
         * System.out.println(dateTime);
         */

        /*
         * //Date retouner au joueur
         * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
         * String dateGiveString = dateFormat.format(datefinal);
         * System.out.println("String: "+dateGiveString);
         * 
         * 
         * //Date dans la bdd
         * String parseCalendar = datefinal.toString();
         * System.out.println("String: "+parseCalendar);
         * 
         * //Date de la bdd vers la comparaison
         * Calendar cal = Calendar.getInstance();
         * SimpleDateFormat sdf = new
         * SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
         * try {
         * cal.setTime(sdf.parse(parseCalendar));
         * } catch (ParseException e) {
         * // 
         * e.printStackTrace();
         * }
         * System.out.println("Object: "+cal.getTime());
         */

        /*
         * setNewPlayer("test");
         * System.out.println(getNumberTentativeOfPlayer("test"));
         * 
         * //1 test
         * incrementNumberTentativeOfPlayer("test");
         * //2 test
         * incrementNumberTentativeOfPlayer("test");
         * //3 test
         * incrementNumberTentativeOfPlayer("test");
         * System.out.println(getNumberTentativeOfPlayer("test"));
         * 
         * if (getNumberTentativeOfPlayer("test") >=3) {
         * System.out.println("KICK 3 TENTATIVE");
         * }
         * 
         * 
         * //quit
         * setRemovePlayer("test");
         * System.out.println(getNumberTentativeOfPlayer("test"));
         */

        /*
         * LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
         * System.out.println(dateTime);
         * 
         * 
         * int i = 0 ;
         * System.out.println(i);
         */

        /*
         * String years = "2022";
         * String months = "06";
         * String dayOfMonths = "23";
         * String hours = "10";
         * String minutes = "20";
         * 
         * if (testChaineNumber(years) == false && testChaineNumber(months) == true
         * && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
         * && testChaineNumber(minutes) == true) {
         * System.out.println("Tu as fais une erreur de caractère dans l'année.");
         * 
         * }else if(testChaineNumber(years) == true && testChaineNumber(months) == false
         * && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
         * && testChaineNumber(minutes) == true) {
         * System.out.println("Tu as fais une erreur de caractère dans le mois.");
         * 
         * }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
         * && testChaineNumber(dayOfMonths) == false && testChaineNumber(hours) == true
         * && testChaineNumber(minutes) == true) {
         * System.out.println("Tu as fais une erreur de caractère dans le jour.");
         * 
         * }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
         * && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == false
         * && testChaineNumber(minutes) == true) {
         * System.out.println("Tu as fais une erreur de caractère dans l'heure.");
         * 
         * }else if(testChaineNumber(years) == true && testChaineNumber(months) == true
         * && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
         * && testChaineNumber(minutes) == false) {
         * System.out.println("Tu as fais une erreur de caractère dans les minutes.");
         * 
         * }else if (testChaineNumber(years) == true && testChaineNumber(months) == true
         * && testChaineNumber(dayOfMonths) == true && testChaineNumber(hours) == true
         * && testChaineNumber(minutes) == true) {
         * 
         * int year = Integer.parseInt(years.replaceAll("\\s", ""));
         * int month = Integer.parseInt(months.replaceAll("\\s", ""));
         * int dayOfMonth = Integer.parseInt(dayOfMonths.replaceAll("\\s", ""));
         * int hour = Integer.parseInt(hours.replaceAll("\\s", ""));
         * int minute = Integer.parseInt(minutes.replaceAll("\\s", ""));
         * 
         * boolean bisextile = false;
         * boolean dateVerification = false;
         * boolean timeVerification = false;
         * 
         * if (year >= 2020 && year <= 2100) {
         * System.out.println("Années comprise entre 2020 et 2100.");
         * 
         * if (year % 4 > 0) {
         * System.out.println("Année non bisextile.");
         * bisextile = false;
         * 
         * } else if (year % 4 == 0) {
         * System.out.println("Année bisextile.");
         * bisextile = true;
         * 
         * }
         * }
         * 
         * if (month >= 1 && month <= 12) {
         * System.out.println("Mois compris entre 1 et 12.");
         * 
         * if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month ==
         * 10 | month == 12) {
         * System.out.println("Mois entre 1 et 31.");
         * if (dayOfMonth >= 1 && dayOfMonth <= 31) {
         * System.out.println("Jour entre 1 et 31.");
         * dateVerification = true;
         * } else {
         * System.out.println("Jour entre 1 et 31 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if (month == 4 | month == 6 | month == 9 | month == 11) {
         * System.out.println("Mois entre 1 et 30.");
         * if (dayOfMonth >= 1 && dayOfMonth <= 30) {
         * System.out.println("Jour entre 1 et 30.");
         * dateVerification = true;
         * } else {
         * System.out.println("Jour entre 1 et 30 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if (month == 2 && bisextile == false) {
         * System.out.println("Mois entre 1 et 28.");
         * if (dayOfMonth >= 1 && dayOfMonth <= 28) {
         * System.out.println("Jour entre 1 et 28.");
         * dateVerification = true;
         * } else {
         * System.out.println("Jour entre 1 et 28 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * if (month == 2 && bisextile == true) {
         * System.out.println("Mois entre 1 et 29.");
         * if (dayOfMonth >= 1 && dayOfMonth <= 29) {
         * System.out.println("Jour entre 1 et 29.");
         * dateVerification = true;
         * } else {
         * System.out.println("Jour entre 1 et 29 non respecter.");
         * dateVerification = false;
         * }
         * }
         * 
         * } else {
         * System.out.println("Erreur sur le mois donné.");
         * dateVerification = false;
         * }
         * 
         * if (hour >= 0 && hour <= 23) {
         * timeVerification = true;
         * System.out.println("Heure entre 0 et 23.");
         * if (minute >=0 && minute <=59) {
         * timeVerification = true;
         * System.out.println("Minute entre 0 et 59.");
         * } else {
         * timeVerification = false;
         * System.out.println("Minute entre 0 et 59 non respecter.");
         * }
         * }else{
         * timeVerification = false;
         * System.out.println("Heure entre 0 et 23 non respecter.");
         * }
         * 
         * 
         * 
         * 
         * if (dateVerification == true && timeVerification == true) {
         * System.out.println("On peut executer les requetes sql.");
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
         * 
         * } else if (dateVerification == false && timeVerification == true) {
         * System.out.println(
         * "On a une érreur dans la date donné. YYYY/MM/DD -> " + year + "/" + month +
         * "/" + dayOfMonth);
         * } else if (dateVerification == true && timeVerification == false) {
         * System.out.println("On a une érreur dans le l'heure donné. HH:MM -> " + hour
         * + ":" + minute);
         * } else if (dateVerification == false && timeVerification == false) {
         * System.out.
         * println("On a une érreur dans la date et l'heure donné. YYYY/MM/DD HH:MM -> "
         * + year + "/"
         * + month + "/" + dayOfMonth + " " + hour + ":" + minute);
         * }
         * 
         * 
         * }
         */

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
         * //
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

        /*
         * private static HashMap<String, Integer> wrongLoginPasswordTentative = new
         * HashMap<String, Integer>();
         * 
         * public static HashMap<String, ArrayList<String>> listArrays = new
         * HashMap<String, ArrayList<String>>();
         * 
         * public static void getIpOfPlayerBeforeLogin(String ip, String name) {
         * if (listArrays.get(ip) != null) {
         * (listArrays.get(ip)).add(name);
         * 
         * } else {
         * listArrays.put(ip, new ArrayList<String>());
         * (listArrays.get(ip)).add(name);
         * }
         * }
         * 
         * public static String sansAccent(String s) {
         * 
         * String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
         * Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
         * return pattern.matcher(strTemp).replaceAll("");
         * }
         * 
         * 
         * public static void setNewPlayer(String uuid) {
         * wrongLoginPasswordTentative.put(uuid, 0);
         * }
         * 
         * public static void setRemovePlayer(String uuid) {
         * wrongLoginPasswordTentative.remove(uuid);
         * }
         * 
         * public static int getNumberTentativeOfPlayer(String uuid) {
         * int numberTentative = wrongLoginPasswordTentative.get(uuid);
         * return numberTentative;
         * }
         * 
         * public static void incrementNumberTentativeOfPlayer(String uuid){
         * int numberTentative = wrongLoginPasswordTentative.get(uuid);
         * numberTentative++;
         * wrongLoginPasswordTentative.put(uuid,numberTentative);
         * }
         * 
         * public static boolean testChaineNumber(String chaine) {
         * boolean testNumber = false;
         * for (int i = 0; i < chaine.length(); i++) {
         * char chaineDeCaractere = chaine.charAt(i);
         * int ascii = chaineDeCaractere;
         * // System.out.println(ascii);
         * if (ascii >= 48 && ascii <= 57) {
         * // System.out.println("ok");
         * testNumber = true;
         * 
         * } else {
         * // System.out.println("Not Ok");
         * testNumber = false;
         * break;
         * }
         * }
         * return testNumber;
         * }
         */

    }
}