package com.soft224.coranapp;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImgToBytes {
    public ImgToBytes(){
        try {
            File img = new File("testeImg.png");
            //System.out.println("file path "+img.getAbsolutePath()+". Exists "+img.exists());
            BufferedImage bt = ImageIO.read(img);
            WritableRaster raster = bt .getRaster();
            DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
            byte[] imBytes=data.getData();
            ByteArrayInputStream reader= new ByteArrayInputStream(imBytes);
            byte[] bt2=new byte[reader.available()];
            for (byte imByte : bt2) {
                int rd=reader.read();
                char b = (char)rd;
                System.out.println("bytes: " + imByte);
                System.out.println("char: " + b);
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] arg){
        new ImgToBytes();
    }
}
