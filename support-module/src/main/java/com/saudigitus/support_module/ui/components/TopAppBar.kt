package com.saudigitus.support_module.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
/*import androidx.room.jarjarred.org.antlr.v4.codegen.model.Sync
import com.saudigitus.support_module.R
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    headers: ToolbarHeaders,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors =  TopAppBarDefaults.topAppBarColors(),
    navigationAction: () -> Unit,
    disableNavigation: Boolean = true,
    actionState: ToolbarActionState = ToolbarActionState(),
    syncAction: () -> Unit = {},
    filterAction: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = headers.title,
                    maxLines = 1,
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true,


                    )
                headers.subtitle?.let { subtitle ->
                    Text(
                        text = subtitle,
                        maxLines = 1,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (!disableNavigation) {
                IconButton(onClick = { navigationAction.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        },
        actions = {
            if (actionState.syncVisibility) {
                IconButton(onClick = { syncAction.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = stringResource(R.string.sync)
                    )
                }
            }
            if (actionState.filterVisibility) {
                IconButton(onClick = { filterAction.invoke() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                        contentDescription = stringResource(R.string.filter)
                    )
                }
            }
        },
        colors = colors
    )
}*/