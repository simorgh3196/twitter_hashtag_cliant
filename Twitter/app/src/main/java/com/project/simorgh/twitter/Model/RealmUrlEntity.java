package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by TomoyaHayakawa on 16/02/20.
 */
public class RealmUrlEntity extends RealmObject {

    @PrimaryKey
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
