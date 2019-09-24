package com.adesk.virtualpiano.mvp.model.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by tony on 2019/9/23.
 */
public class Music {

    private String name;
    private int bpm;
    private String jiepai;
    private String changmin;
    private String[][] tracks;

    public Music(String name, int bpm, String jiepai, String changmin, String[][] tracks) {
        this.name = name;
        this.bpm = bpm;
        this.jiepai = jiepai;
        this.changmin = changmin;
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getJiepai() {
        return jiepai;
    }

    public void setJiepai(String jiepai) {
        this.jiepai = jiepai;
    }

    public String getChangmin() {
        return changmin;
    }

    public void setChangmin(String changmin) {
        this.changmin = changmin;
    }

    public String[][] getTracks() {
        return tracks;
    }

    public void setTracks(String[][] tracks) {
        this.tracks = tracks;
    }


    @NotNull
    @Override
    public String toString() {
        return Arrays.toString(tracks);
    }
}
