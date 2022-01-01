package org.xxxsarutahikoxxx.kotlin.HIDDevice._test

import org.xxxsarutahikoxxx.kotlin.HIDDevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.HIDDevice.runner.HostWebRunner
import org.xxxsarutahikoxxx.kotlin.HIDDevice.runner.TrayFrame

fun main(args: Array<String>) {
    val frame = TrayFrame("Web-Host", false)

    HIDDeviceJIS(true, HostWebRunner(53456).apply { open() })
}