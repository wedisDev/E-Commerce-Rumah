package com.example.rumah.data.network.response.get_penjual_by_pengguna;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("judul_rumah")
	private String judulRumah;

	@SerializedName("harga_rumah")
	private String hargaRumah;

	@SerializedName("transfer")
	private Object transfer;

	@SerializedName("rekening_penjual")
	private String rekeningPenjual;

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

	@SerializedName("status")
	private String status;

	public String getJudulRumah(){
		return judulRumah;
	}

	public String getHargaRumah(){
		return hargaRumah;
	}

	public Object getTransfer(){
		return transfer;
	}

	public String getRekeningPenjual(){
		return rekeningPenjual;
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

	public String getStatus(){
		return status;
	}
}