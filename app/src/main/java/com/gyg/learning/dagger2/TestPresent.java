package com.gyg.learning.dagger2;

import javax.inject.Inject;

/**
 * Created by gyg on 2017/12/25.
 */

public class TestPresent {

    IView mView;
    @Inject
    TestModel mModel;

    @Inject
    public TestPresent(IView view){
        this.mView=view;
    }

    public void updateUI(){
        mView.updateUI(mModel.getText());
    }
}
