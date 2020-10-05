package com.example.madfinal.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.madfinal.Interfaces.ItemClickListner;
import com.example.madfinal.R;

public class MyRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  TextView AssingedTo;
    public  TextView Title;
    public  TextView Description;
    public  Button UpdateButton,RemoveButton;
    private ItemClickListner listner;


    public MyRequestViewHolder(View itemView)
    {
        super(itemView);
        Title=itemView.findViewById(R.id.Title);
        Description=itemView.findViewById(R.id.Description);
        AssingedTo=itemView.findViewById(R.id.Assingedto);
        UpdateButton=itemView.findViewById(R.id.ViewButton);
        RemoveButton=itemView.findViewById(R.id.RemoveButton);
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