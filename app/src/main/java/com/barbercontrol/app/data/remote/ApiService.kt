package com.barbercontrol.app.data.remote

import com.barbercontrol.app.data.remote.dto.LoginRequestDto
import com.barbercontrol.app.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Interface Retrofit que define os endpoints do backend barber-control.
 *
 * Endpoints comentados serão implementados conforme o backend for evoluindo.
 * Todos os endpoints protegidos recebem automaticamente o header Authorization
 * via [com.barbercontrol.app.core.network.AuthInterceptor].
 */
interface ApiService {

    // ── Autenticação ──────────────────────────────────────────────────────────

    /** Realiza login e retorna o token JWT. */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    // @POST("auth/register")
    // suspend fun register(@Body request: RegisterRequestDto): LoginResponseDto

    // @POST("auth/refresh")
    // suspend fun refreshToken(@Body request: RefreshTokenRequestDto): LoginResponseDto

    // ── Usuário ───────────────────────────────────────────────────────────────

    // @GET("users/me")
    // suspend fun getProfile(): UserDto

    // @PUT("users/me")
    // suspend fun updateProfile(@Body request: UpdateProfileRequestDto): UserDto

    // ── Serviços ──────────────────────────────────────────────────────────────

    // @GET("services")
    // suspend fun getServices(): List<ServiceDto>

    // ── Barbeiros ─────────────────────────────────────────────────────────────

    // @GET("barbers")
    // suspend fun getBarbers(): List<BarberDto>

    // ── Agendamentos ──────────────────────────────────────────────────────────

    // @GET("appointments")
    // suspend fun getAppointments(): List<AppointmentDto>

    // @POST("appointments")
    // suspend fun createAppointment(@Body request: CreateAppointmentRequestDto): AppointmentDto

    // @DELETE("appointments/{id}")
    // suspend fun cancelAppointment(@Path("id") id: String)
}
