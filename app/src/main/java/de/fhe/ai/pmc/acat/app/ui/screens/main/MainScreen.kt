package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.domain.User
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val vm = getViewModel<MainScreenViewModel>()
    val users by remember(vm) { vm.getUsers() }.collectAsState(listOf())

    Column(modifier = modifier.background( MaterialTheme.colors.primaryVariant )) {
        UserList(users, modifier)
    }
}

@Composable
fun UserList( users: List<User>, modifier: Modifier = Modifier ) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState, modifier = modifier) {
        items(users) {
            UserRow(it, modifier = modifier)
        }
    }
}

@Composable
fun UserRow( user: User, modifier: Modifier = Modifier ) {
    Row(verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {
                // Do something
            })
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text( user.name )
    }
}

/*
    Previews start here
 */

@Preview( showBackground = true )
@Composable
fun PreviewUserList(
    @PreviewParameter(UserListPreviewParameterProvider::class) users: List<User>
) {
    UserList( users )
}

@Preview( showBackground = true )
@Composable
fun PreviewUserRow(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 3) user: User
) {
    UserRow( user )
}

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values = userList.asSequence()
}

class UserListPreviewParameterProvider : PreviewParameterProvider<List<User>> {
    override val values = sequenceOf( userList )
}

private val userList = listOf(
    User("Max"),
    User("User with a really really very unbelievable long first name"),
    User("Siegfried"),
    User("Tom"),
    User("Another first name"),
    User("Well, yes another one"),
    User("Middle long first name"),
)