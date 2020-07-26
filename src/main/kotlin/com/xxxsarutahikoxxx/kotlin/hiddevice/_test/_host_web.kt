package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDData
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.HostRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDExporter

fun main(args: Array<String>) {
    val frame = TrayFrame("WebHost", false)

    HIDDeviceJIS(true, HostRunner().run {
        object : HIDExporter {
            override fun export(data: HIDData) {}
        }
    }).apply {
        (port as? HostRunner)?.open()
    }
}