package riva.ticona.calcularigv

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CalcularigvActivity : AppCompatActivity() {

    private lateinit var etMonto: EditText
    private lateinit var etmostrar: TextView
    private lateinit var etsubtot: TextView
    private lateinit var ettotal: TextView
    private lateinit var btnCalcular: Button
    private lateinit var listView: ListView
    private val calculoList = mutableListOf<CalculoIGV>()
    private lateinit var adapter: CalculoIGVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcularigv)

        etMonto = findViewById(R.id.etMonto)
        etmostrar = findViewById(R.id.etmostrar)
        etsubtot = findViewById(R.id.etsubtot)
        ettotal = findViewById(R.id.ettotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        listView = findViewById(R.id.listView)

        adapter = CalculoIGVAdapter(this, calculoList)
        listView.adapter = adapter

        btnCalcular.setOnClickListener {
            calcularIGV()
        }
    }

    private fun calcularIGV() {
        val montoStr = etMonto.text.toString()

        if (TextUtils.isEmpty(montoStr)) {
            Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val monto = montoStr.toDouble()
            val igv = monto * 0.18
            val subtotal = monto - igv
            val total = monto

            etmostrar.text = String.format("%.2f", igv)
            etsubtot.text = String.format("%.2f", subtotal)
            ettotal.text = String.format("%.2f", total)

            val calculoIGV = CalculoIGV(monto, igv, subtotal)
            calculoList.add(calculoIGV)
            adapter.notifyDataSetChanged()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
        }
    }
}

