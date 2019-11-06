package prochito.its.skut.Support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sohan on 12/27/2018.
 */

public class SharedPreferencesManager {

    private Context context;
    private SharedPreferences prefs;


    public SharedPreferencesManager(Context mContext) {

        context = mContext;
        prefs = context.getSharedPreferences("skut-android", Context.MODE_PRIVATE);
    }

    public void setStringValueInSharePref(String keyName, String value) {
        prefs.edit().putString(keyName, value).apply();
    }

    public void setBooleanValueInSharePref(String keyName, boolean value) {
        prefs.edit().putBoolean(keyName, value).apply();
    }

    public String getStringValueFromSharePref(String keyName) {
        return prefs.getString(keyName, "");
    }

    public boolean getBooleanValueFromSharePref(String keyName) {
        return prefs.getBoolean(keyName, false);
    }

    public void setIntegerValueInSharePref(String keyName, int value) {
        prefs.edit().putInt(keyName, value).apply();
    }

    public int getIntegerValueInSharePref(String keyName) {
        return prefs.getInt(keyName, 0);
    }


}
