package com.example.electricitybillrevisioncalculator.data.input_structure_objects

object check_meter {
    // input
    var sanction_load: String = "";
    var demand: String = "";
    var last_ok_bill_amount_pending: String = ""
    var last_ok_bill_reading: String = ""
    var current_bill_reading: String = ""
    var new_start_reading: String = ""
    var old_start_reading: String = ""
    var new_end_reading: String = ""
    var old_end_reading: String = ""
    var new_start_date: String = ""
    var new_end_date: String = ""
    var old_start_date: String = ""

    // output
    var EC:String = ""
    var ED:String = ""
    var FC:String = ""
    var extra_demand_penalty:String = ""
    var FC_plus_EDP:String = ""
    var total:String = ""
    var total_with_pending:String = ""
    var surcharge:String = ""
    var grandTotal:String = ""
}