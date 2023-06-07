import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    @org.junit.Test
    fun clearBeforeTest() {
        WallService.clear()
    }

    @org.junit.Test
    fun add() {
        val post = Post(
            0,
            0,
            0,
            0,
            101023,
            "Привет!",
            0,
            0,
            comments = Comments(),
            copyright = "Copyright",
            likes = Likes(),
            geo = Geo(),
            postponedId = 0,
            reposts = Reposts(),
            signerId = 0,
            views = Views()
        )

        val result = WallService.add(post).id
        assertEquals(1, result)
    }

    @org.junit.Test
    fun updateExisting() {
        val post = Post(
            0,
            0,
            0,
            0,
            101023,
            "Привет!",
            0,
            0,
            comments = Comments(),
            copyright = "Copyright",
            likes = Likes(),
            geo = Geo(),
            postponedId = 0,
            reposts = Reposts(),
            signerId = 0,
            views = Views()
        )
        WallService.add(post)

        val postUpdated = Post(
            post.id,
            0,
            0,
            0,
            101023,
            "Привет! Меня обновили!",
            0,
            0,
            comments = Comments(),
            copyright = "Copyright",
            likes = Likes(),
            geo = Geo(),
            postponedId = 0,
            reposts = Reposts(),
            signerId = 0,
            views = Views()
        )

       val result = WallService.update(postUpdated)
        assertTrue(result)
    }

    @org.junit.Test
    fun updateNonExisting() {

        val postUpdated = Post(
            1,
            0,
            0,
            0,
            101023,
            "Привет! Меня не нашли :(",
            0,
            0,
            comments = Comments(),
            copyright = "Copyright",
            likes = Likes(),
            geo = Geo(),
            postponedId = 0,
            reposts = Reposts(),
            signerId = 0,
            views = Views()
        )

        val result = WallService.update(postUpdated)
        assertFalse(result)
    }
}