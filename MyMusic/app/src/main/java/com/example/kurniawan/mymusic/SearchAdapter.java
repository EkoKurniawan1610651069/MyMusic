package com.example.kurniawan.mymusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurniawan.mymusic.top_track.MusicAdapter;
import com.example.kurniawan.mymusic.top_track.MusicDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {

    private ArrayList<Search> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<Search> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final Search item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.search_item, null);
            holder.imageViewTrack = (ImageView) convertView.findViewById(R.id.imageViewTrack);
            holder.textViewTrack = (TextView) convertView.findViewById(R.id.textViewTrack);
            holder.textViewArtist = (TextView) convertView.findViewById(R.id.textViewArtist);
            holder.textViewListeners = (TextView) convertView.findViewById(R.id.textViewListeners);
            holder.buttonDetail = (Button) convertView.findViewById(R.id.buttonDetail);
            holder.buttonShare = (Button) convertView.findViewById(R.id.buttonShare);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewTrack.setText(mData.get(position).getStrJudulLagu());
        holder.textViewArtist.setText(mData.get(position).getStrArtist());
        holder.textViewListeners.setText(String.valueOf(mData.get(position).getListeners()));
        Picasso.get()
                .load(mData.get(position).getStrTrackThumb())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewTrack);
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MusicDetailActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                context.startActivity(intent);

            }
        });
        holder.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonShare) {
                    Intent pindah = new Intent(Intent.ACTION_SEND);
                    pindah.setType("text/plain");
                    pindah.putExtra(Intent.EXTRA_TEXT, mData.get(position).getUrl());
                    context.startActivity(Intent.createChooser(pindah, "Share Text"));
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        Button buttonDetail;
        Button buttonShare;
        TextView textViewTrack;
        TextView textViewArtist;
        TextView textViewListeners;
        ImageView imageViewTrack;
    }

    public void updateList(ArrayList<Search> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
