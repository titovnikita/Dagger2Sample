package com.nick.dagger2sample.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.database.models.Post;

import java.util.List;

public class PostsAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Post> data;

    public PostsAdapter(Context context, List<Post> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_post, null, true);
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            holder.tvBody = (TextView) view.findViewById(R.id.tvBody);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Post post = getItem(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
        return view;
    }

    @Override
    public Post getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void swapData(List<Post> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView tvTitle;
        TextView tvBody;
    }
}
