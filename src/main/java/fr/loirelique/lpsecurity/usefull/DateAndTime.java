package fr.loirelique.lpsecurity.usefull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateAndTime {

    private Calendar calendrier;
    public Calendar getCalendrier() {return calendrier;}
    public void setCalendrier(Calendar calendrier) {this.calendrier = calendrier;}
    
    private SimpleDateFormat simpleDateFormat ;
    public SimpleDateFormat getSimpleDateFormat() {return simpleDateFormat;}
    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {this.simpleDateFormat = simpleDateFormat;}

    private DateFormat dateFormat;
    public DateFormat getDateFormat() {return dateFormat;}
    public void setDateFormat(DateFormat dateFormat) {this.dateFormat = dateFormat;}

    private Date date;
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    private int donneTemps;
    public int getDonneTemps() {return donneTemps;}
    public void setDonneTemps(int donneTemps) {this.donneTemps = donneTemps;}

    private String typeTemps;
    public String getTypeTemps() {return typeTemps;}
    public void setTypeTemps(String typeTemps) {this.typeTemps = typeTemps;}

    
    public DateAndTime() {
    this.calendrier = Calendar.getInstance() ;
    this.simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH) ;
    this.dateFormat  = new SimpleDateFormat("yyyy/MM/dd HH:mm") ;
    this.date = getCalendrier().getTime() ;
    this.donneTemps = 0 ;
    this.typeTemps = "error" ;
    }

    public static String getDateFormatToString(Date date) {
        // Date retouner au joueur
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String dateGiveString = dateFormat.format(date);
        return dateGiveString;
    }
    public static Date setDateFromBddToCompare(String date) {
        // Date de la bdd vers la comparaison
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = cal.getTime();

        return date2;
    }
   
    public static String getDateToString(int donneTemps, String typeTemps) {
            Calendar dateOfTheDay = Calendar.getInstance();
            Date date = dateOfTheDay.getTime();
            if (typeTemps.equals("Jours")) {
                dateOfTheDay.add(Calendar.DATE, donneTemps);
                date = dateOfTheDay.getTime();
            }else if (typeTemps.equals("Mois")) {
                dateOfTheDay.add(Calendar.MONTH, donneTemps);
                date = dateOfTheDay.getTime();
            }else if (typeTemps.equals("Heures")) {
                dateOfTheDay.add(Calendar.HOUR, donneTemps);
                date = dateOfTheDay.getTime();
            }else if (typeTemps.equals("Minutes")) {
                dateOfTheDay.add(Calendar.MINUTE, donneTemps);
                date = dateOfTheDay.getTime();
            }else if (!typeTemps.equals("Jours") && !typeTemps.equals("Mois") && !typeTemps.equals("Heures") && !typeTemps.equals("Minutes")) {
                return "error";
            }
            return date.toString();        
    }


    private static ArrayList<String> listTypeTemps = new ArrayList<String>(); 
    private static ArrayList<String> listNumber = new ArrayList<String>(); 
    public static ArrayList<String> getListTypeTemps() {return listTypeTemps;}  
    public static ArrayList<String> getListNumber() {return listNumber;}
    public static void initializeList() {listTypeTemps.add("Jours");listTypeTemps.add("Mois"); listTypeTemps.add("Heures");listTypeTemps.add("Minutes");for (int i = 1 ; i <= 100; i++) {listNumber.add(Integer.toString(i));}}
}
