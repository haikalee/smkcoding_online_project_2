package com.haikal.project2.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.haikal.project2.data.dao.NoteDao
//import com.haikal.project2.data.note.NoteData
//
//@Database(entities = arrayOf(NoteData::class), version = 1, exportSchema = false)
//abstract class NoteDatabase: RoomDatabase() {
//    abstract fun noteDao(): NoteDao
//    companion object {
//        @Volatile
//        private var INSTANCE: NoteDatabase? = null
//
//        fun getDatabase(context: Context): NoteDatabase {
//            var tempInstance =
//                INSTANCE
//
//            if (tempInstance != null) {
//                return tempInstance
//            }
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}