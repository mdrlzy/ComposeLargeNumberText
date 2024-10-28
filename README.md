# ComposeLargeNumberText
[![](https://jitpack.io/v/mdrlzy/ComposeLargeNumberText.svg)](https://jitpack.io/#mdrlzy/ComposeLargeNumberText)

Material3 Text that shortens large numbers using scale symbols(e.g. k, M, G, T, ..) to fit into maxLines

https://github.com/user-attachments/assets/d70b0394-0cc5-4569-995f-d2eb5094f3c3

### Installation

- Update your `settings.gradle`
```
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Update your module level `build.gradle`. Check latest version on the [tags page](https://github.com/mdrlzy/ComposeLargeNumberText/tags)
```
dependencies {
    implementation 'com.github.mdrlzy:ComposeLargeNumberText:$version'
}
```

### Usage
```kotlin
LargeNumberText(
    number = BigDecimal("1"),
    buildText = { formattedNumber ->
        "Some text $formattedNumber"
    },
)
```