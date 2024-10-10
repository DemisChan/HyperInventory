package com.hyperio.hyperinventory.ui.inventoryEntry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hyperio.hyperinventory.R
import com.hyperio.hyperinventory.navigation.NavigationDestination
import com.hyperio.hyperinventory.ui.InventoryTopAppBar
import com.hyperio.hyperinventory.ui.ViewModelFactoryProvider

object EntryEditDestination : NavigationDestination {
    override val routeName = "entry_edit"
    override val titleRes = R.string.edit_item_title
    const val entryIdArg = "entryId"
    val routeWithArgs = "$routeName/{$entryIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryEditViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(EntryEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryInputBody(
            entryUiState = viewModel.entryUiState,
            onEntryValueChange = { },
            onSaveClick = { },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EntryEditScreenPreview() {
    EntryEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
}