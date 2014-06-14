/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

/**
 *
 * @author bLueZ
 */
public class operasiLapis1 {

    int [] nilaiW = new int [10];
    int [] nilaiPengal = new int [10];
    int [] nilaiPsiko = new int [10];
    int [] nilaiTofel = new int [10];

    int premisAipk = 30; // premis ipk a
    int premisBipk = 60;
    int premisCipk = 90;
    int premisApsiko = 30;
    int premisCpsiko = 60;
    int premisBpsiko = 90;
    int premisAwawan = 30;
    int premisBwawan = 60;
    int premisCwawan = 90;
    int premisApengal = 1;
    int premisBpengal = 3;
    int premisCpengal = 5; // premis c pengalaman kerja

    float nilaiOutW [] = new float [10];
    float nilaiOutTofel [] = new float [10];
    float nilaiOutT [] = new float [10];
    float nilaiOutPsiko [] = new float [10];
    float nilaiOutPengalKerja[] = new float [10];
    String [][] score;
    String countRowStr[][];
    int countRow;

    DBconn con = new DBconn();
    //anfis fuzy = new anfis();

    public void operasiLapis1(String tahap){

        if(tahap.equalsIgnoreCase("maju")){
            firstInitiate();
            fuzzyfy();

        }else if(tahap.equalsIgnoreCase("mundur")){

        }

    }
    
    void firstInitiate(){
        dbQuery();
        getScore();

    }
    
    void fuzzyfy(){
        for(int i=0; i<countRow; i++){
            nilaiOutW[i] = fuzzyfikasiPengalKerja(nilaiPengal[i]);
            nilaiOutPsiko[i] = fuzzyfikasiPsikotes(nilaiPsiko[i]);
            nilaiOutTofel[i] = fuzzyfikasiToefl(nilaiTofel[i]);
            nilaiOutW[i] = fuzzyfikasiWawancara(nilaiW[i]);
        }
    }

    void dbQuery(){
        score = con.execQuery("select * from datatraining"); // mengambil semua nilai dari tabel datatraining
        countRowStr =  con.execQuery("select count(*) from datatraining"); // menghitung banyaknya row di tabel datatraining
        countRow = Integer.parseInt(countRowStr[0][0]);
    }

    void getScore(){

        for(int y=0; y<10; y++){ //getScore nilai Wawancara
            nilaiW[y] = Integer.parseInt(score[y][3]);
        }

        for(int y=0; y<10; y++){ //getScore nilai Psikotes
            nilaiPsiko[y] = Integer.parseInt(score[y][5]);
        }

        for(int y=0; y<10; y++){ //getScore nilai Toefl

            nilaiTofel[y] = Integer.parseInt(score[y][6]);
        }

        for(int y=0; y<10; y++){ //getPengalaman kerja
            nilaiPengal[y] = Integer.parseInt(score[y][4]);
        }

    }

    public float fuzzyfikasiWawancara (float nilaiW){
        float nilaiOutRendah=0;
        float nilaiOutSedang=0;
        float nilaiOutTinggi=0;
        float outMin1=0;
        float outMin2=0;
        float outMin3=0;
        if(nilaiW <= premisAwawan){   // fuzzyfikasi nilai Wawancara himpunan rendah
            nilaiOutRendah = 1;
        }else if(nilaiW >= premisAwawan || nilaiW <= premisBwawan){
            nilaiOutRendah = (premisCwawan - nilaiW)/(premisCwawan - premisBwawan);
        }else if(nilaiW >= premisBwawan){
            nilaiOutRendah = 0;
        }
        if(nilaiW <= premisAwawan || nilaiW >= premisCwawan){   // fuzzyfikasi nilai Wawancara himpunan sedang
            nilaiOutSedang = 0;
        }else if(nilaiW >= premisAwawan || nilaiW <= premisBwawan){
            nilaiOutSedang = (nilaiW - 30)/30;
        }else if(nilaiW >= premisBwawan || nilaiW <= premisCwawan){
            nilaiOutSedang = (90 - nilaiW)/30;
        }
        if(nilaiW <= premisBwawan){   // fuzzyfikasi nilai Wawancara himpunan tinggi
            nilaiOutTinggi = 0;
        }else if(nilaiW >= premisBwawan || nilaiW <= premisCwawan){
            nilaiOutTinggi = (nilaiW - 60)/30;
        }else if(nilaiW >= premisCwawan){
            nilaiOutTinggi = 1;
        }
        if(nilaiOutRendah != 0 && nilaiOutSedang != 0){ // pengambilan nilai terendah
            outMin1 = Math.min(nilaiOutRendah, nilaiOutSedang);
        }
        if(nilaiOutSedang != 0 && nilaiOutTinggi != 0){ // pengambilan nilai terendah
            outMin2 = Math.min(nilaiOutSedang, nilaiOutTinggi);
        }

        if(outMin1 != 0){
            outMin3 = outMin2;
        }else if(outMin2 != 0){
            outMin3 = outMin1;
        }

        return outMin3;
    }

