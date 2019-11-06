package prochito.its.skut.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import prochito.its.skut.Support.FourPhoneNumberFormatWatcher;
import prochito.its.skut.R;

public class MobileVerificationActivity extends AppCompatActivity {

    private CountryCodePicker ccp;
/*    private SharedPreferencesManager mySharedPreferences;
    private boolean isMobileVerified = false;*/

    private EditText mContactNumber;
    private Button mBtnNext;
    private String calling_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        /*mySharedPreferences = new SharedPreferencesManager(this);
        isMobileVerified = mySharedPreferences.getBooleanValueFromSharePref("isMobileVerified");*/

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        mContactNumber = (EditText) findViewById(R.id.contact_number);
        mContactNumber.addTextChangedListener(new FourPhoneNumberFormatWatcher());

        mBtnNext = (Button) findViewById(R.id.next_btn_mv);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoneNumber();

            }
        });

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                calling_code = selectedCountry.getPhoneCode();
                // Toast.makeText(getApplicationContext(), "Updated " + selectedCountry.getPhoneCode(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getPhoneNumber() {

        mContactNumber.setError(null);

        String phoneNumber = mContactNumber.getText().toString();
        phoneNumber = phoneNumber.replace(" ", "");

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(phoneNumber)) {
            mContactNumber.setError("Please insert a phone number");
            focusView = mContactNumber;
            cancel = true;
        }

        if (cancel) {
            // form field with an error.
            focusView.requestFocus();
        } else {
            Intent i = new Intent(MobileVerificationActivity.this, InsertCodeActivity.class);
            i.putExtra("phoneNumber", phoneNumber);
            startActivity(i);


        }
    }
}
