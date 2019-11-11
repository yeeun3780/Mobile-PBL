package com.example.shoppingmall;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {


    Context context;
    ArrayList<ProductFromDatabase> unFilteredlist;
    ArrayList<ProductFromDatabase> filteredList;


    public RecyclerViewAdapter(Context context, ArrayList<ProductFromDatabase> list) {
        super();
        this.context = context;
        this.unFilteredlist = list;
        this.filteredList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductFromDatabase item = filteredList.get(position) ;

        holder.icon.setImageDrawable(item.getIcon()) ;
        holder.desc.setText(item.getDesc()) ;
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon ;
        TextView title ;
        TextView desc ;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon) ;
            title = itemView.findViewById(R.id.title) ;
            desc = itemView.findViewById(R.id.desc) ;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filteredList = unFilteredlist;
                } else {
                    ArrayList<ProductFromDatabase> filteringList = new ArrayList<>();
                    for(ProductFromDatabase name : unFilteredlist) {
                        if(name.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name);
                        }
                    }
                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<ProductFromDatabase>)results.values;
                notifyDataSetChanged();
            }
        };
    }

}