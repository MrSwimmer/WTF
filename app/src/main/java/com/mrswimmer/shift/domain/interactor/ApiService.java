package com.mrswimmer.shift.domain.interactor;

import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.api.WtfApi;
import com.mrswimmer.shift.data.api.req.Result;
import com.mrswimmer.shift.data.api.req.TasksRequest;
import com.mrswimmer.shift.data.api.res.ResultStatus;
import com.mrswimmer.shift.data.api.res.TasksResult;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiService {
    private final WtfApi api;

    @Inject
    SettingsService settingsService;

    public ApiService(WtfApi api) {
        this.api = api;
        App.getComponent().inject(this);
    }

    public interface ResultCallback {
        void onSuccess(TasksResult status);
        void onError(Throwable e);
    }

    public void sendResult(Result result, ResultCallback callback) {
        result.setAccId(settingsService.getUserId());
        api.sendResult(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getTasks(TasksRequest request, ResultCallback callback) {
        request.setAccId(settingsService.getUserId());
        api.getTasks(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }
}
