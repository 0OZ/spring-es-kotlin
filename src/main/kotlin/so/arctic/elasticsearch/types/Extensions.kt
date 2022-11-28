package so.arctic.elasticsearch.types

import co.elastic.clients.elasticsearch._types.DistanceUnit

/**
 * adds the unit to the end of the string
 *
 * "10".appendUnit() -> "10km"
 */
fun String.appendUnit(unit: DistanceUnit = DistanceUnit.Kilometers) =
   this.plus(unit.jsonValue())
