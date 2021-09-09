package id.t12uetatay.room.repository

import androidx.lifecycle.LiveData

import id.t12uetatay.room.room.AppDatabase

import android.app.Application
import android.content.Context
import id.t12uetatay.room.model.Notes
import id.t12uetatay.room.room.NoteDao
import android.os.AsyncTask
import java.util.concurrent.ExecutionException


class NoteRepository(context: Context) {
   // private var noteDao: NoteDao? = null

    companion object {
        private lateinit var noteDao: NoteDao
    }

    init {
        val myRoomDB = AppDatabase.getDatabase(context)
        noteDao = myRoomDB?.noteDao()!!
    }


    fun readAll(): LiveData<List<Notes?>?>? {
        return noteDao?.readAll()
    }
    //
    internal class CreateNoteTask : AsyncTask<Notes, Void, Long>() {
        override fun doInBackground(vararg note: Notes?): Long? {
            return noteDao.insert(note[0])
        }
    }

    fun createNote(note: Notes): Long? {
        try {
            return CreateNoteTask().execute(note).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return null
    }

    //


    fun insert(note: Notes?) {
        noteDao?.let { insertAsync(it).execute(note) }
    }

    private class insertAsync internal constructor(noteDao: NoteDao) :
        AsyncTask<Notes?, Void?, Long>() {

        private val mAppDao: NoteDao = noteDao
        override fun doInBackground(vararg note: Notes?): Long {
            return mAppDao.insert(note[0])
        }
    }

    fun delete(entity: Notes?) {
        noteDao?.let { deleteAsync(it).execute(entity) }
    }

    private class deleteAsync internal constructor(noteDao: NoteDao) :
        AsyncTask<Notes?, Void?, Void?>() {

        private val mAppDao: NoteDao = noteDao
        override fun doInBackground(vararg note: Notes?): Void? {
            mAppDao.delete(note[0]!!.getNoteId())
            return null
        }
    }

    fun deleteAll() {
        noteDao?.let { deleteAllAsync(it).execute() }
    }

    private class deleteAllAsync internal constructor(noteDao: NoteDao) :
        AsyncTask<Notes?, Void?, Void?>() {

        private val mAppDao: NoteDao = noteDao
        override fun doInBackground(vararg p0: Notes?): Void? {
            mAppDao.deleteAll()
            return null
        }

    }
}