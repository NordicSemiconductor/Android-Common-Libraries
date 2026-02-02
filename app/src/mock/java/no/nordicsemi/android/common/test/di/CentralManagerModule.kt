package no.nordicsemi.android.common.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import no.nordicsemi.kotlin.ble.client.android.CentralManager
import no.nordicsemi.kotlin.ble.client.android.mock.mock
import no.nordicsemi.kotlin.ble.client.mock.PeripheralSpec
import no.nordicsemi.kotlin.ble.client.mock.Proximity
import no.nordicsemi.kotlin.ble.core.LegacyAdvertisingSetParameters
import no.nordicsemi.kotlin.ble.environment.android.mock.MockAndroidEnvironment
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(ActivityRetainedComponent::class)
object CentralManagerModule {

    private val hrm = PeripheralSpec
        .simulatePeripheral(
            identifier = "11:22:33:44:55:66",
            proximity = Proximity.FAR,
        ) {
            advertising(
                parameters = LegacyAdvertisingSetParameters(
                    connectable = true,
                    interval = 500.milliseconds,
                    txPowerLevel = -7,
                ),
            ) {
                ServiceUuid(shortUuid = 0x180D) // Heart Rate Service
                CompleteLocalName("Nordic HRM")
            }
        }

    private val beacon = PeripheralSpec.simulatePeripheral(
        identifier = "DE:AD:BE:EF:FE:ED",
        proximity = Proximity.NEAR
    ) {
        advertising(
            parameters = LegacyAdvertisingSetParameters(
                connectable = false,
                interval = 1.seconds,
            ),
            // Beacons are excluded if "neverForLocation" flag is disabled.
            isBeacon = true,
        ) {
            CompleteLocalName("Nordic_Beacon")
            ServiceUuid(shortUuid = 0xFEAA) // Eddystone UUID
            IncludeTxPowerLevel()
        }
    }

    private val meshBeacon = PeripheralSpec.simulatePeripheral(
        identifier = "01:23:45:67:89:AB",
        proximity = Proximity.IMMEDIATE
    ) {
        advertising(
            parameters = LegacyAdvertisingSetParameters(
                connectable = false,
                interval = 2.seconds,
            )
        ) {
            MeshBeacon("0100de3e35ab61d19f5d00000000bcd9a6ebd1a23c0c".hexToByteArray())
        }
    }

    @ActivityRetainedScoped
    @Provides
    fun provideCentralManager(
        environment: MockAndroidEnvironment,
        scope: CoroutineScope
    ): CentralManager = CentralManager.mock(environment, scope)
        .apply {
            simulatePeripherals(listOf(hrm, beacon, meshBeacon))
        }
}