# 📱 إدارة العهد والمصروفات

تطبيق Android لإدارة العهد والمصروفات باللغة العربية مع دعم اتجاه RTL.

## المميزات

- ✅ تسجيل العهد (دائن)
- ✅ تسجيل المصروفات (مدين)
- ✅ حساب الرصيد تلقائيًا
- ✅ كشف الحساب مع البحث والتصفية
- ✅ تصدير Excel مع المشاركة
- ✅ النسخ الاحتياطي والاستعادة
- ✅ يعمل بدون إنترنت (Offline)
- ✅ قاعدة بيانات محلية Room/SQLite

## البناء على GitHub Actions

عند رفع الكود إلى GitHub، سيقوم GitHub Actions تلقائيًا بـ:

1. بناء Debug APK
2. بناء Release APK (غير موقع)
3. رفع الملفات كـ Artifacts

### خطوات الاستخدام:

1. ارفع المشروع إلى مستودع GitHub
2. انتقل إلى تبويب **Actions**
3. اختر workflow "Android Build"
4. انتظر اكتمال البناء
5. حمّل ملف APK من قسم Artifacts

### البناء المحلي:

```bash
# بناء Debug APK
./gradlew assembleDebug

# بناء Release APK
./gradlew assembleRelease

# تثبيت على الجهاز المتصل
./gradlew installDebug
```

## متطلبات النظام

- Android Studio Hedgehog أو أحدث
- JDK 17
- Android SDK 34
- Kotlin 1.9+

## هيكل المشروع

```
app/
├── src/main/
│   ├── java/com/example/custodyexpenses/
│   │   ├── data/          # قاعدة البيانات والنماذج
│   │   ├── repository/    # Repository layer
│   │   ├── viewmodel/     # ViewModels
│   │   ├── ui/            # الشاشات والواجهات
│   │   └── utils/         # أدوات مساعدة
│   └── res/               # الموارد (layouts, strings, etc.)
├── build.gradle.kts
└── ...
```

## الترخيص

MIT License
