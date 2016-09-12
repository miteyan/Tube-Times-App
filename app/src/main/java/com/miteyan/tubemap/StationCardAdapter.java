//package com.miteyan.tubemap;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * Created by miteyan on 11/09/2016.
// *///extend abstract adapter class
//
//    //data about the card for stations
//public class StationCardAdapter extends RecyclerView.Adapter<StationCardAdapter.MyViewHolder>{
//    private  LayoutInflater inflater;
//    private List<stationCardInfo> data = Collections.emptyList();
//
//    public StationCardAdapter(Context context, List<stationCardInfo> data){
//        inflater = LayoutInflater.from(context);
//        this.data=data;
//    }
//    //
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.station_card,parent,false);
//        //get xml card file then
//        //inflate cards with data about the station
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        stationCardInfo currCard = data.get(position);
//        holder.stationName.setText(currCard.getStationName());
//        holder.stationLines.setText(currCard.getTubeLines());
//        holder.stationDistance.setText(currCard.getDistance());
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView stationName;
//        TextView stationLines;
//        TextView stationDistance;
//        public MyViewHolder(View itemView) {//
//            super(itemView);
//            stationName= (TextView) itemView.findViewById(R.id.stationName);
//            stationLines= (TextView) itemView.findViewById(R.id.stationLines);
//            stationDistance= (TextView) itemView.findViewById(R.id.stationDistance);
//        }
//    }
//}
