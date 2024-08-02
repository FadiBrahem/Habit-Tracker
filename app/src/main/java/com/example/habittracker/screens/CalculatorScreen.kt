package com.example.habittracker.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.CalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorUI(
    viewModel: CalculatorViewModel,
    onBackClick: () -> Unit
) {
    val expression = viewModel.expression
    val buttonSpacing = 8.dp
    Log.d("CalculatorUI", "Current Expression: ${expression.value}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                    reverseLayout = true
                ) {
                    item {
                        Text(
                            text = expression.value,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp, horizontal = 8.dp),
                            fontWeight = FontWeight.Light,
                            fontSize = 80.sp,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color = Color.Red,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.clear()
                            }
                    )
                    CalculatorButton(
                        symbol = "(",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("(")
                            }
                    )
                    CalculatorButton(
                        symbol = ")",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append(")")
                            }
                    )
                    CalculatorButton(
                        symbol = "÷",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("÷")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("7")
                            }
                    )
                    CalculatorButton(
                        symbol = "8",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("8")
                            }
                    )
                    CalculatorButton(
                        symbol = "9",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("9")
                            }
                    )
                    CalculatorButton(
                        symbol = "×",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("×")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("4")
                            }
                    )
                    CalculatorButton(
                        symbol = "5",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("5")
                            }
                    )
                    CalculatorButton(
                        symbol = "6",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("6")
                            }
                    )
                    CalculatorButton(
                        symbol = "-",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("-")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("1")
                            }
                    )
                    CalculatorButton(
                        symbol = "2",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("2")
                            }
                    )
                    CalculatorButton(
                        symbol = "3",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("3")
                            }
                    )
                    CalculatorButton(
                        symbol = "+",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("+")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("0")
                            }
                    )
                    CalculatorButton(
                        symbol = ".",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append(".")
                            }
                    )
                    CalculatorButton(
                        symbol = "Del",
                        color = Color.Red,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.delete()
                            }
                    )
                    CalculatorButton(
                        symbol = "=",
                        color = Color.Blue,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.evaluate()
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color)
            .padding(16.dp)
    ) {
        Text(
            text = symbol,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
