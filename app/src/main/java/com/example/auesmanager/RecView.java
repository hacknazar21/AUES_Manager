package com.example.auesmanager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auesmanager.pojo.Rasp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RecView extends RecyclerView.Adapter<RecView.RaspViewHolder>{
    private List<Rasp> itemlist = new ArrayList<Rasp>();
    private OnRaspClick onRaspClickListener;

    public RecView(OnRaspClick onRaspClickListener) {
        this.onRaspClickListener = onRaspClickListener;
    }

    @Override
    public RaspViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RaspViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaspViewHolder holder, int position) {
        holder.bind(itemlist.get(position));
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class RaspViewHolder extends RecyclerView.ViewHolder {
        private TextView Dayl;
        private TextView Timel;
        private TextView SubGroup;
        private TextView Namel;
        private TextView Sensei;
        private TextView Rooml;

        public RaspViewHolder(View itemView) {
            super(itemView);
            Dayl = itemView.findViewById(R.id.dayl);
            Timel = itemView.findViewById(R.id.timel);
            SubGroup = itemView.findViewById(R.id.subgroup);
            Namel = itemView.findViewById(R.id.namel);
            Sensei = itemView.findViewById(R.id.sensei);
            Rooml = itemView.findViewById(R.id.rooml);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Rasp rasp) {
            Dayl.setText(rasp.getDayl());
            Timel.setText(rasp.getTimel());
            if (!rasp.getSubGroup().equals("")) {
                SubGroup.setTextScaleX(1);
                SubGroup.setText("Подгруппа: " + rasp.getSubGroup());
            }
            else SubGroup.setTextScaleX(0);
            Namel.setText(rasp.getNamel());
            Sensei.setText(rasp.getSensei());
            Rooml.setText(rasp.getRooml());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rasp rasp = itemlist.get(getLayoutPosition());
                    onRaspClickListener.onRaspClick(rasp);
                }
            });
        }
    }
    public void setItems(Collection<Rasp> items){
        itemlist.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnRaspClick{
        void onRaspClick(Rasp rasp);
    }
}
