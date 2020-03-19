package com.example.yori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.yori.authorization.AuthorizationFragment
import com.example.yori.registration.RegistrationFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.enter_fragment.btnRegister


class MainActivity : AppCompatActivity() {

    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_register_activity)
        getSupportActionBar()?.hide();

        currentFragment = AuthorizationFragment()

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frmAuthorizationRegistrationmbed, currentFragment)
                .addToBackStack(null)
                .commit()
            }
    }

    override fun onStart() {
        super.onStart()

        btnRegister.setOnClickListener {
            currentFragment = RegistrationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frmAuthorizationRegistrationmbed, currentFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        var inten = getIntent()
        if(currentFragment is AuthorizationFragment)
            finish()
        else {
            finish()
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(currentFragment is AuthorizationFragment)
            outState.putString("Auth", "Auth")
        else if(currentFragment is RegistrationFragment)
            outState.putString("Reg", "Reg")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState.getString("Auth") == "Auth")
            currentFragment = AuthorizationFragment()
        else if (savedInstanceState.getString("Reg") == "Reg")
            currentFragment = RegistrationFragment()
    }

}
