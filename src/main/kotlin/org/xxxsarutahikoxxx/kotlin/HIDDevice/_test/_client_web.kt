package org.xxxsarutahikoxxx.kotlin.HIDDevice._test

import org.xxxsarutahikoxxx.kotlin.HIDDevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.HIDDevice.runner.ClientWebRunner
import org.xxxsarutahikoxxx.kotlin.HIDDevice.runner.TrayFrame
import java.net.InetAddress

fun main(args: Array<String>) {
    val frame = TrayFrame("WebClient", false)

    HIDDeviceJIS(false, ClientWebRunner(InetAddress.getLocalHost().hostAddress,53456).apply { connect() })
}

