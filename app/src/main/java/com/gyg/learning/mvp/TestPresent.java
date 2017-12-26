package com.gyg.learning.mvp;

import javax.inject.Inject;

/**
 * Created by gyg on 2017/12/25.
 */

public class TestPresent {

    IView mView;
    TestModel mModel;

    @Inject
    public TestPresent(IView view){
        this.mView=view;
        this.mModel=new TestModel();
    }

    public void updateUI(){
        mView.updateUI(mModel.getText());
    }
}
