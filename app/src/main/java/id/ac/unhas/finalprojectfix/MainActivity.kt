package id.ac.unhas.finalprojectfix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var tambah: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tambah = findViewById(R.id.btn_tambah)
        tambah.setOnClickListener {
            this.startActivity(Intent(this, TambahActivity::class.java))
        }
    }
}