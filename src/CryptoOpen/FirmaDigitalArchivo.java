package CryptoOpen;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
*
* @author CryptoOpen.tk
*/
public class FirmaDigitalArchivo extends javax.swing.JFrame {

/**
* Creates new form FirmaDigitalArchivo
*/
public FirmaDigitalArchivo() {
initComponents();
Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));
}

@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        btarchivo = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        btarchivo1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        btarchivo2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DIGITALSIGNFILE");
        setBackground(new java.awt.Color(33, 33, 33));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("");

        btarchivo.setBackground(new java.awt.Color(33, 33, 33));
        btarchivo.setForeground(new java.awt.Color(255, 255, 255));
        btarchivo.setText("Archivo/E");
        btarchivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btarchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btarchivoActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setToolTipText("");

        btarchivo1.setBackground(new java.awt.Color(33, 33, 33));
        btarchivo1.setForeground(new java.awt.Color(255, 255, 255));
        btarchivo1.setText("Firma/S");
        btarchivo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btarchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btarchivo1ActionPerformed(evt);
            }
        });

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setToolTipText("");

        btarchivo2.setBackground(new java.awt.Color(33, 33, 33));
        btarchivo2.setForeground(new java.awt.Color(255, 255, 255));
        btarchivo2.setText("LlaveP/S");
        btarchivo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btarchivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btarchivo2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Firmar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Verificar");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cancelar");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btarchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btarchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btarchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btarchivo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btarchivo2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btarchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btarchivoActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showOpenDialog(null);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
jTextField1.setText(fl1);
}
catch(Exception e){
}        // TODO add your handling code here:
    }//GEN-LAST:event_btarchivoActionPerformed

    private void btarchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btarchivo1ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showSaveDialog(this);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
jTextField2.setText(fl1);
}catch(Exception e){

}        // TODO add your handling code here:
    }//GEN-LAST:event_btarchivo1ActionPerformed

    private void btarchivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btarchivo2ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showSaveDialog(this);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
jTextField3.setText(fl1);
}catch(Exception e){

}        // TODO add your handling code here:
    }//GEN-LAST:event_btarchivo2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jTextField1.getText().length()!=0&& jTextField2.getText().length()!=0&& jTextField3.getText().length()!=0){
try {
// Get instance and initialize a KeyPairGenerator object.
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
keyGen.initialize(1024, random);

// Get a PrivateKey from the generated key pair.
KeyPair keyPair = keyGen.generateKeyPair();
PrivateKey privateKey = keyPair.getPrivate();

// Get an instance of Signature object and initialize it.
Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
signature.initSign(privateKey);

// Supply the data to be signed to the Signature object
// using the update() method and generate the digital
// signature.
byte[] bytes = Files.readAllBytes(Paths.get(jTextField1.getText()));
signature.update(bytes);
byte[] digitalSignature = signature.sign();

// Save digital signature and the public key to a file.
Files.write(Paths.get(jTextField2.getText()), digitalSignature);
Files.write(Paths.get(jTextField3.getText()), keyPair.getPublic().getEncoded());
} catch (Exception e) {
e.printStackTrace();
}   }else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(jTextField1.getText().length()!=0&& jTextField2.getText().length()!=0&& jTextField3.getText().length()!=0){

try {
byte[] publicKeyEncoded = Files.readAllBytes(Paths.get(jTextField3.getText()));
byte[] digitalSignature = Files.readAllBytes(Paths.get(jTextField2.getText()));

X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyEncoded);
KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
signature.initVerify(publicKey);

byte[] bytes = Files.readAllBytes(Paths.get(jTextField1.getText()));
signature.update(bytes);

boolean verified = signature.verify(digitalSignature);
if (verified) {
jTextField4.setText("DATOS VERIFICADO: DATOS CORRECTOS");
JOptionPane.showMessageDialog(null,"DATOS VERIFICADO: DATOS CORRECTOS");
} else {
jTextField4.setText("DATOS VERIFICADO: LOS DATOS NO SE HAN PODIDO VERIFICAR O SON INCORRECTOS!!!..");
JOptionPane.showMessageDialog(null,"DATOS VERIFICADO: LOS DATOS NO SE HAN PODIDO VERIFICAR O SON INCORRECTOS!!!..");
}
} catch (Exception e) {
e.printStackTrace();
}   }else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
jTextField1.setText("") ; 
jTextField2.setText("") ;   
jTextField3.setText("") ;   
jTextField4.setText("") ;   
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirmaDigitalArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirmaDigitalArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirmaDigitalArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirmaDigitalArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirmaDigitalArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btarchivo;
    private javax.swing.JButton btarchivo1;
    private javax.swing.JButton btarchivo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
