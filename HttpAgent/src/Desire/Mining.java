/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author JohnFlorez
 */
public class Mining {
    
    private boolean pathExists;
    private AnswerD answerD;

    public Mining() {
    }
    
    public boolean pathExistsInBlockChainContent(Socket connect, 
            String pathRequested) throws IOException{
        //Desde acá el valida en nuestros archivos planos creados 
        //si la ruta existe por lo menos en la cantidad determinada de acuerdo
        //a los archivos planos creados y retorna una respuesta.
        //esto llamaria al componente AnswerD para procesar si da una respuesta
        //rapida sin tener que ir a nuestro módulo de INTENSION
        //envía dos parámetros (ruta , estado (true or false)
        
        File folder = new File("/Users/luisa/Desktop/Http Agent/http_agent/HttpAgent/src/Repository/Certifiers/");
        File[] listOfFiles = folder.listFiles();
        
        int pathFilesFound = 0;
        for(File file: listOfFiles){
            
            System.out.println(file.getName());
            Scanner scanner = new Scanner(file);
            //System.out.println(file.getPath());
            
            //String currentLine;
            int lineNumber = 0;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                //System.out.println(line);
                lineNumber++;             
                if(pathRequested.equals(line)){
                    pathFilesFound++;
                    System.out.println(lineNumber);
                }
            }
        }
        System.out.println("Path encontrado en esta cantidad de archivos: "+pathFilesFound);
        
       //luego de validar sucedería esto.
        AnswerD answerd = new AnswerD();
        answerd.sendMessage(connect,pathRequested, pathExists);
        
        
        return true;
    }
    
}
