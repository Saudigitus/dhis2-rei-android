package com.example.viewtest.ui.manualScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.BasicApp
import com.saudigitus.support_module.ui.components.ErrorComponent
import com.saudigitus.support_module.ui.components.ListCard
import com.saudigitus.support_module.ui.MenuScreen

@Composable
fun ErrorsScreen(
    navController: NavHostController,
    onBack: () -> Unit
) {
    BasicApp(
        title = "Relatar erro de sincronização",
        onBack = onBack,
        content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Column {
                Text(
                    text = "Erros de sincronização",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(Modifier.height(10.dp))
                ErrorComponent("Error It is a long established fact reader")
                Spacer(Modifier.height(10.dp))
                ErrorComponent("Error It is a long established fact reader")
                Spacer(Modifier.height(10.dp))
                ErrorComponent("Error It is a long established fact reader")
            }

            val textState = remember { mutableStateOf("") }
            Spacer(Modifier.height(20.dp))
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text(text = "Message") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 8,
                maxLines = 8, // Allows up to 5 lines
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Default // Allows multiline input
                )
            )
            Spacer(Modifier.height(20.dp))

        }
    })


}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ErrorsScreen(navController = NavHostController(LocalContext.current), onBack = {})
}