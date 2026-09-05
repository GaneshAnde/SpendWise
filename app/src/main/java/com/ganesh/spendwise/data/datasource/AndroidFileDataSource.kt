package com.ganesh.spendwise.data.datasource

import android.content.Context

class AndroidFileDataSource(
    val context : Context
) : FileDataSource {
    override suspend fun read(fileName: String): String {
       return context.assets
           .open(fileName)
           .bufferedReader()
           .use { it.readText() }
    }
}