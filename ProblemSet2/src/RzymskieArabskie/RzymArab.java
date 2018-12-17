package RzymskieArabskie;

class RzymArabException extends Exception {
    private String msg;

    RzymArabException(String msg) {
        System.out.println("Error: ");
        this.msg = msg;
    }

    public String tylko() {
        System.out.println("Wprowadź tylko liczby rzymskie albo tylko liczby arabskie.");
        return super.toString();
    }

}


public class RzymArab {

    private static String[] rzymskie =
            {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private static int[] arabskie =
            {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public static int rzym2arab (String rzym) throws RzymArabException{
        for(int i=12; i>=0; i--){                                                                   // Tabela Rzymskich ma 13 opcji
            if(rzym.startsWith(rzymskie[i]))                                                       // Sprawdzam czy args zaczyna się od tej litery
                return arabskie[i] + rzym2arab(rzym.replaceFirst(rzymskie[i], ""));     // Jak tak, to podmieniam tą litere, aż string będzie pusty
        }
        throw new RzymArabException("Należy podać tylko liczby rzymskie.");
    }


    public static String arab2rzym (int arab) throws RzymArabException{
        String rzymskiWynik = "";                   // String do wyświetlania wyniku
        if(arab >= 4000 || arab < 0){
            throw new RzymArabException("Należy podać liczbę z zakresu 0-3999");
        }
        for(int i=12; i>=0; i--){                   // Tabela Arabskich została dostosowana do odpowiadania Rzymskim po indeksach
            while (arab >= arabskie[i]) {           // Jeżeli nasza liczba pasuje, by od niej odjąć obecny arabski odpowiednik
                rzymskiWynik += rzymskie[i];        // To dodajemy rzymski odpowiednik do stringa "rzymskiWynik"
                arab -= arabskie[i];                // Oraz odejmujemy ten arabski odpowiednik od liczby.
            }                                       // Loop trwa aż nie będzie co odejmować
        }
        return rzymskiWynik;
    }
}
