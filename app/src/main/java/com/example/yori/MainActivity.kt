package com.example.yori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yori.authorization.AuthorizationFragment
import com.example.yori.registration.RegistrationFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.enter_fragment.btnRegister


class MainActivity : AppCompatActivity() {

    lateinit private var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_register_activity)
        getSupportActionBar()?.hide();

        if(savedInstanceState == null) {
            currentFragment = AuthorizationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frmAuthorizationRegistrationmbed, currentFragment)
                .commit()
            }
    }

    override fun onStart() {
        super.onStart()
        btnRegister.setOnClickListener {
            currentFragment = RegistrationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frmAuthorizationRegistrationmbed, currentFragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        if(currentFragment is RegistrationFragment){
            val intent = getIntent()
            finish()
            startActivity(intent)
        }
        else
            finish()
    }

}
