package com.android.floodfill.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.floodfill.MyView
import com.android.floodfill.R
import com.chootdev.recycleclick.RecycleClick
import io.navendra.nestedrecycler.models.ParentModel
import io.navendra.nestedrecycler.views.adapters.ChildAdapter
import kotlinx.android.synthetic.main.parent_recycler.view.*


class ParentAdapter(private val parents: List<ParentModel>, var myView: MyView) :
    RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = parents[position]

        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
            (layoutManager as LinearLayoutManager).initialPrefetchItemCount = 4
            adapter = ChildAdapter(parent.children)
        }
        holder.recyclerView.setRecycledViewPool(RecyclerView.RecycledViewPool())

        RecycleClick.addTo(holder.recyclerView)
            .setOnItemClickListener { _, item, _ ->
               myView.paint.color=parent.children[item].childColor
            }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.rvChild as RecyclerView
    }
}