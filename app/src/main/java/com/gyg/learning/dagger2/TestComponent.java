package com.gyg.learning.dagger2;

import dagger.Component;

/**
 * Created by gyg on 2017/12/25.
 * Component必须是一个接口类或者抽象
 */
@Component(modules = TestModule.class)
public interface TestComponent {

    void inject(TestActivity testActivity);
}
