package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDData
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.ClientRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDExporter

fun main(args: Array<String>) {
    val frame = TrayFrame("WebClient", false)

    HIDDeviceJIS(false, ClientRunner(port = 50001)).apply {
        (port as? ClientRunner)?.connect()
    }
}

