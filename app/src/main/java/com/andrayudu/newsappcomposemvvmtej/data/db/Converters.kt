package com.andrayudu.newsappcomposemvvmtej.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.andrayudu.newsappcomposemvvmtej.data.model.Source

class Converters {

    /**
     * this class helps in converting the dataclass "Source" in "Article" for easier storing in the RoomDb
     * otherwise we have to annotate the dataclass "Source" as entity
     */


    @TypeConverter
    fun fromSourceToString(source:Source):String{
        return source.name
    }

    @TypeConverter
    fun toSourceFromString(name:String):Source{
        return Source(name,name)
    }
}