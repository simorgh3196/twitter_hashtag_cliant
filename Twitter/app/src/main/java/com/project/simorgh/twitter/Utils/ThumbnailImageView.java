package com.project.simorgh.twitter.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class ThumbnailImageView extends ImageView {

    private String imageUrl;

    public ThumbnailImageView(Context context) {
        super(context);
    }

    public ThumbnailImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThumbnailImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ThumbnailImageView(Context context, String url) {
        super(context);
        imageUrl = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
