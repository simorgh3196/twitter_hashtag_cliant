package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmMediaEntity extends RealmObject {

    @PrimaryKey
    private String entity_id; // (status_id):(media_url)[(start_index)-(end_index)]

    private long status_id;
    private RealmTweetEntities tweet_entity;

    private String media_url;
    private String media_url_https;

    private int size_w;
    private int size_h;
    private String resize;
    private String type;

    private int start_index;
    private int end_index;


    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public RealmTweetEntities getTweet_entity() {
        return tweet_entity;
    }

    public void setTweet_entity(RealmTweetEntities tweet_entity) {
        this.tweet_entity = tweet_entity;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStart_index() {
        return start_index;
    }

    public void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    public int getEnd_index() {
        return end_index;
    }

    public void setEnd_index(int end_index) {
        this.end_index = end_index;
    }
}
