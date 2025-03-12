package ua.aniloom.presentation.common.utils.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <VH : RecyclerView.ViewHolder> RecyclerView.setupRecycler(
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
    adapter: RecyclerView.Adapter<VH>
) = this.apply {
    this.layoutManager = layoutManager
    this.adapter = adapter
}