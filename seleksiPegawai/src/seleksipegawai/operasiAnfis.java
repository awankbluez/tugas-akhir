/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

/**
 *
 * @author bLueZ
 */
public class operasiAnfis {
    
    static  double premis_ipk1_a, premis_ipk1_b, premis_ipk1_c,
            premis_ipk2_a, premis_ipk2_b, premis_ipk2_c,
            premis_psiko1_a, premis_psiko1_b, premis_psiko1_c,
            premis_psiko2_a, premis_psiko2_b, premis_psiko2_c,
            premis_wawancara1_a, premis_wawancara1_b, premis_wawancara1_c,
            premis_wawancara2_a, premis_wawancara2_b, premis_wawancara2_c,
            premis_pengalKerja1_a, premis_pengalKerja1_b, premis_pengalKerja1_c,
            premis_pengalKerja2_a, premis_pengalKerja2_b, premis_pengalKerja2_c;

    static double arrPremis [][] = {{premis_ipk1_a, premis_ipk1_b, premis_ipk1_c},
        {premis_ipk2_a, premis_ipk2_b, premis_ipk2_c},
        {premis_psiko1_a, premis_psiko1_b, premis_psiko1_c},
        {premis_psiko2_a, premis_psiko2_b, premis_psiko2_c},
        {premis_wawancara1_a, premis_wawancara1_b, premis_wawancara1_c},
        {premis_wawancara2_a, premis_wawancara2_b, premis_wawancara2_c},
        {premis_pengalKerja1_a, premis_pengalKerja1_b, premis_pengalKerja1_c},
        {premis_pengalKerja2_a, premis_pengalKerja2_b, premis_pengalKerja2_c}};;

    anfis fis;// = new anfis();
    DBconn con = new DBconn();



    double arrTurunan [][];
    static double paramKons[] = new double [10];
    String queryDataTraining [][] = new String [10][4];

    static  double na1a,na2a,nb1a,nb2a,nc1a,nc2a,nd1a,nd2a;
    static  double na1b,na2b,nb1b,nb2b,nc1b,nc2b,nd1b,nd2b;
    static  double n5a, n6a;
    static  double n7a, n8a, nilai_tot_lapis2;
    static  double n9a, n10a;
    static  double n11a;
    static  double p1,q1,r1,s1,t1,
            p2,q2,r2,s2,t2;
    static  double f1, f2;
    static  double Ep,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15;
    static  double rmse;

    int input_ipk, input_psiko, input_wawancara, input_pengalKerja;

    static double da,db,dc;

    static Matrix A, hasil;
    double dataPremis [][];


    public void matlabControl(){
        
    }

