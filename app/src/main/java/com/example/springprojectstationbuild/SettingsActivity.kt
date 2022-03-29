package com.example.springprojectstationbuild

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_main.*


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setContentView(R.layout.activity_main);
        // Finding CheckBox by its unique ID
        var checkboxCPU = (CheckBox()) findViewById (R.id.cbCPU);
        var checkboxpower = (CheckBox) findViewById (R.id.cbpowersource);
        var checkboxWIFI = (CheckBox) findViewById (R.id.cbwificard);
        var checkboxRAM=(CheckBox)findViewById(R.id.cbRAM);
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btnSearch.setOnClickListener
        {
            val checkboxCPU = cbCPU.isChecked
            val checkboxWIFI = cbwificard.isChecked
            if (checkboxCPU.isChecked)
            {
                //code to go to next page
            }


        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}