package com.example.uas;

public class KHS {
    private Integer no;
    private String kodemk;
    private String matakuliah;
    private Integer sks;
    private Double nilai_angka;
    private String nilai_huruf;
    private String predikat;

    public KHS(){ }

    public KHS(Integer Ino, String Skodemk, String Smatakuliah, Integer Ssks, Double Snilai_angka, String Snilai_huruf, String Spredikat){
        this.no=Ino;
        this.kodemk=Skodemk;
        this.matakuliah=Smatakuliah;
        this.sks=Ssks;
        this.nilai_angka=Snilai_angka;
        this.nilai_huruf=Snilai_huruf;
        this.predikat=Spredikat;
    }

    public String toPrint(){
        return this.no+"\n"+kodemk+"\n\n\n\n\n\n\n\n\n\n"+matakuliah+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+sks+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+nilai_angka+"\n\n\n\n\n\n\n\n\n\n"+nilai_huruf;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getKodemk() {
        return kodemk;
    }

    public void setKodemk(String kodemk) {
        this.kodemk = kodemk;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public Double getNilai_angka() {
        return nilai_angka;
    }

    public void setNilai_angka(Double nilai_angka) {
        this.nilai_angka = nilai_angka;
    }

    public String getNilai_huruf() {
        return nilai_huruf;
    }

    public void setNilai_huruf(String nilai_huruf) {
        this.nilai_huruf = nilai_huruf;
    }

    public String getPredikat() {
        return predikat;
    }

    public void setPredikat(String predikat) {
        this.predikat = predikat;
    }




}
