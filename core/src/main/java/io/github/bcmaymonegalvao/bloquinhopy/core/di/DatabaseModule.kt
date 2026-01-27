package io.github.bcmaymonegalvao.bloquinhopy.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.bcmaymonegalvao.bloquinhopy.core.database.BloquinhoDatabase
import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.NotebookDao
import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.ProjectDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BloquinhoDatabase {
        return Room.databaseBuilder(
            context,
            BloquinhoDatabase::class.java,
            "bloquinho.db"
        ).build()
    }

    @Provides
    fun provideProjectDao(database: BloquinhoDatabase): ProjectDao {
        return database.projectDao()
    }

    @Provides
    fun provideNotebookDao(database: BloquinhoDatabase): NotebookDao {
        return database.notebookDao()
    }
}
