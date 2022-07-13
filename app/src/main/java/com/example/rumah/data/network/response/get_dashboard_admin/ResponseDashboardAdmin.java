package com.example.rumah.data.network.response.get_dashboard_admin;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDashboardAdmin{

	@SerializedName("data")
	private List<DataItem> data;

	public List<DataItem> getData(){
		return data;
	}
}