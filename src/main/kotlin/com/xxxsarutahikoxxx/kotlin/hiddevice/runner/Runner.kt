package com.xxxsarutahikoxxx.kotlin.hiddevice.runner

import com.xxxsarutahikoxxx.kotlin.SocketRunner.*
import com.xxxsarutahikoxxx.kotlin.Utilitys.out
import com.xxxsarutahikoxxx.kotlin.Utilitys.outstream
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.*
import java.io.Serializable
import javax.bluetooth.RemoteDevice


open class HostRunner : HostWebRunner {
    constructor(port : Int) : super(port)
    constructor() : super()

    var time = System.currentTimeMillis() to System.currentTimeMillis()
    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> { obj.invoke() }
            is Long -> {
                val (send, accept) = obj to System.currentTimeMillis()

                out = "Send : ${send - time.first}, Accept : ${accept - time.second}"
                time = send to accept
            }
        }
    }
}

open class ClientRunner : ClientWebRunner, HIDExporter {
    constructor(address : String, port : Int) : super(address, port)
    constructor() : super()

    override fun export(data: HIDData){
        writeObject(data)
    }
}


open class BTHostRunner : HostBluetoothRunner {
    constructor(uuid : String) : super(uuid)
    constructor() : super()

    var time = System.currentTimeMillis() to System.currentTimeMillis()
    override fun onAccept(obj: Serializable) {
        when( obj ){
            is HIDData -> {
                obj.invoke()

                when( obj ){
                    is KeyPress   -> out = "Press : ${HIDField.VK_Name(obj.keyCode)}"
                    is KeyRelease -> out = "Release : ${HIDField.VK_Name(obj.keyCode)}"
                    is KeyClick -> out = "Click : ${HIDField.VK_Name(obj.keyCode)}"
                }
            }
            is String -> { outstream("$obj") }
            is Long -> {
                val (send, accept) = obj to System.currentTimeMillis()

                out = "Send : ${send - time.first}, Accept : ${accept - time.second}"
                time = send to accept
            }
        }
    }
}
open class BTClientRunner : ClientBluetoothRunner, HIDExporter {
    constructor(device : RemoteDevice, uuid : String) : super(device, uuid)
    constructor(device : RemoteDevice) : super(device)

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

    override fun export(data: HIDData){
        writeObject(data)
    }
}