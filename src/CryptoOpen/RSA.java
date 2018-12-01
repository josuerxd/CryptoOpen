/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package CryptoOpen;

import java.awt.Toolkit;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

/**
*
* @author Josue Daniel Roldan Ochoa.
*/
public class RSA extends javax.swing.JFrame implements Runnable{
private Thread th=null;
private String ab="";
/**
* Creates new form RS
*/
public RSA() {
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));

initComponents();
this.setLocationRelativeTo(null);
cbx1024.setSelected(true);


}
String chunk( String input, int chunklength) {
StringBuffer sb = new StringBuffer();
for( int i = 0; i < input.length(); i+=chunklength) {
if( i > 0) {
sb.append('\n');
}
int end = Math.min( i + chunklength, input.length());
sb.append( input.subSequence(i, end)); 
}
return sb.toString();
}
private void generarLlavesRsaTexto(int nx) throws NoSuchAlgorithmException{
KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
keyPairGen.initialize(nx);//4096 //1024
KeyPair          keyPair    = keyPairGen.genKeyPair();
PublicKey        publicKey  = keyPair.getPublic();
PrivateKey       privateKey = keyPair.getPrivate();

BASE64Encoder b64 = new BASE64Encoder();
String ab=( "-----BEGIN PUBLIC KEY----- ");
ab+=((b64.encode( publicKey.getEncoded())));
ab+=( " -----END PUBLIC KEY-----");
//System.out.print(ab);

String publicK = Base64.encodeBase64String(publicKey.getEncoded());
String privateK = Base64.encodeBase64String(privateKey.getEncoded());
txtllavepublica.setText(publicK);
txtllaveprivada.setText(privateK);
String phpPublic = ("-----BEGIN PUBLIC KEY-----"+publicK+"-----END PUBLIC KEY-----");
//System.out.print(phpPublic);

//txtentrada1.setText(phpPublic);
}

private void encriptarLlavesRsaTexto() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
KeyFactory kf = null;
kf = KeyFactory.getInstance("RSA");
byte[] encodedPb = Base64.decodeBase64(txtllavepublica.getText());
X509EncodedKeySpec keySpecPb = new X509EncodedKeySpec(encodedPb);
PublicKey   publicKey = kf.generatePublic(keySpecPb);
byte[] bufferClaro = txtentrada1.getText().getBytes();
Cipher cifrador = Cipher.getInstance("RSA");
cifrador.init(Cipher.ENCRYPT_MODE, publicKey);
byte[] bufferCifrado = cifrador.doFinal(bufferClaro);

String respuesta =Base64.encodeBase64String(bufferCifrado);
txtrespuesta.setText(respuesta);

}

private void desencriptarLlavesRsaTexto() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
KeyFactory kf = null;
kf = KeyFactory.getInstance("RSA");
byte[] encodedPv = Base64.decodeBase64(txtllaveprivada.getText());
PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
PrivateKey    privateKey = kf.generatePrivate(keySpecPv);
Cipher cifrador = Cipher.getInstance("RSA");    
cifrador.init(Cipher.DECRYPT_MODE, privateKey);


byte[] bufferCifrado2 = Base64.decodeBase64(txtentrada1.getText());
byte[] bufferClaro;
//Obtener y mostrar texto descifrado
bufferClaro = cifrador.doFinal(bufferCifrado2);

