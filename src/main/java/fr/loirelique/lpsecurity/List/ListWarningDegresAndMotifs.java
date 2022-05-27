package fr.loirelique.lpsecurity.List;

import java.util.HashMap;

import fr.loirelique.lpsecurity.String.MessageWarn;

public class ListWarningDegresAndMotifs {
    private static HashMap<String, String> warningMotifs = new HashMap<String,String>();
    private static HashMap<String, Integer> warningDegres = new HashMap<String,Integer>();
    private static int warnDegresMax ;

    public static void initializeList() {
        warnDegresMax = MessageWarn.getWarnDegresMax();
        String motif_warn_1 = MessageWarn.getMotifWarnLvl1Text();
        int Degres_warn_1 = MessageWarn.getMotifWarnlvl1Degres();
        String warn_lvl_1 = MessageWarn.getMotifWarnLvl1();

        String motif_warn_2 = MessageWarn.getMotifWarnLvl2Text();
        int Degres_warn_2 = MessageWarn.getMotifWarnlvl2Degres();
        String warn_lvl_2 = MessageWarn.getMotifWarnLvl2();

        int Degres_warn_3 = MessageWarn.getMotifWarnlvl3Degres();
        String motif_warn_3 = MessageWarn.getMotifWarnLvl3Text();
        String warn_lvl_3= MessageWarn.getMotifWarnLvl3();
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
