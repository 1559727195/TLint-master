package com.gzsll.hupu.ui.pmdetail;

import com.gzsll.hupu.injector.PerActivity;
import com.gzsll.hupu.injector.component.ApplicationComponent;
import com.gzsll.hupu.injector.module.ActivityModule;

import dagger.Component;

/**
 * Created by sll on 2016/5/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, PmDetailModule.class
})
public interface PmDetailComponent {

    void inject(PmDetailFragment fragment);
}
