
package seleksipegawai;

/**
 *
 * @author bLueZ
 */
public class tesMatriks {
    static Matrix A;
    static Matrix hasil;
    public static void main(String args[]){

        double data1[][] = {{1,1,1,1,1,1,1,1,1,1},{15,3,7,3,5,8,7,8,5,6}};
         A = new Matrix(data1);

         System.out.println("A = ");
         A = A.transpose();
         A.show();

         System.out.println("[At A] = ");
         hasil = A.transpose().times(A);
         hasil.show();

         System.out.println("[At A]^-1 = ");
         hasil = hasil.invers2x2();
         hasil.show();

         System.out.println("[[At A]^-1] * At = ");
         hasil = hasil.times(A.transpose());
         hasil.show();

         System.out.println("Nah hasilnya matrix 2x10, trus yg diambil sbgai parameter konsekuensi yg baris bawah kali ya :D");

    }
}

/*tes1 = tes1.times(tes2);
         tes1 = tes1.transpose();
         tes1 = tes1.solve(tes1);
        /*double data1[][] = {{2,4,5},{1,5,3},{8,6,6}};
        double data2[][] = {{2},{6},{7}};
        A = new Matrix(data1);
        Matrix B = new Matrix(data2);
        hasil = A.solve(B);*/