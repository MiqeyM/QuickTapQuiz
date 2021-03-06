package com.kraci.quicktapquiz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kraci.quicktapquiz.R
import com.kraci.quicktapquiz.databinding.ActivityJoinQuizChooseBinding
import com.kraci.quicktapquiz.utils.IntentExtras
import com.kraci.quicktapquiz.viewmodels.JoinQuizChooseViewModel

class JoinQuizChooseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinQuizChooseBinding
    private lateinit var joinQuizChooseViewModel: JoinQuizChooseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Choose a Game"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join_quiz_choose)

        joinQuizChooseViewModel = ViewModelProviders.of(this).get(JoinQuizChooseViewModel::class.java).apply {

            hostGamePicked.observe(this@JoinQuizChooseActivity, Observer {
                val intent = Intent(this@JoinQuizChooseActivity, JoinTeamsWaitingActivity::class.java)
                intent.putExtra(IntentExtras.HOSTED_GAME, it)
                startActivity(intent)
                finish()
            })

            emptyNameEvent.observe(this@JoinQuizChooseActivity, Observer {
                Toast.makeText(baseContext, "You must enter a team name.", Toast.LENGTH_LONG).show()
            })

        }

        binding.hostedGames.layoutManager = LinearLayoutManager(baseContext)
        binding.hostedGames.setHasFixedSize(true)
        binding.viewModel = joinQuizChooseViewModel
    }

    override fun onBackPressed() {
        joinQuizChooseViewModel.stopDiscovery()
        super.onBackPressed()
    }

}