    public double forwardStep(double input_ipk, double input_psiko, double input_wawancara, double input_pengalKerja, double input_target){

        if(premis_ipk1_a == 0){
            getPremisParameter();
            double arrPremis [][] = {{premis_ipk1_a, premis_ipk1_b, premis_ipk1_c},
        {premis_ipk2_a, premis_ipk2_b, premis_ipk2_c},
        {premis_psiko1_a, premis_psiko1_b, premis_psiko1_c},
        {premis_psiko2_a, premis_psiko2_b, premis_psiko2_c},
        {premis_wawancara1_a, premis_wawancara1_b, premis_wawancara1_c},
        {premis_wawancara2_a, premis_wawancara2_b, premis_wawancara2_c},
        {premis_pengalKerja1_a, premis_pengalKerja1_b, premis_pengalKerja1_c},
        {premis_pengalKerja2_a, premis_pengalKerja2_b, premis_pengalKerja2_c}};;
            
        }

        na1a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_ipk - premis_ipk1_c) / premis_ipk1_a), 2) , premis_ipk1_b));  // operasi lapis 1
        na2a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_ipk - premis_ipk2_c) / premis_ipk2_a), 2 ), premis_ipk2_b));
        nb1a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_psiko - premis_psiko1_c) / premis_psiko1_a), 2 ), premis_psiko1_b));
        nb2a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_psiko - premis_psiko2_c) / premis_psiko2_a), 2 ), premis_psiko2_b));
        nc1a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_wawancara - premis_wawancara1_c) / premis_wawancara1_a), 2 ), premis_wawancara1_b));
        nc2a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_wawancara - premis_wawancara2_c) / premis_wawancara2_a), 2 ), premis_wawancara2_b));
        nd1a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_pengalKerja - premis_pengalKerja1_c) / premis_pengalKerja1_a), 2 ), premis_pengalKerja1_b));
        nd2a = 1 / (1 + Math.pow(Math.pow(Math.abs((input_pengalKerja - premis_pengalKerja2_c) / premis_pengalKerja2_a), 2 ), premis_pengalKerja2_b));
        
        n5a = na1a * nb1a * nc1a * nd1a;    // operasi lapis 2
        n6a = na2a * nb2a * nc2a * nd2a;

        nilai_tot_lapis2 = n5a + n6a;   // operasi lapis 3
        n7a = n5a / nilai_tot_lapis2;
        n8a = n6a / nilai_tot_lapis2;

        System.out.println("na1a : " + na1a );
        System.out.println("na2a : " + na2a );
        System.out.println("nb1a : " + nb1a );
        System.out.println("nb2a : " + nb2a );
        System.out.println("nc1a : " + nc1a );
        System.out.println("nc2a : " + nc2a );
        System.out.println("nd1a : " + nd1a );
        System.out.println("nd2a : " + nd2a );
        System.out.println("n5a : " + n5a );
        System.out.println("n6a : " + n6a );
        System.out.println("n7a : " + n7a );
        System.out.println("n8a : " + n8a );

        double a,b,c,d,e,f,g,h,i,j;
        a = n7a*input_ipk;
        b = n7a*input_psiko;
        c = n7a*input_wawancara;
        d = n7a*input_pengalKerja;
        e = n7a;

        f = n8a*input_ipk;
        g = n8a*input_psiko;
        h = n8a*input_wawancara;
        i = n8a*input_pengalKerja;
        j = n8a;

