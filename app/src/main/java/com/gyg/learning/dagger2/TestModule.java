package com.gyg.learning.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gyg on 2017/12/25.
 */

@Module
public class TestModule {

    private IView mView;

    public TestModule(IView iView){
        this.mView=iView;
    }

    @Provides
    public IView provideIView(){
        return this.mView;
    }
}
