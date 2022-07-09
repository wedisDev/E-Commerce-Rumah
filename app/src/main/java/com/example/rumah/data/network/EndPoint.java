package com.example.rumah.data.network;

import com.example.rumah.data.network.response.get_rumah.ResponseGetRumahByPengguna;
import com.example.rumah.data.network.response.login.ResponseLogin;
import com.example.rumah.data.network.response.response_add_iklan.ResponseAddIklan;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PartMap;

//
public interface EndPoint {
    //    //Isi sesuai file di API
//    @GET("retrieve.php")
//    Call<ModelResponse> ardRetrieveAllData();
//
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("usernameKey") String username,
            @Field("passwordKey") String password
    );

    @FormUrlEncoded
    @POST("get_rumah_by_pengguna_id.php")
    Call<ResponseGetRumahByPengguna> getRumahByPenggunaId(
            @Field("pengguna_id") String penggunaId
    );

    @Multipart
    @POST("add_iklan.php")
    Call<ResponseAddIklan> addIklan(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text);
}
