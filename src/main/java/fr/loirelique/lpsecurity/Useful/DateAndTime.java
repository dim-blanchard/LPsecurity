package fr.loirelique.lpsecurity.Useful;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    


 public void name() {

        String years = "2022";
        String months = "04";
        String dayOfMonths = "12";
        String hours = "12";
        String minutes = "12";

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

            }else if(year % 4 == 0){
                 System.out.println("Année bisextile.");
                 bisextile = true;
                 
            }
        }

        if(month >= 1 && month <= 12)
        {
            System.out.println("Mois compris entre 1 et 12.");

            if(month == 1 | month == 3 |month == 5 |month == 7 |month == 8 |month == 10 |month == 12){
                System.out.println("Mois entre 1 et 31.");
                if (dayOfMonth >=1 && dayOfMonth <= 31) {
                    System.out.println("Jour entre 1 et 31.");
                    dateVerification = true;
                }else{
                    System.out.println("Jour entre 1 et 31 non respecter.");
                    dateVerification = false;
                }
            }

            if(month == 4 | month == 6 |month == 9 |month == 11){
                System.out.println("Mois entre 1 et 30.");
                if (dayOfMonth >=1 && dayOfMonth <= 30) {
                    System.out.println("Jour entre 1 et 30.");
                    dateVerification = true;
                }else{
                    System.out.println("Jour entre 1 et 30 non respecter.");
                    dateVerification = false;
                }
            }

            if(month == 2 && bisextile == false){
                System.out.println("Mois entre 1 et 28.");
                if (dayOfMonth >=1 && dayOfMonth <= 28) {
                    System.out.println("Jour entre 1 et 28.");
                    dateVerification = true;
                }else{
                    System.out.println("Jour entre 1 et 28 non respecter.");
                    dateVerification = false;
                }
            }

            if(month == 2 && bisextile == true){
                System.out.println("Mois entre 1 et 29.");
                if (dayOfMonth >=1 && dayOfMonth <= 29) {
                    System.out.println("Jour entre 1 et 29.");
                    dateVerification = true;
                }else{
                    System.out.println("Jour entre 1 et 29 non respecter.");
                    dateVerification = false;
                }
            }

        }
        else{
            System.out.println("Erreur sur le mois donné.");
            dateVerification = false;
        }


        if (dateVerification == true && timeVerification == true ) {
            System.out.println("On peut executer les requetes sql.");
        }else if(dateVerification == false && timeVerification == true){
            System.out.println("On a une érreur dans la date donné. YYYY/MM/DD -> "+year+"/"+month+"/"+dayOfMonth);
        }else if (dateVerification == true && timeVerification == false) {
            System.out.println("On a une érreur dans le l'heure donné. HH:MM -> "+hour+":"+minute);
        }else if (dateVerification == false && timeVerification == false) {
            System.out.println("On a une érreur dans la date et l'heure donné. YYYY/MM/DD HH:MM -> "+year+"/"+month+"/"+dayOfMonth +" "+hour+":"+minute);
        }



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

 } 

    
}
