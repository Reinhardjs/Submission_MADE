package com.example.submission_made.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.submission_made.data.remote.Resource;
import com.example.submission_made.ui.base.BaseAdapter;

import java.util.ArrayList;

final class ListBindingAdapter {

    private ListBindingAdapter() {
        // Private Constructor to hide the implicit one
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null)
            return;

        if (resource == null || resource.getData() == null)
            return;

        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).setData((ArrayList) resource.getData());
        }
    }

}