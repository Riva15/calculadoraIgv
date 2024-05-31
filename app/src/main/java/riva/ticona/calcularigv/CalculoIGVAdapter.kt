package riva.ticona.calcularigv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CalculoIGVAdapter(private val context: Context, private val dataSource: List<CalculoIGV>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.list_item_calculo_igv, parent, false)

        val montoTextView = rowView.findViewById<TextView>(R.id.monto)
        val igvTextView = rowView.findViewById<TextView>(R.id.igv)
        val subtotalTextView = rowView.findViewById<TextView>(R.id.subtotal)

        val calculoIGV = getItem(position) as CalculoIGV

        montoTextView.text = String.format("%.2f", calculoIGV.monto)
        igvTextView.text = String.format("%.2f", calculoIGV.igv)
        subtotalTextView.text = String.format("%.2f", calculoIGV.subtotal)

        return rowView
    }
}
