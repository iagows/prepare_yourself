package com.iaspp.prepareyourself.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemListener implements RecyclerView.OnItemTouchListener {

    public interface RecyclerTouchListener {
        public void onClickItem(View v, int position);

        public void onLongClickItem(View v, int position);
    }

    private RecyclerTouchListener listener;
    private GestureDetector detector;

    public RecyclerItemListener(Context context, final RecyclerView recyclerView, final RecyclerTouchListener listener) {
        this.listener = listener;
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent event) {
                View view = recyclerView.findChildViewUnder(event.getX(), event.getY());
                listener.onLongClickItem(view, recyclerView.getChildAdapterPosition(view));
            }

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                View v = recyclerView.findChildViewUnder(event.getX(), event.getY());
                listener.onClickItem(v, recyclerView.getChildAdapterPosition(v));
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        return (child != null && detector.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


}
