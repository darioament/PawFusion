package fina.dario.pawfusion.core.database

import android.content.Context
import androidx.datastore.core.DataStore
import fina.dario.pawfusion.models.dao.createDataStore
import fina.dario.pawfusion.models.dao.dataStoreFileName
import java.util.prefs.Preferences

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)