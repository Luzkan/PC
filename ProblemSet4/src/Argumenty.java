//Marcel Jerzyk 07.04.2018 21:20

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Argumenty extends JFrame {
    
    private JButton btnUruchom = new JButton("Uruchom");
    private JTextField[] txtArgs;
    private JLabel[] lblArgs;
    private int n, k;

    public Argumenty(int n, int k){
        setTitle("Argumenty");
        setSize(420,50+25*k);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);

        //System.out.println("n: " + n);
        //System.out.println("k: " + k);

        this.n = n;
        this.k = k;
        txtArgs = new JTextField[k];
        lblArgs = new JLabel[k];
        initComponent();
        initEvent();
    }

    private void initComponent(){
        btnUruchom.setBounds(300,7, 90,25);
        int m = 0;

        for(m=0; m<k; m++){
            txtArgs[m] = new JTextField();
            txtArgs[m].setBounds(95,7+25*m, 50, 20);
            lblArgs[m] = new JLabel("Argument: " + (m+1));
            lblArgs[m].setBounds(10,7+25*m, 150, 20);

            add(lblArgs[m]);
            add(txtArgs[m]);
        }
        add(btnUruchom);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        btnUruchom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnUruchomClick(e);
            }
        });
    }

    private void btnUruchomClick(ActionEvent evt){
        int args[] = new int[k];
        int m;
        for(m=0; m<k; m++) {
            try{
                args[m] = Integer.parseInt(txtArgs[m].getText());
            } catch (NumberFormatException ex){
                System.err.print("W pola argumentów można wpisać tylko liczby.");
                final JPanel errorMessage = new JPanel();
                JOptionPane.showMessageDialog(errorMessage, "W pola argumentów można wpisać tylko liczby.", "Błąd!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        Test policz = new Test(n, k, args);
        policz.setVisible(true);

    }
}
