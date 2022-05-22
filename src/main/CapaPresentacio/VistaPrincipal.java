package main.CapaPresentacio;

import main.CapaDomini.Models.Cela;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class VistaPrincipal extends JFrame {
    private JTable Full;
    private JTable FirstCol;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField Contingut;
    private JTextField Tipus;
    private JButton IncrementarButton;
    private JButton reduirButton;
    private JButton valorAbsolutButton;
    private JButton potenciaButton;
    private JButton arrelButton;
    private JButton numeroDecimalsButton;
    private JButton conversioButton;
    private JButton canviarTipusNumeroButton;
    private JButton afegirColumnaButton;
    private JComboBox DataFormat;
    private JButton MMButton;
    private JButton DDButton;
    private JButton textDateButton;
    private JButton dateTextButton;
    private JButton AAAAButton;
    private JButton diaSemanalButton;
    private JTextField Resultat;
    private JButton majusculesButton;
    private JButton minusculesButton;
    private JButton button5;
    private JButton pie;
    private JButton Histograma;
    private JComboBox ListaOps;
    private JButton Operabloc;
    private JTextField buscador;
    private JButton buscarButton;
    private JButton remplaçaButton;
    private JButton cancelaButton;
    private ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> LastBusca;

    private AbstractMap.SimpleEntry<Integer, Integer> CelaActual;
    private int columna;
    private int fila;


    public VistaPrincipal(String title, CtrlPresentacio cp) throws Exception {
        super(title);

        CelaActual = null;


        String[] nomColumnes = new String[cp.GetColumnes("Doc 1", "Full 1")];
        String[] nomFiles = new String[cp.GetFiles("Doc 1", "Full 1")];
        for (int i = 0; i < nomColumnes.length; i++) {
            nomColumnes[i] = String.valueOf(i + 1);
        }

        String[][] data = cp.MostrarLlista("Doc 1", "Full 1");


        Full.setModel(new DefaultTableModel(data, nomColumnes));
        Full.setCellSelectionEnabled(true);
        Full.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Full.getTableHeader().setReorderingAllowed(false);

        for (int j = 0; j < nomColumnes.length; j++) {
            TableColumn column = Full.getColumnModel().getColumn(j);
            column.setMinWidth(50);
            column.setMaxWidth(300);
            column.setPreferredWidth(70);
        }


        JMenuBar menuBar = new JMenuBar();
        JMenu fitxer = new JMenu("Fitxer");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem obrir = new JMenuItem("Obrir");
        JMenu exportar = new JMenu("Exportar");
        JMenu importar = new JMenu("Importar");
        JMenuItem CSV_exp = new JMenuItem("CSV");
        JMenuItem CSV_imp = new JMenuItem("CSV");
        JMenuItem pdf = new JMenuItem("pdf");

        importar.add(CSV_imp);
        exportar.add(CSV_exp);
        exportar.add(pdf);

        fitxer.add(guardar);
        fitxer.add(obrir);
        fitxer.add(exportar);
        fitxer.add(importar);
        menuBar.add(fitxer);

        super.setIconImage(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/App_Logo.png"))).getImage());
        IncrementarButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Incrementar.png"))));
        reduirButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Reduir.png"))));
        valorAbsolutButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Valor_Absolut.png"))));
        potenciaButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Potencia.png"))));
        arrelButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Arrel.png"))));
        numeroDecimalsButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Decimals.png"))));
        canviarTipusNumeroButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Canviar.png"))));
        conversioButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Conversio.png"))));


        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setContentPane(panel1);
        this.pack();

        AtomicBoolean modificat = new AtomicBoolean(false);
        AtomicBoolean dataVector = new AtomicBoolean(false);

        guardar.addActionListener(e -> {
            JFileChooser savefile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Full de càlcul", "fdc");
            savefile.setFileFilter(filter);
            int status = savefile.showSaveDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                String fileName = savefile.getSelectedFile().getName();
                File path = savefile.getCurrentDirectory();
                try {
                    cp.guardarDocument(fileName, path);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else System.out.println("Cancelat");
        });


        obrir.addActionListener(e -> {
            JFileChooser openfile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Full de càlcul", "fdc");
            openfile.setFileFilter(filter);
            int status = openfile.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                String fileName = openfile.getSelectedFile().getName();
                File path = openfile.getCurrentDirectory();
                try {
                    cp.obrirDocument(fileName, path);
                } catch (Exception ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "El fitxer no s'ha pogut obrir", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String[][] temp;
                try {
                    temp = cp.MostrarLlista("Doc 1", "Full 1");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
            } else System.out.println("Cancelat");
        });

        CSV_imp.addActionListener(e -> {
            JFileChooser openfile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
            openfile.setFileFilter(filter);
            int status = openfile.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                String fileName = openfile.getSelectedFile().getName();
                File path = openfile.getCurrentDirectory();
                try {
                    cp.ImportarCSV(fileName, path);
                } catch (FileNotFoundException ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "El fitxer no s'ha trobat", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                String[][] temp;
                try {
                    temp = cp.MostrarLlista("Doc 1", "Full 1");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
            } else System.out.println("Cancelat");
        });

        CSV_exp.addActionListener(e -> {
            JFileChooser savefile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
            savefile.setFileFilter(filter);
            int status = savefile.showSaveDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                String fileName = savefile.getSelectedFile().getName();
                File path = savefile.getCurrentDirectory();
                try {
                    cp.exportarCSV(fileName, path);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else System.out.println("Cancelat");
        });

        Full.getModel().addTableModelListener(e -> {
            System.out.println(e.getType());

            if (e.getType() == TableModelEvent.UPDATE && !modificat.get() && !dataVector.get()) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                String mod = Full.getValueAt(row, col).toString().trim();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(row, col);
                modificat.set(true);

                try {
                    //System.out.println(mod);
                    cp.ModificarContingutCela("Doc 1", "Full 1", id, mod);
                    String[][] temp = cp.MostrarLlista("Doc 1", "Full 1");
                    //System.out.println(temp[row][col]);
                    String obj = temp[row][col];
                    String content = cp.ValorTotal("Doc 1", "Full 1", id);
                    String type = cp.GetTipusCela("Doc 1", "Full 1", id);
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                    String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

                    for (int i = 0; i < nomCol.length; i++) {
                        nomCol[i] = String.valueOf(i + 1);
                    }
                    dataVector.set(true);
                    dtm.setDataVector(temp, nomCol);
                    dataVector.set(false);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    //Full.setValueAt(obj, row, col);
                    Full.repaint();

                    System.out.println(Arrays.deepToString(temp));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                modificat.set(false);
            }
        });

        Full.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = fila = Full.rowAtPoint(e.getPoint());
                int col = columna = Full.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    CelaActual = new AbstractMap.SimpleEntry<>(row, col);
                    String content = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                    String type = cp.GetTipusCela("Doc 1", "Full 1", CelaActual);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    System.out.println(content);
                }
            }
        });


        IncrementarButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] opt = {"No Reemplaçar", "Reemplaçar"};
                int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Incrementar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                if (result == 0) {
                    JTextField rowField = new JTextField(5);
                    JTextField colField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
                    myPanel.add(Box.createHorizontalStrut(15));
                    myPanel.add(new JLabel("Fila:"));
                    myPanel.add(rowField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Columna:"));
                    myPanel.add(colField);

                    int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Incrementar", JOptionPane.OK_CANCEL_OPTION);
                    if (result_2 == JOptionPane.OK_OPTION) {
                        Integer row = Integer.parseInt(rowField.getText());
                        Integer col = Integer.parseInt(colField.getText());
                        AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                        int codi;
                        try {
                            codi = cp.CalculaIncrementIReemplaca("Doc 1", "Full 1", CelaActual, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaRemp);
                            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
                            Full.repaint();
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (result == 1) {
                    try {
                        int codi = cp.CalculaIncrement("Doc 1", "Full 1", CelaActual);
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                            Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                            Full.repaint();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        dateTextButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            try {
                if(!cp.transformaText("Doc 1", "Full 1", id))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);
                else{
                    String[][] temp = new String[0][];
                    try {
                        temp = cp.MostrarLlista("Doc 1", "Full 1");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(temp[fila][columna]);
                    String obj = temp[fila][columna];
                    String content = cp.ValorTotal("Doc 1", "Full 1", id);
                    String type = cp.GetTipusCela("Doc 1", "Full 1", id);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    Full.setValueAt(obj, fila, columna);
                    Full.repaint();
                }
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });
        textDateButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            try {
                if(!cp.transformaData("Doc 1", "Full 1", id))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);
                else{
                    String[][] temp = new String[0][];
                    try {
                        temp = cp.MostrarLlista("Doc 1", "Full 1");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(temp[fila][columna]);
                    String obj = temp[fila][columna];
                    String content = cp.ValorTotal("Doc 1", "Full 1", id);
                    String type = cp.GetTipusCela("Doc 1", "Full 1", id);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    Full.setValueAt(obj, fila, columna);
                    Full.repaint();
                }
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });
        minusculesButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            try {
                cp.AllMinus("Doc 1", "Full 1", id);
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            String[][] temp = new String[0][];
            try {
                temp = cp.MostrarLlista("Doc 1", "Full 1");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(temp[fila][columna]);
            String obj = temp[fila][columna];
            String content = cp.ValorTotal("Doc 1", "Full 1", id);
            String type = cp.GetTipusCela("Doc 1", "Full 1", id);
            Tipus.setText(type);
            Contingut.setText(content);Full.setValueAt(obj, fila, columna);
            Full.repaint();
        });
        majusculesButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            try {
                cp.AllMayus("Doc 1", "Full 1", id);
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            String[][] temp = new String[0][];
            try {
                temp = cp.MostrarLlista("Doc 1", "Full 1");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(temp[fila][columna]);
            String obj = temp[fila][columna];
            String content = cp.ValorTotal("Doc 1", "Full 1", id);
            String type = cp.GetTipusCela("Doc 1", "Full 1", id);
            Tipus.setText(type);
            Contingut.setText(content);Full.setValueAt(obj, fila, columna);
            Full.repaint();
        });
        DDButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            String content = cp.getDia("Doc 1", "Full 1", id);
            if(Objects.equals(content, "null"))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);

            else{
                Resultat.setText("Dia "+content);
            }
        });

        MMButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            String content = cp.getMes("Doc 1", "Full 1", id);
            if(Objects.equals(content, "null"))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);

            else{
                Resultat.setText("Mes "+content);
            }
        });

        AAAAButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            String content = cp.getAny("Doc 1", "Full 1", id);
            if(Objects.equals(content, "null"))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);

            else{
                Resultat.setText("Any "+content);
            }
        });

        diaSemanalButton.addActionListener(e -> {
            System.out.println(fila + " " + columna);
            AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
            String content = cp.getWeekday("Doc 1", "Full 1", id);
            if(Objects.equals(content, "null"))JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog",JOptionPane.ERROR_MESSAGE);

            else{
                Resultat.setText("Dia de la setmana "+content);
            }
        });
        Histograma.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/histogram.png"))));
        Histograma.addActionListener(e -> {
            JTextField colField1 = new JTextField();
            JTextField rowIniField2 = new JTextField();
            JTextField rowFiField2 = new JTextField();
            JTextField colField2 = new JTextField();

            JPanel myPanel = new JPanel();
            JPanel Text = new JPanel();
            JPanel Col1 = new JPanel();
            JPanel Col2 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Introduiex la Columna amb les dades Numeriques"));
            myPanel.add(Text);

            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna 1:"));
            Col1.add(colField1);
            Col1.add(new JLabel("Columna 2:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            myPanel.add(Box.createVerticalStrut(15));
            Col2.add(new JLabel("Fila Inicial:"));
            Col2.add(rowIniField2);
            Col2.add(new JLabel("Fila Final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);

            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Reduir", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                Integer rowI,rowF,col,rowI2,rowF2,col2;
                try {
                    rowI = Integer.parseInt(rowIniField2.getText());
                    rowF = Integer.parseInt(rowFiField2.getText());
                    col = Integer.parseInt(colField1.getText());
                    rowI2 = Integer.parseInt(rowIniField2.getText());
                    rowF2 = Integer.parseInt(rowFiField2.getText());
                    col2 = Integer.parseInt(colField2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!col.equals(col2) && rowI < rowF){
                    CategoryChart chart = null;
                    try {
                        System.out.println("hey");
                        chart = cp.Histograma("Doc 1", "Full 1", col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    else {
                        CategoryChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);

            }
        });
        pie.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/pieChart.png"))));

        pie.addActionListener(e -> {
            JTextField colField1 = new JTextField();
            JTextField rowIniField2 = new JTextField();
            JTextField rowFiField2 = new JTextField();
            JTextField colField2 = new JTextField();

            JPanel myPanel = new JPanel();
            JPanel Text = new JPanel();
            JPanel Col1 = new JPanel();
            JPanel Col2 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Introduiex la Columna amb les dades Numeriques"));
            myPanel.add(Text);

            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna 1:"));
            Col1.add(colField1);
            Col1.add(new JLabel("Columna 2:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            myPanel.add(Box.createVerticalStrut(15));
            Col2.add(new JLabel("Fila Inicial:"));
            Col2.add(rowIniField2);
            Col2.add(new JLabel("Fila Final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);

            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "PIE Chart", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                Integer rowI,rowF,col,rowI2,rowF2,col2;
                try {
                    System.out.println("aaaaaa");
                    rowI = Integer.parseInt(rowIniField2.getText());
                    rowF = Integer.parseInt(rowFiField2.getText());
                    col = Integer.parseInt(colField1.getText());
                    rowI2 = Integer.parseInt(rowIniField2.getText());
                    rowF2 = Integer.parseInt(rowFiField2.getText());
                    col2 = Integer.parseInt(colField2.getText());
                } catch (Exception ex) {
                    System.out.println(1);
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!col.equals(col2) && rowI < rowF){
                    PieChart chart = null;
                    try {
                        System.out.println("hey");
                        chart = cp.PieChart("Doc 1", "Full 1", col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        System.out.println(2);
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    else {
                        PieChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);

            }
        });

        button5.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/chartLinear.png"))));
        button5.addActionListener(e -> {

            JTextField colField1 = new JTextField();
            JTextField rowIniField2 = new JTextField();
            JTextField rowFiField2 = new JTextField();
            JTextField colField2 = new JTextField();

            JPanel myPanel = new JPanel();
            JPanel Text = new JPanel();
            JPanel Col1 = new JPanel();
            JPanel Col2 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Introduiex la Columna amb les dades Numeriques"));
            myPanel.add(Text);

            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna 1:"));
            Col1.add(colField1);
            Col1.add(new JLabel("Columna 2:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            myPanel.add(Box.createVerticalStrut(15));
            Col2.add(new JLabel("Fila Inicial:"));
            Col2.add(rowIniField2);
            Col2.add(new JLabel("Fila Final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);

            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Reduir", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                Integer rowI=0,rowF=0,col=0,rowI2=0,rowF2=0,col2=0;
                try {
                    rowI = Integer.parseInt(rowIniField2.getText());
                    rowF = Integer.parseInt(rowFiField2.getText());
                    col = Integer.parseInt(colField1.getText());
                    rowI2 = Integer.parseInt(rowIniField2.getText());
                    rowF2 = Integer.parseInt(rowFiField2.getText());
                    col2 = Integer.parseInt(colField2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(col != col2 && rowI < rowF){
                    XYChart chart = null;
                    try {
                        chart = cp.LinearChart("Doc 1", "Full 1", col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                    else {
                        chart.getStyler().setCursorEnabled(true);
                        XYChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Dialog", JOptionPane.ERROR_MESSAGE);

            }
        });
        reduirButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] opt = {"No Reemplaçar", "Reemplaçar"};
                int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Reduir", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                if (result == 0) {
                    JTextField rowField = new JTextField(5);
                    JTextField colField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
                    myPanel.add(Box.createHorizontalStrut(15));
                    myPanel.add(new JLabel("Fila:"));
                    myPanel.add(rowField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Columna:"));
                    myPanel.add(colField);

                    int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Reduir", JOptionPane.OK_CANCEL_OPTION);
                    if (result_2 == JOptionPane.OK_OPTION) {
                        Integer row = Integer.parseInt(rowField.getText());
                        Integer col = Integer.parseInt(colField.getText());
                        AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                        int codi;
                        try {
                            codi = cp.CalculaReduirIReemplaca("Doc 1", "Full 1", CelaActual, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaRemp);
                            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
                            Full.repaint();
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (result == 1) {
                    try {
                        int codi = cp.CalculaReduir("Doc 1", "Full 1", CelaActual);
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                            Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                            Full.repaint();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        valorAbsolutButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] opt = {"No Reemplaçar", "Reemplaçar"};
                int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Valor Absolut", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                if (result == 0) {
                    JTextField rowField = new JTextField(5);
                    JTextField colField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
                    myPanel.add(Box.createHorizontalStrut(15));
                    myPanel.add(new JLabel("Fila:"));
                    myPanel.add(rowField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Columna:"));
                    myPanel.add(colField);

                    int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Valor Absolut", JOptionPane.OK_CANCEL_OPTION);
                    if (result_2 == JOptionPane.OK_OPTION) {
                        Integer row = Integer.parseInt(rowField.getText());
                        Integer col = Integer.parseInt(colField.getText());
                        AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                        int codi;
                        try {
                            codi = cp.CalculaValorAbsIReemplaca("Doc 1", "Full 1", CelaActual, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaRemp);
                            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
                            Full.repaint();
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (result == 1) {
                    try {
                        int codi = cp.CalculaValorAbs("Doc 1", "Full 1", CelaActual);
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                            Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                            Full.repaint();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        potenciaButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] opt = {"No Reemplaçar", "Reemplaçar"};
                int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Potencia", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                if (result == 0) {
                    JTextField rowField = new JTextField(5);
                    JTextField colField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
                    myPanel.add(Box.createHorizontalStrut(15));
                    myPanel.add(new JLabel("Fila:"));
                    myPanel.add(rowField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Columna:"));
                    myPanel.add(colField);

                    int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Potencia", JOptionPane.OK_CANCEL_OPTION);
                    if (result_2 == JOptionPane.OK_OPTION) {
                        Integer row = Integer.parseInt(rowField.getText());
                        Integer col = Integer.parseInt(colField.getText());
                        AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                        String expo = JOptionPane.showInputDialog(this, "Introdueixi el exponent que vol utiltizar", "Potencia", JOptionPane.QUESTION_MESSAGE);
                        Double exp = Double.valueOf(expo);
                        int codi;
                        try {
                            codi = cp.CalculaPotenciaIReemplaca("Doc 1", "Full 1", CelaActual, exp, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaRemp);
                            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
                            Full.repaint();
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (result == 1) {
                    try {
                        String expo = JOptionPane.showInputDialog(this, "Introdueixi el exponent que vol utiltizar", "Potencia", JOptionPane.QUESTION_MESSAGE);
                        Double exp = Double.valueOf(expo);
                        int codi = cp.CalculaPotencia("Doc 1", "Full 1", CelaActual, exp);
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                            Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                            Full.repaint();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        arrelButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] opt = {"No Reemplaçar", "Reemplaçar"};
                int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Arrel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                if (result == 0) {
                    JTextField rowField = new JTextField(5);
                    JTextField colField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
                    myPanel.add(Box.createHorizontalStrut(15));
                    myPanel.add(new JLabel("Fila:"));
                    myPanel.add(rowField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Columna:"));
                    myPanel.add(colField);

                    int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Arrel", JOptionPane.OK_CANCEL_OPTION);
                    if (result_2 == JOptionPane.OK_OPTION) {
                        Integer row = Integer.parseInt(rowField.getText());
                        Integer col = Integer.parseInt(colField.getText());
                        AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                        String expo = JOptionPane.showInputDialog(this, "Introdueixi el exponent que vol utiltizar", "Arrel", JOptionPane.QUESTION_MESSAGE);
                        Double exp = Double.valueOf(expo);
                        int codi;
                        try {
                            codi = cp.CalculaArrelIReemplaca("Doc 1", "Full 1", CelaActual, exp, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaRemp);
                            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
                            Full.repaint();
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (result == 1) {
                        String expo = JOptionPane.showInputDialog(this, "Introdueixi el exponent que vol utiltizar", "Potencia", JOptionPane.QUESTION_MESSAGE);
                        Double exp = Double.valueOf(expo);
                    int codi = 1;
                    try {
                        codi = cp.CalculaArrel("Doc 1", "Full 1", CelaActual, exp);
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Un error ha ocorregut to", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (codi == 0) {
                            String temp = cp.ValorTotal("Doc 1", "Full 1", CelaActual);
                            Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                            Full.repaint();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
            }
        });

        numeroDecimalsButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Decimals d = new Decimals(CelaActual, cp, Full);
                d.setLocationRelativeTo(this);
                d.setVisible(true);

            }
        });
        conversioButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela("Doc 1", "Full 1", CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (Objects.equals(cp.GetTipusNumero("Doc 1", "Full 1", CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada ha de ser d'un altre tipus de numero. Consulti el manual per més informació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Conversio c = new Conversio(CelaActual, cp, Full);
                c.setLocationRelativeTo(this);
                c.setVisible(true);

            }
        });
        canviarTipusNumeroButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela("Doc 1", "Full 1", CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Object[] tipus = {"numero", "celsius", "fahrenheit", "kelvin", "km", "m", "cm", "mm", "miles", "yards", "feet", "inches", "graus", "radiants"};
                JComboBox comboBox = new JComboBox(tipus);
                JOptionPane.showMessageDialog(this, comboBox, "Tipus Numero", JOptionPane.QUESTION_MESSAGE);
                try {
                    cp.CanviarTipusNumero("Doc 1", "Full 1", CelaActual, comboBox.getSelectedItem().toString());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        afegirColumnaButton.addActionListener(e -> {
            int colActual = cp.GetColumnes("Doc 1", "Full 1");
            try {
                cp.AfegirCol("Doc 1", "Full 1", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Integer colNova = colActual + 1;
            DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
            dataVector.set(true);
            dtm.addColumn(colNova.toString());
            String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

            for (int i = 0; i < nomCol.length; i++) {
                nomCol[i] = String.valueOf(i + 1);
            }
            String[][] dades;
            try {
                dades = cp.MostrarLlista("Doc 1", "Full 1");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            dtm.setDataVector(dades, nomCol);
            dataVector.set(false);
            Full.repaint();
        });


        Operabloc.addActionListener(e -> {
            JTextField colField1 = new JTextField();
            JTextField rowIniField2 = new JTextField();
            JTextField rowFiField2 = new JTextField();
            JTextField colField2 = new JTextField();
            JTextField colField3 = new JTextField();
            JTextField rowIniField4 = new JTextField();
            JTextField rowFiField4 = new JTextField();
            JTextField colField33 = new JTextField();
            JTextField cont = new JTextField();
            JPanel myPanel = new JPanel();
            JPanel Text = new JPanel();
            JPanel Text2 = new JPanel();
            JPanel Col1 = new JPanel();
            JPanel Col2 = new JPanel();
            JPanel Col3 = new JPanel();
            JPanel Col4 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Blocs per operar"));
            myPanel.add(Text);
            String[] optionsToChoose = {"suma", "resta", "mult", "div", "min","may"};
            ListaOps = new JComboBox<>(optionsToChoose);
            myPanel.add(ListaOps);
            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna incial:"));
            Col1.add(colField1);
            Col1.add(new JLabel("Columna final:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            Col2.add(new JLabel("Fila Inicial:"));
            Col2.add(rowIniField2);
            Col2.add(new JLabel("Fila Final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);
            myPanel.add(Box.createVerticalStrut(15));
            Col3.add(new JLabel("Columna desti inicial:"));
            Col3.add(colField3);
            Col3.add(new JLabel("Columna desti final:"));
            Col3.add(colField33);
            myPanel.add(Col3);


            Col4.add(new JLabel("Fila desti Inicial:"));
            Col4.add(rowIniField4);
            Col4.add(new JLabel("Fila desti Final:"));
            Col4.add(rowFiField4);
            myPanel.add(Col4);

            myPanel.add(Box.createVerticalStrut(15));
            Text2.add(new JLabel("Numero per operar"));
            myPanel.add(Text2);
            myPanel.add(cont);

            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Operacio blocs", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                Integer rowF,col,rowI2,col2, Frowin,Frowdest,Fcolin,Fcoldest;
                    try {
                        col = Integer.parseInt(colField1.getText());
                        rowI2 = Integer.parseInt(rowIniField2.getText());

                        col2 = Integer.parseInt(colField2.getText());
                        rowF = Integer.parseInt(rowFiField2.getText());

                        Frowin = Integer.parseInt(rowIniField4.getText());
                        Frowdest = Integer.parseInt(rowFiField4.getText());

                        Fcolin = Integer.parseInt(colField3.getText());
                        Fcoldest = Integer.parseInt(colField33.getText());

                        System.out.println(col + " " + rowI2 + " " + col2 + " " + rowF + " " + Frowin + " " + Frowdest + " " + Fcolin + " " + Fcoldest + " ");
                    } catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Dialog", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        Double.parseDouble(cont.getText());
                        }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(new JFrame(), "El operador no és un numero vàlid", "Dialog", JOptionPane.ERROR_MESSAGE);
                        return;
                         }
                    int cols = nomColumnes.length;
                    int fils=nomFiles.length;
                if(col>col2 || rowI2 > rowF || Fcolin > Fcoldest || Frowin > Frowdest || ((col2-col)-(Fcoldest-Fcolin))!=0 || ((rowF-rowI2)-(Frowdest-Frowin))!=0
                    || col-1 < 0|| col2 -1 < 0|| rowF-1 < 0 || rowI2-1 < 0|| Fcoldest-1 < 0|| Frowdest-1 < 0|| Fcolin-1 < 0||Frowin-1< 0
                        || col-1 >= cols|| col2 -1 >= cols|| rowF-1 >= fils || rowI2-1 >= fils|| Fcoldest-1 >= cols|| Frowdest-1 >= fils|| Fcolin-1 >= cols||Frowin-1>= fils){
                    JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Dialog", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int a;
                    try {
                        a= cp.Opera_bloc("Doc 1", "Full 1",new AbstractMap.SimpleEntry<Integer,Integer>(rowI2-1,col-1),new AbstractMap.SimpleEntry<Integer,Integer>(rowF-1,col2-1)
                                ,new AbstractMap.SimpleEntry<Integer,Integer>(Frowin-1,Fcolin-1),new AbstractMap.SimpleEntry<Integer,Integer>(Frowdest-1,Fcoldest-1), ListaOps.getItemAt(ListaOps.getSelectedIndex()).toString(),Double.parseDouble(cont.getText()));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    if (a==-1) JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son del tipus correcte", "Dialog", JOptionPane.ERROR_MESSAGE);
                }
                String[][] temp;
                try {
                    temp = cp.MostrarLlista("Doc 1", "Full 1");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
                System.out.println(ListaOps.getItemAt(ListaOps.getSelectedIndex()));

            }
        });
        AtomicReference<Color> color = new AtomicReference<>(new Color(187,225,229));
        class PaintTableCellRender extends DefaultTableCellRenderer{
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
                AbstractMap.SimpleEntry<Integer,Integer> id = new AbstractMap.SimpleEntry<>(row,column);
                if (LastBusca.contains(id)) {
                    System.out.println("CELDA" + id);
                    setBackground(color.get());
                    setEnabled(true);
                    setText(cp.ValorTotal("Doc 1", "Full 1", id));
                } else {
                    setBackground(Color.WHITE);
                    setText(cp.ValorTotal("Doc 1", "Full 1", id));
                }
                return this;
            }
        }

        buscarButton.addActionListener(e -> {
            if(buscador.getText().equals("")){
                JOptionPane.showMessageDialog(new JFrame(), "No s'ha indicat la paraula al buscador", "Dialog", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //ACTUALITZEM BUSCA
                ArrayList<Cela> r= cp.Busca("Doc 1", "Full 1", buscador.getText());
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = new ArrayList<>();
                for(Cela a : r){
                    ids.add(new AbstractMap.SimpleEntry(a.getId().getKey(),a.getId().getValue()));
                }
                LastBusca =  ids;

                for(int i = 0; i < cp.GetColumnes("Doc 1", "Full 1");i++){
                    TableColumn col = Full.getColumnModel().getColumn(i);
                    col.setCellRenderer(new PaintTableCellRender());
                    Full.repaint();
                }
                Full.setEnabled(false);
            }
        });
        cancelaButton.addActionListener(e -> {
                LastBusca =  new ArrayList<>();

            String[][] temp;
            try {
                temp = cp.MostrarLlista("Doc 1", "Full 1");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
            String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

            for (int i = 0; i < nomCol.length; i++) {
                nomCol[i] = String.valueOf(i + 1);
            }
            dataVector.set(true);
            dtm.setDataVector(temp, nomCol);
            dataVector.set(false);
            Full.repaint();
            Full.setEnabled(true);

        });


        remplaçaButton.addActionListener(e -> {
            JTextField colField1 = new JTextField();
            JTextField colField2 = new JTextField();

            JPanel myPanel = new JPanel();
            JPanel Text = new JPanel();
            JPanel Col1 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Els remplaçament només son vàlids a celes de tipus Text"));
            myPanel.add(Text);

            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Remplaça de:"));
            Col1.add(colField1);
            Col1.add(new JLabel("a:"));
            Col1.add(colField2);
            myPanel.add(Col1);


            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Remplaçar", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                String busca, rempla;
                busca = colField1.getText();
                System.out.println(busca);
                rempla = colField2.getText();
                try {
                    ArrayList<Cela> a = cp.BuscarRemp("Doc 1", "Full 1", busca, rempla);
                    for (Cela c : a) {
                        int col = c.getId().getValue();
                        int row = c.getId().getKey();
                        AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(row, col);

                        try {
                            String[][] temp = cp.MostrarLlista("Doc 1", "Full 1");
                            String content = cp.ValorTotal("Doc 1", "Full 1", id);
                            String type = cp.GetTipusCela("Doc 1", "Full 1", id);
                            DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                            String[] nomCol = new String[cp.GetColumnes("Doc 1", "Full 1")];

                            for (int i = 0; i < nomCol.length; i++) {
                                nomCol[i] = String.valueOf(i + 1);
                            }
                            dataVector.set(true);
                            dtm.setDataVector(temp, nomCol);
                            dataVector.set(false);
                            Tipus.setText(type);
                            Contingut.setText(content);
                            //Full.setValueAt(obj, row, col);
                            Full.repaint();

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }


        });
    }


}









