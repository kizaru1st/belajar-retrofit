//package com.example.belajar_retrofit;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.belajar_retrofit.datamodels.LogbooksItem;
//
//import java.util.List;
//
//public class ListLogbookAdapter extends RecyclerView.Adapter<ListLogbookAdapter.ViewHolder> {
//
//    private List<LogbooksItem> itemList;
//
//    public void setLogbooks(List<LogbooksItem> logbooks) {
//        this.itemList = logbooks;
//    }
//
//    @NonNull
//    @Override
//    public ListLogbookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_story, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ListLogbookAdapter.ViewHolder holder, int position) {
//        LogbooksItem logbooksItem = itemList.get(position);
//        holder.tvKegiatan.setText(logbooksItem.getActivities());
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView tvKegiatan;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvKegiatan = itemView.findViewById(R.id.tvKegiatan);
//        }
//    }
//}
