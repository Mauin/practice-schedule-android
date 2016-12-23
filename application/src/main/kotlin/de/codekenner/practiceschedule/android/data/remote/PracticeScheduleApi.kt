package de.codekenner.practiceschedule.data.remote

import de.codekenner.practiceschedule.data.remote.receive.Auth
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.data.remote.receive.User
import de.codekenner.practiceschedule.data.remote.send.AuthRequest
import de.codekenner.practiceschedule.data.remote.send.UserRequest
import io.reactivex.Single
import retrofit2.http.*

interface PracticeScheduleApi {

	@POST("auth")
	fun authenticate(@Body authRequest: AuthRequest): Single<Auth>

	@POST("users")
	fun createUser(@Body userRequest: UserRequest): Single<User>

	@GET("user")
	fun getUser(): Single<User>

	@PUT("users")
	fun updateUser(): Single<Int>

	@PUT("groups/{id}/user/{user_id}")
	fun addUserToGroup(@Path("id") id: Long, @Path("user_id") userId: Long): Single<Int>

	@DELETE("groups/{id}/user/{user_id}")
	fun removeUserFromGroup(@Path("id") id: Long, @Path("user_id") userId: Long): Single<Int>

	@GET("groups/{id}")
	fun getGroup(@Path("id") id: Long): Single<Int>

	@PUT("groups/{id}")
	fun updateGroup(@Path("id") id: Long): Single<Int>

	@DELETE("groups/{id}")
	fun deleteGroup(@Path("id") id: Long): Single<Int>

	@POST("schedules/{schedule_id}/periods")
	fun createPeriod(@Path("schedule_id") scheduleId: Long): Single<Int>

	@DELETE("schedules/{schedule_id}/periods/{id}")
	fun removePeriod(@Path("schedule_id") scheduleId: Long, @Path("id") id: Long): Single<Int>

	@GET("schedules")
	fun getSchedules(): Single<List<Schedule>>

	@GET("schedules")
	fun createSchedule(): Single<Int>

	@GET("schedules/{id}")
	fun getSchedule(@Path("id") id: Long): Single<Schedule>

	@PUT("schedules/{id}")
	fun updateSchedule(@Path("id") id: Long): Single<Int>

	@DELETE("schedules/{id}")
	fun deleteSchedule(@Path("id") id: Long): Single<Int>

}
