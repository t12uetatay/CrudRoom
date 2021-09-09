package id.t12uetatay.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.t12uetatay.room.model.Notes
import id.t12uetatay.room.utils.DateConverter
import androidx.room.Room


@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "notes.db"
        @Synchronized
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}