//mostrarBytes(bufferClaro);
String str = new String(bufferClaro, StandardCharsets.UTF_8);
txtrespuesta.setText(str);

}
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtllavepublica = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtllaveprivada = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rbtencriptar = new javax.swing.JRadioButton();
        rbtdesencriptar = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtrespuesta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtentrada1 = new javax.swing.JTextArea();
        cbx1024 = new javax.swing.JCheckBox();
        cbx2048 = new javax.swing.JCheckBox();
        cbx4096 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CRYPTORSA");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Texto Encriptar/Desencriptar:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Llave Privada:");

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/key.png"))); // NOI18N
        jButton1.setText("GENERAR LLAVES RSA TEXTO");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/encriptacion.png"))); // NOI18N
        jButton3.setText("ENCRIPTAR/DESENCRIPTAR");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/cancelar.png"))); // NOI18N
        jButton4.setText("CANCELAR");
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Llave Publica:");

        rbtencriptar.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup4.add(rbtencriptar);
        rbtencriptar.setForeground(new java.awt.Color(255, 255, 255));
        rbtencriptar.setSelected(true);
        rbtencriptar.setText("ENCRIPTAR");
        rbtencriptar.setFocusPainted(false);

        rbtdesencriptar.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup4.add(rbtdesencriptar);
        rbtdesencriptar.setForeground(new java.awt.Color(255, 255, 255));
        rbtdesencriptar.setText("DESENCRIPTAR");
        rbtdesencriptar.setFocusPainted(false);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ENRIPTADO/DESENCRIPTADO:");

        txtrespuesta.setColumns(20);
        txtrespuesta.setRows(5);
        jScrollPane1.setViewportView(txtrespuesta);

        txtentrada1.setColumns(20);
        txtentrada1.setRows(5);
        jScrollPane2.setViewportView(txtentrada1);

        cbx1024.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup5.add(cbx1024);
        cbx1024.setForeground(new java.awt.Color(255, 255, 255));
        cbx1024.setText("1024");

        cbx2048.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup5.add(cbx2048);
        cbx2048.setForeground(new java.awt.Color(255, 255, 255));
        cbx2048.setText("2048");

        cbx4096.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup5.add(cbx4096);
        cbx4096.setForeground(new java.awt.Color(255, 255, 255));
        cbx4096.setText("4096");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tamaño de Llave RSA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtencriptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtdesencriptar))
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtllavepublica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                        .addComponent(txtllaveprivada, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cbx1024)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx2048)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx4096)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtencriptar)
                    .addComponent(rbtdesencriptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtllavepublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtllaveprivada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx1024)
                    .addComponent(cbx2048)
                    .addComponent(cbx4096)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
ab="generarLlavesTexto";
th=new Thread(this);
th.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(txtentrada1.getText().length()!=0){
if(rbtencriptar.isSelected()){
if(txtllavepublica.getText().length()!=0) {
ab="encriptarLlavesRsaTexto"; 
th=new Thread(this);
th.start();
}
else{
JOptionPane.showMessageDialog(null,"Ingrese Llave Publica!!!");
}
}
else if(rbtdesencriptar.isSelected()){
if(txtllaveprivada.getText().length()!=0) {    
ab="desencriptarLlavesRsaTexto"; 
th=new Thread(this);
th.start();
}else{
JOptionPane.showMessageDialog(null,"Ingrese Llave Privada!!!");
}
}
}else{
JOptionPane.showMessageDialog(null,"Ingrese Texto a Encriptar/Desencriptar!!!");
}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
txtentrada1.setText("");       
txtllavepublica.setText("");
txtllaveprivada.setText("");
txtrespuesta.setText("");
rbtencriptar.setSelected(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JCheckBox cbx1024;
    private javax.swing.JCheckBox cbx2048;
    private javax.swing.JCheckBox cbx4096;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbtdesencriptar;
    private javax.swing.JRadioButton rbtencriptar;
    private javax.swing.JTextArea txtentrada1;
    private javax.swing.JTextField txtllaveprivada;
    private javax.swing.JTextField txtllavepublica;
    private javax.swing.JTextArea txtrespuesta;
    // End of variables declaration//GEN-END:variables

@Override
public void run() {
if(ab.equals("generarLlavesTexto")){
try {
int nnx=0;
if(cbx1024.isSelected()){
nnx=1024;
}
else
if(cbx2048.isSelected()){
nnx=2048;
}          if(cbx4096.isSelected()){
nnx=4096;
}
generarLlavesRsaTexto(nnx);
} catch (NoSuchAlgorithmException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
}
}
else if(ab.equals("encriptarLlavesRsaTexto")){
try {
encriptarLlavesRsaTexto() ;
JOptionPane.showMessageDialog(null,"Datos Encriptados Exitosamente!!!");
} catch (InvalidKeyException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (NoSuchAlgorithmException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (InvalidKeySpecException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (NoSuchPaddingException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (IllegalBlockSizeException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (BadPaddingException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
}
}
else if(ab.equals("desencriptarLlavesRsaTexto")){
try {  
desencriptarLlavesRsaTexto() ;
JOptionPane.showMessageDialog(null,"Datos Desencriptado Exitosamente!!!");
} catch (NoSuchAlgorithmException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (InvalidKeySpecException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (NoSuchPaddingException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (InvalidKeyException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (IllegalBlockSizeException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
} catch (BadPaddingException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
}
}



}
}
