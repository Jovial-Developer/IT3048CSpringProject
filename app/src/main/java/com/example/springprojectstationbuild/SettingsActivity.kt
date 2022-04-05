package com.example.springprojectstationbuild

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.preference.CheckBoxPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragmentEo())
                .commit()
        } else {
            throw Exception("This value already exists, please enter a valid value. ")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragmentEo : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    abstract class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {

        private fun setPreferenceSummary(preference: Preference, value: Any?) {
            val stringValue = value?.toString()

            if (preference is ListPreference) {
                val prefIndex = preference.findIndexOfValue(stringValue ?: "")
                if (prefIndex >= 0) {
                    preference.setSummary(preference.entries[prefIndex])
                }
            } else {
                preference.summary = stringValue
            }
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            addPreferencesFromResource(R.xml.root_preferences)

            val sharedPreferences = preferenceScreen.sharedPreferences
            val prefScreen = preferenceScreen
            val count = prefScreen.preferenceCount
            for (i in 0 until count) {
                val p = prefScreen.getPreference(i)
                if (p !is CheckBoxPreference) {
                    val value = sharedPreferences?.getString(p.key, "")
                    setPreferenceSummary(p, value)
                }
            }
        }
    }
}



private fun Any.replace(settings: Int, settingsFragment: SettingsActivity.SettingsFragment) {

}
