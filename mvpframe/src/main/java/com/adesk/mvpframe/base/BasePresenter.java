package com.adesk.mvpframe.base;

import com.adesk.mvpframe.mvp.IPresenter;
import com.adesk.mvpframe.mvp.IView;
import com.adesk.mvpframe.utils.Preconditions;

public abstract class BasePresenter implements IPresenter {

    protected IView mRootView;

    public BasePresenter(IView rootView) {
        Preconditions.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
