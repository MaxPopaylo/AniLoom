package ua.aniloom.data.datasorce.network.dto

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object LocalDateFormatter {
    private val malFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val jikanFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun malParse(dateString: String): LocalDate = LocalDate.parse(dateString, malFormatter)
    fun jikanParse(dateString: String): LocalDate = OffsetDateTime.parse(dateString, jikanFormatter).toLocalDate()
}