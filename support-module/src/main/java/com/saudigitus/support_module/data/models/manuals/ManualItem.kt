package com.saudigitus.support_module.data.models.manuals


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class ManualItem(
    @JsonProperty("uid")
    @PrimaryKey val uid: Int,

    @JsonProperty("title")
    @ColumnInfo(name = "title")  val path: String,

    @JsonProperty("subtitle")
    @ColumnInfo(name = "subtitle")  val subtitle: String?,

    @JsonProperty("path")
    @ColumnInfo(name = "path") val title: String?
)
