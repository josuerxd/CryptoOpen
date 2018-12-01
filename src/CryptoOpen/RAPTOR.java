package CryptoOpen;

import org.cryptoopen.criptografia.RSAKeyPairGenerator;
import org.cryptoopen.helper.PgpHelper;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;

/**
*
* @author Josue Daniel Roldan Ochoa.
*/
public class RAPTOR extends javax.swing.JFrame implements Runnable {
private boolean isArmored = false;
private static boolean integrityCheck = true;
//private static String ruta = System.getProperties().getProperty("user.dir");
/**
* Creates new form PGPCONLLAVESRSAYZIP4J
*/
public RAPTOR() {
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));

initComponents();
this.setLocationRelativeTo(null);
}

//Funcion Generador de Zip Encriptado con AES256
private void hoiagdsh(String entrada,String salida,String contra){
File fl=new File(entrada);
if(fl.exists()){
if(fl.isFile()){
try {
ZipFile zipFile = new ZipFile(salida);
ArrayList filesToAdd = new ArrayList();
filesToAdd.add(new File(entrada));
ZipParameters parameters = new ZipParameters();
parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
parameters.setEncryptFiles(true);
parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
parameters.setPassword(contra);
zipFile.addFiles(filesToAdd, parameters);
} catch (ZipException e) {
e.printStackTrace();
}
}else
if(fl.isDirectory()){
ZipParameters parameters = new ZipParameters();
parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
parameters.setEncryptFiles(true);
parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
parameters.setPassword(contra);
try {
ZipFile zipFile = new ZipFile(salida);
ArrayList filesToAdd = new ArrayList();
ArrayList filesToAdd2 = new ArrayList();
File[] listOfFiles = fl.listFiles();
for (int i = 0; i < listOfFiles.length; i++)         {
if (listOfFiles[i].isFile())             {
String files = listOfFiles[i].getName();
filesToAdd.add(new File(entrada+"/"+files));
}
else
if (listOfFiles[i].isDirectory()){
String files = listOfFiles[i].getName();
files=(entrada+"/"+files);
zipFile.addFolder(files, parameters);
}
}
zipFile.addFiles(filesToAdd, parameters);
} catch (ZipException e) {
e.printStackTrace();
}
}
}else{System.exit(0);
}  
}


//Funcion Extraer datos Zip encriptado con AES256
private void igadoghsg(String entrada,String salida,String contra){
String source = entrada;
String destination = salida;
String password =contra;
try {
ZipFile zipFile = new ZipFile(source);
if (zipFile.isEncrypted()) {
zipFile.setPassword(password);
}
zipFile.extractAll(destination);
} catch (ZipException e) {
e.printStackTrace();
}
} 


//Funcion Encriptacion pgp con llavesRSA
private void encrypt(String pubKeyFile,String cipherTextFile,String plainTextFile) throws NoSuchProviderException, IOException, PGPException{
FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
FileOutputStream cipheredFileIs = new FileOutputStream(cipherTextFile);
PgpHelper.getInstance().encryptFile(cipheredFileIs, plainTextFile, PgpHelper.getInstance().readPublicKey(pubKeyIs), isArmored, integrityCheck);
cipheredFileIs.close();
pubKeyIs.close();
}
//Funcion Desencriptacion pgp con LlavesRSA
private void decrypt(String privKeyFile,String cipherTextFile,String decPlainTextFile,String passwd) throws Exception{
FileInputStream cipheredFileIs = new FileInputStream(cipherTextFile);
FileInputStream privKeyIn = new FileInputStream(privKeyFile);
FileOutputStream plainTextFileIs = new FileOutputStream(decPlainTextFile);
PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, passwd.toCharArray());
cipheredFileIs.close();
plainTextFileIs.close();
privKeyIn.close();
}


//Funcion Generador Llaves RSA        
private void genKeyPair(String privKeyFile,String pubKeyFile,String id,String password,int nx) throws InvalidKeyException, NoSuchProviderException, SignatureException, IOException, PGPException, NoSuchAlgorithmException {
RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();
Security.addProvider(new BouncyCastleProvider());
KeyPairGenerator    kpg = KeyPairGenerator.getInstance("RSA", "BC");
kpg.initialize(nx);//1024 //2048 //4098 
KeyPair                    kp = kpg.generateKeyPair();
FileOutputStream    out1 = new FileOutputStream(privKeyFile);
FileOutputStream    out2 = new FileOutputStream(pubKeyFile);
rkpg.exportKeyPair(out1, out2, kp.getPublic(), kp.getPrivate(), id, password.toCharArray(), isArmored);
}

