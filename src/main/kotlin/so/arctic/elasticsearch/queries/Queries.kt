package so.arctic.elasticsearch.queries

import co.elastic.clients.elasticsearch._types.FieldValue
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery


fun TermsQuery.Builder.termsQuery(filed: String, values: List<FieldValue>): Query =
   QueryBuilders.terms { t ->
      t.field(filed).terms { ts -> ts.value(values) }
   }