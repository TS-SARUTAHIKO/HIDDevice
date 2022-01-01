package org.xxxsarutahikoxxx.kotlin.HIDDevice.core


/** <KeyCode, <Shift, Control, Alt>> */
typealias CodewithMask = Pair<Int, Triple<Boolean, Boolean, Boolean>>

val CodewithMask.withShift get() = first to Triple(true, second.second, second.third)
val CodewithMask.withControl get() = first to Triple(second.first, true, second.third)
val CodewithMask.withAlt get() = first to Triple(second.first, second.second, true)

val CodewithMask.toKeyClick : HIDData
    get(){
    return KeyClick(first).run {
        if( second.first ) this.withShift else this
    }.run {
        if( second.second ) this.withControl else this
    }.run {
        if( second.third ) this.withAlt else this
    }
}
val CodewithMask.toMouseClick : HIDData
    get(){
    return MouseClick(first).run {
        if( second.first ) this.withShift else this
    }.run {
        if( second.second ) this.withControl else this
    }.run {
        if( second.third ) this.withAlt else this
    }
}
val CodewithMask.toMousePress : HIDData
    get(){
    return MousePress(first).run {
        if( second.first ) this.withShift else this
    }.run {
        if( second.second ) this.withControl else this
    }.run {
        if( second.third ) this.withAlt else this
    }
}
val CodewithMask.toMouseRelease : HIDData
    get(){
    return MouseRelease(first).run {
        if( second.first ) this.withShift else this
    }.run {
        if( second.second ) this.withControl else this
    }.run {
        if( second.third ) this.withAlt else this
    }
}


/** Key */
interface KeyMap {
    // Keyboard
    val VK_1 : CodewithMask
    val VK_2 : CodewithMask
    val VK_3 : CodewithMask
    val VK_4 : CodewithMask
    val VK_5 : CodewithMask
    val VK_6 : CodewithMask
    val VK_7 : CodewithMask
    val VK_8 : CodewithMask
    val VK_9 : CodewithMask
    val VK_0 : CodewithMask

    val VK_F1 : CodewithMask
    val VK_F2 : CodewithMask
    val VK_F3 : CodewithMask
    val VK_F4 : CodewithMask
    val VK_F5 : CodewithMask
    val VK_F6 : CodewithMask
    val VK_F7 : CodewithMask
    val VK_F8 : CodewithMask
    val VK_F9 : CodewithMask
    val VK_F10 : CodewithMask
    val VK_F11 : CodewithMask
    val VK_F12 : CodewithMask

    val VK_a : CodewithMask
    val VK_b : CodewithMask
    val VK_c : CodewithMask
    val VK_d : CodewithMask
    val VK_e : CodewithMask
    val VK_f : CodewithMask
    val VK_g : CodewithMask
    val VK_h : CodewithMask
    val VK_i : CodewithMask
    val VK_j : CodewithMask
    val VK_k : CodewithMask
    val VK_l : CodewithMask
    val VK_m : CodewithMask
    val VK_n : CodewithMask
    val VK_o : CodewithMask
    val VK_p : CodewithMask
    val VK_q : CodewithMask
    val VK_r : CodewithMask
    val VK_s : CodewithMask
    val VK_t : CodewithMask
    val VK_u : CodewithMask
    val VK_v : CodewithMask
    val VK_w : CodewithMask
    val VK_x : CodewithMask
    val VK_y : CodewithMask
    val VK_z : CodewithMask

    //
    val VK_BackQuote : CodewithMask
    val VK_Tilde : CodewithMask
    val VK_Minus : CodewithMask
    val VK_Underscore : CodewithMask
    val VK_Equals : CodewithMask
    val VK_Plus : CodewithMask
    val VK_OpenBracket : CodewithMask
    val VK_LeftCurlyBrace : CodewithMask
    val VK_CloseBracket : CodewithMask
    val VK_RightCurlyBrace : CodewithMask
    val VK_SemiColon : CodewithMask
    val VK_Colon : CodewithMask
    val VK_Quote : CodewithMask
    val VK_DoubleQuote : CodewithMask
    val VK_BackSlash  : CodewithMask
    val VK_VerticalBar : CodewithMask
    val VK_Comma : CodewithMask
    val VK_Less : CodewithMask
    val VK_Period : CodewithMask
    val VK_Greater : CodewithMask
    val VK_Slash : CodewithMask
    val VK_Question : CodewithMask
    val VK_Exclamation : CodewithMask
    val VK_At : CodewithMask
    val VK_NumberSign : CodewithMask
    val VK_Dollars : CodewithMask
    val VK_Percent : CodewithMask
    val VK_Circumflex : CodewithMask
    val VK_Ampersand : CodewithMask
    val VK_Asterisk : CodewithMask
    val VK_LeftParenthesis : CodewithMask
    val VK_RightParenthesis : CodewithMask

