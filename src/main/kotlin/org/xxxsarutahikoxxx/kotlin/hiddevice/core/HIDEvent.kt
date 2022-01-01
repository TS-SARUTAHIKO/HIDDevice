package org.xxxsarutahikoxxx.kotlin.hiddevice.core

import org.xxxsarutahikoxxx.kotlin.hiddevicenative.HID
import java.io.Serializable


/**
 * HID データのルート・クラス
 *
 * invoke() : デスクトップ上でのHIDエミュレートを実行する
 *
 * */
interface HIDData : Serializable {
    val time : Long
    /** データ送信をする際に UDP / TCP のどちらで行うか */
    val udp : Boolean get() = this is MousePoint

    fun invoke()
}
/**
 * HID データのリスト化ラッパークラス
 *
 * リスト内のHIDデータを順に実行する
 * */
open class HIDList (
    val list: List<HIDData>,
    /** 区切り待機時間。 10msec 程度の待機時間がないと処理の取りこぼしが発生する。 */
    val delimiter : Long = 10
) : HIDData {
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        list.forEach {
            it.invoke()
            Thread.sleep(delimiter)
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

/**
 * HID_IO データの Shift・メタラッパークラス
 *
 * Shift が押されてない状態なら Shift を押したのちにリストを実行し Shift を離す
 * */
open class HIDwithShift(list: List<HIDData>) : HIDList(list) {
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        if( HID.isShiftPressed ){
            super.invoke()
        }else{
            HID.keyPress(HID.VK_Shift)
            super.invoke()
            HID.keyRelease(HID.VK_Shift)
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/**
 * HID_IO データの Control・メタラッパークラス
 *
 * Shift が押されてない状態なら Shift を押したのちにリストを実行し Shift を離す
 * */
open class HIDwithControl(list: List<HIDData>) : HIDList(list) {
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        if( HID.isControlPressed ){
            super.invoke()
        }else{
            HID.keyPress(HID.VK_Control)
            super.invoke()
            HID.keyRelease(HID.VK_Control)
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/**
 * HID_IO データの Alt・メタラッパークラス
 *
 * Shift が押されてない状態なら Shift を押したのちにリストを実行し Shift を離す
 * */
open class HIDwithAlt(list: List<HIDData>) : HIDList(list) {
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        if( HID.isAltPressed ){
            super.invoke()
        }else{
            HID.keyPress(HID.VK_Alt)
            super.invoke()
            HID.keyRelease(HID.VK_Alt)
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

val HIDData.withShift : HIDData get() = HIDwithShift( listOf(this) )
val HIDData.withControl : HIDData get() = HIDwithControl( listOf(this) )
val HIDData.withAlt : HIDData get() = HIDwithAlt( listOf(this) )


// Mouse
/** マウス関係イベント */
interface MouseHID : HIDData

/** マウスポインタ関係イベント */
interface MousePoint : MouseHID {
    val x : Int
    val y : Int
}
/** マウスポインタの指定位置への移動イベント */
class MouseMove(override val x: Int, override val y: Int) : MousePoint {
    override val time: Long = System.currentTimeMillis()
    override fun toString(): String = "Move ($x, $y)"

    override fun invoke() {
        HID.mouseMove(x, y)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/** マウスポインタの現在位置からの移動イベント */
class MouseShift(override val x: Int, override val y: Int) : MousePoint {
    override fun toString(): String = "Shift ($x, $y)"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.mouseShift(x, y)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

/** マウスボタン関係イベント */
interface MouseButton : MouseHID {
    val mouseCode : Int
}
class MousePress(override val mouseCode: Int) : MouseButton {
    override fun toString(): String = "M-Press $mouseCode"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.mousePress(mouseCode)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
class MouseRelease(override val mouseCode: Int) : MouseButton {
    override fun toString(): String = "M-Release $mouseCode"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.mouseRelease(mouseCode)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
class MouseClick(
    override val mouseCode: Int
) : MouseButton, HIDList( list= listOf( MousePress(mouseCode), MouseRelease(mouseCode) )) {
    override fun toString(): String = "M-Click $mouseCode"

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

class MouseWheel(val notches : Int) : MouseHID {
    override fun toString(): String = "M-Wheel $notches"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.mouseWheel(notches)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}


// Keyboard
interface KeyboardHID : HIDData {
    val keyCode : Int
}

/** KeyPress イベントを行います */
class KeyPress(override val keyCode: Int) : KeyboardHID {
    override fun toString(): String = "K-Press $keyCode"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.keyPress(keyCode)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/** KeyRelease イベントを行います */
class KeyRelease(override val keyCode: Int) : KeyboardHID {
    override fun toString(): String = "K-Release $keyCode"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        HID.keyRelease(keyCode)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/** キークリックになるように KeyPress / KeyRelease を行います */
open class KeyClick(
    override val keyCode: Int
) : KeyboardHID, HIDList(list = listOf( KeyPress(keyCode), KeyRelease(keyCode) )) {
    override fun toString(): String = "K-Click $keyCode"

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}


// Sleep
/** スレッドを待機します */
class SleepHID(val millis: Long) : HIDData {
    override fun toString(): String = "Sleep $millis"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        Thread.sleep(millis)
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

/** イベントの前に待機時間を挟む形でラップします */
fun HIDData.withPreSleep(millis: Long) = HIDList( listOf( SleepHID(millis), this) )
/** イベントの後に待機時間を挟む形でラップします */
fun HIDData.withPostSleep(millis: Long) = HIDList( listOf( this, SleepHID(millis)) )


// 条件実行
interface ConditionHID : HIDData

/**
 * アクティブなウィンドウのタイトルが正規表現を満たしていた場合に実行する
 *
 * @param mode : 0 -> 完全一致, 1 -> 部分一致
 * */
class WindowTitle(val regex : List<String>, val mode : Int, val data : HIDData) : ConditionHID {
    constructor(data : HIDData, mode : Int, vararg regex : String) : this(regex.toList(), mode, data)

    override fun toString(): String = "WindowName $regex"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        val title = HID.activeWindowTitle()

        val func : String.(Regex)->(Boolean) = when(mode){
            0 -> String::matches
            else -> String::contains
        }

        if( regex.any { title.func(Regex(it)) } ){
            data.invoke()
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}
/**
 * アクティブなプロセスが正規表現を満たしていた場合に実行する
 *
 * @param mode : 0 -> 完全一致, 1 -> 部分一致
 * */
class WindowProcess(val regex : List<String>, val mode : Int, val data : HIDData) : ConditionHID {
    constructor(data : HIDData, mode : Int, vararg regex : String) : this( regex.toList(), mode, data)

    override fun toString(): String = "ProcessName $regex"
    override val time: Long = System.currentTimeMillis()

    override fun invoke() {
        val title = HID.activeWindowProcessName()

        val func : String.(Regex)->(Boolean) = when(mode){
            0 -> String::matches
            else -> String::contains
        }

        if( regex.any { title.func(Regex(it)) } ){
            data.invoke()
        }
    }

    companion object {
        @JvmStatic private val serialVersionUID: Long = 1L
    }
}

fun HIDData.ifTitleMatches(vararg regex : String) = WindowTitle(regex.toList(), 0, this)
fun HIDData.ifTitleContains(vararg regex : String) = WindowTitle(regex.toList(), 1, this)
fun HIDData.ifProcessMatches(vararg regex : String) = WindowProcess(regex.toList(), 0, this)
fun HIDData.ifProcessContains(vararg regex : String) = WindowProcess(regex.toList(), 1, this)
