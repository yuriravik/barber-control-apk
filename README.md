# Barber Control — App Android

App Android nativo para o sistema de gestão de barbearia **barber-control**, consumindo o backend REST do projeto [barber-control-back](https://github.com/yuriravik/barber-control-back).

## Stack

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Kotlin 1.9.22 |
| UI | Jetpack Compose + Material 3 |
| Arquitetura | MVVM + Clean Architecture |
| DI | Hilt 2.50 |
| Rede | Retrofit 2 + OkHttp 4 + Moshi |
| Banco local | Room 2.6 |
| Persistência de token | DataStore Preferences |
| Async | Coroutines + Flow |
| Navegação | Navigation Compose |

---

## Como rodar

### Pré-requisitos

- Android Studio Hedgehog (2023.1.1) ou mais recente
- JDK 17
- Android SDK API 34

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/yuriravik/barber-control-apk.git
cd barber-control-apk

# 2. Abra no Android Studio (File > Open)
# 3. Aguarde o Gradle sync
# 4. Execute no emulador ou dispositivo físico (Run > Run 'app')
```

### URL da API

A URL base da API é configurada via `BuildConfig`:

| Flavor | URL |
|--------|-----|
| **debug** | `http://10.0.2.2:3000/` (emulador → localhost) |
| **release** | `https://api.barbercontrol.com.br/` |

Para alterar, edite o arquivo `app/build.gradle.kts`:

```kotlin
buildConfigField("String", "BASE_URL", "\"https://sua-api.com/\"")
```

---

## Arquitetura

```
app/src/main/java/com/barbercontrol/app/
├── core/
│   ├── di/                   # Módulos Hilt (AppModule, NetworkModule, DatabaseModule)
│   ├── network/              # AuthInterceptor, Resource<T> (sealed class)
│   ├── security/             # TokenManager (DataStore)
│   └── util/                 # Constants
├── data/
│   ├── local/                # Room: AppDatabase, DAO, Entidades
│   ├── remote/               # ApiService (Retrofit), DTOs
│   └── repository/           # AuthRepository (ponte data ↔ domain)
├── domain/
│   ├── model/                # User (modelo de domínio)
│   └── usecase/              # LoginUseCase
└── presentation/
    ├── components/           # PrimaryButton, PrimaryTextField
    ├── navigation/           # Screen (sealed class), NavGraph
    ├── theme/                # Color, Type, Theme (Material 3)
    └── screens/
        ├── splash/           # SplashScreen + SplashViewModel
        ├── login/            # LoginScreen + LoginViewModel
        └── home/             # HomeScreen + HomeViewModel
```

### Fluxo de navegação

```
App inicia
    └── SplashScreen
        ├── Token salvo?  YES → HomeScreen
        └── Token salvo?  NO  → LoginScreen
                                    └── Login OK → HomeScreen
                                                      └── Logout → LoginScreen
```

---

## Tratamento de erros de rede

A sealed class `Resource<T>` (em `core/network/Resource.kt`) encapsula todos os resultados assíncronos:

```kotlin
sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}
```

---

## Próximos passos para integrar endpoints reais

1. **Conectar ao backend real**
   - Atualizar `BASE_URL` no `build.gradle.kts`
   - Descomentar endpoints em `ApiService.kt`

2. **Criar DTOs e entidades por feature**
   - `ServiceDto`, `BarberDto`, `AppointmentDto`
   - Entidades Room correspondentes

3. **Implementar telas de negócio**
   - Lista de barbeiros e serviços
   - Agendamento de horário
   - Minhas reservas
   - Perfil do usuário

4. **Refresh token**
   - Adicionar `Authenticator` no OkHttp para renovar token automaticamente

5. **Notificações push (FCM)**
   - Integrar Firebase Messaging para lembretes de agendamento

6. **Testes**
   - Testes unitários para UseCases e ViewModels (JUnit + MockK)
   - Testes de UI com Compose Testing

---

## Contribuição

1. Crie uma branch: `git checkout -b feature/nome-da-feature`
2. Commit: `git commit -m "feat: descrição"`
3. Push: `git push origin feature/nome-da-feature`
4. Abra um Pull Request

---

## Licença

MIT
