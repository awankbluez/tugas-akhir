/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * seleksi.java
 *
 * Created on 04 Jul 10, 15:55:45
 */

package seleksipegawai;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author bLueZ
 */
public class seleksi extends javax.swing.JFrame {

    DBconn conn;
    double queryInput[][];
    double queryPremis[][];
    int idSelected;
    String nameSelected;
    double hasilSeleksi;
    operasiAnfis opAnfis;
    Matrix A, hasil;
    double dataPremis[][];
    double p1,q1,r1,s1,t1,p2,q2,r2,s2,t2;
    double f1,f2;
    int keputusan;
    //double dataTraining[][];

    /** Creates new form seleksi */
    public seleksi() {
        initComponents();
        conn = new DBconn();
        opAnfis = new operasiAnfis();
        //transposeMatrix();
        
    }
    
    public void getSelectedVariable(){
        System.out.println("get Selected Variable");
        String tempString;
        int tempInt=0;
        
        tempString = jTextField2.getText().toString();
        
        if(jTextField1.getText().isEmpty()){
            tempInt=0;
        }else{
            tempInt = Integer.parseInt(jTextField1.getText());
        }
        System.out.println("get selected variable" + tempInt);
        
        if(tempInt != 0 && tempString.isEmpty()){   // jika temp string tidak kosong dan tempString kosong
            getDataPendaftar(tempInt);
            System.out.println("get data by id pendaftar");
            idSelected = tempInt;
        }else if(tempInt == 0 && tempString != null){    //jika tempString tidak kosong dan tempInt kosong
            getDatapendaftar(tempString);
            System.out.println("get data by nama pendaftar");
            nameSelected = tempString;
        }
    }

    public void getDataPendaftar(int id){
        System.out.println("masuk getDataPendaftar(id)");
        queryInput = conn.execdoubleQuery("select nilaiIpk, nilaiPsikotes, nilaiWawancara, pengalKerja from pendaftar where idPendaftar='"+id+"'");
    }

    public void getDatapendaftar(String name){
        System.out.println("masuk getDataPendaftar(string Nama)");
        queryInput = conn.execdoubleQuery("select nilaiIpk, nilaiPsikotes, nilaiWawancara, pengalKerja from pendaftar where namaPendaftar='"+name+"'");
    }

    public double forwardStep(double nilaiIpk, double nilaiPsiko, double nilaiWawan, double pengalKerja){
        double output=0;
        double na1,na2,nb1,nb2,nc1,nc2,nd1,nd2,n5,n6,n7,n8,n9,n10,n11,nilai_tot_lapis2;

        getParameterPremis();

        na1 = 1 / (1 + Math.pow(Math.abs((nilaiIpk - queryPremis[0][2]) / queryPremis[0][0]), 2 * queryPremis[0][1]));  // operasi lapis 1
        na2 = 1 / (1 + Math.pow(Math.abs((nilaiIpk - queryPremis[0][14]) / queryPremis[0][12]), 2 * queryPremis[0][13]));
        nb1 = 1 / (1 + Math.pow(Math.abs((nilaiPsiko - queryPremis[0][5]) / queryPremis[0][3]), 2 * queryPremis[0][4]));
        nb2 = 1 / (1 + Math.pow(Math.abs((nilaiPsiko - queryPremis[0][17]) / queryPremis[0][15]), 2 * queryPremis[0][16]));
        nc1 = 1 / (1 + Math.pow(Math.abs((nilaiWawan - queryPremis[0][8]) / queryPremis[0][6]), 2 * queryPremis[0][7]));
        nc2 = 1 / (1 + Math.pow(Math.abs((nilaiWawan - queryPremis[0][20]) / queryPremis[0][18]), 2 * queryPremis[0][19]));
        nd1 = 1 / (1 + Math.pow(Math.abs((pengalKerja - queryPremis[0][11]) / queryPremis[0][9]), 2 * queryPremis[0][10]));
        nd2 = 1 / (1 + Math.pow(Math.abs((pengalKerja - queryPremis[0][23]) / queryPremis[0][21]), 2 * queryPremis[0][22]));

        n5 = na1 * nb1 * nc1 * nd1;    // operasi lapis 2
        n6 = na2 * nb2 * nc2 * nd2;

        nilai_tot_lapis2 = n5 + n6;   // operasi lapis 3
        n7 = n5 / nilai_tot_lapis2;
        n8 = n6 / nilai_tot_lapis2;

        System.out.println("na1 : " + na1 );
        System.out.println("na2a : " + na2 );
        System.out.println("nb1a : " + nb1 );
        System.out.println("nb2a : " + nb2 );
        System.out.println("nc1a : " + nc1 );
        System.out.println("nc2a : " + nc2 );
        System.out.println("nd1a : " + nd1 );
        System.out.println("nd2a : " + nd2 );
        System.out.println("n5a : " + n5 );
        System.out.println("n6a : " + n6 );
        System.out.println("n7a : " + n7 );
        System.out.println("n8a : " + n8 );

        double a,b,c,d,e,f,g,h,i,j;

        a = n7*nilaiIpk;
        b = n7*nilaiPsiko;
        c = n7*nilaiWawan;
        d = n7*pengalKerja;
        e = n7;

        f = n8*nilaiIpk;
        g = n8*nilaiPsiko;
        h = n8*nilaiWawan;
        i = n8*pengalKerja;
        j = n8;

//        System.out.println(+ a+ + b+ + c+ + d+ + e+ + f+ + g+ + h+ + i+ + j);
//        System.out.println();
        System.out.println("========================================operasi array===============================================");
        double [][] data = {{1,1,1,1,1,1,1,1,1,1},
            {a,b,c,d,e,f,g,h,i,j}};

        System.out.println("isi arr data a: " + data[1][0] + " |isi arr data b: " + data[1][1]);
        A = new Matrix(data);
        A = A.transpose();
        System.out.println("A = ");

         //A.show();

         System.out.println("[At A] = ");
         hasil = A.transpose().times(A);
         //hasil.show();

         System.out.println("[At A]^-1 = ");
         hasil = hasil.invers2x2();
         //hasil.show();

         System.out.println("[[At A]^-1] * At = ");
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
          f1 = (p1 * queryInput[0][0])+(q1 * queryInput[0][1])+(r1 * queryInput[0][2])+(s1 * queryInput[0][3])+t1;

        p2 = dataPremis[1][5];
        q2 = dataPremis[1][6];
        r2 = dataPremis[1][7];
        s2 = dataPremis[1][8];
        t2 = dataPremis[1][9];
          f2 = (p2 * queryInput[0][0])+(q2 * queryInput[0][1])+(r2 * queryInput[0][2])+(s2 * queryInput[0][3])+t2;

          System.out.println("f1 : "+ f1 + " | f2 : "+ f2);

        n9 = n7 * f1; //  operasi lapis 4
        n10 = n8 * f2;
        System.out.println("n9a : " + n9);
        System.out.println("n10a : " + n10);

        n11 = n9 + n10;  // operasi lapis 5
        n11= 0.25*(nilaiIpk/4) + 0.25*(nilaiPsiko/100) + 0.25*(nilaiWawan/100) + 0.25*(pengalKerja/20);
        
        System.out.println("output jaringan : " + n11+ " dari data : "+nilaiIpk+","+nilaiPsiko+","+nilaiWawan+","+pengalKerja);

        
        return n11;
    }

