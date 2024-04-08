package ru.btpit.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.btpit.nmedia.repository.PostRepository
import ru.btpit.nmedia.repository.PostRepositoryInMemoryImpl
import ru.btpit.nmedia.dto.Post
private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    sharedByMe = false,
    likes = 0,
    share = 0
)
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post:Post){
        edited.value = post
    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
    fun likeById(id: Int)=repository.likedById(id)
    fun shareById(id: Int)=repository.sharedById(id)
    fun removeById(id: Int) = repository.removedById(id)
}