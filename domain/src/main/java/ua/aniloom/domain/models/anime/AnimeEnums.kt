package ua.aniloom.domain.models.anime


enum class MediaType(val label: String) {
    TV("TV"),
    OVA("OVA"),
    MOVIE("Movie"),
    SPECIAL("Special"),
    ONA("ONA"),
    MUSIC("Music"),
    CM("CM"),
    PV("PV"),
    TV_SPECIAL("TV Special")
}

enum class RankingType(val label: String) {
    ALL("all"),
    AIRING("airing"),
    UPCOMING("upcoming"),
    TV("tv"),
    OVA("ova"),
    SPECIAL("special"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite")
}

enum class Genres(val genre: Tag) {
    COMEDY(Tag(4, "Comedy")),
    FANTASY(Tag(10, "Fantasy")),
    ACTION(Tag(1, "Action")),
    ADVENTURE(Tag(2, "Adventure")),
    SCI_FI(Tag(24, "Sci-Fi")),
    DRAMA(Tag(8, "Drama")),
    ROMANCE(Tag(22, "Romance")),
    SLICE_OF_LIFE(Tag(36, "Slice of Life")),
    SUPERNATURAL(Tag(37, "Supernatural")),
    AVANT_GARDE(Tag(5, "Avant Garde")),
    MYSTERY(Tag(7, "Mystery")),
    SPORTS(Tag(30, "Sports")),
    HORROR(Tag(14, "Horror")),
    SUSPENSE(Tag(41, "Suspense")),
    AWARD_WINNING(Tag(46, "Award Winning")),
    BOYS_LOVE(Tag(28, "Boys Love")),
    GIRLS_LOVE(Tag(26, "Girls Love"))
}


enum class Status(val label: String){
    FINISHED_AIRING("Finished airing"),
    CURRENTLY_AIRING("Currently airing"),
    NOT_YET_AIRING("Not yet airing")
}

enum class WatchingStatus(val label: String) {
    WATCHING("Watching"),
    COMPLETED("Completed"),
    ON_HOLD("On-Hold"),
    DROPPED("Dropped"),
    PLAN_TO_WATCH("Plan to Watch")
}

enum class Rating(val label: String, val description: String) {
    G("G","G - All Ages"),
    PG("PG","PG - Children"),
    PG_13("PG-13","PG-13 - Teens 13 or older"),
    R("R", "R - 17+(violence & profanity)"),
    R_PLUS("R+", "R+ - Middle Nudity"),
    RX("Rx", "Rx - Hentai")
}