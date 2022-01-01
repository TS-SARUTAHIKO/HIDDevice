package org.xxxsarutahikoxxx.kotlin.hiddevice.core

import org.xxxsarutahikoxxx.kotlin.hiddeviceNative.HID
import java.lang.RuntimeException


/**
 * HID をエミュレートするためのクラス（マウス・キーボード）
 *
 * [hostMode] = true の場合はHIDイベントを実行します、[hostMode] = false の場合は [port] からHIDイベントを送信します。<br>
 *
 * [hostMode] = false はモバイル端末などから接続したホストのHIDイベントを遠隔操作するための機能です<br>
 * [HIDExporter] は送信するためのポートなどをラップするためのインターフェースでです<br>
 * ホスト側でもこのライブラリーを実装したアプリケーションを実行し、同期することで遠隔操作が可能になります
 *
 * */
open class HIDDevice(val hostMode : Boolean, var port : HIDExporter?, open val keyMap : KeyMap) {
    fun invoke(data : HIDData){
        when {
            hostMode -> data.invoke()
            data.udp -> port?.exportUDP(data)
            ! data.udp -> port?.exportTCP(data)
        }
    }

    // + HIDDeviceNative
    operator fun HIDData.unaryPlus() : HIDDevice {
        invoke(this)

        return this@HIDDevice
    }
    operator fun Char.unaryPlus() : HIDDevice {
        + keyMap.toKeyData(this).toKeyClick

        return this@HIDDevice
    }
    operator fun String.unaryPlus() : HIDDevice {
        this.forEach { + it }

        return this@HIDDevice
    }

    operator fun HIDDevice.plus(data : HIDData) : HIDDevice = data.unaryPlus()
    operator fun HIDDevice.plus(data : Char) : HIDDevice = data.unaryPlus()
    operator fun HIDDevice.plus(data : String) : HIDDevice = data.unaryPlus()

    operator fun HIDDevice.plus(hid : HIDDevice) : HIDDevice = this


    // Mask
    /**
     * shift キーの状態を返します。
     *
     * HostMode ならばシステムに問い合わせます、値はシステムの状態に一致します
     *
     * ClientMode ならば設定された値を返します、ホストの値と異なる場合があります、適切にホストと状態同期を行ってください
     *
     * 値を設定すると必要に応じて [keyPress] / [keyRelease] が発行されます
     * */
    var isShiftPressed : Boolean = false
        get(){
            if( hostMode ) field = HID.isShiftPressed
            return field
        }
        set(value) {
            if( field != value ){
                field = value

                when{
                    field -> keyPress(keyMap.VK_Shift.first)
                    ! field -> keyRelease(keyMap.VK_Shift.first)
                }
            }
        }
    /**
     * control キーの状態を返します。
     *
     * HostMode ならばシステムに問い合わせます、値はシステムの状態に一致します
     *
     * ClientMode ならば設定された値を返します、ホストの値と異なる場合があります、適切にホストと状態同期を行ってください
     *
     * 値を設定すると必要に応じて [keyPress] / [keyRelease] が発行されます
     * */
    var isControlPressed : Boolean = false
        get(){
            if( hostMode ) field = HID.isControlPressed
            return field
        }
        set(value) {
            if( field != value ){
                field = value

                when{
                    field -> keyPress(keyMap.VK_Control.first)
                    ! field -> keyRelease(keyMap.VK_Control.first)
                }
            }
        }
    /**
     * alt キーの状態を返します。
     *
     * HostMode ならばシステムに問い合わせます、値はシステムの状態に一致します
     *
     * ClientMode ならば設定された値を返します、ホストの値と異なる場合があります、適切にホストと状態同期を行ってください
     *
     * 値を設定すると必要に応じて [keyPress] / [keyRelease] が発行されます
     * */
    var isAltPressed : Boolean = false
        get(){
            if( hostMode ) field = HID.isAltPressed
            return field
        }
        set(value) {
            if( field != value ){
                field = value

                when{
                    field -> keyPress(keyMap.VK_Alt.first)
                    ! field -> keyRelease(keyMap.VK_Alt.first)
                }
            }
        }