//Funcion Eliminar Carpetas con Archivos
private static void funcionEliminarCarpeta1(File pArchivo) {
if (!pArchivo.exists()) { return; }
if (pArchivo.isDirectory()) {
for (File f : pArchivo.listFiles()) {
funcionEliminarCarpeta1(f);  }
}
pArchivo.delete();
} 



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtcn1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtllavepr = new javax.swing.JTextField();
        txtllavepub = new javax.swing.JTextField();
        cbx4096 = new javax.swing.JCheckBox();
        cbx2048 = new javax.swing.JCheckBox();
        cbx1024 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtentrada = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtsalida = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtcn = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        txtpubpriv = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        cbxSi = new javax.swing.JCheckBox();
        cbxNo = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Direccion Llave Privada:");

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Direccion Llave Publica:");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Contraseña:");

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/key.png"))); // NOI18N
        jButton3.setText("GENERAR LLAVES RSA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/cancelar.png"))); // NOI18N
        jButton4.setText("CANCELAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbx4096.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(cbx4096);
        cbx4096.setForeground(new java.awt.Color(255, 255, 255));
        cbx4096.setSelected(true);
        cbx4096.setText("4096");

        cbx2048.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(cbx2048);
        cbx2048.setForeground(new java.awt.Color(255, 255, 255));
        cbx2048.setText("2048");

        cbx1024.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(cbx1024);
        cbx1024.setForeground(new java.awt.Color(255, 255, 255));
        cbx1024.setText("1024");
        cbx1024.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx1024ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tamaño de Llave RSA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx1024)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx2048)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx4096)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcn1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtllavepub)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtid))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtllavepr, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtllavepr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtllavepub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx1024)
                    .addComponent(cbx2048)
                    .addComponent(cbx4096)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RAPTOR");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(33, 33, 33));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Direccion Entrada:");

        jButton6.setBackground(new java.awt.Color(33, 33, 33));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/buscar.png"))); // NOI18N
        jButton6.setText("Buscar");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton6.setFocusPainted(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Direccion Salida:");

        jButton7.setBackground(new java.awt.Color(33, 33, 33));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/buscar.png"))); // NOI18N
        jButton7.setText("Buscar");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contraseña:");

        jButton8.setBackground(new java.awt.Color(33, 33, 33));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/encriptacion.png"))); // NOI18N
        jButton8.setText("ENCRIPTAR");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(33, 33, 33));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/encriptacion.png"))); // NOI18N
        jButton9.setText("DESENCRIPTAR");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(33, 33, 33));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/cancelar.png"))); // NOI18N
        jButton10.setText("CANCELAR");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton10.setFocusPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("PGP CON LLAVES RSA Y ZIP4J");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" Direccion Llave Publica o Privada:");

        jButton11.setBackground(new java.awt.Color(33, 33, 33));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/buscar.png"))); // NOI18N
        jButton11.setText("Buscar");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton11.setFocusPainted(false);
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        cbxSi.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(cbxSi);
        cbxSi.setForeground(new java.awt.Color(255, 255, 255));
        cbxSi.setText("Si");

        cbxNo.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(cbxNo);
        cbxNo.setForeground(new java.awt.Color(255, 255, 255));
        cbxNo.setSelected(true);
        cbxNo.setText("No");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Borrar Archivo?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpubpriv, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbxSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxNo)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSi)
                    .addComponent(cbxNo)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtcn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtpubpriv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("GENERADOR DE LLAVES RSA");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
