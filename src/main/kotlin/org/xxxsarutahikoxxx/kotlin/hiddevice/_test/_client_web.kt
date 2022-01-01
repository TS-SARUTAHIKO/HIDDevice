package org.xxxsarutahikoxxx.kotlin.hiddevice._test

import org.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.ClientWebRunner
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import java.net.InetAddress


fun main(args: Array<String>) {
    val frame = TrayFrame("WebClient", false)

    HIDDeviceJIS(false, ClientWebRunner(InetAddress.getLocalHost().hostAddress,53456).apply { connect() })
}

