package com.project.simorgh.twitter.Utils;

import com.twitter.sdk.android.core.models.Tweet;
import java.util.Comparator;

public class TweetComparator implements Comparator<Tweet> {

        // 比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
        public int compare(Tweet a, Tweet b) {
            long no1 = a.getId();
            long no2 = b.getId();

            // 降順でソートされる
            if (no1 < no2) {
                return 1;

            } else if (no1 == no2) {
                return 0;

            } else {
                return -1;

            }
        }

}
