package com.gyg.learning.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gyg on 2017/12/25.
 * Module类提供那些没有构造函数的类的依赖，如第三方类库，系统类，接口类
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
