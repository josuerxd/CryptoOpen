/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package CryptoOpen;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
/**
*
* @author CryptoOpen.tk
*/
public class Hilo extends Thread {
private String shudguiug="iiohoiOIHSIOHDIOGHSIHGADOHUODHIUHIUO9aashiudghiuahdiuhiufiupssfsoihaoishipogwoeihqptuohpoihwioqehtiothwioehiohoIHIOHGIOSOIBOIXHOIBHOIVBCXIOVIOCXUPOIuxigfiohp9uhzp9xb98zbh8das89g8p99gqw8peg8p9h9weg8989h898a8s8h9f8p9hb89b8df8nhd89n8hp98h9a89hnf9p8h98989s89ph89qh898h988H9SH9DFHP98SHA89SHD9GT9UQWEUT98H89TH9W8PHGTQ8PG89GTEWQ9TG89WQ89TG9G89G89ZGX9PHBFIOHSAHA9H89SS8BHFZC89XC8P9PX98B89A9S8P89D8F9WQE8Y989R8989G89G89G80XXXXXXXXXXXXXXXXXXXXXXXXXXXXZVGAS989809QEW80RH98H9T8H9B9W89QTB98WT90H98H098XH8ZXBY8YSA0980Y98YR98YWE8YR0YWQE0TH032G107489-Y5TG238705G0258857G805G38702G5870G3587G3275G3287058703G25870G28705G32G0G8G87SAS8G707GY0W9EYGH9H09H709ha9hsg0ashdg09h98eg89wqb809tbew09qbet809wq89eh890h09jzyx98cub098ub9zxnxbfnaism9dgem9qwej98rh9wehtwh0q7t80hashd870gqweuht208913h5183525g8-7099pabgt0-9w8t9q-te89wht89qwh-9thw89h98hz89hxg989ghas-98h98h8-9H-89HZX8-9G98SHDAG89SH9D8G-9ASN-98GN-A89N89GNDS8-9AG8ZNX-9NB8-9NB98XNDB-89ASG9N9AW8-NET89NASG-9W8HQWE8-9HT28-93HT89H93H98H21T0920397H-AS89S9AHG98WE-8HGQ-89HG89EGH8G89AH9HS98DSHB89SHD8H8XCU0B89HCB-HS870HD9AHG0HDG89HAS9D8GHAS89DHG809ASH0G7HS0GH9ADG7HSADG9SAHGH0WQE-T8HQ98EH-98HTW89HT8-9HW-89QEHT89QHT89HZ8XH89HBH-XB8S8-9HG8-9SAHDSH9GH9SHAG9-HW89EQN98N89ENT98W9E8HT898T9-HET-9H-9hs98h89ghg8h9h9ewh8eth9wh9et8h989q8eht89hx9y9yyzx98y98ygs8ahyya87gy9qwy98ehq-9utb9quebtu9webt-9b93289-5h-985h-7327-9h9236-923h79h8has7-9th779BH-G9SA8DGNAS89GD8-9ADGBSA89DG-9WHE-98GHQW98-HETEWH-9H9EW8QHT9EWHQ-9T8H-EW9Q-TEWH8";    
private String nombre="";
private String entrada="";
private String salida="";
private String contra="";
public Hilo( String nombre,String entrada,String salida, String contra,String ooisjgadi) {
super(nombre);
if(ooisjgadi.equals(shudguiug)) {
this.nombre=nombre;
this.entrada=entrada;
this.salida=salida;
this.contra=contra;


}else{
System.exit(0);
}
}
public void run(){
if(nombre.equals("EncriptarZip")){
hoiagdsh(entrada,salida,contra);
}
else if(nombre.equals("DesencriptarZip")){
igadoghsg(entrada,salida,contra);
}



}

private void hoiagdsh(String entrada,String salida,String contra){
File fl=new File(entrada);
if(fl.exists()){
if(fl.isFile()){


try {


ZipFile zipFile = new ZipFile(salida+".zip");

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


ZipFile zipFile = new ZipFile(salida+".zip");

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

}