package de.traendy.composeflowplayground.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.traendy.composeflowplayground.data.Budget


@Preview
@Composable
fun BudgetViewPreview(){
    BudgetView(budget = Budget( 333.33f), {})
}

@Composable
fun BudgetView(budget: Budget, increaseBudget: () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text("B: ")
        Text(budget.amount.toString())
        IconButton(onClick = { increaseBudget() }) {
            Icon(Icons.Filled.Add, contentDescription = "")

        }
    }

}
