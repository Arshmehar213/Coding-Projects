package com.example.aiva

import android.graphics.Paint.Style
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiva.ui.theme.ModelColor
import com.example.aiva.ui.theme.UserModelColor

@RequiresApi(35)
@Composable
fun ChatPage(modifier: Modifier = Modifier , viewModel: ChatViewModel) {
    Column(modifier = modifier) {
       header(modifier = Modifier
           .fillMaxWidth()
           .background(UserModelColor))
       messageList(modifier = Modifier
           .weight(1f)
           .fillMaxSize(), messegeList = viewModel.messegeList)
       inputField(onMessegeSend = {
             viewModel.sendMessege(it)
       }
           , modifier = Modifier
               .padding(8.dp)
       )
    }
    
}

@Composable
fun messageList(modifier: Modifier ,messegeList : List<MessegeModel>) {
           LazyColumn(
               modifier = modifier,
               reverseLayout = true
           ) {
               items(messegeList.reversed()) {
                   MessegesRow(messegeModel = it)
               }
           }

}
@Composable
fun MessegesRow(messegeModel: MessegeModel){
    val ismodel = messegeModel.Role=="model"

    Row (
            verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier
                .align(if (ismodel) Alignment.BottomStart else Alignment.BottomEnd)
                .padding(
                    start = if (ismodel) 8.dp else 70.dp,
                    end = if (ismodel) 70.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .clip(RoundedCornerShape(74f))
                .background(
                    if (ismodel) ModelColor else UserModelColor
                )
                .padding(16.dp)
            ){
                SelectionContainer {
                    Text(text = messegeModel.Messege,
                        fontWeight = FontWeight.W500,
                        color = if (ismodel) Color.Black else Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun inputField(onMessegeSend : (String) -> Unit , modifier: Modifier){
    var messege by remember {
        mutableStateOf("")
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically

    ) {

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(6.dp)
            ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = UserModelColor,
                unfocusedBorderColor = ModelColor,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(32.dp),
            placeholder = { Text(text = "Ask Anything")},
            value = messege,
           onValueChange = {
                messege = it
            }
        )
        IconButton(onClick = {
            if (messege.isNotEmpty()){
                onMessegeSend(messege)
                messege = ""
            }
        }) {
            Icon(imageVector = Icons.Default.Send,
                contentDescription = "Send",
                modifier = Modifier
                    .size(48.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape
                    )
                    .background(
                        color = ModelColor,
                        shape = CircleShape
                    )
                    .padding(8.dp),
                tint = UserModelColor,
               )
        }
    }
}

@Composable
fun header(modifier: Modifier){
    Box(modifier = modifier
    )
    {
        Row(
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "" ,
                tint = Color.White ,
                modifier = Modifier.padding(9.dp)
                )

            Text(text = "AIVA BOT",
                modifier = Modifier.padding(9.dp),
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontFamily = FontFamily.Serif
            )
        }
    }
}
