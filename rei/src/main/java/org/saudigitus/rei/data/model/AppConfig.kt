package org.saudigitus.rei.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.saudigitus.rei.utils.Utils.toJson

@JsonIgnoreProperties(ignoreUnknown = true)
data class AppConfig(
    @JsonProperty("stageProgram")
    val stageProgram: String,
    @JsonProperty("programs")
    val programs: List<Program>,
    @JsonProperty("stageVaccination")
    val stageVaccination: String,
    @JsonProperty("ccvDataElement")
    val ccvDataElement: String,
) {
    override fun toString() = this.toJson()
}
