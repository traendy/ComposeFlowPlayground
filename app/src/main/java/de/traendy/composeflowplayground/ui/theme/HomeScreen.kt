package de.traendy.composeflowplayground.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.traendy.composeflowplayground.data.Budget
import de.traendy.composeflowplayground.data.Expanse


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ComposeFlowPlaygroundApp(expanseListViewModel: ExpanseListViewModel = viewModel()) {
    val expanses: List<Expanse> by expanseListViewModel.expanseList.observeAsState(emptyList())
    val budget: Budget by expanseListViewModel.budget.observeAsState(Budget( 0f))
    val backgroundVisibility: Boolean by expanseListViewModel.isBackgroundVisible.observeAsState(false)
    Scaffold(
        bottomBar = {
            ExpanseInput(expanseListViewModel::onItemAdded)
        },
        content = {
            AnimatedVisibility(visible = backgroundVisibility) {
                Background()
            }
            Column(modifier = Modifier.height(620.dp)) {
                BudgetView(budget = budget?: Budget(0.0f), expanseListViewModel::increaseBudget)
                ExpanseList(expanses, expanseListViewModel::onExpanseDelete)
            }
        },
    )
}