package com.gyg.learning.dagger2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gyg.learning.R;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity implements IView{

    @Inject
    TestPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DaggerTestComponent.builder().testModule(new TestModule(this)).build().inject(this);
        mPresent.updateUI();

    }

    @Override
    public void updateUI(String text) {
        ((TextView)findViewById(R.id.textview)).setText(text);
    }
}
