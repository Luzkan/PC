import java.util.Scanner;

public class main {

    public static void main(String[] args)
    {

        // First input and creating a loop to repeat the program

        System.out.println("Welcome to my variation of b-tree based on my understanding of the list.");
        System.out.println("Type (show) or (show5) to see the 4-spaced rows.");
        System.out.println("Type exit to quit.");
        System.out.println("Enter a number to start the program:");
        Scanner scanner = new Scanner(System.in);
        String inputStr = ("notexit");

        // Creating first array that just exists at the beginning
        // First one is the column
        // Second one is the row (throwing big number for convenience)

        int[][] array = new int[5][1000];
        int inputNum = 0;

        while(!inputStr.equals("exit")) {

            boolean waitabit = true;

            // Asking for the first inputStr from user and converting to number

            inputStr = scanner.nextLine();
            if(isInt(inputStr)){
                inputNum = Integer.parseInt(inputStr);
            }else if(inputStr.equals("show")) {
                System.out.println("Checking array[0][500]: " + array[0][500]);
                System.out.println("Checking array[1][500]: " + array[1][500]);
                System.out.println("Checking array[2][500]: " + array[2][500]);
                System.out.println("Checking array[3][500]: " + array[3][500]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][501]: " + array[0][501]);
                System.out.println("Checking array[1][501]: " + array[1][501]);
                System.out.println("Checking array[2][501]: " + array[2][501]);
                System.out.println("Checking array[3][501]: " + array[3][501]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][502]: " + array[0][502]);
                System.out.println("Checking array[1][502]: " + array[1][502]);
                System.out.println("Checking array[2][502]: " + array[2][502]);
                System.out.println("Checking array[3][502]: " + array[3][502]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][503]: " + array[0][503]);
                System.out.println("Checking array[1][503]: " + array[1][503]);
                System.out.println("Checking array[2][503]: " + array[2][503]);
                System.out.println("Checking array[3][503]: " + array[3][503]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][504]: " + array[0][504]);
                System.out.println("Checking array[1][504]: " + array[1][504]);
                System.out.println("Checking array[2][504]: " + array[2][504]);
                System.out.println("Checking array[3][504]: " + array[3][504]);
                continue;

            }else if(inputStr.equals("show5")) {
                System.out.println("Checking array[0][505]: " + array[0][505]);
                System.out.println("Checking array[1][505]: " + array[1][505]);
                System.out.println("Checking array[2][505]: " + array[2][505]);
                System.out.println("Checking array[3][505]: " + array[3][505]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][506]: " + array[0][506]);
                System.out.println("Checking array[1][506]: " + array[1][506]);
                System.out.println("Checking array[2][506]: " + array[2][506]);
                System.out.println("Checking array[3][506]: " + array[3][506]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][507]: " + array[0][507]);
                System.out.println("Checking array[1][507]: " + array[1][507]);
                System.out.println("Checking array[2][507]: " + array[2][507]);
                System.out.println("Checking array[3][507]: " + array[3][507]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][508]: " + array[0][508]);
                System.out.println("Checking array[1][508]: " + array[1][508]);
                System.out.println("Checking array[2][508]: " + array[2][508]);
                System.out.println("Checking array[3][508]: " + array[3][508]);
                System.out.println("-------------------------------");
                System.out.println("Checking array[0][509]: " + array[0][509]);
                System.out.println("Checking array[1][509]: " + array[1][509]);
                System.out.println("Checking array[2][509]: " + array[2][509]);
                System.out.println("Checking array[3][509]: " + array[3][509]);
                continue;
            }else {
                System.out.println("Please input numbers. You wrote: " + inputStr);
                continue;
            }

            // Adding the first thing we got from the user
            // Creating the root and first leaf

            if(array[0][500] == 0) {
                array[0][500] = inputNum;
                array[0][501] = inputNum;
                System.out.println("[ADDED] Array: [0] [500] is equal to: " + inputNum);
                System.out.println("[ADDED] Array: [0] [501] is equal to: " + inputNum);
                continue;
            }

            outlook:
            for(int n = 500; n < 1000; n++) {
                for (int i = 0; i <= 4; i++) {


                    // Getting into right column, checking if it exist
                    if(inputNum > array[i][n]) {
                        if(i+1 != 5)
                            if(inputNum < array[i+1][n] || array[i+1][n] == 0)
                                if(array[0][n+1] != 0)
                                    if(inputNum > array[0][n+1])
                                        break;
                    }


                    // Testing if the row is full
                    if(i == 4){
                        for(int p = n; p >= 500; p--){
                            for(int k = 0; k <= 4; k++) {
                                if (array[i - 4][n] == array[k][p]) {
                                    if (array[k+1][p] == 0) {
                                        if (inputNum < array[2][n]) {

                                            array[k+1][p] = inputNum;
                                            array[0][n + 1] = inputNum;
                                            array[1][n + 1] = array[2][n];
                                            array[2][n] = 0;
                                            array[2][n + 1] = array[3][n];
                                            array[3][n] = 0;
                                            System.out.println("Created new column!");
                                            System.out.println("[ADDED] Array: " + "[" + (k+1) + "] [" + (p) + "] is equal to:" + array[k+1][p]);
                                            System.out.println("[CHANGED] Array: " + "[" + 2 + "] [" + n + "] is equal to:" + array[2][n]);
                                            System.out.println("[CHANGED] Array: " + "[" + 3 + "] [" + n + "] is equal to:" + array[3][n]);
                                            System.out.println("[ADDED] Array: " + "[" + 0 + "] [" + (n + 1) + "] is equal to:" + array[0][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 1 + "] [" + (n + 1) + "] is equal to:" + array[1][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 2 + "] [" + (n + 1) + "] is equal to:" + array[2][n + 1]);
                                            break outlook;

                                        } else if (inputNum < array[3][n]) {

                                            array[k+1][p] = array[2][n];
                                            array[0][n + 1] = array[2][n];
                                            array[2][n] = 0;
                                            array[1][n + 1] = inputNum;
                                            array[2][n + 1] = array[3][n];
                                            array[3][n] = 0;
                                            System.out.println("Created new column!");
                                            System.out.println("[ADDED] Array: " + "[" + (k+1) + "] [" + (p) + "] is equal to:" + array[k+1][p]);
                                            System.out.println("[CHANGED] Array: " + "[" + 2 + "] [" + n + "] is equal to:" + array[2][n]);
                                            System.out.println("[CHANGED] Array: " + "[" + 3 + "] [" + n + "] is equal to:" + array[3][n]);
                                            System.out.println("[ADDED] Array: " + "[" + 0 + "] [" + (n + 1) + "] is equal to:" + array[0][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 1 + "] [" + (n + 1) + "] is equal to:" + array[1][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 2 + "] [" + (n + 1) + "] is equal to:" + array[2][n + 1]);
                                            break outlook;

                                        } else {

                                            array[k+1][p] = array[2][n];
                                            array[0][n + 1] = array[2][n];
                                            array[2][n] = 0;
                                            array[1][n + 1] = array[3][n];
                                            array[3][n] = 0;
                                            array[2][n + 1] = inputNum;
                                            System.out.println("Created new column!");
                                            System.out.println("[ADDED] Array: " + "[" + (k+1) + "] [" + (p) + "] is equal to:" + array[k+1][p]);
                                            System.out.println("[CHANGED] Array: " + "[" + 2 + "] [" + n + "] is equal to:" + array[2][n]);
                                            System.out.println("[CHANGED] Array: " + "[" + 3 + "] [" + n + "] is equal to:" + array[3][n]);
                                            System.out.println("[ADDED] Array: " + "[" + 0 + "] [" + (n + 1) + "] is equal to:" + array[0][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 1 + "] [" + (n + 1) + "] is equal to:" + array[1][n + 1]);
                                            System.out.println("[ADDED] Array: " + "[" + 2 + "] [" + (n + 1) + "] is equal to:" + array[2][n + 1]);
                                            break outlook;

                                        }
                                    }
                                }
                            }
                        }

                    }

                    if(inputNum < array[i][n]){
                        int m = array[i][n];
                        if(i+1 != 4) {

                            if(i+1 != 0 && i+2 != 4 && array[3][n] == 0 && array[3][n-1] != 0)
                            {

                                array[i][n] = inputNum;
                                array[i + 3][n] = array[i + 2][n];
                                array[i + 2][n] = array[i + 1][n];
                                array[i + 1][n] = m;
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + i + "] [" + n + "] is equal to: " + array[i][n]);
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + (i+1) + "] [" + n + "] is equal to: " + array[i+1][n]);
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + (i+2) + "] [" + n + "] is equal to: " + array[i+2][n]);
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + (i+3) + "] [" + n + "] is equal to: " + array[i+3][n]);
                                waitabit = false;


                            }else if (array[3][n] == 0){

                                array[i][n] = inputNum;
                                array[i + 2][n] = array[i + 1][n];
                                array[i + 1][n] = m;
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + i + "] [" + n + "] is equal to: " + array[i][n]);
                                System.out.println("[CHANGED/ADDED] Array: " + "[" + (i + 1) + "] [" + n + "] is equal to: " + array[i + 1][n]);
                                waitabit = false;
                            }

                            // Condition if switching first in the row
                            if(i == 0 && n != 500) {
                                firstloopy:
                                for (int p = 500; p < 505; p++) {
                                    for (int k = 0; k <= 3; k++) {
                                        if (array[k][p] == array[i+1][n]) {
                                            array[k][p] = inputNum;
                                            System.out.println("[CHANGED] Array: " + "[" + k + "] [" + p + "] is equal to: " + array[k][p]);
                                            break firstloopy;
                                        }
                                    }
                                }
                            }

                            if(waitabit) {
                                if (array[0][n] != 0 && array[1][n] != 0 && array[2][n] != 0 && array[3][n] != 0) {

                                    System.out.println("Just checking with (i): " + i + " (n): " + n);
                                    System.out.println("array[i][n]: " + array[i][n]);

                                    lastloop:
                                    for (int p = 500; p < 505; p++) {
                                        for (int k = 0; k <= 3; k++) {
                                            if (array[k][p] == array[0][n]) {
                                                for (int u = 0; u <= 3; u++) {
                                                    if (array[u][p] == 0) {
                                                        System.out.println("(u): " + u);

                                                        if (u == 3){


                                                        }

                                                        /*if (u == 3) {

                                                            array[u][p] = array[u - 1][p];
                                                            array[0][p + u + 1] = array[0][p + u];
                                                            array[1][p + u + 1] = array[1][p + u];
                                                            array[2][p + u + 1] = array[2][p + u];
                                                            array[3][p + u + 1] = array[3][p + u];


                                                            System.out.println("[SWITCHED] Array: " + "[" + (u) + "] [" + p + "] is equal to: " + array[u][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u + 1) + "] is equal to: " + array[0][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u + 1) + "] is equal to: " + array[1][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u + 1) + "] is equal to: " + array[2][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (3) + "] [" + (p + u + 1) + "] is equal to: " + array[3][(p + u + 1)]);
                                                            System.out.println("- - - - -");

                                                            array[u - 1][p] = array[u - 2][p];
                                                            array[0][p + u] = array[0][p + u - 1];
                                                            array[1][p + u] = array[1][p + u - 1];
                                                            array[2][p + u] = array[2][p + u - 1];
                                                            array[3][p + u] = array[3][p + u - 1];

                                                            System.out.println("[SWITCHED] Array: " + "[" + (u - 1) + "] [" + p + "] is equal to: " + array[u - 1][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u) + "] is equal to: " + array[0][(p + u)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u) + "] is equal to: " + array[1][(p + u)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u) + "] is equal to: " + array[2][(p + u)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (3) + "] [" + (p + u) + "] is equal to: " + array[3][(p + u)]);
                                                            System.out.println("- - - - -");

                                                            array[u - 2][p] = inputNum;
                                                            array[0][p + u - 1] = inputNum;
                                                            array[1][p + u - 1] = array[i][n];
                                                            array[2][p + u - 1] = array[i + 1][n];
                                                            array[3][p + u - 1] = array[i + 2][n];

                                                            System.out.println("[SWITCHED] Array: " + "[" + (u - 2) + "] [" + p + "] is equal to: " + array[u - 2][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u - 1) + "] is equal to: " + array[0][(p + u - 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u - 1) + "] is equal to: " + array[1][(p + u - 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u - 1) + "] is equal to: " + array[2][(p + u - 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (3) + "] [" + (p + u - 1) + "] is equal to: " + array[3][(p + u - 1)]);
                                                            System.out.println("- - - - -");

                                                            array[i][n] = 0;
                                                            array[i + 1][n] = 0;

                                                            System.out.println("[SWITCHED] Array: " + "[" + (i) + "] [" + (n) + "] is equal to: " + array[i][n]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (i + 1) + "] [" + (n) + "] is equal to: " + array[i + 1][n]);

                                                            //array[u-1][p] = array[i+1][n];

                                                            break lastloop;
                                                        }
                                                        if (u == 1) {

                                                            array[u][p] = array[i][p+u];
                                                            array[0][p + u + 1] = array[i][p+u];
                                                            array[1][p + u + 1] = array[i+1][p + u];
                                                            array[2][p + u + 1] = array[i+2][p + u];
                                                            array[3][p + u + 1] = 0;


                                                            System.out.println("[SWITCHED] Array: " + "[" + (u) + "] [" + p + "] is equal to: " + array[u][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u + 1) + "] is equal to: " + array[i][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u + 1) + "] is equal to: " + array[1][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u + 1) + "] is equal to: " + array[2][(p + u + 1)]);
                                                            System.out.println("- - - - -");


                                                            array[i][p + u] = inputNum;
                                                            array[i+1][p + u] = 0;
                                                            array[i+2][p + u] = 0;

                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (n) + "] is equal to: " + array[1][n]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u) + "] is equal to: " + array[2][p + u]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (3) + "] [" + (p + u) + "] is equal to: " + array[3][p + u]);

                                                            //array[u-1][p] = array[i+1][n];

                                                            break lastloop;
                                                        }
                                                        if (u == 2) {

                                                            array[u][p] = array[u - 1][p];
                                                            array[0][p + u + 1] = array[0][p + u];
                                                            array[1][p + u + 1] = array[1][p + u];
                                                            array[2][p + u + 1] = array[2][p + u];
                                                            array[3][p + u + 1] = array[3][p + u];


                                                            System.out.println("[SWITCHED] Array: " + "[" + (u) + "] [" + p + "] is equal to: " + array[u][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u + 1) + "] is equal to: " + array[0][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u + 1) + "] is equal to: " + array[1][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (2) + "] [" + (p + u + 1) + "] is equal to: " + array[2][(p + u + 1)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (3) + "] [" + (p + u + 1) + "] is equal to: " + array[3][(p + u + 1)]);
                                                            System.out.println("- - - - -");

                                                            array[u - 1][p] = array[i][n];
                                                            array[0][p + u] = array[i][n];
                                                            array[1][p + u] = array[i+1][p + u - 1];
                                                            array[2][p + u] = 0;
                                                            array[3][p + u] = 0;

                                                            System.out.println("[SWITCHED] Array: " + "[" + (u - 1) + "] [" + p + "] is equal to: " + array[u - 1][p]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (0) + "] [" + (p + u) + "] is equal to: " + array[0][(p + u)]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (1) + "] [" + (p + u) + "] is equal to: " + array[1][(p + u)]);
                                                            System.out.println("- - - - -");

                                                            array[i][n] = inputNum;
                                                            array[i + 1][n] = 0;

                                                            System.out.println("[SWITCHED] Array: " + "[" + (i) + "] [" + (n) + "] is equal to: " + array[i][n]);
                                                            System.out.println("[SWITCHED] Array: " + "[" + (i + 1) + "] [" + (n) + "] is equal to: " + array[i + 1][n]);

                                                            //array[u-1][p] = array[i+1][n];

                                                            break lastloop;
                                                        }*/
                                                        break lastloop;
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }


                            break outlook;
                        }



                    }

                    if(array[i][n] == 0 && i != 4) {
                        array[i][n] = inputNum;
                        System.out.println("[ADDED] Array: " + "[" + i + "] [" + n + "] is equal to: " + array[i][n]);
                        break outlook;
                    }

                }
            }








        }

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