fc.showOpenDialog(null);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
txtentrada.setText(fl1);
}
catch(Exception e){
}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showSaveDialog(this);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
txtsalida.setText(fl1);
}catch(Exception e){
}
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// txtentrada txtsalida txtcn txtpubpriv
if(txtentrada.getText().length()!=0 && txtsalida.getText().length()!=0 && txtcn.getText().length()!=0 && txtpubpriv.getText().length()!=0){
rst="CifrarDatos";
t=new Thread(this);
t.start();
}else{
JOptionPane.showMessageDialog(null,"INGRESE CORRECTAMENTO LOS DATOS!!!");
}
/*
try {  
hoiagdsh(txtentrada.getText(),"xxxx.zip",txtcn.getText().toString()); 
encrypt(txtpubpriv.getText(),txtsalida.getText(),"xxxx.zip");
File f=new File("xxxx.zip");
f.delete();
if(cbxSi.isSelected()){
File fx=new File(txtentrada.getText());
funcionEliminarCarpeta1(fx);
fx.delete();
}
JOptionPane.showMessageDialog(null,"Datos Cifrados Exitosamente");
} catch (NoSuchProviderException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (PGPException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
}
*/
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
if(txtentrada.getText().length()!=0 && txtsalida.getText().length()!=0 && txtcn.getText().length()!=0 && txtpubpriv.getText().length()!=0){
rst="DesifrarDatos";
t=new Thread(this);
t.start();
}else{
JOptionPane.showMessageDialog(null,"INGRESE CORRECTAMENTO LOS DATOS!!!");
}
/*


if(txtentrada.getText().length()!=0&&txtsalida.getText().length()!=0 &&txtcn.getText().length()!=0&&txtpubpriv.getText().length()!=0){
try {
decrypt(txtpubpriv.getText(),txtentrada.getText(),"rxptxr.zip",txtcn.getText());
igadoghsg("rxptxr.zip",txtsalida.getText(),txtcn.getText());
File f=new File("rxptxr.zip");
f.delete();
JOptionPane.showMessageDialog(null,"Datos Desifrados Exitosamente");
} catch (Exception ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
}
}else{
JOptionPane.showMessageDialog(null,"Error!!!... Agregar Datos Correctamente!!!");
}    
*/
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
txtpubpriv.setText("");
txtentrada.setText("");
txtsalida.setText("");
txtcn.setText("");
cbxNo.setSelected(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showOpenDialog(null);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
txtpubpriv.setText(fl1);
}
catch(Exception e){
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showSaveDialog(this);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
txtllavepr.setText(fl1);
}catch(Exception e){
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try{
JFileChooser fc=new JFileChooser();
fc.showSaveDialog(this);
File fl=fc.getSelectedFile();
String fl1=fl.toString();
txtllavepub.setText(fl1);
}catch(Exception e){
}   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(txtid.getText().length()!=0 && txtllavepr.getText().length()!=0 && txtllavepub.getText().length()!=0 && txtcn1.getText().length()!=0){
rst="GenerarLlave";
t=new Thread(this);
t.start();}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
/*
try {
genKeyPair(txtllavepr.getText(),txtllavepub.getText(),txtid.getText(),txtcn1.getText());
JOptionPane.showMessageDialog(null,"Llaves Creadas Exitosamente");
} catch (InvalidKeyException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (NoSuchProviderException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (SignatureException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (PGPException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (NoSuchAlgorithmException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
}
*/        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
jDialog1.setSize(486,310);
jDialog1.setVisible(true);//setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));
ImageIcon img = new ImageIcon(RAPTOR.class.getResource("/org/book/ico/cryptoopen.png"));
jDialog1.setIconImage(img.getImage());
//[486, 220]
    }//GEN-LAST:event_jMenu1MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
txtid.setText("");      
txtllavepr.setText("");
txtllavepub.setText("");
txtcn1.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbx1024ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx1024ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx1024ActionPerformed

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
            java.util.logging.Logger.getLogger(RAPTOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RAPTOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RAPTOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RAPTOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RAPTOR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbx1024;
    private javax.swing.JCheckBox cbx2048;
    private javax.swing.JCheckBox cbx4096;
    private javax.swing.JCheckBox cbxNo;
    private javax.swing.JCheckBox cbxSi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtcn;
    private javax.swing.JPasswordField txtcn1;
    private javax.swing.JTextField txtentrada;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtllavepr;
    private javax.swing.JTextField txtllavepub;
    private javax.swing.JTextField txtpubpriv;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables
private Thread t;
private String rst;
@Override
public void run() {
if(rst.equals("CifrarDatos")){
try {  
hoiagdsh(txtentrada.getText(),"xxxx.zip",txtcn.getText().toString()); 
encrypt(txtpubpriv.getText(),txtsalida.getText(),"xxxx.zip");
File f=new File("xxxx.zip");
f.delete();
if(cbxSi.isSelected()){
File fx=new File(txtentrada.getText());
funcionEliminarCarpeta1(fx);
fx.delete();
}
JOptionPane.showMessageDialog(null,"Datos Encriptados Exitosamente");
} catch (NoSuchProviderException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
} catch (PGPException ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
}
}
else
if(rst.equals("DesifrarDatos")){
if(txtentrada.getText().length()!=0&&txtsalida.getText().length()!=0 &&txtcn.getText().length()!=0&&txtpubpriv.getText().length()!=0){
try {
decrypt(txtpubpriv.getText(),txtentrada.getText(),"rxptxr.zip",txtcn.getText());
igadoghsg("rxptxr.zip",txtsalida.getText(),txtcn.getText());
File f=new File("rxptxr.zip");
f.delete();
JOptionPane.showMessageDialog(null,"Datos Desencriptados Exitosamente");
} catch (Exception ex) {
Logger.getLogger(RAPTOR.class.getName()).log(Level.SEVERE, null, ex);
}
}else{
JOptionPane.showMessageDialog(null,"Error!!!... Agregar Datos Correctamente!!!");
} 
}
else
if(rst.equals("GenerarLlave")){
int nxx=0;
try {
if(cbx1024.isSelected()){
nxx=1024;
}else
if(cbx2048.isSelected()){
nxx=2048;
}
else
if(cbx4096.isSelected()){
nxx=4096;
}
genKeyPair(txtllavepr.getText(),txtllavepub.getText(),txtid.getText(),txtcn1.getText(),nxx);
JOptionPane.showMessageDialog(null,"Llaves Creadas Exitosamente");
} catch (InvalidKeyException | NoSuchProviderException | SignatureException | IOException | PGPException | NoSuchAlgorithmException ex) {
JOptionPane.showMessageDialog(null,ex);
}    
}


}

}
