package com.gyg.learning.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gyg.learning.R;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity implements IView {

    TestPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mPresent=new TestPresent(this);
        mPresent.updateUI();

    }

    @Override
    public void updateUI(String text) {
        ((TextView)findViewById(R.id.textview)).setText(text);
    }
}
