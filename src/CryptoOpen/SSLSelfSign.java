/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package CryptoOpen;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.security.auth.x500.X500Principal;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V1CertificateGenerator;

/**
*
* @author CryptoOpen.tk
*/
public class SSLSelfSign extends javax.swing.JFrame {
private static KeyPair keyPair=null;
private Toolkit t= Toolkit.getDefaultToolkit();
private Dimension tamañoPantalla=t.getScreenSize();
private int altura=tamañoPantalla.height;
private int ancho=tamañoPantalla.width;
/**
* Creates new form SSLSelfSign
*/
public SSLSelfSign() {
initComponents();
setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/org/book/ico/cryptoopen.png")));
this.setLocationRelativeTo(null);
try {

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String sourceDate=dateFormat.format(date);
jTextField7.setText(sourceDate);

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

Date myDate = format.parse(sourceDate);
Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
cal.add(Calendar.DATE, 365*3); //minus number would decrement the days
myDate= cal.getTime();
String sourceDate2=dateFormat.format(myDate);
jTextField9.setText(sourceDate2);
//  jTextField7.setText(format.toString());
} catch (ParseException ex) {
//Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
}
}
private void generarCertificado(Integer kys) throws CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, CertificateException, ParseException{
Security.addProvider(new BouncyCastleProvider());

String fechapr =jTextField7.getText();

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

Date validityBeginDate = format.parse(fechapr);
// in 2 years
String sourceDate =jTextField9.getText();


Date myDate = format.parse(sourceDate);

/*
Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
cal.add(Calendar.DATE, 365*3); //minus number would decrement the days
myDate= cal.getTime();
*/

Date validityEndDate =  myDate;

// GENERATE THE PUBLIC/PRIVATE RSA KEY PAIR
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA","BC");
keyPairGenerator.initialize(kys, new SecureRandom());

 keyPair = keyPairGenerator.generateKeyPair();

// GENERATE THE X509 CERTIFICATE
X509V1CertificateGenerator certGen = new X509V1CertificateGenerator();
/*
certGen.AddExtension(
X509Extensions.BasicConstraints.Id,true, new BasicConstraints(true));
*/


X500Principal dnName = new X500Principal("CN="+jTextField1.getText()+", O="+jTextField2.getText()+",OU="+jTextField3.getText()+", L="+jTextField4.getText()+", ST="+jTextField5.getText()+", C="+jTextField6.getText()+"");
//X500Principal dnName2 = new X500Principal("CN=cryptoopen.tk, O=CryptoOpen,OU=CryptoOpen, L=Guatemala, ST=Guatemala, C=GT");
String cx=("MIIDzDCCArSgAwIBAgIJALGosDH5dcOxMA0GCSqGSIb3DQEBCwUAMHsxCzAJBgNV BAYTAkdUMRIwEAYDVQQIDAlHdWF0ZW1hbGExEjAQBgNVBAcMCUd1YXRlbWFsYTET MBEGA1UECgwKQ3J5cHRvT3BlbjETMBEGA1UECwwKQ3J5cHRvT3BlbjEaMBgGA1UE AwwRd3d3LmNyeXB0b29wZW4udGswHhcNMTcwOTI5MDczMzAxWhcNMjAwNzE5MDcz MzAxWjB7MQswCQYDVQQGEwJHVDESMBAGA1UECAwJR3VhdGVtYWxhMRIwEAYDVQQH DAlHdWF0ZW1hbGExEzARBgNVBAoMCkNyeXB0b09wZW4xEzARBgNVBAsMCkNyeXB0 b09wZW4xGjAYBgNVBAMMEXd3dy5jcnlwdG9vcGVuLnRrMIIBIjANBgkqhkiG9w0B AQEFAAOCAQ8AMIIBCgKCAQEAwzCJ3L3TaZ4jfMAbdTEMw5H/v1TenmkHN25BspCo sZ6wsmD8dMVjhiDb+ogQ8glEBEMpZy5KlXciPBpSbiyncv8aLjr/bI6XAlaHJIA3 cuTRQxY8RUvOPjxhvU/kyVHNeyKsJLn0nvkKVkbnd2YOPkFXIC86VC6tNa8slXjo sd+JOIumBgCJTZsE7BW2GZT4U8OML61qpwqW4D1YIiiN15zlyYkhQovoJOjcqTOt g/ELt9MlKGdTZYrH7g8OId8WnvOvVHTGPqGeEv53zymlxo5B06e7LVCNtk9T2wbn i1N9/Ls+McpFMxL7Vlyadjaby4JlbMkmvuy8+oo5dG635wIDAQABo1MwUTAdBgNV HQ4EFgQUmwJnrc6RN+R0ienMc4n+mIUxOM8wHwYDVR0jBBgwFoAUmwJnrc6RN+R0 ienMc4n+mIUxOM8wDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQsFAAOCAQEA jxBqRiaOWEvxLVkdzY2fdLptKe84nf0V3b9IUiU9/eh2WS3EpcIhxeDL3xr9sQhP z94CtUfuyuThB0ljd2my8kNP963evZIsPr5RYWNr8RCY8womXqWlLGkfrSogGsOB fMQ7Ewha2uClsxuAbpTqPdUvjU2rXOkUlJTKyaj3LHylO5DwYxnMsxIupDS1F79X Fc0Cgnbm/0D/u+xt0cduOJmrNwS1K9rD5ZMjRTlitkj5GK/qi7POW1lzevnj4skR xlB7XeI9Z6wcWUVPHM4eK4h/WMyGADxcLGsAKjQnjX165qavcGdStP6UctDCKWh7 qYwfVJ6Kjhk9u65K+aJFIg==") ;



CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
byte[] bytes = Base64.decodeBase64(cx);
InputStream in = new ByteArrayInputStream(bytes);
X509Certificate certx = (X509Certificate)certFactory.generateCertificate(in);
X509Principal principal = PrincipalUtil.getSubjectX509Principal(certx);
Vector<?> values = principal.getValues(X509Name.CN);


String cn = (String) values.get(0);
//System.out.println(cn);
// using X500Principal
//dnName = certx .getSubjectX500Principal();

String arr=values.toString();
//System.out.println(arr);




certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
certGen.setSubjectDN(dnName);
certGen.setIssuerDN( dnName ); // use the same
certGen.setNotBefore(validityBeginDate);
certGen.setNotAfter(validityEndDate);
certGen.setPublicKey(keyPair.getPublic());
certGen.setSignatureAlgorithm("SHA512WithRSAEncryption");

X509Certificate cert = certGen.generate(keyPair.getPrivate(),"BC");
/*
// DUMP CERTIFICATE AND KEY PAIR
BASE64Encoder encoder = new BASE64Encoder();
out.println(X509Factory.BEGIN_CERT);
encoder.encodeBuffer(cert.getEncoded(), out);
out.println(X509Factory.END_CERT);

*/
// System.out.println("CERTIFICATE TO_STRING");

//     System.out.println();
//System.out.println(cert);
//  System.out.println();


//System.out.println("CERTIFICATE PEM (to store in a cert-johndoe.pem file)");

String c=convertToPem(cert);
jTextArea1.setText(c);
//System.out.println(c);
//System.out.println("");

PrivateKey k=  keyPair.getPrivate();  

String px=convertKeyToPem(k);
jTextArea2.setText(px);
//System.out.println(px);

/*
String password="password";
byte[] encryptedPkcs8 = encryptPrivateKey(password, keyPair);

FileOutputStream fos = new FileOutputStream("privkey.p8");
fos.write(encryptedPkcs8);
fos.close();
*/


}

protected static String convertToPem(X509Certificate cert) throws CertificateEncodingException {
Base64 encoder = new Base64(64);
String cert_begin = "-----BEGIN CERTIFICATE-----\n";
String end_cert = "-----END CERTIFICATE-----";

byte[] derCert = cert.getEncoded();
String pemCertPre = new String(encoder.encode(derCert));
String pemCert = cert_begin + pemCertPre + end_cert;
return pemCert;
}


protected static String convertKeyToPem(PrivateKey key) throws CertificateEncodingException {
Base64 encoder = new Base64(64);
String cert_begin = "-----BEGIN PRIVATE KEY-----\n";
String end_cert = "-----END PRIVATE KEY-----";

byte[] derCert = key.getEncoded();
String pemCertPre = new String(encoder.encode(derCert));
String pemCert = cert_begin + pemCertPre + end_cert;
return pemCert;
}
    private static byte[] encryptPrivateKey(String password, KeyPair keyPair)
    throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
    InvalidKeySpecException, NoSuchPaddingException,
    InvalidAlgorithmParameterException, IllegalBlockSizeException,
    BadPaddingException, InvalidParameterSpecException, IOException
{
    int count = 100000; // hash iteration count, best to leave at default or increase
    return encryptPrivateKey(password, keyPair, count);
}

