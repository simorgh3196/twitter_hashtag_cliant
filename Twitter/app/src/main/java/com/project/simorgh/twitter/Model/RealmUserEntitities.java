package com.project.simorgh.twitter.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmUserEntitities extends RealmObject {

    @PrimaryKey
    private long user_id;

    private RealmUser user;
    private RealmList<RealmUrlEntity> urls;
    private RealmList<RealmUrlEntity> description;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public RealmUser getUser() {
        return user;
    }

    public void setUser(RealmUser user) {
        this.user = user;
    }

    public RealmList<RealmUrlEntity> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<RealmUrlEntity> urls) {
        this.urls = urls;
    }

    public RealmList<RealmUrlEntity> getDescription() {
        return description;
    }

    public void setDescription(RealmList<RealmUrlEntity> description) {
        this.description = description;
    }
}
