package com.roberto.deportes50

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var metros: EditText
    private lateinit var pies: RadioButton
    private lateinit var pulgadas: RadioButton
    private lateinit var yardas: RadioButton
    private lateinit var resultado: TextView
    private lateinit var opciones: RadioGroup

    private lateinit var obj: Convertidor
    private val formatoDecimales: DecimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        metros = findViewById(R.id.edtMetros)
        pies = findViewById(R.id.rbtPies)
        pulgadas = findViewById(R.id.rbtPulgadas)
        yardas = findViewById(R.id.rbtYardas)
        resultado = findViewById(R.id.txtResultado)
        opciones = findViewById(R.id.rgpOpciones)

        obj = Convertidor()
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.ibtnConvertir -> btnConvertir()
            R.id.ibtnMostrar -> btnMostrar()
            R.id.ibtnLimpiar -> btnLimpiar()
        }
    }

    fun btnConvertir() {
        if (metros.text.isNotEmpty()) {
            obj.meter = metros.text.toString().toInt()
            if (pies.isChecked) obj.calculateFeet()
            if (pulgadas.isChecked) obj.calculateInch()
            if (yardas.isChecked) obj.calculateYard()
            Toast.makeText(applicationContext, "Metros convertidos.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Ingresa cantidad en metros.", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnMostrar() {
        val res = "Cantidad de metros convertida a: \n" +
                "Pies: ${formatoDecimales.format(obj.feet)}\n" +
                "Pulgadas: ${formatoDecimales.format(obj.inch)}\n" +
                "Yardas: ${formatoDecimales.format(obj.yard)}"
        resultado.text = res
    }

    fun btnLimpiar() {
        metros.text = null
        resultado.text = "Cantidad de metros convertida a:"
        opciones.clearCheck()
        metros.requestFocus()
        obj = Convertidor()
    }
}

class Convertidor {
    var meter: Int = 0
    var feet: Double = 0.0
    var inch: Double = 0.0
    var yard: Double = 0.0

    fun calculateFeet() {
        if (meter > 0) {
            feet = meter * 3.2808
        }
    }

    fun calculateInch() {
        if (meter > 0) {
            inch = meter * 39.3701
        }
    }

    fun calculateYard() {
        if (meter > 0) {
            yard = meter * 1.09361
        }
    }
}
