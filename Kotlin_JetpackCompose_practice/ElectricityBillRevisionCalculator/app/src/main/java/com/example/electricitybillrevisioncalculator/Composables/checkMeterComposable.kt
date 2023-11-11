package com.example.electricitybillrevisioncalculator.Composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.current_bill_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.last_ok_bill_amount_pending
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.last_ok_bill_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.new_end_date
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.new_end_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.new_start_date
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.new_start_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.old_end_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.old_start_date
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.old_start_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.sanction_load
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun checkMeterComposable(
    modifier: Modifier
){
    var sanctionLoad = rememberSaveable {
        mutableStateOf("")
    }
    var demand = rememberSaveable {
        mutableStateOf("")
    }
    var pendingOkAmount = rememberSaveable {
        mutableStateOf("")
    }
    var lastOkBillReading = rememberSaveable {
        mutableStateOf("")
    }
    var currentBillReading = rememberSaveable {
        mutableStateOf("")
    }
    var newStartReading = rememberSaveable {
        mutableStateOf("")
    }
    var oldStartReading = rememberSaveable {
        mutableStateOf("")
    }
    var newEndReading = rememberSaveable {
        mutableStateOf("")
    }
    var oldEndReading = rememberSaveable {
        mutableStateOf("")
    }
    var newStartDate = rememberSaveable {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate_newStartDate = remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd-MM-yyyy")
                .format(newStartDate.value)
        }
    }
    var newEndDate = rememberSaveable {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate_newEndDate = remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd-MM-yyyy")
                .format(newEndDate.value)
        }
    }
    var oldStartDate = rememberSaveable {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate_oldStartDate = remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd-MM-yyyy")
                .format(oldStartDate.value)
        }
    }

    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = "* All fields are required",
            color = Color.Red,
            fontSize = 15.sp
        )
        doubleTextField(
            modifier = Modifier,
            str1 = "Sanction Load in old meter",
            str2 = "Demand in new Meter",
            suffix = "KW",
            state1 = sanctionLoad,
            state2 = demand
        )

        pendingAmountTextField(
            modifier = Modifier,
            state1 = pendingOkAmount
        )

        doubleTextField(
            modifier = Modifier,
            str1 = "Last correct bill reading (Old)",
            str2 = "Current bill reading (New meter)",
            suffix = "units",
            state1 = lastOkBillReading,
            state2 = currentBillReading
        )

        doubleTextField(
            modifier = Modifier,
            str1 = "Initial reading (New meter)",
            str2 = "Initial reading (Old meter)",
            suffix = "units",
            state1 = newStartReading,
            state2 = oldStartReading
        )

        doubleTextField(
            modifier = Modifier,
            str1 = "Final reading (New meter)",
            str2 = "Final reading (Old meter)",
            suffix = "units",
            state1 = newEndReading,
            state2 = oldEndReading
        )

        doubleDates(
            modifier = Modifier,
            str1 = "Installed Date (New meter)",
            str2 = "Final Date (New meter)",
            state1 = newStartDate,
            state2 = newEndDate,
            dateformat1 = formattedDate_newStartDate,
            dateformat2 = formattedDate_newEndDate
        )

        datePicker(
            modifier = Modifier.fillMaxWidth(),
            str1 = "Start data for error (old meter)",
            state1 = oldStartDate,
            dateformat1 = formattedDate_oldStartDate
        )

        val context = LocalContext.current
        var isEnabled  = sanctionLoad.value != "" &&
                demand.value != "" &&
                lastOkBillReading.value != "" &&
                currentBillReading.value != "" &&
                newStartReading.value != "" &&
                oldStartReading.value != "" &&
                newEndReading.value != "" &&
                oldEndReading.value != "" &&
                !newStartDate.value.equals("") &&
                !oldStartDate.value.equals("") &&
                !newEndDate.value.equals("")

        Button(
            onClick = {
                if(!isEnabled)
                {
                    Toast.makeText(
                        context,
                        "Please enter all fields.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    sanction_load = sanctionLoad.value
                    check_meter.demand = demand.value
                    last_ok_bill_amount_pending = pendingOkAmount.value
                    last_ok_bill_reading = lastOkBillReading.value
                    current_bill_reading = currentBillReading.value
                    new_start_reading = newStartReading.value
                    old_start_reading = oldStartReading.value
                    new_end_reading = newEndReading.value
                    old_end_reading = oldEndReading.value
                    new_start_date = formattedDate_newStartDate.value
                    new_end_date = formattedDate_newEndDate.value
                    old_start_date = formattedDate_oldStartDate.value
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            enabled = isEnabled
        ) {
            Text(text = "Check Meter")

        }
    }
}