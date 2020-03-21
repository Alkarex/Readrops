package com.readrops.readropsdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.readrops.readropsdb.dao.AccountDao;
import com.readrops.readropsdb.dao.FeedDao;
import com.readrops.readropsdb.dao.FolderDao;
import com.readrops.readropsdb.dao.ItemDao;
import com.readrops.readropsdb.entities.Feed;
import com.readrops.readropsdb.entities.Folder;
import com.readrops.readropsdb.entities.Item;
import com.readrops.readropsdb.entities.account.Account;


@androidx.room.Database(entities = {Feed.class, Item.class, Folder.class, Account.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {

    public abstract FeedDao feedDao();

    public abstract ItemDao itemDao();

    public abstract FolderDao folderDao();

    public abstract AccountDao accountDao();

    private static Database database;

    public static Database getInstance(Context context) {
        if (database == null)
            database = Room.databaseBuilder(context, Database.class, "readrops-db")
                    .addMigrations(MIGRATION_1_2)
                    .build();

        return database;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter Table Account Add Column notifications_enabled INTEGER Not Null Default 0");

            database.execSQL("Alter Table Feed Add Column notification_enabled INTEGER Not Null Default 1");
        }
    };
}