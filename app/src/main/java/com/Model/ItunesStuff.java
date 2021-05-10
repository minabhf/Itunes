package com.Model;

public class ItunesStuff {

    private String type;
    private String kind;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String artistViewUrl; //عکس
//getter &setter بهشون میدیم.
  //تمام دیتاهایی که از اینترنت قرار بگیریم اول داخل این متغیرها ست میکنیم بعدا هرجا دلمون بخواد مقدار این متغیرهارو میگیریم و داخل دیزاینمون نمایش میدیم
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }
}
