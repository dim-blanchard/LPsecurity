package fr.loirelique.lpsecurity.Useful.List;

import java.util.HashMap;

public class ListWarningDegresAndMotifs {
    private static HashMap<String, String> warningMotifs = new HashMap<String,String>();
    private static HashMap<String, Integer> warningDegres = new HashMap<String,Integer>();
    private static int warnDegresMax ;

    public static void initializeList() {
        warnDegresMax = 10;
        String motif_warn_1 = "Advertissement niveau 1";
        int Degres_warn_1 = 1;
        String warn_lvl_1 = "motif1";
        String motif_warn_2 = "Advertissement niveau 2";
        int Degres_warn_2 = 2;
        String warn_lvl_2 = "motif2";
        int Degres_warn_3 = 3;
        String motif_warn_3 = "Advertissement niveau 3";
        String warn_lvl_3= "motif3";
        warningMotifs.put(warn_lvl_1,motif_warn_1);
        warningMotifs.put(warn_lvl_2,motif_warn_2);
        warningMotifs.put(warn_lvl_3,motif_warn_3);
       
        warningDegres.put(warn_lvl_1, Degres_warn_1);    
        warningDegres.put(warn_lvl_2, Degres_warn_2);
        warningDegres.put(warn_lvl_3,Degres_warn_3);
    
    }

    public static String getMotifs(String motifs ) {
        String motif = warningMotifs.get(motifs);
    return motif;
    }

    public static int getDegres(String motifs) {
        int degre = warningDegres.get(motifs);
    return degre;
    }

    public static int getWarnDegresMax() {
    return warnDegresMax;
    }
}
