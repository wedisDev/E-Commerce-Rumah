package com.example.rumah.data.network;

import com.example.rumah.data.network.response.get_dashboard_admin.ResponseDashboardAdmin;
import com.example.rumah.data.network.response.get_penjual_by_pengguna.ResponseGetPenjualByPengguna;
import com.example.rumah.data.network.response.get_rumah.ResponseGetRumahByPengguna;
import com.example.rumah.data.network.response.get_rumah_not_pending.ResponseRumahNotPending;
import com.example.rumah.data.network.response.get_user.ResponseGetUser;
import com.example.rumah.data.network.response.login.ResponseLogin;
import com.example.rumah.data.network.response.response_add_iklan.ResponseAddIklan;
import com.example.rumah.data.network.response.success.ResponseSuccess;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PartMap;

public interface EndPoint {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("usernameKey") String username,
            @Field("passwordKey") String password
    );

    @FormUrlEncoded
    @POST("get_user.php")
    Call<ResponseGetUser> getUser(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<ResponseSuccess> updateProfile(
            @Field("ID_PENGGUNA") String idPengguna,
            @Field("NAMA_PENGGUNA") String namaPengguna,
            @Field("ALAMAT_PENGGUNA") String alamtPengguna,
            @Field("TELP_PENGGUNA") String telpPengguna,
            @Field("EMAIL_PENGGUNA") String emailPengguna,
            @Field("USERNAME_PENGGUNA") String usernamePengguna,
            @Field("PASS_PENGGUNA") String passPengguna,
            @Field("REKENING_PENGGUNA") String rekPengguna
    );

    @FormUrlEncoded
    @POST("beli_rumah.php")
    Call<ResponseSuccess> beliRumah(
            @Field("ID_PENGGUNA") String idPengguna,
            @Field("ID_RUMAH") String idRumah,
            @Field("EMAIL_PENJUAL") String emailPenjual
    );

    @FormUrlEncoded
    @POST("get_rumah_by_pengguna_id.php")
    Call<ResponseGetRumahByPengguna> getRumahByPenggunaId(
            @Field("pengguna_id") String penggunaId
    );

    @FormUrlEncoded
    @POST("get_all_rumah.php")
    Call<ResponseGetRumahByPengguna> getAllRumah(
            @Field("q") String q
    );

    @GET("get_dashboard_admin.php")
    Call<ResponseDashboardAdmin> getDashboardAdmin();

    @FormUrlEncoded
    @POST("get_rumah_not_pending.php")
    Call<ResponseRumahNotPending> getRumahNotPending(
            @Field("ID_PENGGUNA") String q
    );

    @FormUrlEncoded
    @POST("update_status.php")
    Call<ResponseSuccess> updateStatusPenjualan(
            @Field("id") String id,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("get_penjualan_by_pengguna.php")
    Call<ResponseGetPenjualByPengguna> getPenjualanByPengguna(
            @Field("pengguna_id") String penggunaId
    );

    @Multipart
    @POST("status_transfer.php")
    Call<ResponseSuccess> updateStatus(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text);

    @Multipart
    @POST("add_iklan.php")
    Call<ResponseAddIklan> addIklan(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text);
}
