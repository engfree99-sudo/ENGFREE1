# دليل المساهمة في المشروع

## كيفية البناء على GitHub Actions

عند رفع الكود إلى GitHub، سيقوم GitHub Actions تلقائيًا ببناء التطبيق.

### الخطوات:

1. **رفع المشروع إلى GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/CustodyExpenses.git
   git branch -M main
   git push -u origin main
   ```

2. **الانتقال إلى تبويب Actions**
   - افتح مستودع GitHub
   - انقر على تبويب "Actions"
   - اختر "Android Build" workflow

3. **تحميل APK**
   - بعد اكتمال البناء، انقر على workflow run
   - انزل إلى قسم "Artifacts"
   - حمّل ملف APK المطلوب

### البناء المحلي (اختياري)

```bash
# بناء Debug APK
./gradlew assembleDebug

# بناء Release APK (غير موقع)
./gradlew assembleRelease

# تثبيت على جهاز متصل
./gradlew installDebug
```

### متطلبات البناء المحلي

- JDK 17
- Android SDK 34
- Android Studio Hedgehog أو أحدث (اختياري)

## هيكل الملفات المهمة

```
├── .github/workflows/
│   └── android-build.yml    # إعدادات GitHub Actions
├── app/
│   ├── src/main/
│   │   ├── java/           # كود Kotlin
│   │   └── res/            # الموارد (layouts, strings, etc.)
│   └── build.gradle.kts    # اعتماديات التطبيق
├── build.gradle.kts        # إعدادات المشروع العامة
├── settings.gradle.kts     # إعدادات Gradle
├── gradlew                 # скрипт البناء لـ Linux/Mac
└── gradlew.bat            # скрипт البناء لـ Windows
```

## ملاحظات مهمة

- ملف `gradle-wrapper.jar` سيتم تنزيله تلقائيًا بواسطة GitHub Actions
- لا حاجة لإضافة ملفات JAR يدويًا
- تأكد من وجود جميع ملفات الكود قبل الرفع
