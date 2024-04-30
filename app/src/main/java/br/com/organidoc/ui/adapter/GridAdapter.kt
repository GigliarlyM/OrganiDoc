package br.com.organidoc.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.organidoc.R
import br.com.organidoc.ui.MainActivity
import java.io.File


class GridAdapter(
    private val context: Context,
    private val filesAndFolders: Array<File>
) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var documentName: TextView

        init {
            icon = itemView.findViewById<ImageView>(R.id.iv_icon)
            documentName = itemView.findViewById<TextView>(R.id.tv_name_document)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_document, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectFile = filesAndFolders[position]

        holder.run {
            documentName.text = selectFile.name
            if (selectFile.isDirectory) {
                icon.setImageResource(R.drawable.ic_pasta)
            } else {
                icon.setImageResource(R.drawable.ic_image)
            }

            itemView.setOnClickListener {
                if (selectFile.isDirectory) {
                    val intent = Intent(context, MainActivity::class.java)
                    val path = selectFile.absolutePath
                    intent.putExtra("path", path)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } else {
                    try {
                        val intent = Intent()
                        intent.setAction(android.content.Intent.ACTION_VIEW)
                        val type = "image/*"
                        intent.setDataAndType(Uri.parse(selectFile.absolutePath), type)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(
                            context.applicationContext, "cannot open the file", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = filesAndFolders.size

}

