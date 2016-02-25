package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmHashtagEntity extends RealmObject {

    @PrimaryKey
    private String entity_id; // (status_id):(text)[(start_index)-(end_index)]

    private long status_id;
    private RealmTweetEntities tweet_entity;

    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
