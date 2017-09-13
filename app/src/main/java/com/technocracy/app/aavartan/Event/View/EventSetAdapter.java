package com.technocracy.app.aavartan.Event.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.*;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Sponsors.Model.Data.Sponsor;
import com.technocracy.app.aavartan.helper.App;

import java.util.ArrayList;
import java.util.List;

public class EventSetAdapter extends RecyclerView.Adapter<EventSetAdapter.EventTypeHolder> {


    private final Context mContext;
    private int img[]={R.drawable.fun,R.drawable.man,R.drawable.tec,R.drawable.rob};
    private String events[]={"Fun","Managerial","Technical","Robotics"};
    private Intent intent;

    public static class EventTypeHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ProgressBar pBAr;
        public TextView name;
        public EventTypeHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.event_type);
            imageView = (ImageView) view.findViewById(R.id.event_set_img);


            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(400); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            name.startAnimation(anim); }


    }

    public EventSetAdapter(Context context) {
        mContext = context;
    }

    public EventTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.event_set_item, parent, false);
        return new EventTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventTypeHolder holder, final int position) {
        //App.showProgressBar(holder.pBAr);
        int width = (int) App.getScreenWidth(mContext);
        holder.imageView.getLayoutParams().height = width / 2;
        holder.imageView.getLayoutParams().width = width;
        holder.imageView.requestLayout();
        Picasso.with(mContext).load("www.abhi.co.in").placeholder(img[position]).
                into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        //App.hideProgressBar(holder.pBAr);
                    }

                    @Override
                    public void onError() {
                        //App.hideProgressBar(holder.pBAr);
                    }
                });
        holder.name.setText(events[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0) {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "fun");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
                else if(position==1)
                {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "manager");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
                else if(position==2)
                {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "tech");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
                else if(position==3)
                {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "robo");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return img.length;
    }
}