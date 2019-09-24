package com.adesk.mvpframe.base.delegate;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.adesk.mvpframe.base.BaseActivity;
import com.adesk.mvpframe.base.BasePresenter;

/**
 * ================================================
 * 框架要求框架中的每个 {@link Activity} 都需要实现此类,以满足规范
 *
 * @see BaseActivity
 * ================================================
 */
public interface IActivity {

    BasePresenter setPresenter();

    /**
     * 初始化 View, 如果 {@link #initView(Bundle)} 返回 0, 框架则不会调用 {@link Activity#setContentView(int)}
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);

}
