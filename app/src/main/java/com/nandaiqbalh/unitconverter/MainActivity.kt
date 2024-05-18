package com.nandaiqbalh.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			UnitConverterTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					UnitConverter()
				}
			}
		}
	}
}


@Composable
fun UnitConverter() {

	var inputValue by remember { mutableStateOf("") }
	var outputValue by remember { mutableStateOf("") }
	var inputUnit by remember { mutableStateOf("Meters") }
	var outputUnit by remember { mutableStateOf("Meters") }
	var iExpanded = remember { mutableStateOf(false) }
	var oExpanded = remember { mutableStateOf(false) }
	var conversionFactor = remember { mutableStateOf(1.00) }
	var oconversionFactor = remember { mutableStateOf(1.00) }

	fun convertUnits() {
		val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

		val result =
			(inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0

		outputValue = result.toString()
	}

	val customTextStyle = TextStyle(
		color = Color.Blue, // Custom color
		fontSize = 32.sp,           // Custom font size
		fontWeight = FontWeight.Bold, // Custom font weight
		fontFamily = FontFamily.SansSerif
	)

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		Text(text = "Unit Converter", style = customTextStyle)
		Spacer(modifier = Modifier.height(16.dp))
		OutlinedTextField(
			value = inputValue,
			onValueChange = {
				inputValue = it
				convertUnits()
			},
			label = { Text(text = "Enter Value") }
		)

		Spacer(modifier = Modifier.height(16.dp))

		Row {

			// input box
			Box {
				// input button
				Button(onClick = { iExpanded.value = true }) {
					Text(text = inputUnit)
					Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
				}

				DropdownMenu(
					expanded = iExpanded.value,
					onDismissRequest = { iExpanded.value = false }) {
					DropdownMenuItem(
						text = { Text(text = "Centimeters") },
						onClick = {
							iExpanded.value = false
							inputUnit = "Centimeters"
							conversionFactor.value = 0.01
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Meters") },
						onClick = {
							iExpanded.value = false
							inputUnit = "Meters"
							conversionFactor.value = 1.0
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Feet") },
						onClick = {
							iExpanded.value = false
							inputUnit = "Feet"
							conversionFactor.value = 0.3048
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Milimeters") },
						onClick = {
							iExpanded.value = false
							inputUnit = "Milimeters"
							conversionFactor.value = 0.001
							convertUnits()
						})
				}
			}

			Spacer(modifier = Modifier.width(16.dp))

			// output box
			Box {
				// output button
				Button(onClick = { oExpanded.value = true }) {
					Text(text = outputUnit)
					Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
				}
				DropdownMenu(
					expanded = oExpanded.value,
					onDismissRequest = { oExpanded.value = false }) {
					DropdownMenuItem(
						text = { Text(text = "Centimeters") },
						onClick = {
							oExpanded.value = false
							outputUnit = "Centimeters"
							oconversionFactor.value = 0.01
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Meters") },
						onClick = {
							oExpanded.value = false
							outputUnit = "Meters"
							oconversionFactor.value = 1.0
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Feet") },
						onClick = {
							oExpanded.value = false
							outputUnit = "Feet"
							oconversionFactor.value = 0.3048
							convertUnits()
						})
					DropdownMenuItem(
						text = { Text(text = "Milimeters") },
						onClick = {
							oExpanded.value = false
							outputUnit = "Milimeters"
							oconversionFactor.value = 0.001
							convertUnits()
						})
				}
			}
		}
		Spacer(modifier = Modifier.height(16.dp))

		Text(text = "Result: ${outputValue} ${outputUnit}", style = MaterialTheme.typography.headlineMedium)

	}
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
	UnitConverter()
}
