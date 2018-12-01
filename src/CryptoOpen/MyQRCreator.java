package CryptoOpen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO; 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.EnumMap;

/**
*
* @author Edit. Josue Daniel Roldan Ochoa.
*/
public class MyQRCreator {

/** Constructor de clase */
public MyQRCreator(){}

/**
* Metodo que crea el QR
* @param String data Texto a codificar
* @param int size dimension (Alto,ancho) de la imagen a generar
* @return BufferedImage
*/
public BufferedImage createQR(String data, int size)
{
BitMatrix matrix;
Writer writer = new MultiFormatWriter();
try {            
EnumMap<EncodeHintType,String> hints = new EnumMap<EncodeHintType,String>(EncodeHintType.class);
hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");            
matrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);
ByteArrayOutputStream output = new ByteArrayOutputStream();
MatrixToImageWriter.writeToStream(matrix, "PNG", output);
byte[] data_array = output.toByteArray();
ByteArrayInputStream input = new ByteArrayInputStream(data_array);
return ImageIO.read(input);            
}catch (com.google.zxing.WriterException ex) {
System.err.println(ex.getMessage());
} catch (IOException ex) {
System.err.println(ex.getMessage());
}
return null;
}
public static void main(String[] args) {
}

}
