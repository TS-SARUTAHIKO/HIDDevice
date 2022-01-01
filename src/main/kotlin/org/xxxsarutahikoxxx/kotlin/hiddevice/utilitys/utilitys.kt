package org.xxxsarutahikoxxx.kotlin.hiddevice.utilitys

import org.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceUS


internal val USHost = HIDDeviceUS(true)
fun USHost( action : HIDDeviceUS.()->(Unit) ){
    USHost.action()
}

internal val JISHost = HIDDeviceJIS(true)
fun JISHost( action : HIDDeviceJIS.()->(Unit) ){
    JISHost.action()
}

