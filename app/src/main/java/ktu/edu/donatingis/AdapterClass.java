package ktu.edu.donatingis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    public List<Member> list;

    public AdapterClass(List<Member> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.bloodtype.setText(list.get(position).getBloodType());
        holder.position = position;
        holder.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, address, bloodtype;
        Integer position =-1;
        View rootView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rootView = itemView;
            name = itemView.findViewById(R.id.cardname);
            address = itemView.findViewById(R.id.cardaddress);
            bloodtype = itemView.findViewById(R.id.cardbloodtype);
            itemView.setTag(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.valueOf(v.getTag().toString());
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(v.getContext(), "Deleted sucessful! "+v.getTag().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        public void setTag(int position){
            rootView.setTag((int)position);
        }
    }
}
