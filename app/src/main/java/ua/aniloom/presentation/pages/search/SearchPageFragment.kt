package ua.aniloom.presentation.pages.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

import ua.aniloom.databinding.FragmentSearchPageBinding
import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.AiredProp
import ua.aniloom.domain.models.anime.AnimeData
import ua.aniloom.domain.models.anime.Broadcast
import ua.aniloom.domain.models.anime.DateProp
import ua.aniloom.domain.models.anime.Genre
import ua.aniloom.domain.models.anime.Genres
import ua.aniloom.domain.models.anime.ImageUrls
import ua.aniloom.domain.models.anime.Images
import ua.aniloom.domain.models.anime.Licensor
import ua.aniloom.domain.models.anime.Producer
import ua.aniloom.domain.models.anime.Studio
import ua.aniloom.domain.models.anime.Theme
import ua.aniloom.domain.models.anime.Title
import ua.aniloom.domain.models.anime.Trailer
import ua.aniloom.domain.models.anime.TrailerImages
import ua.aniloom.presentation.recycleview.adapters.HorizontalAnimeAdapter


class SearchPageFragment : Fragment() {
    private var _binding: FragmentSearchPageBinding? = null
    private val binding get() = _binding!!

    private val ongoingAdapter by lazy (LazyThreadSafetyMode.NONE) {
        HorizontalAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.titleEnglish}", Toast.LENGTH_SHORT).show()
        })
    }

    private val historyAdapter by lazy (LazyThreadSafetyMode.NONE) {
        HorizontalAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.titleEnglish}", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchPageBinding.inflate(inflater, container, false)

        with(binding) {
            vSearchField.apply {
                setItems(
                    items = Genres.entries.toList(),
                    labelProvider = { it.genre.name },
                    onItemClick = {}
                )
                setOnSearchListener {  }
            }

            vCharts.apply {
                bMangaCharts.setOnClickListener {  }
                bAnimeCharts.setOnClickListener {  }
            }

            binding.rvOngoingList.apply {
                adapter = ongoingAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            binding.rvHistoryList.apply {
                adapter = historyAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            ongoingAdapter.submitData(PagingData.from(fakeAnimeList))
            historyAdapter.submitData(PagingData.from(fakeAnimeList))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchPageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    val fakeAnimeList = listOf(
        AnimeData(
            malId = 1,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/9/74398.jpg", "https://cdn.myanimelist.net/images/anime/9/74398.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Ace of Diamond: Second Season",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
        AnimeData(
            malId = 2,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/1314/108941.jpg", "https://cdn.myanimelist.net/images/anime/1314/108941t.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Naruto",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
        AnimeData(
            malId = 3,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/1314/108941.jpg", "https://cdn.myanimelist.net/images/anime/1314/108941t.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Naruto",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
        AnimeData(
            malId = 4,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/1314/108941.jpg", "https://cdn.myanimelist.net/images/anime/1314/108941t.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Naruto",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
        AnimeData(
            malId = 5,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/1314/108941.jpg", "https://cdn.myanimelist.net/images/anime/1314/108941t.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Naruto",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
        AnimeData(
            malId = 6,
            url = "https://example.com/anime/1",
            images = Images(webp = ImageUrls("https://cdn.myanimelist.net/images/anime/1314/108941.jpg", "https://cdn.myanimelist.net/images/anime/1314/108941t.jpg", ""), jpg = ImageUrls("", "", "")),
            trailer = Trailer(
                images = TrailerImages(
                    imageUrl = "",
                    "",
                    "",
                    "",
                    ""
                ),
                youtubeId = "dQw4w9WgXcQ",
                url = "https://youtu.be/dQw4w9WgXcQ",
                embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            approved = true,
            titles = listOf(Title(type = "Default", title = "Naruto")),
            title = "Naruto",
            titleEnglish = "Naruto",
            titleJapanese = "ナルト",
            titleSynonyms = listOf("Naruto Uzumaki"),
            type = "TV",
            source = "Manga",
            episodes = 220,
            status = "Finished Airing",
            airing = false,
            aired = Aired("", "", AiredProp(DateProp(1,1,1), DateProp(1,1,1)), ""),
            duration = "23 min per ep",
            rating = "PG-13",
            score = 7.9,
            scoredBy = 100000,
            rank = 50,
            popularity = 1,
            members = 1000000,
            favorites = 50000,
            synopsis = "A young ninja strives to be acknowledged in his village and beyond.",
            background = null,
            season = "Fall",
            year = 2002,
            broadcast = Broadcast(day = "Wednesday", time = "19:00", timezone = "JST", ""),
            producers = listOf(Producer(name = "Studio Pierrot", malId =  1, type =  "", url =  "")),
            licensors = listOf(Licensor(name = "Crunchyroll", malId =  1, type =  "", url =  "")),
            studios = listOf(Studio(name = "Studio Pierrot",malId =  1, type =  "", url =  "")),
            genres = listOf(Genre(malId = 1, name = "Action", type =  "", url =  "")),
            explicitGenres = emptyList(),
            themes = listOf(Theme(malId = 1, name = "Shounen", type = "", url = "")),
            demographics = listOf()
        ),
    )
}
