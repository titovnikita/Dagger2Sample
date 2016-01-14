package com.nick.dagger2sample.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.database.tables.PostsTable;

public class PostsAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public PostsAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View convertView = inflater.inflate(R.layout.item_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        convertView.setTag(viewHolder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTitle.setText(cursor.getString(cursor.getColumnIndex(PostsTable.TITLE)));
        viewHolder.tvBody.setText(cursor.getString(cursor.getColumnIndex(PostsTable.BODY)));
    }

    public static class ViewHolder {
        TextView tvTitle;
        TextView tvBody;
    }
}
