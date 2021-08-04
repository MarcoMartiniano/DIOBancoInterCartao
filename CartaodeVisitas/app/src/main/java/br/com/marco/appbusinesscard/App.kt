package br.com.marco.appbusinesscard

import android.app.Application
import br.com.marco.appbusinesscard.data.AppDatabase
import br.com.marco.appbusinesscard.data.BusinessCardRepository


class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao())}
}