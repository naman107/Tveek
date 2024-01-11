package com.example.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.utils.TV_SHOW

@Entity(tableName = TV_SHOW)
data class TvShowEntity(
    @PrimaryKey
    val id: Long,
    val isLiked: Boolean = false
)