package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.BTHostRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import com.xxxsarutahikoxxx.kotlin.SocketRunner.BluetoothRunner
import com.xxxsarutahikoxxx.kotlin.SocketRunner.SocketRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDData
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDExporter
import com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys.JISHost
import com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys.out
import java.awt.Robot
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    TrayFrame("BT-Host", false).apply {
        val runner = BTHostRunner(UUID.fromString(BluetoothRunner.BLUETOOTH_RUNNER_DEFAULT_UUID))
        runner.open()

        // これがないと読み込みのタイミング制御がうまくいかない
//        thread {
//            while(true){
//                Thread.sleep(33)
//                runner.writeObject(System.currentTimeMillis())
//            }
//        }
    }

}