package com.example.capstone.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.Info.Post
import com.example.capstone.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity12_search.*
import kotlinx.android.synthetic.main.item.view.*

class Activity12Search : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity12_search)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        recyclerview.adapter = RecyclerViewAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)

        // 검색 옵션 변수
        var searchOption = "title"

        // 스피너 옵션에 따른 동작
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (spinner.getItemAtPosition(position)) {
                    "제목" -> {
                        searchOption = "title"
                    }
                    "내용" -> {
                        searchOption = "contents"
                    }
                }
            }
        }

        // 검색 옵션에 따라 검색
        searchBtn.setOnClickListener {
            (recyclerview.adapter as RecyclerViewAdapter).search(searchWord.text.toString(), searchOption)
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        // Person 클래스 ArrayList 생성성
        var posts : ArrayList<Post> = arrayListOf()

        init {  // telephoneBook의 문서를 불러온 뒤 Person으로 변환해 ArrayList에 담음
            firestore?.collection("telephoneBook")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                posts.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(Post::class.java)
                    posts.add(item!!)
                }
                notifyDataSetChanged()
            }
        }

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            viewHolder.title.text = posts[position].title
            viewHolder.contents.text = posts[position].contents
        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return posts.size
        }

        // 파이어스토어에서 데이터를 불러와서 검색어가 있는지 판단
        fun search(searchWord : String, option : String) {
            firestore?.collection("posts")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                posts.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if (snapshot.getString(option)!!.contains(searchWord)) {
                        var item = snapshot.toObject(Post::class.java)
                        posts.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
        }
    }
}