package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository{
    private var nextId = 1
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Купили дом, завезли красивую мебель, приобрели навороченную плазму - а звука нет? Тогда вам стоит посетить КД-Саунд в Борисоглебске! Мы делаем Hi-Fi оборудование на заказ на территории Борисоглебска, возможна доставка по области",
            published = "25 марта в 13:01",
            likedByMe = false,
            likes = 999999,
            share = 999,
            sharedByMe = false
        ),
        Post(
            id = nextId++,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Дамы и господа, колонки ЗАКОНЧИЛИСЬ. Продажи закрыты.",
            published = "01 марта в 3:01",
            likedByMe = false,
            likes = 999999,
            share = 999,
            sharedByMe = false
        ),
    ).reversed()
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likedById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(likedByMe = !it.likedByMe, likes = if (!it.likedByMe) it.likes+1 else it.likes-1)
        }
        data.value = posts
    }
    override fun sharedById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(sharedByMe = !it.sharedByMe, share = it.share+1)
        }
        data.value = posts
    }

    override fun removedById(id: Int) {
        posts = posts.filter { it.id !=id }
        data.value=posts
    }

    override fun save(post: Post) {
        if(post.id==0){
            posts = listOf(post.copy(
                id = nextId++,
                author = "Me",
                likedByMe = false,
                published = "now",
                sharedByMe = false
            )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map{
            if (it.id != post.id) it else it.copy (content = post.content, likes = post.likes, share = post.share)
        }
        data.value = posts
    }
}




