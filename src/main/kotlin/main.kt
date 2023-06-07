import javax.swing.text.Document

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int?,
    val date: Int,
    val text: String,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean = false,
    val comments: Comments,
    val copyright: String?,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String = "post",
    val postSource : PostSource?,
    val attachments : Array<Attachment>?,
    val geo: Geo?,
    val signerId: Int?,
    val copyHistory: Array<Post>?,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int?
)

sealed class Attachment(val type : String)

class PhotoAttachment(val video : Photo) : Attachment("photo")
class VideoAttachment(val video : Video) : Attachment("video")
class AudioAttachment(val video : Audio) : Attachment("audio")
class DocumentAttachment(val video : Doc) : Attachment("doc")
class LinkAttachment(val video : Link) : Attachment("link")

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int = 100,
    val text: String?,
    val date: Int,
    val sizes: Array<Size>,
    val width: Int,
    val height: Int
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String?,
    val duration: Int,
    val firstFrame320: String,
    val date: Int,
    val views: Int = 0,
    val comments: Int = 0
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int?,
    val albumId: Int?,
    val genreId: Int?,
    val date: Int,
    val noSearch: Boolean = false,
    val isHQ: Boolean
)
data class Doc(val id : Int, val ownerId: Int, val title: String, val size: Int, val ext: String, val url: String, val date: Int, val type: Int = 1)
data class Link(val url: String, val title: String, val caption : String?, val description: String, val photo: Photo?)

data class Size(val type: String, val url: String, val width: Int, val height: Int)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class Geo(
    val type: String = " ",
    val coordinates: String = " ",
    val place: String = " "
)

data class PostSource(val type: String = "vk", val platform : String?, val data : String?, val url : String?)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }

    fun add(post: Post): Post {
        if (posts.isNotEmpty()) {
        nextId = posts.last().id + 1 }

        var postToId = post.copy(id = nextId)
        posts += postToId
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var hasChanged = false
        for ((index, post) in posts.withIndex()) {
            if (post.id == posts[index].id) {
                posts[index] = post.copy()
                hasChanged = true
            }
        }
        return hasChanged
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes.copy(count = post.likes.count + 1))
            }
        }
    }
}

fun main() {

}