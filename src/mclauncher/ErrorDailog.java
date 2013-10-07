package mclauncher;

import javax.swing.*;
import java.awt.event.*;

public class ErrorDailog extends JDialog{

    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea messageTextArea;

    public ErrorDailog(){
        this("");
    }
    public ErrorDailog(String errMessage) {

        super(new JFrame(),"Error Message");

        messageTextArea.setText(errMessage);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


// call onOK() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

// call onOK() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void onOK() {
// add your code here
        dispose();
    }
}
