package myapplication.android.musicplayerapp.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class MviBaseViewModel<
        PartialState : MviPartialState,
        Intent : MviIntent,
        State : MviState,
        Effect : MviEffect> : ViewModel() {

    protected abstract val store: MviStore<PartialState, Intent, State, Effect>

    protected fun startCollecting() {
        viewModelScope.launch {
            store.uiState.collect(::resolveState)
        }

        viewModelScope.launch {
            store.effect.collect(::resolveEffect)
        }
    }

    protected abstract fun resolveState(state: State)

    protected abstract fun resolveEffect(effect: Effect)
}