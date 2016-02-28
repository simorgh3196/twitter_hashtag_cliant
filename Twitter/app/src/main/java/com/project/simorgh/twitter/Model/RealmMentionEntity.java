package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class RealmMentionEntity extends RealmObject {

    @PrimaryKey
    private String entity_id; // (status_id):(target_status_id)[(start_index)-(end_index)]

    private RealmTweetEntities tweet_entity;

    private long target_status_id;
    private String name;
    private String screenName;

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

    public long getTarget_status_id() {
        return target_status_id;
    }

    public void setTarget_status_id(long target_status_id) {
        this.target_status_id = target_status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
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
