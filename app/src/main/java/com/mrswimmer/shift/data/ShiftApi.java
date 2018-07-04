package com.mrswimmer.shift.data;

import com.mrswimmer.shift.data.model.req.Fio;
import com.mrswimmer.shift.data.model.res.FioResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ShiftApi {
    @POST("acc/result")
    Observable<FioResponse> sendResult(@Body Fio fio);
}