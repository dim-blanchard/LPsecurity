package fr.loirelique.lpsecurity.Useful;


public class DateAndTime {

    public boolean testChaineNumber(String chaine) {
        boolean testNumber = false;
        for (int i = 0; i < chaine.length(); i++) {
            char chaineDeCaractere = chaine.charAt(i);
            int ascii = chaineDeCaractere;
            if (ascii >= 48 && ascii <= 57) {
                testNumber = true;
            } else {
                testNumber = false;
                break;
            }
        }
        return testNumber;
    }

    public boolean testDateEtTime(String years, String months, String dayOfMonths, String hours, String minutes) {

        boolean testDateEtTime = false;

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
    }

}
