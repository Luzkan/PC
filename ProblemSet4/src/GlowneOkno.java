//Marcel Jerzyk 07.04.2018 21:20

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GlowneOkno extends JFrame {

    private JButton btnKolorki = new JButton("Kolorki");
    private JButton btnArgumenty = new JButton("Argumenty");
    private JButton btnWyjscie = new JButton("Wyjście");

    private JButton btnZakres = new JButton("?");
    private JButton btnIloscArg = new JButton("?");

    private JButton btnOProg = new JButton("O Programie");

    private JTextField txtZakres = new JTextField();
    private JTextField txtIloscArg = new JTextField();

    private JLabel lblZakres = new JLabel("Zakres Pierwszych:");
    private JLabel lblIloscArg = new JLabel("Ilość Argumentów:");

    public GlowneOkno(){
        setTitle("Liczby Pierwsze");
        setSize(420,137);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        initComponent();
        initEvent();
    }

    private void initComponent(){
        btnKolorki.setBounds(290,7, 110,25);
        btnArgumenty.setBounds(290,37, 110,25);
        btnWyjscie.setBounds(290,67, 110,25);

        btnOProg.setBounds(10,67, 110,25);

        btnZakres.setBounds(240,7,41,20);
        btnIloscArg.setBounds(240,32,41,20);

        txtZakres.setBounds(130,7,100,20);
        txtIloscArg.setBounds(130,32,100,20);

        lblZakres.setBounds(10,7,140,20);
        lblIloscArg.setBounds(10,32,140,20);

        add(btnKolorki);
        add(btnArgumenty);
        add(btnWyjscie);

        add(btnOProg);

        add(btnZakres);
        add(btnIloscArg);

        add(lblZakres);
        add(lblIloscArg);

        add(txtZakres);
        add(txtIloscArg);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        btnKolorki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnKolorkiClick(e);
            }
        });

        btnArgumenty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnArgumentyClick(e);
            }
        });

        btnWyjscie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnWyjscieClick(e);
            }
        });

        btnZakres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnZakreskiClick(e);
            }
        });

        btnIloscArg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnIloscArgClick(e);
            }
        });

        btnOProg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOProgClick(e);
            }
        });
    }

    private void btnZakreskiClick(ActionEvent evt){
        final JPanel informMessage = new JPanel();
        JOptionPane.showMessageDialog(informMessage, "Zakres Pierwszych tworzy tablicę liczb pierwszych zaczynając od cyfry 2, aż do wpisanej wartości.", "Zakres", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnIloscArgClick(ActionEvent evt){
        final JPanel informMessage = new JPanel();
        JOptionPane.showMessageDialog(informMessage, "Ilość Argumentów odpowiada za stworzenie danej ilości pól, w których będzie można wpisać szukane wartości.", "Zakres", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnOProgClick(ActionEvent evt){
        final JPanel informMessage = new JPanel();
        JOptionPane.showMessageDialog(informMessage, "Program tworzy tablicę liczb pierwszych.\n" +
                "Tabela zaczyna się od cyfry 2 do podanej przez użytkownika wartości.\n" +
                "Następnie przy użyciu argumentów można wyszukać którąś liczbę pierwszą z kolei.\n\n" +
                "Przykład: (2, 3, 5, 7, 11, 13, 17, 21, 23, 27)\n" +
                "Dla argumentu 6 będzie to szósta liczba z rzędu, czyli:  13.\n\n" +
                "Marcel Jerzyk, 07.04.2018", "O Programie", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnKolorkiClick(ActionEvent evt){
        Kolorki kolorowanka = new Kolorki();
        kolorowanka.setVisible(true);
    }

    private void btnArgumentyClick(ActionEvent evt){
        int n,k;

        try{
            n = Integer.parseInt(txtZakres.getText());
        } catch (NumberFormatException ex) {
            System.err.println("Należy podać liczbę w polu zakres.");
            final JPanel errorMessage = new JPanel();
            JOptionPane.showMessageDialog(errorMessage, "Należy podać liczbę w polu zakres.", "Błąd!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            k = Integer.parseInt(txtIloscArg.getText());
        } catch (NumberFormatException ex) {
            System.err.println("Należy podać liczbę w polu ilość argumentów.");
            final JPanel errorMessage = new JPanel();
            JOptionPane.showMessageDialog(errorMessage, "Należy podać liczbę w polu ilość argumentów.", "Błąd!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(k == 0){
            final JPanel errorMessage = new JPanel();
            JOptionPane.showMessageDialog(errorMessage, "Program nic ciekawego nie zrobi bez argumentów.", "Zero argumentów!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Argumenty argumentowanie = new Argumenty(n, k);
        argumentowanie.setVisible(true);
    }

    private void btnWyjscieClick(ActionEvent evt){
        System.exit(0);
    }
}
