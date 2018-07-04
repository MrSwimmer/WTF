package com.mrswimmer.shift.presentation.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.mrswimmer.shift.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseFragment extends MvpAppCompatFragment implements BaseView {

    @Override
    public void showSnack(String message, View.OnClickListener snackOnClickListener) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                .setAction("lol", snackOnClickListener);
        snackbar.getView();
        TextView tv = (snackbar.getView()).findViewById(R.id.snackbar_action);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        //setRetainInstance(true);
        return inflater.inflate(getLayoutID(), container, false);
    }

    protected abstract void injectDependencies();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void showDialog(String title, String message) {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "main.ttf");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ОК",
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutID();
}
