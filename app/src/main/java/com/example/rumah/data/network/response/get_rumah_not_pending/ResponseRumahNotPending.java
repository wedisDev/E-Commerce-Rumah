package com.example.rumah.data.network.response.get_rumah_not_pending;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRumahNotPending{

	@SerializedName("data")
	private ArrayList<DataItem> data;

	public ArrayList<DataItem> getData(){
		return data;
	}
}