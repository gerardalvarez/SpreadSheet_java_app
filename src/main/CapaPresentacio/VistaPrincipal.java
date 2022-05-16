package main.CapaPresentacio;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private AbstractMap.SimpleEntry<Integer, Integer> CelaActual;

    public VistaPrincipal(String title, CtrlPresentacio cp) throws Exception {
        super(title);

        CelaActual = null;

        String[] nomColumnes = new String[cp.GetColumnes("Doc 1", "Full 1")];

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

        fitxer.add(guardar);
        fitxer.add(obrir);
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

        guardar.addActionListener(e -> {
            JFileChooser savefile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
            savefile.setFileFilter(filter);
            int status = savefile.showSaveDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    cp.guardarDocument();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else System.out.println("Cancelat");
        });

        obrir.addActionListener(e -> {
            JFileChooser openfile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
            openfile.setFileFilter(filter);
            int status = openfile.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    cp.obrirDocument();
                } catch (Exception ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "El fitxer no s'ha pogut obrir", "Error", JOptionPane.ERROR_MESSAGE);
                }

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

                Full.setModel(new DefaultTableModel(dades, nomCol));
                Full.repaint();
            } else System.out.println("Cancelat");
        });


        AtomicBoolean modificat = new AtomicBoolean(false);

        Full.getModel().addTableModelListener(e -> {
            System.out.println(e.getType());

            if (e.getType() == TableModelEvent.UPDATE && !modificat.get()) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                String mod = Full.getValueAt(row, col).toString().trim();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(row, col);
                modificat.set(true);

                try {
                    System.out.println(mod);
                    cp.ModificarContingutCela("Doc 1", "Full 1", id, mod);
                    String[][] temp = cp.MostrarLlista("Doc 1", "Full 1");
                    System.out.println(temp[row][col]);
                    Object obj = temp[row][col];
                    String content = cp.ValorTotal("Doc 1", "Full 1", id);
                    String type = cp.GetTipusCela("Doc 1", "Full 1", id);
                    Tipus.setText(type);
                    Contingut.setText(content);
                    Full.setValueAt(obj, row, col);
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
                int row = Full.rowAtPoint(e.getPoint());
                int col = Full.columnAtPoint(e.getPoint());
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
                cp.CanviarTipusNumero("Doc 1", "Full 1", CelaActual, comboBox.getSelectedItem().toString());
            }
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



