package prochito.its.skut.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import prochito.its.skut.R;
import prochito.its.skut.Support.SharedPreferencesManager;

public class InsertCodeActivity extends AppCompatActivity {

    private SharedPreferencesManager mySharedPreferences;
    private boolean isMobileVerified = false;

    private TextView mMsg;
    private EditText mInputOne, mInputTwo, mInputThree, mInputFour;
    private Button mNextInsertCodeBtn;
    private String mVerificationCode, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_code);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_insert_code);
     //   toolbar.setLogo(R.drawable.skut_toolbar);
        toolbar.setTitle("SKüT");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        phoneNumber = getIntent().getStringExtra("phoneNumber");

     /*   View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/

        mySharedPreferences = new SharedPreferencesManager(this);
        //isMobileVerified = mySharedPreferences.getBooleanValueFromSharePref("isMobileVerified");

        //if mobile number verified
        //mySharedPreferences.setBooleanValueInSharePref("isMobileVerified", true);


        mMsg = (TextView) findViewById(R.id.msg_insert_code);
        mMsg.setText("Enter the 4-digit code send to +1\n" + "" + phoneNumber);

        mNextInsertCodeBtn = (Button) findViewById(R.id.insert_code_btn);
        mNextInsertCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerificationCode = getVerificationCode();
                if (mVerificationCode.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Another Code Sending...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Inserted Code is " + mVerificationCode, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(InsertCodeActivity.this, HomeActivity.class);
                    startActivity(i);
                }

            }
        });

        mInputOne = (EditText) findViewById(R.id.insert_code_one);
        mInputTwo = (EditText) findViewById(R.id.insert_code_two);
        mInputThree = (EditText) findViewById(R.id.insert_code_three);
        mInputFour = (EditText) findViewById(R.id.insert_code_four);


        if (mInputOne.getText().toString().length() == 0) {
            mNextInsertCodeBtn.setText("RESEND CODE NOW");
        }


        mInputOne.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (mInputOne.getText().toString().length() == 1)     //size as per your requirement
                {
                    mInputTwo.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        mInputTwo.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (mInputOne.getText().toString().length() == 1)     //size as per your requirement
                {
                    mInputThree.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        mInputThree.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (mInputOne.getText().toString().length() == 1)     //size as per your requirement
                {
                    mInputFour.requestFocus();
                  //  mNextInsertCodeBtn.setText("NEXT");
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        mInputFour.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (mInputOne.getText().toString().length() == 1)     //size as per your requirement
                {
                 //   mInputFour.requestFocus();
                    mNextInsertCodeBtn.setText("NEXT");
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

    }

    private String getVerificationCode() {
        String a = mInputOne.getText().toString();
        String b = mInputTwo.getText().toString();
        String c = mInputThree.getText().toString();
        String d = mInputFour.getText().toString();

        String code = a + b + c + d;

        return code;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;

    }
}
