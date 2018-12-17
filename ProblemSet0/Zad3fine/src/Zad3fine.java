public class Zad3fine
{
    public static void main (String[] args)
    {
        for (String arg : args)
        {
            try
            {
                int n=Integer.parseInt(arg);
                System.out.println(div(n));
            }
        catch (NumberFormatException ex)
            {
                System.out.println(arg + " nie jest liczbą całkowitą");
            }
        }
    }

    public static int div (int n)
    {
        n = Math.abs(n);
        for (int i=2; i<Math.sqrt(n); i++)
        {
            if (n%i == 0)
            {
                return n/i;
            }
        }
        return 1;
     }
}
