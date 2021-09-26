package multimedia2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;

public class MainJFrame extends JFrame {
  private JButton jButton2;
  
  private JButton jButton3;
  
  private JButton jButtonHF;
  
  private JButton jButtonSF;
  
  private JDialog jDialog1;
  
  private JLabel jLabel1;
  
  private JPanel jPanel1;
  
  private JPanel jPanel2;
  
  private JScrollPane jScrollPane2;
  
  private JScrollPane jScrollPane3;
  
  private JTextArea jTextArea2;
  
  private JTextArea jTextArea3;
  
  public MainJFrame() {
    initComponents();
    this.jDialog1.setVisible(false);
  }
  
  private void initComponents() {
    this.jDialog1 = new JDialog();
    this.jButton3 = new JButton();
    this.jScrollPane2 = new JScrollPane();
    this.jTextArea2 = new JTextArea();
    this.jScrollPane3 = new JScrollPane();
    this.jTextArea3 = new JTextArea();
    this.jPanel1 = new JPanel();
    this.jButtonHF = new JButton();
    this.jButtonSF = new JButton();
    this.jPanel2 = new JPanel();
    this.jButton2 = new JButton();
    this.jLabel1 = new JLabel();
    this.jDialog1.setTitle("Cr");
    this.jDialog1.setMinimumSize(new Dimension(397, 270));
    this.jDialog1.setResizable(false);
  //  this.jButton3.setIcon(new ImageIcon(getClass().getResource("/multimedia2/logo_jpg_cor1.jpg")));
    this.jButton3.setBorderPainted(false);
    this.jButton3.setFocusable(false);
    this.jButton3.setRequestFocusEnabled(false);
    this.jButton3.setRolloverEnabled(false);
    this.jButton3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            MainJFrame.this.jButton3ActionPerformed(evt);
          }
        });
    this.jTextArea2.setBackground(new Color(242, 241, 241));
    this.jTextArea2.setColumns(20);
    this.jTextArea2.setEditable(false);
    this.jTextArea2.setFont(new Font("Verdana", 0, 13));
    this.jTextArea2.setLineWrap(true);
    this.jTextArea2.setRows(5);
    this.jTextArea2.setText("Trabalho realizado pelos Alunos:\n- JoTim22361\n- Rui Barbosa, 20632\n- Rui Santos, 23294");
    this.jTextArea2.setBorder((Border)null);
    this.jTextArea2.setMargin(new Insets(0, 0, 0, 0));
    this.jScrollPane2.setViewportView(this.jTextArea2);
    this.jScrollPane3.setBorder((Border)null);
    this.jTextArea3.setBackground(new Color(242, 241, 241));
    this.jTextArea3.setColumns(20);
    this.jTextArea3.setEditable(false);
    this.jTextArea3.setFont(new Font("Verdana", 0, 13));
    this.jTextArea3.setLineWrap(true);
    this.jTextArea3.setRows(5);
    this.jTextArea3.setText("Trabalho realizado no da unidade curricular de MultimII, leccionada pelo Prof. Doutor Nuno MagalhRibeiro, do curso de Engenharia Informda Faculdade de Cie Tecnologia da Universidade Fernando Pessoa");
    this.jTextArea3.setWrapStyleWord(true);
    this.jTextArea3.setAutoscrolls(false);
    this.jTextArea3.setBorder((Border)null);
    this.jScrollPane3.setViewportView(this.jTextArea3);
    GroupLayout jDialog1Layout = new GroupLayout(this.jDialog1.getContentPane());
    this.jDialog1.getContentPane().setLayout(jDialog1Layout);
    jDialog1Layout.setHorizontalGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jDialog1Layout.createSequentialGroup().addContainerGap().addGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jDialog1Layout.createSequentialGroup().addComponent(this.jButton3, -2, 109, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane2, -2, 248, -2)).addComponent(this.jScrollPane3)).addContainerGap(20, 32767)));
    jDialog1Layout.setVerticalGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jDialog1Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton3).addGroup(jDialog1Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.jScrollPane2, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane3, -2, 100, -2)));
    setDefaultCloseOperation(3);
    setTitle("DemonstraPrdos Algoritmos Huffman & Shanon-Fano");
    setResizable(false);
    this.jButtonHF.setText("Huffman");
    this.jButtonHF.setMaximumSize(new Dimension(101, 23));
    this.jButtonHF.setMinimumSize(new Dimension(101, 23));
    this.jButtonHF.setPreferredSize(new Dimension(101, 23));
    this.jButtonHF.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            MainJFrame.this.jButtonHFActionPerformed(evt);
          }
        });
    this.jButtonSF.setText("Shannon Fano");
    this.jButtonSF.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            MainJFrame.this.jButtonSFActionPerformed(evt);
          }
        });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jButtonHF, -2, 136, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, 32767).addComponent(this.jButtonSF, -2, 136, -2).addGap(33, 33, 33)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButtonSF, -2, 42, -2).addComponent(this.jButtonHF, -2, 42, -2)));
//    this.jButton2.setIcon(new ImageIcon(getClass().getResource("/multimedia2/logo_jpg_cor1.jpg")));
    this.jButton2.setBorderPainted(false);
    this.jButton2.setFocusable(false);
    this.jButton2.setRequestFocusEnabled(false);
    this.jButton2.setRolloverEnabled(false);
    this.jButton2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            MainJFrame.this.jButton2ActionPerformed(evt);
          }
        });
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton2, -2, 109, -2));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton2));
    this.jLabel1.setFont(new Font("Tahoma", 0, 18));
    this.jLabel1.setText("DemonstraPrdos Algoritmos:");
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jLabel1).addGap(47, 47, 47)))));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -2, -1, -2).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(this.jLabel1).addGap(18, 18, 18).addComponent(this.jPanel1, -2, -1, -2))).addContainerGap(-1, 32767)));
    pack();
  }
  
  private void jButtonHFActionPerformed(ActionEvent evt) {
    (new HuffmanJFrame()).setVisible(true);
  }
  
  private void jButtonSFActionPerformed(ActionEvent evt) {
    (new ShannonFanoJFrame()).setVisible(true);
  }
  
  private void jButton2ActionPerformed(ActionEvent evt) {
    this.jDialog1.setVisible(true);
  }
  
  private void jButton3ActionPerformed(ActionEvent evt) {}
  
  public static void main(String[] args) {
      double a =5;
      double b =2;
      double c =a/b;
      System.out.println("la division del inicio"+c);
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new MainJFrame()).setVisible(true);
          }
        });
    
  }
}
