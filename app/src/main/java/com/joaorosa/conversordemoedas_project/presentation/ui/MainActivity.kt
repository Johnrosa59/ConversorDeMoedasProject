package com.joaorosa.conversordemoedas_project.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.joaorosa.conversordemoedas_project.databinding.ActivityMainBinding
import com.joaorosa.conversordemoedas_project.presentation.viewmodel.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: ExchangeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configureSpinner()
        configureButtonConvert()
        observeData()
        configureClearFunction()
    }

    private fun configureClearFunction() {
        binding.btnClear.setOnClickListener {
            binding.convertEditTextTop.text?.clear()
            binding.convertedEditTextBottom.text?.clear()
        }
    }

    private fun observeData() {
        viewModel.resultConversion.observe(this) { result ->
            binding.convertedEditTextBottom.setText(
                String.format(Locale.getDefault(), "%.2f", result)
            )
        }

        viewModel.error.observe(this) { mensagemErro ->
            Toast.makeText(this, mensagemErro, Toast.LENGTH_LONG).show()
        }
    }

    private fun SpinnerConversionChoose() {
        val valorTexto = binding.convertEditTextTop.text.toString().trim()
        val valueTyped = valorTexto.toDoubleOrNull()

        if (valueTyped != null) {
            val selectedCoin = binding.spinner.selectedItem.toString()
            viewModel.calcConversion(valueTyped, selectedCoin)
        }
    }

    private fun configureButtonConvert() {
        binding.btnConvert.setOnClickListener {
            val valorTexto = binding.convertEditTextTop.text.toString().trim()

            if (valorTexto.isEmpty()) {
                Toast.makeText(this, "Digite um valor para converter", Toast.LENGTH_SHORT).show()
            } else {
                SpinnerConversionChoose()
            }
        }
    }

    private fun configureSpinner() {
        val coins = listOf("Dólar", "Euro", "Dólar Canadense", "Peso Argentino") // Mantido 'coins'

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, coins)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                SpinnerConversionChoose()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}