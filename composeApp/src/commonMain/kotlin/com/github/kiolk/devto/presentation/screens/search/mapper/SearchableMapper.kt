package com.github.kiolk.devto.presentation.screens.search.mapper

import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Organization
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.search.model.CommentSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.OrganizationSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchableUi
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi
import com.github.kiolk.devto.utils.colors.hexToColor
import com.github.kiolk.devto.utils.localisation.StringProvider

fun Searchable.mapToSearchableUi(stringProvider: StringProvider): SearchableUi {
    return when (this) {
        is Article -> this.mapToArticleUi(stringProvider)
        is Comment -> this.mapToCommentUi()
        is Organization -> this.mapToOrganizationUi()
        is Tag -> this.mapToTagUi()
        is User -> this.mapToUserUi()
    }
}

fun Comment.mapToCommentUi(): CommentSearchUi {
    return CommentSearchUi(this)
}

fun Organization.mapToOrganizationUi(): OrganizationSearchUi {
    return OrganizationSearchUi(this)
}

fun Tag.mapToTagUi(): TagSearchUi {
    return TagSearchUi(
        this,
        backgroundColor = hexToColor(this.bgColorHex).copy(alpha = 0.2f),
        badgeColor = hexToColor(this.bgColorHex),
        summary = this.shortSummary.ifEmpty { null }
    )
}

fun User.mapToUserUi(): UserSearchUi {
    return UserSearchUi(this, userImage = this.profileImage ?: profileImage90)
}
