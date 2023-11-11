package com.example.electricitybillrevisioncalculator

import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.current_bill_reading
import com.example.electricitybillrevisioncalculator.data.input_structure_objects.check_meter.demand
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

fun validate_check_meter(): Boolean{
    println(sanction_load)
    return sanction_load != ""&&
            old_start_date!= "" &&
            new_end_date != "" &&
            new_start_date != "" &&
            old_end_reading != "" &&
            new_end_reading != "" &&
            new_start_reading != "" &&
            old_start_reading != "" &&
            current_bill_reading != "" &&
            last_ok_bill_reading != "" &&
            last_ok_bill_amount_pending != "" &&
            demand != ""
}