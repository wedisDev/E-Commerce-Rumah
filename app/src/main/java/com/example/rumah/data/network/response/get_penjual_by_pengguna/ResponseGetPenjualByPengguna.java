package com.example.rumah.data.network.response.get_penjual_by_pengguna;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetPenjualByPengguna{

	@SerializedName("data")
	private ArrayList<DataItem> data;

	public ArrayList<DataItem> getData(){
		return data;
	}
}