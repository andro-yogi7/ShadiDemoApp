package com.tcg.shaditask.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tcg.garageapplication.util.Coroutines
import com.tcg.shaditask.R
import com.tcg.shaditask.data.db.entities.DBModel
import com.tcg.shaditask.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class HomeActivity : AppCompatActivity(), KodeinAware, DBModelAdapter.ItemCliclListner {

    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbList: List<DBModel>
    private lateinit var dbModelAdapter: DBModelAdapter
    private lateinit var context:Context
    private var recyclerViewState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        init()
    }

    private fun init(){

        updateNetworkData()
        loadList()
    }

    private fun updateNetworkData(){
        lifecycleScope.launch {
            viewModel.getAllData()
        }
    }

    private fun loadList()= Coroutines().main{
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.setLayoutManager(mLayoutManager)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        context = this
        //dbModelAdapter = DBModelAdapter(this,dbList)
        viewModel.dbList.await().observe(this, {
            dbList = it
            dbModelAdapter = DBModelAdapter(context, dbList, this)
            binding.recyclerView.adapter = dbModelAdapter
            binding.recyclerView.getLayoutManager()?.onRestoreInstanceState(recyclerViewState);
        })
        //dbModelAdapter = DBModelAdapter(this,)
    }

    override fun onItemClick(dbModel: DBModel) {
        //TODO("Not yet implemented")
        lifecycleScope.launch {
            recyclerViewState = binding.recyclerView.getLayoutManager()?.onSaveInstanceState();
            viewModel.updateDBModel(dbModel)
        }
    }
}