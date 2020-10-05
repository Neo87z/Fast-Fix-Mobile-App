package com.example.madfinal.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.madfinal.Interfaces.ItemClickListner;
import com.example.madfinal.R;

public class TechnicianRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static TextView AssingedTo;
    public static TextView Title;
    public static TextView Description;
    public static Button UpdateButton;
    private ItemClickListner listner;


    public TechnicianRequestViewHolder(View itemView)
    {
        super(itemView);
        Title=itemView.findViewById(R.id.Title);
        Description=itemView.findViewById(R.id.Description);
        AssingedTo=itemView.findViewById(R.id.Assingedto);
        UpdateButton=itemView.findViewById(R.id.ViewButton);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}