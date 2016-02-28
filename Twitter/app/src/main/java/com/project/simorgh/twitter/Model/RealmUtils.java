package com.project.simorgh.twitter.Model;

import android.content.Context;
import android.util.Log;

import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.MentionEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetEntities;
import com.twitter.sdk.android.core.models.UrlEntity;

import java.util.ArrayList;

import io.realm.Realm;


public class RealmUtils {

    public static void saveTweets(Context context, ArrayList<Tweet> tweets) {

        Realm realm = Realm.getInstance(context);
        saveTweets(realm, tweets);
    }

    public static void saveTweets(Realm realm, final ArrayList<Tweet> tweets) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                for (Tweet tweet : tweets) {
                    RealmTweet realmTweet = bgRealm.copyToRealmOrUpdate(makeRealmTweet(tweet));
                }
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                Log.i("Realm", "Save tweet success");
            }

            @Override
            public void onError(Exception e) {
                Log.i("Realm", "Save tweet failure");
                e.printStackTrace();
            }
        });
    }

    private static RealmTweet makeRealmTweet(Tweet tweet) {

        if (tweet == null || tweet.user == null) {
            return null;
        }

        RealmTweet rTweet = new RealmTweet();
        rTweet.setId(tweet.id);
        rTweet.setUser_id(tweet.user.id);
        rTweet.setScreen_name(tweet.user.screenName);
        rTweet.setName(tweet.user.name);
        rTweet.setProfile_image_url(tweet.user.profileImageUrl);
        rTweet.setText(tweet.text);
        rTweet.setCreated_at(tweet.createdAt);
        rTweet.setLang(tweet.lang);
        rTweet.setSource(tweet.source);
        rTweet.setFavorite_count(tweet.favoriteCount);
        rTweet.setRetweet_count(tweet.retweetCount);
        rTweet.setFavorited(tweet.favorited);
        rTweet.setRetweeted(tweet.retweeted);
        rTweet.setPossibly_sensitive(tweet.possiblySensitive);
        rTweet.setIn_reply_to_screen_name(tweet.inReplyToScreenName);
        rTweet.setIn_reply_to_status_id(tweet.inReplyToStatusId);
        rTweet.setIn_reply_to_user_id(tweet.inReplyToUserId);
        rTweet.setRetweeted_status(makeRealmTweet(tweet.retweetedStatus));
        rTweet.setEntities(makeRealmEntities(rTweet, tweet.entities));
        rTweet.setExtended_etities(makeRealmEntities(rTweet, tweet.extendedEtities));

        return rTweet;
    }

    private static String makeEntityId(String param1, String param2, int start, int end) {
        return param1 + ":" + param2 + "[" + start + "-" + end + "]";
    }

    private static RealmTweetEntities makeRealmEntities(RealmTweet tweet, TweetEntities entities) {

        if (tweet == null || entities == null) {
            return null;
        }

        RealmTweetEntities rTweetEntities = new RealmTweetEntities();
        rTweetEntities.setStatus_id(tweet.getId());
        rTweetEntities.setTweet(tweet);

        if (entities.urls != null) {
            for (UrlEntity url : entities.urls) {
                RealmUrlEntity rUrlEntity = new RealmUrlEntity();

                String id = makeEntityId(""+tweet.getId(), url.url, url.getStart(), url.getEnd());
                rUrlEntity.setEntity_id(id);
                rUrlEntity.setTweet_entity(rTweetEntities);

                rUrlEntity.setUrl(url.url);
                rUrlEntity.setExpanded_url(url.expandedUrl);
                rUrlEntity.setDisplay_url(url.displayUrl);
                rUrlEntity.setStart_index(url.getStart());
                rUrlEntity.setEnd_index(url.getEnd());

                rTweetEntities.getUrls().add(rUrlEntity);
            }
        }

        if (entities.userMentions != null) {
            for (MentionEntity mention : entities.userMentions) {
                RealmMentionEntity rMentionEntity = new RealmMentionEntity();

                String id = makeEntityId(""+tweet.getId(),""+mention.id,mention.getStart(),mention.getEnd());
                rMentionEntity.setEntity_id(id);
                rMentionEntity.setTweet_entity(rTweetEntities);

                rMentionEntity.setTarget_status_id(mention.id);
                rMentionEntity.setName(mention.name);
                rMentionEntity.setScreenName(mention.screenName);
                rMentionEntity.setStart_index(mention.getStart());
                rMentionEntity.setEnd_index(mention.getEnd());

                rTweetEntities.getUser_mentions().add(rMentionEntity);
            }
        }

        if (entities.media != null) {
            for (MediaEntity media : entities.media) {
                RealmMediaEntity rMediaEntity = new RealmMediaEntity();

                String id = makeEntityId("" + tweet.getId(),media.idStr,media.getStart(),media.getEnd());
                rMediaEntity.setEntity_id(id);
                rMediaEntity.setTweet_entity(rTweetEntities);

                rMediaEntity.setMedia_id(media.id);
                rMediaEntity.setMedia_url(media.mediaUrl);
                rMediaEntity.setMedia_url_https(media.mediaUrlHttps);
                rMediaEntity.setType(media.type);
                rMediaEntity.setStart_index(media.getStart());
                rMediaEntity.setEnd_index(media.getEnd());

                rTweetEntities.getMedia().add(rMediaEntity);
            }
        }

        if (entities.hashtags != null) {
            for (HashtagEntity hashtag : entities.hashtags) {
                RealmHashtagEntity rHashtagEntity = new RealmHashtagEntity();

                String id = makeEntityId("" + tweet.getId(),hashtag.text,hashtag.getStart(),hashtag.getEnd());
                rHashtagEntity.setEntity_id(id);
                rHashtagEntity.setTweet_entity(rTweetEntities);

                rHashtagEntity.setText(hashtag.text);
                rHashtagEntity.setStart_index(hashtag.getStart());
                rHashtagEntity.setEnd_index(hashtag.getEnd());

                rTweetEntities.getHashtags().add(rHashtagEntity);
            }
        }

        return rTweetEntities;
    }

}
