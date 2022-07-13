package com.example.rumah.data.network.response.get_user;

import com.google.gson.annotations.SerializedName;

public class ResponseGetUser{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}