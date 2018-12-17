//Marcel Jerzyk 07.04.2018 21:20

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {

    private JButton btnOK = new JButton("OK");
    private JLabel[] lblWynik;
    private int k;
    private int n;
    private int[] args;


    public Test(int n, int k, int args[]){

        //GUI
        setTitle("Wynik");
        setSize(420,50+25*k);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        lblWynik = new JLabel[k];
        initEvent();
        this.k = k;
        this.n = n;
        this.args = args;

        /*                                                                   //Wyświetlanie do testów w konsoli
        LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze(n);

        for(int i=1; i<args.length; i++){
                if (args[i] > n){
                    System.out.println(args[i] + " - Liczba poza zakresem pierwszego argumentu.");
                }else if(args[i] < 0 || liczbyPierwsze.liczba(args[i]) == -1){
                    System.out.println(args[i] + " - Liczba spoza zakresu.");
                }else{
                    System.out.println(args[i] + " - " + liczbyPierwsze.liczba(args[i]));
                }
        }*/
        initComponent();
    }

    private void initComponent(){
        btnOK.setBounds(300,7, 90,25);
        int m = 0;
        LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze(n);
        for(m=0; m<k; m++){

            if (args[m] > n){
                lblWynik[m] = new JLabel("Dla liczby " + args[m] + " - Liczba spoza zakresu.");
                lblWynik[m].setBounds(10,7+25*m, 400, 20);
                add(lblWynik[m]);
            }else if(args[m] < 0 || liczbyPierwsze.liczba(args[m]) == -1){
                lblWynik[m] = new JLabel("Dla liczby " + args[m] + " - Liczba spoza zakresu");
                lblWynik[m].setBounds(10,7+25*m, 400, 20);
                add(lblWynik[m]);
            }else{
                lblWynik[m] = new JLabel("Dla liczby " + args[m] + " - " + liczbyPierwsze.liczba(args[m]));
                lblWynik[m].setBounds(10,7+25*m, 400, 20);
                add(lblWynik[m]);
            }
        }
        add(btnOK);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOKClick(e);
            }
        });

    }
    private void btnOKClick(ActionEvent evt){
        System.exit(0);
    }
}
