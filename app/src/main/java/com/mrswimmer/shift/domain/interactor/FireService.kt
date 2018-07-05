package com.mrswimmer.shift.domain.interactor

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.mrswimmer.shift.App
import com.mrswimmer.shift.data.model.firebase.Acc
import com.mrswimmer.shift.data.model.firebase.Task
import com.mrswimmer.shift.data.model.req.Fio
import javax.inject.Inject

class FireService {
    private var auth = FirebaseAuth.getInstance()
    private var db = FirebaseDatabase.getInstance().reference

    @Inject
    lateinit var settingsService: SettingsService

    init {
        App.getComponent().inject(this)
    }

    fun signIn(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
                .map({ authResult -> authResult.user != null })
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun signUp(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
                .map({ authResult -> authResult.user != null })
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun isSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }

    fun getEmail(): String {
        return auth.currentUser!!.email!!
    }

    private fun getId(): String {
        return settingsService.userId
    }

    private val dbUser = db.child("accs").child(getId()).child("id")

    fun loadRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Fio>) {
        db.child("tasks").limitToFirst(params.loadSize).startAt(params.startPosition.toDouble()).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i("code", "cancel fire")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire $dataSnapshot")
                val tasks: List<Fio> = listOf()
                dataSnapshot.children.forEach {
                    it.getValue(Fio::class.java)
                }
                callback.onResult(tasks)
            }

        })
    }

    fun loadFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Fio>) {
        db.child("tasks").limitToFirst(params.pageSize).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("code", "cancel fire first")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire first ")
                val tasks: List<Fio> = listOf()
                dataSnapshot.children.forEach{
                    Log.i("code", "first ch ${it.child("id")}")
                }
                /*val fio = Fio("q", "w", "e", "r", "t", 0)
                val tasks: List<Fio> = listOf(fio, fio, fio)*/
                /*dataSnapshot.children.forEach {
                    it.getValue(Fio::class.java)
                }*/
                callback.onResult(tasks, 0)
            }
        })
    }

    interface AuthCallBack {
        fun onSuccess(success: Boolean)

        fun onError(e: Throwable)
    }

    fun createUser(country: String) {
        val accId = db.child("accs").push()
        val acc = Acc(getEmail(), country, accId.key)
        accId.setValue(acc)
    }

    fun addNotes() {
        val taskId = db.child("tasks").push()
        val task = Task(taskId.key, 0)
        taskId.setValue(task)
    }
}