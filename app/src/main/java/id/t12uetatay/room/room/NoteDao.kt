package id.t12uetatay.room.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.t12uetatay.room.model.Notes
import androidx.lifecycle.LiveData





interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Notes?): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(todos: List<Notes>): Array<Long>

    @Query("SELECT * FROM note ORDER BY createAt DESC ")
    fun readAll(): LiveData<List<Notes?>?>?

    @Query("SELECT * FROM note WHERE title LIKE:query")
    fun serach(query: String?): LiveData<List<Notes?>?>?

    @Query("DELETE  FROM note WHERE noteId=:id")
    fun delete(id: Long)

    @Query("DELETE FROM note")
    fun deleteAll()
}