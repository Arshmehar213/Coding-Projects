package com.example.aiva

import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    val messegeList by lazy {
        mutableStateListOf<MessegeModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = constants.ApiKey
    )
    @RequiresApi(35)
    fun sendMessege(question : String){
        viewModelScope.launch {
           try {
               val chat = generativeModel.startChat(
                   history = messegeList.map {
                       content(it.Role){
                           text(it.Messege)
                       }
                   }.toList()
               )
               messegeList.add(MessegeModel(question , "user"))
               messegeList.add(MessegeModel("Generating..." ,"model" ))
               val response = chat.sendMessage(question)
               messegeList.removeLast()
               messegeList.add(MessegeModel(response.text.toString() , "model"))
           }catch (e : Exception){
               Log.e("geminiError" , e.toString())
           }
        }
    }

}