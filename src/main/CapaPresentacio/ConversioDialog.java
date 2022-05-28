package main.CapaPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Objects;

public class ConversioDialog extends JDialog {
    private JPanel contentPane;
    private JButton convertirButton;
    private JButton buttonCancel;
    private JTextField tipusText;
    private JComboBox<String> comboBox1;

    public ConversioDialog(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(convertirButton);
        setResizable(false);
        setMinimumSize(new Dimension(500,200));
        setTitle("Conversió");

        String tipus = cp.GetTipusNumero("Full 1", cela);
        tipusText.setText(tipus);
        tipusText.setEditable(false);

        String [] temperatures = {"celsius", "fahrenheit", "kelvin"};
        String [] distancies = {"km", "m", "cm", "mm", "miles", "yards", "feet", "inches"};
        String [] graus = {"graus", "radiants"};

        if(Arrays.asList(temperatures).contains(tipus)) {
            for (String s : temperatures) {
                comboBox1.addItem(s);
            }
        }
        else if ((Arrays.asList(distancies).contains(tipus))){
            for (String s : distancies) {
                comboBox1.addItem(s);
            }
        }
        else if ((Arrays.asList(graus).contains(tipus))){
            for (String s : graus) {
                comboBox1.addItem(s);
            }
        }

        convertirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK(cela, cp, full, tipus);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, JTable full, String tipus) throws Exception {
        String selecccio = comboBox1.getSelectedItem().toString();
        if (selecccio == null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Ompli tots els camps per poder procedir", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (Objects.equals(selecccio, tipus)){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No pot convertir un numero al mateix tipus", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            cp.CalculaConversio("Full 1", cela, selecccio);
            String valor = cp.ValorTotal("Full 1", cela);
            full.setValueAt(valor, cela.getKey(), cela.getValue());
            full.repaint();
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
