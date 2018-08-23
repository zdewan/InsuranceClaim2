package com.insuranceclaim.insuranceclaim.ViewObjects;
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
import java.util.List;

import com.insuranceclaim.insuranceclaim.Cards.AutomobileInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.CustomInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.DentalInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.HealthInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.InsurableCard;
import com.insuranceclaim.insuranceclaim.R;
import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import com.insuranceclaim.insuranceclaim.insurables.InsurableDataField;
import com.insuranceclaim.insuranceclaim.insurables.Insurable.InsurableTypes;
/**
 * Created by kyrel_000 on 2018-07-17.
 * Originally created in InsuranceClaim2.
 */
public class CardListViewAdapter extends RecyclerView.Adapter<CardListViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private List<InsurableCard> mCards;
    private List<Insurable> mInsurables;
    public void setInsurables(List<Insurable> insurables){
        mInsurables = insurables;
        mCards = new ArrayList<>();
        for(int x = 0; x < mInsurables.size(); x ++){
            mCards.add(Insurable.generateCard(mInsurables.get(x)));
            mCards.get(x).loadInsurable();
        }
        notifyDataSetChanged();
    }

    private Context mContext;
    public CardListViewAdapter(ArrayList<InsurableCard> Cards, Context Context) {
        mCards = Cards;
        mContext = Context;
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
                Log.d(TAG, "onBindViewHolder called");
                Log.d(TAG,  "onBindViewHolder:  cardHeader Test set to:" +  mCards.get(position).getHeader());
                holder.cardHeader.setText(mCards.get(position).getHeader());
                holder.cardDescription.setText(mCards.get(position).getImportant());
                holder.parentCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Log.d(TAG, "OnClick: clicked on: " + mCards.get(position).getInsurable().getName());
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
