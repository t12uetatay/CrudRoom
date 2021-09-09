package id.t12uetatay.room.model

import androidx.room.*
import id.t12uetatay.room.utils.DateConverter
import java.util.*
import androidx.room.TypeConverters
import java.io.Serializable


@Entity(tableName = "note")
class Notes (noteId: Long, title: String?, description: String?, createAt: Date?) : Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    private var noteId: Long = 0

    @ColumnInfo(name = "title")
    private var title: String? = null

    @ColumnInfo(name = "description")
    private var description: String? = null

    @TypeConverters(DateConverter::class)
    private var createAt: Date? = null

    init {
        this.noteId =noteId
        this.title=title
        this.description=description
        this.createAt=createAt
    }

    fun getNoteId(): Long {
        return noteId
    }

    fun getTitle(): String? {
        return title
    }

    fun getDescription(): String? {
        return description
    }

    fun getCreateAt(): Date? = createAt
}