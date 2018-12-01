package CryptoOpen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Edit. Josue Daniel Roldan Ochoa.
 */
public class QRDialog extends javax.swing.JDialog {

    /**
     * Creates new form QRDialog
     */
BufferedImage bufferedImage;
public QRDialog(java.awt.Frame parent, boolean modal) {
    
super(parent, modal);
initComponents();
setTitle("QR");
jLabel1.setText("");
jLabel1.setToolTipText("Click para guardar");
       
setBackground(Color.white);
}
    
public void setImageQR(BufferedImage bufferedImage)
{
if(bufferedImage!=null)
{
this.bufferedImage = bufferedImage;
ImageIcon icon = new ImageIcon(bufferedImage);            
setSize(icon.getIconWidth()+20,icon.getIconHeight()+40);
jLabel1.setIcon( icon );   
jLabel1.setSize(icon.getIconWidth()+20,icon.getIconHeight()+40);

}
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 200, 190);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Guardar QR");
FileFilter filter = new FileNameExtensionFilter("QR Image", "PNG");
fileChooser.addChoosableFileFilter(filter);
int userSelection = fileChooser.showSaveDialog(this);
if (userSelection == JFileChooser.APPROVE_OPTION)
{
File fileToSave = fileChooser.getSelectedFile();
if (!fileToSave.toString().endsWith(".png"))
{
fileToSave = new File(fileChooser.getSelectedFile()+".png");
}
try {
ImageIO.write(bufferedImage, "png", fileToSave);
} catch (IOException ex) {
System.err.println( ex.getMessage() );
}
dispose();
}        
      
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(QRDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QRDialog dialog = new QRDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
