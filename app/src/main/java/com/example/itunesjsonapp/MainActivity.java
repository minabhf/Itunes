package com.example.itunesjsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ItunesHTTPClient;
import com.JsonItunesParser;
import com.Model.ItunesStuff;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtArtistName;
    TextView txtType;
    TextView txtKind;
    TextView txtCollectionName;
    TextView txtTrackName;
    ImageView imgArt;
    String imgURL;
    Button btnGetData;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtArtistName = findViewById(R.id.txtArtistName);
        txtType = findViewById(R.id.txtType);
        txtKind = findViewById(R.id.txtKind);
        txtCollectionName = findViewById(R.id.txtCollectionName);
        txtTrackName = findViewById(R.id.txttrackName);
        imgArt = findViewById(R.id.imgArt);
        btnGetData = findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(MainActivity.this); //setOnClickLIstener رو به روش قدیم اجرا کرده
    }

    @Override
    public void onClick(View v) {

        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();//اطلاعات jsonItunesStuff رو احرا کن

    }
 //کلاس JSONItunesStuffTask آرث بری کزده از AsyncTask .گفته ورودی شما از جنس string هست  و هیچ پردازشی نداره وخروجی شما Itunesstuff هست
    private class JSONItunesStuffTask extends AsyncTask<String, Void, ItunesStuff> {
        Context context; //از کلاس Contex یه متغیر میگیریم
        ProgressDialog progressDialog;  //از کلاس Progressdialog هم متغیر میگیریم

        public JSONItunesStuffTask(Context context) {  //برای کلاس JSONItunesStuffTask کانستراکتور مینویسیم / یعنی هرزمانی که این کلاسو صدا بزنم اول باید کانستراکتور رو پرکنم

            this.context = context;
            progressDialog = new ProgressDialog(context);

        }

        @Override
        protected void onPreExecute() { //قبل از اینکه کدهای من اجرا بشه
            super.onPreExecute();
// قبل از اینکه صفجه ای که میخوام باز شه این متن رو به من نشون بده
            progressDialog.setTitle("Downloading info From Itunes.....Please Wait");
            progressDialog.show();
        }

        @Override
        protected ItunesStuff doInBackground(String... strings) {  //توی پشت زمینه
            ItunesStuff itunesStuff = new ItunesStuff(); //آبجکت گرفتیم
            ItunesHTTPClient itunesHTTPClient = new ItunesHTTPClient(); //آبجکت گرفتیم
            String data = (itunesHTTPClient.getItunesStuffData()); //itunesHTTPClient یه تابع داشت به نام getItunesStuffData که طلاعات سایت در اون بودن .میگیم همه اون اطلاعات رو بگیر  وداخل استرینگ data ذخیره کن
            try {
                itunesStuff = JsonItunesParser.getItunesStuff(data); //اطلاعات سایت رو (data) پاس میدیم به getItnesStuff ت جیسون ها رو به وسیله یJsonItunesParser  از هم جدا کنه بریزه داخل itunesStuff

                imgURL = itunesStuff.getArtistViewUrl(); //عکس رو هم بگیر بریز داخل imgURL
                bitmap = (itunesHTTPClient.getBitmapFromURL(imgURL));//توی itunesHTTPClient تابعی بود به نام getBitmapFromURL وحود داره که آدرس سایت رو تبدیل به  عکس کردیم و ریختیم داهل bitmap
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return itunesStuff;
        }

        @Override
        protected void onPostExecute(ItunesStuff itunesStuff) { //زمانی که پستمون انتشار پیدا میکنه
            super.onPostExecute(itunesStuff);

            txtArtistName.setText(itunesStuff.getArtistName()); //میخوایم روی txtArtistName یه تکست بندازیم یعنی (setText) از itunesStuff name رو میگیرم و ست میکنم
            txtType.setText(itunesStuff.getType());
            txtKind.setText(itunesStuff.getKind());
            txtCollectionName.setText(itunesStuff.getCollectionName());
            txtTrackName.setText(itunesStuff.getTrackName());
            imgArt.setImageBitmap(bitmap);//از bitmap عکس رو گرفتیم و میریزیمش داحل imgArt

            if (progressDialog.isShowing()) {  //اگر progressDialog همچنان در حال نمایش یود
                progressDialog.dismiss();  // dismiss ش کن که دیگه نشون نده
            }
        }


    }
}