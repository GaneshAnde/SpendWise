package com.ganesh.spendwise.data.datasource

interface FileDataSource {
    suspend fun read(fileName : String) : String
}