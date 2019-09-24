package com.adesk.virtualpiano.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.adesk.mvpframe.base.BaseActivity;
import com.adesk.mvpframe.base.BasePresenter;
import com.adesk.pianokeyboardlib.keyboard.PianoKeyBoard;
import com.adesk.pianokeyboardlib.sound.SoundPlayUtils;
import com.adesk.virtualpiano.PianoPlayer;
import com.adesk.virtualpiano.R;
import com.adesk.virtualpiano.mvp.contract.PianoMainContract;
import com.adesk.virtualpiano.mvp.presenter.PianoMainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author tony
 */
public class PianoMainActivity extends BaseActivity implements PianoMainContract.View {

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    @BindView(R.id.keyboard)
    PianoKeyBoard mKeyboard;
    private PianoPlayer mPianoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoundPlayUtils.init(getApplicationContext());
        mKeyboard = findViewById(R.id.keyboard);
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mKeyboard.moveToPosition(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setMax(mKeyboard.getMaxMovePosition());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mPianoPlayer = new PianoPlayer(mKeyboard);
    }

    @Override
    protected void onDestroy() {
        mPianoPlayer.stop();
        super.onDestroy();
    }

    @Override
    public BasePresenter setPresenter() {
        return new PianoMainPresenter();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick({R.id.tv_star, R.id.tv_star2})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_star:
                mPianoPlayer.play(this, "小星星.json");
                break;
            case R.id.tv_star2:
                mPianoPlayer.play(this, "小星星2.json");
                break;
        }
    }
}
