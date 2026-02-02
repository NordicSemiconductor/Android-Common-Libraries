package no.nordicsemi.android.common.test.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import no.nordicsemi.kotlin.ble.core.android.AndroidEnvironment
import no.nordicsemi.kotlin.ble.environment.android.mock.MockAndroidEnvironment

@Module
@InstallIn(ActivityRetainedComponent::class)
object EnvironmentModule {

    @ActivityRetainedScoped
    @Provides
    fun provideEnvironment(
        lifecycle: ActivityRetainedLifecycle,
    ): MockAndroidEnvironment {
        // Set up the mock Environment. Check the documentation for more information.
        val env = MockAndroidEnvironment.Api31(
            isLocationPermissionGranted = false,
//            isLocationEnabled = false,
            isNeverForLocationFlagSet = false,
            isBluetoothScanPermissionGranted = false,
            isBluetoothConnectPermissionGranted = false,
            isBluetoothAdvertisePermissionGranted = false,
        )

        // Make sure the environment is closed when the lifecycle is cleared.
        // This will unregister the broadcast receiver.
        return env.also {
            lifecycle.addOnClearedListener { it.close() }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AndroidEnvironmentModule {

    @Binds
    abstract fun bindEnvironment(environment: MockAndroidEnvironment): AndroidEnvironment
}