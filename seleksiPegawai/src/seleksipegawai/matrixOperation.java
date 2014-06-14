/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

import java.util.Arrays;


/**
 *
 * @author bLueZ
 */
public class matrixOperation {

    public void transpose(){
         double[] m = {1, 1, 1, 1};
         double[][] ans = new double[m.length][1];
         for(int rows = 0; rows < m.length; rows++){
               /*for(int cols = 0; cols < m[0].length; cols++){
                       ans[cols][rows] = m[rows][cols];
               }*/
          ans[rows][0]=m[rows];
       }
       for(double[] i:ans){//2D arrays are arrays of arrays
               System.out.println(Arrays.toString(i));

       }
    }

}
