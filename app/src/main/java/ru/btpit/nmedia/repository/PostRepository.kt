package ru.btpit.nmedia.repository
import androidx.lifecycle.LiveData
import ru.btpit.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likedById(id: Int)
    fun sharedById(id: Int)
    fun removedById(id: Int)
    fun save(post: Post)
}