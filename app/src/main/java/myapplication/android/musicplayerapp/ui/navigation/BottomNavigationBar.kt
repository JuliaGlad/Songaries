package myapplication.android.musicplayerapp.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.MiddleGrey
import myapplication.android.musicplayerapp.ui.theme.White


data class BottomNavigationItem(
    val title: String,
    val icon: Int,
    val route: String
)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: (BottomNavigationItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = MainGrey,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onClick(item) },
                label = { Text(text = item.title) },
                icon = {
                    Column {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.title
                        )
                    }

                },
                colors = NavigationBarItemColors(
                    selectedIconColor = White,
                    selectedTextColor = White,
                    unselectedIconColor = MiddleGrey,
                    unselectedTextColor = MiddleGrey,
                    selectedIndicatorColor = Color.Transparent,
                    disabledIconColor = MiddleGrey,
                    disabledTextColor = MiddleGrey
                )
            )
        }
    }
}
