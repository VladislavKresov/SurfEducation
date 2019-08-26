package com.example.surf_education.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {

//    @GET("/конечная/{точка}")                                       //пример запроса на получение данных
//    public Call<POJO-класс,-в-который-преобразовываем-JSON-ответ> getPostWithID(@Path("точка") тип переменная);

// если вставить переменную, тогда ретрофит подставит ее значение вместо {точка} в путь. (например id)

    @GET("/memes")
    public Call<MemesResponse> getPost();

    @POST("/auth/login")
    public Call<AuthResponse> postAuthorizationRequest(@Body AuthorizationRequest authorizationRequest);


}
