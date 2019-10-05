package br.ufpe.cin.android.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import org.jetbrains.anko.startActivity

// Custom Adapter para Item Feed
// Baseado no PessoaAdapter visto em sala
class ItemFeedAdapter (private val items: List<ItemFeed>, private val c: Context) : RecyclerView.Adapter<ItemFeedAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.viewTitle?.text = item.title
        holder.viewDate?.text = item.pubDate

        // Define as informaçoes para serem passadas para EpisodeDetailActivity
        holder.item_title = item.title
        holder.item_pubDate = item.pubDate
        holder.item_link = item.link
        holder.item_description = item.description
        holder.item_downloadLink = item.downloadLink
        holder.item_imageSrc = item.imageSrc
        // Adiciona funcionalidade de download ao botão do RecyclerView
        holder.viewButton.setOnClickListener {
            val i = Intent()
            i.action = ACTION_VIEW
            i.data = Uri.parse(item.downloadLink)
            it.context.startActivity(i)
        }
    }

    class ViewHolder (item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val viewTitle = item.item_title
        val viewDate = item.item_date
        val viewButton = item.item_action
        // Valores que serão passados para EpisodeDetailActivity
        var item_title : String? = null
        var item_pubDate : String? = null
        var item_link : String? = null
        var item_description : String? = null
        var item_downloadLink : String? = null
        var item_imageSrc : String ? = null

        init {
            item.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val context = p0?.context
            val i = Intent(context?.applicationContext, EpisodeDetailActivity::class.java)
            i.putExtra("item_title", item_title)
            i.putExtra("item_pubDate", item_pubDate)
            i.putExtra("item_link", item_link)
            i.putExtra("item_description", item_description)
            i.putExtra("item_downloadLink", item_downloadLink)
            i.putExtra("item_imageSrc", item_imageSrc)
            context?.startActivity(i)
        }
    }
}