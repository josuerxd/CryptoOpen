package org.cryptoopen.helper;

/**
 *
 * @author Edit. Josue Daniel Roldan Ochoa.
 */
import CryptoOpen.BlackQR;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LeerQr {
public LeerQr(){
}    
    
private Reader lector = new MultiFormatReader();

private BufferedImage imagen; 
private String dr="";
public void leer(String dir) throws IOException, NotFoundException{
dr=dir;
File ubicacionImagen = new File(dr);    
if(ubicacionImagen.exists()){
imagen = ImageIO.read(ubicacionImagen);
LuminanceSource fuente = new BufferedImageLuminanceSource(imagen);
BinaryBitmap mapaBits = new BinaryBitmap(new HybridBinarizer(fuente));
Result resultado = null; 
try {
resultado = lector.decode(mapaBits);
} catch (ChecksumException ex) {
} catch (FormatException ex) {
}
BlackQR.respuesta(resultado.getText());
} 
}
}
