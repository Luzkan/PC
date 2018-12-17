package RzymskieArabskie;

public class Main {

    public static void main(String[] args)
    {
        int argumentArabski = 0;
        String argumentRzymski = "";

        RzymArab rzymArab = new RzymArab();


        try {
            if (isInt(args[0])) {
                for (int i = 0; i < args.length; i++) {
                    argumentArabski += (Integer.parseInt(args[i]));
                }
                try {
                    System.out.println(rzymArab.arab2rzym(argumentArabski));
                } catch (RzymArabException ex) {
                    System.err.println(ex.tylko());
                }
            } else {
                for (String arg : args) {
                    argumentRzymski += arg;
                }
                try {
                    System.out.println(rzymArab.rzym2arab(argumentRzymski));
                } catch (RzymArabException ex) {
                    System.err.println(ex.tylko());
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Error: Wprowadź liczby arabskie lub rzymskie.");
        }

        //System.out.println(rzymArab.rzym2arab("LXXVI"));      //76
        //System.out.println(rzymArab.arab2rzym(1827));         //MDCCCXXVII

    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        }catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}