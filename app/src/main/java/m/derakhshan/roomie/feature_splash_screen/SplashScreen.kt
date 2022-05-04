package m.derakhshan.roomie.feature_splash_screen


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.presentation.RemovableDiagonalRectShape
import m.derakhshan.roomie.ui.theme.White

@Composable
fun SplashScreen(removeScreen: Boolean, animationFinish: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo))
    val progress by animateLottieCompositionAsState(composition)
    if (progress >= 0.4f)
        animationFinish()

    val offset = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(removeScreen) {
        if (removeScreen)
            offset.animateTo(1000f, animationSpec = tween(3000, easing = FastOutSlowInEasing))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RemovableDiagonalRectShape(offset = offset.value))
            .background(White)
    ) {
        LottieAnimation(
            composition,
            progress,
        )
    }
}