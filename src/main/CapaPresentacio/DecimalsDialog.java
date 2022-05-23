package main.CapaPresentacio;

import main.CapaDomini.Models.PublicFuntions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;

public class DecimalsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JRadioButton arrodonirRadioButton;
    private JRadioButton truncarRadioButton;

    public DecimalsDialog(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        setResizable(false);
        setTitle("Decimals");

        ButtonGroup group = new ButtonGroup();
        group.add(arrodonirRadioButton);
        group.add(truncarRadioButton);

        buttonOK.addActionListener(e -> {
            try {
                onOK(cela, cp, full);
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });

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

    private void onOK(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full) throws CloneNotSupportedException {
        String dec = textField1.getText().trim();
        if (dec.isBlank() || (!arrodonirRadioButton.isSelected() && !truncarRadioButton.isSelected())) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Ompli tots els camps per poder procedir", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (PublicFuntions.isNum(dec) && Integer.parseInt(dec) >= 0) {
            try {
                cp.CanviarDecimals("Full 1", cela, Integer.valueOf(dec));
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            if (truncarRadioButton.isSelected()) {
                try {
                    cp.CanviarArrodonit("Full 1", cela, false);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    cp.CanviarArrodonit("Full 1", cela, true);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            String cont = cp.ValorTotal("Full 1", cela);
            full.setValueAt(cont, cela.getKey(), cela.getValue());
            dispose();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "El decimals han de ser números per sobre el 0", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
