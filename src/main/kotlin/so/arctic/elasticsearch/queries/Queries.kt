package so.arctic.elasticsearch.queries

import co.elastic.clients.elasticsearch._types.FieldValue
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery
import co.elastic.clients.util.ObjectBuilder


fun termsQuery(field: String, values: List<FieldValue>): TermsQuery =
   TermsQuery.of { t ->
      t.field(field)
         .terms { ts -> ts.value(values) }
   }

@DslMarker
annotation class QueryDSL

@QueryDSL
class QueryBuilder {
   fun nested(apply: NestedQuery.Builder.() -> Unit): ObjectBuilder<Query> = Query.Builder().nested {
      it.apply(apply)
   }

   companion object {
      inline fun builder(block: QueryBuilder.() -> Unit) = QueryBuilder().apply(block)
   }
}


