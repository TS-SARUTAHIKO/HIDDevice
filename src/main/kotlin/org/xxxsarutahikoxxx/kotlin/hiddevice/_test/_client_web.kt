package org.xxxsarutahikoxxx.kotlin.hiddevice._test

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDDeviceJIS
import org.xxxsarutahikoxxx.kotlin.hiddevice.core.MousePoint
import org.xxxsarutahikoxxx.kotlin.hiddevice.core.MouseShift
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.ClientWebRunner
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.HostWebRunner
import org.xxxsarutahikoxxx.kotlin.hiddevice.runner.TrayFrame
import java.net.InetAddress
import kotlin.concurrent.thread
import kotlin.random.Random

fun main(args: Array<String>) {
    val frame = TrayFrame("WebClient", false)

    HIDDeviceJIS(false, ClientWebRunner(InetAddress.getLocalHost().hostAddress,53456).apply { connect() })
}

