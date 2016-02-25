package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmTweet extends RealmObject {

    @PrimaryKey
    private long id;

    private long   user_id;
    private String screen_name;
    private String name;
    private String text;
    private String created_at;
    private String lang;
    private String source;

    private int     favorite_count;
    private int     retweet_count;
    private boolean favorited;
    private boolean retweeted;
    private boolean possibly_sensitive;

    private String in_reply_to_screen_name;
    private long   in_reply_to_status_id;
    private long   in_reply_to_user_id;
    private RealmTweet retweeted_status;

    private RealmTweetEntities entities;
    private RealmTweetEntities extended_etities;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public boolean isPossibly_sensitive() {
        return possibly_sensitive;
    }

    public void setPossibly_sensitive(boolean possibly_sensitive) {
        this.possibly_sensitive = possibly_sensitive;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public long getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(long in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public long getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(long in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public RealmTweet getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(RealmTweet retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public RealmTweetEntities getEntities() {
        return entities;
    }

    public void setEntities(RealmTweetEntities entities) {
        this.entities = entities;
    }

    public RealmTweetEntities getExtended_etities() {
        return extended_etities;
    }

    public void setExtended_etities(RealmTweetEntities extended_etities) {
        this.extended_etities = extended_etities;
    }
}
