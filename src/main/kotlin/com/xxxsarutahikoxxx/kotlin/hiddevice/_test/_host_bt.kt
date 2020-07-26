package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.BTHostRunner
import com.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    TrayFrame("BT-Host", false).apply {
        val runner = BTHostRunner()
        runner.open()


        // これがないと読み込みのタイミング制御がうまくいかない
        thread {
            while(true){
                Thread.sleep(16)
                runner.writeObject(System.currentTimeMillis())
            }
        }

    }

}