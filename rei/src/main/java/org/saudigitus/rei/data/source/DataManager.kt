package org.saudigitus.rei.data.source

import androidx.compose.ui.graphics.Color
import org.saudigitus.rei.data.model.Stage

interface DataManager {
    suspend fun getStages(
        program: String,
    ): List<Stage>

    suspend fun getStageEventData(
        program: String,
        stage: String,
    ): List<Triple<String, Int, Color>>
}
