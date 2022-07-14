package com.example.userlogindemo.Adapter

import android.R
import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.userlogindemo.Report.Data
import com.example.userlogindemo.databinding.ItemReportBinding
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.item_report.view.*


class ReportAdapter : RecyclerView.Adapter<ReportAdapter.ReportHolder> {

 var reportList: ArrayList<Data> = ArrayList()
    lateinit var context: Context
    var ll: LinearLayout? = null

    var checkId:Int=0


    constructor()
    constructor(context: Context, reportList: ArrayList<Data>) {
        this.context = context
        this.reportList = reportList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportHolder, position: Int) {
        with(holder) {
            with(reportList[position]) {



            //    for (index in reportList.indices) {
                    val chip = Chip(context)
                    chip.text= name
                    chip.id = id

                    // necessary to get single selection working
                    chip.isCheckable = true
                //    chip.isClickable = true
                    chip.isCloseIconVisible = false
//                    binding.chipGroup1.addView(chip)
                    binding.chipGroup1.addView(chip);



                var lastCheckedId = View.NO_ID
                d("aaaaaaa","View no id  -----> ${View.NO_ID}")
                binding.chipGroup1.setOnCheckedChangeListener { chipGroup, checkedId ->
                    d("aaaaaaa","checkedid--------------> $checkedId")
                    d("aaaaaaa","chip id----------> ${chip.id}")
                    d("aaaaaaa","--id---------> ${id}")
                    d("aaaa", "Chip group children count: "+chipGroup.childCount.toString())

                       val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
                        Toast.makeText(chipGroup.context, titleOrNull ?: "No Choice", Toast.LENGTH_LONG).show()
                        chip.isCheckable = true

                }




                d("radioButton", "------radioid data class -->${id}")

            }
        }
    }
    private fun createTagChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(R.color.background_dark)
            isCloseIconVisible = true
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }

    }


    override fun getItemCount(): Int {
        d("repots", "------getcout-->${reportList.size}")

        return reportList.size

    }

    class ReportHolder(val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root){

    }

}