package com.gyg.learning.dagger2;

import javax.inject.Inject;

/**
 * Created by gyg on 2017/12/25.
 */

public class TestModel {

    @Inject
    public TestModel(){
    }

    public String getText(){
        return "Dagger2应用实践...";
    }
}
