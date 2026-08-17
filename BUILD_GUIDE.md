# 📋 دليل البناء على GitHub

## المطلوب لبناء التطبيق على GitHub Actions

### 1. رفع المشروع إلى GitHub

```bash
cd /workspace
git init
git add .
git commit -m "Initial commit - إدارة العهد والمصروفات"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
git push -u origin main
```

### 2. ما يحدث تلقائيًا بعد الرفع

عند رفع الكود إلى GitHub، سيقوم GitHub Actions بالآتي:

1. ✅ تنزيل الكود من المستودع
2. ✅ إعداد JDK 17
3. ✅ تنزيل Gradle Wrapper JAR تلقائيًا
4. ✅ بناء Debug APK
5. ✅ بناء Release APK (غير موقع)
6. ✅ رفع الملفات كـ Artifacts لمدة 30 يوم

### 3. كيفية تحميل APK

1. اذهب إلى مستودع GitHub الخاص بك
2. انقر على تبويب **Actions** في الأعلى
3. اختر workflow **"Android Build"** من القائمة الجانبية
4. انقر على أحدث workflow run (سيكون في الأعلى)
5. انتظر حتى تكتمل جميع الخطوات (علامة ✓ خضراء)
6. انزل إلى قسم **Artifacts** في أسفل الصفحة
7. انقر على:
   - `app-debug` لتحميل نسخة التجربة
   - `app-release-unsigned` لتحميل نسخة الإنتاج (تحتاج توقيع)

### 4. ملفات المشروع الأساسية

```
/workspace/
├── .github/workflows/
│   └── android-build.yml    ← ملف إعدادات GitHub Actions
├── app/
│   ├── src/main/
│   │   ├── java/           ← كود Kotlin
│   │   ├── res/            ← الموارد (strings, layouts, etc.)
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts    ← اعتماديات التطبيق
├── build.gradle.kts        ← إعدادات المشروع العامة
├── settings.gradle.kts     ← إعدادات Gradle
├── gradle.properties       ← خصائص Gradle
├── gradlew                 ← скрипт البناء لـ Linux/Mac ✓
├── gradlew.bat            ← скрипт البناء لـ Windows ✓
└── gradle/wrapper/
    └── gradle-wrapper.properties  ← إصدار Gradle ✓
```

### 5. ملاحظات مهمة

#### ✅ ما هو موجود بالفعل:
- ملف workflow جاهز (.github/workflows/android-build.yml)
- скрипты gradlew و gradlew.bat
- gradle-wrapper.properties
- جميع ملفات الكود والمصادر

#### ⚠️ ما لا تحتاجه:
- ~~gradle-wrapper.jar~~ (يتم تنزيله تلقائيًا)
- ~~أي مفاتيح توقيع~~ (للبناء الأولي غير الموقع)
- ~~أي إعدادات إضافية~~

#### 🔧 للبناء المحلي (اختياري):

```bash
# تأكد من وجود JDK 17
java -version

# أعطِ صلاحية التنفيذ
chmod +x gradlew

# بناء Debug APK
./gradlew assembleDebug

# بناء Release APK
./gradlew assembleRelease

# مكان الملفات:
# Debug: app/build/outputs/apk/debug/app-debug.apk
# Release: app/build/outputs/apk/release/app-release-unsigned.apk
```

### 6. حل المشاكل الشائعة

#### المشكلة: Workflow لا يعمل
**الحل:** تأكد من:
- اسم الفرع هو `main` أو `master`
- ملف `.github/workflows/android-build.yml` موجود
- جميع ملفات Gradle موجودة

#### المشكلة: خطأ في البناء
**الحل:** 
- تحقق من سجلات Actions لمعرفة الخطأ التفصيلي
- تأكد من توافق إصدارات Kotlin و Gradle

#### المشكلة: APK لا يُبنى
**الحل:**
- تحقق من وجود جميع ملفات الكود
- تأكد من صحة syntax في ملفات build.gradle.kts

### 7. التوقيع والإصدار النهائي

للحصول على APK موقع للإنتاج:

1. أنشئ keystore محليًا
2. أضف بيانات التوقيع إلى gradle.properties
3. عدّل build.gradle.kts لإضافة signingConfigs
4. شغّل workflow مرة أخرى

## الخلاصة

**الحد الأدنى المطلوب:**
```bash
git add .
git commit -m "Initial commit"
git push origin main
```

ثم انتقل إلى تبويب Actions في GitHub وانتظر اكتمال البناء! 🎉
