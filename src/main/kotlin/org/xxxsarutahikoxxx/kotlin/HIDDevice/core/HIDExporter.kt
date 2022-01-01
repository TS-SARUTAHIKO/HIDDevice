package org.xxxsarutahikoxxx.kotlin.HIDDevice.core

/**
 *
 * */
interface HIDExporter {
    fun exportTCP(data : HIDData)
    fun exportUDP(data : HIDData)
}