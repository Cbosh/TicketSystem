package com.mrbreak.ticketsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class PaymentFragment : Fragment() {
    private lateinit var edtOriginalPrice: EditText
    private lateinit var edtPaymentPrice: EditText
    private lateinit var btnPay: Button
    private lateinit var txtLeftOverAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtOriginalPrice = view.findViewById(R.id.edt_original_amount)
        edtPaymentPrice = view.findViewById(R.id.edt_payment_amount)
        btnPay = view.findViewById(R.id.btn_pay)
        txtLeftOverAmount = view.findViewById(R.id.txt_left_over_amount)
    }

    override fun onResume() {
        super.onResume()
        btnPay.setOnClickListener {
            val originalPrice : String =  edtOriginalPrice.text.toString()
            val paymentPrice : String =  edtPaymentPrice.text.toString()
            if(originalPrice.isNotEmpty() && paymentPrice.isNotEmpty()){
                txtLeftOverAmount.text = (calculateLeftOverAmount(
                    originalPrice.toDouble(),
                    paymentPrice.toDouble()
                ))
            }
        }
    }

    private fun calculateLeftOverAmount(originalAmount: Double, paymentAmount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale(getString(R.string.language),
            getString(R.string.country))).format(originalAmount - paymentAmount)
    }
}