    //
    fun HIDData.x(num : Int) = HIDList( Array(num){ this }.toList() )
    val HIDData.x5 get() = this.x(5)
    val HIDData.x4 get() = this.x(4)
    val HIDData.x3 get() = this.x(3)
    val HIDData.x2 get() = this.x(2)


    //
    fun Sleep(millis : Long) = SleepHID(millis)

    //
    fun keyClick(keyCode : Int){
        invoke(KeyClick(keyCode))
    }
    fun keyPress(keyCode : Int){
        invoke(KeyPress(keyCode))
    }
    fun keyRelease(keyCode : Int){
        invoke(KeyRelease(keyCode))
    }

    //
    fun onClick(keyCode : Int, action : HIDDevice.()->(Unit) = {} ) : HIDDevice {
        keyPress(keyCode)
        action()
        keyRelease(keyCode)

        return this
    }
    /**
     * shift を押した状態で処理を行った後に shift を離します
     *
     * shift の press / release は現状の shift の状態にかかわらず必ず行われます
     *  */
    fun Shift(action : HIDDevice.()->(Unit) = {} ) = onClick( keyMap.VK_Shift.first, action)
    /**
     * control を押した状態で処理を行った後に control を離します
     *
     * control の press / release は現状の control の状態にかかわらず必ず行われます
     *  */
    fun Ctrl(action : HIDDevice.()->(Unit) = {} ) = onClick( keyMap.VK_Control.first, action)
    /**
     * alt を押した状態で処理を行った後に alt を離します
     *
     * alt の press / release は現状の alt の状態にかかわらず必ず行われます
     *  */
    fun Alt(action : HIDDevice.()->(Unit) = {} ) = onClick( keyMap.VK_Alt.first, action)
    /**
     * win を押した状態で処理を行った後に win を離します
     *
     * win の press / release は現状の win の状態にかかわらず必ず行われます
     *  */
    fun Win(action : HIDDevice.()->(Unit) = {} ) = onClick( keyMap.VK_LWindows.first, action)

    fun onUnClick(keyCode : Int, action : HIDDevice.()->(Unit) = {} ) : HIDDevice {
        keyRelease(keyCode)
        action()
        keyPress(keyCode)

        return this
    }
    /**
     * shift を離した状態で処理を行った後に shift を押します
     *
     * shift の release / press は現状の shift の状態にかかわらず必ず行われます
     *  */
    fun UnShift(action : HIDDevice.()->(Unit) = {} ) = onUnClick( keyMap.VK_Shift.first, action)
    /**
     * control を離した状態で処理を行った後に control を押します
     *
     * control の release / press は現状の control の状態にかかわらず必ず行われます
     *  */
    fun UnCtrl(action : HIDDevice.()->(Unit) = {} ) = onUnClick( keyMap.VK_Control.first, action)
    /**
     * alt を離した状態で処理を行った後に alt を押します
     *
     * alt の release / press は現状の alt の状態にかかわらず必ず行われます
     *  */
    fun UnAlt(action : HIDDevice.()->(Unit) = {} ) = onUnClick( keyMap.VK_Alt.first, action)
    /**
     * win を離した状態で処理を行った後に win を押します
     *
     * win の release / press は現状の win の状態にかかわらず必ず行われます
     *  */
    fun UnWin(action : HIDDevice.()->(Unit) = {} ) = onUnClick( keyMap.VK_LWindows.first, action)




