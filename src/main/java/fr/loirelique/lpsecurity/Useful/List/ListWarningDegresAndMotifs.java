package fr.loirelique.lpsecurity.Useful.List;

import java.util.ArrayList;
import java.util.HashMap;

public class ListWarningDegresAndMotifs {
    private static HashMap<String, ArrayList<String>> warningMotifs = new HashMap<String, ArrayList<String>>();
    private static HashMap<String, ArrayList<Integer>> warningDegres = new HashMap<String, ArrayList<Integer>>();

    public static void initializeList() {
        String motif_warn_1 = "Advertissement niveau 1";
        int Degres_warn_1 = 1;
        String warn_lvl_1 = "motif1";
        String motif_warn_2 = "Advertissement niveau 2";
        int Degres_warn_2 = 2;
        String warn_lvl_2 = "motif2";
        int Degres_warn_3 = 3;
        String motif_warn_3 = "Advertissement niveau 3";
        String warn_lvl_3= "motif3";
        warningMotifs.put(warn_lvl_1, new ArrayList<String>());
        (warningMotifs.get(warn_lvl_1)).add(motif_warn_1);
        warningMotifs.put(warn_lvl_2, new ArrayList<String>());
        (warningMotifs.get(warn_lvl_2)).add(motif_warn_2);
        warningMotifs.put(warn_lvl_3, new ArrayList<String>());
        (warningMotifs.get(warn_lvl_3)).add(motif_warn_3);

        warningDegres.put(warn_lvl_1, new ArrayList<Integer>());
        (warningDegres.get(warn_lvl_1)).add(Degres_warn_1);
        warningDegres.put(warn_lvl_2, new ArrayList<Integer>());
        (warningDegres.get(warn_lvl_2)).add(Degres_warn_2);
        warningDegres.put(warn_lvl_3, new ArrayList<Integer>());
        (warningDegres.get(warn_lvl_3)).add(Degres_warn_3);
   
    }

    public static String getMotif(String motif ) {
        String motif = warningMotifs.get(motif).get(index);

    
    }
}
