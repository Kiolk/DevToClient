package com.github.kiolk.devto.presentation.screens.home.models

import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_exploding_head_daceb
import devto.composeapp.generated.resources.ic_fire
import devto.composeapp.generated.resources.ic_multi_unicorn
import devto.composeapp.generated.resources.ic_raised_hands
import devto.composeapp.generated.resources.ic_sparkle_heart
import org.jetbrains.compose.resources.DrawableResource

sealed class ReactionType(val resId: DrawableResource) {

    data object Fire : ReactionType(resId = Res.drawable.ic_fire)
    data object Heart : ReactionType(resId = Res.drawable.ic_sparkle_heart)
    data object Unicorn : ReactionType(resId = Res.drawable.ic_multi_unicorn)
    data object Head : ReactionType(resId = Res.drawable.ic_exploding_head_daceb)
    data object Hands : ReactionType(resId = Res.drawable.ic_raised_hands)
}
