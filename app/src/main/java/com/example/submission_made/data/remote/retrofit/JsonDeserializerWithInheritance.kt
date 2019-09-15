package com.example.submission_made.data.remote.retrofit

import com.google.gson.*
import java.lang.reflect.Type


class JsonDeserializerWithInheritance<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): T {
        val jsonObject = json.asJsonObject
        val classNamePrimitive = jsonObject.get("type") as JsonPrimitive

        val className = classNamePrimitive.asString

        val clazz: Class<*>
        try {
            clazz = Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e.message)
        }

        return context.deserialize(jsonObject, clazz)
    }
}