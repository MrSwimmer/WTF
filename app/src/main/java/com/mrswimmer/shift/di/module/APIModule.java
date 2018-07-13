package com.mrswimmer.shift.di.module;

import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.api.WtfApi;
import com.mrswimmer.shift.domain.interactor.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ApiService providesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.21.151:8090/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WtfApi api = retrofit.create(WtfApi.class);
        return new ApiService(api);
    }
}
