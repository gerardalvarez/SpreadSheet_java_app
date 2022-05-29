/**
 * Aquesta classe mostra un dialog per poder canviar els decimals i truncor o arrodmir el numero
 * @author Marc Castells
 */

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


    /**
     * Creadora de la classe la qual es l'encarregada de mostrar el dialeg i tractar els possibles errors
     * @param cela Identificador de la cela amb la qual volem treballar
     * @param cp Instancia del controlador de presetenacio per poder fer les crides al domini
     */
    public DecimalsDialog(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp) {
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
            onOK(cela, cp);
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

    /**
     * Aquesta funcio dictamina el que s'ha de fer si l'usuari clica al boto OK, en aquest col·loca el numeros de decimals indicats i trunca o arrodoneix
     * @param cela Identificador de la cela amb la qual volem treballar
     * @param cp Instancia del controlador de presetenacio per poder fer les crides al domini
     */
    private void onOK(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp) {
        String dec = textField1.getText().trim();
        if (dec.isBlank() || (!arrodonirRadioButton.isSelected() && !truncarRadioButton.isSelected())) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Ompli tots els camps per poder procedir", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (PublicFuntions.isNum(dec)) {
            Double d = Double.valueOf(dec);
            if (Integer.parseInt(String.valueOf(d.intValue())) >= 0) {
                cp.CanviarDecimals("Full 1", cela, d.intValue());
                if (truncarRadioButton.isSelected()) {
                    cp.CanviarArrodonit("Full 1", cela, false);
                } else {
                    cp.CanviarArrodonit("Full 1", cela, true);
                }
                dispose();
            }
            else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "El decimals han de ser números per sobre el 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Introdueixi només números", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Funcio que tanca el dialeg quan l'usuari prem el boto Cancel
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
