package com.mehmetfatih.flagquiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class FlagRepository {

    private FlagDAO mFlagDao;
    private LiveData<List<Flag>> mAllFlags;

    // Note that in order to unit test the FlagRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    FlagRepository(Application application) {
        FlagRoomDatabase db = FlagRoomDatabase.getDatabase(application);
        mFlagDao = db.flagDAO();
        mAllFlags = mFlagDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Flag>> getAllFlags() {
        return mAllFlags;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Flag flag) {
        FlagRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFlagDao.insert(flag);
        });
    }
}
