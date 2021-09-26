package multimedia2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

public class HuffmanJFrame extends JFrame {
  private JButton jButtonClear;
  
  private JButton jButtonDecode;
  
  private JButton jButtonDrawTree;
  
  private JButton jButtonEncode;
  
  private JLabel jLabel1;
  
  private JLabel jLabel2;
  
  private JLabel jLabel3;
  
  private JLabel jLabel4;
  
  private JLabel jLabel5;
  
  private JLabel jLabel6;
  
  private JLabel jLabelAvgCod;
  
  private JLabel jLabelCodifiedMessage;
  
  private JLabel jLabelDecodedMessage;
  
  private JLabel jLabelEntropy;
  
  private JLabel jLabelRedundancy;
  
  private JMenuBar jMenuBar1;
  
  private JMenu jMenuDecoding;
  
  private JMenu jMenuEncoding;
  
  private JMenuItem jMenuItemOpenCodify;
  
  private JMenuItem jMenuItemOpenFile;
  
  private JMenuItem jMenuItemSaveCodify;
  
  private JMenuItem jMenuItemSaveOriginalMessage;
  
  private JPanel jPanel1;
  
  private JPanel jPanel2;
  
  private JPanel jPanelDecode;
  
  private JPanel jPanelFinalTable;
  
  private JPanel jPanelInputString;
  
  private JPanel jPanelTableInt;
  
  private JScrollPane jScrollPane1;
  
  private JScrollPane jScrollPane2;
  
  private JTable jTableFinal;
  
  private JTable jTableIntermedia;
  
  private JTextField jTextFieldInputSting;
  
  private Huffman hf;
  
  private DecimalFormat df;
  
  private MM2BST<String, Integer> st;
  
  protected JFileChooser fc;
  
  protected String[] fileExtensions;
  
  protected ExtensionsFileFilter filter;
  
  public HuffmanJFrame() {
    this.hf = null;
    this.df = new DecimalFormat("#.#####");
    this.st = null;
    this.fc = new JFileChooser("/Users/ruib/Desktop/NetBeansProjects/LP2/inOutMM2");
    this.fileExtensions = new String[] { "txt" };
    this.filter = new ExtensionsFileFilter(this.fileExtensions);
    initComponents();
    this.jPanelDecode.setVisible(false);
  }
  
