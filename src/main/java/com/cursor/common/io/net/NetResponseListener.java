package com.cursor.common.io.net;

import java.util.ArrayList;

/**
 * USER: ldx
 * DATE: 2015-06-19
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class NetResponseListener<T>{
    public void onResponse(Exception e, ArrayList<T> result){
    }
}