    //
    val _1 get() = keyMap.VK_1.toKeyClick
    val _2 get() = keyMap.VK_2.toKeyClick
    val _3 get() = keyMap.VK_3.toKeyClick
    val _4 get() = keyMap.VK_4.toKeyClick
    val _5 get() = keyMap.VK_5.toKeyClick
    val _6 get() = keyMap.VK_6.toKeyClick
    val _7 get() = keyMap.VK_7.toKeyClick
    val _8 get() = keyMap.VK_8.toKeyClick
    val _9 get() = keyMap.VK_9.toKeyClick
    val _0 get() = keyMap.VK_0.toKeyClick
    val F1 get() = keyMap.VK_F1.toKeyClick
    val F2 get() = keyMap.VK_F2.toKeyClick
    val F3 get() = keyMap.VK_F3.toKeyClick
    val F4 get() = keyMap.VK_F4.toKeyClick
    val F5 get() = keyMap.VK_F5.toKeyClick
    val F6 get() = keyMap.VK_F6.toKeyClick
    val F7 get() = keyMap.VK_F7.toKeyClick
    val F8 get() = keyMap.VK_F8.toKeyClick
    val F9 get() = keyMap.VK_F9.toKeyClick
    val F10 get() = keyMap.VK_F10.toKeyClick
    val F11 get() = keyMap.VK_F11.toKeyClick
    val F12 get() = keyMap.VK_F12.toKeyClick
    val _A get() = keyMap.VK_a.toKeyClick
    val _B get() = keyMap.VK_b.toKeyClick
    val _C get() = keyMap.VK_c.toKeyClick
    val _D get() = keyMap.VK_d.toKeyClick
    val _E get() = keyMap.VK_e.toKeyClick
    val _F get() = keyMap.VK_f.toKeyClick
    val _G get() = keyMap.VK_g.toKeyClick
    val _H get() = keyMap.VK_h.toKeyClick
    val _I get() = keyMap.VK_i.toKeyClick
    val _J get() = keyMap.VK_j.toKeyClick
    val _K get() = keyMap.VK_k.toKeyClick
    val _L get() = keyMap.VK_l.toKeyClick
    val _M get() = keyMap.VK_m.toKeyClick
    val _N get() = keyMap.VK_n.toKeyClick
    val _O get() = keyMap.VK_o.toKeyClick
    val _P get() = keyMap.VK_p.toKeyClick
    val _Q get() = keyMap.VK_q.toKeyClick
    val _R get() = keyMap.VK_r.toKeyClick
    val _S get() = keyMap.VK_s.toKeyClick
    val _T get() = keyMap.VK_t.toKeyClick
    val _U get() = keyMap.VK_u.toKeyClick
    val _V get() = keyMap.VK_v.toKeyClick
    val _W get() = keyMap.VK_w.toKeyClick
    val _X get() = keyMap.VK_x.toKeyClick
    val _Y get() = keyMap.VK_y.toKeyClick
    val _Z get() = keyMap.VK_z.toKeyClick
    val BackQuote get() = keyMap.VK_BackQuote.toKeyClick
    val Tilde get() = keyMap.VK_Tilde.toKeyClick
    val Minus get() = keyMap.VK_Minus.toKeyClick
    val Underscore get() = keyMap.VK_Underscore.toKeyClick
    val Equals get() = keyMap.VK_Equals.toKeyClick
    val Plus get() = keyMap.VK_Plus.toKeyClick
    val OpenBracket get() = keyMap.VK_OpenBracket.toKeyClick
    val LeftCurlyBrace get() = keyMap.VK_LeftCurlyBrace.toKeyClick
    val CloseBracket get() = keyMap.VK_CloseBracket.toKeyClick
    val RightCurlyBrace get() = keyMap.VK_RightCurlyBrace.toKeyClick
    val SemiColon get() = keyMap.VK_SemiColon.toKeyClick
    val Colon get() = keyMap.VK_Colon.toKeyClick
    val Quote get() = keyMap.VK_Quote.toKeyClick
    val DoubleQuote get() = keyMap.VK_DoubleQuote.toKeyClick
    val BackSlash  get() = keyMap.VK_BackSlash .toKeyClick
    val VerticalBar get() = keyMap.VK_VerticalBar.toKeyClick
    val Comma get() = keyMap.VK_Comma.toKeyClick
    val Less get() = keyMap.VK_Less.toKeyClick
    val Period get() = keyMap.VK_Period.toKeyClick
    val Greater get() = keyMap.VK_Greater.toKeyClick
    val Slash get() = keyMap.VK_Slash.toKeyClick
    val Question get() = keyMap.VK_Question.toKeyClick
    val Exclamation get() = keyMap.VK_Exclamation.toKeyClick
    val At get() = keyMap.VK_At.toKeyClick
    val NumberSign get() = keyMap.VK_NumberSign.toKeyClick
    val Dollars get() = keyMap.VK_Dollars.toKeyClick
    val Percent get() = keyMap.VK_Percent.toKeyClick
    val Circumflex get() = keyMap.VK_Circumflex.toKeyClick
    val Ampersand get() = keyMap.VK_Ampersand.toKeyClick
    val Asterisk get() = keyMap.VK_Asterisk.toKeyClick
    val LeftParenthesis get() = keyMap.VK_LeftParenthesis.toKeyClick
    val RightParenthesis get() = keyMap.VK_RightParenthesis.toKeyClick
    val Escape get() = keyMap.VK_Escape.toKeyClick
    val Tab get() = keyMap.VK_Tab.toKeyClick
    val CapsLock get() = keyMap.VK_CapsLock.toKeyClick
    val Space get() = keyMap.VK_Space.toKeyClick
    val ContextMenu get() = keyMap.VK_ContextMenu.toKeyClick
    val Enter get() = keyMap.VK_Enter.toKeyClick
    val BackSpace get() = keyMap.VK_BackSpace.toKeyClick
    val Shift get() = keyMap.VK_Shift.toKeyClick
    val Ctrl get() = keyMap.VK_Control.toKeyClick
    val Alt get() = keyMap.VK_Alt.toKeyClick
    val LShift get() = keyMap.VK_LShift.toKeyClick
    val LCtrl get() = keyMap.VK_LControl.toKeyClick
    val LWindows get() = keyMap.VK_LWindows.toKeyClick
    val LAlt get() = keyMap.VK_LAlt.toKeyClick
    val RShift get() = keyMap.VK_RShift.toKeyClick
    val RCtrl get() = keyMap.VK_RControl.toKeyClick
    val RWindows get() = keyMap.VK_RWindows.toKeyClick
    val RAlt get() = keyMap.VK_RAlt.toKeyClick
    val PrintScreen get() = keyMap.VK_PrintScreen.toKeyClick
    val ScrollLock get() = keyMap.VK_ScrollLock.toKeyClick
    val Pause get() = keyMap.VK_Pause.toKeyClick
    val Insert get() = keyMap.VK_Insert.toKeyClick
    val Home get() = keyMap.VK_Home.toKeyClick
    val Delete get() = keyMap.VK_Delete.toKeyClick
    val End get() = keyMap.VK_End.toKeyClick
    val PageUp get() = keyMap.VK_PageUp.toKeyClick
    val PageDown get() = keyMap.VK_PageDown.toKeyClick
    val Up get() = keyMap.VK_Up.toKeyClick
    val Down get() = keyMap.VK_Down.toKeyClick
    val Left get() = keyMap.VK_Left.toKeyClick
    val Right get() = keyMap.VK_Right.toKeyClick
    val NumLock get() = keyMap.VK_NumLock.toKeyClick
    val Num0 get() = keyMap.VK_Num0.toKeyClick
    val Num1 get() = keyMap.VK_Num1.toKeyClick
    val Num2 get() = keyMap.VK_Num2.toKeyClick
    val Num3 get() = keyMap.VK_Num3.toKeyClick
    val Num4 get() = keyMap.VK_Num4.toKeyClick
    val Num5 get() = keyMap.VK_Num5.toKeyClick
    val Num6 get() = keyMap.VK_Num6.toKeyClick
    val Num7 get() = keyMap.VK_Num7.toKeyClick
    val Num8 get() = keyMap.VK_Num8.toKeyClick
    val Num9 get() = keyMap.VK_Num9.toKeyClick
    val NumDivide get() = keyMap.VK_NumDivide.toKeyClick
    val NumMultiply get() = keyMap.VK_NumMultiply.toKeyClick
    val NumSubtract get() = keyMap.VK_NumSubtract.toKeyClick
    val NumAdd get() = keyMap.VK_NumAdd.toKeyClick
    val NumDecimal get() = keyMap.VK_NumDecimal.toKeyClick


