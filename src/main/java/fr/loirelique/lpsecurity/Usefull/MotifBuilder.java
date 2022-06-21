package fr.loirelique.lpsecurity.Usefull;

import fr.loirelique.lpsecurity.Main;

public class MotifBuilder {
    
    public static String getMotif(String[] args , int length) {
        StringBuilder builder = new StringBuilder();
        for ( int i = length ;i < args.length; i++) {

            String ar = Main.plugin.sansAccent(args[i].replace(" ' ", " \' "));
            builder.append(ar).append(" ");
        }
        String motif = builder.toString();

        return motif;
    }

}
