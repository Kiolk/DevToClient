package com.github.kiolk.devto.presentation.screens.home.models

import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_exploding_head_daceb
import devto.composeapp.generated.resources.ic_fire
import devto.composeapp.generated.resources.ic_multi_unicorn
import devto.composeapp.generated.resources.ic_raised_hands
import devto.composeapp.generated.resources.ic_sparkle_heart
import org.jetbrains.compose.resources.DrawableResource

sealed class ReactionType(val resId: DrawableResource, val name: String = "") {

    data object Fire : ReactionType(resId = Res.drawable.ic_fire, name = "fire")
    data object Heart : ReactionType(resId = Res.drawable.ic_sparkle_heart, name = "like")
    data object Unicorn : ReactionType(resId = Res.drawable.ic_multi_unicorn, name = "unicorn")
    data object Head : ReactionType(resId = Res.drawable.ic_exploding_head_daceb, name = "exploding_head")
    data object Hands : ReactionType(resId = Res.drawable.ic_raised_hands, name = "raised_hands")
}