  private void initComponents() {
    this.jPanelInputString = new JPanel();
    this.jLabel1 = new JLabel();
    this.jTextFieldInputSting = new JTextField();
    this.jButtonEncode = new JButton();
    this.jButtonDrawTree = new JButton();
    this.jButtonClear = new JButton();
    this.jPanelTableInt = new JPanel();
    this.jScrollPane1 = new JScrollPane();
    this.jTableIntermedia = new JTable();
    this.jPanelFinalTable = new JPanel();
    this.jScrollPane2 = new JScrollPane();
    this.jTableFinal = new JTable();
    this.jPanel1 = new JPanel();
    this.jPanel2 = new JPanel();
    this.jLabel2 = new JLabel();
    this.jLabelEntropy = new JLabel();
    this.jLabel3 = new JLabel();
    this.jLabelAvgCod = new JLabel();
    this.jLabel4 = new JLabel();
    this.jLabelRedundancy = new JLabel();
    this.jPanelDecode = new JPanel();
    this.jLabel5 = new JLabel();
    this.jLabelCodifiedMessage = new JLabel();
    this.jLabelDecodedMessage = new JLabel();
    this.jLabel6 = new JLabel();
    this.jButtonDecode = new JButton();
    this.jMenuBar1 = new JMenuBar();
    this.jMenuEncoding = new JMenu();
    this.jMenuItemOpenFile = new JMenuItem();
    this.jMenuItemSaveCodify = new JMenuItem();
    this.jMenuDecoding = new JMenu();
    this.jMenuItemOpenCodify = new JMenuItem();
    this.jMenuItemSaveOriginalMessage = new JMenuItem();
    setDefaultCloseOperation(2);
    setTitle("Huffman - Coding & Decoding");
    setResizable(false);
    this.jLabel1.setText("String");
    this.jButtonEncode.setText("Encode");
    this.jButtonEncode.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jButtonEncodeActionPerformed(evt);
          }
        });
    this.jButtonDrawTree.setText("DrawTree");
    this.jButtonDrawTree.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jButtonDrawTreeActionPerformed(evt);
          }
        });
    this.jButtonClear.setText("Clear");
    this.jButtonClear.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jButtonClearActionPerformed(evt);
          }
        });
    GroupLayout jPanelInputStringLayout = new GroupLayout(this.jPanelInputString);
    this.jPanelInputString.setLayout(jPanelInputStringLayout);
    jPanelInputStringLayout.setHorizontalGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelInputStringLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextFieldInputSting, -2, 279, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonEncode).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonDrawTree).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonClear).addContainerGap(-1, 32767)));
    jPanelInputStringLayout.setVerticalGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelInputStringLayout.createSequentialGroup().addContainerGap().addGroup(jPanelInputStringLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextFieldInputSting, -2, -1, -2).addComponent(this.jButtonEncode).addComponent(this.jButtonDrawTree).addComponent(this.jButtonClear)).addContainerGap(-1, 32767)));
    this.jPanelTableInt.setBorder(BorderFactory.createTitledBorder("Intermediate Tables"));
    this.jPanelTableInt.setPreferredSize(new Dimension(441, 351));
    this.jTableIntermedia.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[] { "Symbol (Si)", "Probability (Pi)" }) {
          Class[] types = new Class[] { String.class, String.class };
          
          boolean[] canEdit = new boolean[] { false, false };
          
          public Class getColumnClass(int columnIndex) {
            return this.types[columnIndex];
          }
          
          public boolean isCellEditable(int rowIndex, int columnIndex) {
            return this.canEdit[columnIndex];
          }
        });
    this.jScrollPane1.setViewportView(this.jTableIntermedia);
    this.jTableIntermedia.getColumnModel().getColumn(0).setResizable(false);
    this.jTableIntermedia.getColumnModel().getColumn(0).setPreferredWidth(200);
    this.jTableIntermedia.getColumnModel().getColumn(1).setResizable(false);
    this.jTableIntermedia.getColumnModel().getColumn(1).setPreferredWidth(200);
    GroupLayout jPanelTableIntLayout = new GroupLayout(this.jPanelTableInt);
    this.jPanelTableInt.setLayout(jPanelTableIntLayout);
    jPanelTableIntLayout.setHorizontalGroup(jPanelTableIntLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelTableIntLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 417, 32767).addContainerGap()));
    jPanelTableIntLayout.setVerticalGroup(jPanelTableIntLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelTableIntLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 317, 32767).addContainerGap()));
    this.jPanelFinalTable.setBorder(BorderFactory.createTitledBorder("Final Table"));
    this.jPanelFinalTable.setPreferredSize(new Dimension(441, 351));
    this.jTableFinal.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[] { "Si", "Fa", "Pi", "Codeword", "NBits" }) {
          Class[] types = new Class[] { String.class, String.class, String.class, String.class, String.class };
          
          public Class getColumnClass(int columnIndex) {
            return this.types[columnIndex];
          }
        });
    this.jScrollPane2.setViewportView(this.jTableFinal);
    GroupLayout jPanelFinalTableLayout = new GroupLayout(this.jPanelFinalTable);
    this.jPanelFinalTable.setLayout(jPanelFinalTableLayout);
    jPanelFinalTableLayout.setHorizontalGroup(jPanelFinalTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelFinalTableLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 417, 32767).addContainerGap()));
    jPanelFinalTableLayout.setVerticalGroup(jPanelFinalTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelFinalTableLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 317, 32767).addContainerGap()));
    this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Results", 1, 0));
    this.jLabel2.setText("Entropy");
    this.jLabelEntropy.setText("H = 0.0");
    this.jLabel3.setText("Average Codeword");
    this.jLabelAvgCod.setText("L = 0.0");
    this.jLabel4.setText("Coding Redundancy");
    this.jLabelRedundancy.setText("L-H = 0.0");
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabelEntropy, -2, 124, -2).addComponent(this.jLabel2).addComponent(this.jLabelAvgCod, -2, 124, -2).addComponent(this.jLabel3).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabelRedundancy, -2, 124, -2).addComponent(this.jLabel4))).addContainerGap(-1, 32767)));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(52, 52, 52).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabelEntropy).addGap(39, 39, 39).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabelAvgCod).addGap(42, 42, 42).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabelRedundancy).addContainerGap(82, 32767)));
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel2, -2, -1, -2)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767));
    this.jLabel5.setText("Codified Message");
    this.jLabelCodifiedMessage.setText(" ");
    this.jLabelDecodedMessage.setText(" ");
    this.jLabel6.setText("Decoded Message");
    this.jButtonDecode.setText("Decode");
    this.jButtonDecode.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jButtonDecodeActionPerformed(evt);
          }
        });
    GroupLayout jPanelDecodeLayout = new GroupLayout(this.jPanelDecode);
    this.jPanelDecode.setLayout(jPanelDecodeLayout);
    jPanelDecodeLayout.setHorizontalGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addComponent(this.jLabel5).addGap(365, 365, 365).addComponent(this.jButtonDecode)).addComponent(this.jLabelCodifiedMessage, -2, 416, -2)).addGap(49, 49, 49).addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel6).addComponent(this.jLabelDecodedMessage, -1, 427, 32767)).addContainerGap()));
    jPanelDecodeLayout.setVerticalGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelDecodeLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jButtonDecode).addComponent(this.jLabel6)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelDecodeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelCodifiedMessage).addComponent(this.jLabelDecodedMessage)).addContainerGap(15, 32767)));
    this.jLabelCodifiedMessage.getAccessibleContext().setAccessibleName("");
    this.jLabelDecodedMessage.getAccessibleContext().setAccessibleName("");
    this.jMenuEncoding.setText("Encoding");
    this.jMenuItemOpenFile.setText("Open...");
    this.jMenuItemOpenFile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jMenuItemOpenFileActionPerformed(evt);
          }
        });
    this.jMenuEncoding.add(this.jMenuItemOpenFile);
    this.jMenuItemSaveCodify.setText("Save");
    this.jMenuItemSaveCodify.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jMenuItemSaveCodifyActionPerformed(evt);
          }
        });
    this.jMenuEncoding.add(this.jMenuItemSaveCodify);
    this.jMenuBar1.add(this.jMenuEncoding);
    this.jMenuDecoding.setText("Decoding");
    this.jMenuItemOpenCodify.setText("Open...");
    this.jMenuItemOpenCodify.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jMenuItemOpenCodifyActionPerformed(evt);
          }
        });
    this.jMenuDecoding.add(this.jMenuItemOpenCodify);
    this.jMenuItemSaveOriginalMessage.setText("Save");
    this.jMenuItemSaveOriginalMessage.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            HuffmanJFrame.this.jMenuItemSaveOriginalMessageActionPerformed(evt);
          }
        });
    this.jMenuDecoding.add(this.jMenuItemSaveOriginalMessage);
    this.jMenuBar1.add(this.jMenuDecoding);
    setJMenuBar(this.jMenuBar1);
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.jPanelTableInt, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addGap(12, 12, 12).addComponent(this.jPanelFinalTable, -2, -1, -2)).addComponent(this.jPanelInputString, GroupLayout.Alignment.LEADING, -2, -1, -2).addComponent(this.jPanelDecode, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap(-1, 32767)));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanelInputString, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanelTableInt, GroupLayout.Alignment.LEADING, -1, 357, 32767).addComponent(this.jPanelFinalTable, GroupLayout.Alignment.LEADING, -1, 357, 32767).addComponent(this.jPanel1, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelDecode, -2, -1, -2).addContainerGap(-1, 32767)));
    pack();
  }
  
  private void jButtonEncodeActionPerformed(ActionEvent evt) {
    String message = this.jTextFieldInputSting.getText();
    if (message.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Insert the message to encode!!!");
    } else {
      clearTableInt(this.jTableIntermedia);
      clearTableFinal(this.jTableFinal);
      this.hf = new Huffman();
      this.hf.setFrame(this);
      this.hf.run(message);
      this.hf.printAlphabet();
      this.jLabelEntropy.setText("H = ".concat(this.df.format(this.hf.entropy())));
      this.jLabelAvgCod.setText("L = ".concat(this.df.format(this.hf.averageLenght())));
      this.jLabelRedundancy.setText("L-H = ".concat(this.df.format(this.hf.averageLenght() - this.hf.entropy())));
      this.jPanelDecode.setVisible(true);
      this.jLabelDecodedMessage.setText("");
      this.jLabelCodifiedMessage.setText(this.hf.compress(message));
    } 
  }
  
  private void jButtonDecodeActionPerformed(ActionEvent evt) {
    this.jLabelDecodedMessage.setText(this.hf.decode(this.jLabelCodifiedMessage.getText()));
  }
  
  private void jButtonDrawTreeActionPerformed(ActionEvent evt) {
    try {
      if (this.hf == null) {
        JOptionPane.showMessageDialog(this, "Insert the message and press encode button!!!");
      } else if (this.hf.getHuffmanTree().getRootNode() == null) {
        JOptionPane.showMessageDialog(this, "There is no tree to draw!!!");
      } else {
        this.st = new MM2BST<>();
        this.st.setHuffman(this.hf);
        this.st.drawHuffmanTree(this.st);
      } 
    } catch (InterruptedException ex) {
      Logger.getLogger(HuffmanJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  private void jButtonClearActionPerformed(ActionEvent evt) {
    this.hf = null;
    this.jLabelCodifiedMessage.setText("");
    this.jLabelDecodedMessage.setText("");
    this.jPanelDecode.setVisible(false);
    this.jTextFieldInputSting.setText("");
    clearTableInt(this.jTableIntermedia);
    clearTableFinal(this.jTableFinal);
    this.jLabelEntropy.setText("H = 0.0");
    this.jLabelAvgCod.setText("L = 0.0");
    this.jLabelRedundancy.setText("L-H = 0.0");
    if (this.st != null) {
      this.st.clearWindow();
      this.st = null;
    } 
  }
  
  private void jMenuItemOpenFileActionPerformed(ActionEvent evt) {
    FileFilter ff = this.fc.getFileFilter();
    if (ff != null && !ff.getDescription().contains("txt"))
      this.fc.setFileFilter(this.filter); 
    this.fc.setFileSelectionMode(2);
    FileReader fr = null;
    try {
      File f;
      BufferedReader br;
      String line, buffer;
      int option = this.fc.showOpenDialog(this);
      switch (option) {
        case 0:
          f = this.fc.getSelectedFile();
          fr = new FileReader(f);
          br = new BufferedReader(fr);
          line = null;
          buffer = new String("");
          while ((line = br.readLine()) != null)
            buffer = buffer.concat(line); 
          this.jTextFieldInputSting.setText(buffer);
          br.close();
          break;
      } 
    } catch (FileNotFoundException ex) {
      Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IOException ex) {
      Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
    } finally {
      try {
        if (fr != null)
          fr.close(); 
      } catch (IOException ex) {
        Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      } 
    } 
  }
  
  private void jMenuItemSaveCodifyActionPerformed(ActionEvent evt) {
    FileFilter ff = this.fc.getFileFilter();
    if (ff != null && !ff.getDescription().contains("txt"))
      this.fc.setFileFilter(this.filter); 
    this.fc.setFileSelectionMode(2);
    if (this.hf != null) {
      FileWriter fw = null;
      try {
        File f;
        PrintWriter pw;
        ArrayList<Alphabet> alphabet;
        int option = this.fc.showSaveDialog(this);
        switch (option) {
          case 0:
            f = this.fc.getSelectedFile();
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            alphabet = this.hf.getAlphabet();
            pw.println(alphabet.size());
            for (Alphabet a : alphabet)
              pw.println(a.getSi() + " " + a.getCodeword()); 
            pw.println(this.jLabelCodifiedMessage.getText());
            pw.flush();
            pw.close();
            break;
        } 
      } catch (IOException ex) {
        Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      } finally {
        try {
          if (fw != null)
            fw.close(); 
        } catch (IOException ex) {
          Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } 
      } 
    } else {
      JOptionPane.showMessageDialog(this, "No data to record!!!");
    } 
  }
  
  private void jMenuItemOpenCodifyActionPerformed(ActionEvent evt) {
    FileFilter ff = this.fc.getFileFilter();
    if (ff != null && !ff.getDescription().contains("txt"))
      this.fc.setFileFilter(this.filter); 
    this.fc.setFileSelectionMode(2);
    FileReader fr = null;
    try {
      File f;
      BufferedReader br;
      int nChars;
      DefaultTableModel tm;
      int i, option = this.fc.showOpenDialog(this);
      switch (option) {
        case 0:
          f = this.fc.getSelectedFile();
          fr = new FileReader(f);
          br = new BufferedReader(fr);
          this.hf = new Huffman();
          this.jLabelCodifiedMessage.setText("");
          this.jLabelDecodedMessage.setText("");
          this.jPanelDecode.setVisible(false);
          this.jTextFieldInputSting.setText("");
          clearTableInt(this.jTableIntermedia);
          clearTableFinal(this.jTableFinal);
          this.jLabelEntropy.setText("H = 0.0");
          this.jLabelAvgCod.setText("L = 0.0");
          this.jLabelRedundancy.setText("L-H = 0.0");
          if (this.st != null) {
            this.st.clearWindow();
            this.st = null;
          } 
          nChars = Integer.parseInt(br.readLine());
          tm = (DefaultTableModel)this.jTableFinal.getModel();
          for (i = 0; i < nChars; i++) {
            String line = br.readLine();
            String si = (String)line.subSequence(0, 1);
            String codeword = line.substring(2);
            this.hf.getAlphabet().add(new Alphabet(si.charAt(0), codeword));
            tm.addRow(new Object[] { new String(si), new String("-"), new String("-"), new String(codeword), new String("-") });
          } 
          this.jLabelCodifiedMessage.setText(br.readLine());
          this.jPanelDecode.setVisible(true);
          br.close();
          break;
      } 
    } catch (FileNotFoundException ex) {
      Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IOException ex) {
      Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
    } finally {
      try {
        if (fr != null)
          fr.close(); 
      } catch (IOException ex) {
        Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      } 
    } 
  }
  
  private void jMenuItemSaveOriginalMessageActionPerformed(ActionEvent evt) {
    FileFilter ff = this.fc.getFileFilter();
    if (ff != null && !ff.getDescription().contains("txt"))
      this.fc.setFileFilter(this.filter); 
    this.fc.setFileSelectionMode(2);
    if (this.hf != null && !this.jLabelDecodedMessage.getText().equalsIgnoreCase("")) {
      FileWriter fw = null;
      try {
        File f;
        PrintWriter pw;
        int option = this.fc.showSaveDialog(this);
        switch (option) {
          case 0:
            f = this.fc.getSelectedFile();
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            pw.println(this.jLabelDecodedMessage.getText());
            pw.flush();
            pw.close();
            break;
        } 
      } catch (IOException ex) {
        Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      } finally {
        try {
          if (fw != null)
            fw.close(); 
        } catch (IOException ex) {
          Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } 
      } 
    } else {
      JOptionPane.showMessageDialog(this, "No data to record!!!");
    } 
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new HuffmanJFrame()).setVisible(true);
          }
        });
  }
  
  public JTable getjTableIntermedia() {
    return this.jTableIntermedia;
  }
  
  public JTable getjTableFinal() {
    return this.jTableFinal;
  }
  
  public void clearTableInt(JTable t) {
    DefaultTableModel dtm = new DefaultTableModel(new Object[0][], (Object[])new String[] { "Symbol (Si)", "Probability (Pi)" });
    t.setModel(dtm);
  }
  
  public void clearTableFinal(JTable t) {
    DefaultTableModel dtm = new DefaultTableModel(new Object[0][], (Object[])new String[] { "Si", "Fa", "Pi", "Codeword", "NBits" });
    t.setModel(dtm);
  }
}