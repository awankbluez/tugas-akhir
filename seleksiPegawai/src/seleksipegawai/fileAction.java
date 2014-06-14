/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author anakbaiksekali
 */

public class fileAction {
    //attributes
    private final static String FILE_CLIENT_DB_CONFIG = "db_config.txt";
    //private final static String FILE_CLIENT_CONFIG = "client_config.txt";
    private String serverAddr, serverPort, dbClientPort, dbClientAddr, dbName, dbClientUsername, dbClientPassword;
    
    //preferred variables
    private StringBuffer contentFileBuff = new StringBuffer();
    private String stringConvert, contentLine;
    private String arrTemp[];
    private final static int SERVER_ADDR = 0, SERVER_PORT = 1;
    private final static int DB_CLIENT_ADDR = 0, DB_CLIENT_PORT = 1, DB_CLIENT_USERNAME = 2, DB_CLIENT_PASSWORD = 3, DB_NAME = 4;
    private final static int TOTAL_CONFIG = 2;

    public fileAction() {
        specifyConfiguration();
    }
    
    private synchronized void specifyConfiguration(){
        readFile(FILE_CLIENT_DB_CONFIG);
        arrTemp = stringConvert.split("\n");
        dbClientAddr = arrTemp[DB_CLIENT_ADDR];
        dbClientPort = arrTemp[DB_CLIENT_PORT];
        dbName = arrTemp[DB_NAME];
        dbClientUsername = arrTemp[DB_CLIENT_USERNAME];
        dbClientPassword = arrTemp[DB_CLIENT_PASSWORD];
        
        
    }
    
    public void readFile(String fileInput){
        //contentnya di flush!
        if (contentFileBuff.length()>0)
            contentFileBuff.delete(0, contentFileBuff.length());
        try{
            BufferedReader readFile = new BufferedReader(new FileReader(fileInput));
            do{
                if(contentLine!=null)
                    contentFileBuff.append(contentLine + "\n");
                contentLine = readFile.readLine();
            }while(contentLine!=null);
            readFile.close();
        }catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null, "File: " + fileInput + " tidak ditemukan", "File Not Found!", JOptionPane.ERROR_MESSAGE);
        }catch (IOException ioe){
            JOptionPane.showMessageDialog(null, "Proses membaca file error", "IO Error", JOptionPane.ERROR_MESSAGE);
        }
        stringConvert = contentFileBuff.toString();
    }    
    
    public void setConfiguration(String sa, String sp){
        String config = "";
        serverAddr = sa;
        serverPort = sp;
        config = serverAddr + "\n" + serverPort;
        //writeFile(config, FILE_CLIENT_CONFIG, "Configuration saved!");
    }
    
    public void writeFile(String strContentTemp, String fileName, String msgInfo){
        try{
            FileWriter writeFile = new FileWriter(fileName);
            writeFile.write(strContentTemp);
            writeFile.close();
            JOptionPane.showMessageDialog(null, msgInfo, "File save success", JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }        
    }
    
    public void writeFile(String strContentTemp, String fileName){
        writeFile(strContentTemp, fileName, "File saved!");
    }
    
    public String getServerAddr() {
        return serverAddr;
    }

    public String getServerPort() {
        return serverPort;
    }
    public String getDbName() {
        return dbName;
    }

    public String getDbClientAddr() {
        return dbClientAddr;
    }

    public String getDbClientPassword() {
        return dbClientPassword;
    }

    public String getDbClientUsername() {
        return dbClientUsername;
    }

    public String getDbClientPort() {
        return dbClientPort;
    }
}