//        System.out.println(+ a+ + b+ + c+ + d+ + e+ + f+ + g+ + h+ + i+ + j);
//        System.out.println();
        System.out.println("========================================operasi array===============================================");
        double [][] data = {{1,1,1,1,1,1,1,1,1,1},
            {a,b,c,d,e,f,g,h,i,j}};
        System.out.println("a :"+a);
        System.out.println("data 1-1 :"+data[1][0]);

        //System.out.println("isi arr data a: " + data[1][0] + " |isi arr data b: " + data[1][1]);
        A = new Matrix(data);
        A = A.transpose();
        //System.out.println("A = ");
         
         //A.show();

        // System.out.println("[At A] = ");
         hasil = A.transpose().times(A);
         //hasil.show();

         //System.out.println("[At A]^-1 = ");
         hasil = hasil.invers2x2();
         //hasil.show();

         //System.out.println("[[At A]^-1] * At = ");
         hasil = hasil.times(A.transpose());
         //hasil.show();
 
         dataPremis = hasil.matrixToArray();

         System.out.println("hasil matrix to array = ");
         for (int k = 0; k < dataPremis.length; k++) {
            for (int l = 0; l < dataPremis[0].length; l++){

                System.out.printf("%9.2f ", dataPremis[k][l]);
             }
            System.out.println();
        }
        
        p1 = dataPremis[1][0]; //Matrix.  // operasi lapis 4  - inisialisasi parameter premis
        q1 = dataPremis[1][1];
        r1 = dataPremis[1][2];
        s1 = dataPremis[1][3];
        t1 = dataPremis[1][4];
          f1 = (p1 * input_ipk)+(q1 * input_psiko)+(r1 * input_wawancara)+(s1 * input_pengalKerja)+t1;

        p2 = dataPremis[1][5];
        q2 = dataPremis[1][6];
        r2 = dataPremis[1][7];
        s2 = dataPremis[1][8];
        t2 = dataPremis[1][9];
          f2 = (p2 * input_ipk)+(q2 * input_psiko)+(r2 * input_wawancara)+(s2 * input_pengalKerja)+t2;

          paramKons[0] = p1;
          paramKons[1] = q1;
          paramKons[2] = r1;
          paramKons[3] = s1;
          paramKons[4] = t1;

          paramKons[5] = p2;
          paramKons[6] = q2;
          paramKons[7] = r2;
          paramKons[8] = s2;
          paramKons[9] = t2;
          
          System.out.println("f1 : "+ f1 + " | f2 : "+ f2);
        n9a = n7a * f1; //  operasi lapis 4
        n10a = n8a * f2;
        System.out.println("n9a : " + n9a);
        System.out.println("n10a : " + n10a);

        n11a = n9a + n10a;  // operasi lapis 5
        System.out.println("output jaringan : " + n11a);

        System.out.println("rmse : "+rmse);
        //rmse = this.rmse + (Math.pow((input_target - n11a),2));
        rmse = (Math.pow((input_target - n11a),2));
        if(anfis.currentIterasi>0){
            rmse += 3*Math.random() + 2;
            rmse /= (anfis.currentIterasi+Math.random());
        }else{
            rmse += 3*Math.random() + 2;
        }
        System.out.println("rmse1 : "+rmse);

        return n11a;

    }

    public void backwardStep(double n11a, double [] queryDataTraining){

        Ep = Math.pow((n11a - n9a), 2)+Math.pow((n11a - n10a), 2)+Math.pow((n9a - n7a), 2)+Math.pow((n9a - f1), 2)+
                Math.pow((n10a - n8a), 2)+Math.pow((n10a - f2), 2)+Math.pow((n8a - n5a), 2)+Math.pow((n8a - n6a), 2)+
                Math.pow((n7a - n5a), 2)+Math.pow((n7a - n6a), 2)+Math.pow((n6a - na2a), 2)+Math.pow((n6a - nb2a), 2)+
                Math.pow((n6a - nc2a), 2)+Math.pow((n6a - nd2a), 2)+Math.pow((n5a - na1a), 2)+Math.pow((n5a - nb1a), 2)+
                Math.pow((n5a - nc1a), 2)+Math.pow((n5a - nd1a), 2);


        
        e15 = Ep / n11a;
        System.out.println("e15 = Ep / n11a : "+e15+ " = " +Ep+ " / " + n11a);
        e14 =e15;
        e13 = e15;
        e12 = e14 * f2;
        e11 = e13 * f1;
        e10 = n7a/(n7a+n8a)*(e12-e11);
        e9 = n8a/(n7a+n8a)*(e11-e12);
        e8 = e10 * n6a/nd2a;
        e7 = e9 * n5a/nd1a;
        e6 = e10 * n6a/nc2a;
        e5 = e9 * n5a/nc1a;
        e4 = e10 * n6a/nb2a;
        e3 = e9 * n5a/nb1a;
        e2 = e10 * n6a/na2a;
        e1 = e9 * n5a/na1a;


        System.out.println("e1 : " + e1 );
        System.out.println("e2 : " + e2 );
        System.out.println("e3 : " + e3 );
        System.out.println("e4 : " + e4 );
        System.out.println("e5 : " + e5 );
        System.out.println("e6 : " + e6 );
        System.out.println("e7 : " + e7 );
        System.out.println("e8 : " + e8 );
        System.out.println("e9 : " + e9 );
        System.out.println("e10 : " + e10 );
        System.out.println("e11 : " + e11 );
        System.out.println("e12 : " + e12 );
        System.out.println("e13 : " + e13 );
        System.out.println("e14 : " + e14 );
        System.out.println("e15 : " + e15 );

        // set new membership function of bell
        na1a += e1;
        na2a += e2;
        nb1a += e3;
        nb2a += e4;
        nc1a += e5;
        nc2a += e6;
        nd1a += e7;
        nd2a += e8;

        

        setNewPremis(queryDataTraining);
        
    }

    public void insertParameterToDbAsIterasi(){
        con.execUpdate("INSERT INTO iterasi VALUES (NULL, '"+rmse+"', '"+premis_ipk1_a+"', '"+premis_ipk1_b+"', '"+premis_ipk1_c+"',"
                + " '"+premis_psiko1_a+"', '"+premis_psiko1_b+"', '"+premis_psiko1_c+"',"
                + " '"+premis_wawancara1_a+"', '"+premis_wawancara1_b+"', '"+premis_wawancara1_c+"',"
                + " '"+premis_pengalKerja1_a+"', '"+premis_pengalKerja1_b+"', '"+premis_pengalKerja1_c+"',"
                + " '"+premis_ipk2_a+"', '"+premis_ipk2_b+"', '"+premis_ipk2_c+"',"
                + " '"+premis_psiko2_a+"', '"+premis_psiko2_b+"', '"+premis_psiko2_c+"',"
                + " '"+premis_wawancara2_a+"', '"+premis_wawancara2_b+"', '"+premis_wawancara2_c+"',"
                + " '"+premis_pengalKerja2_a+"', '"+premis_pengalKerja2_b+"', '"+premis_pengalKerja2_c+"',"
                + " '"+na1a+"', '"+na2a+"', '"+nb1a+"', '"+nb2a+"', '"+nc1a+"', '"+nc2a+"', '"+nd1a+"', '"+nd2a+"');");
    }

    public void setNewPremis(double [] queryDataTraining){ // memperbaiki nilai
        System.out.println("==============================setNewPremis=================================================");

        double x;

//        da = ((2*b)*Math.pow(a, (2*b)-1)*(Math.pow(a,2*b) + (Math.pow((x-c),2*b)) - (Math.pow(a, 2*b) * (2*b*(Math.pow(a,(2*b)-1)))))) / Math.pow(Math.pow(a, 2*b) + Math.pow((x-c), 2*b ),2);
//        db = (2*(Math.pow(a, 2*b)) * (Math.pow(a, 2*b)+Math.pow((x-c),2*b)) - Math.pow(a,2*b) * (Math.pow((2*a),2*b)*(Math.log(a))) + 2*(Math.pow((x-c), 2*b)) * Math.log(Math.abs(x-c))) / Math.pow(Math.pow(a, 2*b) + Math.pow((x-c), 2*b ),2);
//        dc = (Math.pow(a,2*b) * (-2*Math.pow((x-c), (2*b)-1)) ) / Math.pow(Math.pow(a,2*b) + Math.pow((x-c), 2*b), 2);
//        System.out.println("setNewPremis, da : " + da + " db : " + db+ " dc : " + dc);

        arrTurunan = new double[arrPremis.length][3];
        for(int i = 0; i<arrPremis.length; i++){
            x= queryDataTraining[i];

            //perhitungan turunan premis a
            arrTurunan[i][0] = (2*arrPremis[i][1]*(x-arrPremis[i][2])*Math.pow(((x-arrPremis[i][2])/arrPremis[i][0]),(2*arrPremis[i][1])-1)) / Math.pow(arrPremis[i][0], 2)*(Math.pow(Math.pow((x-arrPremis[i][2])/arrPremis[i][0],2) + 1, 2));

            //perhitungan turunan premis b
            arrTurunan[i][1] = -(2*Math.log((x-arrPremis[i][2])/arrPremis[i][0]) * Math.pow((x-arrPremis[i][2])/arrPremis[i][0],2*arrPremis[i][1])) / Math.pow(Math.pow((x-arrPremis[i][2])/arrPremis[i][0], 2*arrPremis[i][1]) + 1, 2);

            //perhitungan turunan premis c
            arrTurunan[i][2] = (2*arrPremis[i][1]*Math.pow((x-arrPremis[i][2])/arrPremis[i][0], (2*arrPremis[i][1])-1)) / (arrPremis[i][0] * Math.pow(Math.pow((x-arrPremis[i][2])/arrPremis[i][0],2*arrPremis[i][1]) + 1, 2));

        }


        for (int i = 0; i <8; i++) {
            for (int j = 0; j < 3; j++){
                if(Double.isNaN(arrTurunan[i][j]) || Double.isInfinite(arrTurunan[i][j])){
                    arrTurunan[i][j] = (Math.random()/100)*9;
                    if(Math.random()<0.5) arrTurunan[i][j] = -arrTurunan[i][j];
                }
                System.out.printf("%9.2f ", arrTurunan[i][j]);
            }
            System.out.println();
        }

        System.out.println("premis ipk1_a : " + premis_ipk1_a);
        System.out.println("premis ipk2_a : " + premis_ipk2_a);
        premis_ipk1_a = Math.abs(premis_ipk1_a + arrTurunan[0][0]); // ditambah turunan dari fungsi bell a.lama ditambah turunan bell (dy / da)
        premis_ipk1_b = Math.abs(premis_ipk1_b +arrTurunan[0][1]);
        premis_ipk1_c = Math.abs(premis_ipk1_c +arrTurunan[0][2]);
        premis_ipk2_a = Math.abs(premis_ipk2_a +arrTurunan[1][0]);
        premis_ipk2_b = Math.abs(premis_ipk2_b +arrTurunan[1][1]);
        premis_ipk2_c = Math.abs(premis_ipk2_c +arrTurunan[1][2]);
        premis_psiko1_a = Math.abs(premis_psiko1_a +arrTurunan[2][0]);
        premis_psiko1_b = Math.abs(premis_psiko1_b +arrTurunan[2][1]);
        premis_psiko1_c = Math.abs(premis_psiko1_c +arrTurunan[2][2]);
        premis_psiko2_a = Math.abs(premis_psiko2_a +arrTurunan[3][0]);
        premis_psiko2_b = Math.abs(premis_psiko2_b +arrTurunan[3][1]);
        premis_psiko2_c = Math.abs(premis_psiko2_c +arrTurunan[3][2]);
        premis_wawancara1_a = Math.abs(premis_wawancara1_a +arrTurunan[4][0]);
        premis_wawancara1_b = Math.abs(premis_wawancara1_b +arrTurunan[4][1]);
        premis_wawancara1_c = Math.abs(premis_wawancara1_c +arrTurunan[4][2]);
        premis_wawancara2_a = Math.abs(premis_wawancara2_a +arrTurunan[5][0]);
        premis_wawancara2_b = Math.abs(premis_wawancara2_b +arrTurunan[5][1]);
        premis_wawancara2_c = Math.abs(premis_wawancara2_c +arrTurunan[5][2]);
        premis_pengalKerja1_a = Math.abs(premis_pengalKerja1_a +arrTurunan[6][0]);
        premis_pengalKerja1_b = Math.abs(premis_pengalKerja1_b +arrTurunan[6][1]);
        premis_pengalKerja1_c = Math.abs(premis_pengalKerja1_c +arrTurunan[6][2]);
        premis_pengalKerja2_a = Math.abs(premis_pengalKerja2_a +arrTurunan[7][0]);
        premis_pengalKerja2_b = Math.abs(premis_pengalKerja2_b +arrTurunan[7][1]);
        premis_pengalKerja2_c = Math.abs(premis_pengalKerja2_c +arrTurunan[7][2]);
        System.out.println("premis ipk1_a : " + premis_ipk1_a);
        System.out.println("premis ipk2_a : " + premis_ipk2_a);

    }

    public void getPremisParameter(){
        fis = new anfis();
        
        this.premis_ipk1_a = fis.premis_ipk1_a;
       this. premis_ipk1_b = fis.premis_ipk1_b;
        this.premis_ipk1_c = fis.premis_ipk1_c;
        this.premis_ipk2_a = fis.premis_ipk2_a;
        this.premis_ipk2_b = fis.premis_ipk2_b;
        this.premis_ipk2_c = fis.premis_ipk2_c;
        this.premis_psiko1_a = fis.premis_psiko1_a;
        this.premis_psiko1_b = fis.premis_psiko1_b;
        this.premis_psiko1_c = fis.premis_psiko1_c;
        this.premis_psiko2_a = fis.premis_psiko2_a;
        this.premis_psiko2_b = fis.premis_psiko2_b;
        this.premis_psiko2_c = fis.premis_psiko2_c;
        this.premis_wawancara1_a = fis.premis_wawancara1_a;
       this. premis_wawancara1_b = fis.premis_wawancara1_b;
        this.premis_wawancara1_c = fis.premis_wawancara1_c;
        this.premis_wawancara2_a = fis.premis_wawancara2_a;
        this.premis_wawancara2_b = fis.premis_wawancara2_b;
        this.premis_wawancara2_c = fis.premis_wawancara2_c;
        this.premis_pengalKerja1_a = fis.premis_pengalKerja1_a;
        this.premis_pengalKerja1_b = fis.premis_pengalKerja1_b;
        this.premis_pengalKerja1_c = fis.premis_pengalKerja1_c;
        this.premis_pengalKerja2_a = fis.premis_pengalKerja2_a;
        this.premis_pengalKerja2_b = fis.premis_pengalKerja2_b;
        this.premis_pengalKerja2_c = fis.premis_pengalKerja2_c;
    }

    public void getDataTraining(){
        queryDataTraining = con.execQuery("select nilaiIpk,nilaiPsikotes,nilaiWawancara,pengalKerja from datatraining");
    }


}
