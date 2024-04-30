package br.com.organidoc.ui

import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.organidoc.databinding.ActivityMainBinding
import br.com.organidoc.ui.adapter.GridAdapter
import java.io.File


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvListFile.layoutManager = GridLayoutManager(this, 2)

//        val items: List<Item> = ArrayList<Item>()
//      Adicione seus itens à lista aqui
//      Adicione seus itens à lista aqui
//        val adapter = MyAdapter(items)
//        recyclerView.setAdapter(adapter)
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