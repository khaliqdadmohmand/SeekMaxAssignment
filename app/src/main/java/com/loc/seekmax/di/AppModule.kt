package com.loc.seekmax.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.data.local.JobsDatabase
import com.loc.seekmax.data.local.JobsTypeConvertor
import com.loc.seekmax.data.manger.LocalUserMangerImpl
import com.loc.seekmax.data.remote.JobsApi
import com.loc.seekmax.data.repository.JobsRepositoryImpl
import com.loc.seekmax.domain.manager.LocalUserManger
import com.loc.seekmax.domain.repository.JobsRepository
import com.loc.seekmax.domain.usecases.app_entry.AppEntryUseCases
import com.loc.seekmax.domain.usecases.app_entry.ReadAppEntry
import com.loc.seekmax.domain.usecases.app_entry.SaveAppEntry
import com.loc.seekmax.domain.usecases.auth.AuthUseCase
import com.loc.seekmax.domain.usecases.jobs.DeleteArticle
import com.loc.seekmax.domain.usecases.jobs.GetArticle
import com.loc.seekmax.domain.usecases.jobs.GetArticles
import com.loc.seekmax.domain.usecases.jobs.GetJobs
import com.loc.seekmax.domain.usecases.jobs.JobsUseCases
import com.loc.seekmax.domain.usecases.jobs.SearchJobs
import com.loc.seekmax.domain.usecases.jobs.UpsertArticle
import com.loc.seekmax.domain.usecases.auth.Authenticate
import com.loc.seekmax.domain.usecases.auth.ClearState
import com.loc.seekmax.domain.usecases.auth.ReadAuthState
import com.loc.seekmax.domain.usecases.auth.SaveAuthState
import com.loc.seekmax.domain.usecases.auth.UpsertUser
import com.loc.seekmax.domain.usecases.profile.GetUserData
import com.loc.seekmax.domain.usecases.profile.InsertUser
import com.loc.seekmax.domain.usecases.profile.ProfileUseCase
import com.loc.seekmax.util.Constants.BASE_URL
import com.loc.seekmax.util.Constants.JOBS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideApiInstance(@ApplicationContext appContext: Context): JobsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJobsRepository(
        newsApi: JobsApi,
        dao: JobsDao
    ): JobsRepository {
        return JobsRepositoryImpl(newsApi, dao)
    }

    @Provides
    @Singleton
    fun provideJobsUseCases(
        jobsRepository: JobsRepository,
        jobsDao: JobsDao
    ): JobsUseCases {
        return JobsUseCases(
            getJobs = GetJobs(jobsRepository),
            searchJobs = SearchJobs(jobsRepository),
            upsertArticle = UpsertArticle(jobsDao),
            deleteArticle = DeleteArticle(jobsDao),
            getArticles = GetArticles(jobsDao),
            getArticle = GetArticle(jobsDao)
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCase(
        jobsDao: JobsDao,
        localUserManger: LocalUserManger
    ): ProfileUseCase {
        return ProfileUseCase(
            getUserData = GetUserData(jobsDao),
            insertUser = InsertUser(jobsDao)
        )
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(
        localUserManger: LocalUserManger,
        repository: JobsRepository,
        jobsDao: JobsDao,
    ): AuthUseCase {
        return AuthUseCase(
            authenticate = Authenticate(repository),
            readAuthState = ReadAuthState(localUserManger),
            saveAuthState = SaveAuthState(localUserManger),
            clearState = ClearState(localUserManger),
            upsertUser = UpsertUser(jobsDao)

        )
    }


    @Provides
    @Singleton
    fun provideJobsDatabase(
        application: Application
    ): JobsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = JobsDatabase::class.java,
            name = JOBS_DATABASE_NAME
        ).addTypeConverter(JobsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideJobsDao(
        jobsDatabase: JobsDatabase
    ): JobsDao = jobsDatabase.jobsDao

}