    // Mouse Press / Release
    val LClick get() = keyMap.VK_MouseLeft.toMouseClick
    val MClick get() = keyMap.VK_MouseMiddle.toMouseClick
    val RClick get() = keyMap.VK_MouseRight.toMouseClick
    val Ex1Click get() = keyMap.VK_MouseEx1.toMouseClick
    val Ex2Click get() = keyMap.VK_MouseEx2.toMouseClick

    val LPress get() = keyMap.VK_MouseLeft.toMousePress
    val MPress get() = keyMap.VK_MouseMiddle.toMousePress
    val RPress get() = keyMap.VK_MouseRight.toMousePress
    val Ex1Press get() = keyMap.VK_MouseEx1.toMousePress
    val Ex2Press get() = keyMap.VK_MouseEx2.toMousePress

    val LRelease get() = keyMap.VK_MouseLeft.toMouseRelease
    val MRelease get() = keyMap.VK_MouseMiddle.toMouseRelease
    val RRelease get() = keyMap.VK_MouseRight.toMouseRelease
    val Ex1Release get() = keyMap.VK_MouseEx1.toMouseRelease
    val Ex2Release get() = keyMap.VK_MouseEx2.toMouseRelease


    /**
     * マウスの左ボタン を押した状態で処理を行った後に離します
     *
     * 処理の前後には待機時間[millis]があります。（待機時間がないとマウスのプレス処理が正常に処理されないため）
     *
     * ボタンの press / release は現状のボタンの状態にかかわらず必ず行われます
     *  */
    fun LClick( millis : Long = 20, action : HIDDevice.()->(Unit) = {} ){
        + LPress
        + Sleep(millis)

        action()

        + Sleep(millis)
        + LRelease
    }
    /**
     * マウスの中ボタン を押した状態で処理を行った後に離します
     *
     * 処理の前後には待機時間[millis]があります。（待機時間がないとマウスのプレス処理が正常に処理されないため）
     *
     * ボタンの press / release は現状のボタンの状態にかかわらず必ず行われます
     *  */
    fun MClick( millis : Long = 20, action : HIDDevice.()->(Unit) = {} ){
        + MPress
        + Sleep(millis)

        action()

        + Sleep(millis)
        + MRelease
    }
    /**
     * マウスの右ボタン を押した状態で処理を行った後に離します
     *
     * 処理の前後には待機時間[millis]があります。（待機時間がないとマウスのプレス処理が正常に処理されないため）
     *
     * ボタンの press / release は現状のボタンの状態にかかわらず必ず行われます
     *  */
    fun RClick( millis : Long = 20, action : HIDDevice.()->(Unit) = {} ){
        + RPress
        + Sleep(millis)

        action()

        + Sleep(millis)
        + RRelease
    }
    fun Ex1Click( millis : Long = 20, action : HIDDevice.()->(Unit) = {} ){
        + Ex1Press
        + Sleep(millis)

        action()

        + Sleep(millis)
        + Ex1Release
    }
    fun Ex2Click( millis : Long = 20, action : HIDDevice.()->(Unit) = {} ){
        + Ex2Press
        + Sleep(millis)

        action()

        + Sleep(millis)
        + Ex2Release
    }


