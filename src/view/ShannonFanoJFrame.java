package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import model.ShannonFano;

public class ShannonFanoJFrame extends JFrame {
    
  private JButton jButtonLimpiar;
  private JButton jButtonDecodificar;
  private JButton jButtonCodificar;
  private JLabel jLabelString;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabelAvgCod;
  private JLabel jLabelMensajeCodificado;
  private JLabel jLabelMensajeDecodificado;
  private JLabel jLabelEntropia;
  private JLabel jLabelRedundancia;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanelDecodificacion;
  private JPanel jPanelTablaInicial;
  private JPanel jPanelTablaFinal;
  private JPanel jPanelSuperior;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JTable jTableInicial;
  private JTable jTableFinal;
  private JTextField jTextFieldEntradaString;
  private ShannonFano shannonFanno;
  private DecimalFormat decimalFormat;
  
  //Constructor 
  public ShannonFanoJFrame() {
    this.shannonFanno = null;
    this.decimalFormat = new DecimalFormat("#.#####");
    initComponents();
    this.setLocationRelativeTo(this);
    /*JPanelDecodificacion por defecto no se muestra, al crear el JFrame,
    posteriormente con el evento del JButtonCodificar sera visible*/
    this.jPanelDecodificacion.setVisible(false);
  }
  
