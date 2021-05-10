package com;  // این کلاس رو برای متصل شدن به سایت ساختیم

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItunesHTTPClient {

    private static String BASE_URL = "https://itunes.apple.com/search?term=mikhael+jacson";
//یه تابع که خروحیش استرینگ هست به نام getITUNESstuff نوشتم
    public String getItunesStuffData() {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;


        try {
                                                   //BASE_URL استدرینگ هست واسه ی همین اول میدیمش به کلاس URL و گفتیم اتصال پیدا کنه به سایته
            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL)).openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);  //هر موقع بخوایم از سایت اطلاعات بگیریم setDoInput رو true میزاریم
            //httpURLConnection.setDoOutput(true); //   هر وقت بخوایم چیزی روی سایت بنویسیم از setDoOutput استفاده میکنیم
            httpURLConnection.connect();  //به سایت وصل شو

            //let's read the respose
            StringBuffer stringBuffer = new StringBuffer(); //از کلاس StringBuffer آبحکت گرفتم
            inputStream = httpURLConnection.getInputStream(); //چون میخوایم دیتا ها رو بخونیم از inputStream استقاده کردیم / گفتیم HTTPUrlConnection هر جی اطلاعات به صورت url داری یده و بریز توی inputStream
            //جون inputStream به صورت صفر و یک هست اول به کمک InputStreamReader میخونمش اما جون inputStreamReader هم برای من قابل فهم نیست از کلاس BufferReader استقاده میکنم و توی bufferReader میریزیمش  .
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            //   تا زمانی که خطی وجود داره(null نباشه) برای من خط به خط بخون و بریز توی line
            while ((line = bufferedReader.readLine()) != null) {
                //هر بار که خطی خوندی اینتر بزت برو خط بعدی و بعد اضافش کن به stringBuffer
                stringBuffer.append(line + "\n");

            }
            //کارم که با inputStream تمام شد clos میکنیم
            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();// همه اطلاعات رو که دادیم به stringBuffer(همه اطلاغات سایت) تبر=دیل به استرینگ میکنیم و برش میگردونیم

        } catch (IOException e) {
            e.printStackTrace();
        } finally { //درنهایت

            try {
                inputStream.close(); //سعی کن inputStream رو ببندی

                httpURLConnection.disconnect(); //اتصالت به سایت رو هم قطع کنی

            } catch (IOException e) {  //اگر مشکلی هم پیش اومد
                e.printStackTrace(); //پرینتش کن
            }
        }

        return null;
    }
// خروجیمون bitma میذاریم چون عکس هست .از کلاس Bitmat به متغیر میگیریم و میگیم یه استرینگی به نام stringUrl میاد واردت میشه
    public Bitmap getBitmapFromURL (String stringUrl){
        Bitmap bitmap = null;  //از کلاس BItmap یه متغیر گرفتم و مقدار اولیه هم null قرار دادم

        URL url = null;
        try {
            url = new URL(stringUrl);

        InputStream inputStream = url.openStream(); //از کلاس InputStream یه متغیر میگیریم و  میگیم یه سری دیتا از openStream برداشته میشه و داخل inputStream ریخته میشه
        bitmap = BitmapFactory.decodeStream(inputStream); //inputStream رو تبدیل به bitmap میکنم
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}

