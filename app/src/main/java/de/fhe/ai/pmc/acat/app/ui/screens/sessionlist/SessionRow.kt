package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import android.content.Context
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fhe.ai.pmc.acat.app.R
import de.fhe.ai.pmc.acat.app.ui.screens.util.PREVIEW_BACKGROUND_COLOR
import de.fhe.ai.pmc.acat.app.ui.screens.util.SessionPreviewParameterProvider
import de.fhe.ai.pmc.acat.app.ui.theme.lightRed
import de.fhe.ai.pmc.acat.app.ui.theme.redBackground
import de.fhe.ai.pmc.acat.domain.Session
import java.time.format.DateTimeFormatter

@Composable
fun SessionRow(
    session: Session,
    modifier: Modifier = Modifier,
    onItemPressed: ( itemId: String ) -> Unit,
    vm: SessionListScreenViewModel
) {
    val context = LocalContext.current
    val shape = RoundedCornerShape(30.dp)
    var backgroundColor = MaterialTheme.colors.surface

    if(session.infected) {
        backgroundColor = MaterialTheme.colors.redBackground
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(6.dp)
            .shadow(elevation = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemPressed(session.id) }
            .background(backgroundColor)
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
                        .fillMaxWidth(0.85f))

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

        if(!session.infected) {
            Button(modifier = modifier
                .align(Alignment.CenterVertically)
                .width(50.dp), shape = shape, onClick = {
                vm.markAsInfected(context, session.id);

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_coronavirus_24),
                    "mark as infected"
                )
            }
        }
    }
}