  private void initComponents() {
    //Panel superior del JFrame
    this.jPanelSuperior = new JPanel();
    
    //Elementos del panel superior 
    this.jLabelString = new JLabel();
    this.jTextFieldEntradaString = new JTextField();
    this.jButtonCodificar = new JButton();
    this.jButtonLimpiar = new JButton();
    
    //Panel inferior izquierdo 
    this.jPanelTablaInicial = new JPanel();
    //Scroll tabla inicial 
    this.jScrollPane1 = new JScrollPane();
    //Tabla inicial 
    this.jTableInicial = new JTable();
    //Panel inferior derecho 
    this.jPanelTablaFinal = new JPanel();
    //Scroll tabla final 
    this.jScrollPane2 = new JScrollPane();
    //Tabla final 
    this.jTableFinal = new JTable();
    
    /*JPanels para agrupar elementos del JFrame posteriormente
    a trav??s de GroupLayaouts*/
    this.jPanel1 = new JPanel();
    this.jPanel2 = new JPanel();
    
    /*Labels del Jframe principal, clave/valor*/
    /*Entropia*/
    this.jLabel2 = new JLabel();
    this.jLabelEntropia = new JLabel();
    /*Average codeword*/
    this.jLabel3 = new JLabel();
    this.jLabelAvgCod = new JLabel();
    /*Redundancia*/
    this.jLabel4 = new JLabel();
    this.jLabelRedundancia = new JLabel();
    
    /*JPanel inferior del JFrame*/
    this.jPanelDecodificacion = new JPanel();
    this.jLabel5 = new JLabel();
    this.jLabelMensajeCodificado = new JLabel();
    this.jLabelMensajeDecodificado = new JLabel();
    this.jLabel6 = new JLabel();
    this.jButtonDecodificar = new JButton();
    
    /*Configuracion adicional al JFrame*/
    setDefaultCloseOperation(2);
    setTitle("Shannon Fano - Codificaci??n y Decodificaci??n");
    setResizable(false);
    
    /*Texto del JLabel y del JButton: elementos del JPanel superior*/
    this.jLabelString.setText("String");
    this.jButtonCodificar.setText("Codificar");
    this.jButtonCodificar.setBackground(new Color(162,217,206));
    /*Captura el evento del JButtonCodificar*/
    this.jButtonCodificar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            ShannonFanoJFrame.this.jButtonEncodeActionPerformed(evt);
          }
        });
    
    /*Texto del JButton y captura del evento*/
    this.jButtonLimpiar.setText("Limpiar");
    this.jButtonLimpiar.setBackground(new Color(162,217,206));
    this.jButtonLimpiar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            ShannonFanoJFrame.this.jButtonClearActionPerformed(evt);
          }
        });
    
    /*GroupLayaout para el JPanel superior de la ventana: JLabel, JTextField, JButtonEncode, JButtonClear*/
    GroupLayout jPanelInputStringLayout = new GroupLayout(this.jPanelSuperior);
    this.jPanelSuperior.setLayout(jPanelInputStringLayout);
    jPanelInputStringLayout.setHorizontalGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelInputStringLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabelString).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextFieldEntradaString, -2, 279, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonCodificar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonLimpiar).addContainerGap(-1, 32767)));
    jPanelInputStringLayout.setVerticalGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelInputStringLayout.createSequentialGroup().addContainerGap().addGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelString).addComponent(this.jTextFieldEntradaString, -2, -1, -2).addComponent(this.jButtonCodificar).addComponent(this.jButtonLimpiar)).addContainerGap(-1, 32767)));
    
    //JPanel izquierdo que contendr?? la tabla inicial 
    this.jPanelTablaInicial.setBorder(BorderFactory.createTitledBorder("Tabla Inicial"));
    this.jPanelTablaInicial.setPreferredSize(new Dimension(441, 351));
    
    /*Esquema tabla inicial*/
    this.jTableInicial.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[] { "Simbolo (Si)", "Frecuencia (Fi)", "Probabilidad (Pi)" }) {
          Class[] types = new Class[] { String.class, String.class, String.class };
          
          boolean[] canEdit = new boolean[] { false, false, false };
          
          public Class getColumnClass(int columnIndex) {
            return this.types[columnIndex];
          }
          
          public boolean isCellEditable(int rowIndex, int columnIndex) {
            return this.canEdit[columnIndex];
          }
        });
    
    this.jScrollPane1.setViewportView(this.jTableInicial);
    this.jTableInicial.getColumnModel().getColumn(0).setResizable(false);
    this.jTableInicial.getColumnModel().getColumn(1).setResizable(false);
    this.jTableInicial.getColumnModel().getColumn(2).setResizable(false);
    
    /*GroupLayaout para la tabla inicial, con el scroll respectivo*/
    GroupLayout jPanelTableIntLayout = new GroupLayout(this.jPanelTablaInicial);
    this.jPanelTablaInicial.setLayout(jPanelTableIntLayout);
    jPanelTableIntLayout.setHorizontalGroup(jPanelTableIntLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelTableIntLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 409, 32767).addContainerGap()));
    jPanelTableIntLayout.setVerticalGroup(jPanelTableIntLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelTableIntLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 334, 32767).addContainerGap()));
    
    /*JPanel tabla final y su configuraci??n*/
    this.jPanelTablaFinal.setBorder(BorderFactory.createTitledBorder("Tabla Final"));
    this.jPanelTablaFinal.setPreferredSize(new Dimension(441, 351));
    this.jTableFinal.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[] { "Si", "Fi", "Pi", "Codeword", "NBits" }) {
          Class[] types = new Class[] { String.class, String.class, String.class, String.class, String.class };
          
          public Class getColumnClass(int columnIndex) {
            return this.types[columnIndex];
          }
        });
    this.jScrollPane2.setViewportView(this.jTableFinal);
    
    /*GroupLayaout para el JPanel de la tabla final*/
    GroupLayout jPanelFinalTableLayout = new GroupLayout(this.jPanelTablaFinal);
    this.jPanelTablaFinal.setLayout(jPanelFinalTableLayout);
    jPanelFinalTableLayout.setHorizontalGroup(jPanelFinalTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelFinalTableLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 409, 32767).addContainerGap()));
    jPanelFinalTableLayout.setVerticalGroup(jPanelFinalTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelFinalTableLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 334, 32767).addContainerGap()));
    
   
    /*Elementos del panel inferior del JFrame*/
    this.jLabel5.setText("Mensaje Codificado");
    this.jLabelMensajeCodificado.setText(" ");
    this.jLabelMensajeDecodificado.setText(" ");
    this.jLabel6.setText("Mensaje Decodificado");
    this.jButtonDecodificar.setText("Decodificar");
    this.jButtonDecodificar.setBackground(new Color(88, 214, 141));
    this.jButtonDecodificar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            ShannonFanoJFrame.this.jButtonDecodeActionPerformed(evt);
          }
        });
    
    /*GroupLayaout del JPanel inferior del JFrame, con sus elementos*/
    GroupLayout jPanelDecodeLayout = new GroupLayout(this.jPanelDecodificacion);
    this.jPanelDecodificacion.setLayout(jPanelDecodeLayout);
    jPanelDecodeLayout.setHorizontalGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addComponent(this.jLabel5).addGap(365, 365, 365).addComponent(this.jButtonDecodificar)).addComponent(this.jLabelMensajeCodificado, -2, 416, -2)).addGap(49, 49, 49).addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel6).addComponent(this.jLabelMensajeDecodificado, -1, -1, 32767)).addContainerGap()));
    jPanelDecodeLayout.setVerticalGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jButtonDecodificar).addComponent(this.jLabel6)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelMensajeCodificado).addComponent(this.jLabelMensajeDecodificado)).addContainerGap(15, 32767)));
    this.jLabelMensajeCodificado.getAccessibleContext().setAccessibleName("");
    this.jLabelMensajeDecodificado.getAccessibleContext().setAccessibleName("");
    
    /*GroupLayaout de todos los elementos del JFrame: JPanels, JLabels, etc*/
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    getContentPane().setBackground(new Color(51,204,255));
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.jPanelTablaInicial, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addGap(12, 12, 12).addComponent(this.jPanelTablaFinal, -2, -1, -2)).addComponent(this.jPanelSuperior, GroupLayout.Alignment.LEADING, -2, -1, -2).addComponent(this.jPanelDecodificacion, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap(-1, 32767)));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanelSuperior, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanelTablaInicial, GroupLayout.Alignment.LEADING, -1, 374, 32767).addComponent(this.jPanelTablaFinal, GroupLayout.Alignment.LEADING, -1, 374, 32767).addComponent(this.jPanel1, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelDecodificacion, -2, -1, -2).addContainerGap(-1, 32767)));
    pack();
  }
  
  /*Codifica el mensaje ingresado, calculando los atributos adicionales*/
  private void jButtonEncodeActionPerformed(ActionEvent evt) {
    String message = this.jTextFieldEntradaString.getText();
    //Validacion
    if (message.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese un mensaje a codificar");
    } else {
        
      /*Limpia las tablas previamente*/  
      limpiarTableIni(this.jTableInicial);
      limpiarTableFinal(this.jTableFinal);
      
      //Envia el JFrame a la clase ShannonFano que realiza la codificacion y decodificacion*/
      this.shannonFanno = new ShannonFano();
      this.shannonFanno.setFrame(this);
      this.shannonFanno.run(message);
      this.shannonFanno.writeTable();
      
  
      /*Muestra el JPanel inferior con la codificacion*/
      this.jPanelDecodificacion.setVisible(true);
      this.jLabelMensajeDecodificado.setText("");
      /*Calcula la codificacion del mensaje*/
      this.jLabelMensajeCodificado.setText(this.shannonFanno.compress(message)); 
    } 
  }
  
  /*Realiza la decodificaci??n del mensaje codificado y la muestra en el respectivo JLabel*/
  private void jButtonDecodeActionPerformed(ActionEvent evt) {
    this.jLabelMensajeDecodificado.setText(this.shannonFanno.decodificar(this.jLabelMensajeCodificado.getText()));
  }
  
  /*Inicializa todo el contenido del frame en vacio*/
  private void jButtonClearActionPerformed(ActionEvent evt) {
    this.shannonFanno = null;
    this.jLabelMensajeCodificado.setText("");
    this.jLabelMensajeDecodificado.setText("");
    this.jPanelDecodificacion.setVisible(false);
    this.jTextFieldEntradaString.setText("");
    limpiarTableIni(this.jTableInicial);
    limpiarTableFinal(this.jTableFinal);
    
  }
  
  /*M??todos get para cada tabla */
  public JTable getjTableInicial() {
    return this.jTableInicial;
  }
  
  public JTable getjTableFinal() {
    return this.jTableFinal;
  }
  
  /*limpiar contenido de cada tabla*/
  public void limpiarTableIni(JTable t) {
    DefaultTableModel dtm = new DefaultTableModel(new Object[0][], (Object[])new String[] { "Simbolo (Si)", "Frecuencia (Fi)", "Probabilidad (Pi)" });
    t.setModel(dtm);
  }
  
  public void limpiarTableFinal(JTable t) {
    DefaultTableModel dtm = new DefaultTableModel(new Object[0][], (Object[])new String[] { "Si", "Fi", "Pi", "Codeword", "NBits" });
    t.setModel(dtm);
  }
  
  /*-------METODO MAIN-------*/
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new ShannonFanoJFrame()).setVisible(true);
          }
        });
  }
}
