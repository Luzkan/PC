//Marcel Jerzyk 07.04.2018 21:20

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Kolorki extends JFrame {

    private JButton btnJasnoSzary = new JButton("Jasno Szary");
    private JButton btnSzary = new JButton("Szary");
    private JButton btnCiemnoSzary = new JButton("Ciemno Szary");

    public Kolorki(){
        setTitle("Kolorki");
        setSize(150,145);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        initComponent();
        initEvent();
    }

    private void initComponent(){
        btnJasnoSzary.setBounds(10,10, 115,25);
        btnSzary.setBounds(10,40, 115,25);
        btnCiemnoSzary.setBounds(10,70, 115,25);

        add(btnJasnoSzary);
        add(btnSzary);
        add(btnCiemnoSzary);
    }

    private void initEvent(){

        btnJasnoSzary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnJasnoSzaryClick(e);
            }
        });

        btnSzary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSzaryClick(e);
            }
        });

        btnCiemnoSzary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCiemnoSzaryClick(e);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

    }

    private void btnJasnoSzaryClick(ActionEvent evt){
        getContentPane().setBackground(Color.LIGHT_GRAY);
        //UIManager.put("Panel.background", Color.LIGHT_GRAY);
    }

    private void btnSzaryClick(ActionEvent evt){
        getContentPane().setBackground(Color.GRAY);
        //UIManager.put("Panel.background", Color.GRAY);
    }

    private void btnCiemnoSzaryClick(ActionEvent evt){
        getContentPane().setBackground(Color.DARK_GRAY);
        //UIManager.put("Panel.background", Color.DARK_GRAY);
    }
}
