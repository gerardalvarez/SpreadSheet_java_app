package main.CapaPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Objects;

/**
 * Aquesta classe mostra un dialog per poder fer la conversió de valors
 * @author Marc Castells
 */
public class ConversioDialog extends JDialog {
    private JPanel contentPane;
    private JButton convertirButton;
    private JButton buttonCancel;
    private JTextField tipusText;
    private JComboBox<String> comboBox1;

    /**
     * Creadora de la classe la qual es l'encarregada de mostrar el dialeg i tractar els possibles errors
     * @param cela Identificador de la cela amb la qual volem treballar
     * @param cp Instancia del controlador de presetenacio per poder fer les crides al domini
     */
    public ConversioDialog(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp) {
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

        convertirButton.addActionListener(e -> {
            try {
                onOK(cela, cp, tipus);
            } catch (Exception ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ha ocorregut un error en la conversió", "Error", JOptionPane.ERROR_MESSAGE);
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

    /**
     * Aquesta funcio dictamina el que s'ha de fer si l'usuari clica al boto OK, en aquest cas fa la conversio
     * @param cela Identificador de la cela amb la qual volem treballar
     * @param cp Instancia del controlador de presetenacio per poder fer les crides al domini
     * @param tipus El nom del tipus al qual volem convertir
     * @throws Exception Si hi ha algun error durant la conversio es llença una execpcio
     */
    private void onOK(AbstractMap.SimpleEntry<Integer, Integer> cela, CtrlPresentacio cp, String tipus) throws Exception {
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
            dispose();
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
