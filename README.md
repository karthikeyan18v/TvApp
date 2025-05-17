# TvApp
# 📺 Android TV Video App

This project is a basic Android TV-compatible app built using Kotlin and ExoPlayer. It displays a grid of video content (streaming URLs or local assets) and allows users to play them in full-screen mode. The app supports D-pad navigation, remote control actions, and is optimized for both Android TV and Android Phones.

---

## 🚀 Features

- 🎞️ TV-style grid layout with thumbnails and titles
- 🎮 D-Pad/remote navigation and focus scaling
- 📺 Full-screen video playback using **ExoPlayer**
- ⏯️ Play, pause, stop, back support via remote
- ⚡ Smooth performance on TV emulators and real devices
- 📱 Compatible with Android TV and Android phones

---

## 📦 Build Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/tv-video-app.git
   cd tv-video-app
   ```

2. Open the project in **Android Studio Arctic Fox+**

3. Build the APK:
   - **Build > Build Bundle(s) / APK(s) > Build APK(s)**

4. Or run via terminal:
   ```bash
   ./gradlew assembleDebug
   ```

5. The APK will be generated in:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📊 Performance & Testing Notes

- Tested on:
  - ✅ Android TV Emulator (API 29)
  - ✅ Xiaomi Mi Box S
  - ✅ Android Phone (Pixel 4)
- Memory usage optimized using RecyclerView & ExoPlayer lifecycle handling
- Playback buffering handled efficiently for smooth streaming
- No crashes or memory leaks observed during testing

---

## 🤝 Contributing
Feel free to fork the repo and submit pull requests for new features, bug fixes, or improvements.

---

## 📜 License
This project is open-source and available under the [MIT License](LICENSE).

