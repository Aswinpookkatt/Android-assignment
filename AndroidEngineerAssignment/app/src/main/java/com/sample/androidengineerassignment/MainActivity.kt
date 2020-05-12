package com.sample.androidengineerassignment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sample.androidengineerassignment.Fragment.MessageFragment
import com.sample.androidengineerassignment.Fragment.NotificationsFragment
import com.sample.androidengineerassignment.Fragment.PostFragment

var count = 0

class MainActivity : AppCompatActivity() {
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder1: Notification.Builder
    lateinit var builder2: Notification.Builder
    private val channelId = "com.sample.androidengineerassignment"
    val description = "You have a message"

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinaterLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinaterLayout = findViewById(R.id.coordinator_layout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openPostsFragment()
        var previousMenuItem: MenuItem? = null
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout, R.string.open_drawer, R.string.close_drawer
        )
        val fragM = intent.getStringExtra("fragM")
        val fragN = intent.getStringExtra("fragN")

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent2 = Intent(this, MainActivity::class.java)
        intent2.putExtra("fragN", "NotificationsFragment")
        val intent1 = Intent(this, MainActivity::class.java)
        intent1.putExtra("fragM", "MessageFragment")


        val pendingIntent1 =
            PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        val pendingIntent2 =
            PendingIntent.getActivity(this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT)

        if (fragM=="MessageFragment") {
        msgfrgmnt()
        }
        if(fragN=="NotificationsFragment"){
            ntfcnfrgmnt()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)


            builder1 = Notification.Builder(this, channelId)
                .setContentTitle("Message")
                .setContentText("Open message")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent1)
                .setAutoCancel(true)


        } else {

            builder1 = Notification.Builder(this)
                .setContentTitle("Message")
                .setContentText("Open Message")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent1)
                .setAutoCancel(true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)


            builder2 = Notification.Builder(this, channelId)
                .setContentTitle("Notification")
                .setContentText("You have a new notification")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent2)
                .setAutoCancel(true)


        } else {

            builder2 = Notification.Builder(this)
                .setContentTitle("Notification")
                .setContentText("You have a new notification")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent2)
                .setAutoCancel(true)
        }

        if (count == 0) {
            notificationManager.notify(1, builder1.build())
            notificationManager.notify(2, builder2.build())
            count++
        }
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false

            }
            it.isChecked = true
            it.isCheckable = true
            previousMenuItem = it
            when (it.itemId) {
                R.id.posts -> {
                    openPostsFragment()
                }
                R.id.messages -> {
                    msgfrgmnt()

                }
                R.id.notifications -> {
                    ntfcnfrgmnt()
                }


            }
            return@setNavigationItemSelectedListener true
        }

    }


    //setting toolbar
    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Posts"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }


    fun openPostsFragment() {
        val Fragment = PostFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, Fragment)
        transaction.commit()
        supportActionBar?.title = "Posts"
        navigationView.setCheckedItem(R.id.posts)
        drawerLayout.closeDrawers()
    }

    fun msgfrgmnt() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout,
                MessageFragment()
            )
            .addToBackStack("Message")
            .commit()
        drawerLayout.closeDrawers()
        supportActionBar?.title = "Messages"
    }

    fun ntfcnfrgmnt() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout,
                NotificationsFragment()
            )
            .addToBackStack("Notifications")
            .commit()
        drawerLayout.closeDrawers()
        supportActionBar?.title = "Notifications"
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when (frag) {
            !is PostFragment -> openPostsFragment()
            else -> super.onBackPressed()

        }

    }
}
