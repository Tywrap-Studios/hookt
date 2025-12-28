@file:OptIn(DelicateCoroutinesApi::class)

package org.tywrapstudios.hookt.test

import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.tywrapstudios.hookt.Webhook
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.execute
import kotlin.test.Test
import kotlin.test.assertEquals

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
            val url = System.getenv("DISCORD_URL")
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
            assertEquals(HttpStatusCode.OK, result.status)
            println("Do not forget to check Discord!")
        }
        println("End of blocking")
    }

    @Test
    fun testSingleExecution() {
        runBlocking {
            val url = System.getenv("DISCORD_URL")
            val result = execute(url, "Hello!") {
                embed {
                    title = "I am an embed!"
                }
            }.second
            assertEquals(HttpStatusCode.OK, result.status)
        }
    }
}