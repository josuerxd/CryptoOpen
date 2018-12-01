package CryptoOpen;
import org.cryptoopen.criptografia.Aes;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josue Daniel Roldan Ochoa.
 */
public class Decode extends javax.swing.JFrame implements Runnable {
private BufferedImage image = null;
private String rst="";
private Thread th=null;
    /**
     * Creates new form DecodMessageG
     */
    public Decode() {
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));

        initComponents();
             if(jRadioButton2.isSelected())   {
            txtllaveaes.setText("");
            txtllaversa.setText("");
            txtllaveaes.setEnabled(false);
            txtllaversa.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jButton4.setEnabled(false);
         
        } 
        img2();
    }
public  void img2(){
ImageIcon img2 =  new ImageIcon(getClass().getResource("/org/book/ico/foto.jpg"));
Image img = img2.getImage();
Image newimg = img.getScaledInstance(l.getWidth(),l.getHeight(), java.awt.Image.SCALE_SMOOTH);
ImageIcon newicon = new ImageIcon(newimg);
l.setIcon(newicon); 
}
   
 private java.io.File showFileDialog(boolean open) {
    JFileChooser fc = new JFileChooser("Open an image");
    javax.swing.filechooser.FileFilter ff = new javax.swing.filechooser.FileFilter() {
       public boolean accept(java.io.File f) {
          String name = f.getName().toLowerCase();
          return f.isDirectory() ||   name.endsWith(".png") || name.endsWith(".bmp");
          }
       public String getDescription() {
          return "Image (*.png, *.bmp)";
          }
       };
    fc.setAcceptAllFileFilterUsed(false);
    fc.addChoosableFileFilter(ff);
 
    java.io.File f = null;
    if(open && fc.showOpenDialog(this) == fc.APPROVE_OPTION)
       f = fc.getSelectedFile();
    else if(!open && fc.showSaveDialog(this) == fc.APPROVE_OPTION)
       f = fc.getSelectedFile();
    return f;
   }
 
 private void openImage() {
    java.io.File f = showFileDialog(true);
    try {   
       image = ImageIO.read(f);
    ImageIcon img2 =  new ImageIcon(image);
         Image img = img2.getImage();
         Image newimg = img.getScaledInstance(l.getWidth(),l.getHeight(), java.awt.Image.SCALE_SMOOTH);
         ImageIcon newicon = new ImageIcon(newimg);
         l.setIcon(newicon);
       
        JOptionPane.showMessageDialog(null,"Imagen Importada Exitosamente!!!");
       this.validate();
       } catch(Exception ex) { ex.printStackTrace(); }
    }
 
 private void decodeMessage() {
    int len = extractInteger(image, 0, 0);
    byte b[] = new byte[len];
    for(int i=0; i<len; i++)
       b[i] = extractByte(image, i*8+32, 0);
    message.setText(new String(b));
     JOptionPane.showMessageDialog(null,"Imagen Descodificada  Exitosamente!!!");
    }
 
 private int extractInteger(BufferedImage img, int start, int storageBit) {
   int maxX = img.getWidth(), maxY = img.getHeight(), 
       startX = start/maxY, startY = start - startX*maxY, count=0;
    int length = 0;
    for(int i=startX; i<maxX && count<32; i++) {
       for(int j=startY; j<maxY && count<32; j++) {
          int rgb = img.getRGB(i, j), bit = getBitValue(rgb, storageBit);
          length = setBitValue(length, count, bit);
          count++;
          }
       }
    return length;
    }
 
 private byte extractByte(BufferedImage img, int start, int storageBit) {
    int maxX = img.getWidth(), maxY = img.getHeight(), 
       startX = start/maxY, startY = start - startX*maxY, count=0;
    byte b = 0;
    for(int i=startX; i<maxX && count<8; i++) {
       for(int j=startY; j<maxY && count<8; j++) {
          int rgb = img.getRGB(i, j), bit = getBitValue(rgb, storageBit);
          b = (byte)setBitValue(b, count, bit);
          count++;
          }
       }
    return b;
    }
 
 private void resetInterface() {
image = null;
l.setIcon(null);
 txtllaveaes.setText("");
            txtllaversa.setText("");
            txtllaveaes.setEnabled(false);
            txtllaversa.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jButton4.setEnabled(false);
                
            jRadioButton2.setSelected(true);
            message.setText("");
    this.validate();
     img2();
    }
 

 private int getBitValue(int n, int location) {
    int v = n & (int) Math.round(Math.pow(2, location));
    return v==0?0:1;
    }
 
 private int setBitValue(int n, int location, int bit) {
    int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
    if(bv == bit)
       return n;
    if(bv == 0 && bit == 1)
       n |= toggle;
    else if(bv == 1 && bit == 0)
       n ^= toggle;
    return n;
    }


 private static String decryptaes(String texto,String ky) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
Aes xx=new Aes();
String respuesta="";
String salt=Sha256(ky);
String iv=md5(ky);
String passphrase=Sha512(ky);
respuesta=xx.decrypt2(salt,iv,passphrase,texto);
return respuesta;
}

private static String md5(String texto) throws UnsupportedEncodingException{
MessageDigest md = null;
String s = null;
try {
//SHA-512
String encripcion;
md= MessageDigest.getInstance("MD5");
md.update(texto.getBytes());
 byte[] mb = md.digest();
          //  System.out.println((Hex.encodeHex(mb)));
            char [] dds=(Hex.encodeHex(mb));
            s = new String(dds);
           
          //  System.out.println((Arrays.toString(Hex.encodeHex(mb))));
            } catch (NoSuchAlgorithmException e) {
            //Error
        } 
return s;
}