    //
    val VK_Escape : CodewithMask
    val VK_Tab : CodewithMask
    val VK_CapsLock : CodewithMask
    val VK_Space : CodewithMask
    val VK_ContextMenu : CodewithMask
    val VK_Enter : CodewithMask
    val VK_BackSpace : CodewithMask

    val VK_Shift : CodewithMask
    val VK_Control : CodewithMask
    val VK_Alt : CodewithMask
    val VK_LShift : CodewithMask
    val VK_LControl : CodewithMask
    val VK_LWindows : CodewithMask
    val VK_LAlt : CodewithMask
    val VK_RShift : CodewithMask
    val VK_RControl : CodewithMask
    val VK_RWindows : CodewithMask
    val VK_RAlt : CodewithMask

    val VK_PrintScreen : CodewithMask
    val VK_ScrollLock : CodewithMask
    val VK_Pause : CodewithMask
    val VK_Insert : CodewithMask
    val VK_Home : CodewithMask
    val VK_Delete : CodewithMask
    val VK_End : CodewithMask
    val VK_PageUp : CodewithMask
    val VK_PageDown : CodewithMask
    val VK_Up : CodewithMask
    val VK_Down : CodewithMask
    val VK_Left : CodewithMask
    val VK_Right : CodewithMask

    // NumPad
    val VK_NumLock : CodewithMask
    val VK_Num0 : CodewithMask
    val VK_Num1 : CodewithMask
    val VK_Num2 : CodewithMask
    val VK_Num3 : CodewithMask
    val VK_Num4 : CodewithMask
    val VK_Num5 : CodewithMask
    val VK_Num6 : CodewithMask
    val VK_Num7 : CodewithMask
    val VK_Num8 : CodewithMask
    val VK_Num9 : CodewithMask
    val VK_NumDivide : CodewithMask
    val VK_NumMultiply : CodewithMask
    val VK_NumSubtract : CodewithMask
    val VK_NumAdd : CodewithMask
    val VK_NumDecimal : CodewithMask

    // Mouse
    val VK_MouseLeft : CodewithMask
    val VK_MouseMiddle : CodewithMask
    val VK_MouseRight : CodewithMask
    val VK_MouseEx1 : CodewithMask
    val VK_MouseEx2 : CodewithMask


