package org.xxxsarutahikoxxx.kotlin.HIDDevice.utilitys

import org.xxxsarutahikoxxx.kotlin.HIDDevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.HIDDevice.core.HIDDeviceUS


internal val USHost = HIDDeviceUS(true)
fun USHost( action : HIDDeviceUS.()->(Unit) ){
    USHost.action()
}

internal val JISHost = HIDDeviceJIS(true)
fun JISHost( action : HIDDeviceJIS.()->(Unit) ){
    JISHost.action()
}

