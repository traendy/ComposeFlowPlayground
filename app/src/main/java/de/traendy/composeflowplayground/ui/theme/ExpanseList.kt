package de.traendy.composeflowplayground.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.traendy.composeflowplayground.data.Expanse

@Composable
fun ExpanseList(expanses: List<Expanse>, deleteExpanse: (Expanse) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            count = expanses.size,
            itemContent = {
                ExpanseListItem(expanses[it],it, deleteExpanse)
            }
        )
    }
}

@Composable
fun ExpanseListItem(expanse: Expanse,position:Int, deleteExpanse: (Expanse) -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.fillMaxWidth(0.8f)) {
                Text(text = expanse.name, style = MaterialTheme.typography.h6)
                Text(text = "${expanse.amount}", style = MaterialTheme.typography.caption)
            }
            ColorChangingButton(onClick = {deleteExpanse(expanse)}, Modifier.fillMaxWidth(0.8f), animOffset = position*100) {
                Text(text = "Del")
            }
        }
    }
}