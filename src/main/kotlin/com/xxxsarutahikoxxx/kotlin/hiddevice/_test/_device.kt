package com.xxxsarutahikoxxx.kotlin.hiddevice._test

import com.xxxsarutahikoxxx.kotlin.hiddevice.core.HIDField
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.Paste
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.WindowProcess
import com.xxxsarutahikoxxx.kotlin.hiddevice.core.WindowTitle
import com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys.JISHost
import com.xxxsarutahikoxxx.kotlin.hiddevice.utilitys.out
import com.xxxsarutahikoxxx.kotlin.hiddeviceNative.HID
import com.xxxsarutahikoxxx.kotlin.hiddeviceNative.TryWithHWND
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

fun main(args: Array<String>) {

    JISHost {
//        mPoint = 100 to 300
//
//        Mouse + (100 to 200)
//        + LClick.x2 // 左ダブル・クリック
//
//        MouseX + 100
//        MouseY + 200
//        mX + 100
//        mY + 200
//
//        mW + listOf(1, 1, 1)
//        mX + 10 + 20 + 10
//
//        + LPress + LRelease // 左ボタンを押す＋離す( = クリック)
//
//        + LClick // 左ボタンをクリックする
//        + MClick // 中ボタン
//        + RClick // 右ボタン
//
//        + Ex1Click // Ex1 ボタン(戻るボタン・Backボタン)
//        + Ex2Click // Ex2 ボタン(進むボタン・Goボタン)
//
//        LClick { mPoint = 400 to 400 } //　左ボタンを押しながら動作を実行した後に、左ボタンを離す
//
//
//        + 'a' // a
//        + 'A' // Shift(Press) + a + Shift(Release)
//
//        + Shift.x2
//
//        Ctrl{ + 'c' }
//        Alt{ Shift{ + F10 }}
//        Ctrl{ + LClick }
//
//        Alt{ + Insert }
//
//        Shift{ + Left.x4 } + Copy
//        + Right + Paste
//
//        + "lowercase" + Shift{ + "uppercase" + UnShift { + "lower" } }
//
//        mouseDrag( 100 to 200 )

        out = isShiftPressed
        isShiftPressed = true
        out = isShiftPressed
        isShiftPressed = false
        out = isShiftPressed
    }




//    val user = TryWithHWND.User32.INSTANCE
//    val window = user.GetForegroundWindow()
//    out = window
//
//    Thread.sleep(3000)
//
//    out = user.BringWindowToTop(window)
}
