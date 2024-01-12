package com.prometheus.egp_tpv.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prometheus.egp_tpv.R;
import com.prometheus.egp_tpv.model.Programa;

import java.util.List;
import java.util.Locale;

public class ProgramaAdapter extends RecyclerView.Adapter<ProgramaAdapter.ProgramaViewHolder> {

    private final List<Programa> programas;
    private final OnItemClickListener clickListener;

    public ProgramaAdapter(List<Programa> programas, OnItemClickListener clickListener) {
        this.programas = programas;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProgramaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_programa, parent, false);
        return new ProgramaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramaViewHolder holder, int position) {
        Programa programa = programas.get(position);
        holder.titleTextView.setText(programa.getProgram().getName());
        holder.descriptionTextView.setText(programa.getDescription());
        holder.startTimeTextView.setText(programa.getHumanStartTime());
        holder.durationTextView.setText(String.format(Locale.getDefault(), "%d min", programa.getDurationInMinutes()));

        Glide.with(holder.imageView.getContext())
                .load(programa.getCustomInfo().getGraficos().getImagemURL())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            clickListener.onItemClick(programa);
        });
    }

    @Override
    public int getItemCount() {
        return programas.size();
    }

    static class ProgramaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView startTimeTextView;
        TextView durationTextView;

        ProgramaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            startTimeTextView = itemView.findViewById(R.id.startTimeTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);
        }
    }
}

