package com.example.pbl2_shoppingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<SamsungItem> mData = null ;
    private Context context;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerImageTextAdapter(ArrayList<SamsungItem> list) {
        mData = list ;
    }

    public RecyclerImageTextAdapter(Context mContext, ArrayList<SamsungItem> list) {
        context = mContext;
        mData = list ;

    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /*
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_list, parent, false) ;
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view) ;

        return vh ;
        */
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
// set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        final SamsungItem item = mData.get(position) ;

        holder.icon.setImageDrawable(item.getIcon()) ;
        holder.title.setText(item.getTitle()) ;
        holder.desc.setText(item.getDesc()) ;

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon ;
        TextView title ;
        TextView desc ;
        private SamsungItem data;
        public LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            icon = itemView.findViewById(R.id.icon) ;
            title = itemView.findViewById(R.id.title) ;
            desc = itemView.findViewById(R.id.desc) ;
            linearLayout = (LinearLayout)itemView.findViewById(R.id.itemView);
        }


        }
    }
