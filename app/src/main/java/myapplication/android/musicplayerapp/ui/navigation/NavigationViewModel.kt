package myapplication.android.musicplayerapp.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NavigationViewModel: ViewModel() {
    private val _bottomBarVisible = MutableStateFlow(true)
    val bottomBarVisible: StateFlow<Boolean> = _bottomBarVisible

    fun setBottomBarVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _bottomBarVisible.emit(isVisible)
        }
    }
}