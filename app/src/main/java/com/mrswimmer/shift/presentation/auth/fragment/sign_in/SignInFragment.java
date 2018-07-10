package com.mrswimmer.shift.presentation.auth.fragment.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInFragmentView {

    private static final String TAG = "code";
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.sign_in_email)
    TextInputEditText email;
    @BindView(R.id.sign_in_pass)
    TextInputEditText pass;

    @InjectPresenter
    SignInFragmentPresenter presenter;
    private GoogleSignInClient mGoogleSignInClient;

    @ProvidePresenter
    public SignInFragmentPresenter presenter() {
        return new SignInFragmentPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_in;
    }

    @OnClick(R.id.sign_in_enter)
    void onEnterClick() {
        if (isFullFields())
            presenter.enter(email.getText().toString(), pass.getText().toString());
        else
            showToast("Заполните все поля");
    }

    @OnClick(R.id.sign_in_reg)
    void onRegClick() {
        presenter.goToRegistration();
    }

    boolean isFullFields() {
        return !email.getText().toString().equals("") && !pass.getText().toString().equals("");
    }

    @Override
    public void showErrorToast(String message) {
        showToast(message);
    }

    @OnClick(R.id.sign_in_button_google)
    void onGoogleClick() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getActivity(), "Ощибка авторизации", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.i(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "signInWithCredential:success");
                        presenter.initUserAfterGoogleSignIn();
                    } else {
                        Log.i(TAG, "signInWithCredential:failure" + task.getException());
                        Toast.makeText(getActivity(), "Ощибка авторизации", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
