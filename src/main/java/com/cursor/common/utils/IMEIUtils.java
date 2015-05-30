package com.cursor.common.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.cursor.common.AppData;

/**
 * USER: ldx
 * EMAIL: danxionglei@foxmail.com
 * PROJECT_NAME: SpeechProject
 * DATE: 2015/3/20
 */
public class IMEIUtils {
    public static String getIMEIId(){
         TelephonyManager telephonyManager =
                (TelephonyManager) AppData.getContext().getSystemService( Context.TELEPHONY_SERVICE );
        /*
         * getDeviceId() function Returns the unique device ID.
         * for example,the IMEI for GSM and the MEID or ESN for CDMA phones.
         */
        return telephonyManager.getDeviceId();
    }
}
