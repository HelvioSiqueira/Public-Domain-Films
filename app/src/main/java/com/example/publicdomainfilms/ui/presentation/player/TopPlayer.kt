package com.example.publicdomainfilms.ui.presentation.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.publicdomainfilms.R
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme

@Composable
fun TopPlayer(modifier: Modifier = Modifier, title: () -> String, onBackClicked: () -> Unit) {
    val videoTitle = remember(title()) { title() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier.padding(end = 4.dp),
            onClick = {
                onBackClicked()
            }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                tint = MaterialTheme.colorScheme.surface,
                contentDescription = null
            )
        }
        Text(
            text = videoTitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400
            )
        )
    }
}

@Preview
@Composable
private fun TopPlayerPreview() {

    fun mock(): String = "Frankenstein"

    PublicDomainFilmsTheme {
        TopPlayer(title = ::mock){}
    }
}