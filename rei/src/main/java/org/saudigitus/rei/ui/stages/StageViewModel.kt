package org.saudigitus.rei.ui.stages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.saudigitus.rei.data.source.DataManager

class StageViewModel(
    private val dataManager: DataManager,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(StageTabState())

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value,
        )

    init {
        loadStages()
    }

    private fun loadStages() {
        viewModelScope.launch {
            val program = dataManager.loadConfig()?.stageProgram
            val stages = dataManager.getStages(program ?: "")

            viewModelState.update {
                it.copy(
                    stages = stages,
                    stagesData = dataManager.getStageEventData(
                        program ?: "",
                        stages.firstOrNull()?.uid ?: "",
                    ),
                )
            }
        }
    }

    fun loadStageData(stage: String) {
        viewModelScope.launch {
            val program = dataManager.loadConfig()?.stageProgram

            viewModelState.update {
                it.copy(stagesData = dataManager.getStageEventData("$program", stage))
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class StageViewModelFactory(
    private val dataManager: DataManager,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StageViewModel(dataManager) as T
    }
}
