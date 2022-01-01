package org.xxxsarutahikoxxx.kotlin.HIDDeviceNative

internal class HID : HID_IO(){
    companion object {
        // Window
        fun activeWindowTitle(): String {
            return io.INSTANCE.ActiveWindowTitle().toString()
        }
        fun activeWindowProcessName(): String {
            return io.INSTANCE.ActiveWindowProcessName()
        }
        fun minimizeActiveWindow() {
            io.INSTANCE.MinimizeActiveWindow()
        }

        // KeyCode
        val VK_MouseLeft = io.INSTANCE.VK_MouseLeft()
        val VK_MouseMiddle = io.INSTANCE.VK_MouseMiddle()
        val VK_MouseRight = io.INSTANCE.VK_MouseRight()
        val VK_MouseEx1 = io.INSTANCE.VK_MouseEx1()
        val VK_MouseEx2 = io.INSTANCE.VK_MouseEx2()

        // Mouse Location
        val mouseX get(): Int {
            return io.INSTANCE.MouseX()
        }
        val mouseY get(): Int {
            return io.INSTANCE.MouseY()
        }
        fun mouseMove(x: Int, y: Int): Int {
            return io.INSTANCE.MouseMove(x, y)
        }
        fun mouseShift(x: Int, y: Int): Int {
            return io.INSTANCE.MouseShift(x, y)
        }
        fun mousePress(code: Int): Int {
            return io.INSTANCE.MousePress(code)
        }
        fun mouseRelease(code: Int): Int {
            return io.INSTANCE.MouseRelease(code)
        }
        fun mouseClick(code: Int): Int {
            return io.INSTANCE.MouseClick(code)
        }
        fun mouseWheel(notches: Int): Int {
            return io.INSTANCE.MouseWheel(notches)
        }


        // KeyCode
        // Number
        val VK_0 = '0'.code
        val VK_1 = '1'.code
        val VK_2 = '2'.code
        val VK_3 = '3'.code
        val VK_4 = '4'.code
        val VK_5 = '5'.code
        val VK_6 = '6'.code
        val VK_7 = '7'.code
        val VK_8 = '8'.code
        val VK_9 = '9'.code

        // Alphabet
        val VK_A = 'A'.code
        val VK_B = 'B'.code
        val VK_C = 'C'.code
        val VK_D = 'D'.code
        val VK_E = 'E'.code
        val VK_F = 'F'.code
        val VK_G = 'G'.code
        val VK_H = 'H'.code
        val VK_I = 'I'.code
        val VK_J = 'J'.code
        val VK_K = 'K'.code
        val VK_L = 'L'.code
        val VK_M = 'M'.code
        val VK_N = 'N'.code
        val VK_O = 'O'.code
        val VK_P = 'P'.code
        val VK_Q = 'Q'.code
        val VK_R = 'R'.code
        val VK_S = 'S'.code
        val VK_T = 'T'.code
        val VK_U = 'U'.code
        val VK_V = 'V'.code
        val VK_W = 'W'.code
        val VK_X = 'X'.code
        val VK_Y = 'Y'.code
        val VK_Z = 'Z'.code

        // Special Key
        val VK_Escape = io.INSTANCE.VK_Escape()
        val VK_Tab = io.INSTANCE.VK_Tab()
        val VK_CapsLock = io.INSTANCE.VK_CapsLock()
        val VK_Space = io.INSTANCE.VK_Space()
        val VK_ContextMenu = io.INSTANCE.VK_ContextMenu()
        val VK_Enter = io.INSTANCE.VK_Enter()
        val VK_BackSpace = io.INSTANCE.VK_BackSpace()

        val VK_Shift = io.INSTANCE.VK_Shift()
        val VK_Control = io.INSTANCE.VK_Control()
        val VK_Alt = io.INSTANCE.VK_Alt()

        val VK_LShift = io.INSTANCE.VK_LShift()
        val VK_LControl = io.INSTANCE.VK_LControl()
        val VK_LWindows = io.INSTANCE.VK_LWindows()
        val VK_LAlt = io.INSTANCE.VK_LAlt()
        val VK_RShift = io.INSTANCE.VK_RShift()
        val VK_RControl = io.INSTANCE.VK_RControl()
        val VK_RWindows = io.INSTANCE.VK_RWindows()
        val VK_RAlt = io.INSTANCE.VK_RAlt()

        val VK_PrintScreen = io.INSTANCE.VK_PrintScreen()
        val VK_ScrollLock = io.INSTANCE.VK_ScrollLock()
        val VK_Pause = io.INSTANCE.VK_Pause()
        val VK_Insert = io.INSTANCE.VK_Insert()
        val VK_Home = io.INSTANCE.VK_Home()
        val VK_Delete = io.INSTANCE.VK_Delete()
        val VK_End = io.INSTANCE.VK_End()
        val VK_PageUp = io.INSTANCE.VK_PageUp()
        val VK_PageDown = io.INSTANCE.VK_PageDown()
        val VK_Up = io.INSTANCE.VK_Up()
        val VK_Down = io.INSTANCE.VK_Down()
        val VK_Left = io.INSTANCE.VK_Left()
        val VK_Right = io.INSTANCE.VK_Right()
        val VK_NumLock = io.INSTANCE.VK_NumLock()
        val VK_NumDivide = io.INSTANCE.VK_NumDivide()
        val VK_NumMultiply = io.INSTANCE.VK_NumMultiply()
        val VK_NumSubtract = io.INSTANCE.VK_NumSubtract()
        val VK_NumAdd = io.INSTANCE.VK_NumAdd()
        val VK_NumDecimal = io.INSTANCE.VK_NumDecimal()

        val VK_Num0 = io.INSTANCE.VK_Num0()
        val VK_Num1 = io.INSTANCE.VK_Num1()
        val VK_Num2 = io.INSTANCE.VK_Num2()
        val VK_Num3 = io.INSTANCE.VK_Num3()
        val VK_Num4 = io.INSTANCE.VK_Num4()
        val VK_Num5 = io.INSTANCE.VK_Num5()
        val VK_Num6 = io.INSTANCE.VK_Num6()
        val VK_Num7 = io.INSTANCE.VK_Num7()
        val VK_Num8 = io.INSTANCE.VK_Num8()
        val VK_Num9 = io.INSTANCE.VK_Num9()

        // Function Key
        val VK_F1 = io.INSTANCE.VK_Func1()
        val VK_F2 = io.INSTANCE.VK_Func2()
        val VK_F3 = io.INSTANCE.VK_Func3()
        val VK_F4 = io.INSTANCE.VK_Func4()
        val VK_F5 = io.INSTANCE.VK_Func5()
        val VK_F6 = io.INSTANCE.VK_Func6()
        val VK_F7 = io.INSTANCE.VK_Func7()
        val VK_F8 = io.INSTANCE.VK_Func8()
        val VK_F9 = io.INSTANCE.VK_Func9()
        val VK_F10 = io.INSTANCE.VK_Func10()
        val VK_F11 = io.INSTANCE.VK_Func11()
        val VK_F12 = io.INSTANCE.VK_Func12()
        val VK_F13 = io.INSTANCE.VK_Func13()
        val VK_F14 = io.INSTANCE.VK_Func14()
        val VK_F15 = io.INSTANCE.VK_Func15()
        val VK_F16 = io.INSTANCE.VK_Func16()
        val VK_F17 = io.INSTANCE.VK_Func17()
        val VK_F18 = io.INSTANCE.VK_Func18()
        val VK_F19 = io.INSTANCE.VK_Func19()
        val VK_F20 = io.INSTANCE.VK_Func20()

        // JIS Key
        val VK_InputMethod = io.INSTANCE.VK_InputMethod()
        val VK_Convert = io.INSTANCE.VK_Convert()
        val VK_NonConvert = io.INSTANCE.VK_NonConvert()

        // OEM Key
        val VK_MARK_1 = io.INSTANCE.VK_MARK_1()
        val VK_MARK_2 = io.INSTANCE.VK_MARK_2()
        val VK_MARK_3 = io.INSTANCE.VK_MARK_3()
        val VK_MARK_4 = io.INSTANCE.VK_MARK_4()
        val VK_MARK_5 = io.INSTANCE.VK_MARK_5()
        val VK_MARK_6 = io.INSTANCE.VK_MARK_6()
        val VK_MARK_7 = io.INSTANCE.VK_MARK_7()
        val VK_MARK_102 = io.INSTANCE.VK_MARK_102()
        val VK_MARK_MINUS = io.INSTANCE.VK_MARK_MINUS()
        val VK_MARK_PLUS = io.INSTANCE.VK_MARK_PLUS()
        val VK_MARK_COMMA = io.INSTANCE.VK_MARK_COMMA()
        val VK_MARK_PERIOD = io.INSTANCE.VK_MARK_PERIOD()

        // Func Key
        val VK_Volume_Mute = io.INSTANCE.VK_Volume_Mute()
        val VK_Volume_Up = io.INSTANCE.VK_Volume_Up()
        val VK_Volume_Down = io.INSTANCE.VK_Volume_Down()

        val VK_Media_Play_Pause = io.INSTANCE.VK_Media_Play_Pause()
        val VK_Media_Prev_Track = io.INSTANCE.VK_Media_Prev_Track()
        val VK_Media_Next_Track = io.INSTANCE.VK_Media_Next_Track()
        val VK_Media_Stop = io.INSTANCE.VK_Media_Stop()


        // Key Emulate
        fun keyClick(code: Int) {
            io.INSTANCE.KeyClick(code)
        }
        fun keyPress(code: Int) {
            io.INSTANCE.KeyPress(code)
        }
        fun keyRelease(code: Int) {
            io.INSTANCE.KeyRelease(code)
        }
        fun keyClick_with(code: Int, shift: Boolean, ctrl: Boolean, alt: Boolean, win: Boolean) {
            io.INSTANCE.KeyClick_with(code, shift, ctrl, alt, win)
        }

        // Key State
        fun isKeyPressed(code: Int): Boolean {
            return io.INSTANCE.isKeyPressed(code)
        }
        fun isKeyReleased(code: Int): Boolean {
            return io.INSTANCE.isKeyReleased(code)
        }

        val isShiftPressed get(): Boolean {
            return io.INSTANCE.isShiftPressed
        }
        val isControlPressed get(): Boolean {
            return io.INSTANCE.isControlPressed
        }
        val isAltPressed get(): Boolean {
            return io.INSTANCE.isAltPressed
        }

    }
}