    public float fuzzyfikasiPsikotes(float nilaiPsiko){
        float nilaiOutRendah=0;
        float nilaiOutSedang=0;
        float nilaiOutTinggi=0;
        float outMin1=0;
        float outMin2=0;
        float outMin3=0;
        if(nilaiPsiko <= 30){   // fuzzyfikasi nilai Wawancara himpunan rendah
            nilaiOutRendah = 1;
        }else if(nilaiPsiko >= 30 || nilaiPsiko <= 60){
            nilaiOutRendah = (60 - nilaiPsiko)/30;
        }else if(nilaiPsiko >= 60){
            nilaiOutRendah = 0;
        }
        if(nilaiPsiko <= 30 || nilaiPsiko >= 90){   // fuzzyfikasi nilai Wawancara himpunan sedang
            nilaiOutSedang = 0;
        }else if(nilaiPsiko >= 30 || nilaiPsiko <= 60){
            nilaiOutSedang = (nilaiPsiko - 30)/30;
        }else if(nilaiPsiko >= 60 || nilaiPsiko <= 90){
            nilaiOutSedang = (90 - nilaiPsiko)/30;
        }
        if(nilaiPsiko <= 60){   // fuzzyfikasi nilai Wawancara himpunan tinggi
            nilaiOutTinggi = 0;
        }else if(nilaiPsiko >= 60 || nilaiPsiko <= 90){
            nilaiOutTinggi = (nilaiPsiko - 60)/30;
        }else if(nilaiPsiko >= 90){
            nilaiOutTinggi = 1;
        }

        if(nilaiOutRendah != 0 && nilaiOutSedang != 0){ // pengambilan nilai terendah
            outMin1 = Math.min(nilaiOutRendah, nilaiOutSedang);
        }
        if(nilaiOutSedang != 0 && nilaiOutTinggi != 0){ // pengambilan nilai terendah
            outMin2 = Math.min(nilaiOutSedang, nilaiOutTinggi);
        }

        if(outMin1 != 0){
            outMin3 = outMin2;
        }else if(outMin2 != 0){
            outMin3 = outMin1;
        }

        return outMin3;
    }

