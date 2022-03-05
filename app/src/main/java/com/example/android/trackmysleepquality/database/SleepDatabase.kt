/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class],version=1,exportSchema=false)
abstract class SleepDatabase: RoomDatabase(){

    abstract val sleepDatabaseDao:SleepDatabaseDao

    companion object{

        //The volatile tag, prevents memory cache
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        /**
         * @param {Context} context - The context is the application
         * which can be found by calling returnNotNull(this.activity).application
         */
        fun getInstance(context: Context):SleepDatabase{
            //the sychronized block is for when multiple
            //threads request the database at the same
            //time, only one thread of execution can enter
            //this block of code at any time
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}
