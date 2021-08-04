package br.com.marco.appbusinesscard.ui


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.marco.appbusinesscard.App
import br.com.marco.appbusinesscard.R
import br.com.marco.appbusinesscard.data.BusinessCard
import br.com.marco.appbusinesscard.databinding.ActivityAddBusinessCardBinding
import kotlinx.android.synthetic.main.activity_add_business_card.*


class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityAddBusinessCardBinding.inflate(layoutInflater)}

    private var spinnerItemSenioridade = ""
    private var colorBackground = ""
    private var contagemCheckBox = 0
    val listaHabilidades = mutableListOf<Int>()

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        btnConfirm.isEnabled = false
        insertListeners()


        sp_senioridade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerItemSenioridade = parent?.getItemAtPosition(position).toString()
                sp_senioridade.hideKeyboard()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinnerItemSenioridade = parent?.getItemAtPosition(0).toString()
                sp_senioridade.hideKeyboard()
            }
        }

        sp_cor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                colorBackground = backgroundColor(position)
                binding.layoutAddBusinessCard.setBackgroundColor(Color.parseColor(colorBackground))
                sp_cor.hideKeyboard()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                colorBackground = backgroundColor(0)
                binding.layoutAddBusinessCard.setBackgroundColor(Color.parseColor(colorBackground))
                sp_cor.hideKeyboard()
            }
        }
        cb_java.setOnCheckedChangeListener{ _, isChecked ->
            when(isChecked){
                true -> contagemCheckBox++
                false -> contagemCheckBox--
            }
            Toast.makeText(this,isChecked.toString() + contagemCheckBox,Toast.LENGTH_SHORT).show()
            verificaDados()
        }
        cb_csharp.setOnCheckedChangeListener{ _, isChecked ->
            when(isChecked){
                true -> contagemCheckBox++
                false -> contagemCheckBox--
            }
            Toast.makeText(this,isChecked.toString() + contagemCheckBox,Toast.LENGTH_SHORT).show()
            verificaDados()
        }
        cb_kotlin.setOnCheckedChangeListener{ _, isChecked ->
            when(isChecked){
                true -> contagemCheckBox++
                false -> contagemCheckBox--
            }
            Toast.makeText(this,isChecked.toString() + contagemCheckBox,Toast.LENGTH_SHORT).show()
            verificaDados()
        }
        cb_sql.setOnCheckedChangeListener{ _, isChecked ->
            when(isChecked){
                true -> contagemCheckBox++
                false -> contagemCheckBox--
            }
            Toast.makeText(this,isChecked.toString() + contagemCheckBox,Toast.LENGTH_SHORT).show()
            verificaDados()
        }


    }


    //criar todos os listeners
    private fun insertListeners(){
        binding.btnClose.setOnClickListener {
           finish()
        }

        binding.btnConfirm.setOnClickListener {
            addHabilidades()
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                especialidade = binding.tilEspecialidade.editText?.text.toString(),
                senioridade = spinnerItemSenioridade,
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = colorBackground,
                habilidade1 = listaHabilidades[0],
                habilidade2 = listaHabilidades[1]
            )
            mainViewModel.insert(businessCard)
            finish()
        }
    }

    private fun backgroundColor(index: Int): String {
        return when(index){
            0 -> "#F7C40F"
            1 -> "#49C5EB"
            2 -> "#FFFFFF"
            3 -> "#32D627"
            4 -> "#FC3D3D"
            5 -> "#FF80F2"
            else -> "#F7C40F"
        }
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun verificaDados(){
        btnConfirm.isEnabled = contagemCheckBox == 2
    }
    private fun addHabilidades(){
        if(cb_java.isChecked){
            listaHabilidades.add(0)
        }
        if(cb_kotlin.isChecked){
            listaHabilidades.add(1)
        }
        if(cb_csharp.isChecked){
            listaHabilidades.add(2)
        }
        if(cb_sql.isChecked){
            listaHabilidades.add(3)
        }
    }
}