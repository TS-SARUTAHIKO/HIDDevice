# HIDDevice

入力端末のエミュレートを行うためのパッケージ

実行部分は C++ で作成した DLL を JNA を用いて呼び出す
DLL の配置が resources/win32-x86-64/HID_IO.dll であるため実行環境に依存する

他の環境で扱うにはDLLファイルを他の環境向けにコンパイルして、複数のDLLを用いる必要があると思われる
(ex) 32bit環境 resources/win32-x86/HID_IO.dll


## 導入

```build.gradle
repositories {
    maven { url 'https://github.com/TS-SARUTAHIKO/KotlinLibrary/raw/master/repository/' }
    maven { url 'https://github.com/TS-SARUTAHIKO/HIDDevice/raw/master/repository/' }
}
dependencies {
    // Omitted
    
    implementation 'com.xxxsarutahikoxxx.kotlin:HIDDevice:1.0.1'
}
```


## 使用方法・マウス

マウスを移動する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        Mouse + (100 to 200) // マウスポインタを(100, 200)の増加値でスライド移動する

        MouseX + 100
        MouseY + 200
        
        mX + 100
        mY + 200
        
        Mouse + listOf( 10 to 10, 20 to 0, 0 to 10 ) // 連続入力
        mX + 10 + 20 + 10 // 連続入力
    }
```

マウスを指定場所に移動する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        mPoint = 100 to 300 // マウスポインタを 座標(100, 300)に移動する
    }
```

マウス・ホイールを回転する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        MouseWheel + 5 // ホイールの上回転
        MouseWheel - 5 // ホイールの下回転

        mW + 5
        mW - 5
        
        mW + listOf(1, 1, 1) // 連続入力
    }
```

マウス・ボタンをクリックする

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        + LPress + LRelease // 左ボタンを押す＋離す( = クリック)

        + LClick // 左ボタンをクリックする
        + MClick // 中ボタン
        + RClick // 右ボタン

        + Ex1Click // Ex1 ボタン(戻るボタン・Backボタン)
        + Ex2Click // Ex2 ボタン(進むボタン・Goボタン)


        LClick { mX + 500 } //　左ボタンを押しながら動作を実行した後に、左ボタンを離す
        
        + LClick.x2 // 左ダブル・クリック
        + RClick.x3 // 右トリプル・クリック
    }
```


## 使用方法・キーボード

キーを入力する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        + Enter   // Enter Key 
        + Tab     // Tab Key
        + F2 + F4 // F2 Key, F4 Key
    }
```

文字形式で入力する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        + 'a' // A Key
        + 'A' // Shift + A Key
        + _A // A Key
        
        + ';' // SemiColon Key
        
        + "sample" // s,a,m,p,l,e Key
    }
```

メタキーを押しながら実行する<br>
メタキーを離しながら実行する

```main.kt
    HIDDeviceJIS(hostMode = true, port = null).apply {
        Shift { + Left } // Shift 押しながら Left
        Alt { + _A }    // Alt 押しながら a
        Ctrl { + 'z' }  // Ctrl 押しながら z
        
        + "lower " + Shift{ + "upper" } // 出力 = "lower UPPER"
        
        Shift{ + "upper " + UnShift{ + "lower" } } // 出力 = "UPPER lower"
    }
```


## 拡張

Kotlin の拡張変数機能で特定の動作にネーミングを付けて使用する

```
    val HIDDevice.Copy : KeyClick get() = VK_C.ctrl.toKeyClick
    val HIDDevice.Cut : KeyClick get() = VK_X.ctrl.toKeyClick
    val HIDDevice.Paste : KeyClick get() = VK_V.ctrl.toKeyClick
    val HIDDevice.Undo : KeyClick get() = VK_Z.ctrl.toKeyClick


    HIDDeviceJIS(hostMode = true, port = null).apply {
        + Copy + Paste
        
        + Undo.x2
    }
```