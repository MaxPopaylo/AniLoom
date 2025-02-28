package ua.aniloom.domain.models.anime

data class Genre(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Theme(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

enum class Genres(val genre: Genre) {
    COMEDY(Genre(4, "anime", "Comedy", "https://myanimelist.net/anime/genre/4/Comedy")),
    FANTASY(Genre(10, "anime", "Fantasy", "https://myanimelist.net/anime/genre/10/Fantasy")),
    ACTION(Genre(1, "anime", "Action", "https://myanimelist.net/anime/genre/1/Action")),
    ADVENTURE(Genre(2, "anime", "Adventure", "https://myanimelist.net/anime/genre/2/Adventure")),
    SCI_FI(Genre(24, "anime", "Sci-Fi", "https://myanimelist.net/anime/genre/24/Sci-Fi")),
    DRAMA(Genre(8, "anime", "Drama", "https://myanimelist.net/anime/genre/8/Drama")),
    ROMANCE(Genre(22, "anime", "Romance", "https://myanimelist.net/anime/genre/22/Romance")),
    SLICE_OF_LIFE(Genre(36, "anime", "Slice of Life", "https://myanimelist.net/anime/genre/36/Slice_of_Life")),
    SUPERNATURAL(Genre(37, "anime", "Supernatural", "https://myanimelist.net/anime/genre/37/Supernatural")),
    AVANT_GARDE(Genre(5, "anime", "Avant Garde", "https://myanimelist.net/anime/genre/5/Avant_Garde")),
    MYSTERY(Genre(7, "anime", "Mystery", "https://myanimelist.net/anime/genre/7/Mystery")),
    SPORTS(Genre(30, "anime", "Sports", "https://myanimelist.net/anime/genre/30/Sports")),
    HORROR(Genre(14, "anime", "Horror", "https://myanimelist.net/anime/genre/14/Horror")),
    SUSPENSE(Genre(41, "anime", "Suspense", "https://myanimelist.net/anime/genre/41/Suspense")),
    AWARD_WINNING(Genre(46, "anime", "Award Winning", "https://myanimelist.net/anime/genre/46/Award_Winning")),
    BOYS_LOVE(Genre(28, "anime", "Boys Love", "https://myanimelist.net/anime/genre/28/Boys_Love")),
    GIRLS_LOVE(Genre(26, "anime", "Girls Love", "https://myanimelist.net/anime/genre/26/Girls_Love"))
}