    private static byte[] encryptPrivateKey(String password, 
        KeyPair keyPair, int count) throws NoSuchAlgorithmException,
        NoSuchProviderException, InvalidKeySpecException,
        NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException,
        BadPaddingException, InvalidParameterSpecException, IOException
{
    // extract the encoded private key, this is an unencrypted PKCS#8 private key
        byte[] encodedprivkey = keyPair.getPrivate().getEncoded();

        // Use a PasswordBasedEncryption (PBE) algorithm, OID of this algorithm will be saved
        // in the PKCS#8 file, so changing it (when more standard algorithm or safer
        // algorithm is available) doesn't break backwards compatibility.
        // In other words, decryptor doesn't need to know the algorithm before it will be
        // able to decrypt the PKCS#8 object.
        String encAlg = BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId();

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Create PBE parameter set
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFac = SecretKeyFactory.getInstance(encAlg, "BC");
        SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

        Cipher pbeCipher = Cipher.getInstance(encAlg, "BC");

        // Initialize PBE Cipher with key and parameters
        pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

        // Encrypt the encoded Private Key with the PBE key
        byte[] ciphertext = pbeCipher.doFinal(encodedprivkey);

        // Now construct  PKCS #8 EncryptedPrivateKeyInfo object
        AlgorithmParameters algparms = AlgorithmParameters.getInstance(encAlg, "BC");
        algparms.init(pbeParamSpec);
        EncryptedPrivateKeyInfo encinfo = new EncryptedPrivateKeyInfo(algparms, ciphertext);

        // DER encoded PKCS#8 encrypted key
        byte[] encryptedPkcs8 = encinfo.getEncoded();

        return encryptedPkcs8;
    }

/**
* This method is called from within the constructor to initialize the form.
* WARNING: Do NOT modify this code. The content of this method is always
* regenerated by the Form Editor.
*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        jPanel2.setBackground(new java.awt.Color(33, 33, 33));

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Contraseña:");

        jButton7.setText("Aceptar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancelar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SSLSELFSIGN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Common Name:");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Organization:");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Organization Unit:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Locality:");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("State: ");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Country: ");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Certificate(PEM FORMAT):");

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Generar Certificado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Private Key(PEM FORMAT):");

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Guardar Certificado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Key-");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4096", "2048", "1024" }));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/book/ico/ssl(1).png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Guardar Llave Privada");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Inicio:");

        jTextField7.setText("2017-09-29");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Extender:");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("365");
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("2020-09-29");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("FInal:");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Duracion:");

        jButton5.setBackground(new java.awt.Color(33, 33, 33));
        jButton5.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Guardar Certificado y Llave Privada");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(33, 33, 33));
        jButton6.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Guardar Llave Privada.p8");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("*");

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("3");
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(21, 21, 21)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(405, 405, 405))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField5)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField6)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField3)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField4)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField1)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField2)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(350, 350, 350))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel12)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("Check Certificate");
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
if(jTextField1.getText().length()!=0 && jTextField2.getText().length()!=0 && jTextField3.getText().length()!=0 && jTextField4.getText().length()!=0 && jTextField5.getText().length()!=0 && jTextField6.getText().length()!=0 && jTextField7.getText().length()!=0 && jTextField9.getText().length()!=0){
try {
generarCertificado(Integer.parseInt((String) jComboBox1.getSelectedItem()));        // TODO add your handling code here:
} catch (IllegalStateException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (NoSuchProviderException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (NoSuchAlgorithmException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (SignatureException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (InvalidKeyException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (CertificateException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
} catch (ParseException ex) {
Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
}
}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(jTextArea2.getText().length()!=0){
try
{
String nombre="";
javax.swing.JFileChooser file=new javax.swing.JFileChooser();
file.showSaveDialog(this);
File guarda =file.getSelectedFile();
if(guarda !=null)
{
nombre=file.getSelectedFile().getName();
/*guardamos el archivo y le damos el formato directamente,
* si queremos que se guarde en formato doc lo definimos como .doc*/
FileWriter  save=new FileWriter(guarda+".key");
save.write(jTextArea2.getText());
save.close();
JOptionPane.showMessageDialog(null,
"El archivo se a guardado Exitosamente",
"Informaci�n",JOptionPane.INFORMATION_MESSAGE);
}
}
catch(IOException ex)
{
JOptionPane.showMessageDialog(null,
"Su archivo no se ha guardado",
"Advertencia",JOptionPane.WARNING_MESSAGE);
}        
  }else{
JOptionPane.showMessageDialog(null,"DATOS INCORRECTO!!");
}   
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
if(jTextArea1.getText().length()!=0 && jTextArea2.getText().length()!=0){
try
{
String nombre="";
javax.swing.JFileChooser file=new javax.swing.JFileChooser();
file.showSaveDialog(this);
File guarda =file.getSelectedFile();
if(guarda !=null)
{
nombre=file.getSelectedFile().getName();
/*guardamos el archivo y le damos el formato directamente,
* si queremos que se guarde en formato doc lo definimos como .doc*/
FileWriter  save=new FileWriter(guarda+".crt");
save.write(jTextArea1.getText());
save.close();

FileWriter  save2=new FileWriter(guarda+".key");
save2.write(jTextArea1.getText());
save2.close();

JOptionPane.showMessageDialog(null,
"El archivo se a guardado Exitosamente",
"Informaci�n",JOptionPane.INFORMATION_MESSAGE);
}
}
catch(IOException ex)
{
JOptionPane.showMessageDialog(null,
"Su archivo no se ha guardado",
"Advertencia",JOptionPane.WARNING_MESSAGE);
} 
  }else{
JOptionPane.showMessageDialog(null,"DATOS INCORRECTO!!");
}   
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(jTextArea1.getText().length()!=0){
try
{
String nombre="";
javax.swing.JFileChooser file=new javax.swing.JFileChooser();
file.showSaveDialog(this);
File guarda =file.getSelectedFile();
if(guarda !=null)
{
nombre=file.getSelectedFile().getName();
/*guardamos el archivo y le damos el formato directamente,
* si queremos que se guarde en formato doc lo definimos como .doc*/
FileWriter  save=new FileWriter(guarda+".crt");
save.write(jTextArea1.getText());
save.close();
JOptionPane.showMessageDialog(null,
"El archivo se a guardado Exitosamente",
"Informaci�n",JOptionPane.INFORMATION_MESSAGE);
}
}
catch(IOException ex)
{
JOptionPane.showMessageDialog(null,
"Su archivo no se ha guardado",
"Advertencia",JOptionPane.WARNING_MESSAGE);
}   }else{
JOptionPane.showMessageDialog(null,"DATOS INCORRECTO!!");
}     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
jTextArea1.setText("");
jTextArea2.setText("");
jTextField1.setText("");
jTextField2.setText("");
jTextField3.setText("");
jTextField4.setText("");
jTextField5.setText("");
jTextField6.setText("");
jComboBox1.setSelectedItem("4096");
try {

this.setLocationRelativeTo(null);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String sourceDate=dateFormat.format(date);
jTextField7.setText(sourceDate);

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

Date myDate = date;
Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
cal.add(Calendar.DATE, 365*3); //minus number would decrement the days
myDate= cal.getTime();
String sourceDate2=dateFormat.format(myDate);
jTextField9.setText(sourceDate2);
Integer g=365;
jTextField8.setText(g.toString());
g=3;
jTextField10.setText(g.toString());
//  jTextField7.setText(format.toString());
} catch (Exception ex) {
//Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
try {

this.setLocationRelativeTo(null);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();


SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

Date myDate = format.parse(  jTextField7.getText());
Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
String nx=jTextField8.getText();
String nx2=jTextField10.getText();
String[]nnx=new String [2];
Integer in= 0;
Integer in2= 0;

in=Integer.parseInt(nx);
in2=Integer.parseInt(nx2);


cal.add(Calendar.DATE, in*in2); //minus number would decrement the days
myDate= cal.getTime();
String sourceDate2=dateFormat.format(myDate);
jTextField9.setText(sourceDate2);
//  jTextField7.setText(format.toString());
} catch (Exception ex) {

}   



    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
try {

this.setLocationRelativeTo(null);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();


SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

Date myDate = format.parse(  jTextField7.getText());
Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
String nx=jTextField8.getText();
String nx2=jTextField10.getText();
String[]nnx=new String [2];
Integer in= 0;
Integer in2= 0;

in=Integer.parseInt(nx);
in2=Integer.parseInt(nx2);


cal.add(Calendar.DATE, in*in2); //minus number would decrement the days
myDate= cal.getTime();
String sourceDate2=dateFormat.format(myDate);
jTextField9.setText(sourceDate2);
//  jTextField7.setText(format.toString());
} catch (Exception ex) {

}         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        int k = (int) evt.getKeyChar();
        if (k > 58 && k < 255) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Solo puede Ingresar numeros!!!", "Validando Datos",
                JOptionPane.ERROR_MESSAGE);

        }
        int d = (int) evt.getKeyChar();
        if (d > 32 && d < 47) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Solo puede Ingresar numeros!!!", "Validando Datos",
                JOptionPane.ERROR_MESSAGE);

        }          // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped

        int k = (int) evt.getKeyChar();
        if (k > 58 && k < 255) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Solo puede Ingresar numeros!!!", "Validando Datos",
                JOptionPane.ERROR_MESSAGE);

        }
        int d = (int) evt.getKeyChar();
        if (d > 32 && d < 47) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Solo puede Ingresar numeros!!!", "Validando Datos",
                JOptionPane.ERROR_MESSAGE);

        }        
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    if(keyPair!=null){
        jDialog1.setSize(460, 235);
        jTextField11.setText("");
jDialog1.setLocation(ancho/2 - jDialog1.getWidth()/2, altura/2 - jDialog1.getHeight()/2);
jDialog1.setVisible(true);
jDialog1.setResizable(false);    

        
    }else{
JOptionPane.showMessageDialog(null,"DATOS INCORRECTO!!");
}  
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
if(jTextField11.getText().length()!=0)     {
        
           try {
               
               javax.swing.JFileChooser file=new javax.swing.JFileChooser();
file.showSaveDialog(this);
String nombre =file.getSelectedFile().toString();

        String password=jTextField11.getText().toString();
        byte[] encryptedPkcs8 = encryptPrivateKey(password, keyPair);
        
        FileOutputStream fos = new FileOutputStream(nombre+".p8");
        fos.write(encryptedPkcs8);
        fos.close();   
        
                   jDialog1.dispose();
           JOptionPane.showMessageDialog(null,"ARCHIVO GENERADO EXITOSAMENTE!!!");
    } catch (IOException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeyException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchProviderException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeySpecException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchPaddingException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidAlgorithmParameterException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalBlockSizeException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidParameterSpecException ex) {
        Logger.getLogger(SSLSelfSign.class.getName()).log(Level.SEVERE, null, ex);
    }

}else{
JOptionPane.showMessageDialog(null,"INGRESE DATOS CORRECTAMENTE!!!");
}
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
jTextField11.setText("")  ;
jDialog1.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
Desktop desktop = Desktop.getDesktop();	    URI uri = null;
try {
uri = new URI("https://www.sslshopper.com/csr-decoder.html");
} catch (URISyntaxException ex) {

}
try {
desktop.browse(uri);
} catch (IOException ex) {

}  
//https://www.sslshopper.com/csr-decoder.html
    }//GEN-LAST:event_jMenu1MousePressed

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
            java.util.logging.Logger.getLogger(SSLSelfSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SSLSelfSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SSLSelfSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SSLSelfSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SSLSelfSign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JPasswordField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
