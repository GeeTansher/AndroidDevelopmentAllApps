package com.example.electricitybillrevisioncalculator.Composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun doubleDates(
    modifier: Modifier,
    str1: String,
    str2: String,
    state1: MutableState<LocalDate>,
    state2: MutableState<LocalDate>,
    dateformat1: State<String>,
    dateformat2: State<String>

){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        datePicker(
            modifier = modifier.weight(1f),
            str1 = str1,
            state1 = state1,
            dateformat1 = dateformat1
        )

        datePicker(
            modifier = modifier.weight(1f),
            str1 = str2,
            state1 = state2,
            dateformat1 = dateformat2
        )
    }

}

@Composable
fun pendingAmountTextField(
    modifier: Modifier,
    state1: MutableState<String>
){
    TextField(
        value = state1.value,
        onValueChange = {
            state1.value=it
            check_meter.last_ok_bill_amount_pending = it
        },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Pending amount till last correct bill")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.CurrencyRupee,
                contentDescription = "Rupee"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        maxLines = 1
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun doubleTextField(
    modifier: Modifier,
    str1: String,
    str2: String,
    suffix: String,
    state1: MutableState<String>,
    state2: MutableState<String>
){
    Row (
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ){
        TextField(
            value = state1.value,
            onValueChange = {
                state1.value=it
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Right
            ),
            label = {
                Text(text = str1)
            },
            modifier = Modifier
                .weight(1f),
            suffix = {
                Text(text = suffix)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            maxLines = 1
        )
        TextField(
            value = state2.value,
            onValueChange = {
                state2.value = it
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Right
            ),
            label = {
                Text(text = str2)
            },
            modifier = Modifier
                .weight(1.2f),
            suffix = {
                Text(text = suffix)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            maxLines = 1
        )
    }
}

@Composable
fun datePicker(
    modifier: Modifier,
    str1: String,
    state1: MutableState<LocalDate>,
    dateformat1: State<String>
){

    val dateDialogState = rememberMaterialDialogState()

    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilledTonalButton(onClick = {
            dateDialogState.show()
        }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "pick date",
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = str1)
        }
        Text(text = dateformat1.value)
    }


    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    context,
                    "Date Selected",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date for $str1"
        ) {
            state1.value = it
        }
    }
}