    /** char に対応した[CodewithMask]を返却する */
    fun toKeyData(char : Char) : CodewithMask {
        return when(char){
            'a' -> VK_a
            'b' -> VK_b
            'c' -> VK_c
            'd' -> VK_d
            'e' -> VK_e
            'f' -> VK_f
            'g' -> VK_g
            'h' -> VK_h
            'i' -> VK_i
            'j' -> VK_j
            'k' -> VK_k
            'l' -> VK_l
            'm' -> VK_m
            'n' -> VK_n
            'o' -> VK_o
            'p' -> VK_p
            'q' -> VK_q
            'r' -> VK_r
            's' -> VK_s
            't' -> VK_t
            'u' -> VK_u
            'v' -> VK_v
            'w' -> VK_w
            'x' -> VK_x
            'y' -> VK_y
            'z' -> VK_z

            'A' -> VK_a.withShift
            'B' -> VK_b.withShift
            'C' -> VK_c.withShift
            'D' -> VK_d.withShift
            'E' -> VK_e.withShift
            'F' -> VK_f.withShift
            'G' -> VK_g.withShift
            'H' -> VK_h.withShift
            'I' -> VK_i.withShift
            'J' -> VK_j.withShift
            'K' -> VK_k.withShift
            'L' -> VK_l.withShift
            'M' -> VK_m.withShift
            'N' -> VK_n.withShift
            'O' -> VK_o.withShift
            'P' -> VK_p.withShift
            'Q' -> VK_q.withShift
            'R' -> VK_r.withShift
            'S' -> VK_s.withShift
            'T' -> VK_t.withShift
            'U' -> VK_u.withShift
            'V' -> VK_v.withShift
            'W' -> VK_w.withShift
            'X' -> VK_x.withShift
            'Y' -> VK_y.withShift
            'Z' -> VK_z.withShift

            '0' -> VK_0
            '1' -> VK_1
            '2' -> VK_2
            '3' -> VK_3
            '4' -> VK_4
            '5' -> VK_5
            '6' -> VK_6
            '7' -> VK_7
            '8' -> VK_8
            '9' -> VK_9

            // 記号
            '!' -> VK_Exclamation
            '"' -> VK_DoubleQuote
            '#' -> VK_NumberSign
            '$' -> VK_Dollars
            '%' -> VK_Percent
            '&' -> VK_Ampersand
            '\'' -> VK_Quote
            '(' -> VK_LeftParenthesis
            ')' -> VK_RightParenthesis
            '-' -> VK_Minus
            '=' -> VK_Equals
            '^' -> VK_Circumflex
            '~' -> VK_Tilde
            '|' -> VK_VerticalBar
            '@' -> VK_At
            '`' -> VK_BackQuote
            '[' -> VK_OpenBracket
            '{' -> VK_LeftCurlyBrace
            ';' -> VK_SemiColon
            '+' -> VK_Plus
            ':' -> VK_Colon
            '*' -> VK_Asterisk
            ']' -> VK_CloseBracket
            '}' -> VK_RightCurlyBrace
            ',' -> VK_Comma
            '<' -> VK_Less
            '.' -> VK_Period
            '>' -> VK_Greater
            '/' -> VK_Slash
            '?' -> VK_Question
            '\\' -> VK_BackSlash
            '_' -> VK_Underscore

            // 特殊記号
            ' ' -> VK_Space
            '\t' -> VK_Tab
            '\n' -> VK_Enter

            else -> throw RuntimeException("NoMuch Char = ${this}")
        }
    }
}
interface KeyMapUS : KeyMap {

}
interface KeyMapJIS : KeyMap {
    val VK_InputMethod : CodewithMask

    val VK_Convert : CodewithMask
    val VK_NonConvert : CodewithMask

    val VK_Yen : CodewithMask
}

abstract class KeyMapImpl : KeyMap {
    override val VK_1 : CodewithMask
        get() = HIDField.VK_1 to Triple(false, false, false)
    override val VK_2 : CodewithMask
        get() = HIDField.VK_2 to Triple(false, false, false)
    override val VK_3 : CodewithMask
        get() = HIDField.VK_3 to Triple(false, false, false)
    override val VK_4 : CodewithMask
        get() = HIDField.VK_4 to Triple(false, false, false)
    override val VK_5 : CodewithMask
        get() = HIDField.VK_5 to Triple(false, false, false)
    override val VK_6 : CodewithMask
        get() = HIDField.VK_6 to Triple(false, false, false)
    override val VK_7 : CodewithMask
        get() = HIDField.VK_7 to Triple(false, false, false)
    override val VK_8 : CodewithMask
        get() = HIDField.VK_8 to Triple(false, false, false)
    override val VK_9 : CodewithMask
        get() = HIDField.VK_9 to Triple(false, false, false)
    override val VK_0 : CodewithMask
        get() = HIDField.VK_0 to Triple(false, false, false)

    override val VK_F1 : CodewithMask
        get() = HIDField.VK_F1 to Triple(false, false, false)
    override val VK_F2 : CodewithMask
        get() = HIDField.VK_F2 to Triple(false, false, false)
    override val VK_F3 : CodewithMask
        get() = HIDField.VK_F3 to Triple(false, false, false)
    override val VK_F4 : CodewithMask
        get() = HIDField.VK_F4 to Triple(false, false, false)
    override val VK_F5 : CodewithMask
        get() = HIDField.VK_F5 to Triple(false, false, false)
    override val VK_F6 : CodewithMask
        get() = HIDField.VK_F6 to Triple(false, false, false)
    override val VK_F7 : CodewithMask
        get() = HIDField.VK_F7 to Triple(false, false, false)
    override val VK_F8 : CodewithMask
        get() = HIDField.VK_F8 to Triple(false, false, false)
    override val VK_F9 : CodewithMask
        get() = HIDField.VK_F9 to Triple(false, false, false)
    override val VK_F10 : CodewithMask
        get() = HIDField.VK_F10 to Triple(false, false, false)
    override val VK_F11 : CodewithMask
        get() = HIDField.VK_F11 to Triple(false, false, false)
    override val VK_F12 : CodewithMask
        get() = HIDField.VK_F12 to Triple(false, false, false)

