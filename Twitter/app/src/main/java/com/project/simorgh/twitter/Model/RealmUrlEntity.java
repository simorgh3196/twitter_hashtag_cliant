package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmUrlEntity extends RealmObject {

    @PrimaryKey
    private String entity_id; // (status_id):(url)[(start_index)-(end_index)]

    private RealmTweetEntities tweet_entity;
    private RealmUserEntitities user_entity;

    private String url;
    private String expanded_url;
    private String display_url;

    private int start_index;
    private int end_index;

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public RealmTweetEntities getTweet_entity() {
        return tweet_entity;
    }

    public void setTweet_entity(RealmTweetEntities tweet_entity) {
        this.tweet_entity = tweet_entity;
    }

    public RealmUserEntitities getUser_entity() {
        return user_entity;
    }

    public void setUser_entity(RealmUserEntitities user_entity) {
        this.user_entity = user_entity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpanded_url() {
        return expanded_url;
    }

    public void setExpanded_url(String expanded_url) {
        this.expanded_url = expanded_url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
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
