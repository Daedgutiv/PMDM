package com.example.todo.core;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Tasks> items;
    private Activity activity;

    public ListAdapter(Activity activity, ArrayList<Tasks> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi= convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.tareas, null);
        }

        Tasks item = items.get(position);

        TextView tv1 = (TextView) vi.findViewById(R.id.izquierda);
        tv1.setText(item.getTask());

        TextView tv2 = (TextView) vi.findViewById(R.id.derecha);
        String aux = item.getDia()+ "/" + item.getMes()+ "/"+ item.getAnho();
        tv2.setText(aux);

        return vi;
    }
}
