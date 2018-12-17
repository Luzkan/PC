//Marcel Jerzyk 07.04.2018 21:20
//Jednak to nie było mi potrzebnę, lecz wszystko zachowuje w celach wspominkowych "kiedyś to było".

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Error extends JFrame {
    
    private JButton btnOK = new JButton("OK");
    private String errorString;

    public Error(int errorCode){
        setTitle("Błąd!");
        setSize(260,145);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        initComponent();
        initEvent();
        errorString = errorMessage(errorCode);
    }

    public String errorMessage(int errorCode){

        String errorMessage;
        switch (errorCode) {
            case 1:  errorMessage = "Należy podać liczbę w polu zakres.";
                break;
            case 2:  errorMessage = "Należy podać liczbę w polu ilość argumentów.";
                break;
            case 3:  errorMessage = "W polach argumentów można podać tylko liczby.";
                break;
            default: errorMessage = "Wystąpił nieznany error. Przepraszam :(";
                break;
        }
        return errorMessage;
    }

    private JLabel lblError = new JLabel(errorString);

    private void initComponent(){
        btnOK.setBounds(70,70, 115,25);
        add(btnOK);

        lblError.setBounds(10,10,250,20);
        add(lblError);

    }

    private void initEvent(){

        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOKClick(e);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

    }

    private void btnOKClick(ActionEvent evt){
        //ZAMKNIJ OKIENECZKO
    }
}
*/