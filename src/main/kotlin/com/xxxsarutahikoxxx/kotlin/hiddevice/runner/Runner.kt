package com.xxxsarutahikoxxx.kotlin.hiddevice.runner

import com.xxxsarutahikoxxx.kotlin.SocketRunner.*
import com.xxxsarutahikoxxx.kotlin.Utilitys.outstream
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDData
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDExporter
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.MousePoint
import com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys.out
import java.io.Serializable
import java.lang.RuntimeException
import java.util.*
import javax.bluetooth.RemoteDevice


open class HostRunner(port : Int) : HostWebRunner(port, true){
    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> { obj.invoke() }
        }
    }
}

open class ClientRunner(address : String = "localhost", port : Int) : ClientWebRunner(address, port, true), HIDExporter {
    override fun export(data: HIDData){
        writeObject(data)
    }
}


open class BTHostRunner : HostBluetoothRunner {
    constructor() : super()
    constructor(uuid : String) : super(uuid)

    var time = System.currentTimeMillis() to System.currentTimeMillis()
    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> { obj.invoke() }
            is String -> { outstream("$obj") }
            is Long -> {
                val (send, accept) = obj to System.currentTimeMillis()

                out = "Send : ${send - time.first}, Accept : ${accept - time.second}"
                time = send to accept
            }
        }
    }
}
open class BTClientRunner : ClientBluetoothRunner {
    constructor(device : RemoteDevice) : super(device)
    constructor(device : RemoteDevice, uuid : String) : super(device, uuid)

    var time = System.currentTimeMillis() to System.currentTimeMillis()
    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> { obj.invoke() }
            is String -> { outstream("$obj") }
            is Long -> {
                val (send, accept) = obj to System.currentTimeMillis()

                out = "Send : ${send - time.first}, Accept : ${accept - time.second}"
                time = send to accept
            }
        }
    }
}