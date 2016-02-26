package br.edu.unifei.ControlePatrimonio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiaArquivo {
	
	public static void copyFiles(File sourceLocation , File targetLocation)
		    throws IOException {

		        if (sourceLocation.isDirectory()) {
		            if (!targetLocation.exists()) {
		                targetLocation.mkdir();
		            }
		            File[] files = sourceLocation.listFiles();
		            for(File file:files){
		                InputStream in = new FileInputStream(file);
		                OutputStream out = new FileOutputStream(targetLocation+"/"+file.getName());

		                // Copy the bits from input stream to output stream
		                byte[] buf = new byte[1024];
		                int len;
		                while ((len = in.read(buf)) > 0) {
		                    out.write(buf, 0, len);
		                }
		                System.out.println("teste " + out);
		                in.close();
		                out.close();
		            }            
		        }
}
}
	


