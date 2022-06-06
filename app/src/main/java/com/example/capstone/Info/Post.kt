package com.example.capstone.Info

import com.google.firebase.Timestamp

data class Post(var title : String? = null,
                var contents : String? = null,
                var publisher : String? = null,
                var likecnt : Integer? = null,
                var postId : String? = null
)