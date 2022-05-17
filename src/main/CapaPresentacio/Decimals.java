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

    public Decimals(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        setResizable(false);
        setTitle("Decimals");

        ButtonGroup group = new ButtonGroup();
        group.add(arrodonirRadioButton);
        group.add(truncarRadioButton);

        buttonOK.addActionListener(e -> onOK(cela, cp, full));

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

    private void onOK(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full){
        if (textField1.getText().trim().isBlank() || (!arrodonirRadioButton.isSelected() && !truncarRadioButton.isSelected())) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Ompli tots els camps per poder procedir", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                cp.CanviarDecimals("Doc 1", "Full 1", cela, Integer.valueOf(textField1.getText().trim()));
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            if (truncarRadioButton.isSelected()) {
                try {
                    cp.CanviarArrodonit("Doc 1", "Full 1", cela, false);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    cp.CanviarArrodonit("Doc 1", "Full 1", cela, true);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            String cont = cp.ValorTotal("Doc 1", "Full 1", cela);
            full.setValueAt(cont, cela.getKey(), cela.getValue());
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
