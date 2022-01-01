package org.xxxsarutahikoxxx.kotlin.hiddevice.runner

import org.xxxsarutahikoxxx.kotlin.IORunner.*
import org.xxxsarutahikoxxx.kotlin.Utilitys.out
import org.xxxsarutahikoxxx.kotlin.hiddevice.core.*
import java.io.Serializable
import java.lang.RuntimeException

open class HostWebRunner(port : Int) : HostTCPRunner(port), HIDExporter {
    val udp = object : HostUDPRunner(port+1, 500){
        override fun onAccept(obj: Serializable) {
            this@HostWebRunner.onAccept(obj)
        }
    }

    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> { obj.invoke() }
        }
    }

    override fun exportTCP(data: HIDData) = throw RuntimeException("")
    override fun exportUDP(data: HIDData) = throw RuntimeException("")

    override fun openPort() {
        super.openPort()
        udp.open()
    }
}

open class ClientWebRunner(address : String, port : Int) : ClientTCPRunner(address, port), HIDExporter {
    val udp = ClientUDPRunner(address, port+1, 500, false)

    override fun exportTCP(data: HIDData){
        writeObject(data)
    }
    override fun exportUDP(data: HIDData) {
        udp.writeObject(data)
    }

    override fun connectPort() {
        super.connectPort()
        udp.connect()
    }
}
