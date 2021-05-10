package com;

import com.Model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//این کلاس برای جدا کردن جیسون هاست
public class JsonItunesParser {
    //خروحی این تابع از حنس ItunesStuff هست
//یدونه public static از ItunesStuff میسازم و متغیر بهش میدم و میگم یه urlمیخواد بیاد داخلت
    public static ItunesStuff getItunesStuff(String url) throws JSONException {
//جون در نهایت همه اطلاعات سایت رو میخوایم بریزیم توی ItunesStuff پس میام از کلاس ItunesStuff آبجکت میگیریم
        ItunesStuff itunesStuff = new ItunesStuff();
//از کلاس JSONObject آبجکت میگیریم وurl که همون آدرس سایت هست  رو بهش پاس میدیم
        JSONObject itunesStuffJsonObject = new JSONObject(url);
//ازJSONArray یه متغیر میگیریم و میگیم داخل itunesSttufJsonObject (منظور کل جیسون هست که با یه آکولاد شروع شده و با یه آکولاد  تموم شده)یدونه JSONArray هست که اسمش  result اونو بگیرش
        JSONArray resultJsonArray = itunesStuffJsonObject.getJSONArray("results");

        // از کلاس JSONObject یه متغبر میگیریم ومیگیم داخل خونه صفرمش یه JSONObject هست اونو بگیرش
        //{result[{
        JSONObject artistObject = resultJsonArray.getJSONObject(0);

        //میخوام یه جیزی رو روی itunesStuff ست تای(setType)
//یه استرینگی هست به نام wrapperType از artistObject بگیرش
        //track که مقدار wraperType هست رو گرفتیم و و روی type ریختیم
        itunesStuff.setType(getString("wrapperType", artistObject));
        itunesStuff.setKind(getString("kind", artistObject));
        itunesStuff.setArtistName(getString("artistName",artistObject));
        itunesStuff.setCollectionName(getString("collectionName", artistObject));
        itunesStuff.setArtistViewUrl(getString("artworkur1100",artistObject));
        itunesStuff.setTrackName(getString("trackName", artistObject));


        return itunesStuff;  //در نهایت itunesSttaf رو باید برگردونه
    }
//وقتی خروحی ما JSONObject باشه ممکنه توی آرایه ای jsonObjectداشته باشیم
    private static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getJSONObject(tagName);
        // یه استرینگ میخوام از tagName بگیر(یعنی tagName رو بهش میدیم jsonObject بهمون برمیگردونه)
    }

    private static String getString (String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    private static float getFloat(String tagName,JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    private static int getInt (String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
    private static boolean getBoolean(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getBoolean(tagName);
    }
}
//خروحی های ما JSONObject,Strind,Boolean,Int,Float هستند