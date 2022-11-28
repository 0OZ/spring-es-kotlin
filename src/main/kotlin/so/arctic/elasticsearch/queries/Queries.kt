package so.arctic.elasticsearch.queries

import co.elastic.clients.elasticsearch._types.FieldValue
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery


fun termsQuery(field: String, values: List<FieldValue>): TermsQuery =
   TermsQuery.of { t ->
      t.field(field)
         .terms { ts -> ts.value(values) }
   }