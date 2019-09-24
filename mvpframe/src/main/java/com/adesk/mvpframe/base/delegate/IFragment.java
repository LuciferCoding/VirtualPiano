package com.adesk.mvpframe.base.delegate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adesk.mvpframe.base.BaseFragment;
import com.adesk.mvpframe.base.BasePresenter;

/**
 * ================================================
 * 框架要求框架中的每个 {@link Fragment} 都需要实现此类,以满足规范
 *
 * @see BaseFragment
 * ================================================
 */
public interface IFragment {

    BasePresenter setPresenter();

    /**
     * 初始化 View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);


}
