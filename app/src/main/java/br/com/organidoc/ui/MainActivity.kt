package br.com.organidoc.ui

import android.os.Bundle
import android.widget.Adapter
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.organidoc.R
import br.com.organidoc.databinding.ActivityMainBinding
import br.com.organidoc.ui.adapter.GridAdapter
import java.io.File


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvListFile.layoutManager = GridLayoutManager(this, 2)
        binding.ibMenu.setOnClickListener {
            PopupMenu(this, binding.ibMenu).apply {
                menuInflater.inflate(R.menu.menu_sanduiche, this.menu)
                setOnMenuItemClickListener {
                    Toast.makeText(
                        this@MainActivity,
                        "Você entrou no ${it.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val path = intent.getStringExtra("path")
        val root = File(path)
        val filesAndFolders = root.listFiles()

        if (filesAndFolders == null || filesAndFolders.isEmpty()) {
//            TODO - fazer um elemento que apareca quando nao tiver arquivos
            return
        }

        binding.rvListFile.adapter = GridAdapter(applicationContext, filesAndFolders)
    }

}