    override val VK_a : CodewithMask
        get() = HIDField.VK_A to Triple(false, false, false)
    override val VK_b : CodewithMask
        get() = HIDField.VK_B to Triple(false, false, false)
    override val VK_c : CodewithMask
        get() = HIDField.VK_C to Triple(false, false, false)
    override val VK_d : CodewithMask
        get() = HIDField.VK_D to Triple(false, false, false)
    override val VK_e : CodewithMask
        get() = HIDField.VK_E to Triple(false, false, false)
    override val VK_f : CodewithMask
        get() = HIDField.VK_F to Triple(false, false, false)
    override val VK_g : CodewithMask
        get() = HIDField.VK_G to Triple(false, false, false)
    override val VK_h : CodewithMask
        get() = HIDField.VK_H to Triple(false, false, false)
    override val VK_i : CodewithMask
        get() = HIDField.VK_I to Triple(false, false, false)
    override val VK_j : CodewithMask
        get() = HIDField.VK_J to Triple(false, false, false)
    override val VK_k : CodewithMask
        get() = HIDField.VK_K to Triple(false, false, false)
    override val VK_l : CodewithMask
        get() = HIDField.VK_L to Triple(false, false, false)
    override val VK_m : CodewithMask
        get() = HIDField.VK_M to Triple(false, false, false)
    override val VK_n : CodewithMask
        get() = HIDField.VK_N to Triple(false, false, false)
    override val VK_o : CodewithMask
        get() = HIDField.VK_O to Triple(false, false, false)
    override val VK_p : CodewithMask
        get() = HIDField.VK_P to Triple(false, false, false)
    override val VK_q : CodewithMask
        get() = HIDField.VK_Q to Triple(false, false, false)
    override val VK_r : CodewithMask
        get() = HIDField.VK_R to Triple(false, false, false)
    override val VK_s : CodewithMask
        get() = HIDField.VK_S to Triple(false, false, false)
    override val VK_t : CodewithMask
        get() = HIDField.VK_T to Triple(false, false, false)
    override val VK_u : CodewithMask
        get() = HIDField.VK_U to Triple(false, false, false)
    override val VK_v : CodewithMask
        get() = HIDField.VK_V to Triple(false, false, false)
    override val VK_w : CodewithMask
        get() = HIDField.VK_W to Triple(false, false, false)
    override val VK_x : CodewithMask
        get() = HIDField.VK_X to Triple(false, false, false)
    override val VK_y : CodewithMask
        get() = HIDField.VK_Y to Triple(false, false, false)
    override val VK_z : CodewithMask
        get() = HIDField.VK_Z to Triple(false, false, false)

    override val VK_Escape : CodewithMask
        get() = HIDField.VK_Escape to Triple(false, false, false)
    override val VK_Tab : CodewithMask
        get() = HIDField.VK_Tab to Triple(false, false, false)
    override val VK_CapsLock : CodewithMask
        get() = HIDField.VK_CapsLock to Triple(false, false, false)
    override val VK_Space : CodewithMask
        get() = HIDField.VK_Space to Triple(false, false, false)
    override val VK_ContextMenu : CodewithMask
        get() = HIDField.VK_ContextMenu to Triple(false, false, false)
    override val VK_Enter : CodewithMask
        get() = HIDField.VK_Enter to Triple(false, false, false)
    override val VK_BackSpace : CodewithMask
        get() = HIDField.VK_BackSpace to Triple(false, false, false)

