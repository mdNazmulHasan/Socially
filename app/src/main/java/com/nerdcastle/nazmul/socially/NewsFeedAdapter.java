package com.nerdcastle.nazmul.socially;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by po on 5/11/16.
 */
public class NewsFeedAdapter extends ArrayAdapter< PostModel> {
    ArrayList<PostModel> postModelArrayList;
    private Activity context;


    static class ViewHolder {
        public TextView mStatus;
        public ImageView image;
    }
    public NewsFeedAdapter(Activity context,  ArrayList<PostModel> postModelArrayList) {
        super(context,R.layout.newsfeed_row ,postModelArrayList);
        this.postModelArrayList=postModelArrayList;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.newsfeed_row, parent,
                    false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.ivImage);
            viewHolder.mStatus= (TextView) rowView
                    .findViewById(R.id.statusTV);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.mStatus.setText( ""+postModelArrayList.get(position).getmStatus());
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        Bitmap bitmap= BitmapFactory.decodeFile(postModelArrayList.get(position).getmPhotopath(),options);
        holder.image.setImageBitmap(bitmap);
        return rowView;
    }

}
