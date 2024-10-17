package org.saudigitus.rei.ui.stages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.saudigitus.rei.data.model.AppConfigItem
import org.saudigitus.rei.data.source.DataManager
import javax.inject.Inject

@HiltViewModel
class StageViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(StageTabState())

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value,
        )

    private val _config = MutableStateFlow<List<AppConfigItem>>(emptyList())
    private val config: StateFlow<List<AppConfigItem>> = _config

    private val _program = MutableStateFlow("")
    private val program: StateFlow<String> = _program

    init {
        loadConfig()
        loadStages()
    }

    private fun loadConfig() {
        viewModelScope.launch {
            _config.value = dataManager.loadConfig()
        }
    }

    private fun loadStages() {
        viewModelScope.launch {
            val stages = dataManager.getStages(program.value)

            viewModelState.update {
                it.copy(
                    stages = stages,
                    stagesData = dataManager.getStageEventData(
                        program.value,
                        stages.firstOrNull()?.uid ?: "",
                    ),
                )
            }
        }
    }

    fun setProgram(program: String) {
        _program.value = program
    }

    fun loadStageData(stage: String) {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(stagesData = dataManager.getStageEventData(program.value, stage))
            }
        }
    }
}
