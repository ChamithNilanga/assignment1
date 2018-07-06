package com.gapstars.cwe.assessment.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cwe on 3/6/2018.
 */

public class ServiceGenerator {
        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        private static Retrofit.Builder builder;
        private static Retrofit retrofit = null;

        static {
                Gson gson = new GsonBuilder()
                        .setDateFormat("E, dd MMM yyyy HH:mm:ss Z")
                        .create();

                builder = new Retrofit.Builder()
                        .baseUrl(Constant.API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));
        }

        public static <S> S createService(Class<S> serviceClass) {
                httpClient.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Accept", "application/json")
                                        .header("Content-Type", "application/json")
                                        .method(original.method(), original.body());
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                        }
                });
                httpClient.authenticator(new Authenticator() {
                        @Override
                        public Request authenticate(Route route, Response response) throws IOException {
                                if (responseCount(response) >= Constant.NUMBER_OF_RETRIES) {
                                        return null; // If we've failed 3 times, give up.
                                }
                                return response.request();
                        }
                });

//                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                httpClient.addInterceptor(loggingInterceptor);
                httpClient.connectTimeout(Constant.CONNECTION_TIME_OUT, TimeUnit.MINUTES);
                httpClient.readTimeout(Constant.READ_TIME_OUT, TimeUnit.MINUTES);
                httpClient.writeTimeout(Constant.WRITE_TIME_OUT, TimeUnit.MINUTES);
                OkHttpClient client = httpClient.build();
                Retrofit retrofit = builder.client(client).build();
                return retrofit.create(serviceClass);
        }

        private static int responseCount(Response response) {
                int result = 1;
                while ((response = response.priorResponse()) != null) {
                        result++;
                }
                return result;
        }
}
