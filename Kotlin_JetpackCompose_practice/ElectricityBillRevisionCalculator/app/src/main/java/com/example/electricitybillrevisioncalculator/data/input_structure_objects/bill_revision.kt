package com.example.electricitybillrevisioncalculator.data.input_structure_objects

object bill_revision {
    // input
    var sanction_load: String = "";
    var demand: String = "";
    var last_ok_bill_amount_pending: String = ""
    var last_ok_bill_reading: String = ""
    var current_bill_reading: String = ""
    var error_start_date: String = ""
    var error_end_date: String = ""
    var new_start_date: String = ""
    var new_end_date: String = ""
//    var already_paid_in_advance: String = ""
//    var meter_cost:String = ""

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