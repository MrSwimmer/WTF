package com.mrswimmer.shift.domain.interactor

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.events.Subscriber
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import com.mrswimmer.shift.App
import com.mrswimmer.shift.data.model.req.Fio
import com.mrswimmer.shift.domain.CallbackFabric
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

    fun getId(): String {
        return settingsService.userId
    }

    private val dbUser = db.child("accs").child(getId()).child("id")

    fun loadRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Fio>) {
        dbUser.child("tasks").limitToFirst(params.loadSize).startAt(params.startPosition.toDouble()).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.i("code", "cancel fire")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire")
                var tasks: List<Fio> = listOf()
                dataSnapshot.children.forEach {
                    it.getValue(Fio::class.java)
                }
                callback.onResult(tasks)
            }

        })
    }

    fun loadFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Fio>) {
        dbUser.child("tasks").limitToFirst(params.pageSize).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("code", "cancel fire")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire")
                var tasks: List<Fio> = listOf()
                dataSnapshot.children.forEach {
                    it.getValue(Fio::class.java)
                }
                callback.onResult(tasks, 0)
            }
        })
    }

    interface AuthCallBack {
        fun onSuccess(success: Boolean)

        fun onError(e: Throwable)
    }

    interface FiosCallBack {
        fun onSuccess(fios: List<Fio>)

        fun onError(e: Throwable)
    }
}