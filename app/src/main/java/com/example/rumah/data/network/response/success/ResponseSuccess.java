package com.example.rumah.data.network.response.success;

import com.google.gson.annotations.SerializedName;

public class ResponseSuccess{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}