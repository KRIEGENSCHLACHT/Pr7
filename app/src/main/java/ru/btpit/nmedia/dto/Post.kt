package ru.btpit.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    var share: Int,
    val likedByMe: Boolean,
    val sharedByMe: Boolean
)
