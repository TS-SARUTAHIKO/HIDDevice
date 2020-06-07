package com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys

import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceUS
import java.lang.RuntimeException

internal var out : Any?
    get() = throw RuntimeException("入力のみのフィールドです")
    set(value) { println(value) }



internal val USHost = HIDDeviceUS(true)
fun USHost( action : HIDDeviceUS.()->(Unit) ){
    USHost.action()
}

internal val JISHost = HIDDeviceJIS(true)
fun JISHost( action : HIDDeviceJIS.()->(Unit) ){
    JISHost.action()
}

