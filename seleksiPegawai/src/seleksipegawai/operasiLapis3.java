/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai1;

/**
 *
 * @author bLueZ
 */
public class operasiLapis3 {

    int na1a,na2a,nb1a,nb2a,nc1a,nc2a,nd1a,nd2a;
    int na1b,na2b,nb1b,nb2b,nc1b,nc2b,nd1b,nd2b;
    int n5a_lapis2,n6a_lapis2,n5b_lapis2,n6b_lapis2;
    int n7a,n8a,n7b,n8b;

    operasiLapis2 lapis2 = new operasiLapis2();

    public void operasiLapis3(String tahap){
        initiate(tahap);
        if(tahap.equalsIgnoreCase("maju")){
            n7a = n5a_lapis2/(n5a_lapis2 + n6a_lapis2);
            n8a = n6a_lapis2/(n5a_lapis2 + n6a_lapis2);

        }else if(tahap.equalsIgnoreCase("mundur")){

        }
    }

    public void initiate(String tahap){
        if(tahap.equalsIgnoreCase("maju")){
            n5a_lapis2 = lapis2.n5a;
            n6a_lapis2 = lapis2.n6a;

        }else if(tahap.equalsIgnoreCase("mundur")){

        }


    }

}
