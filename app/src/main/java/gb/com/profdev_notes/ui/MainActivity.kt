package gb.com.profdev_notes.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import gb.com.profdev_notes.R
import gb.com.profdev_notes.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()

        binding.fab.setOnClickListener{
            onFloatingClicked()
        }
    }

    private fun onFloatingClicked() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, AddFragment.newInstance())
            .commitNow()
    }
}