    public float fuzzyfikasiToefl(float nilaiToefl){
        float nilaiOutRendah=0;
        float nilaiOutSedang=0;
        float nilaiOutTinggi=0;
        float outMin1=0;
        float outMin2=0;
        float outMin3=0;
        if(nilaiToefl <= 300){   // fuzzyfikasi nilai Toefl himpunan rendah
            nilaiOutRendah = 1;
        }else if(nilaiToefl >= 300 || nilaiToefl <= 450){
            nilaiOutRendah = (450 - nilaiToefl)/150;
        }else if(nilaiToefl >= 450){
            nilaiOutRendah = 0;
        }
        if(nilaiToefl <= 300 || nilaiToefl >= 500){   // fuzzyfikasi nilai Toefl himpunan sedang
            nilaiOutSedang = 0;
        }else if(nilaiToefl >= 300 || nilaiToefl <= 500){
            nilaiOutSedang = (nilaiToefl - 300)/100;
        }else if(nilaiToefl >= 400 || nilaiToefl <= 500){
            nilaiOutSedang = (500 - nilaiToefl)/100;
        }
        if(nilaiToefl <= 450){   // fuzzyfikasi nilai Toefl himpunan tinggi
            nilaiOutTinggi = 0;
        }else if(nilaiToefl >= 450 || nilaiToefl <= 600){
            nilaiOutTinggi = (nilaiToefl - 450)/200;
        }else if(nilaiToefl >= 600){
            nilaiOutTinggi = 1;
        }

        if(nilaiOutRendah != 0 && nilaiOutSedang != 0){ // pengambilan nilai terendah
            outMin1 = Math.min(nilaiOutRendah, nilaiOutSedang);
        }
        if(nilaiOutSedang != 0 && nilaiOutTinggi != 0){ // pengambilan nilai terendah
            outMin2 = Math.min(nilaiOutSedang, nilaiOutTinggi);
        }

        if(outMin1 != 0){
            outMin3 = outMin2;
        }else if(outMin2 != 0){
            outMin3 = outMin1;
        }

        return outMin3;
    }

    public float fuzzyfikasiPengalKerja(float pengalKerja){
        float nilaiOutRendah=0;
        float nilaiOutSedang=0;
        float nilaiOutTinggi=0;
        float outMin1=0;
        float outMin2=0;
        float outMin3=0;
        if(pengalKerja <= 1){   // fuzzyfikasi pengalaman kerja himpunan rendah
            nilaiOutRendah = 1;
        }else if(pengalKerja >= 1 || pengalKerja <= 3){
            nilaiOutRendah = (3 - pengalKerja)/2;
        }else if(pengalKerja >= 3){
            nilaiOutRendah = 0;
        }
        if(pengalKerja <= 1 || pengalKerja >= 5){   // fuzzyfikasi nilai Psiko himpunan sedang
            nilaiOutSedang = 0;
        }else if(pengalKerja >= 1 || pengalKerja <= 3){
            nilaiOutSedang = (pengalKerja - 3)/2;
        }else if(pengalKerja >= 3 || pengalKerja <= 5){
            nilaiOutSedang = (5 - pengalKerja)/2;
        }
        if(pengalKerja <= 3){   // fuzzyfikasi nilai Psiko himpunan tinggi
            nilaiOutTinggi = 0;
        }else if(pengalKerja >= 3 || pengalKerja <= 5){
            nilaiOutTinggi = (pengalKerja - 5)/2;
        }else if(pengalKerja >= 5){
            nilaiOutTinggi = 1;
        }

        if(nilaiOutRendah != 0 && nilaiOutSedang != 0){ // pengambilan nilai terendah
            outMin1 = Math.min(nilaiOutRendah, nilaiOutSedang);
        }
        if(nilaiOutSedang != 0 && nilaiOutTinggi != 0){ // pengambilan nilai terendah
            outMin2 = Math.min(nilaiOutSedang, nilaiOutTinggi);
        }

        if(outMin1 != 0){
            outMin3 = outMin2;
        }else if(outMin2 != 0){
            outMin3 = outMin1;
        }

        return outMin3;


    }

    public void storeScore(){
        con.execUpdate("TRUNCATE table fuzzyfikasi");
        for(int i=0; i<countRow; i++){
            con.execUpdate("INSERT INTO `seleksipegawai`.`fuzzyfikasi` (`idFuzzy`, `hasilWawancara`, `hasilPengalKerja`, `hasilPsikotes`, `hasilToefl`) VALUES (NULL, '"+nilaiOutW[i]+"', '"+nilaiOutPengalKerja[i]+"', '"+nilaiOutPsiko[i]+"', '"+nilaiOutTofel[i]+"');");
        }
    }

}
