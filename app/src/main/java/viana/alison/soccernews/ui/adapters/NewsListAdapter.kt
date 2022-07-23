package viana.alison.soccernews.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import viana.alison.soccernews.data.model.News
import com.squareup.picasso.Picasso
import viana.alison.soccernews.R
import viana.alison.soccernews.databinding.ItemNewsBinding

class NewsListAdapter : ListAdapter<News, NewsListAdapter.ViewHolder>(DiffCallBack()) {

    var favoriteButtonListener: (News) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemNewsBinding
        ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {

            binding.tvTitle.text = item.title
            binding.tvBody.text = item.description

            val color = if (item.favorite) R.color.red else R.color.black
            binding.ivFavorite.setColorFilter(
                ContextCompat.getColor(binding.ivFavorite.context, color))

            Picasso.get()
                .load(item.image)
                .fit()
                .into(binding.ivThumbnail)

            binding.btnOpenLink.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(item.link)
                    itemView.context.startActivity(this)
                }
            }
            binding.ivShare.setOnClickListener {
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TITLE, item.title)
                    putExtra(Intent.EXTRA_TEXT, item.link)
                    itemView.context.startActivity(Intent.createChooser(this, "Share"))
                }
            }
            binding.ivFavorite.setOnClickListener {
                item.favorite = !item.favorite
                favoriteButtonListener(item)
                notifyItemChanged(adapterPosition)
            }

        }
    }

}

class DiffCallBack : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem.id == newItem.id
}