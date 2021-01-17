package com.androidpractice.testappkotlin

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main_master.*

class MainMasterActivity : AppCompatActivity() {

    var sEmail:String?=null
    var sName:String?=null
    var sUid:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_master)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)






        sEmail = intent.getStringExtra("user_email")
        sName = intent.getStringExtra("user_display_name")
        sUid = intent.getStringExtra("user_uid")

        tvUserEmail.text = sEmail + "  " + sName + "  " + sUid

        tvTitleActivity.append(" " + sName)

    }
}