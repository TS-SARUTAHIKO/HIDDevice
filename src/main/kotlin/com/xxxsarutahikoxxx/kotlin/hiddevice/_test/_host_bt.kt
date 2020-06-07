package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.BTHostRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import com.xxxsarutahikoxxx.kotlin.SocketRunner.BluetoothRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDData
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDExporter
import java.util.*

fun main(args: Array<String>) {
    val frame = TrayFrame("BT-Host", false)

    HIDDeviceJIS(
        true,
        BTHostRunner(UUID.fromString(BluetoothRunner.BLUETOOTH_RUNNER_DEFAULT_UUID)).run {
            object : HIDExporter{
                override fun export(data: HIDData) {}
            }
        }
    ).apply {
        (port as? BTHostRunner)?.open()
    }
}