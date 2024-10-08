package com.sergio.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.sergio.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Layout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Layout(modifier: Modifier = Modifier) {
    var oldValue    : String by remember { mutableStateOf("0") }
    var valueScreen : String by remember { mutableStateOf("0") }
    var operator    : String by remember { mutableStateOf("") }
    var isNewValue  : Boolean by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Row(
            Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.White),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text     = valueScreen,
                color    = Color.Black,
                modifier = Modifier.padding(end = 10.dp)
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 50.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("7", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("7")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("8", valueScreen, isNewValue)
                        isNewValue  = false
                    }) {
                    Text("8")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("9", valueScreen, isNewValue)
                        isNewValue  = false
                    }) {
                    Text("9")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        operator   = "/"
                        oldValue   = valueScreen
                        isNewValue = true
                }) {
                    Text("/")
                }
            }
            Row {
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("4", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("4")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("5", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("5")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("6", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("6")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        operator   = "x"
                        oldValue   = valueScreen
                        isNewValue = true
                }) {
                    Text("x")
                }
            }
            Row {
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("1", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("1")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("2", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("2")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("3", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("3")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        operator   = "-"
                        oldValue   = valueScreen
                        isNewValue = true
                }) {
                    Text("-")
                }
            }
            Row {
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue("0", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text("0")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = addValue(".", valueScreen, isNewValue)
                        isNewValue  = false
                }) {
                    Text(".")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        valueScreen = performAction(operator, oldValue, valueScreen)
                        oldValue    = valueScreen
                        isNewValue  = true
                }) {
                    Text("=")
                }
                Button(
                    shape  = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor   = Color.White
                    ),
                    onClick = {
                        operator   = "+"
                        oldValue   = valueScreen
                        isNewValue = true
                }) {
                    Text("+")
                }
            }
        }
    }
}

fun addValue(newNumber: String, currentValue: String, isNewValue: Boolean): String {
    return if (isNewValue || currentValue == "0")
        newNumber
    else
        currentValue + newNumber
}

fun performAction(action: String, oldValue: String, newValue: String): String {
    try {
        val oldNumber = oldValue.toDouble()
        val newNumber = newValue.toDouble()
        var result = 0.0

        when (action) {
            "+" -> result = oldNumber + newNumber
            "-" -> result = oldNumber - newNumber
            "/" -> result = oldNumber / newNumber
            "x" -> result = oldNumber * newNumber
        }

        return result.toString()
    } catch (e: Exception) {
        return "ERROR"
    }
}