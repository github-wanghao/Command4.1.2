package com.example.command412;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    static char[] FamilyNames = new char[] { '赵', '钱', '孙', '李', '周', '吴', '郑',
            '王', '冯', '陈', '楮', '卫', '蒋', '沈', '韩', '杨', '朱', '秦', '尤', '许',
            '何', '吕', '施', '张', '孔', '曹', '严', '华', '金', '魏', '陶', '姜', '戚',
            '谢', '邹', '喻', '柏', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范',
            '彭', '郎', '鲁', '韦', '昌', '马', '苗', '凤', '花', '方', '俞', '任', '袁',
            '柳', '酆', '鲍', '史', '唐', '费', '廉', '岑', '薛', '雷', '贺', '倪', '汤',
            '滕', '殷', '罗', '毕', '郝', '邬', '安', '常', '乐', '于', '时', '傅', '皮',
            '卞', '齐', '康', '伍', '余', '元', '卜', '顾', '孟', '平', '黄', '和', '穆',
            '萧', '尹', '姚', '邵', '湛', '汪', '祁', '毛', '禹', '狄', '米', '贝', '明',
            '臧', '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈',
            '项', '祝', '董', '梁', '杜', '阮', '蓝', '闽', '席', '季', '麻', '强', '贾',
            '路', '娄', '危', '江', '童', '颜', '郭', '梅', '盛', '林', '刁', '锺', '徐',
            '丘', '骆', '高', '夏', '蔡', '田', '樊', '胡', '凌', '霍', '虞', '万', '支',
            '柯', '昝', '管', '卢', '莫', '经', '房', '裘', '缪', '干', '解', '应', '宗',
            '丁', '宣', '贲', '邓', '郁', '单', '杭', '洪', '包', '诸', '左', '石', '崔',
            '吉', '钮', '龚', '程', '嵇', '邢', '滑', '裴', '陆', '荣', '翁', '荀', '羊',
            '於', '惠', '甄', '麹', '家', '封', '芮', '羿', '储', '靳', '汲', '邴', '糜',
            '松', '井', '段', '富', '巫', '乌', '焦', '巴', '弓', '牧', '隗', '山', '谷',
            '车', '侯', '宓', '蓬', '全', '郗', '班', '仰', '秋', '仲', '伊', '宫', '宁',
            '仇', '栾', '暴', '甘', '斜', '厉', '戎', '祖', '武', '符', '刘', '景', '詹',
            '束', '龙', '叶', '幸', '司', '韶', '郜', '黎', '蓟', '薄', '印', '宿', '白',
            '怀', '蒲', '邰', '从', '鄂', '索', '咸', '籍', '赖', '卓', '蔺', '屠', '蒙',
            '池', '乔', '阴', '郁', '胥', '能', '苍', '双', '闻', '莘', '党', '翟', '谭',
            '贡', '劳', '逄', '姬', '申', '扶', '堵', '冉', '宰', '郦', '雍', '郤', '璩',
            '桑', '桂', '濮', '牛', '寿', '通', '边', '扈', '燕', '冀', '郏', '浦', '尚',
            '农', '温', '别', '庄', '晏', '柴', '瞿', '阎', '充', '慕', '连', '茹', '习',
            '宦', '艾', '鱼', '容', '向', '古', '易', '慎', '戈', '廖', '庾', '终', '暨',
            '居', '衡', '步', '都', '耿', '满', '弘', '匡', '国', '文', '寇', '广', '禄',
            '阙', '东', '欧', '殳', '沃', '利', '蔚', '越', '夔', '隆', '师', '巩', '厍',
            '聂', '晁', '勾', '敖', '融', '冷', '訾', '辛', '阚', '那', '简', '饶', '空',
            '曾', '毋', '沙', '乜', '养', '鞠', '须', '丰', '巢', '关', '蒯', '相', '查',
            '后', '荆', '红', '游', '竺', '权', '逑', '盖', '益', '桓', '公', '仉', '督',
            '晋', '楚', '阎', '法', '汝', '鄢', '涂', '钦', '岳', '帅', '缑', '亢', '况',
            '后', '有', '琴', '归', '海', '墨', '哈', '谯', '笪', '年', '爱', '阳', '佟',
            '商', '牟', '佘', '佴', '伯', '赏' };

    Phone phone = new Phone(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtCommand = (EditText) findViewById(R.id.editCommand);
        Button btnPerform = (Button) findViewById(R.id.btnCommand);

        btnPerform.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Command = txtCommand.getText().toString();
                Log.v("SHURU", Command);
                String test = "打电话给王灏";
                String[] PingYin = getParagraph(Command);
                getFunction(PingYin);

            }
        });
    }


    // 转化
    public String[] getParagraph(String inputString) {
        char[] temp = new char[inputString.length()];
        temp = inputString.toCharArray();
        String[] paragraph = new String[temp.length];
        for (int i = 0; i < inputString.length(); i++) {
            paragraph[i] = String.valueOf(temp[i]);
        }
        return paragraph;
    }

    public void getFunction(String[] inputString) {
        String Function = null;
        String AppName = "";
        for (int i = 0; i < inputString.length; i++) {
            if (inputString[i] != null && inputString[i] != "") {
                Function = inputString[i];
                Log.v("1",String.valueOf(i));
            }
            switch (Function) {
                case "打":
                    String Name = null;
                    String paragraph = null;
                    if(inputString.length >= (i + 3) ){
                        int  NameLength = inputString.length - i - 1;
                        for(int m = 0; m < NameLength;m++ ){
                            if (NameLength >= (i + 2 + m)) {
                                Name =  inputString[i + 1 + m] + inputString[i + 2 + m];
                                if (inputString[i + 1].equals("开")) {
                                    final WifiManager wifiManager = (WifiManager) super
                                            .getSystemService(Context.WIFI_SERVICE);
                                    int InputLength = inputString.length - (i + 2);
                                    for (int o = 0; o < InputLength; o++) {
                                        if(inputString.length >= (i + 3 + o)){
                                     //   if(inputString[i + 2 + o] != null && inputString[i + 2 + o] != ""){
                                            AppName = AppName + inputString[i + 2 + o];
                                        Boolean BoolApp = OpenApplication(AppName);
                                        if (BoolApp == false && AppName.trim().equals("wifi")) {
                                            wifiManager.setWifiEnabled(true);
                                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                            startActivity(intent);

                                            break;
                                        }
                                    }

                                    }

                                } else if (Name.equals("电话")) {
                                    for (int n = i + 3; n < inputString.length; n++) {
                                        if (paragraph == null) {
                                            paragraph = inputString[n];
                                        } else {
                                            paragraph = paragraph + inputString[n];
                                        }
                                    }
                                    if(paragraph != null) {
                                        String PhoneName = GetChineseName(paragraph.trim());
                                        phone.Call(PhoneName);
                                    }
                                }

                            }
                        }
                    }
                    break;
                case "关":

                    if((inputString.length - i ) > 0 ){
                        if(inputString[i + 1].equals("闭")){
                            final WifiManager wifiManager = (WifiManager) super
                                    .getSystemService(Context.WIFI_SERVICE);
                            int InputLength = inputString.length - (i + 2);
                            for (int o = 0; o < InputLength; o++) {
                                AppName = AppName + inputString[i + 2 + o];
                                Log.v("inputString", inputString[o]);
                                Log.v("AppName", AppName);
                                if(AppName.equals("wifi")){
                                    wifiManager.setWifiEnabled(false);
                                    break;
                                }

                            }
                        }
                    }
                    break;
                case "查":
                    if((inputString.length - i ) > 0){
                        Log.v("1", "in");
                        if(inputString[i + 1].equals("看")){
                            String New = null;
                            Log.v("2", "in");
                            int InputLength = inputString.length - (i + 2);
                            for (int o = 0; o < InputLength; o++) {
                                Log.v("3", "in");
                                if(inputString[i + 5 + o] != null && inputString[i + 5 + o] != ""){
                                    Log.v("4", "in");
                                    String WifiName =  inputString[i + 2 + o] +
                                            inputString[i + 3 + o] +
                                            inputString[i + 4 + o] +
                                            inputString[i + 5 + o];
                                    Log.v("4", WifiName);
                                    switch (WifiName){
                                        case "wifi":

                                        break;
                                        case "社会新闻":

                                            break;
                                        case "体育新闻":

                                            break;
                                        case "科技新闻":

                                            break;
                                        case "国际新闻":

                                            break;
                                        default:
                                            for(int n = 0;n < (WifiName.length() - 1);n++){
                                                New = inputString[i + 2 + o + n] + inputString[i + 3 + o + n];
                                            }
                                            if(New.equals("新闻")){

                                            }
                                            break;
                                    }
                                    if(WifiName.equals("wifi")){
                                        Log.v("chakan", "in");
                                        Intent intent = new Intent(MainActivity.this, WatchWifi.class);
                                        startActivity(intent);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "发":
                    if((inputString.length - i ) > 0 ){
                        String MassageName = null;
                        int InputLength = inputString.length - (i + 1);
                        Log.v("2", "in");
                        for (int o = 0; o < InputLength; o++) {
                            if(InputLength >= (i + 2 + o) ){
                                String Massage = inputString[i + 1 + o]
                                        + inputString[i + 2 + o];
                                Log.v("3", Massage);
                                Log.v("3", "in");
                                if(Massage.equals("短信")){
                                    Log.v("duanxin", "in");
                                    for (int n = i + 3; n < inputString.length; n++) {
                                        if (MassageName == null) {
                                            MassageName = inputString[n];
                                        } else {
                                            MassageName = MassageName + inputString[n];
                                        }
                                    }

                                    if(MassageName != null){
                                        String PhoneName = GetChineseName(MassageName.trim());
                                        Bundle bundle=new Bundle();
                                        bundle.putSerializable("MassageName", PhoneName);
                                        Log.v("MassageName", PhoneName);
                                        Intent intent=new Intent(MainActivity.this,MassageActivity.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }else{
                                        Log.v("no", "no");
                                        Intent intent = new Intent(MainActivity.this, MassageActivity.class);
                                        startActivity(intent);
                                    }

                                    break;
                                }
                            }
                        }
                    }
                    break;
                case "增":
                    if (inputString[i + 1] != null && inputString[i + 1] != "") {
                        if (inputString[i + 1].equals("大")
                                || inputString[i + 1].equals("加")) {

                            int InputLength = inputString.length - i;
                            if (InputLength >= 2) {
                                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                                for (int o = 0; o < InputLength; o++) {
                                    if (InputLength > (i + 3 + o)) {
                                        String CommandName = inputString[i + 2 + o]
                                                + inputString[i + 3 + o];
                                        switch (CommandName) {
                                            case "亮度":
                                                int BrightnessNow = screenBrightness_check();
                                                int Brightness = BrightnessNow + 52;
                                                Log.v("Brightness",String.valueOf(Brightness));
                                                setScreenBritness(Brightness);
                                                break;
                                            case "媒体":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {

                                                        // 音乐回放即媒体音量
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_MUSIC,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_UP);
                                                    }
                                                }
                                                break;
                                            case "提示":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 提示
                                                        mAudioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
                                                    }
                                                }
                                                break;
                                            case "铃声":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 铃声
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_RING,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_UP);
                                                    }
                                                }
                                                break;
                                            case "通话":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 通话
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_VOICE_CALL,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_UP);
                                                    }
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "降":
                    if (inputString[i + 1] != null && inputString[i + 1] != "") {
                        if (inputString[i + 1].equals("低")) {
                            int InputLength = inputString.length - i;
                            if (InputLength >= 2) {
                                for (int o = 0; o < InputLength; o++) {
                                    if (InputLength > (i + 3 + o)) {
                                        String CommandName = inputString[i + 2 + o]
                                                + inputString[i + 3 + o];
                                        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                                        switch (CommandName) {
                                            case "亮度":
                                                int BrightnessNow = screenBrightness_check();
                                                int Brightness = BrightnessNow - 52;
                                                setScreenBritness(Brightness);
                                                break;
                                            case "媒体":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {

                                                        // 音乐回放即媒体音量
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_MUSIC,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                                                    }
                                                }
                                                break;
                                            case "提示":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 提示
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_ALARM,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                                                    }
                                                }
                                                break;
                                            case "铃声":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 铃声
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_RING,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                                                    }
                                                }
                                                break;
                                            case "通话":
                                                if (InputLength > (i + 5 + o)) {
                                                    String SongName = inputString[i
                                                            + 4 + o]
                                                            + inputString[i + 5 + o];
                                                    if (SongName.equals("音量")) {
                                                        // 通话
                                                        mAudioManager
                                                                .adjustStreamVolume(
                                                                        AudioManager.STREAM_VOICE_CALL,
                                                                        AudioManager.ADJUST_RAISE,
                                                                        AudioManager.FX_FOCUS_NAVIGATION_DOWN);
                                                    }
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    // 打电话
    public String GetChineseName(String Name) {
        String EName = null;
        String GName = null;
        String StringPhoneName = null;

        if(!Name.equals(null)){
            char[] FName = new char[Name.length()];
            FName = Name.toCharArray();
            String[] CName = getParagraph(Name);
            for (int i = 0; i < FName.length; i++) {
                EName = CName[i];
                Log.v("EName", EName);
                for (int n = 0; n < FamilyNames.length; n++) {
                    GName = String.valueOf(FamilyNames[n]);
                    if (EName.equals(GName)) {
                        if ((i + 3) < FName.length) {
                            StringPhoneName = String.valueOf(FName[i])
                                    + String.valueOf(FName[i + 1])
                                    + String.valueOf(FName[i + 2])
                                    + String.valueOf(FName[i + 3]);
                            return StringPhoneName;
                        } else if ((i + 2) < FName.length) {

                            StringPhoneName = String.valueOf(FName[i])
                                    + String.valueOf(FName[i + 1])
                                    + String.valueOf(FName[i + 2]);
                            return StringPhoneName;

                        } else if ((i + 1) < FName.length) {
                            StringPhoneName = String.valueOf(FName[i])
                                    + String.valueOf(FName[i + 1]);
                            return StringPhoneName;

                        }

                    }
                }
            }
        }
        return StringPhoneName;
    }

    // 打开App或wifi
    public Boolean OpenApplication(String AppName) {
        final PackageManager packageManager = MainActivity.this
                .getPackageManager();
        final List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);


        for (int i = 0; i < pInfo.size(); i++) {
            PackageInfo p = pInfo.get(i);
            String label = packageManager
                    .getApplicationLabel(p.applicationInfo).toString();
            if (label.equals(AppName.trim())) { // 比较label
                String pName = p.packageName; // 获取包名
                Intent intent = new Intent();
                intent = packageManager.getLaunchIntentForPackage(pName);
                startActivity(intent);
                return true;
            }

        }
        return false;
    }

    private int screenBrightness_check() {
        // 先关闭系统的亮度自动调节

        try {
            if (android.provider.Settings.System.getInt(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE) == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                android.provider.Settings.System
                        .putInt(getContentResolver(),
                                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        // 获取当前亮度,获取失败则返回255
        int intScreenBrightness = (int) (android.provider.Settings.System
                .getInt(getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS, 255));
        String stringScreenBrightness = String.valueOf(intScreenBrightness);
        Log.d("test", stringScreenBrightness);
        return intScreenBrightness;
    }

    // 屏幕亮度
    private void setScreenBritness(int brightness) {
        String StringBrightness = String.valueOf(brightness);
        Log.d("BrightnessBefore", StringBrightness);
        // 不让屏幕全暗
        if (brightness <= 1) {
            brightness = 1;
        } else if (brightness >= 255) {
            brightness = 255;
        }
        // 设置当前activity的屏幕亮度
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        // 0到1,调整亮度暗到全亮
        lp.screenBrightness = Float.valueOf(brightness / 255f);
        this.getWindow().setAttributes(lp);
        Log.d("BrightnessAfter", StringBrightness);

        // 保存为系统亮度方法1
        android.provider.Settings.System.putInt(getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS, brightness);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
