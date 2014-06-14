/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

/**
 *
 * @author 
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DBconn {
    //private String dbServer, dbName, dbUser, dbPassword;
    private Connection conn;
    private fileAction fr;
    private com.mysql.jdbc.Driver driverDB;
    private Statement stateSql = null;
    private ResultSet rsSql = null;

    
	public void closeConn(){
		try{
			conn.close();
		}
                catch(com.mysql.jdbc.CommunicationsException e){
                    System.out.println("Koneksi ke database MySQL gagal, cek koneksi anda!");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
        
        public boolean doConnectDB(String serverAddr, String serverPort, String username, String password, String dbName){
      
        boolean retVal = false;
        try{
            //System.out.println("Locating database server...");
            driverDB = new com.mysql.jdbc.Driver();
            conn = DriverManager.getConnection("jdbc:mysql://" + serverAddr + ":" + serverPort+
                    "/", username, password);
            conn.setCatalog(dbName);
            retVal = true;
            //System.out.println("Database server is up!");
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, "Tidak tersambung ke server database", 
                    "SQL Error!", JOptionPane.ERROR_MESSAGE);
            se.printStackTrace();
            retVal = false;
            System.out.println("Database server is down, please check database file configuration...");
        }finally{
            return retVal;
        }
    }
        
        public boolean executeQuery(String query){
        boolean retVal = false;
        try{
            stateSql = getConn().createStatement();
            if (stateSql.execute(query)){
                retVal = true;
            }
            retVal = true;
        }catch(SQLException se){
            retVal = false;
            se.printStackTrace();
        }finally{
            System.out.println(retVal);
            return retVal;
        }
    }
        public void selectQuery(String querySelect){
        String strRes = "";
        try{
            stateSql = getConn().createStatement();
            rsSql = stateSql.executeQuery(querySelect);
            while(!rsSql.isLast()){
                rsSql.next();
                strRes = rsSql.getString(2);
                System.out.println(strRes);
            }
        }catch(SQLException se){
            se.printStackTrace();
        }        
    }
        public ResultSet execQueryku(String querySelect){
            System.out.println(querySelect);

            try{
                stateSql = getConn().createStatement();
                rsSql = stateSql.executeQuery(querySelect);
                
            }catch(SQLException se){
                 
                se.printStackTrace();
            }
            return rsSql;
        }
        
        public String[][] execQuery(String whatToExec){
	
		String temp[][] = new String[400][32];
		int tempCount =0;
		try {
			Statement view = getConn().createStatement();
                        ResultSet rs = view.executeQuery(whatToExec);
			while(rs.next()){
                            for(int i=0;i<18;i++){
                                try{
                                    temp[tempCount][i] = rs.getString(i+1);
                                }catch(SQLException e){
                                    break;
                                }
                            }
                            tempCount++;
			}
			
			rs.close();
			view.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
                //System.out.println("lili+"+temp[0][0]);
		return temp;
	}

        public double [][] execdoubleQuery(String whatToExec){

		double temp[][] = new double[400][32];
		int tempCount =0;
		try {
			Statement view = getConn().createStatement();
                        ResultSet rs = view.executeQuery(whatToExec);
			while(rs.next()){
                            for(int i=0;i<18;i++){
                                try{
                                    temp[tempCount][i] = rs.getDouble(i+1);

                                }catch(SQLException e){
                                    break;
                                }
                            }
                            tempCount++;
			}

			rs.close();
			view.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
                //System.out.println("lili+"+temp[0][0]);
		return temp;
	}

        public void execUpdate(String whatToExec){

            try{
                stateSql = getConn().createStatement();
                stateSql.execute(whatToExec);

                stateSql.close();
               // rsSql.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        
        public Connection getConn(){
           fileAction fr = new fileAction();
           doConnectDB(fr.getDbClientAddr(),fr.getDbClientPort(),fr.getDbClientUsername(),fr.getDbClientPassword(),fr.getDbName());
           // doConnectDB(dbServ, dbPort, dbUse, dbPass, dbNama);
           // System.out.println("aaaaaaas");
            
            return conn;
        }
        
       

}
