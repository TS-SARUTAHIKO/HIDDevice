package org.xxxsarutahikoxxx.kotlin.HIDDevice.runner

import org.xxxsarutahikoxxx.kotlin.KotlinLibrary.Utilitys.outstream
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.JFrame
import kotlin.system.exitProcess

class TrayFrame(title : String, pushTray : Boolean) : JFrame() {

    val tray = TrayIcon(ImageIO.read(ClassLoader.getSystemResource("Java.png"))).apply {
        popupMenu = PopupMenu("sample").apply {

        }

        toolTip = title
        addMouseListener(object : MouseAdapter(){
            override fun mouseClicked(e: MouseEvent) {
                when( e.button ){
                    MouseEvent.BUTTON1 -> { this@TrayFrame.isVisible = true }
                    MouseEvent.BUTTON2 -> {  }
                    MouseEvent.BUTTON3 -> { exitProcess(0) }
                }
            }
        })
    }
    val text = TextArea()

    init {
        outstream = { value -> text.append("$value"+"\n") ; println("$value") }

        layout = BorderLayout()
        add(text, BorderLayout.CENTER)
        add(Button("Close").apply { addActionListener{
            exitProcess(0)
        } }, BorderLayout.SOUTH)

        setBounds(200, 200, 600, 400)

        if( pushTray ){
            SystemTray.getSystemTray().add(tray)
        }else{
            defaultCloseOperation = EXIT_ON_CLOSE
            isVisible = true
        }
    }
}