    // Mouse Wheel
    fun mouseWheel(num : Int) : MouseWheelIndicator {
        invoke(MouseWheel(num))

        return MouseWheel
    }

    inner class MouseWheelIndicator
    val MouseWheel : MouseWheelIndicator = MouseWheelIndicator()
    val mW = MouseWheel

    operator fun MouseWheelIndicator.plus(num : Int) = mouseWheel(num)
    operator fun MouseWheelIndicator.minus(num : Int) = mouseWheel(-num)
    operator fun MouseWheelIndicator.plus(nums : List<Int>) : MouseWheelIndicator {
        nums.forEach { MouseWheel + it }
        return MouseWheel
    }
    operator fun MouseWheelIndicator.minus(nums : List<Int>) : MouseWheelIndicator {
        nums.forEach { MouseWheel - it }
        return MouseWheel
    }

    // Mouse Move
    var mPoint : Pair<Int, Int>
        get() = throw RuntimeException("入力専用フィールドです。参照はしないで下さい。")
        set(value) { mouseMove(value.first, value.second) }
    fun mouseMove(x : Int, y : Int){
        invoke(MouseMove(x, y))
    }
    fun mouseMove(point : Pair<Int, Int>) = mouseMove(point.first, point.second)

    fun mouseShift(x : Int = 0, y : Int = 0){
        invoke(MouseShift(x, y))
    }
    fun mouseShift(point : Pair<Int, Int>) = mouseShift(point.first, point.second)


    inner class MouseShiftIndicator
    inner class MouseShiftIndicatorX
    inner class MouseShiftIndicatorY

    val Mouse : MouseShiftIndicator = MouseShiftIndicator()
    val MouseX : MouseShiftIndicatorX = MouseShiftIndicatorX()
    val MouseY : MouseShiftIndicatorY = MouseShiftIndicatorY()
    val mXY : MouseShiftIndicator = Mouse
    val mX : MouseShiftIndicatorX = MouseX
    val mY : MouseShiftIndicatorY = MouseY

