package main.CapaPresentacio;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class VistaPrincipal extends JFrame {
    private JTable Full;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField Contingut;
    private JButton decimalsButton;

    public VistaPrincipal(String title, CtrlPresentacio cp) throws Exception {
        super(title);

        String[] nomColumnes = new String[cp.GetColumnes("Doc 1", "Full 1")];

        for (int i = 0; i < nomColumnes.length; i++) {
            nomColumnes[i] = String.valueOf(i + 1);
        }

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

        AtomicBoolean modificat = new AtomicBoolean(false);

        Full.getModel().addTableModelListener(e -> {
            System.out.println(e.getType());

            if (e.getType() == TableModelEvent.UPDATE && !modificat.get()) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                String mod = Full.getValueAt(row, col).toString();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(col, row);
                modificat.set(true);

                try {
                    System.out.println(mod);
                    cp.ModificarContingutCela("Doc 1", "Full 1", id, mod);
                    String[][] temp = cp.MostrarLlista("Doc 1", "Full 1");
                    System.out.println(temp[col][row]);
                    Object obj = temp[col][row];
                    Full.setValueAt(obj, row, col);
                    Full.repaint();

                    System.out.println(Arrays.deepToString(temp));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            else {
                modificat.set(false);
            }
        });
        Full.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = Full.rowAtPoint(e.getPoint());
                int col = Full.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String[][] temp;
                    try {
                        temp = cp.MostrarLlista("Doc 1", "Full 1");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Contingut.setText(temp[col][row]);
                    System.out.println(temp[col][row]);
                }
            }
        });
        decimalsButton.addActionListener(e -> {
            Decimals d = new Decimals();
            d.setVisible(true);
        });
    }

    /*public void showPieChart(){

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset( );
        barDataset.setValue( "IPhone 5s" , new Double( 20 ) );
        barDataset.setValue( "SamSung Grand" , new Double( 20 ) );
        barDataset.setValue( "MotoG" , new Double( 40 ) );
        barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("mobile sales",barDataset, false,true,false);//explain

        PiePlot piePlot =(PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));


        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelBarChart.removeAll();
        panelBarChart.add(barChartPanel, BorderLayout.CENTER);
        panelBarChart.validate();
    }*/
}



