package com.cursor.common.widget.tabgroup;

/**
 * USER: ldx
 * DATE: 2015-06-14
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class TextTab implements Tab{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
