package gb.com.profdev_notes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import gb.com.profdev_notes.model.datasource.NoteDao
import gb.com.profdev_notes.model.datasource.NoteDatabase
import gb.com.profdev_notes.model.repository.NoteRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesAppDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app,NoteDatabase::class.java,"note_database").build()
    }

    @Singleton
    @Provides
    fun providesNoteDao(db: NoteDatabase): NoteDao {
        return db.noteDao()
    }

    @Provides
    fun providesRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
}