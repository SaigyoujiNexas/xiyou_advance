package com.xiyou.advance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        navController.addOnDestinationChangedListener{controller, dest, agrs ->
            findViewById<BottomNavigationView>(R.id.bottom_nav_view).let {
                if (dest.id != com.xiyou.main.R.id.nav_frag_homepage && dest.id != com.xiyou.community.R.id.nav_frag_community) {
                    it.visibility = View.GONE
                }
                else {
                    if(!it.isVisible) {
                        it.visibility = View.VISIBLE
                    }
                }
            }
        }
        appBarConfig = AppBarConfiguration(
            setOf(com.xiyou.main.R.id.nav_homepage, R.id.nav_community, com.xiyou.community.R.id.nav_community), null)
        setUpActionBar(navController, appBarConfig)
        setupNavigationMenu(navController)
        setupBottomNavMenu(navController)
    }
    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
    private fun setUpActionBar(navController: NavController, appBarConfig: AppBarConfiguration)
    {
         setupActionBarWithNavController(navController, appBarConfig)
    }
    private fun setupNavigationMenu(navController: NavController)
    {

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val retval = super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.fragment_container)) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp(appBarConfig)
    }

}