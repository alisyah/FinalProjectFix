package id.ac.unhas.finalprojectfix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class TambahActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etnama : EditText
    private lateinit var etalamat : EditText
    private lateinit var rbbiasa : RadioButton
    private lateinit var rbmini : RadioButton
    private lateinit var cbkacang : CheckBox
    private lateinit var cbmeses : CheckBox
    private lateinit var cbselai : CheckBox
    private lateinit var btnsimpan : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        etnama = findViewById(R.id.et_nama)
        etalamat = findViewById(R.id.et_alamat)
        rbbiasa = findViewById(R.id.rb_biasa)
        rbmini = findViewById(R.id.rb_mini)
        cbkacang = findViewById(R.id.cb_kacang)
        cbmeses = findViewById(R.id.cb_meses)
        cbselai = findViewById(R.id.cb_selai)

        btnsimpan = findViewById(R.id.btn_simpan)
        btnsimpan.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        saveData()
    }

    private fun saveData() {
        var biasa : String = " "
        var mini : String = " "
        var kacang : String = " "
        var meses : String = " "
        var selai : String = " "

        val nama = etnama.toString().trim()
        val alamat = etalamat.toString().trim()

        if (rbbiasa.isChecked){
            biasa = rbbiasa.text.toString().trim()
        }else if(rbmini.isChecked) {
            mini = rbmini.text.toString().trim()
        }

        if(cbkacang.isChecked){
            kacang = cbkacang.text.toString().trim()
        }else{
            kacang = " "
        }

        if(cbmeses.isChecked){
            meses = cbmeses.text.toString().trim()
        }else{
            meses = " "
        }

        if(cbselai.isChecked){
            selai = cbselai.text.toString().trim()
        }else{
            selai = " "
        }

        if (nama.isEmpty()){
            etnama.error = "isi nama terlebih dahulu"
            return
        }

        if (alamat.isEmpty()){
            etalamat.error = "isi Alamat terlebih dahulu"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("pesanan")

        val pesanId = ref.push().key

        val dnt = pesanId?.let { Donat(it,nama,alamat,biasa+mini,kacang+" "+meses+" "+selai) }

        if (pesanId != null) {
            ref.child(pesanId).setValue(dnt).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
        clearText()
    }

    private fun clearText(){
        etnama.setText("")
        etalamat.setText("")
        rbbiasa.isChecked = false
        rbmini.isChecked = false
        cbkacang.isChecked = false
        cbmeses.isChecked = false
        cbselai.isChecked = false
    }
}

private fun Button.setOnClickListener(tambahActivity: TambahActivity) {

}




















