package nasser.com.athletes.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.news.model.entities.RemoteResponse


@Database(entities = arrayOf(RemoteResponse.Article::class), version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun listAtheletsDao(): ListNewsDao


    companion object {
        private var database: AppDatabase? = null

        fun getDataBaseInstance(context: Context): AppDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()
            }
            return database as AppDatabase
        }
    }


}