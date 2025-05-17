package com.example.tvapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.tvapp.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the video URL from the intent.
        // If it's null, log an error and finish the activity.
        val videoUrl = intent.getStringExtra("VIDEO_URL")
        Log.d("PlayerActivity", "Received VIDEO_URL: $videoUrl")
        if (videoUrl == null) {
            Log.e("PlayerActivity", "VIDEO_URL is null. Finishing activity.")
            finish()
            return
        }
        initializePlayer(videoUrl)
    }

    private fun initializePlayer(videoUrl: String) {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.playerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
                exoPlayer.setMediaItem(mediaItem)

                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)

                exoPlayer.addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        super.onPlayerError(error)
                        Log.e("PlayerActivity", "ExoPlayer Error: ", error)
                        // Optionally: show an error message to the user or finish activity
                        // Toast.makeText(this@PlayerActivity, "Error playing video", Toast.LENGTH_LONG).show()
                        // finish()
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        // You can log state changes or update UI based on state
                        when (playbackState) {
                            Player.STATE_BUFFERING -> Log.d("PlayerActivity", "State: Buffering")
                            Player.STATE_ENDED -> Log.d("PlayerActivity", "State: Ended")
                            Player.STATE_IDLE -> Log.d("PlayerActivity", "State: Idle")
                            Player.STATE_READY -> Log.d("PlayerActivity", "State: Ready")
                            else -> Log.d("PlayerActivity", "State: Unknown")
                        }
                    }
                })
                exoPlayer.prepare()
            }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    // Activity lifecycle methods for ExoPlayer
    // See: https://developer.android.com/guide/topics/media/exoplayer/players#activity-lifecycle

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            val videoUrl = intent.getStringExtra("VIDEO_URL")
            if (videoUrl != null) {
                initializePlayer(videoUrl)
            } else {
                Log.e("PlayerActivity", "VIDEO_URL is null in onStart. Cannot initialize player.")
                finish() // Or handle this error appropriately
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            val videoUrl = intent.getStringExtra("VIDEO_URL")
            if (videoUrl != null) {
                initializePlayer(videoUrl)
            } else {
                Log.e("PlayerActivity", "VIDEO_URL is null in onResume. Cannot initialize player.")
                finish() // Or handle this error appropriately
            }
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                player?.let {
                    it.playWhenReady = !it.playWhenReady
                }
                true
            }
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                player?.let {
                    it.playWhenReady = !it.playWhenReady
                }
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                finish() // or super.onBackPressedDispatcher.onBackPressed() for more complex back navigation
                true
            }
            // Add more key handling if needed, e.g., for seeking
            // KeyEvent.KEYCODE_MEDIA_FAST_FORWARD -> { player?.seekForward(); true }
            // KeyEvent.KEYCODE_MEDIA_REWIND -> { player?.seekBack(); true }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}