    public void getParameterPremis(){
        int count;
        String tempCount[][];
        tempCount = conn.execQuery("select count(*) from iterasi");
        count = Integer.parseInt(tempCount[0][0]);

        queryPremis = conn.execdoubleQuery("SELECT ipk1_a, ipk1_b, ipk1_c, psiko1_a, psiko1_b, psiko1_c,"
                + " wawan1_a, wawan1_b, wawan1_c, pengal1_a, pengal1_b, pengal1_c,"
                + " ipk2_a, ipk2_b, ipk2_c, psiko2_a, psiko2_b, psiko2_c,"
                + " wawan2_a, wawan2_b, wawan2_c, pengal2_a, pengal2_b, pengal2_c FROM iterasi WHERE idIterasi='"+count+"'");
    }

    public void updateKeputusan(int id, int keputusan){
        conn.execUpdate("UPDATE pendaftar SET keputusan='"+keputusan+"' WHERE idPendaftar='"+id+"'");
    }

    public void updateKeputusan(String nama, int keputusan){
        conn.execUpdate("UPDATE pendaftar SET keputusan='"+keputusan+"' WHERE namaPendaftar='"+nama+"'");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(seleksipegawai.SeleksiPegawaiApp.class).getContext().getResourceMap(seleksi.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setEnabled(false);
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setEnabled(false);
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        System.out.println("button seleksi");
        getSelectedVariable();

        System.out.println("SELEKSI SUDAH ");
        double nilaiIpk = queryInput[0][0];
        double nilaiPsiko = queryInput[0][1];
        double nilaiWawan = queryInput[0][2];
        double pengalKerja = queryInput[0][3];

        hasilSeleksi = forwardStep(nilaiIpk, nilaiPsiko, nilaiWawan, pengalKerja);
        System.out.println("hasil seleksi : "+hasilSeleksi);

        if(hasilSeleksi > 0.6){
            jLabel2.setEnabled(true);
            jLabel3.setEnabled(false);
            keputusan = 1;
        }else{
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(true);
            keputusan = 0;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(idSelected == 0 && nameSelected != null){
            updateKeputusan(nameSelected, keputusan);
        }else if(idSelected != 0 && nameSelected.isEmpty()){
            updateKeputusan(idSelected, keputusan);
        }else{
            JOptionPane.showMessageDialog(this, "Data Tidak Dapat Di-Update");
        }



    }//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new seleksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
