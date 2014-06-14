/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai1;

/**
 *
 * @author bLueZ
 */
public class operasiLapis2 {

    int na1a,na2a,nb1a,nb2a,nc1a,nc2a,nd1a,nd2a;
    int na1b,na2b,nb1b,nb2b,nc1b,nc2b,nd1b,nd2b;
    int n5a,n6a,n5b,n6b;

    public void operasiLapis2(String tahap){
         if(tahap.equalsIgnoreCase("maju")){
            n5a = na1a*nb1a*nc1a*nd1a;
            n6a = na2a*nb2a*nc2a*nd2a;

        }else if(tahap.equalsIgnoreCase("mundur")){

        }
    }

}
