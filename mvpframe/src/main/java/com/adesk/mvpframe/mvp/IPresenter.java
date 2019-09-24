package com.adesk.mvpframe.mvp;

public interface IPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 在框架中 Activity onDestroy 时会默认调用 {@link IPresenter#onDestroy()}
     */
    void onDestroy();
}
