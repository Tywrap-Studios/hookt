# hookt

A simple DSL wrapper to interact with Discord webhooks in Kotlin.

## Usage

You can create a new `DiscordWebhook` instance in the following way:

```kotlin
fun main() {
    val url = getEnv("WEBHOOK_URL")
    val webhook = Webhook(url)
}
```

### Simple messages

Sending simple messages can be done in one command:

```kotlin
fun main() {
    /* Other Code */
    webhook.execute("Hello!")
}
```

### Embeds

However, you may want to use more than just plain messages, and send rich embeds instead.
This is fully possible too using our built-in DSL functions:

```kotlin
fun main() {
    /* Other Code */
    webhook.execute {
        content = "Just some text"
        embed {
            title = "I'm an embed"
            description = "Lorem ipsum is an awesome little long text!"
            // Add one or more fields using the field function
            field {
                name = "Field 1"
                value = "Value 1"
            }
            field {
                name = "Field 2"
                value = "Value 2"
                // You can use inline to make the fields appear side-by-side
                inline = true
            }
            field {
                name = "Field next to field 2"
                value = "Value next to value 2"
                inline = true
            }
            footer("Footer text!")
            timestampNow()
        }
    }
}
```

### Components V2

Discord (somewhat recently) added new message components in Components V2, this library
allows for the use of the ones available for non-app webhooks using the `component` DSL
function:

```kotlin
fun main() {
    /* Other Code */
    webhook.execute {
        component<SectionComponent> {
            addComponent<TextDisplayComponent> {
                content = "# Hello world!"
            }
            addComponent<TextDisplayComponent> {
                content = "I am a text display"
            }
            addComponent<TextDisplayComponent> {
                content = "-# I should *be tiny*"
            }
            addAccessory<ThumbnailComponent> {
                media = UnfurledMediaItem("https://foxpictures.com/cute_fox")
            }
        }
        component<SeparatorComponent> {
            divider = true
        }
        component<TextDisplayComponent> {
            content = "Have some more fox pictures!"
        }
        component<MediaGalleryComponent> {
            item {
                media = UnfurledMediaItem("https://foxpictures.com/fun_fox")
            }
            item {
                media = UnfurledMediaItem("https://foxpictures.com/scary_fox")
                description = "Very scary fox! Unspoiler if you dare..."
                spoiler = true
            }
        }
    }
}
```

<details>
    <summary>Available components</summary>

```java
Name(Discord type value)
```

```java
Section(9)

TextDisplay(10)

Thumbnail(11)

MediaGallery(12)

File(13)

Separator(14)

Container(17)
```

</details>

---

And much more! All the functions, objects and classes in this package are documented
with Javadocs, so finding out what something does and how to use it should be easy
if you've got the sources jar. Happy webhooking!

## Gradle Setup ![Release](https://jitpack.io/v/Tywrap-Studios/hookt.svg)

Adding this to your Gradle project is easy:

### Groovy

```groovy
repositories {
    maven {
        name = "JitPack"
        url = "https://jitpack.io"
    }
}

dependencies {
    implementation "com.github.Tywrap-Studios:hookt:${project.hookt_version}"
}
```

### Kotlin DSL

```kotlin
repositories {
    maven("https://jitpack.io") {
        name = "Jitpack"
    }
}

dependencies {
    implementation("com.github.Tywrap-Studios:hookt:${project.hookt_version}")
}
```

Or using `libs.versions.toml`:

```toml
[versions]
hookt = "<VERSION>"

[libraries]
hookt = { module = "com.github.Tywrap-Studios:hookt", version.ref = "hookt" }
```

```kotlin
/* repositories */
dependencies {
    implementation(libs.hookt)
}
```
