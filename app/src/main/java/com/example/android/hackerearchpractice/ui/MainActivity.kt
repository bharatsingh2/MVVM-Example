package com.example.android.hackerearchpractice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.hackerearchpractice.R
import com.example.android.hackerearchpractice.databinding.ActivityMainBinding
import com.example.android.hackerearchpractice.util.inTransaction
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setSupportActionBar(mainBinding.toolbar)

        navigateToHome()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun navigateToHome() {
        supportFragmentManager.inTransaction {
            replace(R.id.container, MainFragment.newInstance())
        }
    }

}