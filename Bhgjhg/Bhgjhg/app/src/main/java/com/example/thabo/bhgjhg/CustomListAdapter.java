package com.example.thabo.bhgjhg;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Thabo on 2016/05/03.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList<NewsItem> listData;
    private LayoutInflater layoutInflater;
    MediaPlayer mplayer;
    NewsItem newsItem;
    public CustomListAdapter(ArrayList listData, Context context) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
             newsItem = (NewsItem) listData.get(position);
                    holder.headingView = (TextView) convertView.findViewById(R.id.title);
                    holder.descriptionView = (TextView) convertView.findViewById(R.id.reporter);
                    holder.reportedDateView = (TextView) convertView.findViewById(R.id.date);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
                    holder.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //  Toast.makeText(v.getContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();

                   // new AlertDialog.Builder(v.getContext()).setMessage("Track 1 is the first track on the list");
                    final AlertDialog.Builder play = new AlertDialog.Builder(v.getContext()).setPositiveButton("Play", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //play sound here
                            Toast.makeText(holder.imageView.getContext(),"Playing Sound", Toast.LENGTH_SHORT).show();
                       MediaPlayer     mplayer1=MediaPlayer.create(holder.imageView.getContext(),R.raw.sleep);
                            if(mplayer1.isPlaying()){
                                mplayer1.stop();

                                mplayer1.reset();
                                mplayer1.start();

                            }else{
                                mplayer1.start();
                            }

                        }
                    });
                    play.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    play.setTitle(listData.get(position).getHeading());
                            play.setMessage(listData.get(position).getDescription());
                            play.show();


                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.headingView.setText(newsItem.getHeading());
        holder.descriptionView.setText(newsItem.getDescription());//description
       // holder.reportedDateView.setText(newsItem.getDate());//date
        if (holder.imageView != null) {

            new ImageDownloaderTask(holder.imageView).execute(newsItem.getUrl());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView headingView;
        TextView descriptionView;
        TextView reportedDateView;
        ImageView imageView;
    }
}
