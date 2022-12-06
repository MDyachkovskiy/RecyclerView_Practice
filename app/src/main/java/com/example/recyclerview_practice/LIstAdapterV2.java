package com.example.recyclerview_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class LIstAdapterV2 extends RecyclerView.Adapter <LIstAdapterV2.ViewHolder> {

    CardSource dataSource;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public LIstAdapterV2(CardSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LIstAdapterV2.ViewHolder holder, int position) {

        holder.setData(dataSource.getCardData(position));
    }

    @NonNull
    @Override
    public LIstAdapterV2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_v2, viewGroup, false);

        return new LIstAdapterV2.ViewHolder(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, position);
                }
            });
        }

        public void setData (CardData cardData){
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
            like.setChecked(cardData.isLike());
            image.setImageResource(cardData.getPicture());
        }
    }

}
