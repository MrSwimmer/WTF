package com.mrswimmer.shift.presentation.main.fragment.settings.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.di.qualifier.Global;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Router;

public class ReportActivity extends AppCompatActivity {

    @Inject
    @Global
    Router globalRouter;

    @BindView(R.id.support_subj)
    TextInputEditText subj;
    @BindView(R.id.support_message)
    TextInputEditText message;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
    }

    @OnClick(R.id.support_send)
    void onSendClick() {
        if (isFullFields()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mrswimmerlab@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, subj.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
            try {
                startActivity(Intent.createChooser(intent, "Отправить с помощью"));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "У вас не установлено почтовых клиентов", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFullFields() {
        return !subj.getText().toString().equals("") && !message.getText().toString().equals("");
    }
    
}
