package com.example.soccernews.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccernews.R;
import com.example.soccernews.data.model.News;
import com.example.soccernews.databinding.NewsItemBinding;
import com.squareup.picasso.Picasso;

public class NewsListAdapter extends ListAdapter<News, NewsListAdapter.ViewHolder>{

    public NewsListAdapter() {
        super(new DiffCallBack());
    }

    public FavoriteInterface favoriteInterface;

    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final NewsItemBinding binding = NewsItemBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;


        public ViewHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(News item){
            int color;

            binding.tvTitle.setText(item.getTitle());
            binding.tvBody.setText(item.getDescription());

            color = item.getFavorite() ? R.color.red : R.color.black;
            binding.ivFavorite.setColorFilter(
                    ContextCompat.getColor(binding.ivFavorite.getContext(), color));

            Picasso.get()
                    .load(item.getImage())
                    .fit()
                    .into(binding.ivThumbnail);

            binding.btnOpenLink.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getLink()));
                itemView.getContext().startActivity(intent);
            });
            binding.ivShare.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE, item.getTitle());
                intent.putExtra(Intent.EXTRA_TEXT, item.getLink());
                itemView.getContext().startActivity(Intent.createChooser(intent, "Share"));
            });
            binding.ivFavorite.setOnClickListener(view -> {
                item.setFavorite(!item.getFavorite());
                favoriteInterface.favoriteListener(item);
                notifyItemChanged(getAdapterPosition());
            });

        }

    }
}

class DiffCallBack extends DiffUtil.ItemCallback<News>{

    @Override
    public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return false;
    }
}
