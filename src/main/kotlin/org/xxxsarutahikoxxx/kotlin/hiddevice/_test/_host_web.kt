package org.xxxsarutahikoxxx.kotlin.hiddevice._test

import org.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.HostWebRunner
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame

fun main(args: Array<String>) {
    val frame = TrayFrame("Web-Host", false)

    HIDDeviceJIS(true, HostWebRunner(53456).apply { open() })
}