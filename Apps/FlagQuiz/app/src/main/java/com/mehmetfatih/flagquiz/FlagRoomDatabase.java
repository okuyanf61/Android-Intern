package com.mehmetfatih.flagquiz;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Flag.class}, version = 2, exportSchema = false)
public abstract class FlagRoomDatabase extends RoomDatabase {

    public abstract FlagDAO flagDAO();

    private static volatile FlagRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static FlagRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlagRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FlagRoomDatabase.class, "flag_database").fallbackToDestructiveMigration()
                            .addCallback(sFlagDatabaseCallback).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sFlagDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FlagDAO dao = INSTANCE.flagDAO();
                dao.deleteAll();

                Flag flag = new Flag(0, "Turkey", "turkey");
                dao.insert(flag);
                flag = new Flag(1,"Germany","germany");
                dao.insert(flag);
                flag = new Flag(2,"Italy","italy");
                dao.insert(flag);
                flag = new Flag(3,"France","france");
                dao.insert(flag);
                flag = new Flag(4,"Spain","spain");
                dao.insert(flag);
                flag = new Flag(5,"Portugal","portugal");
                dao.insert(flag);
                flag = new Flag(6,"Netherlands","netherlands");
                dao.insert(flag);
                flag = new Flag(7,"Romania","romania");
                dao.insert(flag);
                flag = new Flag(8,"Bulgaria","bulgaria");
                dao.insert(flag);
                flag = new Flag(9,"Norway","norway");
                dao.insert(flag);
                flag = new Flag(10,"Belgium","belgium");
                dao.insert(flag);
            });
        }
    };


}
