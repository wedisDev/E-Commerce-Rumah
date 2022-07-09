package com.example.rumah.data.network.response.get_rumah;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetRumahByPengguna{

	@SerializedName("data")
	private ArrayList<DataItem> data;

	public void setData(ArrayList<DataItem> data){
		this.data = data;
	}

	public ArrayList<DataItem> getData(){
		return data;
	}
}