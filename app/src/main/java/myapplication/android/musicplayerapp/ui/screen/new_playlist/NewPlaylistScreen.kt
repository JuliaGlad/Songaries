package myapplication.android.musicplayerapp.ui.screen.new_playlist

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.CustomOutlineTextField
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.theme.DarkGrey
import myapplication.android.musicplayerapp.ui.theme.DarkerGrey
import myapplication.android.musicplayerapp.ui.theme.LightGrey
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.Purple

@Composable
fun NewPlaylistScreen(
    onDismiss: () -> Unit,
    onAddPlaylist: (PlaylistUi) -> Unit
) {
    val viewModel: NewPlaylistViewModel = hiltViewModel()
    var titleFieldState by remember { mutableStateOf("") }
    var descriptionFieldState by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    val error by viewModel.onError.collectAsState(initial = null)
    val isAdded by viewModel.isAdded.collectAsState(initial = false)

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        image = it.toString()
    }

    if (isAdded) {
        onAddPlaylist.invoke(
            PlaylistUi(
                title = titleFieldState,
                image = image,
                description = descriptionFieldState,
                tracks = TrackUiList(emptyList())
            )
        )
        onDismiss.invoke()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGrey)
            .padding(top = 20.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (error != null) {
                Log.e("New playlist error", error?.message.toString())
                ErrorScreen()
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.new_playlist),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.clickable {
                            photoPicker.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        if (image.isEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .background(color = DarkGrey)
                                    .size(
                                        width = 80.dp,
                                        height = 80.dp
                                    )
                                    .border(width = 1.dp, color = DarkerGrey)
                                    .padding(24.dp),
                                painter = painterResource(R.drawable.ic_add_playlist),
                                contentDescription = stringResource(R.string.create_playlist),
                                tint = Purple
                            )
                        } else {
                            Image(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(image.toUri())
                                        .build()
                                ),
                                contentDescription = image
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.padding(start = 14.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.title),
                            style = MaterialTheme.typography.bodySmall,
                            color = LightGrey
                        )
                        CustomOutlineTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = titleFieldState,
                            label = stringResource(R.string.enter_title)
                        ) { newValue -> titleFieldState = newValue }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 14.dp
                        )
                ) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.bodySmall,
                        color = LightGrey
                    )
                    CustomOutlineTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descriptionFieldState,
                        label = stringResource(R.string.enter_description)
                    ) { newValue -> descriptionFieldState = newValue }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple
                    ),
                    onClick = {
                        viewModel.addNewPlaylist(
                            titleFieldState,
                            descriptionFieldState,
                            image
                        )
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.done),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