    override val VK_Shift : CodewithMask
        get() = HIDField.VK_Shift to Triple(false, false, false)
    override val VK_Control : CodewithMask
        get() = HIDField.VK_Control to Triple(false, false, false)
    override val VK_Alt : CodewithMask
        get() = HIDField.VK_Alt to Triple(false, false, false)
    override val VK_LShift : CodewithMask
        get() = HIDField.VK_LShift to Triple(false, false, false)
    override val VK_LControl : CodewithMask
        get() = HIDField.VK_LControl to Triple(false, false, false)
    override val VK_LWindows : CodewithMask
        get() = HIDField.VK_LWindows to Triple(false, false, false)
    override val VK_LAlt : CodewithMask
        get() = HIDField.VK_LAlt to Triple(false, false, false)
    override val VK_RShift : CodewithMask
        get() = HIDField.VK_RShift to Triple(false, false, false)
    override val VK_RControl : CodewithMask
        get() = HIDField.VK_RControl to Triple(false, false, false)
    override val VK_RWindows : CodewithMask
        get() = HIDField.VK_RWindows to Triple(false, false, false)
    override val VK_RAlt : CodewithMask
        get() = HIDField.VK_RAlt to Triple(false, false, false)

    override val VK_PrintScreen : CodewithMask
        get() = HIDField.VK_PrintScreen to Triple(false, false, false)
    override val VK_ScrollLock : CodewithMask
        get() = HIDField.VK_ScrollLock to Triple(false, false, false)
    override val VK_Pause : CodewithMask
        get() = HIDField.VK_Pause to Triple(false, false, false)
    override val VK_Insert : CodewithMask
        get() = HIDField.VK_Insert to Triple(false, false, false)
    override val VK_Home : CodewithMask
        get() = HIDField.VK_Home to Triple(false, false, false)
    override val VK_Delete : CodewithMask
        get() = HIDField.VK_Delete to Triple(false, false, false)
    override val VK_End : CodewithMask
        get() = HIDField.VK_End to Triple(false, false, false)
    override val VK_PageUp : CodewithMask
        get() = HIDField.VK_PageUp to Triple(false, false, false)
    override val VK_PageDown : CodewithMask
        get() = HIDField.VK_PageDown to Triple(false, false, false)
    override val VK_Up : CodewithMask
        get() = HIDField.VK_Up to Triple(false, false, false)
    override val VK_Down : CodewithMask
        get() = HIDField.VK_Down to Triple(false, false, false)
    override val VK_Left : CodewithMask
        get() = HIDField.VK_Left to Triple(false, false, false)
    override val VK_Right : CodewithMask
        get() = HIDField.VK_Right to Triple(false, false, false)

    override val VK_NumLock : CodewithMask
        get() = HIDField.VK_NumLock to Triple(false, false, false)
    override val VK_Num0 : CodewithMask
        get() = HIDField.VK_Num0 to Triple(false, false, false)
    override val VK_Num1 : CodewithMask
        get() = HIDField.VK_Num1 to Triple(false, false, false)
    override val VK_Num2 : CodewithMask
        get() = HIDField.VK_Num2 to Triple(false, false, false)
    override val VK_Num3 : CodewithMask
        get() = HIDField.VK_Num3 to Triple(false, false, false)
    override val VK_Num4 : CodewithMask
        get() = HIDField.VK_Num4 to Triple(false, false, false)
    override val VK_Num5 : CodewithMask
        get() = HIDField.VK_Num5 to Triple(false, false, false)
    override val VK_Num6 : CodewithMask
        get() = HIDField.VK_Num6 to Triple(false, false, false)
    override val VK_Num7 : CodewithMask
        get() = HIDField.VK_Num7 to Triple(false, false, false)
    override val VK_Num8 : CodewithMask
        get() = HIDField.VK_Num8 to Triple(false, false, false)
    override val VK_Num9 : CodewithMask
        get() = HIDField.VK_Num9 to Triple(false, false, false)
    override val VK_NumDivide : CodewithMask
        get() = HIDField.VK_NumDivide to Triple(false, false, false)
    override val VK_NumMultiply : CodewithMask
        get() = HIDField.VK_NumMultiply to Triple(false, false, false)
    override val VK_NumSubtract : CodewithMask
        get() = HIDField.VK_NumSubtract to Triple(false, false, false)
    override val VK_NumAdd : CodewithMask
        get() = HIDField.VK_NumAdd to Triple(false, false, false)
    override val VK_NumDecimal : CodewithMask
        get() = HIDField.VK_NumDecimal to Triple(false, false, false)

