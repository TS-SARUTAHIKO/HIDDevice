package org.xxxsarutahikoxxx.kotlin.hiddevice.core


/**
 * C++ のキー・マウスの定数フィールドののハードコーディング値
 *
 * Android 環境においては DLL の読み込みができないため、[Companion]内の関数を呼び出すとエラーが発生する
 * */
open class HIDField {
    /**
     * C++ のキー・マウスの定数フィールドののハードコーディング値
     *
     * Android 環境においては DLL の読み込みができないため、[Companion]内の関数を呼び出すとエラーが発生する
     * */
    companion object {
        val VK_MouseLeft : Int = 1
        val VK_MouseMiddle : Int = 4
        val VK_MouseRight : Int = 2
        val VK_MouseEx1 : Int = 5
        val VK_MouseEx2 : Int = 6

        val VK_0 : Int = 48
        val VK_1 : Int = 49
        val VK_2 : Int = 50
        val VK_3 : Int = 51
        val VK_4 : Int = 52
        val VK_5 : Int = 53
        val VK_6 : Int = 54
        val VK_7 : Int = 55
        val VK_8 : Int = 56
        val VK_9 : Int = 57

        val VK_A : Int = 65
        val VK_B : Int = 66
        val VK_C : Int = 67
        val VK_D : Int = 68
        val VK_E : Int = 69
        val VK_F : Int = 70
        val VK_G : Int = 71
        val VK_H : Int = 72
        val VK_I : Int = 73
        val VK_J : Int = 74
        val VK_K : Int = 75
        val VK_L : Int = 76
        val VK_M : Int = 77
        val VK_N : Int = 78
        val VK_O : Int = 79
        val VK_P : Int = 80
        val VK_Q : Int = 81
        val VK_R : Int = 82
        val VK_S : Int = 83
        val VK_T : Int = 84
        val VK_U : Int = 85
        val VK_V : Int = 86
        val VK_W : Int = 87
        val VK_X : Int = 88
        val VK_Y : Int = 89
        val VK_Z : Int = 90

        val VK_Escape : Int = 27
        val VK_Tab : Int = 9
        val VK_CapsLock : Int = 20
        val VK_Space : Int = 32
        val VK_ContextMenu : Int = 93
        val VK_Enter : Int = 13
        val VK_BackSpace : Int = 8

        val VK_Shift : Int = 16
        val VK_Control : Int = 17
        val VK_Alt : Int = 18

        val VK_LShift : Int = 160
        val VK_LControl : Int = 162
        val VK_LWindows : Int = 91
        val VK_LAlt : Int = 164
        val VK_RShift : Int = 161
        val VK_RControl : Int = 163
        val VK_RWindows : Int = 92
        val VK_RAlt : Int = 165

        val VK_PrintScreen : Int = 44
        val VK_ScrollLock : Int = 145
        val VK_Pause : Int = 19
        val VK_Insert : Int = 45
        val VK_Home : Int = 36
        val VK_Delete : Int = 46
        val VK_End : Int = 35
        val VK_PageUp : Int = 33
        val VK_PageDown : Int = 34
        val VK_Up : Int = 38
        val VK_Down : Int = 40
        val VK_Left : Int = 37
        val VK_Right : Int = 39

        val VK_NumLock : Int = 144
        val VK_NumDivide : Int = 111
        val VK_NumMultiply : Int = 106
        val VK_NumSubtract : Int = 109
        val VK_NumAdd : Int = 107
        val VK_NumDecimal : Int = 110
        val VK_Num0 : Int = 96
        val VK_Num1 : Int = 97
        val VK_Num2 : Int = 98
        val VK_Num3 : Int = 99
        val VK_Num4 : Int = 100
        val VK_Num5 : Int = 101
        val VK_Num6 : Int = 102
        val VK_Num7 : Int = 103
        val VK_Num8 : Int = 104
        val VK_Num9 : Int = 105

        val VK_F1 : Int = 112
        val VK_F2 : Int = 113
        val VK_F3 : Int = 114
        val VK_F4 : Int = 115
        val VK_F5 : Int = 116
        val VK_F6 : Int = 117
        val VK_F7 : Int = 118
        val VK_F8 : Int = 119
        val VK_F9 : Int = 120
        val VK_F10 : Int = 121
        val VK_F11 : Int = 122
        val VK_F12 : Int = 123
        val VK_F13 : Int = 124
        val VK_F14 : Int = 125
        val VK_F15 : Int = 126
        val VK_F16 : Int = 127
        val VK_F17 : Int = 128
        val VK_F18 : Int = 129
        val VK_F19 : Int = 130
        val VK_F20 : Int = 131

        val VK_InputMethod : Int = 25
        val VK_Convert : Int = 28
        val VK_NonConvert : Int = 29

        val VK_MARK_1 : Int = 186
        val VK_MARK_2 : Int = 191
        val VK_MARK_3 : Int = 192
        val VK_MARK_4 : Int = 219
        val VK_MARK_5 : Int = 220
        val VK_MARK_6 : Int = 221
        val VK_MARK_7 : Int = 222
        val VK_MARK_102 : Int = 226
        val VK_MARK_MINUS : Int = 189
        val VK_MARK_PLUS : Int = 187
        val VK_MARK_COMMA : Int = 188
        val VK_MARK_PERIOD : Int = 190

        val VK_Volume_Mute : Int = 173
        val VK_Volume_Up : Int = 175
        val VK_Volume_Down : Int = 174
        val VK_Media_Play_Pause : Int = 179
        val VK_Media_Prev_Track : Int = 177
        val VK_Media_Next_Track : Int = 176
        val VK_Media_Stop : Int = 178


        private val VK_Map : Map<Int, String> = {
            Companion::class.java.declaredMethods
                .filter { it.name.startsWith("getVK_") && it.returnType == Int::class.java }
                .associate {
                    it.invoke(Companion) as Int to it.name.substring(3, it.name.length)
                }
        }.invoke()

        fun VK_Name(value : Int) : String? = VK_Map[value]
    }
}

