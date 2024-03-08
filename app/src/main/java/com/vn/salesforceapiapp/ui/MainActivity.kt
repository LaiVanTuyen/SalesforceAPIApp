package com.vn.salesforceapiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vn.salesforceapiapp.adapter.RecyclerViewAdapter
import com.vn.salesforceapiapp.api.APIService
import com.vn.salesforceapiapp.api.Retrofit
import com.vn.salesforceapiapp.databinding.ActivityMainBinding
import com.vn.salesforceapiapp.model.Feed
import com.vn.salesforceapiapp.model.Token
import com.vn.salesforceapiapp.util.Utils


import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var postList: List<Feed> = emptyList()
    private var token: String = "Bearer "



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createToken()
        initRecyclerView()
        getAllPost()
        println("Post1: " + postList.size)
        binding.imgSend.setOnClickListener {
            val newContent = binding.edtSend.text.toString()
            if (newContent.isNotEmpty()) {
                createPost(newContent)
                println("Post2: " + postList.size)
                binding.edtSend.text.clear()
                Toast.makeText(this, "Post Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter content", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getAllPost() {
        Retrofit.api.getPosts(token)
            .enqueue(object : retrofit2.Callback<List<Feed>> {
                override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
                    if (response.isSuccessful) {
                        postList = response.body()!!
//                        initRecyclerView()
                        recyclerViewAdapter.updateData(postList)
                    } else {
                        Log.e(
                            "MainActivity",
                            "Failed to fetch posts: ${response.errorBody()?.string()}"
                        )
                    }
                }

                override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
                    Log.e("MainActivity", "Failed to fetch posts: ${t.message}", t)
                }

            })
    }

    private fun createPost(title: String) {
        val postRequest = APIService.PostRequest(title)
        Retrofit.api.createPost(
            token,
            postRequest
        ).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    getAllPost()
                    Log.d("MainActivity", "Post successfully created")
                } else {
                    Log.e(
                        "MainActivity",
                        "Failed to create post: ${response.errorBody()?.string()}"
                    )
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("MainActivity", "Failed to create post: ${t.message}", t)
            }
        })
    }

    private fun createToken(){
        Retrofit.api.getToken("application/json;charset=UTF-8",Utils.GRANT_TYPE,
            Utils.CLIENT_ID,
            Utils.CLIENT_SECRET,
            Utils.USERNAME,
            Utils.PASSWORD).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    token = response.body()?.access_token.toString()
                    Log.d("MainActivity", "Token: ${token?.substring(0, 10)}...${token?.substring(token.length - 10)}")
                } else {
                    Log.e(
                        "MainActivity",
                        "Failed to fetch token: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.e("MainActivity", "Failed to fetch token: ${t.message}", t)
            }
        })
    }


    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(postList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

}