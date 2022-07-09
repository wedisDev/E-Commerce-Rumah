package com.example.rumah.data.network.response.get_rumah;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("judul_rumah")
	private String judulRumah;

	@SerializedName("harga_rumah")
	private String hargaRumah;

	@SerializedName("desc_rumah")
	private String descRumah;

	@SerializedName("id")
	private int id;

	@SerializedName("alamat_rumah")
	private String alamatRumah;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("kelurahan")
	private String kelurahan;

	public void setJudulRumah(String judulRumah){
		this.judulRumah = judulRumah;
	}

	public String getJudulRumah(){
		return judulRumah;
	}

	public void setHargaRumah(String hargaRumah){
		this.hargaRumah = hargaRumah;
	}

	public String getHargaRumah(){
		return hargaRumah;
	}

	public void setDescRumah(String descRumah){
		this.descRumah = descRumah;
	}

	public String getDescRumah(){
		return descRumah;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAlamatRumah(String alamatRumah){
		this.alamatRumah = alamatRumah;
	}

	public String getAlamatRumah(){
		return alamatRumah;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setKelurahan(String kelurahan){
		this.kelurahan = kelurahan;
	}

	public String getKelurahan(){
		return kelurahan;
	}
}