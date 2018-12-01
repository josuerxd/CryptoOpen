/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.cryptoopen.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
*
* @author root
*/
public class EncDecF {
private static boolean asciiArmored = false;
private static boolean integrityCheck = true;   


public static boolean encrypt(String keyFile,String outputFile,String inputFile) throws Exception {
FileInputStream keyIn = new FileInputStream(keyFile);
FileOutputStream out = new FileOutputStream(outputFile);
PgpH.encryptFile(out, inputFile, PgpH.readPublicKey(keyIn),
asciiArmored, integrityCheck);
out.close();
keyIn.close();
return true;
}

public static  boolean decrypt(String sad2,String keyFile,String outputFile,String inputFile,String passphrase) throws Exception {
File fichero = new File(keyFile);
FileWriter fw = new FileWriter(fichero);
fw.write(sad2);
fw.flush();  
FileInputStream in = new FileInputStream(inputFile);
FileInputStream keyIn = new FileInputStream(keyFile);
fichero.delete();

FileOutputStream out = new FileOutputStream(outputFile);
PgpH.decryptFile(in, out, keyIn, passphrase.toCharArray());
in.close();
out.close();
keyIn.close();
return true;
}
}
