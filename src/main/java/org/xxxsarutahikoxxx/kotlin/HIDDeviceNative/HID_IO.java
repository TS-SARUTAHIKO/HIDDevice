package org.xxxsarutahikoxxx.kotlin.HIDDeviceNative;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;

class HID_IO {
    protected interface io extends Library {
        io INSTANCE = Native.loadLibrary("HID_IO", io.class);


        // Window
        WString ActiveWindowTitle();
        String ActiveWindowProcessName();
        void MinimizeActiveWindow();


        // Mouse KeyCode
        int VK_MouseLeft();
        int VK_MouseRight();
        int VK_MouseMiddle();
        int VK_MouseEx1();
        int VK_MouseEx2();

        // Mouse Location
        int MouseX();
        int MouseY();

        int MouseMove(int x, int y);
        int MouseShift(int x, int y);

        int MousePress(int code);
        int MouseRelease(int code);
        int MouseClick(int code);

        int MouseWheel(int notches);



        // Keyboard KeyCode
        int VK_Escape();
        int VK_Tab();
        int VK_CapsLock();
        int VK_Space();
        int VK_ContextMenu();
        int VK_Enter();
        int VK_BackSpace();

        int VK_Shift();
        int VK_Control();
        int VK_Alt();

        int VK_LShift();
        int VK_LControl();
        int VK_LWindows();
        int VK_LAlt();
        int VK_RShift();
        int VK_RControl();
        int VK_RWindows();
        int VK_RAlt();

        int VK_PrintScreen();
        int VK_ScrollLock();
        int VK_Pause();
        int VK_Insert();
        int VK_Home();
        int VK_Delete();
        int VK_End();
        int VK_PageUp();
        int VK_PageDown();
        int VK_Up();
        int VK_Down();
        int VK_Left();
        int VK_Right();

        int VK_NumLock();
        int VK_NumDivide();
        int VK_NumMultiply();
        int VK_NumSubtract();
        int VK_NumAdd();
        int VK_NumDecimal();
        int VK_Num0();
        int VK_Num1();
        int VK_Num2();
        int VK_Num3();
        int VK_Num4();
        int VK_Num5();
        int VK_Num6();
        int VK_Num7();
        int VK_Num8();
        int VK_Num9();

        // Function Key
        int VK_Func1();
        int VK_Func2();
        int VK_Func3();
        int VK_Func4();
        int VK_Func5();
        int VK_Func6();
        int VK_Func7();
        int VK_Func8();
        int VK_Func9();
        int VK_Func10();
        int VK_Func11();
        int VK_Func12();
        int VK_Func13();
        int VK_Func14();
        int VK_Func15();
        int VK_Func16();
        int VK_Func17();
        int VK_Func18();
        int VK_Func19();
        int VK_Func20();

        // JIS Key
        int VK_InputMethod();
        int VK_Convert();
        int VK_NonConvert();

        // OEM Key
        int VK_MARK_1();
        int VK_MARK_2();
        int VK_MARK_3();
        int VK_MARK_4();
        int VK_MARK_5();
        int VK_MARK_6();
        int VK_MARK_7();
        int VK_MARK_102();
        int VK_MARK_MINUS();
        int VK_MARK_PLUS();
        int VK_MARK_COMMA();
        int VK_MARK_PERIOD();

        // Function Key
        int VK_Volume_Mute();
        int VK_Volume_Up();
        int VK_Volume_Down();

        int VK_Media_Play_Pause();
        int VK_Media_Prev_Track();
        int VK_Media_Next_Track();
        int VK_Media_Stop();


        // Key Emulate
        void KeyClick(int code);
        void KeyPress(int code);
        void KeyRelease(int code);

        void KeyClick_with(int code, boolean shift, boolean ctrl, boolean alt, boolean win);

        // Key State
        boolean isKeyPressed(int code);
        boolean isKeyReleased(int code);

        boolean isShiftPressed();
        boolean isControlPressed();
        boolean isAltPressed();
    }
}
