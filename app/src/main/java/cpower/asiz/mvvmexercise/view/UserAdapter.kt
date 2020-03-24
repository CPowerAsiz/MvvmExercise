package cpower.asiz.mvvmexercise.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cpower.asiz.mvvmexercise.R
import cpower.asiz.mvvmexercise.model.User

class UserAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.nameText.text = user.login
        holder.adminText.visibility = if (user.site_admin) View.VISIBLE else View.GONE
        Glide.with(holder.avatarImage.context).load(user.avatar_url).circleCrop()
            .into(holder.avatarImage)
    }

    fun update(data: List<User>) {
        users = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage: ImageView = itemView.findViewById(R.id.avatarImage)
        val nameText: TextView = itemView.findViewById(R.id.nameText)
        val adminText: TextView = itemView.findViewById(R.id.adminText)
    }
}