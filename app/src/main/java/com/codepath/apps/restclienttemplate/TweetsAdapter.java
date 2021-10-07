package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    // pass in the context and the list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }


    // for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);

        return new ViewHolder(view);
    }

    // bind values based on the position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get data as position
        Tweet tweet = tweets.get(position);

        // Bind the tweet with the view holder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // clean all elements in the recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> newTweets){
        tweets.addAll(newTweets);
        notifyDataSetChanged();
    }

    // Define a viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvHandler;
        TextView tvCreateAt;
        TextView tvScreenName;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvHandler = itemView.findViewById(R.id.tvHandler);
            tvCreateAt = itemView.findViewById(R.id.tvCreatedAt);

        }

        public void bind(Tweet tweet) {

            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.name);
            tvHandler.setText(String.format("@%s",tweet.user.screenName));
            tvCreateAt.setText(tweet.createdAt);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);

        }
    }
}
