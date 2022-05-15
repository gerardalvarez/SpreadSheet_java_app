package main.CapaPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;

public class Decimals extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JRadioButton arrodonirRadioButton;
    private JRadioButton truncarRadioButton;

    public Decimals(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        setTitle("Decimals");

        ButtonGroup group = new ButtonGroup();
        group.add(arrodonirRadioButton);
        group.add(truncarRadioButton);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
