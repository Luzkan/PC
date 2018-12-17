public class Test {
    public static void main(String[] args){

        int n;

        try{                                                // Sprawdzam, czy pierwszy argument
            n = Integer.parseInt(args[0]);                  // jest liczbą, która będzie n'em
        } catch (NumberFormatException ex) {                // W konstruktorze klasy LiczbyPierwsze
            System.err.print("Należy podać liczbę.");       // Jak nie to error i koniec pracy programu
            return;
        }

        LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze(n);

        for(int i=1; i<args.length; i++){

            if(isInt(args[i])){                                                         //Sprawdzam, czy jest to liczba

                if (Integer.parseInt(args[i]) > Integer.parseInt(args[0])){             //Jak tak, czy mieści się w zakresie
                    System.out.println(args[i] + " - Liczba poza zakresem pierwszego argumentu.");
                }else if(Integer.parseInt(args[i]) < 0 || liczbyPierwsze.liczba(Integer.parseInt(args[i])) == -1){
                    System.out.println(args[i] + " - Liczba spoza zakresu.");           //Jak tak, czy jest w tabelce
                }else{                                                                  //Jak tak, podaje odpowiedź
                    System.out.println(args[i] + " - " + liczbyPierwsze.liczba(Integer.parseInt(args[i])));
                }
            }else{
                System.out.print(args[i] + " - Nieprawidłowy argument");
            }
        }
    }


    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
