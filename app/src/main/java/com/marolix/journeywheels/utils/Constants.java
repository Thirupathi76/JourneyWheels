package com.marolix.journeywheels.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Constants {

    public static final String MYPREFERENCES = "mypreferences";
    public static final int REQ_PERMISSION = 3020;
    public static final String LOGIN_PREFERENCES = "login_preferences";

    public static final String CATEGORY_1 = "CATEGORY_1";
    public static final String CATEGORY_2 = "CATEGORY_2";
    public static final String CATEGORY_3 = "CATEGORY_3";

    public static final String SUCCESS = "200";
    public static final String FAILED = "100";
    public static final String INVALID = "101";
    public static final String INVALID_DEVICE = "300";
    public static final String UPLOADED = "uploaded";

    public static final String APK_VERSION = "apk_version";
    public static final String USER_TYPE = "user_type";
    public static final String FULL_ADDRESS = "address";
    public static final String STATUS = "status";
    public static final String TIMESTAMP = "date";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";

    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String IMEI = "imei";
    public static final String PASSWORD = "password";
    public static final String COMP_NAME = "comp_name";
    public static final String BRANCH = "branch";
    public static final String DEPT = "dept";
    public static final String DESIGNATION = "designation";
    public static final String EMAIL = "email";

    public static final String LOGIN_TIME = "login_time";
    public static final String LOGOUT_TIME = "logout_time";
    public static final String LAST_LAT = "last_lat";
    public static final String LAST_LON = "last_lon";

    public static final String SERVICE_RUNNING = "service_running";
    public static String userlocationPath;
    public static final int NOTIFICATION_ID = 1337;
    public static final String AlREADY_LOGGED_IN = "300";
    public static final String INVALID_LOGIN = "100";
    public static final String LOGIN_SUCCESS = "200";
    public static final String TIME_INTERVAL_BTW_UPDATES = "time_interval";
    public static final String DEVICE_ID = "device_id";
    public static final String PREVIOUS_LOGIN_DATE = "previous_login_date";
    public static final String CURRENT_LOGIN_DATE = "current_login_date";
    public static final String USER_LOGGED_OUT = "user_logged_out";
    public static final String CHANGES = "changes";

    public static final String FIRST_LOC_RECORD = "first_loc_record";
    public static final String LOGIN_LAT = "login_lat";
    public static final String LOGIN_LON = "login_lon";
    public static final String LOGIN_FIRST_REC_TIME = "login_first_rec_time";

    public static boolean checkPermission(Context context) {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        /*int FourthPermissionResult = ContextCompat.checkSelfPermission(context, READ_PHONE_STATE);*/
        int FifthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                /*FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&*/
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity context) {

        ActivityCompat.requestPermissions(context, new String[]{
                        CAMERA,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                        /*READ_PHONE_STATE,*/
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION
                }, REQ_PERMISSION);

    }


}