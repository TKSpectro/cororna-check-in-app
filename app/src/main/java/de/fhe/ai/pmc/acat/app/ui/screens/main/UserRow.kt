package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.R
import de.fhe.ai.pmc.acat.app.ui.screens.core.LocalNavCtrl
import de.fhe.ai.pmc.acat.app.ui.screens.core.ScreensEnum
import de.fhe.ai.pmc.acat.app.ui.screens.util.UserPreviewParameterProvider
import de.fhe.ai.pmc.acat.app.ui.screens.util.PREVIEW_BACKGROUND_COLOR
import de.fhe.ai.pmc.acat.domain.User

@Composable
fun UserRow(
    user: User,
    modifier: Modifier = Modifier,
    onItemPressed: ( itemId: Long ) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(6.dp)
            .shadow(elevation = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemPressed( user.id ) }
            .background(MaterialTheme.colors.surface)
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Row() {
            Box(
                modifier = modifier
                    .padding(6.dp)
                    .width(55.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_account_box_24),
                    contentDescription = "Avatar for ${user.name}",
                    modifier = modifier
                        .padding(6.dp)
                        .width(45.dp)
                        .height(45.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Text(
                user.name,
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.9f))
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .width(24.dp)
                    .height(24.dp)
            )
        }

    }

}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND_COLOR
)
@Composable
fun PreviewUserRow(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 3) user: User
) {
    UserRow( user ) {}
}