    operator fun MouseShiftIndicator.plus(xy : Pair<Int, Int>) : MouseShiftIndicator {
        mouseShift(x=xy.first, y=xy.second)
        return Mouse
    }
    operator fun MouseShiftIndicatorX.plus(x : Int) : MouseShiftIndicatorX {
        mouseShift(x=x)
        return MouseX
    }
    operator fun MouseShiftIndicatorY.plus(y : Int) : MouseShiftIndicatorY {
        mouseShift(y=y)
        return MouseY
    }
    operator fun MouseShiftIndicator.plus(xy : List<Pair<Int, Int>>) : MouseShiftIndicator {
        xy.forEach { Mouse + it }
        return Mouse
    }
    operator fun MouseShiftIndicatorX.plus(x : List<Int>) : MouseShiftIndicatorX {
        x.forEach { MouseX + it }
        return MouseX
    }
    operator fun MouseShiftIndicatorY.plus(y : List<Int>) : MouseShiftIndicatorY {
        y.forEach { MouseY + it }
        return MouseY
    }

    // Mouse Drag
    fun mouseDrag( from : Pair<Int, Int>, to : Pair<Int, Int> ){
        mouseMove(from.first, from.second)

        LClick { mouseMove(to.first, to.second) }
    }
    fun mouseDrag( shift : Pair<Int, Int> ){
        LClick { mouseShift(shift) }
    }
    infix fun Pair<Int, Int>.drag(to : Pair<Int, Int>) = mouseDrag(this, to)

}

class HIDDeviceUS private constructor(hostMode : Boolean, port : HIDExporter?, override val keyMap: KeyMapUS) : HIDDevice(hostMode, port, keyMap) {
    constructor(hostMode : Boolean, port : HIDExporter? = null) : this(hostMode, port, KeyMapUSImpl())
}
class HIDDeviceJIS private constructor(hostMode : Boolean, port : HIDExporter?, override val keyMap: KeyMapJIS) : HIDDevice(hostMode, port, keyMap) {
    constructor(hostMode : Boolean, port : HIDExporter? = null) : this(hostMode, port, KeyMapJISImpl())

    val InputMethod get() = keyMap.VK_InputMethod.toKeyClick
    val Convert get() = keyMap.VK_Convert.toKeyClick
    val NoConvert get() = keyMap.VK_NonConvert.toKeyClick

    val Yen get() = keyMap.VK_Yen.toKeyClick


    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, 確定, IME = off の順に実行する
     *
     * TODO : IME の状態で分岐処理を行うべき。C++で実装する際に imm64.dll を使用するために現状では保留中
     * */
    fun InputMethod( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + Enter
        + InputMethod
        return this
    }
    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, F6変換, 確定, IME = off の順に実行する
     * */
    fun HIRAGANA( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + F6
        + Enter
        + InputMethod
        return this
    }
    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, F7変換, 確定, IME = off の順に実行する
     * */
    fun ZENKATA( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + F7
        + Enter
        + InputMethod
        return this
    }
    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, F8変換, 確定, IME = off の順に実行する
     * */
    fun HANKATA( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + F8
        + Enter
        + InputMethod
        return this
    }
    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, F9変換, 確定, IME = off の順に実行する
     * */
    fun ZENALPLA( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + F9
        + Enter
        + InputMethod
        return this
    }
    /**
     * IME = off の状態で使用すること
     *
     * IME = on, 文字入力, F10変換, 確定, IME = off の順に実行する
     * */
    fun HANALPLA( action : HIDDeviceJIS.()->(Unit) ) : HIDDeviceJIS {
        + InputMethod
        action()
        + F10
        + Enter
        + InputMethod
        return this
    }
}

val HIDDevice.Copy : HIDData get() = keyMap.VK_c.withControl.toKeyClick
val HIDDevice.Cut : HIDData get() = keyMap.VK_x.withControl.toKeyClick
val HIDDevice.Paste : HIDData get() = keyMap.VK_v.withControl.toKeyClick
val HIDDevice.Undo : HIDData get() = keyMap.VK_z.withControl.toKeyClick
