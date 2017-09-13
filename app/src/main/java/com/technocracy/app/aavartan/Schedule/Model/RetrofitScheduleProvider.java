package com.technocracy.app.aavartan.Schedule.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.Schedule.Api.ScheduleApi;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;
import com.technocracy.app.aavartan.helper.App;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhi on 01-Sep-17.
 */

public class RetrofitScheduleProvider implements ScheduleProvider {
    private final Retrofit retrofit;
    private ScheduleApi api;

    public RetrofitScheduleProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(App.Base_Url).
                addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
    }


    @Override
    public void getSchedule(String day, final ScheduleCallback callback) {
        api = retrofit.create(ScheduleApi.class);
        Call<ScheduleData> call = api.getSchedule(day);
        call.enqueue(new retrofit2.Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });

    }
}