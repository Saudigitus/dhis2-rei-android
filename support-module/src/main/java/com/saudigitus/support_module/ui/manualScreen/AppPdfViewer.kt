package com.saudigitus.support_module.ui.manualScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.saudigitus.support_module.ui.components.BasicApp
import java.io.File
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rizzi.bouquet.VerticalPDFReader
import timber.log.Timber

@Composable
fun PdfViewer(
    navController: NavHostController,
    onBack: () -> Unit = {}, // Placeholder for back action
    viewModel: ManualViewModel = hiltViewModel(),
    file: File?,
) {
    val pdfVerticalReaderState by viewModel.pdfVerticalReaderState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(file) {
        if (file != null) {
            viewModel.openPdf(file)
        }
    }
    BasicApp(
        title = "PDF Viewer",
        onBack = onBack,
        content = {
            Text(text = "PATH ${file?.path}")
            if(uiState.hasFileLoaded){
                VerticalPDFReader(
                    state = pdfVerticalReaderState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                )
            }

        })
}
