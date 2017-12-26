package com.gyg.learning.dagger2;

import javax.inject.Inject;

/**
 * Created by gyg on 2017/12/25.
 */

public class TestPresent {

    IView mView;
    @Inject
    TestModel mModel;//Dagger2遇到@Inject标记的成员属性，就会去查看该成员类的构造函数，如果构造函数也被@Inject标记,则会自动初始化，完成依赖注入。

    @Inject
    public TestPresent(IView view){
        this.mView=view;
    }

    public void updateUI(){
        mView.updateUI(mModel.getText());
    }
}
