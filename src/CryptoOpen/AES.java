package CryptoOpen;
import org.cryptoopen.criptografia.Algoritmos;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import static org.cryptoopen.criptografia.Algoritmos.md5;
import static org.cryptoopen.criptografia.Algoritmos.sha256;
import static org.cryptoopen.criptografia.Algoritmos.sha512;

/**
*
* @author Josue Daniel Roldan Ochoa.
*/
//to compile with aes256 need to disable some  security authority policity of java to allow aes 256
public class AES extends javax.swing.JFrame {
private static  int keySize=256;
private static final int iterationCount=10000;
private static Cipher cipher;  

    public static int getKeySize() {
        return keySize;
    }

    public static void setKeySize(int keySize) {
        AES.keySize = keySize;
    }

    public static Cipher getCipher() {
        return cipher;
    }

    public static void setCipher(Cipher cipher) {
        AES.cipher = cipher;
    }
/**
* Creates new form AES
*/
public AES() {
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));

try {
//cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
setCipher(Cipher.getInstance("AES/CBC/PKCS5Padding"));
}catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
throw fail(e);
}
initComponents();
this.setLocationRelativeTo(null);
}


public static String encryptaes(String texto,String ky,int key) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
keySize=key;
Algoritmos ob=new Algoritmos();
String esp="";
String respuesta="";
String salt=sha256(ky);
String iv=md5(ky);
String passphrase=sha512(ky);
respuesta=encrypt(salt,iv,passphrase,texto);
return respuesta;
}

public static String decryptaes(String texto,String ky,int key) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
keySize=key;
Algoritmos ob=new Algoritmos();
String esp="";
String respuesta="";
String salt=sha256(ky);
String iv=md5(ky);
String passphrase=sha512(ky);
respuesta=decrypt2(salt,iv,passphrase,texto);
return respuesta;
}


private static String encrypt(String salt, String iv, String passphrase, String plaintext) throws IllegalBlockSizeException, BadPaddingException {
byte[] encrypted=null;    
try {
SecretKey key = generateKey(salt, passphrase);
encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
base64(encrypted);
}
catch (UnsupportedEncodingException e) {

}
return  base64(encrypted);
}

private static String decrypt2(String salt, String iv, String passphrase, String ciphertext) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
byte[] decrypted=null;  
try {
SecretKey key = generateKey(salt, passphrase);
decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
return new String(decrypted, "UTF-8");
}
catch (UnsupportedEncodingException e) {

}
return new String(decrypted, "UTF-8");
}

private static byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) throws IllegalBlockSizeException, BadPaddingException {
try {
cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
return cipher.doFinal(bytes);
}
catch (InvalidKeyException
| InvalidAlgorithmParameterException
| IllegalBlockSizeException
| BadPaddingException e) {

}
return cipher.doFinal(bytes);
}

private static SecretKey generateKey(String salt, String passphrase) {
SecretKey key=null ;
try {
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
return key;
}
catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

}
return key;
}

private static String random(int length) {
byte[] salt = new byte[length];
new SecureRandom().nextBytes(salt);
return hex(salt);
}

private static String base64(byte[] bytes) {
return Base64.encodeBase64String(bytes);
}

private static byte[] base64(String str) {
return Base64.decodeBase64(str);
}

private static String hex(byte[] bytes) {
return Hex.encodeHexString(bytes);
}

private static byte[] hex(String str) {
try {
return Hex.decodeHex(str.toCharArray());
}
catch (DecoderException e) {
throw new IllegalStateException(e);
}
}

private IllegalStateException fail(Exception e) {
return new IllegalStateException(e);
}


@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttexto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtresult = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rbt1 = new javax.swing.JRadioButton();
        rbt2 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AES");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Texto:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/encriptacion.png"))); // NOI18N
        jButton1.setText("Encriptar/Desencriptar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Resultado:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo:");

        rbt1.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(rbt1);
        rbt1.setForeground(new java.awt.Color(255, 255, 255));
        rbt1.setSelected(true);
        rbt1.setText("Encriptar");
        rbt1.setFocusPainted(false);

        rbt2.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(rbt2);
        rbt2.setForeground(new java.awt.Color(255, 255, 255));
        rbt2.setText("Desencriptar");
        rbt2.setFocusPainted(false);

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/cancelar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/aes.png"))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "256", "128" }));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Key-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtresult))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpass))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rbt1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbt2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttexto)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(rbt1)
                                .addComponent(rbt2))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txttexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtresult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(67, 67, 67))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(txttexto.getText().length()!=0 && txtpass.getText().length()!=0){
if(rbt1.isSelected()){
Integer ix=Integer.parseInt((String) jComboBox1.getSelectedItem());
String encriptar = null;
try {
encriptar = encryptaes(txttexto.getText(),txtpass.getText().toString(),ix);
} catch (UnsupportedEncodingException ex) {
Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
} catch (IllegalBlockSizeException ex) {
Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
} catch (BadPaddingException ex) {
Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
}
txtresult.setText(encriptar);
}else{
Integer ix=Integer.parseInt((String) jComboBox1.getSelectedItem());
String desencriptar = null;
try {
desencriptar = decryptaes(txttexto.getText(),txtpass.getText().toString(),ix);
} catch (Exception ex) {
JOptionPane.showMessageDialog(null,ex.getMessage());
}
txtresult.setText(desencriptar);
}
}else{
JOptionPane.showMessageDialog(null,"INGRESA DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
txttexto.setText("");
txtpass.setText("");
txtresult.setText("");
rbt1.setSelected(true);
jComboBox1.setSelectedItem("256");

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbt1;
    private javax.swing.JRadioButton rbt2;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtresult;
    private javax.swing.JTextField txttexto;
    // End of variables declaration//GEN-END:variables
}
