package com.example.command412;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ASUS on 2016/4/6.
 */
public class MassageActivity extends Activity{

    Phone phone = new Phone(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.massage);

        final EditText eidtName = (EditText)findViewById(R.id.editName);
        final EditText eidtMassage = (EditText)findViewById(R.id.editMassage);
        Button  btnSend = (Button)findViewById(R.id.btnMassage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String MassageName = null;
        if(bundle != null){
            Log.v("dx2", "in");
            MassageName = (String)bundle.getSerializable("MassageName");
            eidtName.setText(MassageName);
        }
        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String PhoneName=eidtName.getText().toString();
                String Massage=eidtMassage.getText().toString();
                String PinYinName = phone.getPinYin(PhoneName);

                char[] CharPinYin = PhoneName.toCharArray();
                phone.FuzzySearch(String.valueOf(CharPinYin[0]));

                if (phone.phoneName.size() > 0) {
                    for (int i = 0; i < phone.phoneName.size(); i++) {
                        String GetFuzzyPinYin = phone.getPinYin(phone.phoneName.get(i));
                        if (PinYinName.equals(GetFuzzyPinYin) && phone.phoneId.size() > 0) {
                            Long PhoneNum = phone.GainPhoneNum(phone.phoneId.get(i));
                            SmsManager manager = SmsManager.getDefault();
                            ArrayList<String> list = manager.divideMessage(Massage);  //因为一条短信有字数限制，因此要将长短信拆分
                            for(String text:list){
                                manager.sendTextMessage(String.valueOf(PhoneNum), null, text, null, null);
                            }
                            Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }

        });

    }
}
