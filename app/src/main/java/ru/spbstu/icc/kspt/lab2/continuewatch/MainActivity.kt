package ru.spbstu.icc.kspt.lab2.continuewatch
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsLeft: Int = 0

    lateinit var textSecondsLeft: TextView
    var isOnScreen = true

    var backgroundThread = Thread {
        while (true) {
            if (isOnScreen) {
                Thread.sleep(1000)
                textSecondsLeft.post {
                    textSecondsLeft.text = getString(R.string.seconds_left, secondsLeft++)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondsLeft = savedInstanceState?.getInt(SECONDS_LEFT) ?: 0
        setContentView(R.layout.activity_main)
        textSecondsLeft = findViewById(R.id.textSecondsLeft)
        backgroundThread.start()
    }

    override fun onStart() {
        super.onStart()
        isOnScreen = true
    }

    companion object { const val SECONDS_LEFT = "Seconds left" }

    override fun onStop() {
        super.onStop()
        isOnScreen = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SECONDS_LEFT, secondsLeft)
        super.onSaveInstanceState(outState)
    }



}
