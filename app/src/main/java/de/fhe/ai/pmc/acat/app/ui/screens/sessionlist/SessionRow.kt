package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import de.fhe.ai.pmc.acat.app.ui.screens.util.PREVIEW_BACKGROUND_COLOR
import de.fhe.ai.pmc.acat.app.ui.screens.util.SessionPreviewParameterProvider
import de.fhe.ai.pmc.acat.domain.Session
import java.time.format.DateTimeFormatter

@Composable
fun SessionRow(
    session: Session,
    modifier: Modifier = Modifier,
    onItemPressed: ( itemId: String ) -> Unit
) {
    val shape = RoundedCornerShape(30.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(6.dp)
            .shadow(elevation = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemPressed(session.id) }
            .background(MaterialTheme.colors.surface)
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row() {
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    session.room?.name ?: "",
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.8f))

            }
            Row {
                val startFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy   HH:mm")
                val endFormatter = DateTimeFormatter.ofPattern("HH:mm")

                Spacer(modifier = modifier.width(20.dp))
                Row {
                    Text(text = session.startTime.format(startFormatter))
                    Text(text = " - ")
                    Text(text = session.endTime?.format(endFormatter) ?: "Running")
                }
            }
        }

        Button(modifier = modifier
            .align(Alignment.CenterVertically)
            .width(80.dp)
            , shape = shape, onClick = {

        }) {
            Text("Inf")
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND_COLOR
)
@Composable
fun PreviewSessionRow(
    @PreviewParameter(SessionPreviewParameterProvider::class, limit = 3) session: Session
) {
    SessionRow( session ) {}
}
