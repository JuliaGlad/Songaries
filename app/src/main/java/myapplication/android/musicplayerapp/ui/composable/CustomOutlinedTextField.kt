package myapplication.android.musicplayerapp.ui.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import myapplication.android.musicplayerapp.ui.theme.DarkGrey
import myapplication.android.musicplayerapp.ui.theme.DarkerGrey
import myapplication.android.musicplayerapp.ui.theme.MiddleGrey
import myapplication.android.musicplayerapp.ui.theme.White

@Composable
fun CustomOutlineTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    var isLabelVisible by remember { mutableStateOf(true) }
    OutlinedTextField(
        modifier = modifier.onFocusChanged { focusState ->
            isLabelVisible = !focusState.isFocused
        },
        value = value,
        label = {
            if (isLabelVisible) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MiddleGrey,
                )
            }
        },
        textStyle = MaterialTheme.typography.bodySmall,

        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = DarkGrey,
            focusedContainerColor = DarkGrey,
            focusedTextColor = White,
            unfocusedTextColor = White,
            focusedBorderColor = DarkerGrey,
            unfocusedBorderColor = DarkerGrey
        ),
        onValueChange = { newText ->  onValueChange.invoke(newText) }
    )
}