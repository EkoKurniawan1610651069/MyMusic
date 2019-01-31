package com.example.kurniawan.mymusic.library.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurniawan.mymusic.LibraryArtist;
import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.top_artist.ProfilActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryAdapter extends BaseAdapter {
    private ArrayList<LibraryArtist> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public LibraryAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<LibraryArtist> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final LibraryArtist item) {
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
            convertView = mInflater.inflate(R.layout.library_item, null);
            holder.imageViewArtist = (ImageView) convertView.findViewById(R.id.imageViewArtist);
            holder.textViewNamaArtist = (TextView) convertView.findViewById(R.id.textViewNamaArtist);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewNamaArtist.setText(mData.get(position).getTextViewNamaArtist());

        Picasso.get()
                .load(mData.get(position).getImageViewArtist())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewArtist);

        holder.imageViewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LibraryDetailActivity.class);
                intent.putExtra("url", mData.get(position).getUrlLibrary());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    private static class ViewHolder {
        TextView textViewNamaArtist;
        ImageView imageViewArtist;
    }

    public void updateList(ArrayList<LibraryArtist> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
