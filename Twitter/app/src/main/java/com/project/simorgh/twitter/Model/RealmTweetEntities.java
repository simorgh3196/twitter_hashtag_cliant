package com.project.simorgh.twitter.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmTweetEntities extends RealmObject {

    @PrimaryKey
    private long status_id;
    private RealmTweet tweet;

    private RealmList<RealmUrlEntity> urls;
    private RealmList<RealmMentionEntity> user_mentions;
    private RealmList<RealmMediaEntity> media;
    private RealmList<RealmHashtagEntity> hashtags;

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public RealmTweet getTweet() {
        return tweet;
    }

    public void setTweet(RealmTweet tweet) {
        this.tweet = tweet;
    }

    public RealmList<RealmUrlEntity> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<RealmUrlEntity> urls) {
        this.urls = urls;
    }

    public RealmList<RealmMentionEntity> getUser_mentions() {
        return user_mentions;
    }

    public void setUser_mentions(RealmList<RealmMentionEntity> user_mentions) {
        this.user_mentions = user_mentions;
    }

    public RealmList<RealmMediaEntity> getMedia() {
        return media;
    }

    public void setMedia(RealmList<RealmMediaEntity> media) {
        this.media = media;
    }

    public RealmList<RealmHashtagEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(RealmList<RealmHashtagEntity> hashtags) {
        this.hashtags = hashtags;
    }
}
