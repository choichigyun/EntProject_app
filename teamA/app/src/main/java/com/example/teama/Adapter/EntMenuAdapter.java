package com.example.teama.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teama.DTO.EntMenuDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.ent.EntMenuListActivity.selItem;

public class EntMenuAdapter extends RecyclerView.Adapter<EntMenuAdapter.ItemViewHolder> {

    Context mContext;
    ArrayList<EntMenuDTO> arrayList;

    public EntMenuAdapter(Context mContext, ArrayList<EntMenuDTO> arrayList){
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.entmenulistview, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {

        EntMenuDTO emdto = arrayList.get(position);
        holder.setItem(emdto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selItem = arrayList.get(position);

                Toast.makeText(mContext, "Onclick " + arrayList.get(position).getMenu_name(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() { return arrayList.size(); }


    public void removeAllItem(){ arrayList.clear(); }

    public EntMenuDTO getItem(int position){ return arrayList.get(position); }

    public void setItem(int position, EntMenuDTO emdto){ arrayList.set(position, emdto); }

    public void setItems(ArrayList<EntMenuDTO> arrayList){ this.arrayList = arrayList; }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout parentLayout;
        public ImageView menu_img;
        public TextView menu_name;
        public EditText menu_price;
        public ImageButton menuUpdate, menuDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            menu_img = itemView.findViewById(R.id.menu_img);
            menu_name = itemView.findViewById(R.id.menu_UName);
            menu_price = itemView.findViewById(R.id.menu_UPrice);
            menuUpdate = itemView.findViewById(R.id.menuUpdate);
            menuDelete = itemView.findViewById(R.id.menuDelete);
        }

        public void setItem(EntMenuDTO emdto) {

            menu_name.setText(emdto.getMenu_name());
            menu_price.setText(emdto.getMenu_price());

            Glide.with(itemView).load(emdto.getFdpicture_path())
                    .into(menu_img);

        }
    }
}