    override val VK_MouseLeft : CodewithMask
        get() = HIDField.VK_MouseLeft to Triple(false, false, false)
    override val VK_MouseMiddle : CodewithMask
        get() = HIDField.VK_MouseMiddle to Triple(false, false, false)
    override val VK_MouseRight : CodewithMask
        get() = HIDField.VK_MouseRight to Triple(false, false, false)
    override val VK_MouseEx1 : CodewithMask
        get() = HIDField.VK_MouseEx1 to Triple(false, false, false)
    override val VK_MouseEx2 : CodewithMask
        get() = HIDField.VK_MouseEx2 to Triple(false, false, false)
}
class KeyMapUSImpl : KeyMapImpl(), KeyMapUS {
    override val VK_BackQuote: CodewithMask
        get() = HIDField.VK_MARK_3 to Triple(false, false, false)
    override val VK_Tilde: CodewithMask
        get() = HIDField.VK_MARK_3 to Triple(true, false, false)
    override val VK_Minus: CodewithMask
        get() = HIDField.VK_MARK_MINUS to Triple(false, false, false)
    override val VK_Underscore: CodewithMask
        get() = HIDField.VK_MARK_MINUS to Triple(true, false, false)
    override val VK_Equals: CodewithMask
        get() = HIDField.VK_MARK_PLUS to Triple(false, false, false)
    override val VK_Plus: CodewithMask
        get() = HIDField.VK_MARK_PLUS to Triple(true, false, false)
    override val VK_OpenBracket: CodewithMask
        get() = HIDField.VK_MARK_4 to Triple(false, false, false)
    override val VK_LeftCurlyBrace: CodewithMask
        get() = HIDField.VK_MARK_4 to Triple(true, false, false)
    override val VK_CloseBracket: CodewithMask
        get() = HIDField.VK_MARK_6 to Triple(false, false, false)
    override val VK_RightCurlyBrace: CodewithMask
        get() = HIDField.VK_MARK_6 to Triple(true, false, false)
    override val VK_SemiColon: CodewithMask
        get() = HIDField.VK_MARK_1 to Triple(false, false, false)
    override val VK_Colon: CodewithMask
        get() = HIDField.VK_MARK_1 to Triple(true, false, false)
    override val VK_Quote: CodewithMask
        get() = HIDField.VK_MARK_7 to Triple(false, false, false)
    override val VK_DoubleQuote: CodewithMask
        get() = HIDField.VK_MARK_7 to Triple(true, false, false)
    override val VK_BackSlash: CodewithMask
        get() = HIDField.VK_MARK_5 to Triple(false, false, false)
    override val VK_VerticalBar: CodewithMask
        get() = HIDField.VK_MARK_5 to Triple(true, false, false)
    override val VK_Comma: CodewithMask
        get() = HIDField.VK_MARK_COMMA to Triple(false, false, false)
    override val VK_Less: CodewithMask
        get() = HIDField.VK_MARK_COMMA to Triple(true, false, false)
    override val VK_Period: CodewithMask
        get() = HIDField.VK_MARK_PERIOD to Triple(false, false, false)
    override val VK_Greater: CodewithMask
        get() = HIDField.VK_MARK_PERIOD to Triple(true, false, false)
    override val VK_Slash: CodewithMask
        get() = HIDField.VK_MARK_2 to Triple(false, false, false)
    override val VK_Question: CodewithMask
        get() = HIDField.VK_MARK_2 to Triple(true, false, false)

    override val VK_Exclamation: CodewithMask
        get() = HIDField.VK_1 to Triple(true, false, false)
    override val VK_At: CodewithMask
        get() = HIDField.VK_2 to Triple(true, false, false)
    override val VK_NumberSign: CodewithMask
        get() = HIDField.VK_3 to Triple(true, false, false)
    override val VK_Dollars: CodewithMask
        get() = HIDField.VK_4 to Triple(true, false, false)
    override val VK_Percent: CodewithMask
        get() = HIDField.VK_5 to Triple(true, false, false)
    override val VK_Circumflex: CodewithMask
        get() = HIDField.VK_6 to Triple(true, false, false)
    override val VK_Ampersand: CodewithMask
        get() = HIDField.VK_7 to Triple(true, false, false)
    override val VK_Asterisk: CodewithMask
        get() = HIDField.VK_8 to Triple(true, false, false)
    override val VK_LeftParenthesis: CodewithMask
        get() = HIDField.VK_9 to Triple(true, false, false)
    override val VK_RightParenthesis: CodewithMask
        get() = HIDField.VK_0 to Triple(true, false, false)
}
class KeyMapJISImpl : KeyMapImpl(), KeyMapJIS {
    override val VK_InputMethod : CodewithMask
        get() = HIDField.VK_InputMethod to Triple(false, false, false)
    override val VK_Convert : CodewithMask
        get() = HIDField.VK_Convert to Triple(false, false, false)
    override val VK_NonConvert : CodewithMask
        get() = HIDField.VK_NonConvert to Triple(false, false, false)

