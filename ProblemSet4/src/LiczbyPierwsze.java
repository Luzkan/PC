//Marcel Jerzyk 07.04.2018 21:20

public class LiczbyPierwsze {

    int n = 0;
    private int[] tabeleczka;
    private int iloscPierwszych;

    public LiczbyPierwsze(int n){

        int iloscPierwszych = 0;            // Trzeba będzie sprawdzić jak duża będzie tabela pierwszych

        for (int j = 1; j <= n; j ++){      // Ten loop sprawdzi w/w problem dla danego n
            if (jestPierwsza(j))
                iloscPierwszych +=1;
        }

        tabeleczka = new int[iloscPierwszych];    // Tworzę tabelke

                                            // Zmienna do indexowania liczb pierwszych w tabelce
                                            // Dzięki niemu liczby pierwsze będą ponumerowane
        int indexPierwszej = 0;

                                            // Kolejny loop sprawdza czy liczba jest pierwsza
                                            // Jeżeli tak, to wrzuca do tabeli i przechodzi do następnego indeksu
        for (int x = 1; x <= n; x++){
            if (jestPierwsza(x)) {
                tabeleczka[indexPierwszej] = x;
                indexPierwszej++;
            }
        }
                                            // 'tabeleczka' zawiera teraz liczby pierwsze
                                            // Włożone są po kolei od najmn. do najw.
                                            // iloscPierwszych to wielkość tej tabeli
                                            // czyli ilość liczb pierwszych
    }

    public int liczba(int m){

        int wartoscDlaM;                                 // 'liczba' ma z zadanie zwracać wartość liczby
        if (m < 0 || m >= tabeleczka.length) return -1;  //  pierwszej z tabeleczki o indeksie "m"
        wartoscDlaM = tabeleczka[m];                     //  if sprawdza, by nie wpaść za tabelke
        return wartoscDlaM;
    }


    public static boolean jestPierwsza(int x){

        if (x < 2) return false;
        if (x == 2) return true;
        if (x%2==0) return false;
        //Jeżeli nie, to sprawdzaj tylko nieparzyste
        for(int i=3; i*i <= x; i += 2) {
            if(x%i==0)
                return false;
        }
        return true;
    }
}
