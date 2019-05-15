package zarama.fabian.downloadingimages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    class ImageDownloader : AsyncTask<String,Void, Bitmap>() {
        override fun doInBackground(vararg urls: String?): Bitmap? {

            try {
                val url = URL(urls.get(0))
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()
                val input = connection.inputStream
                val myBitMap = BitmapFactory.decodeStream(input)
                return myBitMap

            }catch (e:Exception){
                Log.i("error doInBackground", e.localizedMessage)
                return null
            }

        }
    }

    fun downloadImage(view : View){
        val task = ImageDownloader()
        val myImage : Bitmap
        try {
            myImage = task.execute("https://vignette.wikia.nocookie.net/vsbattles/images/6/62/Supershenron.png/revision/latest?cb=20160706134151").get()
            imgDownload.setImageBitmap(myImage)
        }catch (e:Exception){
            Log.i("error downloadImage", e.localizedMessage)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
