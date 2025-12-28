@file:OptIn(DelicateCoroutinesApi::class)

package org.tywrapstudios.hookt.test

import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.assertThrows
import org.tywrapstudios.hookt.EnvNotFoundException
import org.tywrapstudios.hookt.Webhook
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.execute
import org.tywrapstudios.hookt.getEnv
import org.tywrapstudios.hookt.getEnvOrElse
import org.tywrapstudios.hookt.getEnvOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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
            assertEquals(HttpStatusCode.NoContent, result.status)
            println("Do not forget to check Discord!")
        }
        println("End of blocking")
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
            assertEquals(HttpStatusCode.NoContent, result.status)
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
}