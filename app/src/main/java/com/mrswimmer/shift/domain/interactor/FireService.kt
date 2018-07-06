package com.mrswimmer.shift.domain.interactor

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.mrswimmer.shift.App
import com.mrswimmer.shift.data.model.firebase.Acc
import com.mrswimmer.shift.data.model.firebase.Task
import javax.inject.Inject
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import com.mrswimmer.shift.data.model.req.TaskResult


class FireService {
    private var auth = FirebaseAuth.getInstance()
    private var db = FirebaseDatabase.getInstance().reference

    var lastId: String? = null

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

    fun loadRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<Task>) {
        Log.i("code", "lastid $lastId")
        /*db.child("tasks").limitToFirst(params.loadSize).orderByKey().startAt(lastId + 1).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i("code", "cancel fire")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire ${dataSnapshot.children}")
                val tasks: MutableList<Task> = mutableListOf()
                dataSnapshot.children.forEach {
                    val task = it.getValue(Task::class.java)
                    tasks.add(task!!)
                    Log.i("code", "${task.id}")
                    lastId = task!!.id
                }
                callback.onResult(tasks)
            }
        })*/
        RxFirebaseDatabase.observeSingleValueEvent(db.child("tasks").limitToFirst(params.loadSize).orderByKey().startAt(lastId + 1), DataSnapshotMapper.listOf(Task::class.java))
                .subscribe({ tasks ->
                    Log.i("code", "${tasks.size}")
                    if (tasks.size > 0)
                        lastId = tasks[tasks.size - 1].id
                    callback.onResult(tasks)
                })
    }

    fun loadFirstPage(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<Task>) {
        /*db.child("tasks").limitToFirst(params.pageSize).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("code", "cancel fire first")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("code", "data change fire first ")
                var tasks: MutableList<Task> = mutableListOf()
                dataSnapshot.children.forEach {
                    val task = it.getValue(Task::class.java)
                    tasks.add(task!!)
                    Log.i("code", "first ${task.id}")
                    lastId = task.id
                }
                callback.onResult(tasks, 0)
            }
        })*/
        RxFirebaseDatabase.observeSingleValueEvent(db.child("tasks").limitToFirst(params.pageSize), DataSnapshotMapper.listOf(Task::class.java))
                .subscribe({ tasks ->
                    if (tasks.size > 0)
                        lastId = tasks[tasks.size - 1].id
                    callback.onResult(tasks, 0)
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
        val task = Task(taskId.key, 0, "first", "second", "third", "Russia")
        taskId.setValue(task)
    }

    fun sendResult(id: String, result: Int) {
        val taskId = db.child("tasks").child(id).child("accs").push()
        taskId.setValue(result)
    }
}