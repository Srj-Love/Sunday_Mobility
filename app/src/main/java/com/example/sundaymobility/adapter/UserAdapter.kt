package com.example.sundaymobility.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sundaymobility.R
import com.example.sundaymobility.model.UserModel
import com.example.sundaymobility.utils.loadImage

class UserAdapter
    (
    private val mList: MutableList<UserModel>,
    private var mCallback: AlbumCallback,
    private var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface AlbumCallback {
        fun onPhotoClick(position: Int)
        fun onDelete(position: Int)
    }


    inner class OriginalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ivProfile: ImageView = v.findViewById<View>(R.id.iv_profile) as ImageView
        var ivDelete: ImageView = v.findViewById<View>(R.id.iv_delete) as ImageView
        var tvName: TextView = v.findViewById<View>(R.id.tv_name) as TextView
        var tvType: TextView = v.findViewById<View>(R.id.tv_type) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.itemm_users_list, parent, false)
        vh = OriginalViewHolder(v)
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = mList[position]

        if (holder is OriginalViewHolder) {

            if (model.login != null) {
                holder.tvName.text = model.login
            } else holder.tvName.text = "-"

            if (model.type != null) {
                holder.tvType.text = model.type
            } else holder.tvType.text = "-"

            if (model.avatarUrl?.isNotEmpty()!!) {
                holder.ivProfile.loadImage(context, model.avatarUrl, 0, false)
                holder.ivProfile.setOnClickListener {
                    mCallback.onPhotoClick(holder.adapterPosition)
                }
            } else {
                holder.ivProfile.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.sunday_mob
                    )
                )
            }

            holder.ivDelete.setOnClickListener {
                mCallback.onDelete(holder.adapterPosition)
            }

        }


    }

    override fun getItemCount() = mList.size

    public fun deleteData(position:Int){
        if (position >= 0) {
            mList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position, mList.size)
        }
    }
}