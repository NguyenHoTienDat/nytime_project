package com.framgia.newyorktime.mapper

import com.framgia.domain.model.Story
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.model.nytime.StoryItemMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

/**
 * Lưu ý, khi test mapper nên test từng thuộc tính chứ k đc so sánh cả 2 item. Ví dụ ntn
 *       Assert.assertEquals(expectObj, actualObj) bởi vì khi mapper, có thể bên pre có số thuộc tính
 *       ít hơn bên domain. nếu so sánh cả obj sẽ sai. Chỉ so sánh nhưng j map sang thôi
 */
@RunWith(MockitoJUnitRunner::class)
class StoryMapperTest {

    @Mock
    private lateinit var mockStoryMapper: StoryItemMapper

    @Spy
    private lateinit var spyStoryMapper: StoryItemMapper

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    /**
     * Fun này dùng mock để test. Với mock ta phải giả lập kết quả khi thực thi fun nào đó của obj
     */
    @Test
    fun mockToDomainTest() {
        val expectValue = Story("My Title Item", "My Abs Item",
                "http://Item", "abcItem", "http://imageItem", "18/10/2018")

        // Do mock là giả haonf toàn.Nếu k giả lập value ntn thì khi gọi fun của obj mock luôn return null hoặc donothing
        `when`(mockStoryMapper.mapToDomain(createStoryItem())).thenReturn(expectValue)

        //neu k co doan when o tren thi var nay se null va test fail luon (chua can xet den logic vi null da sai luon roi)
        val actualValue = mockStoryMapper.mapToDomain(createStoryItem())

        assert(expectValue.section == actualValue.section)
        assert(expectValue.subsection == actualValue.subsection)
        assert(expectValue.title == actualValue.title)
        assert(expectValue.abstract == actualValue.abstract)
        assert(expectValue.url == actualValue.url)
        assert(expectValue.byline == actualValue.byline)
    }

    /**
     * Fun này dùng spy để test. Do spy vẫn có 1 nửa là obj thật ( ta muốn giả chỗ nào thì giả ). Nên khi gọi
     * fun thì logic trong fun thật sẽ đc exe nên k cần giả lập như mock ở trên
     *
     * Hiện tại case này sẽ faile. Kp do logic sai. Mà do fun mapToDomain bên mapper ta làm ntn
     * override fun mapToDomain(item: StoryItem): Story = Story()
     * Nó luôn return obj trống với value default = "" nên đương nhiên equasl sẽ f
     */
    @Test
    fun spyToDomainTest() {
        val expectValue = Story("My Title Item", "My Abs Item",
                "http://Item", "abcItem", "http://imageItem", "18/10/2018")

        val actualValue = spyStoryMapper.mapToDomain(createStoryItem())

        assert(expectValue.section == actualValue.section)
        assert(expectValue.subsection == actualValue.subsection)
        assert(expectValue.title == actualValue.title)
        assert(expectValue.abstract == actualValue.abstract)
        assert(expectValue.url == actualValue.url)
        assert(expectValue.byline == actualValue.byline)
    }

    /**
     * Tương tự mock test map pre
     */
    @Test
    fun mockToPresentationTest() {
        val expectValue = StoryItem("My Title", "My Abs",
                "http://", "abc", "http://image", "18/10/2018")

        `when`(mockStoryMapper.mapToPresentation(createStory())).thenReturn(expectValue)

        val actualValue = mockStoryMapper.mapToPresentation(createStory())

        assert(expectValue.title == actualValue.title)
    }

    /**
     * Spy test map to pre
     */
    @Test
    fun spyToPresentationTest() {
        val expectValue = StoryItem("http://", "My Abs",
                "http://", "abc", "http://image", "18/10/2018")

        val actualValue = spyStoryMapper.mapToPresentation(createStory())

        Assert.assertEquals(expectValue.title, actualValue.title)
    }

    private fun createStoryItem() = StoryItem("My Title Item", "My Abs Item",
            "http://Item", "abcItem", "http://imageItem", "18/10/2018")

    private fun createStory() = Story("My Title", "My Abs",
            "http://", "abc", "http://image", "18/10/2018")
}