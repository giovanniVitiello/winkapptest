# WinkAppTest

WinkAppTest è un'app Android sviluppata con **Jetpack Compose** seguendo l'architettura **MVI** (Model-View-Intent). 
L'app è pensata per essere modulare, scalabile e facilmente manutenibile, con un'attenzione particolare a performance 
e gestione degli stati di caricamento/errore.

---

## Tecnologie e librerie principali

- **Jetpack Compose**: UI dichiarativa e moderna.
- **MVI**: Architettura unidirezionale per separare logica di business e UI.
- **Paginazione custom**: implementata internamente per avere controllo completo sul caricamento e caching dei dati paginati.
- **Retrofit** con caching di 10 minuti: per le chiamate di rete efficienti.
- **Coil** con `AsyncImage`: per il caricamento asincrono delle immagini.
- **Hilt**: Dependency Injection semplice e sicura.
- **Gestione degli stati con `Resource`**: supporto integrato a **Loading**, **Success** e **Error** negli UseCase tramite Flow.

Esempio di conversione di un `Flow` in `Resource`:

```kotlin
fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> { data -> Resource.Success(data) }
        .onStart { emit(Resource.Loading) }
        .catch { throwable ->
            emit(Resource.Error(throwable.message.orEmpty(), throwable))
        }
}
```

---

## Configurazione API Unsplash

Per far funzionare correttamente l'app è necessario inserire la **chiave API di Unsplash**.

1. Crea un file `local.properties` nella root del progetto (se non esiste già).
2. Aggiungi la tua chiave con la seguente riga: 
   UNSPLASH_ACCESS_KEY=la_tua_chiave_unsplash
3. Nel codice dell'app, leggi la chiave tramite `BuildConfig` o `local.properties`: 
   val unsplashKey = BuildConfig.UNSPLASH_ACCESS_KEY