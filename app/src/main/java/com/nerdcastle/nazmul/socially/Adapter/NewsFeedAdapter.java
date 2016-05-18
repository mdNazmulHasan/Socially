package com.nerdcastle.nazmul.socially.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdcastle.nazmul.socially.Model.PostModel;
import com.nerdcastle.nazmul.socially.R;

import java.util.ArrayList;

/**
 * Created by po on 5/11/16.
 */
public class NewsFeedAdapter extends ArrayAdapter<PostModel> {
    ArrayList<PostModel> postModelArrayList;
    private Activity context;
    private String userName;


    static class ViewHolder {
        public TextView mStatus;
        public ImageView image;
        public ImageView logo;
    }
    public NewsFeedAdapter(Activity context, ArrayList<PostModel> postModelArrayList, String userName) {
        super(context, R.layout.newsfeed_row ,postModelArrayList);
        this.postModelArrayList=postModelArrayList;
        this.context = context;
        this.userName=userName;
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
            viewHolder.logo = (ImageView) rowView
                    .findViewById(R.id.logoIV);
            viewHolder.mStatus= (TextView) rowView
                    .findViewById(R.id.statusTV);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        int profileId=postModelArrayList.get(position).getProfileId();
        if(profileId==1){
            holder.logo.setImageResource(R.drawable.iid);
        }else if(profileId==2){
            holder.logo.setImageResource(R.drawable.child);
        }else if(profileId==3){
            holder.logo.setImageResource(R.drawable.jaago);
        }else if(profileId==4){
            holder.logo.setImageResource(R.drawable.brac);
        }else if(profileId==5){
            holder.logo.setImageResource(R.drawable.shakti);
        }

        holder.mStatus.setText( ""+postModelArrayList.get(position).getmStatus());
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 14;
        Bitmap bitmap= BitmapFactory.decodeFile(postModelArrayList.get(position).getmPhotopath(),options);
        holder.image.setImageBitmap(bitmap);
        return rowView;
    }

}
