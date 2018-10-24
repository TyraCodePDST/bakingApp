package com.example.vuthyra.bakingtime.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuthyra.bakingtime.R;
import com.example.vuthyra.bakingtime.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class Fragment_video extends Fragment {

    public static final String TAG = Fragment_video.class.getSimpleName();

    private PlayerView mPlayerView;
    private SimpleExoPlayer player;
//    private MediaSessionCompat mMediaSession;
//    private PlaybackStateCompat.Builder mStateBuilder;


    private TextView mTextDescription;
    private TextView mVideoTitle;

    private String videoURL;
    private String description;
    private int videoId;


    private String userAgent = "Recipe Step";
    private MediaSource videoSource;

    // Getting current  positiion of the data timing
    // and passing along with a condition of whether is ready
    // to play or not .

    private Long currentWindow;
    private boolean playWhenReady = true;

    //    public static final String savedState = "recorded";
    private Steps mStepData;


    public Fragment_video() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootViewStep = inflater.inflate(R.layout.container_video_instruction, container, false);
        mPlayerView = rootViewStep.findViewById(R.id.video_playerView);
        mTextDescription = rootViewStep.findViewById(R.id.video_description);
        mVideoTitle = rootViewStep.findViewById(R.id.video_title);



        if (savedInstanceState != null ) {

            mStepData = savedInstanceState.getParcelable("stepData");
//            videoURL = savedInstanceState.getString("videoUrl");
//            description = savedInstanceState.getString("videoDescription");
            currentWindow = savedInstanceState.getLong("currentWindow");
            playWhenReady = savedInstanceState.getBoolean("playWhenReady");
        }

        videoURL = mStepData.getVideoURL();
        description = mStepData.getShortDescription();
        videoId = mStepData.getId();

        Log.d(TAG, "This is the URL :" + description);


        initializePlayer();
        mTextDescription.setText(description);
        mVideoTitle.setText(String.valueOf(videoId));


        return rootViewStep;


    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//            initializePlayer();
//
//    }


//            @Override
//            public void onStop() {
//                super.onStop();
//                releasePlayer();
//            }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
//                player.prepare(videoSource);
    }


    //Create a main method to start our ExoPlayer which also includes a callback
    // from MediaSession


    private void initializePlayer() {


        if (player == null) {

            // 1. Create a default TrackSelector
            //            Handler mainHandler = new Handler();
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);

            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            //Initialize the Player View
            player.setPlayWhenReady(playWhenReady);
            mPlayerView.setPlayer(player);

            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory =
                    new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), userAgent));

            // This is the MediaSource representing the media to be played.
            final Uri videoUri = Uri.parse(videoURL);
            if (videoUri == null) {

                Toast.makeText(getContext(), "There is no video to display" + description,
                        Toast.LENGTH_SHORT).show();
                player.release();

            }

            videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoUri);
            player.prepare(videoSource);

            if (currentWindow != null) {
                player.seekTo(currentWindow);
            }
            if (player == null) {
                Log.d(TAG, "We got player that is null :" + description);


            }
        }


    }


    private void releasePlayer() {
        if (player == null) {

            Log.d(TAG, "We got player that is null :" + description);

        } else {
            currentWindow = player.getCurrentPosition();
            playWhenReady = player.getPlayWhenReady();
            player.stop();
            player.release();
            player = null;
        }
    }


    public void setVideoData(Steps step) {
        this.mStepData = step;

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(currentWindow != null){
            //save the position of the video player
            outState.putLong("currentWindow", currentWindow);
        }
//         Step object
        outState.putParcelable("stepData",mStepData );



//        //video url
//        outState.putString("videoURL", videoURL);
        //step description
//        outState.putString("videoDescription", description);
        //state of ready to play
        outState.putBoolean("playWhenReady",playWhenReady );

    }




}
