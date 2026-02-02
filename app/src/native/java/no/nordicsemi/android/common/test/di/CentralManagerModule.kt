package no.nordicsemi.android.common.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import no.nordicsemi.kotlin.ble.client.android.CentralManager
import no.nordicsemi.kotlin.ble.client.android.native
import no.nordicsemi.kotlin.ble.environment.android.NativeAndroidEnvironment

@Module
@InstallIn(ActivityRetainedComponent::class)
object CentralManagerModule {

    @ActivityRetainedScoped
    @Provides
    fun provideCentralManager(
        environment: NativeAndroidEnvironment,
        scope: CoroutineScope
    ): CentralManager = CentralManager.native(environment, scope)
}