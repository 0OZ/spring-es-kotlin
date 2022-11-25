package dev.zwei.elasticsearch.types

import co.elastic.clients.elasticsearch._types.FieldValue

fun List<String>.toFieldValues(): List<FieldValue> = this.map { FieldValue.of { fb -> fb.stringValue(it) } }