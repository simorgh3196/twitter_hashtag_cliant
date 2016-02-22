package com.project.simorgh.twitter.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by TomoyaHayakawa on 16/02/20.
 */
public class RealmHashtagEntity extends RealmObject {

    @PrimaryKey
    private long status_id;

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }
}