    override val VK_Minus: CodewithMask
        get() = HIDField.VK_MARK_MINUS to Triple(false, false, false)
    override val VK_Equals: CodewithMask
        get() = HIDField.VK_MARK_MINUS to Triple(true, false, false)
    override val VK_Circumflex: CodewithMask
        get() = HIDField.VK_7 to Triple(false, false, false)
    override val VK_Tilde: CodewithMask
        get() = HIDField.VK_7 to Triple(true, false, false)
    override val VK_Yen: CodewithMask
        get() = HIDField.VK_MARK_5 to Triple(false, false, false)
    override val VK_VerticalBar: CodewithMask
        get() = HIDField.VK_MARK_5 to Triple(true, false, false)
    override val VK_At: CodewithMask
        get() = HIDField.VK_MARK_3 to Triple(false, false, false)
    override val VK_BackQuote: CodewithMask
        get() = HIDField.VK_MARK_3 to Triple(true, false, false)
    override val VK_OpenBracket: CodewithMask
        get() = HIDField.VK_MARK_4 to Triple(false, false, false)
    override val VK_LeftCurlyBrace: CodewithMask
        get() = HIDField.VK_MARK_4 to Triple(true, false, false)
    override val VK_SemiColon: CodewithMask
        get() = HIDField.VK_MARK_PLUS to Triple(false, false, false)
    override val VK_Plus: CodewithMask
        get() = HIDField.VK_MARK_PLUS to Triple(true, false, false)
    override val VK_Colon: CodewithMask
        get() = HIDField.VK_MARK_1 to Triple(false, false, false)
    override val VK_Asterisk: CodewithMask
        get() = HIDField.VK_MARK_1 to Triple(true, false, false)
    override val VK_CloseBracket: CodewithMask
        get() = HIDField.VK_MARK_6 to Triple(false, false, false)
    override val VK_RightCurlyBrace: CodewithMask
        get() = HIDField.VK_MARK_6 to Triple(true, false, false)
    override val VK_Comma: CodewithMask
        get() = HIDField.VK_MARK_COMMA to Triple(false, false, false)
    override val VK_Less: CodewithMask
        get() = HIDField.VK_MARK_COMMA to Triple(true, false, false)
    override val VK_Period: CodewithMask
        get() = HIDField.VK_MARK_PERIOD to Triple(false, false, false)
    override val VK_Greater: CodewithMask
        get() = HIDField.VK_MARK_PERIOD to Triple(true, false, false)
    override val VK_Slash: CodewithMask
        get() = HIDField.VK_MARK_2 to Triple(false, false, false)
    override val VK_Question: CodewithMask
        get() = HIDField.VK_MARK_2 to Triple(true, false, false)
    override val VK_BackSlash: CodewithMask
        get() = HIDField.VK_MARK_102 to Triple(false, false, false)
    override val VK_Underscore: CodewithMask
        get() = HIDField.VK_MARK_102 to Triple(true, false, false)

    override val VK_Exclamation: CodewithMask
        get() = HIDField.VK_1 to Triple(true, false, false)
    override val VK_DoubleQuote: CodewithMask
        get() = HIDField.VK_2 to Triple(true, false, false)
    override val VK_NumberSign: CodewithMask
        get() = HIDField.VK_3 to Triple(true, false, false)
    override val VK_Dollars: CodewithMask
        get() = HIDField.VK_4 to Triple(true, false, false)
    override val VK_Percent: CodewithMask
        get() = HIDField.VK_5 to Triple(true, false, false)
    override val VK_Ampersand: CodewithMask
        get() = HIDField.VK_6 to Triple(true, false, false)
    override val VK_Quote: CodewithMask
        get() = HIDField.VK_7 to Triple(true, false, false)
    override val VK_LeftParenthesis: CodewithMask
        get() = HIDField.VK_8 to Triple(true, false, false)
    override val VK_RightParenthesis: CodewithMask
        get() = HIDField.VK_9 to Triple(true, false, false)
}
