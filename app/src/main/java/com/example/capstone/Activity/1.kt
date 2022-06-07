//package com.example.capstone.adapter
//
//import android.app.Activity
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.cardview.widget.CardView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.capstone.Activity.Activity6Reader
//import com.example.capstone.Info.PostInfo
//import com.example.capstone.R
//import com.google.firebase.Timestamp
//import java.text.SimpleDateFormat
//import java.util.*
//
//class mainRecyclerAdapter(
//    private val activity: Activity,
//    private val mDataset: ArrayList<PostInfo>
//) :
//    RecyclerView.Adapter<mainRecyclerAdapter.MyViewHolder>() {
//    class MyViewHolder(var cardView: CardView) : RecyclerView.ViewHolder(cardView)
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val cardView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.main_items, parent, false) as CardView
//        val myViewHolder = MyViewHolder(cardView)
//        cardView.setOnClickListener {
//            val mPosition = myViewHolder.adapterPosition
//            val intent = Intent(activity, Activity6Reader::class.java)
//            intent.putExtra("title", mDataset[mPosition].title)
//            intent.putExtra("contents", mDataset[mPosition].contents)
//            intent.putExtra("likecnt", mDataset[mPosition].likecnt.toString())
//            intent.putExtra("postId", mDataset[mPosition].postId)
//            activity.startActivity(intent)
//        }
//        return myViewHolder
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
////        val cardView = holder.cardView
////        val m_title = TextView.findViewById<TextView>(R.id.m_title)
////        m_title.text = mDataset[position].title
////        val m_content = cardView.findViewById<TextView>(R.id.m_content)
////        m_content.text = mDataset[position].contents
////        val m_likecnt = cardView.findViewById<TextView>(R.id.m_likecnt)
////        m_likecnt.text = mDataset[position].likecnt.toString()
////        val m_commentcnt = cardView.findViewById<TextView>(R.id.m_commentcnt)
////        m_commentcnt.text = mDataset[position].commentcnt.toString()
////        val m_time = cardView.findViewById<TextView>(R.id.m_time)
////        val timestamp = mDataset[position].createdAt as Timestamp
////        val date = SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(timestamp.toDate().toString()))
////        m_time.text = date
//
//        //Log.e("로그: ","데이터: "+date);
//    }
//
//    override fun getItemCount(): Int {
//        return mDataset.size
//    }
//}