private static String Sha512(String texto) throws UnsupportedEncodingException{
MessageDigest md = null;
String s = null;
try {
//SHA-512
String encripcion;
md= MessageDigest.getInstance("SHA-512");
md.update(texto.getBytes());
 byte[] mb = md.digest();
          //  System.out.println((Hex.encodeHex(mb)));
            char [] dds=(Hex.encodeHex(mb));
            s = new String(dds);
           
          //  System.out.println((Arrays.toString(Hex.encodeHex(mb))));
            } catch (NoSuchAlgorithmException e) {
            //Error
        } 
return s;
}
 
private static String Sha256(String texto) throws UnsupportedEncodingException{
MessageDigest md = null;
String s = null;
try{
//256
String encripcion;
md= MessageDigest.getInstance("SHA-256");
md.update(texto.getBytes("UTF-8")); // Change this to "UTF-16" if needed
byte[] mb = md.digest();
char [] dds=(Hex.encodeHex(mb));
 s = new String(dds);
//   txencripcion.setText(s);
} catch (NoSuchAlgorithmException e) {
//Error
}
return s;
}


 private  static String desencriptarLlavesRsaTexto(String texto,String ky) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
KeyFactory kf = null;
String rsp="";
kf = KeyFactory.getInstance("RSA");
byte[] encodedPv = Base64.decodeBase64(ky);
PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
PrivateKey    privateKey = kf.generatePrivate(keySpecPv);
Cipher cifrador = Cipher.getInstance("RSA");    
cifrador.init(Cipher.DECRYPT_MODE, privateKey);
byte[] bufferCifrado2 = Base64.decodeBase64(texto);
byte[] bufferClaro;
//Obtener y mostrar texto descifrado
bufferClaro = cifrador.doFinal(bufferCifrado2);
//mostrarBytes(bufferClaro);
String str = new String(bufferClaro, StandardCharsets.UTF_8);
rsp=(str);
return rsp;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        l = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtllaveaes = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtllaversa = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("STEGANO-IMAGE");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagen Original", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mensaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Encriptado?");

        jRadioButton1.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Si");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("No");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo de Encriptacion?");

        jRadioButton3.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("AES");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("RSA");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña AES:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Llave Privada RSA:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Created by:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Mohit");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rsa and Aes integrated by:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Crypto Open");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtllaveaes))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtllaversa)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtllaveaes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtllaversa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Abrir");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Decodificar");
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Resetear");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Desencriptar");
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(33, 33, 33));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Cancelar");
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected())   {

            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);
            jRadioButton4.setSelected(true);
            jButton4.setEnabled(true);
        }
        if(jRadioButton3.isSelected())   {
            txtllaveaes.setEnabled(true);
            txtllaversa.setEnabled(false);

        }
        if(jRadioButton4.isSelected())   {
            txtllaversa.setEnabled(true);
            txtllaveaes.setEnabled(false);

        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected())   {
            txtllaveaes.setText("");
            txtllaversa.setText("");
            txtllaveaes.setEnabled(false);
            txtllaversa.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jButton4.setEnabled(false);
         
        }       
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
if(jRadioButton3.isSelected())   {
txtllaveaes.setEnabled(true);
txtllaversa.setEnabled(false);
}        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
if(jRadioButton4.isSelected())   {
txtllaversa.setEnabled(true);
txtllaveaes.setEnabled(false);
} 
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
rst="AbrirImagen";
th=new Thread(this);
th.start();         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(image!=null){
rst="Decodificar";
th=new Thread(this);
th.start();
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(jRadioButton1.isSelected()){
if(jRadioButton3.isSelected() || jRadioButton4.isSelected()){    
rst="Desencriptar";
th=new Thread(this);
th.start(); 
}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 txtllaveaes.setText("");
            txtllaversa.setText("");
            txtllaveaes.setEnabled(false);
            txtllaversa.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jButton4.setEnabled(false);
               
            jRadioButton2.setSelected(true);
            message.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
rst="Reset";
th=new Thread(this);
th.start();   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        Desktop desktop = Desktop.getDesktop();	    URI uri = null;
        try {
            uri = new URI("https://www.youtube.com/watch?v=ZIKCoF_H8g4");
        } catch (URISyntaxException ex) {
            Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            desktop.browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(Decode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Decode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Decode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Decode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Decode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JTextArea message;
    private javax.swing.JPasswordField txtllaveaes;
    private javax.swing.JPasswordField txtllaversa;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
    if(rst.equals("AbrirImagen")){
     openImage() ;
    }
    else
   if(rst.equals("Decodificar")){
     decodeMessage();
    }  else
   if(rst.equals("Reset")){
      resetInterface();
    }
    else
       if(rst.equals("Desencriptar")){
       if(message.getText().length()!=0){
if(jRadioButton3.isSelected()) {
    if(txtllaveaes.getText().length()!=0){
String tx=message.getText();
String k=txtllaveaes.getText().toString();
    try {
      
        String rsp= decryptaes(tx,k);
        message.setText(rsp);
         JOptionPane.showMessageDialog(null,"Mensaje Desencriptado Exitosamente!!!");
    } catch (IllegalBlockSizeException ex) {
        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}else
    if(jRadioButton4.isSelected()) {
    if(txtllaversa.getText().length()!=0){
String tx=message.getText();
String k=txtllaversa.getText().toString();
    try {
      
        String rsp= desencriptarLlavesRsaTexto(tx,k);
        message.setText(rsp);
    } catch (IllegalBlockSizeException ex) {
        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
}
       }
   
    }
}
