package com.example.rumah.data.network.response.get_rumah;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {

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

	@SerializedName("penjual_id")
	private int penjualId;

	@SerializedName("penjual")
	private String penjual;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("email_penjual")
	private String email_penjual;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("status")
	private String status;

	public String getJudulRumah(){
		return judulRumah;
	}

	public String getHargaRumah(){
		return hargaRumah;
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

	public String getTgl(){
		return tgl;
	}

	public String getEmailPenjual(){
		return email_penjual;
	}

	public String getStatus(){
		return status;
	}
}