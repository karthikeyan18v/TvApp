package com.example.tvapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.example.tvapp.adapter.VideoAdapter
import com.example.tvapp.model.Video
import com.example.tvapp.ui.PlayerActivity
import com.example.tvapp.ui.theme.TvAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoGrid = findViewById<RecyclerView>(R.id.video_grid)
        videoGrid.adapter = VideoAdapter(getSampleVideos()) { video ->
            val intent = Intent(this, PlayerActivity::class.java).apply {
                putExtra("VIDEO_URL", video.videoUrl)
            }
            startActivity(intent)
        }

}

    private fun getSampleVideos(): List<Video> = listOf(
        Video(
            "Big Buck Bunny",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://peach.blender.org/wp-content/uploads/bbb-splash.png"
        ),

        Video(
            "Sintel",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg"
        ),

        Video(
            "For Bigger Joyrides",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg"
        ),
        Video(
            "For Bigger Blazes",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg"
        ),


        Video(
            "Subaru Outback On Street And Dirt",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg"
        )
    )
}