package com.example.rumah.data.network.response.get_user;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("telp")
	private String telp;

	@SerializedName("nama")
	private String nama;

	@SerializedName("pass")
	private String pass;

	@SerializedName("rekening")
	private String rekening;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("username")
	private String username;

	public String getTelp(){
		return telp;
	}

	public String getNama(){
		return nama;
	}

	public String getPass(){
		return pass;
	}

	public String getRekening(){
		return rekening;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getUsername(){
		return username;
	}
}