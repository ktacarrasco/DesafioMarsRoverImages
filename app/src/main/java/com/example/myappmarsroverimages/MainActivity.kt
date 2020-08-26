package com.example.myappmarsroverimages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val urls = listOf<String>("https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg",
            "https://i.pinimg.com/originals/f3/4f/c8/f34fc80a4497d7bf8a57a6fdbcdd9916.png",
            "https://photoartinc.com/wp-content/uploads/2018/03/photos-jpg-4.jpg",
            "https://nineplanets.org/wp-content/uploads/2020/03/OSIRIS_Mars_true_color.jpg")


        btn.setOnClickListener {

            Toast.makeText(this, "Alerta de notificacion", Toast.LENGTH_SHORT).show()



        }

        CoroutineScope(Dispatchers.Main).launch {
           for (i in urls.indices){

            val image = downloadImagesNasaRover(urls[i])
            Log.d("COROUTINE", image.toString())
            if (image != null) {
                downloadImages(image)

            }
           }
        }

    }


    suspend fun  downloadImagesNasaRover(url: String): Bitmap? {
        var bmp: Bitmap? = null
        withContext(Dispatchers.Default) {
            try {
                progressBar.visibility = View.VISIBLE
                val newURL = URL(url)
                val inputStream = newURL.openConnection().getInputStream()
                Log.e("INPUT", inputStream.toString())
                bmp = BitmapFactory.decodeStream(newURL.openConnection().getInputStream())

            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
                e.printStackTrace()
            }
        }
        return bmp
    }

    suspend fun downloadImages(result: Bitmap) {
        progressBar.visibility = View.VISIBLE
        delay(1000)
        imgOfDay.setImageBitmap(result)
        progressBar.visibility = View.GONE

        progressBar2.visibility = View.VISIBLE
        delay(2000)
        imgOfDay2.setImageBitmap(result)
        progressBar2.visibility = View.GONE

        progressBar3.visibility = View.VISIBLE
        delay(3000)
        imgOfDay3.setImageBitmap(result)
        progressBar3.visibility = View.GONE

        progressBar4.visibility = View.VISIBLE
        delay(4000)
        imgOfDay4.setImageBitmap(result)
        progressBar4.visibility = View.GONE


    }
    }
