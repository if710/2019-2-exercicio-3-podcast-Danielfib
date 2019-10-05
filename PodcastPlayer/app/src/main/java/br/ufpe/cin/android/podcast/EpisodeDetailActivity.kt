package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.android.synthetic.main.activity_episode_detail.*
import kotlinx.android.synthetic.main.itemlista.item_title
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class EpisodeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        item_title.text = intent.getStringExtra("item_title")
        item_description.text = intent.getStringExtra("item_description")
        item_link.text = intent.getStringExtra("item_link")
        item_link.setOnClickListener {
            val i = Intent()
            i.action = ACTION_VIEW
            i.data = Uri.parse(intent.getStringExtra("item_link"))
            startActivity(i)
        }
        download_button.setOnClickListener {
            val i = Intent()
            i.action = ACTION_VIEW
            i.data = Uri.parse(intent.getStringExtra("item_downloadLink"))
            startActivity(i)
        }
        item_pubDate.text = intent.getStringExtra("item_pubDate")
        // Carrega a imagem
        doAsync {
            val url = URL(intent.getStringExtra("item_imageSrc"))
            val mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            uiThread {
                item_image.setImageBitmap(mBitmap)
            }
        }
    }
}
