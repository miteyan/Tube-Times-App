//package com.miteyan.tubemap;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//public class Train extends AppCompatActivity {
//
//    TextView stations;
//    Button getStations;
//
//
//    LatLng location = new LatLng(51.5333, 0.1322);
//    List<Station> listStations = Collections.EMPTY_LIST;
//
//    private void setList(List<Station> list){
//        this.listStations=list;
//        System.out.println("List set: " + list.size());
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_train);
//
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    NearestStations ns = new NearestStations(location);
//                    List<Station> stationsList = ns.getStations();
//                    setList(stationsList);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        getStations = (Button) findViewById(R.id.getStationsButton);
//        stations = (TextView) findViewById(R.id.stationsTextView);
//    }
//
//    public void getStations(View view) throws IOException {
//
//        Toast.makeText(this,listStations.get(0).getName(),Toast.LENGTH_SHORT).show();
//        String str = "";
//        for (int i =0 ; i<listStations.size(); i ++) {
//            str +=listStations.get(i).toString()+"\n";
//        }
//        stations.setText(str);
//    }
//
////    class Task extends AsyncTask<Context, Station,Void> {
////
////
////        @Override
////        protected Void doInBackground(Context...contexts ) {
////            Toast.makeText(Train.this,"Started",Toast.LENGTH_LONG).show();
////
////                stationsList.add(new Station("d"));
////
////            Toast.makeText(Train.this,"DONE1",Toast.LENGTH_LONG).show();
////            return null;
////        }
////
//////        @Override
//////        protected void onProgressUpdate(Station... values) {
//////            stations.setText(values[0].getName());
//////        }
////
////        @Override
////        protected void onPostExecute(Void  result) {
////            Toast.makeText(Train.this,"DONE",Toast.LENGTH_LONG).show();
//////            Toast.makeText(Train.this,result.get(0).getName(),Toast.LENGTH_LONG).show();
//////            stations.setText(result.get(0).getName());
////        }
////    }
//
//}
