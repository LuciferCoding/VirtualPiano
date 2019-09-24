package com.adesk.virtualpiano;

import android.content.Context;
import android.content.res.AssetManager;

import com.adesk.pianokeyboardlib.keyboard.Key;
import com.adesk.pianokeyboardlib.keyboard.PianoKeyBoard;
import com.adesk.virtualpiano.mvp.model.entity.Music;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tony on 2019/9/23.
 * 自动播放器
 */
public class PianoPlayer {
    PianoKeyBoard mPianoKeyBoard;
    private double mInterval;
    private CompositeDisposable mCompositeDisposable;

    public PianoPlayer(PianoKeyBoard pianoKeyBoard) {
        mPianoKeyBoard = pianoKeyBoard;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void play(Context context, String fileName) {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
        String jsonStr = getJson(context, fileName);
        Music music = JsonToObject(jsonStr, Music.class);
        mInterval = calculateInterval(music);
        String[][] tracks = music.getTracks();
        long time = 0;
        for (String[] track : tracks) {
            for (String rhythm : track) {
                Disposable disposable = Observable.timer(time, TimeUnit.MILLISECONDS)
                        .subscribe(aLong -> mPianoKeyBoard.simulateKeyDown(rhythmToKey(rhythm)));
                mCompositeDisposable.add(disposable);
                time += getIntervalTime(rhythm);
            }
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    /**
     * 将节奏转为key
     *
     * @param rhythm
     * @return
     */
    private Key rhythmToKey(String rhythm) {
        String[] r1 = rhythm.split("（");
        String s = r1[0];
        char k = s.charAt(0);
        int index = KeyIndexFactory.create(k);
        return mPianoKeyBoard.getList().get(index);
    }

    /**
     * 解析间隔时间
     *
     * @param rhythm
     * @return
     */
    private long getIntervalTime(String rhythm) {
        String matcher = getMatcher(rhythm);
        return (long) (Double.parseDouble(matcher) * mInterval);
    }

    /**
     * 获取小括号的内容
     *
     * @param source
     * @return
     */
    private String getMatcher(String source) {
        String result = "";
        Pattern pattern = Pattern.compile("\\(([^}]*)\\)");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(1);//只取第一组
        }
        return result;
    }

    /**
     * 根据bmp的值计算每拍的时间
     *
     * @param music
     * @return
     */
    private static double calculateInterval(Music music) {
        return 60.0 * 1000.0 / music.getBpm();
    }


    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    private static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为 对象
     *
     * @param json
     * @param type
     * @return
     */
    private static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    private static class KeyIndexFactory {

        public static int create(char rhythm) {
            int index = 0;
            //测试
            switch (rhythm) {
                case 'a':
                    index = 28;
                    break;
                case 'b':
                    index = 29;
                    break;
                case 'c':
                    index = 23;
                    break;
                case 'd':
                    index = 24;
                    break;
                case 'e':
                    index = 25;
                    break;
                case 'f':
                    index = 26;
                    break;
                case 'g':
                    index = 27;
                    break;
                default:
                    break;
            }
            return index;
        }

    }
}
