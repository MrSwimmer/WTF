package com.mrswimmer.shift.domain.interactor;

import com.mrswimmer.shift.data.ShiftApi;
import com.mrswimmer.shift.data.model.req.Fio;
import com.mrswimmer.shift.data.model.res.FioResponse;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiService {
    private final ShiftApi shiftApi;

    public ApiService(ShiftApi shiftApi) {
        this.shiftApi = shiftApi;
    }

    public void sendResult(Fio fio, AccCallback callback) {
        shiftApi.sendResult(fio)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    interface AccCallback {
        void onSuccess(FioResponse response);
        void onError(Throwable e);
    }
}

