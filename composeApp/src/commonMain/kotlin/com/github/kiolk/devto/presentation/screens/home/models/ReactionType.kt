package com.github.kiolk.devto.presentation.screens.home.models

import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_exploding_head_daceb
import devto.composeapp.generated.resources.ic_fire
import devto.composeapp.generated.resources.ic_multi_unicorn
import devto.composeapp.generated.resources.ic_raised_hands
import devto.composeapp.generated.resources.ic_sparkle_heart
import org.jetbrains.compose.resources.DrawableResource

open class ReactionType(
    val resId: DrawableResource,
    open val name: String = "",
    open val count: Int = 0,
) {

    data class Fire(override val count: Int = 0) :
        ReactionType(resId = Res.drawable.ic_fire, name = "fire")

    data class Heart(override val count: Int = 0) :
        ReactionType(resId = Res.drawable.ic_sparkle_heart, name = "like")

    data class Unicorn(override val count: Int = 0) :
        ReactionType(resId = Res.drawable.ic_multi_unicorn, name = "unicorn")

    data class Head(override val count: Int = 0) :
        ReactionType(resId = Res.drawable.ic_exploding_head_daceb, name = "exploding_head")

    data class Hands(override val count: Int = 0) :
        ReactionType(resId = Res.drawable.ic_raised_hands, name = "raised_hands")
}
