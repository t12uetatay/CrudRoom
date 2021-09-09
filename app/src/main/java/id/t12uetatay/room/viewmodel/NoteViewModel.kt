package id.t12uetatay.room.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import id.t12uetatay.room.repository.NoteRepository
import androidx.lifecycle.LiveData
import id.t12uetatay.room.model.Notes


class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: NoteRepository? = null
    @SuppressLint("StaticFieldLeak")
    private val context: Context

    init {
        this.context = application
        mRepository = NoteRepository(context)
    }


    fun readAll(): LiveData<List<Notes?>?>? {
        return mRepository?.readAll()
    }

    fun insert(notes: Notes?) {
        mRepository?.insert(notes)
    }

    //
    fun create(note: Notes): Long? {
        return mRepository?.createNote(note)
    }
    //

    fun delete(notes: Notes?) {
        mRepository?.delete(notes)
    }

    fun deleteAll() {
        mRepository?.deleteAll()
    }


}