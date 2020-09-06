package com.mehmetfatih.flagquiz;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FlagViewModel extends AndroidViewModel {

    private FlagRepository mRepository;

    private LiveData<List<Flag>> mAllFlags;

    public FlagViewModel (Application application) {
        super(application);
        mRepository = new FlagRepository(application);
        mAllFlags = mRepository.getAllFlags();
    }

    LiveData<List<Flag>>getAllFlags() { return mAllFlags; }

    public void insert(Flag flag) { mRepository.insert(flag); }
}
