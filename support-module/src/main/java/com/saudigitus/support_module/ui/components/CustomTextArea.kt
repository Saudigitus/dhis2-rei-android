package com.saudigitus.support_module.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.saudigitus.support_module.ui.theme.NeutralVariant
import com.saudigitus.support_module.ui.theme.app_blue_color

@Composable
fun CustomTextField(textState: androidx.compose.runtime.MutableState<String>, label: String, minLines: Int) {
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        minLines = minLines,
        maxLines = minLines,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Default
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = NeutralVariant,
            unfocusedContainerColor = NeutralVariant,
            focusedSupportingTextColor = app_blue_color,
            focusedIndicatorColor = app_blue_color,
        )
    )
}
