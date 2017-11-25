package com.sasmobile.galleryupdated;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sasmobile.R;
import com.sasmobile.SASBaseActivity;

/**
 * Created by ashwini.vemulapally on 9/10/2017.
 */

public class FullScreenVideo extends SASBaseActivity {
    private VideoView vv;
    private int videoPosition = 0;
    private MediaController mediaControls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.videoplay);
        vv = (VideoView) findViewById(R.id.news_videos);

        Intent i = getIntent();
        Gallery gallery = (Gallery) i.getSerializableExtra("VIDEO_URL");
        if (mediaControls == null) {
            mediaControls = new MediaController(FullScreenVideo.this);
        }
        vv.setMediaController(mediaControls);


        int rawId = getResources().getIdentifier("sample_video", "raw", getPackageName());

// URI formation
        String path = "android.resource://" + getPackageName() + "/" + rawId;

// Set the URI to play video file
        vv.setVideoURI(Uri.parse(path));


        // vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video));
        mediaControls.requestFocus();
        vv.requestFocus();


        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                // Toast.makeText(getApplicationContext(), "video listener" + Urr, Toast.LENGTH_SHORT).show();
                mediaControls.show(0);
                if (videoPosition == 0) {
                    vv.start();
                    vv.seekTo(500);
                    vv.pause();
                } else {
                    vv.pause();
                }


            }
        });

    }
}
