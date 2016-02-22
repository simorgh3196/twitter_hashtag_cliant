package com.project.simorgh.twitter.Model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by TomoyaHayakawa on 16/02/20.
 */
public class RealmMediaEntity extends RealmObject {

    @PrimaryKey
    private long id;

    private String media_url;
    private String media_url_https;
    // private MediaEntity.Sizes sizes;
    private int size_w;
    private int size_h;
    private String resize;
    private long source_status_id;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getMedia_url_https() {
        return media_url_https;
    }

    public void setMedia_url_https(String media_url_https) {
        this.media_url_https = media_url_https;
    }

    public int getSize_w() {
        return size_w;
    }

    public void setSize_w(int size_w) {
        this.size_w = size_w;
    }

    public int getSize_h() {
        return size_h;
    }

    public void setSize_h(int size_h) {
        this.size_h = size_h;
    }

    public String getResize() {
        return resize;
    }

    public void setResize(String resize) {
        this.resize = resize;
    }

    public long getSource_status_id() {
        return source_status_id;
    }

    public void setSource_status_id(long source_status_id) {
        this.source_status_id = source_status_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
