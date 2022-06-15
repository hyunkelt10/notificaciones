package com.example.toast

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        


    }



    fun normal(view: View){
        Toast.makeText(this, "toast normal", Toast.LENGTH_SHORT).show()
    }

    fun gravity(view: View) {
        val t = Toast.makeText(this, "gravity izquierda",Toast.LENGTH_SHORT)
        t.setGravity(Gravity.CENTER,0,0)
        t.show()

    }

    fun layout (view: View) {
        val layout = layoutInflater.inflate(R.layout.toast_layout,null)
        layout!!.findViewById<TextView>(R.id.tvTitulo).text = "prueba de layout titulo"
        layout!!.findViewById<TextView>(R.id.tvDescripcion).text = "prueba de layout  descripcion"
        val t = Toast(this@MainActivity)
        t.duration = Toast.LENGTH_SHORT
        t.view = layout
        t.show()

    }

    fun Snackbar(view:View) {
        Snackbar.make(view, "Test", Snackbar.LENGTH_SHORT).show()
    }

    fun SnackbarColor(view:View) {
        val s = Snackbar.make(view, "Test Color", Snackbar.LENGTH_SHORT)
        s.setTextColor(ContextCompat.getColor(view.context, R.color.purple_500))
        s.setActionTextColor(ContextCompat.getColor(view.context, R.color.teal_700))
        s.setBackgroundTint(Color.BLUE)
        s.show()







    }
    fun SnackbarCustom(view: View) {
        val s = Snackbar.make(view,"custom", Snackbar.LENGTH_SHORT)
        val sblayout = s.view as Snackbar.SnackbarLayout
        val customLayout = layoutInflater.inflate(R.layout.toast_layout, null)

        customLayout!!.findViewById<TextView>(R.id.tvTitulo).text = "alerta de titulo"
        customLayout!!.findViewById<TextView>(R.id.tvDescripcion).text = "Descripcion personalizada"
        sblayout.addView(customLayout, 0)
        s.setBackgroundTint(Color.GREEN)
        s.show()





    }

    fun alerta (view: View) {
        AlertDialog.Builder(this)
            .setTitle("Aviso")
            .setMessage("me dejas que active tu camara?")
            .setIcon(R.drawable.ic_launcher_foreground)
            .setPositiveButton("aceptar") {dialog, id -> /* Accion */}
            .setNegativeButton("cancelar")    {dialog, id -> /* Accion */ }
            .show()

    }

    fun seleccion (view: View) {
        val inflater = this.layoutInflater
        val custom_layout = inflater.inflate(R.layout.layout_dialog, null)

        AlertDialog.Builder(this)
            .setView(custom_layout)
            .setPositiveButton(R.string.aceptar, null)
            .setNegativeButton(R.string.cancelar, null)
            .show()
            

    }

    fun seleccion2 (view: View) {
        val items = arrayOf("español", "ingles", "frances")
        AlertDialog.Builder(this)
            .setTitle("selecciona idioma")
            .setMultiChoiceItems(items, null) {dialog, which, isChecked -> Log.i("dialogos", "opcion" + items [which] + "marcada a" + isChecked)}
            .setPositiveButton("aceptar") {dialog, id -> /* Accion */}
            .show()
    }


    private fun createNotificationchannel () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel (CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_description)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        } else {
            Toast.makeText(this@MainActivity, "No entra", Toast.LENGTH_SHORT).show()
        }
    }
    fun notificacion (view: View) {
        createNotificationchannel()
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon (R.drawable.ic_launcher_foreground)
            .setContentTitle ("TIENES UN AVISOOO!!!!!!!! ")
            .setContentText ("este es el cuerpo de la notificacion, hazme un bizum")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val intent = Intent(this, MainActivity2::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val NOTIF_ID = 1
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIF_ID, builder.build())

    }








}

