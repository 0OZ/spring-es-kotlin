package so.arctic.elasticsearch.types

import co.elastic.clients.elasticsearch._types.FieldValue

inline fun <reified T : Any> List<T>.toFieldValues(): List<FieldValue> =
   this.map {
      FieldValue.of { fb ->
         when (T::class) {
            String::class -> fb.stringValue(it.toString())
            Double::class -> fb.doubleValue(it as Double)
            Long::class -> fb.longValue(it as Long)
            Boolean::class -> fb.booleanValue(it as Boolean)
            else -> fb.stringValue(it.toString())
         }
      }
   }
