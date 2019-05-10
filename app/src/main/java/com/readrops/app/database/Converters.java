package com.readrops.app.database;

import androidx.room.TypeConverter;

import org.joda.time.LocalDateTime;

public class Converters {

    @TypeConverter
    public LocalDateTime fromTimeStamp(Long value) {
        return new LocalDateTime(value);
    }

    @TypeConverter
    public long fromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toDateTime().getMillis();
    }


}
