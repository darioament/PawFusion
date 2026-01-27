package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import fina.dario.pawfusion.core.BreedsListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Preview(showBackground = true, name ="Error Dialog")
@Composable
internal fun ErrorDialog(
    modifier: Modifier = Modifier,
    error: String,
    onDismiss: () -> Unit
){


    Dialog(
        onDismissRequest = onDismiss,
    ){
        Column(
            modifier.fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
        ){
            Box(
                modifier.fillMaxSize()
                    .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = error,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    minLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }


    }

}