package de.hshl.isd.tunesearchassignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun TrackRow(item: TrackViewModel) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.padding(top = 4.dp)) {
            Image(
                painter = rememberCoilPainter(item.image),
                contentDescription = stringResource(R.string.image_content_desc)
            )
            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = item.subtitle,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Divider(
            Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
    }
}
