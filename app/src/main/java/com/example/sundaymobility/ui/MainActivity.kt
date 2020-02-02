package com.example.sundaymobility.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sundaymobility.R
import com.example.sundaymobility.adapter.UserAdapter
import com.example.sundaymobility.factory.MainViewModelFactory
import com.example.sundaymobility.model.UserModel
import com.example.sundaymobility.repository.UserRepository
import com.example.sundaymobility.utils.Constants
import com.example.sundaymobility.utils.Constants.Companion.PHOTO_REQUEST_CODE
import com.example.sundaymobility.utils.Constants.Companion.getAPI
import com.example.sundaymobility.utils.data
import com.example.sundaymobility.viewmodel.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), UserAdapter.AlbumCallback {


    private val mService by lazy { getAPI() }


    private lateinit var mList: MutableList<UserModel>
    private lateinit var mAdapter: UserAdapter
    var model: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        model = ViewModelProviders.of(
            this,
            MainViewModelFactory(UserRepository(mService))
        )[MainViewModel::class.java]

        init()
    }

    private fun init() {


        supportActionBar?.show()
        mList = arrayListOf()

        rcv_user.layoutManager = GridLayoutManager(this, 2)
        rcv_user.setHasFixedSize(true)
        mAdapter = UserAdapter(mList, this@MainActivity, this@MainActivity)
        rcv_user.adapter = mAdapter

        fab_add.setOnClickListener {
            addUser()
        }
//        pb.visibility = View.VISIBLE
        /*mDisposable.add(
            mService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ s ->
                    pb.visibility = View.GONE
                    handleUserData(s)
                }, { throwable ->
                    pb.visibility = View.GONE
                    i("ERROR: ", throwable.message.toString())
                })
        )*/

        pb.visibility = View.VISIBLE
        model!!.getUsers().observe(this, Observer<List<UserModel>> { users ->
            handleUserData(users)
        })


    }

    private fun addUser() {
        val model = data.getRandomUser()
        if (mList.isNotEmpty()) {
            showRCV()
            mList.add(model)

            mAdapter.notifyItemInserted(mList.count() - 1)
            rcv_user.smoothScrollToPosition(mList.count() - 1)

        } else {
            showEmptyView()
        }

    }

    private fun handleUserData(model: List<UserModel>) {
        pb.visibility = View.GONE
        if (model.isNotEmpty()) {

            showRCV()

            mList.addAll(model as MutableList<UserModel>)
            mAdapter.notifyDataSetChanged()
        } else {
            showEmptyView()
        }
    }

    private fun showRCV() {
        tv_empty.visibility = View.GONE
        rcv_user.visibility = View.VISIBLE
    }

    private fun showEmptyView() {
        tv_empty.visibility = View.VISIBLE
        rcv_user.visibility = View.GONE
    }


    override fun onPhotoClick(position: Int) {

        val url = mList[position].avatarUrl
        Intent(this@MainActivity, PhotoViewActivity::class.java).apply {
            putExtra("photo_url", url)
            putExtra("photo_position", position)
            startActivityForResult(this, PHOTO_REQUEST_CODE)
        }
    }

    override fun onDelete(position: Int) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.warning))
            .setMessage(getString(R.string.delete_user))
            .setPositiveButton("delete") { dialog, _ ->
                dialog?.cancel()
                deletePhoto(position)
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog?.cancel()
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PHOTO_REQUEST_CODE -> {
                when (resultCode) {
                    Constants.REQUEST_SUCCESS -> {
                        if (data != null) {
                            val position = data.getIntExtra("photo_position", -1)
                            deletePhoto(position)
                        }
                    }
                }
            }
        }
    }

    private fun deletePhoto(position: Int) = mAdapter.deleteData(position)


}
