package com.mrswimmer.shift.presentation.main.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Router;

public class MainActivity extends BaseActivity implements MainActivityView {
    private final String TOPIC = "JavaSampleApproach";

    ActionBarDrawerToggle drawerToggle;

    @InjectPresenter
    MainActivityPresenter presenter;

    @ProvidePresenter
    public MainActivityPresenter presenter() {
        return new MainActivityPresenter();
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navView)
    NavigationView navigationView;
    @BindView(R.id.main_container)
    FrameLayout mainFrameLayout;

    View headerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
        setSupportActionBar(toolbar);
        presenter.setupDrawerContent(navigationView);
        headerLayout = navigationView.getHeaderView(0);
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);
        TextView email = headerLayout.findViewById(R.id.header_email);
        email.setText(presenter.getEmail());
        ImageView share = headerLayout.findViewById(R.id.header_share);
        share.setOnClickListener(v -> presenter.share());
        //first screen
        Menu m = navigationView.getMenu();
        m.getItem(1).setChecked(true);
        setTitle(m.getItem(1).getTitle());
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected int getContainerId() {
        return R.id.main_container;
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void checkDrawerItem(MenuItem menuItem) {
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
