package com.example.rumah.data.network.response.get_dashboard_admin;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("judul_rumah")
	private String judulRumah;

	@SerializedName("harga_rumah")
	private String hargaRumah;

	@SerializedName("transfer")
	private String transfer;

	@SerializedName("pembeli")
	private String pembeli;

	@SerializedName("desc_rumah")
	private String descRumah;

	@SerializedName("id")
	private int id;

	@SerializedName("alamat_rumah")
	private String alamatRumah;

	@SerializedName("penjual_id")
	private int penjualId;

	@SerializedName("penjual")
	private String penjual;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("id_pembeli")
	private int idPembeli;

	@SerializedName("status")
	private String status;


	public String getJudulRumah(){
		return judulRumah;
	}

	public String getHargaRumah(){
		return hargaRumah;
	}

	public String getTransfer(){
		return transfer;
	}

	public String getPembeli(){
		return pembeli;
	}

	public String getDescRumah(){
		return descRumah;
	}

	public int getId(){
		return id;
	}

	public String getAlamatRumah(){
		return alamatRumah;
	}

	public int getPenjualId(){
		return penjualId;
	}

	public String getPenjual(){
		return penjual;
	}

	public String getGambar(){
		return gambar;
	}

	public String getKelurahan(){
		return kelurahan;
	}

	public int getIdPembeli(){
		return idPembeli;
	}

	public String getStatus(){
		return status;
	}
}