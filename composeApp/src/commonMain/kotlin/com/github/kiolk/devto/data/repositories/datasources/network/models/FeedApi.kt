package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedApi(
    @SerialName("title")
    val title: String,
    @SerialName("path")
    val path: String,
    @SerialName("id")
    val id: Int,
//    @SerialName("user_id")
//    val userId: Int,
//    @SerialName("comments_count")
//    val commentsCount: Int,
//    @SerialName("public_reactions_count")
//    val publicReactionCount: Int,
//    @SerialName("organization_id")
//    val organizationId: Int? = null,
//    @SerialName("reading_time")
//    val readingTimeMinutes: Int,
//    @SerialName("video_thumbnail_url")
//    val videoThumbnailUrl: String? = null,
//    @SerialName("video")
//    val video: String? = null,
//    @SerialName("video_duration_in_minutes")
//    val videoDurationMinutes: String,
//    @SerialName("experience_level_rating")
//    val experienceLevelRating: Double,
//    @SerialName("experience_level_rating_distribution")
//    val experienceLevelRatingDistribution: Double,
//    @SerialName("main_image_height")
//    val mainImageHeight: Int,
//    @SerialName("user")
//    val user: UserApi,
//    @SerialName("pinned")
//    val pinned: Boolean,
//    @SerialName("main_image")
//    val mainImage: String?,
//    @SerialName("tag_list")
//    val tagList: List<String>,
//    @SerialName("readable_publish_date")
//    val readablePublishDate: String,
//    @SerialName("flare_tag")
//    val flareTag: FlareTagApi? = null,
//    @SerialName("class_name")
//    val className: String,
//    @SerialName("cloudinary_video_url")
//    val cloudinaryVideoUrl: String? = null,
//    @SerialName("published_at_int")
//    val publishedAtInt: Long,
//    @SerialName("published_timestamp")
//    val publishedTimestamp: Instant,
//    @SerialName("main_image_background_hex_color")
//    val mainImageBackgroundHexColor: String,
//    @SerialName("public_reaction_categories")
//    val publicReactionCategories: List<PublicReactionCategoryApi>,
//    @SerialName("top_comments")
//    val topComments: List<TopCommentApi>
)
