@file:OptIn(DelicateCoroutinesApi::class)

package org.tywrapstudios.hookt.test

import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.assertThrows
import org.tywrapstudios.hookt.*
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.types.components.*
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

object Tests {
    @Test
    fun testDsl() {
        val execution: ExecuteBuilder.() -> Unit = {
            content = "Hello!"
            embed {
                title = "I am an embed!"
                description = "You sure are"
                field {
                    name = "And I am a field!!"
                    value = "true"
                }
                field {
                    name = "And I am a field!! But inlined..."
                    value = "yeah, right"
                    inline = true
                }
                field {
                    name = "And I am also field!! But also inlined..."
                    value = "HOLY SHIT!"
                    inline = true
                }
                field {
                    name = "And I am another field!! But NOT inlined..."
                    value = "maybe?"
                    inline = false
                }
                footer("ya")
                author {
                    name = "Author.."
                    url = "https://www.example.com"
                }
            }
        }
        val form = ExecuteBuilder().also(execution).build()
        assertEquals("HOLY SHIT!", form.embeds?.get(0)?.fields?.get(2)?.value)
        assertEquals("https://www.example.com", form.embeds?.get(0)?.author?.url)
        assertEquals("I am an embed!", form.embeds?.get(0)?.title)
    }

    @Test
    fun testExecution() {
        runBlocking {
            val url = getEnv("DISCORD_URL")
            val webhook = Webhook(url)
            val result = webhook.execute {
                content = "Hello!"
                embed {
                    title = "I am an embed!"
                    description = "You sure are"
                    field {
                        name = "And I am a field!!"
                        value = "true"
                    }
                }
                embed {
                    title = "I'm red-ish"
                    rgb(234, 15, 15)
                }
                embed {
                    title = "I'm blue but with hex"
                    hex("#596BECFF")
                }
            }
            assertEquals(HttpStatusCode.NoContent, result.second.status)
            println("Do not forget to check Discord!")
        }
    }

    @Test
    fun testSingleExecution() {
        runBlocking {
            val url = getEnv("DISCORD_URL")
            val result = execute(url, "Hello!") {
                embed {
                    title = "I am an embed!"
                }
            }.second
            assertEquals(HttpStatusCode.OK, result.second.status)
        }
    }

    @Test
    fun testEnv() {
        val val1 = getEnv("VAL1")
        val val2 = getEnvOrNull("VAL2")
        val val3 = getEnvOrNull("VAL3")
        val val4 = getEnvOrElse("VAL4") {
            "world"
        }
        val val5 = getEnvOrElse("VAL5") {
            "world"
        }

        assertEquals("hello", val1)
        assertEquals("big", val2)
        assertNotEquals("not present!", val3)
        assertEquals(null, val3)
        assertThrows<EnvNotFoundException> {
            getEnv("VAL3")
        }
        assertNotEquals("not present.", val4)
        assertEquals("world", val4)
        assertEquals("world!", val5)
        assertNotEquals("world", val5)
    }

    @Test
    fun testComponents() {
        runBlocking {
            val url = getEnv("DISCORD_URL")
            val result = execute(url) {
                component<SectionComponent> {
                    addComponent<TextDisplayComponent> {
                        content = "# Hello!"
                    }
                    addComponent<TextDisplayComponent> {
                        content = "I am a text display"
                    }
                    addComponent<TextDisplayComponent> {
                        content = "-# I should *WORK*"
                    }
                    addAccessory<ThumbnailComponent> {
                        media =
                            UnfurledMediaItem("https://cdn.discordapp.com/attachments/1249069998148812930/1249085058682458173/green_mc_mod_badge.png?ex=69542a1c&is=6952d89c&hm=d7c4850b4b98d7079b23dd93ca999e76b22ec1739eeac381b21a6c94fa061e58")
                    }
                }
                component<ContainerComponent> {
                    addComponent<TextDisplayComponent> {
                        content = "yeah in container"
                    }
                    addComponent<SeparatorComponent> {
                        divider = true
                    }
                    addComponent<TextDisplayComponent> {
                        content = "yeah in container"
                    }
                    addComponent<SeparatorComponent> {
                        divider = false
                    }
                    addComponent<TextDisplayComponent> {
                        content = "yeah in container"
                    }
                    addComponent<SeparatorComponent> {
                        spacing = 2
                    }
                    hex("#32a852")
                    spoiler = true
                }
            }.second
            assertEquals(HttpStatusCode.OK, result.second.status)
        }
    }

    @Test
    fun testFiles() {
        runBlocking {
            val hook = Webhook(getEnv("DISCORD_URL"))
            val execution: ExecuteBuilder.() -> Unit = {
                println(this.files.size)
                file(".gitignore")
                println(this.files.size)
                component<TextDisplayComponent> {
                    content = "I am a text display"
                }
                component<FileComponent> {
                    file = UnfurledMediaItem("attachment://.gitignore")
                }
                component<TextDisplayComponent> {
                    content = "I am a text display"
                }
                println(this.files.size)
                file("LICENSE") {
                    filename = "LICENSE"
                    description = "The License of hookt"
                }
                println(this.files.size)
                component<FileComponent> {
                    file = UnfurledMediaItem("attachment://LICENSE")
                }
            }
            val form = ExecuteBuilder().also(execution).build()
            println(form.files.size)
            assertEquals(0.toULong(), form.attachments?.get(0)?.id)
            assertEquals(1.toULong(), form.attachments?.get(1)?.id)
            assertEquals(".gitignore", form.attachments?.get(0)?.filename)
            assertEquals("LICENSE", form.attachments?.get(1)?.filename)
            assertEquals("The License of hookt", form.attachments?.get(1)?.description)
            assertEquals(2, form.files.size)
            assertEquals(2, form.attachments?.size)
            val result = hook.execute(block = execution)
            assertEquals(HttpStatusCode.OK, result.second.status)
        }
    }

    @Test
    fun testImagery() {
        runBlocking {
            val hook = Webhook(getEnv("DISCORD_URL"))
            val execution: ExecuteBuilder.() -> Unit = {
                file("src/test/resources/test-images/centred.png")
                file("src/test/resources/test-images/offset.png")
                file("src/test/resources/test-images/docs.png")
                component<MediaGalleryComponent> {
                    item {
                        media = UnfurledMediaItem("attachment://centred.png")
                    }
                    item {
                        media = UnfurledMediaItem("attachment://offset.png")
                    }
                    item {
                        media = UnfurledMediaItem("attachment://docs.png")
                    }
                }
                component<SectionComponent> {
                    addComponent<TextDisplayComponent> {
                        content = "# Our documentation!"
                    }
                    addComponent<TextDisplayComponent> {
                        content = "I am a text display"
                    }
                    addAccessory<ThumbnailComponent> {
                        media = UnfurledMediaItem("attachment://docs.png")
                    }
                }
            }
            val form = ExecuteBuilder().also(execution).build()
            println(form.files.size)
            val result = hook.execute(block = execution)
            assertEquals(HttpStatusCode.OK, result.second.status)
        }
    }

    @Test
    fun testDeletion() {
        runBlocking {
            val hook = Webhook(getEnv("DISCORD_URL"))
            val message = hook.execute("Hello!").first
            assertNotNull(message)
            val result = hook.deleteMessage(message.id)
            assertEquals(HttpStatusCode.NoContent, result.status)
        }
    }
}