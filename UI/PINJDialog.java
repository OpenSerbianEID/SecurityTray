package UI;

import Crypto.Facade;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PINJDialog extends JDialog {
   public char[] PIN;
   public String odabraniCitac = null;
   public int odabraniCitacIndex = 0;
   public boolean unetPin = false;
   public boolean IDLogin = false;
   static final Logger _log = LoggerFactory.getLogger(PINJDialog.class);
   private JButton jButton1;
   private JButton jButton2;
   private JButton jButton3;
   private JComboBox jComboBox1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JPasswordField jPasswordField1;

   public PINJDialog() {
   }

   public PINJDialog(Frame parent, boolean modal, boolean administration) {
      super(parent, modal);
      super.setAlwaysOnTop(true);
      this.initComponents();
      this.setAlwaysOnTop(true);
      this.jButton2.setVisible(false);
      this.jButton3.setVisible(false);
      this.getRootPane().setDefaultButton(this.jButton1);
      this.jPasswordField1.requestFocus();
      Object[] r = null;

      try {
         r = Facade.getReaders();
      } catch (Exception var6) {
         _log.error(var6.getMessage());
      }

      this.jComboBox1.removeAllItems();
      if (!administration) {
         if (r != null && r.length != 0) {
            for(int i = 0; i < r.length; ++i) {
               this.jComboBox1.addItem(r[i]);
            }
         }
      } else if ((new File(System.getProperty("user.home").substring(0, 1) + ":\\Windows\\System32\\WDP11_ND_v33.dll")).exists()) {
         super.setAlwaysOnTop(false);
         this.jComboBox1.addItem(ResourceBundle.getBundle("UI/Bundle").getString("TOKEN"));
      }

      if (this.jComboBox1.getItemCount() > 0) {
         this.setLocationRelativeTo((Component)null);
         this.setVisible(true);
      } else {
         this.unetPin = false;
         this.setVisible(false);
         JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("NO_CARD_READER"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
      }

   }

   private void initComponents() {
      this.jButton1 = new JButton();
      this.jLabel1 = new JLabel();
      this.jComboBox1 = new JComboBox();
      this.jLabel2 = new JLabel();
      this.jButton2 = new JButton();
      this.jButton3 = new JButton();
      this.jPasswordField1 = new JPasswordField();
      ResourceBundle bundle = ResourceBundle.getBundle("UI/Bundle");
      this.setTitle(bundle.getString("LOGIN"));
      this.setModal(true);
      this.setResizable(false);
      this.jButton1.setText(bundle.getString("OK"));
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            PINJDialog.this.jButton1ActionPerformed(evt);
         }
      });
      this.jLabel1.setText(bundle.getString("ENTER_PIN:"));
      this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
      this.jComboBox1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            PINJDialog.this.jComboBox1ActionPerformed(evt);
         }
      });
      this.jLabel2.setText(bundle.getString("SELECT_READER:"));
      this.jButton2.setText(bundle.getString("CHANGE_PIN"));
      this.jButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            PINJDialog.this.jButton2ActionPerformed(evt);
         }
      });
      this.jButton3.setText(bundle.getString("UNBLOCK_PIN"));
      this.jButton3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            PINJDialog.this.jButton3ActionPerformed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.jLabel2, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jComboBox1, 0, 262, 32767).addComponent(this.jPasswordField1, -1, 262, 32767))).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButton2, -1, 105, 32767).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton1, -1, 104, 32767).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton3, -2, 105, -2))).addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jComboBox1, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jPasswordField1, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jButton3).addComponent(this.jButton1)).addContainerGap(-1, 32767)));
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      this.PIN = this.jPasswordField1.getPassword();
      if (this.PIN.length == 0) {
         super.setAlwaysOnTop(false);
         JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("PIN_LENGTH_CAN_NOT_BE_NULL!"), ResourceBundle.getBundle("UI/Bundle").getString("WARNING"), 2);
         super.setAlwaysOnTop(true);
      } else {
         if (this.PIN.length >= 1 & this.PIN.length <= 40) {
            this.odabraniCitac = (String)this.jComboBox1.getSelectedItem();
            this.odabraniCitacIndex = this.jComboBox1.getSelectedIndex();
            this.unetPin = true;
            this.setVisible(false);
         } else {
            super.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("PIN_LENGTH!"), ResourceBundle.getBundle("UI/Bundle").getString("WARNING"), 2);
            super.setAlwaysOnTop(true);
         }

      }
   }

   private void jComboBox1ActionPerformed(ActionEvent evt) {
   }

   private void jButton2ActionPerformed(ActionEvent evt) {
   }

   private void jButton3ActionPerformed(ActionEvent evt) {
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            PINJDialog dialog = new PINJDialog(new JFrame(), true, true);
            dialog.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
            dialog.setVisible(true);
         }
      });
   }
}
