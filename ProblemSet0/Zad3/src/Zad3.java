import java.util.ArrayList;
import java.util.Scanner;
public class Zad3
{
    static ArrayList<Integer> list;

    public Zad3()
    {
        list = new ArrayList<Integer>();
    }

    //Tutaj tworzę funkcje, do której będę chciał się odwoływać
    //Chyba tak to działa
    //Funkcja to proste NWD dwóch liczb (x, y), a zwraca y
    public static int NWD(int x, int y)
    {
        int z;
        //Zawsze chce mieć dzielenie większej przez mniejszą, więc sprawdzam, czy czasem y nie jest większy
        //Jak jest to zamieniam
        if(y > x)
        {
            int u;
            u = y;
            y = x;
            x = u;
        }
        while (y != 0)
        {
            //Z to reszta dzielenia x przez y (czyli: x mod y)
            z = x % y;
            x = y;
            y = z;
        }
        return x;
    }

    static public void main(String[] args)
    {
        ArrayList<Integer> l = new ArrayList<Integer>();
        Scanner s = new Scanner(System.in);
        String p;

        System.out.println("Zakończ wpisywanie komendą 'stop'.");
        System.out.println("Wprowadź liczby:");
        while (s.hasNext() && !(p = s.next()).equals("stop"))
        {
            l.add(s.nextInt());
        }

        //c będzie zwracanym NWD
        int c;
        //Jeżeli został wpisany jeden element, to wtedy
        //Jest on swoim NWD, więc
        if(l.size() == 1)
        {
            c = l.get(0);
        }
        //A jak nie to lecimy
        else
        {
            //Przygotowuje zmienne dla pętli
            int a, b;

            c = 2147483647;

            for (int i = 0; i < l.size(); i++)
            {
                //Znalazłem, że jest wartość minimalna, co przydatne
                //a to będzie pierwszy element listy, albo obecny NWD
                //b to będzie każdy kolejny element listy, aż do końca list.size
                //jako, że nie wiem jak Java reaguje na brak wartości, bo c przed pętlą, jest jakby po prostu int
                //to można wrzucić przed pętlą for "c = 'bardzo duża liczba';"
                //aktualnie to zrobilem

                if(c == 2147483647)
                {
                    a = l.get(0);
                }else
                {
                    a = c;
                }
                b = l.get(i);
                c = NWD(a, b);

                //Jak NWD dwóch liczb to 1, to nie trzeba dalej sprawdzać
                //Program wychodzi z pętli za pomocą break; a NWD to 1
                if (NWD(a, b) == 1)
                {
                    c = 1;
                    break;
                }
            }
        }


        //Przerwa by ładniej było
        System.out.println("  ");
        System.out.println("  ");

        //W sumie tutaj nie wiem jak pokazać temu pritnln poniże by wyprintował
        //jeden pod drugim tą liste wpisanych liczb
        System.out.println();
        System.out.println("NWD tych liczb:");
        System.out.println(c);
    }
}