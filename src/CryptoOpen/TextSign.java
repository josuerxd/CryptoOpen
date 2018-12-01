/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package CryptoOpen;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
*
* @author CryptoOpen.tk
*/
public class TextSign extends javax.swing.JFrame {
private KeyPairGenerator keyGen;
private KeyPair pair;
private PrivateKey privateKey;
private PublicKey publicKey;
private List<byte[]> list;
/**
* Creates new form TextSign
*/
public TextSign() {
initComponents();
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));
}

//The constructor of Message class builds the list that will be written to the file. The list consists of the message and the signature.
public TextSign(String data, String keyFile) throws InvalidKeyException, Exception {
list = new ArrayList<byte[]>();
list.add(data.getBytes());
list.add(sign(data, keyFile));
}

//The method that signs the data using the private key that is stored in keyFile path
public byte[] sign(String data, String keyFile) throws InvalidKeyException, Exception{
Signature dsa = Signature.getInstance("SHA1withRSA"); 
dsa.initSign(getPrivate(keyFile));
dsa.update(data.getBytes());
return dsa.sign();
}

//Method to retrieve the Private Key from a file
public PrivateKey getPrivate(String filename) throws Exception {
byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
KeyFactory kf = KeyFactory.getInstance("RSA");
return kf.generatePrivate(spec);
}

//Method to write the List of byte[] to a file
private void writeToFile(String filename) throws FileNotFoundException, IOException {
File f = new File(filename);
f.getParentFile().mkdirs();
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
out.writeObject(list);
out.close();
JOptionPane.showMessageDialog(null,"Firma Creado Exitosamente!!!");
}


public TextSign(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {

this.keyGen = KeyPairGenerator.getInstance("RSA");
this.keyGen.initialize(keylength);
}

public void createKeys() {
this.pair = this.keyGen.generateKeyPair();
this.privateKey = pair.getPrivate();
this.publicKey = pair.getPublic();

}

public PrivateKey getPrivateKey() {
return this.privateKey;
}

public PublicKey getPublicKey() {
return this.publicKey;
}

public void writeToFile(String path, byte[] key) throws IOException {

File f = new File(path);
f.getParentFile().mkdirs();

FileOutputStream fos = new FileOutputStream(f);
fos.write(key);
fos.flush();
fos.close();

}


//The constructor of VerifyMessage class retrieves the byte arrays from the File and prints the message only if the signature is verified.
private void TextSign(String filename, String keyFile) throws Exception {
ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
this.list = (List<byte[]>) in.readObject();
in.close();

jTextArea1.setText(verifySignature(list.get(0), list.get(1), keyFile) ? "VERIFIED MESSAGE" + "\n----------------\n" + new String(list.get(0)) : "Could not verify the signature.");	    
}

//Method for signature verification that initializes with the Public Key, updates the data to be verified and then verifies them using the signature
private boolean verifySignature(byte[] data, byte[] signature, String keyFile) throws Exception {
Signature sig = Signature.getInstance("SHA1withRSA");
sig.initVerify(getPublic(keyFile));
sig.update(data);

return sig.verify(signature);
}

//Method to retrieve the Public Key from a file
public PublicKey getPublic(String filename) throws Exception {
byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
KeyFactory kf = KeyFactory.getInstance("RSA");
return kf.generatePublic(spec);
}

@SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TEXTSIGN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Generar LLaves");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4096", "2048", "1024" }));

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Firmar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Verificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Llave Privada");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Llave Publica");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Archivo Firma");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jTextField1.getText().length()!=0 && jTextField2.getText().length()!=0){

File f1=new File("MyKeys/"+jTextField1.getText()+".cryptoopen") ;
File f2=new File("MyKeys/"+jTextField2.getText()+".cryptoopen") ;
if(f1.exists() || f2.exists() || f1.exists() && f2.exists()){
JOptionPane.showMessageDialog(null,"Archivo/s existente/s... Imposible sobrescribirlos!!!");
}else{

try {
Integer i=Integer.parseInt(jComboBox1.getSelectedItem().toString());
TextSign myKeys = new TextSign(i);
myKeys.createKeys();
myKeys.writeToFile("MyKeys/"+jTextField2.getText()+".cryptoopen", myKeys.getPublicKey().getEncoded());
myKeys.writeToFile("MyKeys/"+jTextField1.getText()+".cryptoopen", myKeys.getPrivateKey().getEncoded());        // TODO add your handling code here:
JOptionPane.showMessageDialog(null,"Llaves Generadas!!!");
} catch (NoSuchAlgorithmException ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (NoSuchProviderException ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
}
}
}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(jTextField1.getText().length()!=0 && jTextField3.getText().length()!=0  && jTextArea1.getText().length()!=0 ){
File f1=new File("MyKeys/"+jTextField1.getText()+".cryptoopen") ;
File f3=new File("MyData/"+jTextField3.getText()+".cryptoopen") ;
if(!f1.exists() && f3.exists()){
JOptionPane.showMessageDialog(null,"Archivo existente... Imposible sobrescribirlo!!!.. Llave Privada no existe!!!");
}else

if( f3.exists()){
JOptionPane.showMessageDialog(null,"Archivo existente... Imposible sobrescribirlo!!!");
}
else
if(!f1.exists()){
JOptionPane.showMessageDialog(null,"Llave Privada no existe!!!");
}

else{

String data = jTextArea1.getText();

try {
new TextSign(data, "MyKeys/"+jTextField1.getText()+".cryptoopen").writeToFile("MyData/"+jTextField3.getText().toString()+".cryptoopen");        // TODO add your handling code here:
} catch (IOException ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (Exception ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
}
}
}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(jTextField3.getText().length()!=0 && jTextField2.getText().length()!=0){
File f2=new File("MyKeys/"+jTextField2.getText()+".cryptoopen") ;
File f3=new File("MyData/"+jTextField3.getText()+".cryptoopen") ;
if(!f2.exists() && !f3.exists()){
JOptionPane.showMessageDialog(null,"Archivo no existente!!!.. Llave Publica no existe!!!");
}
else
if(!f3.exists()){
JOptionPane.showMessageDialog(null,"Archivo no existente!!!");
}
else
if(!f2.exists()){
JOptionPane.showMessageDialog(null,"Llave Publica no existe!!!");
}
else{

try {
TextSign("MyData/"+jTextField3.getText()+".cryptoopen", "MyKeys/"+jTextField2.getText()+".cryptoopen");        // TODO add your handling code here:
} catch (Exception ex) {
Logger.getLogger(TextSign.class.getName()).log(Level.SEVERE, null, ex);
}
}
}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
jTextArea1.setText("");  
jTextField1.setText("");
jTextField2.setText("");
jTextField3.setText("");
jComboBox1.setSelectedItem("4096");
// TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(TextSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextSign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
