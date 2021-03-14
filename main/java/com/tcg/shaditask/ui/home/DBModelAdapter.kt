package com.tcg.shaditask.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tcg.shaditask.R
import com.tcg.shaditask.data.db.entities.DBModel

class DBModelAdapter(
    var context: Context,
    var list: List<DBModel>,
    var listner: ItemCliclListner
): RecyclerView.Adapter<DBModelAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textViewName: TextView
        lateinit var textViewAge: TextView
        lateinit var textViewRemark: TextView
        lateinit var buttonCancel: Button
        lateinit var buttonAccept: Button
        lateinit var imageView: ImageView

        init {
            textViewName = view.findViewById(R.id.textViewName)
            textViewAge= view.findViewById(R.id.textViewAge)
            textViewRemark= view.findViewById(R.id.textViewRemark)
            buttonCancel= view.findViewById(R.id.buttonCancel)
            buttonAccept= view.findViewById(R.id.buttonAccept)
            imageView= view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //TODO("Not yet implemented")
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //TODO("Not yet implemented")

        var dbModel:DBModel = list.get(position)

        holder.textViewName.setText(dbModel.firstName + " " + dbModel.lastName)
        holder.textViewAge.setText(dbModel.age)
        if(dbModel.isSurveyDone){
            holder.buttonAccept.visibility=View.GONE
            holder.buttonCancel.visibility=View.GONE
            holder.textViewRemark.visibility = View.VISIBLE
        }else{
            holder.textViewRemark.visibility = View.INVISIBLE
        }
        if(dbModel.isAccepted) {
            holder.textViewName.setText("You already accept this")
        }else{
            holder.textViewRemark.setText("You cancel this member")
        }

        Glide.with(context)
            .load(dbModel.largePicture)
            .crossFade()
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)

        holder.buttonAccept.setOnClickListener(){
            dbModel.isSurveyDone=true
            dbModel.isAccepted=true
            listner.onItemClick(dbModel)
        }
        holder.buttonCancel.setOnClickListener(){
            dbModel.isSurveyDone=true
            dbModel.isAccepted=false
            listner.onItemClick(dbModel)
        }
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return list.size
    }

    interface ItemCliclListner {
        fun onItemClick(dbModel: DBModel)
    }
}