package com.project.simorgh.twitter.Model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class RealmTweetEntities extends RealmObject {

    private RealmList<RealmUrlEntity> urls;
    private RealmList<RealmMentionEntity> user_mentions;
    private RealmList<RealmMediaEntity> media;
    private RealmList<RealmHashtagEntity> hashtags;

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
