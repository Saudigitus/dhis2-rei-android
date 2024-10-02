package com.saudigitus.support_module.ui.manualScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.BasicApp
import com.saudigitus.support_module.ui.components.ErrorComponent

@Composable
fun GeneralReportScreen(
    //viewModel: MenuViewModel,
    //onBack: () -> Unit
) {
    BasicApp(title = "Relatar erro de sincronização",
        fab = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send") }, // Icon on the FAB
                text = { Text("Enviar") }, // Text on the FAB
                onClick = { /* Action when clicked */ },
                containerColor = Color.White, // Background color of the FAB
                contentColor = Color(0xFF2C98F0) // Color of the text and icon
            )
        },
        content = {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.support), // Substitua 'your_image' pelo nome do seu arquivo
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(10.dp))
        }
            val textState = remember { mutableStateOf("") }
            Spacer(Modifier.height(20.dp))
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text(text = "Message") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 12,
                maxLines = 12, // Allows up to 5 lines
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
fun GenScreenPreview() {
    GeneralReportScreen()
}