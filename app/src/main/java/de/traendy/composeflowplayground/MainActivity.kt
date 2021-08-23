package de.traendy.composeflowplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.traendy.composeflowplayground.data.Expanse
import de.traendy.composeflowplayground.ui.theme.ComposeFlowPlaygroundTheme
import de.traendy.composeflowplayground.ui.theme.ExpanseListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFlowPlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ComposeFlowPlaygroundApp()
                }
            }
        }
    }
}

@Composable
fun ComposeFlowPlaygroundApp(expanseListViewModel: ExpanseListViewModel = viewModel()) {
    val list: List<Expanse> by expanseListViewModel.list.observeAsState(emptyList())
    Scaffold(
        bottomBar = {
            ExpanseInput(expanseListViewModel::onItemAdded)
        },
        content = {
            Column(modifier = Modifier.height(620.dp)) {
                ExpanseList(list, expanseListViewModel::onExpanseDelete)
            }
        },
    )
}

@Composable
fun ExpanseInput(addExpanse: (String, Float) -> Unit) {
    var name by remember { mutableStateOf("Name") }
    var amount by remember {
        mutableStateOf(0.0f)
    }
    Column {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = {
            Text("Name")
        })
        Row {
            OutlinedTextField(
                value = amount.toString(),
                onValueChange = { amount = it.toFloat() },
                label = {
                    Text("Amount")
                })
            Button(onClick = { addExpanse(name, amount) }) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
private fun ExpanseList(expanses: List<Expanse>, deleteExpanse: (Expanse) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            count = expanses.size,
            itemContent = {
                ExpanseListItem(expanses[it], deleteExpanse)
            }
        )
    }
}

@Composable
fun ExpanseListItem(expanse: Expanse, deleteExpanse: (Expanse) -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.fillMaxWidth(0.8f)) {
                Text(text = expanse.name, style = typography.h6)
                Text(text = "${expanse.amount}", style = typography.caption)
            }
            Button(onClick = {deleteExpanse(expanse)},Modifier.fillMaxWidth(0.8f)) {
                Text(text = "Del")
            }
        }
    }
}
