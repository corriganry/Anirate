package edu.lewisu.cs.corriganry.anirate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AnimeListAdapter extends FirebaseRecyclerAdapter<Anime, AnimeListAdapter.AnimeHolder> {
    private final AnimeListAdapterOnClickHandler clickHandler;

    public interface AnimeListAdapterOnClickHandler {
        void onClick(int position);
    }

    public AnimeListAdapter (FirebaseRecyclerOptions<Anime> options, AnimeListAdapterOnClickHandler clickHandler) {
        super(options);
        this.clickHandler = clickHandler;
    }

    @Override
    protected void onBindViewHolder(@NonNull AnimeHolder holder, int position, @NonNull Anime model) {
        holder.animeTextView.setText(model.getAnimeName());
        int rating = model.getRating();
        String ratingString = "Rating: " + String.valueOf(rating);
        holder.ratingTextView.setText(ratingString);
    }

    @NonNull
    @Override
    public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new AnimeHolder(view);
    }

    class AnimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView animeTextView;
        private final TextView ratingTextView;

        AnimeHolder(View itemView) {
            super(itemView);
            animeTextView = itemView.findViewById(R.id.anime_text_view);
            ratingTextView = itemView.findViewById(R.id.rating_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }
    }
}

