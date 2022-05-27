package main.CapaPresentacio;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import main.CapaDomini.Models.Cela;
import main.CapaDomini.Models.PublicFuntions;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class VistaPrincipal extends JFrame {
    private JTable Full;
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
    private JButton LinearButton;
    private JButton pie;
    private JButton Histograma;
    private JComboBox ListaOps;
    private JButton Operabloc;
    private JButton afegirFilaButton;
    private JButton eliminarColumnaButton;
    private JButton eliminarFilaButton;
    private JButton Copia;
    private JButton ordenaBlocButton;
    private JButton cancelButton;
    private JButton buscarButton;
    private JTextField buscador;
    private JButton remplaçaButton;
    private JButton wordsButton;
    private JButton charsButton;
    private JButton vowelsButton;
    private JLabel NomFull;

    private AbstractMap.SimpleEntry<Integer, Integer> CelaActual;
    private int columna;
    private int fila;
    private ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> LastBusca;
    
    private String FullActual;


    public VistaPrincipal(String title, CtrlPresentacio cp) throws Exception {
        super(title);

        CelaActual = null;

        FullActual = "Full 1";
        String[] nomColumnes = new String[cp.GetColumnes(FullActual)];
        String[] nomFiles = new String[cp.GetFiles( FullActual)];
        for (int i = 0; i < nomColumnes.length; i++) {
            nomColumnes[i] = String.valueOf(i + 1);
        }

        String[][] data = cp.MostrarLlista( FullActual);


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
        JMenu fulls = new JMenu("Fulls");
        JMenuItem afegirFull = new JMenuItem("Afegir full");
        JMenuItem eliminarFull = new JMenuItem("Eliminar full");
        JMenuItem canvifull = new JMenuItem("Canviar full");
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

        fulls.add(afegirFull);
        fulls.add(eliminarFull);
        fulls.add(canvifull);
        menuBar.add(fulls);

        super.setIconImage(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/App_Logo.png"))).getImage());
        IncrementarButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Incrementar.png"))));
        reduirButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Reduir.png"))));
        valorAbsolutButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Valor_Absolut.png"))));
        potenciaButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Potencia.png"))));
        arrelButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Arrel.png"))));
        numeroDecimalsButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Decimals.png"))));
        canviarTipusNumeroButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Canviar.png"))));
        conversioButton.setIcon(new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("main/CapaPresentacio/Icons/Conversio.png"))));
        buscarButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/search.svg",22,22));
        cancelButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/cancel.svg",22,22));
        remplaçaButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/replace.svg",24,24));
        pie.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/pieChart.svg",22,22));
        Histograma.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/barChart.svg",22,22));
        LinearButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/lineChart.svg",22,22));
        afegirColumnaButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/addCol.svg",28,28));
        eliminarColumnaButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/deleteCol.svg",28,28));
        afegirFilaButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/addRow.svg",28,28));
        eliminarFilaButton.setIcon(new FlatSVGIcon("main/CapaPresentacio/Icons/deleteRow.svg",28,28));

        NomFull.setText(FullActual);

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1000, 600));
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

                ArrayList<String> llistaFulls = cp.GetFulls();
                FullActual = llistaFulls.get(0);
                NomFull.setText(FullActual);
                String[][] temp;
                temp = cp.MostrarLlista( FullActual);
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes( FullActual)];

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
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes( FullActual)];

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
                    cp.exportarCSV(fileName, path, FullActual);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else System.out.println("Cancelat");
        });

        afegirFull.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Introdueixi el nom del nou full", "Nou Full", JOptionPane.INFORMATION_MESSAGE);
            ArrayList<String> llistaFulls = cp.GetFulls();
            if (nom == null || llistaFulls.contains(nom)) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Dos fulls no poden tenir el mateix nom.\nIntrodueixi un altre nom", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                cp.CrearNouFull(nom, 25, 25);
                FullActual = nom;
                String [][] temp = cp.MostrarLlista(FullActual);
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes(FullActual)];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
                NomFull.setText(FullActual);
            }
        });

        eliminarFull.addActionListener(e -> {
            int opt = JOptionPane.showConfirmDialog(this, "Estas segur que vols eliminar el full?", "Eliminar Full", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                cp.EliminarFull(FullActual);
                ArrayList<String> llistaFulls = cp.GetFulls();
                FullActual = llistaFulls.get(0);
                String [][] temp = cp.MostrarLlista(FullActual);
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes(FullActual)];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
                NomFull.setText(FullActual);
            }
        });

        canvifull.addActionListener(e -> {
            ArrayList<String> llistaFulls = cp.GetFulls();
            if (llistaFulls.size() < 2) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Només tens un full.\nCrea un de nou per poder canviar entre ells.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                llistaFulls.remove(FullActual);
                String[] lf = llistaFulls.toArray(new String[0]);
                int opt = JOptionPane.showOptionDialog(this, "Esculli el full mostrar", "Canvi de full", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, lf, null);
                FullActual = llistaFulls.get(opt);
                String[][] temp = cp.MostrarLlista(FullActual);
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes(FullActual)];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
                NomFull.setText(FullActual);
            }
        });

        Full.getModel().addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE && !modificat.get() && !dataVector.get()) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                String mod = Full.getValueAt(row, col).toString().trim();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(row, col);
                modificat.set(true);
                try {
                    //System.out.println(mod);
                    cp.ModificarContingutCela( FullActual, id, mod);
                    String[][] temp = cp.MostrarLlista( FullActual);
                    //System.out.println(temp[row][col]);
                    String obj = temp[row][col];
                    String content = cp.ValorTotal( FullActual, id);
                    String type = cp.GetTipusCela( FullActual, id);
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                    String[] nomCol = new String[cp.GetColumnes( FullActual)];
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
                    String content = cp.ValorTotal( FullActual, CelaActual);
                    String type = cp.GetTipusCela( FullActual, CelaActual);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    System.out.println(content);
                }
            }
        });


        IncrementarButton.addActionListener(e -> {
            Incrementar(cp);
        });


        dateTextButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
                try {
                    if (!cp.transformaText( FullActual, id))
                        JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog", JOptionPane.ERROR_MESSAGE);
                    else {
                        String[][] temp = new String[0][];
                        try {
                            temp = cp.MostrarLlista( FullActual);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(temp[fila][columna]);
                        String obj = temp[fila][columna];
                        String content = cp.ValorTotal( FullActual, id);
                        String type = cp.GetTipusCela( FullActual, id);
                        Tipus.setText(type);
                        Contingut.setText(content);
                        Full.setValueAt(obj, fila, columna);
                        Full.repaint();
                    }
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        textDateButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
                try {
                    if (!cp.transformaData( FullActual, id))
                        JOptionPane.showMessageDialog(new JFrame(), "La Cela no es de tipus Data", "Dialog", JOptionPane.ERROR_MESSAGE);
                    else {

                        String[][] temp = new String[0][];
                        try {
                            temp = cp.MostrarLlista( FullActual);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(temp[fila][columna]);
                        String obj = temp[fila][columna];
                        String content = cp.ValorTotal( FullActual, id);
                        String type = cp.GetTipusCela( FullActual, id);
                        Tipus.setText(type);
                        Contingut.setText(content);
                        Full.setValueAt(obj, fila, columna);
                        Full.repaint();
                    }
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        minusculesButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "text")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Text", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {

                System.out.println(fila + " " + columna);
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
                try {
                    cp.AllMinus( FullActual, id);
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                String[][] temp = new String[0][];
                try {
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(temp[fila][columna]);
                String obj = temp[fila][columna];
                String content = cp.ValorTotal( FullActual, id);
                String type = cp.GetTipusCela( FullActual, id);
                Tipus.setText(type);
                Contingut.setText(content);
                Full.setValueAt(obj, fila, columna);
                Full.repaint();
            }
        });

        majusculesButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "text")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Text", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {

                System.out.println(fila + " " + columna);
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(fila, columna);
                try {
                    cp.AllMayus( FullActual, id);
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                String[][] temp = new String[0][];
                try {
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(temp[fila][columna]);
                String obj = temp[fila][columna];
                String content = cp.ValorTotal( FullActual, id);
                String type = cp.GetTipusCela( FullActual, id);
                Tipus.setText(type);
                Contingut.setText(content);
                Full.setValueAt(obj, fila, columna);
                Full.repaint();
            }
        });

        DDButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String content = cp.getDia( FullActual, CelaActual);
                if (Objects.equals(content, "null")) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    Resultat.setText("Dia " + content);
                }
            }
        });

        MMButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.getMes( FullActual, CelaActual);
                if (Objects.equals(content, "null")) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    Resultat.setText("Mes " + content);
                }
            }
        });

        AAAAButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.getAny( FullActual, CelaActual);
                if (Objects.equals(content, "null")) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    Resultat.setText("Any " + content);
                }
            }
        });

        diaSemanalButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "data")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.getWeekday( FullActual, CelaActual);
                if (Objects.equals(content, "null")) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és una Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Resultat.setText("Dia de la setmana " + content);
                }
            }
        });

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
            Col2.add(new JLabel("Fila Final :"));
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
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!col.equals(col2) && rowI < rowF){
                    CategoryChart chart = null;
                    try {
                        System.out.println("hey");
                        chart = cp.Histograma( FullActual, col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        CategoryChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

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
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!col.equals(col2) && rowI < rowF){
                    PieChart chart = null;
                    try {
                        System.out.println("hey");
                        chart = cp.PieChart( FullActual, col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        System.out.println(2);
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        PieChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        LinearButton.addActionListener(e -> {

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
                    JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(col != col2 && rowI < rowF){
                    XYChart chart = null;
                    try {
                        chart = cp.LinearChart( FullActual, col-1,rowI-1,rowF-1,col2-1,rowI2-1,rowF2-1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (Objects.equals(chart, null))
                        JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        chart.getStyler().setCursorEnabled(true);
                        XYChart finalChart = chart;
                        Thread t = new Thread(() -> new SwingWrapper(finalChart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
                        t.start();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "El Format de les cel·les no es correcte", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        reduirButton.addActionListener(e -> {
            Reduir(cp);
        });

        valorAbsolutButton.addActionListener(e -> {
            ValorAbsolut(cp);
        });

        potenciaButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
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
                            codi = cp.CalculaPotenciaIReemplaca( FullActual, CelaActual, exp, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal( FullActual, CelaRemp);
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
                        int codi = cp.CalculaPotencia( FullActual, CelaActual, exp);
                        if (codi == 0) {
                            String temp = cp.ValorTotal( FullActual, CelaActual);
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
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
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
                            codi = cp.CalculaArrelIReemplaca( FullActual, CelaActual, exp, CelaRemp);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if (codi == 0) {
                            String temp = cp.ValorTotal( FullActual, CelaRemp);
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
                        codi = cp.CalculaArrel( FullActual, CelaActual, exp);
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Un error ha ocorregut to", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (codi == 0) {
                            String temp = cp.ValorTotal( FullActual, CelaActual);
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
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                DecimalsDialog d = new DecimalsDialog(CelaActual, cp, Full);
                d.setLocationRelativeTo(this);
                d.setVisible(true);

            }
        });

        conversioButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (Objects.equals(cp.GetTipusNumero( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada ha de ser d'un altre tipus de número \nConsulti el manual per més informació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                ConversioDialog c = new ConversioDialog(CelaActual, cp, Full);
                c.setLocationRelativeTo(this);
                c.setVisible(true);

            }
        });
        canviarTipusNumeroButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Objects.equals(cp.GetTipusCela( FullActual, CelaActual), "numero")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Object[] tipus = {"numero", "celsius", "fahrenheit", "kelvin", "km", "m", "cm", "mm", "miles", "yards", "feet", "inches", "graus", "radiants"};
                JComboBox comboBox = new JComboBox(tipus);
                JOptionPane.showMessageDialog(this, comboBox, "Tipus Numero", JOptionPane.QUESTION_MESSAGE);
                try {
                    cp.CanviarTipusNumero( FullActual, CelaActual, Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        afegirColumnaButton.addActionListener(e -> {
            String num = JOptionPane.showInputDialog(this, "Escrigui la posicio on vol afegir la nova columna", "Afegir Fila", JOptionPane.QUESTION_MESSAGE);

            if (!(num == null)) {
                if (PublicFuntions.isNum(num)) {
                    int colActual = cp.GetColumnes( FullActual);
                    String[] nomCol = new String[cp.GetColumnes( FullActual)];
                    if (Integer.parseInt(num) <= 0 || Integer.parseInt(num)> nomCol.length+1 ){
                        JOptionPane.showMessageDialog(this, "La columna no pertany al full. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        cp.AfegirCol( FullActual, Integer.parseInt(num) - 1);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    dataVector.set(true);
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                    String[] dataFilaNova = new String[colActual];
                    Arrays.fill(dataFilaNova, " ");
                    dtm.addRow(dataFilaNova);

                    nomCol = new String[cp.GetColumnes( FullActual)];
                    for (int i = 0; i < nomCol.length; i++) {
                        nomCol[i] = String.valueOf(i + 1);
                    }
                    String[][] dades;
                    try {
                        dades = cp.MostrarLlista( FullActual);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    dtm.setDataVector(dades, nomCol);
                    dataVector.set(false);
                    Full.repaint();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Escrigui un número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        afegirFilaButton.addActionListener(e -> {
            String num = JOptionPane.showInputDialog(this, "Escrigui la posicio on vol afegir la nova fila", "Afegir Fila", JOptionPane.QUESTION_MESSAGE);

            if (!(num == null)) {
                if (PublicFuntions.isNum(num)) {
                    String[] nomF = new String[cp.GetFiles( FullActual)];
                    if (Integer.parseInt(num) <= 0 || Integer.parseInt(num)> nomF.length+1 ){
                        JOptionPane.showMessageDialog(this, "La fila no pertany al full. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int colActual = cp.GetColumnes( FullActual);
                    try {
                        cp.AfegirFila( FullActual, Integer.parseInt(num) - 1);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    dataVector.set(true);
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                    String[] dataFilaNova = new String[colActual];
                    Arrays.fill(dataFilaNova, " ");
                    dtm.addRow(dataFilaNova);

                    String[] nomCol = new String[cp.GetColumnes( FullActual)];
                    for (int i = 0; i < nomCol.length; i++) {
                        nomCol[i] = String.valueOf(i + 1);
                    }
                    String[][] dades;
                    try {
                        dades = cp.MostrarLlista( FullActual);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    dtm.setDataVector(dades, nomCol);
                    dataVector.set(false);
                    Full.repaint();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Escrigui un número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        eliminarColumnaButton.addActionListener(e -> {
            String num = JOptionPane.showInputDialog(this, "Escrigui la posicio de la columna que vols esborrar", "Afegir Fila", JOptionPane.QUESTION_MESSAGE);

            if (!(num == null)) {
                if (PublicFuntions.isNum(num)) {
                    String[] nomCol = new String[cp.GetColumnes( FullActual)];
                    if (Integer.parseInt(num) <= 0 || Integer.parseInt(num)> nomCol.length ){
                        JOptionPane.showMessageDialog(this, "La columna no pertany al full. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        cp.EliminarCol( FullActual, Integer.parseInt(num) - 1);
                    } catch (Exception ex) {
                        System.out.println(ex);
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Ha sorgit un error en afegir una Columna. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[][] temp;
                    try {
                        temp = cp.MostrarLlista( FullActual);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                     nomCol = new String[cp.GetColumnes( FullActual)];

                    for (int i = 0; i < nomCol.length; i++) {
                        nomCol[i] = String.valueOf(i + 1);
                    }
                    dataVector.set(true);
                    dtm.setDataVector(temp, nomCol);
                    dataVector.set(false);
                    Full.repaint();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Escrigui un número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarFilaButton.addActionListener(e -> {
            String num = JOptionPane.showInputDialog(this, "Escrigui la posicio de la fila que vol eliminar", "Afegir Fila", JOptionPane.QUESTION_MESSAGE);

            if (!(num == null)) {
                if (PublicFuntions.isNum(num)) {
                    int colActual = cp.GetColumnes( FullActual);
                    String[] nomF = new String[cp.GetFiles( FullActual)];
                    if (Integer.parseInt(num) <= 0 || Integer.parseInt(num)> nomF.length ){
                        JOptionPane.showMessageDialog(this, "La fila no pertany al full. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        cp.EliminarFila( FullActual, Integer.parseInt(num) - 1);
                    } catch (Exception ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "Ha sorgit un error en afegir una Columna. \n Torni a intentar-ho", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String[][] temp;
                    try {
                        temp = cp.MostrarLlista( FullActual);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                    String[] nomCol = new String[cp.GetColumnes( FullActual)];

                    for (int i = 0; i < nomCol.length; i++) {
                        nomCol[i] = String.valueOf(i + 1);
                    }
                    dataVector.set(true);
                    dtm.setDataVector(temp, nomCol);
                    dataVector.set(false);
                    Full.repaint();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Escrigui un número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Copia.addActionListener(e -> {
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

            Text.add(new JLabel("Bloc a copiar"));
            myPanel.add(Text);
            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna incial:"));
            Col1.add(colField1);

            Col1.add(new JLabel("Columna final:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            Col2.add(Box.createRigidArea(new Dimension(22, 0)));
            Col2.add(new JLabel("Fila inicial:"));
            Col2.add(rowIniField2);
            Col2.add(Box.createRigidArea(new Dimension(25, 0)));
            Col2.add(new JLabel("Fila final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);
            myPanel.add(Box.createVerticalStrut(15));
            Text2.add(new JLabel("Bloc desti"));
            myPanel.add(Text2);
            Col3.add(new JLabel("Columna inicial:"));
            Col3.add(colField3);
            Col3.add(new JLabel("Columna final:"));
            Col3.add(colField33);
            myPanel.add(Col3);

            Col4.add(Box.createRigidArea(new Dimension(25, 0)));
            Col4.add(new JLabel("Fila inicial:"));
            Col4.add(rowIniField4);
            Col4.add(Box.createRigidArea(new Dimension(25, 0)));
            Col4.add(new JLabel("Fila final:"));
            Col4.add(rowFiField4);
            myPanel.add(Col4);


            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Copia", JOptionPane.OK_CANCEL_OPTION);
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
                    JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] nomC = new String[cp.GetColumnes(FullActual)];
                String[] nomf = new String[cp.GetFiles( FullActual)];
                int cols = nomC.length;
                int fils=nomf.length;
                if(col>col2 || rowI2 > rowF || Fcolin > Fcoldest || Frowin > Frowdest || ((col2-col)-(Fcoldest-Fcolin))!=0 || ((rowF-rowI2)-(Frowdest-Frowin))!=0
                        || col-1 < 0|| col2 -1 < 0|| rowF-1 < 0 || rowI2-1 < 0|| Fcoldest-1 < 0|| Frowdest-1 < 0|| Fcolin-1 < 0||Frowin-1< 0
                        || col-1 >= cols|| col2 -1 >= cols|| rowF-1 >= fils || rowI2-1 >= fils|| Fcoldest-1 >= cols|| Frowdest-1 >= fils|| Fcolin-1 >= cols||Frowin-1>= fils){
                    JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int a;
                    try {
                        cp.Copiar( FullActual,new AbstractMap.SimpleEntry<Integer,Integer>(rowI2-1,col-1),new AbstractMap.SimpleEntry<Integer,Integer>(rowF-1,col2-1)
                                ,new AbstractMap.SimpleEntry<Integer,Integer>(Frowin-1,Fcolin-1),new AbstractMap.SimpleEntry<Integer,Integer>(Frowdest-1,Fcoldest-1));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                String[][] temp;
                try {
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes( FullActual)];

                for (int i = 0; i < nomCol.length; i++) {
                    nomCol[i] = String.valueOf(i + 1);
                }
                dataVector.set(true);
                dtm.setDataVector(temp, nomCol);
                dataVector.set(false);
                Full.repaint();
            }
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

            Col2.add(Box.createRigidArea(new Dimension(22, 0)));
            Col2.add(new JLabel("Fila inicial:"));
            Col2.add(rowIniField2);
            Col2.add(Box.createRigidArea(new Dimension(25, 0)));
            Col2.add(new JLabel("Fila final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);
            myPanel.add(Box.createVerticalStrut(15));
            Col3.add(new JLabel("Columna inicial:"));
            Col3.add(colField3);
            Col3.add(new JLabel("Columna final:"));
            Col3.add(colField33);
            myPanel.add(Col3);

            Col4.add(Box.createRigidArea(new Dimension(25, 0)));
            Col4.add(new JLabel("Fila inicial:"));
            Col4.add(rowIniField4);
            Col4.add(Box.createRigidArea(new Dimension(25, 0)));
            Col4.add(new JLabel("Fila final:"));
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
                    JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Double d=0.0;
                if ( ! ListaOps.getItemAt(ListaOps.getSelectedIndex()).toString().equals("min") && ! ListaOps.getItemAt(ListaOps.getSelectedIndex()).toString().equals("may")) {
                    try {
                        d= Double.parseDouble(cont.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "El operador no és un numero vàlid", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                String[] nomC = new String[cp.GetColumnes(FullActual)];
                String[] nomf = new String[cp.GetFiles( FullActual)];
                int cols = nomC.length;
                int fils=nomf.length;
                if(col>col2 || rowI2 > rowF || Fcolin > Fcoldest || Frowin > Frowdest || ((col2-col)-(Fcoldest-Fcolin))!=0 || ((rowF-rowI2)-(Frowdest-Frowin))!=0
                        || col-1 < 0|| col2 -1 < 0|| rowF-1 < 0 || rowI2-1 < 0|| Fcoldest-1 < 0|| Frowdest-1 < 0|| Fcolin-1 < 0||Frowin-1< 0
                        || col-1 >= cols|| col2 -1 >= cols|| rowF-1 >= fils || rowI2-1 >= fils|| Fcoldest-1 >= cols|| Frowdest-1 >= fils|| Fcolin-1 >= cols||Frowin-1>= fils){
                    JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son vàlids", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int a;
                    try {
                        a= cp.Opera_bloc( FullActual,new AbstractMap.SimpleEntry<Integer,Integer>(rowI2-1,col-1),new AbstractMap.SimpleEntry<Integer,Integer>(rowF-1,col2-1)
                                ,new AbstractMap.SimpleEntry<Integer,Integer>(Frowin-1,Fcolin-1),new AbstractMap.SimpleEntry<Integer,Integer>(Frowdest-1,Fcoldest-1), ListaOps.getItemAt(ListaOps.getSelectedIndex()).toString(),d);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    if (a==-1) JOptionPane.showMessageDialog(new JFrame(), "Els blocs no son del tipus correcte", "Error", JOptionPane.ERROR_MESSAGE);
                }
                String[][] temp;
                try {
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes( FullActual)];

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

        ordenaBlocButton.addActionListener(e -> {
            JTextField colField1 = new JTextField();
            JTextField rowIniField2 = new JTextField();
            JTextField rowFiField2 = new JTextField();
            JTextField colField2 = new JTextField();
            JPanel myPanel = new JPanel();
            JTextField cont = new JTextField();
            JPanel Text = new JPanel();
            JPanel Text2 = new JPanel();
            JPanel Col1 = new JPanel();
            JPanel Col2 = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            Text.add(new JLabel("Bloc a ordenar"));
            myPanel.add(Text);
            String[] optionsToChoose = {"Major-menor", "Menor-major"};
            ListaOps = new JComboBox<>(optionsToChoose);
            myPanel.add(ListaOps);

            myPanel.add(Box.createVerticalStrut(15));
            Col1.add(new JLabel("Columna incial:"));
            Col1.add(colField1);

            Col1.add(new JLabel("Columna final:"));
            Col1.add(colField2);
            myPanel.add(Col1);

            Col2.add(Box.createRigidArea(new Dimension(22, 0)));
            Col2.add(new JLabel("Fila inicial:"));
            Col2.add(rowIniField2);
            Col2.add(Box.createRigidArea(new Dimension(25, 0)));
            Col2.add(new JLabel("Fila final:"));
            Col2.add(rowFiField2);
            myPanel.add(Col2);
            myPanel.add(Box.createVerticalStrut(15));
            Text2.add(new JLabel("Ordre de columnes separades per ,"));
            myPanel.add(Text2);
            myPanel.add(cont);



            int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Ordena bloc", JOptionPane.OK_CANCEL_OPTION);
            if (result_2 == JOptionPane.OK_OPTION) {
                Integer rowF,col,rowI2,col2;
                try {
                    col = Integer.parseInt(colField1.getText());
                    rowI2 = Integer.parseInt(rowIniField2.getText());

                    col2 = Integer.parseInt(colField2.getText());
                    rowF = Integer.parseInt(rowFiField2.getText());

                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "El bloc no és vàlid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                if (cont.getText().equals("")) JOptionPane.showMessageDialog(new JFrame(), "Les columnes no s'han introduit correctament", "Error", JOptionPane.ERROR_MESSAGE);


                String[] nomC = new String[cp.GetColumnes(FullActual)];
                String[] nomf = new String[cp.GetFiles( FullActual)];
                int cols = nomC.length;
                int fils=nomf.length;
                if(col>col2 || rowI2 > rowF || col-1 < 0|| col2 -1 < 0|| rowF-1 < 0 || rowI2-1 < 0
                        || col-1 >= cols|| col2 -1 >= cols|| rowF-1 >= fils || rowI2-1 >= fils){
                    JOptionPane.showMessageDialog(new JFrame(), "El bloc no és vàlid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int a;
                    try {
                        Scanner sc =new Scanner(cont.getText());
                        sc.useDelimiter(Pattern.compile(","));
                        ArrayList<Integer>columnas=new ArrayList<>();
                        int c;
                        while (sc.hasNext()){
                            try {
                                c = Integer.parseInt(sc.next());
                            }catch (NumberFormatException e1){
                                JOptionPane.showMessageDialog(new JFrame(), "Les columnes no s'han introduit correctament", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (c>=0 && c<=(col2-col) && !columnas.contains(c)){
                                columnas.add(c);
                            }else JOptionPane.showMessageDialog(new JFrame(), "Les columnes no s'han introduit correctament", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        cp.ordena_bloc(FullActual,new AbstractMap.SimpleEntry<Integer,Integer>(rowI2-1,col-1),new AbstractMap.SimpleEntry<Integer,Integer>(rowF-1,col2-1),columnas,ListaOps.getItemAt(ListaOps.getSelectedIndex()).toString());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                String[][] temp;
                try {
                    temp = cp.MostrarLlista( FullActual);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                String[] nomCol = new String[cp.GetColumnes( FullActual)];

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
        class PaintTableCellRender extends DefaultTableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
                AbstractMap.SimpleEntry<Integer,Integer> id = new AbstractMap.SimpleEntry<>(row,column);
                if (LastBusca.contains(id)) {
                    System.out.println("CELDA" + id);
                    setBackground(color.get());
                    setEnabled(true);
                    setText(cp.ValorTotal( FullActual, id));
                } else {
                    setBackground(Color.WHITE);
                    setText(cp.ValorTotal( FullActual, id));
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
                ArrayList<Cela> r= cp.Busca( FullActual, buscador.getText());
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = new ArrayList<>();
                for(Cela a : r){
                    ids.add(new AbstractMap.SimpleEntry(a.getId().getKey(),a.getId().getValue()));
                }
                LastBusca =  ids;

                for(int i = 0; i < cp.GetColumnes( FullActual);i++){
                    TableColumn col = Full.getColumnModel().getColumn(i);
                    col.setCellRenderer(new PaintTableCellRender());
                    Full.repaint();
                }
                Full.setEnabled(false);
            }
        });
        cancelButton.addActionListener(e -> {
            LastBusca =  new ArrayList<>();

            String[][] temp;
            try {
                temp = cp.MostrarLlista( FullActual);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
            String[] nomCol = new String[cp.GetColumnes( FullActual)];

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
                    ArrayList<Cela> a = cp.BuscarRemp("Full 1", busca, rempla);
                    for (Cela c : a) {
                        int col = c.getId().getValue();
                        int row = c.getId().getKey();
                        AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(row, col);

                        try {
                            String[][] temp = cp.MostrarLlista("Full 1");
                            String content = cp.ValorTotal("Full 1", id);
                            String type = cp.GetTipusCela("Full 1", id);
                            DefaultTableModel dtm = (DefaultTableModel) Full.getModel();
                            String[] nomCol = new String[cp.GetColumnes("Full 1")];

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
        wordsButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.countWords( FullActual, CelaActual);
                Resultat.setText(content);
            }
        });

        vowelsButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.countVowels( FullActual, CelaActual);
                Resultat.setText(content);

            }
        });

        charsButton.addActionListener(e -> {
            if (CelaActual == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println(fila + " " + columna);
                String content = cp.countChars( FullActual, CelaActual);
                Resultat.setText(content);

            }
        });

    }

    private void Incrementar(CtrlPresentacio cp) {
        if (CelaActual == null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!Objects.equals(cp.GetTipusCela(FullActual, CelaActual), "numero")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] opt = {"No Reemplaçar", "Reemplaçar"};
            int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Incrementar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
            if (result == 0) {
                JTextField rowField = new JTextField(5);
                JTextField colField = new JTextField(5);

                int result_2 = PreguntarCela(rowField, colField);

                if (result_2 == JOptionPane.OK_OPTION) {
                    Integer row = Integer.parseInt(rowField.getText());
                    Integer col = Integer.parseInt(colField.getText());
                    AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                    if (cp.ComprovarId(FullActual, CelaRemp)) {
                        if (!cp.ComprovaCelaNoOcupa(FullActual, CelaRemp)) {
                            CalculIncrementRemp(cp, CelaRemp);
                        } else {
                            if (JOptionPane.showConfirmDialog(this, "Aquesta cel·la ja te contingut, vol reemplçar-lo?", "Incrementar", JOptionPane.YES_NO_OPTION) == 0) {
                                CalculIncrementRemp(cp, CelaRemp);
                            } else Incrementar(cp);
                        }
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la introduïda no existeix", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else if (result == 1) {
                try {
                    int codi = cp.CalculaIncrement(FullActual, CelaActual);
                    if (codi == 0) {
                        String temp = cp.ValorTotal(FullActual, CelaActual);
                        Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                        Full.repaint();
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void Reduir(CtrlPresentacio cp) {
        if (CelaActual == null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!Objects.equals(cp.GetTipusCela(FullActual, CelaActual), "numero")){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[] opt = {"No Reemplaçar", "Reemplaçar"};
            int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Reduir", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
            if (result == 0) {
                JTextField rowField = new JTextField(5);
                JTextField colField = new JTextField(5);

                int result_2 = PreguntarCela(rowField, colField);

                if (result_2 == JOptionPane.OK_OPTION) {
                    Integer row = Integer.parseInt(rowField.getText());
                    Integer col = Integer.parseInt(colField.getText());
                    AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                    if (cp.ComprovarId(FullActual, CelaRemp)) {
                        if (!cp.ComprovaCelaNoOcupa(FullActual, CelaRemp)) {
                            CalculReduirRemp(cp, CelaRemp);
                        }
                        else {
                            if (JOptionPane.showConfirmDialog(this, "Aquesta cel·la ja te contingut, vol reemplçar-lo?", "Reduir", JOptionPane.YES_NO_OPTION) == 0) {
                                CalculReduirRemp(cp, CelaRemp);
                            }
                            else Reduir(cp);
                        }
                    }
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la introduïda no existeix", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else if (result == 1) {
                try {
                    int codi = cp.CalculaReduir( FullActual, CelaActual);
                    if (codi == 0) {
                        String temp = cp.ValorTotal( FullActual, CelaActual);
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
    }

    private void ValorAbsolut(CtrlPresentacio cp) {
        if (CelaActual == null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!Objects.equals(cp.GetTipusCela(FullActual, CelaActual), "numero")){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[] opt = {"No Reemplaçar", "Reemplaçar"};
            int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Valor Absolut", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
            if (result == 0) {
                JTextField rowField = new JTextField(5);
                JTextField colField = new JTextField(5);

                int result_2 = PreguntarCela(rowField, colField);

                if (result_2 == JOptionPane.OK_OPTION) {
                    Integer row = Integer.parseInt(rowField.getText());
                    Integer col = Integer.parseInt(colField.getText());
                    AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                    if (cp.ComprovarId(FullActual, CelaRemp)) {
                        if (!cp.ComprovaCelaNoOcupa(FullActual, CelaRemp)) {
                            CalculValorAbsRemp(cp, CelaRemp);
                        }
                        else {
                            if (JOptionPane.showConfirmDialog(this, "Aquesta cel·la ja te contingut, vol reemplçar-lo?", "Valor Absolut", JOptionPane.YES_NO_OPTION) == 0) {
                                CalculValorAbsRemp(cp, CelaRemp);
                            }
                            else ValorAbsolut(cp);
                        }
                    }
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la introduïda no existeix", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else if (result == 1) {
                try {
                    int codi = cp.CalculaValorAbs(FullActual, CelaActual);
                    if (codi == 0) {
                        String temp = cp.ValorTotal( FullActual, CelaActual);
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
    }

    private void Potencia(CtrlPresentacio cp) {
        if (CelaActual == null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Seleccioni una Cela abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!Objects.equals(cp.GetTipusCela(FullActual, CelaActual), "numero")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] opt = {"No Reemplaçar", "Reemplaçar"};
            int result = JOptionPane.showOptionDialog(this, "Vol reemplaçar el contingut o colocar-lo en una altre cela?", "Incrementar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
            if (result == 0) {
                JTextField rowField = new JTextField(5);
                JTextField colField = new JTextField(5);

                int result_2 = PreguntarCela(rowField, colField);

                if (result_2 == JOptionPane.OK_OPTION) {
                    Integer row = Integer.parseInt(rowField.getText());
                    Integer col = Integer.parseInt(colField.getText());
                    AbstractMap.SimpleEntry<Integer, Integer> CelaRemp = new AbstractMap.SimpleEntry<>(row - 1, col - 1);
                    if (cp.ComprovarId(FullActual, CelaRemp)) {
                        if (!cp.ComprovaCelaNoOcupa(FullActual, CelaRemp)) {
                            CalculIncrementRemp(cp, CelaRemp);
                        } else {
                            if (JOptionPane.showConfirmDialog(this, "Aquesta cel·la ja te contingut, vol reemplçar-lo?", "Incrementar", JOptionPane.YES_NO_OPTION) == 0) {
                                CalculIncrementRemp(cp, CelaRemp);
                            } else Incrementar(cp);
                        }
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la introduïda no existeix", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else if (result == 1) {
                try {
                    int codi = cp.CalculaIncrement(FullActual, CelaActual);
                    if (codi == 0) {
                        String temp = cp.ValorTotal(FullActual, CelaActual);
                        Full.setValueAt(temp, CelaActual.getKey(), CelaActual.getValue());
                        Full.repaint();
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Seleccioni una Cel·la abans de fer l'operació", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void CalculIncrementRemp(CtrlPresentacio cp, AbstractMap.SimpleEntry<Integer, Integer> CelaRemp) {
        int codi;
        try {
            codi = cp.CalculaIncrementIReemplaca(FullActual, CelaActual, CelaRemp);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if (codi == 0) {
            String temp = cp.ValorTotal(FullActual, CelaRemp);
            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
            Full.repaint();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CalculReduirRemp(CtrlPresentacio cp, AbstractMap.SimpleEntry<Integer, Integer> CelaRemp) {
        int codi;
        try {
            codi = cp.CalculaReduirIReemplaca(FullActual, CelaActual, CelaRemp);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if (codi == 0) {
            String temp = cp.ValorTotal(FullActual, CelaRemp);
            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
            Full.repaint();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CalculValorAbsRemp(CtrlPresentacio cp, AbstractMap.SimpleEntry<Integer, Integer> CelaRemp) {
        int codi;
        try {
            codi = cp.CalculaValorAbsIReemplaca(FullActual, CelaActual, CelaRemp);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if (codi == 0) {
            String temp = cp.ValorTotal(FullActual, CelaRemp);
            Full.setValueAt(temp, CelaRemp.getKey(), CelaRemp.getValue());
            Full.repaint();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "La cel·la seleccionada no és un Numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int PreguntarCela(JTextField rowField, JTextField colField) {
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Introdueixi la Cel·la on vol col·locar el resultat"));
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Fila:"));
        myPanel.add(rowField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Columna:"));
        myPanel.add(colField);
        int result_2 = JOptionPane.showConfirmDialog(this, myPanel, "Reduir", JOptionPane.OK_CANCEL_OPTION);
        return result_2;
    }

}



