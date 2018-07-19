package com.insuranceclaim.insuranceclaim.Cards;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.insuranceclaim.insuranceclaim.R;

/**
 * Created by kyrel_000 on 2018-07-17.
 * Originally created in InsuranceClaim2.
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<InsurableCard> mCards = new ArrayList<>();

    private Context mContext;

    public CardRecyclerViewAdapter(ArrayList<InsurableCard> mCards, Context mContext) {
        this.mCards = mCards;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurancecard,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
                Log.d(TAG, "onBindViewHolderL: called.");
                holder.cardHeader.setText(mCards.get(position).getHeader());
                holder.cardDescription.setText(mCards.get(position).getImportant());
                holder.parentCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Log.d(TAG, "OnClick: clicked on: " + mCards.get(position).getHeader());
                    Toast.makeText(mContext,mCards.get(position).getImportant(), Toast.LENGTH_SHORT);
                    //TODO: Make this depend on long and short press, remove toast.
                }
        });
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView cardHeader;
        TextView cardDescription;
        CardView parentCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentCard = itemView.findViewById(R.id.insurableCard);
            cardDescription =   itemView.findViewById(R.id.cardDescTextView);
            image = itemView.findViewById(R.id.cardImageView);
            cardHeader = itemView.findViewById(R.id.cardDescTextView);

        }
    }
}
