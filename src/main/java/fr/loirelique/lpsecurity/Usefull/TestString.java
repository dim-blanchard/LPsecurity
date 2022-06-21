package fr.loirelique.lpsecurity.Usefull;

public class TestString {
/* 
    Test if have letter in string.
     */
    public static boolean isNumber(String chaine) {
        boolean testNumber = false;
        for (int i = 0; i < chaine.length(); i++) {
            char c = chaine.charAt(i);
            int ascii = c;
            if (ascii >= 48 && ascii <= 57) {
                testNumber = true;
            } else {
                testNumber = false;
                break;
            }
        }
        return testNumber;
    }

}
