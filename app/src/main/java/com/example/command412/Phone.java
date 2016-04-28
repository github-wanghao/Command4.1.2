package com.example.command412;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ASUS on 2016/4/6.
 */
public class Phone {
    public List<String> phoneName = new ArrayList<String>();
    public List<String> phoneId = new ArrayList<String>();

    private Context context;

    Phone(Context context) {
        this.context = context;
    }

    public void Call(String Name) {
        // FuzzySearch
        String GetPinYinName = getPinYin(Name);
        char[] CharPinYin = Name.toCharArray();
        FuzzySearch(String.valueOf(CharPinYin[0]));
        Log.v("1","2");
        if (phoneName.size() > 0) {
            Log.v("3","4");
            for (int i = 0; i < phoneName.size(); i++) {
                String GetFuzzyPinYin = getPinYin(phoneName.get(i));
                if (GetPinYinName.equals(GetFuzzyPinYin) && phoneId.size() > 0) {
                    Long PhoneNum = GainPhoneNum(phoneId.get(i));
                    CallPhoneNum(PhoneNum);

                }
            }
        }

    }

    public Long GainPhoneNum(String phoneNameId) {
        int i;
        Long phone = null;
        String phoneNumber = null;
		/*
		 * 查找该联系人的phone信息
		 */
        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                        + phoneNameId, null, null);
        int phoneIndex = 0;
        if (phones.getCount() > 0) {
            phoneIndex = phones
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        }
        while (phones.moveToNext()) {
            phoneNumber = phones.getString(phoneIndex);
            Log.i("null", phoneNumber);
        }
        phone = PhoneToNum(phoneNumber);
        return phone;
    }

    public Long PhoneToNum(String Num) {
        String No = null;
        char[] temp = new char[Num.length()];
        temp = Num.toCharArray();
        char[] copyPhone = Arrays.copyOf(temp, temp.length);
        String[] copy = new String[copyPhone.length];
        for (int n = 0; n < copyPhone.length; n++) {
            copy[n] = String.valueOf(copyPhone[n]);

            if (!copy[n].endsWith(" ")) {
                if (!copy[n].equals("-")) {

                    if (No == null) {
                        No = copy[n];
                    } else {
                        No = No + copy[n];
                    }
                }
            }
        }
        return Long.parseLong(No.trim());
    }

    public void CallPhoneNum(Long Num) {

        Toast.makeText(context, String.valueOf(Num), Toast.LENGTH_LONG).show();
        if (!Num.equals(null)) {
            // 取得输入的电话号码串
            String inputStr = String.valueOf(Num);
            // 如果输入不为空创建打电话的Intent
            if (inputStr.trim().length() != 0) {
                Intent phoneIntent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + inputStr));
                // 启动
                context.startActivity(phoneIntent);
            }
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            context.startActivity(intent);
        }
    }

    public static String getPinYin(String inputString) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        char[] input = inputString.trim().toCharArray();
        StringBuffer output = new StringBuffer("");

        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                            input[i], format);
                    output.append(temp[0]);
                    output.append(" ");
                } else
                    output.append(Character.toString(input[i]));
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void FuzzySearch(String key) {
        Log.v("qweqwe", key);
        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID };
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                ContactsContract.PhoneLookup.DISPLAY_NAME + " LIKE" + " '%"
                        + key + "%'", null, null); // Sort order.
        String[] SearchName = new String[cursor.getCount()];
        Log.d("Fuzzy", String.valueOf(cursor));
        if (cursor == null) {
            Log.v("tag", "getPeople null");
        }
        Log.d("tag", "getPeople cursor.getCount() = " + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String uname = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.v("TAG", "Name is : " + uname);
            Log.d("Fuzzy", String.valueOf(i));
            phoneName.add(uname);
            String number = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            Log.v("TAG ", "Number is : " + number);
            phoneId.add(number);
        }
        cursor.close();
    }
}
