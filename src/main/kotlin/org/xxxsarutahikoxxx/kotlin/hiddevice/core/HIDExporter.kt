package org.xxxsarutahikoxxx.kotlin.hiddevice.core

/**
 *
 * */
interface HIDExporter {
    fun exportTCP(data : HIDData)
    fun exportUDP(data : HIDData)
}