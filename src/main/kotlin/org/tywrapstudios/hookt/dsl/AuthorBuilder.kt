package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.Author

class AuthorBuilder : FormBuilder<Author> {
    var name: String? = null
    var url: String? = null
    var iconUrl: String? = null
    var proxyIconUrl: String? = null

    override fun build(): Author = Author(
        name ?: throw IllegalStateException("Name for author is null"),
        url,
        iconUrl,
        proxyIconUrl,
    )
}