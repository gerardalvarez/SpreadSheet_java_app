package main.CapaPresentacio;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;
import java.util.Arrays;

public class VistaPrincipal extends JFrame {
    private JTable Full;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    public VistaPrincipal(String title, CtrlPresentacio cp) throws Exception {
        super(title);

        String[] nomColumnes = new String[cp.GetColumnes("Doc 1", "Full 1")];

        for (int i = 0; i < nomColumnes.length; i++) {
            nomColumnes[i] = String.valueOf(i + 1);
        }

        Object[][] dades = {

                {"Mecuri", 0, false},
                {"Venus", 0, false},
                {"Terra", 1, true}
        };

        String[][] data = cp.MostrarLlista("Doc 1", "Full 1");


        Full.setModel(new DefaultTableModel(data, nomColumnes));
        Full.setCellSelectionEnabled(true);
        Full.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        Full.getTableHeader().setReorderingAllowed(false);

        for (int  j = 0; j < nomColumnes.length; j++) {
            TableColumn column = Full.getColumnModel().getColumn(j);
            column.setMinWidth(50);
            column.setMaxWidth(300);
            column.setPreferredWidth(70);
        }



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setContentPane(panel1);
        this.pack();

        Full.getModel().addTableModelListener(e -> {
            System.out.println(e.getType());
            if (e.getType() == TableModelEvent.UPDATE) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                String mod = Full.getValueAt(row, col).toString();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(col, row);

                try {
                    System.out.println(mod);
                    cp.ModificarContingutCela("Doc 1", "Full 1", id, mod);
                    String[][] temp = cp.MostrarLlista("Doc 1", "Full 1");
                    System.out.println(temp[col][row]);
                    Object obj = temp[col][row];
                    //Full.setValueAt("Hola", row, col);
                    Full.repaint();

                    System.out.println(Arrays.deepToString(temp));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
