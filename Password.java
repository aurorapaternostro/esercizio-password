/**
 * Created by aura on 03/02/16.
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Programmare un’interfaccia grafica per la cassaforte.
 * All’inserimento della password deve comparire in tempo
 * reale una scritta che dice se la password immessa è poco
 * sicura (in rosso), mediamente sicura (in arancione) o molto
 * sicura (in verde)
 */
public class Password extends JFrame{

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    ImageIcon icon = new ImageIcon("/home/aura/IdeaProjects/Esercizi11/password.png");
    JLabel label1 = new JLabel(icon);
    JLabel label2 = new JLabel("Password:");
    JPasswordField password = new JPasswordField(20);
    JLabel label3 = new JLabel();
    JLabel passSecure = new JLabel();

    public Password(){
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel1.add(label1);
        panel2.add(label2);
        panel2.add(password);
        panel2.add(passSecure);
        panel3.add(passSecure);
        c.add(panel1);
        c.add(panel2);
        c.add(panel3);
        this.password.getDocument().addDocumentListener(new MyDocumentListener());


        this.setVisible(true);
        this.setSize(500,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MyDocumentListener implements DocumentListener{
        @Override public void insertUpdate(DocumentEvent e){
            verificaPassword(password,passSecure);
        }
        @Override public void removeUpdate(DocumentEvent e){
            verificaPassword(password,passSecure);
        }
        @Override public void changedUpdate(DocumentEvent e){ verificaPassword(password,passSecure); }
    }

    //#### Metodi ####

    /*tre condizioni. almeno 8 caratteri, almeno una lettera maiuscola, almeno una cifra*/
    public void verificaPassword(JPasswordField pw, JLabel passSecure ){
        boolean condizione1 = false;
        boolean condizione2 = false;
        boolean condizione3 = false;

        String pass = new String(pw.getPassword());

        System.out.println(pass);

        String regex1 = "\\.*+[A-Z]+\\.*";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(pass);

        String regex2 = "\\.*+[0-9]+\\.*";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(pass);

        if(pass.length() >= 8) condizione1 = true;
        System.out.println("lunghezza " + pass.length());
        System.out.print(condizione1);
        if(matcher1.find()) condizione2 = true;
        System.out.print(condizione2);
        if(matcher2.find()) condizione3 = true;
        System.out.print(condizione3);
        System.out.println("");

        if(condizione1 && condizione2 && condizione3){
            passSecure.setText("Password Molto Sicura!");
            passSecure.setForeground(Color.GREEN);
        }
        else
        if( (condizione1 && condizione2) || (condizione1 && condizione3)){
            passSecure.setText("Password Poco Sicura!");
            passSecure.setForeground(Color.ORANGE);
        }

        else {
            passSecure.setText("Password Non Sicura");
            passSecure.setForeground(Color.RED);
        }


    }
}
