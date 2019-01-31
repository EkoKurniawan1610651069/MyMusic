package com.example.kurniawan.mymusic.top_artist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurniawan.mymusic.Music;
import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopArtisAdapter extends BaseAdapter {
    private ArrayList<TopArtist> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public TopArtisAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<TopArtist> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final TopArtist item) {
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
            convertView = mInflater.inflate(R.layout.topartist_item, null);
            holder.imageViewArtistTop = (ImageView) convertView.findViewById(R.id.imageViewArtistTop);
            holder.textViewArtistTop = (TextView) convertView.findViewById(R.id.textViewArtistTop);
            holder.textViewPlaycountTopArtist = (TextView) convertView.findViewById(R.id.textViewPlaycountTopArtist);
            holder.textViewListenersTopArtist = (TextView) convertView.findViewById(R.id.textViewListenersTopArtist);
            holder.buttonProfil = (Button) convertView.findViewById(R.id.buttonProfil);
            holder.buttonShare = (Button) convertView.findViewById(R.id.buttonShare);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewArtistTop.setText(mData.get(position).getStrArtistTop());
        holder.textViewPlaycountTopArtist.setText(String.valueOf(mData.get(position).getPlayCount()));
        holder.textViewListenersTopArtist.setText(String.valueOf(mData.get(position).getListenersArtistTop()));
        Picasso.get()
                .load(mData.get(position).getStrArtistThumb())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewArtistTop);
        holder.buttonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProfilActivity.class);
                intent.putExtra("url", mData.get(position).getUrlProfil());
                context.startActivity(intent);

            }
        });
        holder.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonShare) {
                    Intent pindah = new Intent(Intent.ACTION_SEND);
                    pindah.setType("text/plain");
                    pindah.putExtra(Intent.EXTRA_TEXT, mData.get(position).getUrlProfil());
                    context.startActivity(Intent.createChooser(pindah, "Share Text"));
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        Button buttonProfil;
        Button buttonShare;
        TextView textViewArtistTop;
        TextView textViewPlaycountTopArtist;
        TextView textViewListenersTopArtist;
        ImageView imageViewArtistTop;
    }

    public void updateList(ArrayList<TopArtist> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
