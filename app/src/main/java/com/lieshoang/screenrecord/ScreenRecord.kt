/**
 * Designed and developed by Aidan Follestad (@afollestad)
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
@file:Suppress("unused")

package com.lieshoang.screenrecord

import android.app.Application
import com.lieshoang.screenrecord.BuildConfig.DEBUG
import com.lieshoang.screenrecord.common.commonModule
import com.lieshoang.screenrecord.common.prefModule
import com.lieshoang.screenrecord.di.mainModule
import com.lieshoang.screenrecord.di.viewModelModule
import com.lieshoang.screenrecord.engine.engineModule
import com.lieshoang.screenrecord.logging.FabricTree
import com.lieshoang.screenrecord.notifications.Notifications
import com.lieshoang.screenrecord.notifications.notificationsModule
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

/** @author Aidan Follestad (@afollestad) */
class ScreenRecord : Application() {

    override fun onCreate() {
        super.onCreate()

        if (DEBUG) {
            Timber.plant(DebugTree())
        }

        Timber.plant(FabricTree())
        Fabric.with(this, Crashlytics())

        startKoin {
            androidLogger()
            androidContext(this@ScreenRecord)
            modules(
                listOf(
                    commonModule,
                    notificationsModule,
                    prefModule,
                    engineModule,
                    mainModule,
                    viewModelModule
                )
            )
        }

        val notifications by inject<Notifications>()
        notifications.createChannels()
    }
}
