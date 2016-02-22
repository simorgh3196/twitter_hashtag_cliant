package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmTweet extends RealmObject {

    @PrimaryKey
    private long status_id;

    private String             user_id_str;
    private String             screen_name;
    private String             name;
    private String             text;
    private String             created_at;
    private RealmTweetEntities entities;
    private RealmTweetEntities extended_etities;
    private Integer            favorite_count;
    private boolean            favorited;
    private String             in_reply_to_screen_name;
    private long               in_reply_to_status_id;
    private long               in_reply_to_user_id;
    private String             in_reply_to_user_id_str;
    private String             lang;
    private boolean            possibly_sensitive;
    private int                retweet_count;
    private boolean            retweeted;
    private RealmTweet         retweeted_status;
    private String             source;

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public String getUser_id_str() {
        return user_id_str;
    }

    public void setUser_id_str(String user_id_str) {
        this.user_id_str = user_id_str;
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

    public Integer getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Integer favorite_count) {
        this.favorite_count = favorite_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
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

    public String getIn_reply_to_user_id_str() {
        return in_reply_to_user_id_str;
    }

    public void setIn_reply_to_user_id_str(String in_reply_to_user_id_str) {
        this.in_reply_to_user_id_str = in_reply_to_user_id_str;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isPossibly_sensitive() {
        return possibly_sensitive;
    }

    public void setPossibly_sensitive(boolean possibly_sensitive) {
        this.possibly_sensitive = possibly_sensitive;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public RealmTweet getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(RealmTweet retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
