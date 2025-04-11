package myapplication.android.musicplayerapp.ui.screen.general

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.LoadingScreen
import myapplication.android.musicplayerapp.ui.composable.TrackItem
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.navigation.withArgs
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralEffect
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralIntent
import myapplication.android.musicplayerapp.ui.screen.general.mvi.GeneralState
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.Purple
import myapplication.android.musicplayerapp.ui.theme.PurpleDark
import myapplication.android.musicplayerapp.ui.theme.White
import kotlin.random.Random

@Composable
fun GeneralScreen(
    navController: NavController,
    onBottomBarVisible: () -> Unit
) {
    onBottomBarVisible()
    val viewModel: GeneralScreenViewModel = hiltViewModel()
    val state: GeneralState = viewModel.uiState.collectAsState().value
    val listItems: MutableList<TrackUiModel> = remember { mutableStateListOf() }

    CollectEffect(viewModel, navController)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SetUI(
            state = state,
            listItems = listItems,
            viewModel = viewModel
        )
    }
}

@Composable
fun SetUI(
    viewModel: GeneralScreenViewModel,
    state: GeneralState,
    listItems: MutableList<TrackUiModel>
) {
    when (state.lceState) {
        is LceState.Content -> {
            listItems.addAll(state.lceState.data.tracks)
            Column(modifier = Modifier.fillMaxSize()) {
                ImageCard()
                MusicListCard(
                    tracks = listItems,
                    playTrack = { viewModel.sendEffect(GeneralEffect.StartTrack) },
                    addTrack = { track ->
                        viewModel.sendEffect(GeneralEffect.NavigateToAddTrack(track))
                    },
                    getNewItems = { viewModel.sendIntent(GeneralIntent.LoadTracks) }
                )
            }
        }

        is LceState.Error -> {
            Log.i("Error", state.lceState.throwable.message.toString())
            ErrorScreen()
        }

        LceState.Loading -> LoadingScreen()
    }
}

@Composable
fun ImageCard() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.abstract_background),
            contentScale = ContentScale.Crop,
            contentDescription = CONTENT_DESCRIPTION,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        AnimatedMusicVisualizer()
        AnimatedFloatingText()
    }
}

@Composable
fun AnimatedFloatingText(
    text: String = stringResource(R.string.app_name)
) {
    Row(horizontalArrangement = Arrangement.Center) {
        text.forEachIndexed { index, char ->
            val infiniteTransition = rememberInfiniteTransition(label = "Transition_$index")
            val randomDelay = remember(index) { (300..1200).random() }
            val randomDuration = remember(index) { (800..1500).random() }

            val animatedOffset = infiniteTransition.animateFloat(
                initialValue = -5f,
                targetValue = 5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = randomDuration,
                        easing = LinearEasing,
                        delayMillis = randomDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "Animation_$index"
            )
            Text(
                text = char.toString(),
                style = TextStyle(
                    shadow = Shadow(color = White, blurRadius = 8f),
                    fontSize = 48.sp,
                    color = White
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.offset(y = animatedOffset.value.dp)
            )
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun MusicListCard(
    modifier: Modifier = Modifier,
    tracks: MutableList<TrackUiModel>,
    getNewItems: () -> Unit,
    playTrack: () -> Unit,
    addTrack: (TrackUiModel) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF030406)),
        colors = CardDefaults.cardColors(MainGrey),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 20.dp,
                    bottom = 12.dp
                ),
                text = stringResource(R.string.tracks),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            val listState = rememberLazyListState()

            LaunchedEffect(listState) {
                snapshotFlow {
                    listState.isScrolledToTheEnd()
                }
                    .debounce(500)
                    .collectLatest { isEnd ->
                        if (isEnd) {
                            getNewItems.invoke()
                        }
                    }
            }

            LazyColumn(
                state = listState
            ) {
                items(tracks.size) { index ->
                    with(tracks[index]) {
                        TrackItem(
                            title = title,
                            artist = artist,
                            uri = image,
                            icon = R.drawable.ic_add,
                            playTrack = { playTrack.invoke() },
                            iconAction = {
                                addTrack.invoke(tracks[index])
                            }
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.height(20.dp),
                            color = DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedMusicVisualizer(height: Dp = 400.dp) {
    val numBars = 48
    val barWidth = 12.dp
    val barSpacing = 2.dp
    val animatedBarHeights = remember {
        List(numBars) { mutableFloatStateOf(Random.nextFloat() * 100) }
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        while (true) {
            animatedBarHeights.forEach { barHeight ->
                coroutineScope.launch {
                    val newHeight = Random.nextFloat() * 600 + 18
                    animate(
                        initialValue = barHeight.floatValue,
                        targetValue = newHeight,
                        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing)
                    ) { value, _ ->
                        barHeight.floatValue = value
                    }
                }
            }
            delay(55)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxWidth()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val totalBarAreaWidth =
                (numBars * barWidth.toPx()) + ((numBars - 1) * barSpacing.toPx())
            val startX = (canvasWidth - totalBarAreaWidth) / 2

            for (i in 0 until numBars) {
                val x = startX + (i * (barWidth.toPx() + barSpacing.toPx()))
                val barHeightPx = animatedBarHeights[i].floatValue
                val y = canvasHeight / 2 - barHeightPx / 2


                drawLine(
                    color = lerp(Purple, PurpleDark, i.toFloat() / numBars),
                    start = Offset(x, y),
                    end = Offset(x, y + barHeightPx),
                    strokeWidth = barWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
private fun CollectEffect(
    viewModel: GeneralScreenViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                GeneralEffect.StartTrack ->
                    Log.i("Track is playing", "playing")

                is GeneralEffect.NavigateToAddTrack ->
                    navController.navigate(
                        route = withArgs(
                            route = PlaylistsScreen.AddTrackScreen.route,
                            Uri.encode(Gson().toJson(effect.track))
                        )
                    )
            }
        }
    }
}

private fun lerp(color1: Color, color2: Color, factor: Float): Color {
    val red = (color1.red * (1 - factor) + color2.red * factor).coerceIn(0f, 1f)
    val green = (color1.green * (1 - factor) + color2.green * factor).coerceIn(0f, 1f)
    val blue = (color1.blue * (1 - factor) + color2.blue * factor).coerceIn(0f, 1f)
    val alpha = (color1.alpha * (1 - factor) + color2.alpha * factor).coerceIn(0f, 1f)

    return Color(red, green, blue, alpha)
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

const val CONTENT_DESCRIPTION = "Music Wave"