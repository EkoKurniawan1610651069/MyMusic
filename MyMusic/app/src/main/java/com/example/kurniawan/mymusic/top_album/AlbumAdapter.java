package com.example.kurniawan.mymusic.top_album;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;
import com.example.kurniawan.mymusic.top_artist.ProfilActivity;
import com.example.kurniawan.mymusic.top_artist.TopArtisAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {
    private ArrayList<Album> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public AlbumAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<Album> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final Album item) {
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
            convertView = mInflater.inflate(R.layout.album_item, null);
            holder.imageViewAlbum = (ImageView) convertView.findViewById(R.id.imageViewAlbum);
            holder.textViewJudulAlbum = (TextView) convertView.findViewById(R.id.textViewJudulAlbum);
            holder.textViewArtistAlbum = (TextView) convertView.findViewById(R.id.textViewArtistAlbum);
            holder.textViewPlayCountAlbum = (TextView) convertView.findViewById(R.id.textViewPlayCountAlbum);
            holder.buttonDetail = (Button) convertView.findViewById(R.id.buttonDetail);
            holder.buttonShare = (Button) convertView.findViewById(R.id.buttonShare);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewJudulAlbum.setText(mData.get(position).getJudulAlbum());
        holder.textViewArtistAlbum.setText(mData.get(position).getArtistAlbum());
        holder.textViewPlayCountAlbum.setText(String.valueOf(mData.get(position).getPlayCount()));
        Picasso.get()
                .load(mData.get(position).getImageAlbum())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewAlbum);
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailAlbum.class);
                intent.putExtra("url", mData.get(position).getUrlAlbum());
                context.startActivity(intent);

            }
        });
        holder.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonShare) {
                    Intent pindah = new Intent(Intent.ACTION_SEND);
                    pindah.setType("text/plain");
                    pindah.putExtra(Intent.EXTRA_TEXT, mData.get(position).getUrlAlbum());
                    context.startActivity(Intent.createChooser(pindah, "Share Text"));
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        Button buttonDetail;
        Button buttonShare;
        TextView textViewJudulAlbum;
        TextView textViewArtistAlbum;
        TextView textViewPlayCountAlbum;
        ImageView imageViewAlbum;
    }

    public void updateList(ArrayList<Album> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
