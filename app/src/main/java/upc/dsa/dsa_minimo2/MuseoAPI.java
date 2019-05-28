package upc.dsa.dsa_minimo2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MuseoAPI {

    String BASE_URL = "https://do.diba.cat/api/dataset/museus/format/json/";

    @GET("pag-ini/{numinici}/pag-fi/{numfinal}")
    Call<Museums> getData(@Path("numinici") int pagini, @Path("numfinal") int pagfi);

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
